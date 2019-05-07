import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Buildings extends Objects {
	
	public static final String HIGHLIGHT_IMAGE = "assets/highlight_large.png";

	private Image highlight = new Image(HIGHLIGHT_IMAGE);
	
	public Buildings(float x, float y) throws SlickException {
		super(x, y);
	}
	
	public void render(Graphics g) {
		if(super.isSelected()&&(!(this instanceof Pylon))) {
			super.getImage().drawCentered(super.getPos().x, super.getPos().y);
			highlight.drawCentered(super.getPos().x, super.getPos().y);
		} else {
			super.getImage().drawCentered(super.getPos().x, super.getPos().y);
		}
	}
	

}
