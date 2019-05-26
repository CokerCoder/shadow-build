import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Factory, a type of buildings, can train trucks
 *
 */
public class Factory extends Buildings {

	/**
	 * Location of the factory's image
	 */
	public static final String IMAGE_LOCATION = "assets/buildings/factory.png";
	/**
	 * Time needed to train a truck
	 */
	public static final int TRAINING_TIME = 5;
	/**
	 * Cost of a truck
	 */
	public static final int TRUCK_COST = 150;

	private boolean isTraining = false;
	private int trainingTime = 0;

	/**
	 * @param x
	 * @param y
	 * @throws SlickException
	 * Constructor, call when creating a new factory
	 */
	public Factory(float x, float y) throws SlickException {
		super(x, y);
		super.setImage(new Image(IMAGE_LOCATION));
	}

	/* (non-Javadoc)
	 * @see Objects#update(World)
	 */
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
