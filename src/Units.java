import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public abstract class Units extends Objects{

	// Every unit can move based on an angle and a speed
	private double angle;
	private float speed;
	private Vector2f target;
	
	private boolean isSelected = false;
	
	
	public Units(float x, float y) throws SlickException {
		super(x, y);
		// At the start, the unit are not moving
		target = super.getPos();
	}
	

	public void update(World world) {
		// Set the angle to the destination for this unit
		angle = Math.atan2(target.y-super.getPos().y, target.x-super.getPos().x);
		
		// Potential position after each update
		Vector2f nextPos = new Vector2f((float)(super.getPos().x + speed * world.getDelta() * Math.cos(angle)), 
				(float)(super.getPos().y + speed * world.getDelta() * Math.sin(angle)));
		
		// Check if the next position is solid, if not then update the current position
		if(getPos().distance(target)>App.MIN_DISTANCE && isPositionFree(nextPos)==true) {
			setPos(nextPos);
		}
	}

	// Check if the current tile is solid or not
	// Copied from the model answer, slightly modified
	public boolean isPositionFree(Vector2f pos) {
		int tileId = getMap().getTileId((int)(pos.x/getMap().getTileWidth()), (int)(pos.y/getMap().getTileHeight()), 0);
		return !Boolean.parseBoolean(getMap().getTileProperty(tileId, World.SOLID_PROPERTY, "false"));
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public Vector2f getTarget() {
		return target;
	}

	public void setTarget(Vector2f target) {
		this.target = target;
	}
	
	public void resetTarget() {
		setTarget(super.getPos());
	}


	public boolean isSelected() {
		return isSelected;
	}


	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}
