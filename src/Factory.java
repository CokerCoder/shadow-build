import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Factory extends Buildings {

	public static final String IMAGE_LOCATION = "assets/buildings/factory.png";
	public static final int TRAINING_TIME = 5;
	public static final int TRUCK_COST = 150;

	private boolean isTraining = false;
	private int trainingTime = 0;

	public Factory(float x, float y) throws SlickException {
		super(x, y);
		super.setImage(new Image(IMAGE_LOCATION));
	}

	@Override
	public void update(World world) throws SlickException {
		if (world.getInput().isKeyDown(Input.KEY_1) && super.isSelected() && world.getCurrMetal() >= TRUCK_COST) {
			isTraining = true;
			trainingTime = 0;
		}
		if (isTraining) {
			trainingTime += world.getDelta();
			if (trainingTime / 1000 == TRAINING_TIME) {
				isTraining = false;
				trainingTime = 0;

				world.getList().add(new Truck(super.getPos().x, super.getPos().y));
				world.setCurrMetal(world.getCurrMetal() - TRUCK_COST);
			}
		}
	}
}
