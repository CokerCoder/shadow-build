import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

/* This class contains all the objects in the game */

public class World {
	
	public static final String mapLocation = "assets/main.tmx";
	
	// Set the local variables for the map, player and camera
	private final TiledMap map;
	private final Camera camera;
	
	private final Objects player;
	private final Objects engineer;
	
	
	// Size of the map in pixels
	private int mapWidth, mapHeight;
	
	// The destination position and store it into a vector
	// Update when right-click anywhere, initilised at (0, 0)
	private Vector2f destPos = new Vector2f(0, 0);
	
	// The position the player choose, update when left-click anywhere
	private Vector2f selectPos = new Vector2f(0, 0);


	// Construct the World
	public World() throws SlickException {
		map = new TiledMap(mapLocation);
		mapWidth = map.getWidth() * map.getTileWidth();
		mapHeight = map.getHeight() * map.getTileHeight();
		
		player = new Scout(destPos.x, destPos.y, map);
		engineer = new Engineer(100, 100, map);
		// Initialise the camera
		camera = new Camera(map, mapWidth, mapHeight);
	}
	
	public void update(Input input, int delta) {
		
		// Read the mouse
		if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			// Calculate the right button position respect to the world using the function in the Camera class
			destPos.x = camera.calcWorldX(input.getMouseX());
			destPos.y = camera.calcWorldY(input.getMouseY());
		} else if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			// Calculate the ledt button position respect to the world
			selectPos.x = camera.calcWorldX(input.getMouseX());
			selectPos.y = camera.calcWorldY(input.getMouseY());
		}
		
		// Check if select an object
		
		/* code */
		
		// Update the player's position
		player.update(delta, destPos);
	}
	
	public void render(Graphics g) {
		// Firstly translate the camera based on the player's position
		camera.translate(g, player);
		// Display the map onto the screen
		map.render(0, 0);
		player.render(g);
		engineer.render(g);
	}
}
