import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

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
	
	private Input lastInput;
	private int lastDelta;
	
	// Create a list contains all the objects in the world
	private Objects[] objectList = new Objects[maximumObjects];
	// Keep track the number of the objects
	private int numberOfObjects = 0;
	
	// The position the player choose, update when left-click anywhere
	private Vector2f selectPos = new Vector2f(0, 0);

	
	boolean isAnythingSelected = false;
	int selectedIndex = -1;

	// Construct the World
	public World() throws SlickException {
		map = new TiledMap(mapLocation);
		camera = new Camera(map, map.getWidth() * map.getTileWidth(), map.getHeight() * map.getTileHeight());
		
		// Load the initial objects
		loadInitialObjects(objectList);
	}
	
	public void update(Input input, int delta) {
		lastInput = input;
		lastDelta = delta;
		
		// Read the mouse
		if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			// Calculate the right button position respect to the world using the function in the Camera class
			Vector2f destPos = new Vector2f(0, 0);
			destPos.x = camera.calcWorldX(input.getMouseX());
			destPos.y = camera.calcWorldY(input.getMouseY());
			
			if(isAnythingSelected) {
				if(objectList[selectedIndex] instanceof Units) {
					// Polymorphism
					Units a = (Units)objectList[selectedIndex];
					a.setTarget(destPos);
				}
			}

		} else if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			boolean isNewPosSelected = false;
			// Calculate the left button position respect to the world
			selectPos.x = camera.calcWorldX(input.getMouseX());
			selectPos.y = camera.calcWorldY(input.getMouseY());
			
			for(int i=0;i<numberOfObjects;i++) {
				if((objectList[i].getPos().distance(selectPos)<=App.SELECT_DISTANCE)) {
					// Select a unit
					if(objectList[i] instanceof Units) {
						isAnythingSelected = true;
						selectedIndex = i;
						isNewPosSelected = true;
						// Break if there is a unit within the mouse since we want a unit instead of a building if they appear both
						break;
					}
					else if(objectList[i] instanceof Buildings) {
						isAnythingSelected = true;
						selectedIndex = i;
						isNewPosSelected = true;
					}
				}
			}	
			if(!isNewPosSelected) {
				isAnythingSelected = false;
			}
		}
		for(int i=0;i<numberOfObjects;i++) {
			objectList[i].update(this);
		}
		
	}
	
	public void render(Graphics g) {
		
		// Firstly translate the camera based on the unit or building selected
		if(isAnythingSelected && (!(objectList[selectedIndex] instanceof Resources))) {
			camera.translate(g, objectList[selectedIndex].getPos().x, objectList[selectedIndex].getPos().y);
		} else if(!isAnythingSelected) {
			camera.translate(g, camera.getLastTransPos().x, camera.getLastTransPos().y);
		}
		
		// Display the map onto the screen
		map.render(0, 0);
		
		// Loop to render all the objects
		for(int i=0;i<numberOfObjects;i++) {
			objectList[i].render(g);
		}
	}
	
	public void loadInitialObjects(Objects[] objectList) throws SlickException {

		// Read CSV
		try (BufferedReader br =
				new BufferedReader(new FileReader(csvLocation))) {
				String text;
				while ((text = br.readLine()) != null) {
					
					String cells[] = text.split(",");
					String name = cells[0];
					int x = Integer.parseInt(cells[1]);
					int y = Integer.parseInt(cells[2]);
					
					if(name.equals("command_centre")) {
						objectList[numberOfObjects++] = new Commandcentre(x, y, map);
					} else if (name.equals("metal_mine")) {
						objectList[numberOfObjects++] = new Metal(x, y, map);
					} else if (name.equals("unobtainium_mine")) {
						objectList[numberOfObjects++] = new Unobtainium(x, y, map);
					} else if (name.equals("pylon")) {
						objectList[numberOfObjects++] = new Pylon(x, y, map);
					} else if (name.equals("engineer")) {
						objectList[numberOfObjects++] = new Engineer(x, y, map);
					} else if (name.contentEquals("scout")) {
						objectList[numberOfObjects++] = new Scout(x, y, map);
					}
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Input getInput() {
		return lastInput;
	}
	
	public int getDelta() {
		return lastDelta;
	}
}
