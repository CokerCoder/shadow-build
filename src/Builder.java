import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Builder, a type of units, can build factories
 *
 */
public class Builder extends Units {

	/**
	 * Location of the builder's image
	 */
	public static final String imageLocation = "assets/units/builder.png";
	/**
	 * The speed of a builder
	 */
	public static final float BUILDER_SPEED = 0.1f;
	/**
	 * The time needed to build a factory
	 */
	public static final int BUILDING_TIME = 10;
	/**
	 * The cost of building a factory
	 */
	public static final int FACTORY_COST = 100;

	private boolean isBuilding = false;
	private int buildingTime = 0;

	/**
	 * @param x
	 * @param y
	 * @throws SlickException
	 * Constructor, call when creating a new builder
	 */
	public Builder(float x, float y) throws SlickException {
		super(x, y);
		this.setImage(new Image(imageLocation));
		this.setSpeed(BUILDER_SPEED);
	}

	/* (non-Javadoc)
	 * @see Units#update(World)
	 */
	@Override
	public void update(World world) throws SlickException {
		// If 2 is pressed, starting to train
		if (world.getInput().isKeyPressed(Input.KEY_1) && World.isPositionNotOccupied(super.getPos())) {
			isBuilding = true;
			buildingTime = 0;
			// Set the target position to its current position to let it stay at the same
			// position when training
			super.setTarget(super.getPos());
		}

		// Move unless not training
		if (!isBuilding) {
			super.update(world);
		}

		if (isBuilding) {
			buildingTime += world.getDelta();
			if (buildingTime / 1000 == BUILDING_TIME) {
				buildingTime = 0;
				isBuilding = false;
				world.setCurrMetal(world.getCurrMetal() - FACTORY_COST);
				world.getList().add(new Factory(super.getPos().x, super.getPos().y));
			}
		}
	}
}
