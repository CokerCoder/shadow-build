import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Buildings extends Objects {

	public static final String HIGHLIGHT_IMAGE = "assets/highlight_large.png";

	private Image highlight = new Image(HIGHLIGHT_IMAGE);

	public Buildings(float x, float y) throws SlickException {
		super(x, y);
	}

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
