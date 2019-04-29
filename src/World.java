import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

/**
 * This class should be used to contain all the different objects in your game world, and schedule their interactions.
 * 
 * You are free to make ANY modifications you see fit.
 * These classes are provided simply as a starting point. You are not strictly required to use them.
 */

public class World {
	
	// Set the local variables for the map, player and camera
	private final TiledMap map;
	private final Units player;
	private final Camera camera;
	
	// Size of the map in pixels
	private int mapWidth, mapHeight;
	
	// Get the destination position and store it into a vector
	// This is updated when pressing the mouse, initilised at (0, 0)
	private Vector2f dest = new Vector2f(0, 0);


	// Construct the World
	public World() throws SlickException {
		map = new TiledMap("assets/main.tmx");
		mapWidth = map.getWidth() * map.getTileWidth();
		mapHeight = map.getHeight() * map.getTileHeight();
		
		// Initialise the player at the same position as the destination position
		// with a speed of 0.25
		player = new Scout(dest.x, dest.y, map, new Image("assets/scout.png"), App.PLAYER_SPEED);
		// Initialise the camera
		camera = new Camera(map, mapWidth, mapHeight);
	}
	
	public void update(Input input, int delta) {
		if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			// Calculate the mouse position respect to the world using the function in the Camera class
			dest.x = camera.calcWorldX(input.getMouseX());
			dest.y = camera.calcWorldY(input.getMouseY());
		}
		// Update the player's position
		player.update(delta, dest);
	}
	
	public void render(Graphics g) {
		// Firstly translate the camera based on the player's position
		camera.translate(g, player);
		// Display the map onto the screen
		map.render(0, 0);
		player.render(g);
	}
	

	// Helper method to calculate the angle that between two points
	public static double getAngle(float x1, float y1, float x2, float y2) {
		return (Math.atan2(y2-y1, x2-x1));
	}
}
