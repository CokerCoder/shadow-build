import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class Buildings extends Objects {
	
	private boolean isSelected = false;
	
	public Buildings(float x, float y) throws SlickException {
		super(x, y);
	}
	
	public void render(Graphics g) {
		super.getImage().drawCentered(super.getPos().x, super.getPos().y);
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}
