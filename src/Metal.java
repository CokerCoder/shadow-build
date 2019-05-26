import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Metal, a type of resources
 *
 */
public class Metal extends Resources {

	/**
	 * Location of the metal's image
	 */
	public static final String imageLocation = "assets/resources/metal_mine.png";

	/**
	 * Initial amount of a metal mine
	 */
	public static final int initialAmount = 500;

	/**
	 * @param x
	 * @param y
	 * @throws SlickException
	 * Constrctor, call when initialize the world
	 */
	public Metal(float x, float y) throws SlickException {
		super(x, y);
		super.setImage(new Image(imageLocation));
		super.setAmount(initialAmount);
	}

}
