import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;


/**
 * Parent class of all objects, can inherit to Units, Buildings and Resources
 *
 */
public abstract class Objects {

	// Every object defined should have these attributes
	private Image image;
	private Vector2f pos;
	private boolean isSelected = false;

	/**
	 * @param x
	 * @param y
	 * @throws SlickException
	 * Constructor
	 */
	public Objects(float x, float y) throws SlickException {
		pos = new Vector2f(x, y);
	}

	/**
	 * @param world
	 * @throws SlickException
	 * Update the current object's status
	 */
	public abstract void update(World world) throws SlickException;

	/**
	 * Render the current object onto the screen
	 */
	public abstract void render();

	// Getters and setters
	
	/**
	 * @return the current position of this object
	 */
	public Vector2f getPos() {
		return pos;
	}

	/**
	 * @param pos
	 * Set the current object's position
	 */
	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	/**
	 * @return the image of this object
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image
	 * Set current object's image
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return true if the current object is selected, false otherwise
	 */
	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * @param isSelected
	 * Set the status whether this object is selected or not
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
