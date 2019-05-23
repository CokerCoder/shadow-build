import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Builder extends Units {

	public static final String imageLocation = "assets/units/builder.png";
	public static final float BUILDER_SPEED = 0.1f;
	public static final int TRAINING_TIME = 10;
	public static final int FACTORY_COST = 100;

	private boolean isTraining = false;
	private int trainingTime = 0;

	public Builder(float x, float y) throws SlickException {
		super(x, y);
		this.setImage(new Image(imageLocation));
		this.setSpeed(BUILDER_SPEED);
	}

	@Override
	public void update(World world) throws SlickException {
		// If 2 is pressed, starting to train
		if (world.getInput().isKeyPressed(Input.KEY_1) && World.isPositionNotOccupied(super.getPos())) {
			isTraining = true;
			trainingTime = 0;
			// Set the target position to its current position to let it stay at the same
			// position when training
			super.setTarget(super.getPos());
		}

		// Move unless not training
		if (!isTraining) {
			super.update(world);
		}

		if (isTraining) {
			trainingTime += world.getDelta();
			if (trainingTime / 1000 == TRAINING_TIME) {
				trainingTime = 0;
				isTraining = false;
				world.setCurrMetal(world.getCurrMetal() - FACTORY_COST);
				world.getList().add(new Factory(super.getPos().x, super.getPos().y));
			}
		}
	}
}
