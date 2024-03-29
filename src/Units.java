import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 * A Unit can be different types: Scout, builder, engineer and truck, all units can move
 *
 */
public abstract class Units extends Objects {

	/**
	 * Location of the highlight image of a unit
	 */
	public static final String HIGHLIGHT_IMAGE = "assets/highlight.png";

	// Common arrtibutes for every unit
	private double angle;
	private float speed;
	private Vector2f target;
	private Image highlight = new Image(HIGHLIGHT_IMAGE);

	/**
	 * @param x
	 * @param y
	 * @throws SlickException
	 * Constructor
	 */
	public Units(float x, float y) throws SlickException {
		super(x, y);
		// At the start, the unit are not moving
		target = super.getPos();
	}

	/* (non-Javadoc)
	 * @see Objects#update(World)
	 */
	public void update(World world) throws SlickException {
		if (super.isSelected() && world.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			// Calculate the right button position respect to the world using the function
			// in the Camera class
			Vector2f destPos = new Vector2f(world.getCamera().calcWorldX(world.getInput().getMouseX()),
					world.getCamera().calcWorldY(world.getInput().getMouseY()));
			setTarget(destPos);
		}
		moveTo(world);
	}

	/* (non-Javadoc)
	 * @see Objects#render()
	 */
	public void render() {
		// If the unit isn't selected, render the normal image, otherwise render it with
		// its highlight image
		if (!super.isSelected()) {
			super.getImage().drawCentered(super.getPos().x, super.getPos().y);
		} else {
			highlight.drawCentered(super.getPos().x, super.getPos().y);
			super.getImage().drawCentered(super.getPos().x, super.getPos().y);
		}
	}

	private void moveTo(World world) {
		// Move the unit to its target position
		// Set the angle to the destination
		angle = Math.atan2(target.y - super.getPos().y, target.x - super.getPos().x);

		// Potential position after each update
		Vector2f nextPos = new Vector2f((float) (super.getPos().x + speed * world.getDelta() * Math.cos(angle)),
				(float) (super.getPos().y + speed * world.getDelta() * Math.sin(angle)));

		// Check if the next position is solid, if not then move
		if (getPos().distance(target) > App.MIN_DISTANCE && World.isPositionFree(nextPos) == true) {
			setPos(nextPos);
		}
	}

	/**
	 * @param speed
	 * Set the speed of this unit
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * @return the current target (destination) of this unit
	 */
	public Vector2f getTarget() {
		return target;
	}

	/**
	 * @param target
	 * Set the target of the unit
	 */
	public void setTarget(Vector2f target) {
		this.target = target;
	}
}
