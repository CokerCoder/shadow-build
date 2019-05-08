import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

/* This class contains all the objects in the game */

public class World {
	
	public static final String mapLocation = "assets/main.tmx";
	public static final String csvLocation = "assets/objects.csv";
	
	public static final String SOLID_PROPERTY = "solid";
	public static final String OCCUPIED_PROPERTY = "occupied";
	
	// Set the local variables for the map, player and camera
	private final TiledMap map;
	private final Camera camera;
	private final Info info;
	
	private Input lastInput;
	private int lastDelta;

	// ArrayList of type Objects contains all the objects in this game
	private ArrayList<Objects> objectsList = new ArrayList<Objects>();
	
	// The position the player choose, update when left-click anywhere
	private Vector2f selectPos = new Vector2f(0, 0);
	
	// Keep track of the current amount of resources thr player hold
	private int currMetal;
	private int currUnobtain;

	// Keep track of the current selected object
	boolean isAnythingSelected = false;
	int selectedIndex = -1;
	
	// Construct the World
	public World() throws SlickException {
		map = new TiledMap(mapLocation);
		camera = new Camera(map, map.getWidth() * map.getTileWidth(), map.getHeight() * map.getTileHeight());
		info = new Info();
		// Load the initial objects
		loadInitialObjects(objectsList);
	}
	
	public void update(Input input, int delta) {
		lastInput = input;
		lastDelta = delta;
		
		// Read the mouse
		 if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			// Reset each time click left button, there is only one object can be selected at a time
			resetSelect(objectsList);
			boolean isNewPosSelected = false;
			// Calculate the left button position respect to the world
			selectPos.x = camera.calcWorldX(input.getMouseX());
			selectPos.y = camera.calcWorldY(input.getMouseY());
			
			for(int i=0;i<objectsList.size();i++) {
				if((objectsList.get(i).getPos().distance(selectPos)<=App.SELECT_DISTANCE)) {
					// If a unit is selected
					if(objectsList.get(i) instanceof Units) {
						objectsList.get(i).setSelected(true);
						isAnythingSelected = true;
						selectedIndex = i;
						isNewPosSelected = true;
						// Break if there is a unit within the mouse since we want a unit instead of a building if they appear both
						break;
					}
					// If a building is selected
					else if(objectsList.get(i) instanceof Buildings) {
						objectsList.get(i).setSelected(true);
						isAnythingSelected = true;
						selectedIndex = i;
						isNewPosSelected = true;
					}
				} 
				// No unit/building is selected
				else {
					objectsList.get(i).setSelected(false);
				}
			}	
			if(!isNewPosSelected) {
				isAnythingSelected = false;
			}
		}
		for(int i=0;i<objectsList.size();i++) {
			objectsList.get(i).update(this);
		}
		
	}
	
	public void render(Graphics g) {
		
		// Firstly translate the camera based on the unit or building selected
		if(isAnythingSelected && (!(objectsList.get(selectedIndex) instanceof Resources))) {
			camera.translate(g, objectsList.get(selectedIndex).getPos().x, objectsList.get(selectedIndex).getPos().y);
		} else if(!isAnythingSelected) {
			camera.translate(g, camera.getLastTransPos().x, camera.getLastTransPos().y);
		}
		
		// Display the map onto the screen
		map.render(0, 0);
		
		// Loop to render all the objects
		for(int i=0;i<objectsList.size();i++) {
			objectsList.get(i).render();
		}
		
		// Render the info after the objects to make the info stay on top of the obejcts
		info.renderInfo(this, g, objectsList);
		
	}
	
	public void loadInitialObjects(ArrayList<Objects> list) throws SlickException {

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
						list.add(new Commandcentre(x, y));
					} else if (name.equals("metal_mine")) {
						list.add(new Metal(x, y));
					} else if (name.equals("unobtainium_mine")) {
						list.add(new Unobtainium(x, y));
					} else if (name.equals("pylon")) {
						list.add(new Pylon(x, y));
					} else if (name.equals("engineer")) {
						list.add(new Engineer(x, y));
					} else if (name.contentEquals("scout")) {
						list.add(new Scout(x, y));
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
	
	public void resetSelect(ArrayList<Objects> list) {
		for(int i=0;i<list.size();i++) {
			list.get(i).setSelected(false);
		}
	}

	public int getCurrMetal() {
		return currMetal;
	}

	public void setCurrMetal(int currMetal) {
		this.currMetal = currMetal;
	}

	public int getCurrUnobtain() {
		return currUnobtain;
	}

	public void setCurrUnobtain(int currUnobtain) {
		this.currUnobtain = currUnobtain;
	}
	
	public Camera getCamera() {
		return this.camera;
	}
}
