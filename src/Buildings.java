import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Building class have three different kinds of buildings, command centre, factory and pylons
 *
 */
public abstract class Buildings extends Objects {

	/**
	 * Location of the highlight image
	 */
	public static final String HIGHLIGHT_IMAGE = "assets/highlight_large.png";

	private Image highlight = new Image(HIGHLIGHT_IMAGE);

	/**
	 * @param x
	 * @param y
	 * @throws SlickException
	 * Constructor
	 */
	public Buildings(float x, float y) throws SlickException {
		super(x, y);
	}
	
	
	/* (non-Javadoc)
	 * @see Objects#render()
	 */
	@Override
	public void render() {
		if (!super.isSelected()) {
			super.getImage().drawCentered(super.getPos().x, super.getPos().y);

		} else {
			if (!(this instanceof Pylon)) {
				highlight.drawCentered(super.getPos().x, super.getPos().y);
			}
			super.getImage().drawCentered(super.getPos().x, super.getPos().y);
		}
	}
}
