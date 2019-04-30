import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

/* This class contains all the objects in the game */

public class World {
	// We set the maximum objects in this world is 50
	public static final int maximumObjects = 50;
	
	public static final String mapLocation = "assets/main.tmx";
	public static final String csvLocation = "assets/objects.csv";
	
	public static final String SOLID_PROPERTY = "solid";
	public static final String OCCUPIED_PROPERTY = "occupied";
	
	// Set the local variables for the map, player and camera
	private final TiledMap map;
	private final Camera camera;
	
	private final Objects player;
	
	// Create a list contains all the objects in the world
	private Objects[] objectList = new Objects[maximumObjects];
	// Keep track the number of the objects
	private int numberOfObjects = 0;
	
	// The destination position and store it into a vector
	// Update when right-click anywhere, initilised at (0, 0)
	private Vector2f destPos = new Vector2f(0, 0);
	
	// The position the player choose, update when left-click anywhere
	private Vector2f selectPos = new Vector2f(0, 0);


	// Construct the World
	public World() throws SlickException, FileNotFoundException {
		map = new TiledMap(mapLocation);
		player = new Scout(destPos.x, destPos.y, map);
		// Initialise the camera with the map and its size
		camera = new Camera(map, map.getWidth() * map.getTileWidth(), map.getHeight() * map.getTileHeight());
		
		// Get the initial objects
		Scanner scanner = new Scanner(new File(csvLocation));
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
        	if(scanner.next().equals("command_centre")) {
        		System.out.println(scanner.next()+" + "+scanner.next());
        	} 
        }
        scanner.close();
	}
	
	public void update(Input input, int delta) {
		
		// Read the mouse
		if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			// Calculate the right button position respect to the world using the function in the Camera class
			destPos.x = camera.calcWorldX(input.getMouseX());
			destPos.y = camera.calcWorldY(input.getMouseY());
		} else if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			// Calculate the left button position respect to the world
			selectPos.x = camera.calcWorldX(input.getMouseX());
			selectPos.y = camera.calcWorldY(input.getMouseY());
		}
		
		// If an object is selected, update it
		
		/* code */
		player.update(delta, destPos);
	}
	
	public void render(Graphics g) {
		// Firstly translate the camera based on the player's position
		camera.translate(g, player);
		// Display the map onto the screen
		map.render(0, 0);
		player.render(g);
		
		// Loop to render all the objects
	}
}
