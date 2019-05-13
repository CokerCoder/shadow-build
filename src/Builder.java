import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Builder extends Units {

	public static final String imageLocation = "assets/units/builder.png";
	public static final float BUILDER_SPEED = 0.1f;
	public static final int TRAINING_TIME = 10;
	public static final int TRUCK_COST = 100;
	
	private boolean isTraining = false;
	private int trainingTime = 0;
	
	
	public Builder(float x, float y) throws SlickException {
		super(x, y);
		this.setImage(new Image(imageLocation));
		this.setSpeed(BUILDER_SPEED);
	}

	@Override
	public void update(World world) throws SlickException {
		System.out.println(world.getInput());
		if(world.getInput().isKeyPressed(Input.KEY_2)) {
			System.out.println("Start training!");
			isTraining = true;
			trainingTime = 0;
			// Set the target position to its current position to let it stay same position after building
			super.setTarget(super.getPos());
		}
		
		
		// Move unless not training
		if(!isTraining) {	
			super.update(world);
		}
		

		
		if(isTraining) {
			trainingTime += world.getDelta();
			if(trainingTime/1000==2) {
				trainingTime = 0;
				isTraining = false;
				world.setCurrMetal(world.getCurrMetal()-TRUCK_COST);
				world.getList().add(new Factory(super.getPos().x, super.getPos().y));
			}
		}
		
	}
	
}
