import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public abstract class Buildings extends Objects {
	
	public Buildings(float x, float y, TiledMap map) {
		super.setPos(new Vector2f(x, y));
		super.setMap(map);
	}
	
}
