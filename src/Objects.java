import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/* This class is the top class among the objects, containing three sub-classes: Units, Buildings and Resources, each of these contains different sub-classes. */

public abstract class Objects {

	// Every object defined should have these attributes
	private Image image;
	private Vector2f pos;
	private boolean isSelected = false;

	public Objects(float x, float y) throws SlickException {
		pos = new Vector2f(x, y);
	}

	public abstract void update(World world) throws SlickException;

	public abstract void render();

	// Getters and setters
	public Vector2f getPos() {
		return pos;
	}

	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
