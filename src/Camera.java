import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

/**
 * This class should be used to restrict the game's view to a subset of the entire world.
**/

public class Camera {
	
	// Amount of pixels need to be transfered when renedering the map
	private float transX, transY;
	// Size of the map used to check if out of bound
	private float mapWidth, mapHeight;
   
	// Camera position in a World coordinates
	// Define a rectangle with the size of our window, this represents our camera "range"
    // So everything inside the viewport will be drawn on the screen
	// The camera starting position is its top-left corner
	private Rectangle viewPort; 
 
	// Camera constructor
	public Camera(TiledMap map, int mapWidth, int mapHeight) {
		transX = 0;
		transY = 0;
      
		// The initial window is the top left corner of the whole map which starts at (0, 0)
		viewPort = new Rectangle(0, 0, App.WINDOW_WIDTH, App.WINDOW_HEIGHT);
      
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
	}
	
	// Calculate the movement along the x and y axis
	public void translate (Graphics g, Objects unit) {
		
		/* The number 16 means the number of pixels of the map
		 * that we need to let the render method to render this much more
		 * which will look more smooth when the player is about to the edge
		 * I don't know if this count as a constant becasue everyone has different
		 * definition for what is smooth. However I do think 16 fits the best
		 */
		
		// If the player is at left edge of the map, keep the camera at left so the player cannot go across the boundry
		if(unit.getPos().x-App.WINDOW_WIDTH/2+16 < 0)
			transX = 0;
		// ...right edge
		else if(unit.getPos().x+App.WINDOW_WIDTH/2+16 > mapWidth)
			transX = -mapWidth+App.WINDOW_WIDTH;
		// Otherwise update the movement
		else
			transX = -unit.getPos().x+App.WINDOW_WIDTH/2-16;
		
		// Vise versa
		if(unit.getPos().y-App.WINDOW_HEIGHT/2+16 < 0)
			transY = 0;
		else if(unit.getPos().y+App.WINDOW_HEIGHT/2+16 > mapHeight)
			transY = -mapHeight+App.WINDOW_HEIGHT;
		else
			transY = -unit.getPos().y+App.WINDOW_HEIGHT/2-16;
		
		// Apply the transformation and update the camera location
		g.translate(transX, transY);
		viewPort.setX(-transX);
		viewPort.setY(-transY);
		
	}
	
	// Hepler method to calculate a point position in a World perpective (the total map)
	// based on its window position and the camera position in the World
    public float calcWorldX(float cameraX) {
		return cameraX+viewPort.getX();
	}
	public float calcWorldY(float cameraY) {
		return cameraY+viewPort.getY();
	}
}