import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Truck extends Units {

	public static final String imageLocation = "assets/units/truck.png";
	public static final float TRUCK_SPEED = 0.25f;

	public static final int TRAINING_TIME = 15;

	private boolean isTraining = false;
	private int trainingTime = 0;

	public Truck(float x, float y) throws SlickException {
		super(x, y);
		this.setImage(new Image(imageLocation));
		this.setSpeed(TRUCK_SPEED);
	}

	@Override
	public void update(World world) throws SlickException {
		if (world.getInput().isKeyPressed(Input.KEY_1) && World.isPositionNotOccupied(super.getPos())) {
			isTraining = true;
			trainingTime = 0;
			super.setTarget(super.getPos());
		}

		if (!isTraining) {
			super.update(world);
		}

		if (isTraining) {
			trainingTime += world.getDelta();
			if (trainingTime / 1000 == TRAINING_TIME) {
				trainingTime = 0;
				isTraining = false;
				world.getList().add(new Commandcentre(super.getPos().x, super.getPos().y));
				// Destroy after append the list since we need its position
				world.getList().remove(this);
			}
		}
	}
}
