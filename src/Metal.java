import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Metal extends Resources {

	public static final String imageLocation = "assets/resources/metal_mine.png";

	public static final int initialAmount = 500;

	public Metal(float x, float y) throws SlickException {
		super(x, y);
		super.setImage(new Image(imageLocation));
		super.setAmount(initialAmount);
	}

}
