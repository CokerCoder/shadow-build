import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public abstract class Buildings extends Objects {
	
	public Buildings(float x, float y) throws SlickException {
		super(x, y);
	}
	
	public void render(Graphics g) {
		super.getImage().drawCentered(super.getPos().x, super.getPos().y);
	}
}
