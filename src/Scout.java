import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Scout, a type of Units, can do nothing
 *
 */
public class Scout extends Units {

	/**
	 * Location of the image of a Scout
	 */
	public static final String imageLocation = "assets/units/scout.png";
	/**
	 * Speed of a scout
	 */
	public static final float SCOUT_SPEED = 0.3f;

	/**
	 * @param x
	 * @param y
	 * @throws SlickException
	 * Constructor, call when creating a Scout
	 */
	public Scout(float x, float y) throws SlickException {
		super(x, y);
		super.setImage(new Image(imageLocation));
		super.setSpeed(SCOUT_SPEED);
	}

	/* (non-Javadoc)
	 * @see Units#update(World)
	 */
	@Override
	public void update(World world) throws SlickException {
		// A scout could only move around
		super.update(world);
	}
}
