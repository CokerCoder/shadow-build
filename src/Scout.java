import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

// Inherited from sprite class (player is a sprite)
public class Scout extends Units {
	
	public Scout(float x, float y, TiledMap map, Image image, float speed) throws SlickException {
		super(x, y, map, image, speed);
	}

	@Override
	// Different sprites can have different update (methods)
	// Move the sprite to its dest position
	public void update(int delta, Vector2f dest) {		
		
		// Set the angle to the destination for this player
		super.setAngle(World.getAngle(super.getPos().x, super.getPos().y, dest.x, dest.y));
		
		// Potential position after each update
		Vector2f nextPos = new Vector2f((float)(super.getPos().x + super.getSpeed() * delta * Math.cos(super.getAngle())), 
				(float)(super.getPos().y + super.getSpeed() * delta * Math.sin(super.getAngle())));
		
		// Check if the next position is solid, if not then update the current position
		if(super.getPos().distance(dest)>App.MIN_DISTANCE && isSolid(nextPos)==false) {
			super.setPos(nextPos);
		}
	}
}
