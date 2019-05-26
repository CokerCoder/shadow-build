import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Unobtainium, a type of resources
 *
 */
public class Unobtainium extends Resources {

	/**
	 * Location of the image of unobtainium
	 */
	public static final String imageLocation = "assets/resources/unobtainium_mine.png";

	/**
	 * Initial amount of a unobtainium mine
	 */
	public static final int initialAmount = 50;

	/**
	 * @param x
	 * @param y
	 * @throws SlickException
	 * Constructor, call when initialize the world
	 */
	public Unobtainium(float x, float y) throws SlickException {
		super(x, y);
		super.setImage(new Image(imageLocation));
		super.setAmount(initialAmount);
	}

}
