import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

/**
 * 
 * Represent the game world, contains all the objects used in this game
 *
 */

public class World {

	/**
	 * The location of the map
	 */
	public static final String mapLocation = "assets/main.tmx";
	/**
	 * The location of the CSV file used to load the initial objects
	 */
	public static final String csvLocation = "assets/objects.csv";

	/**
	 * Name of the solid property
	 */
	public static final String SOLID_PROPERTY = "solid";
	/**
	 * Name of the occupied property
	 */
	public static final String OCCUPIED_PROPERTY = "occupied";

	// Set the local variables for the map, player and camera
	private static TiledMap map;
	private final Camera camera;
	private final Info info;

	private Input lastInput;
	private int lastDelta;

	// ArrayList of type Objects contains all the objects in this game
	private ArrayList<Objects> objectsList = new ArrayList<Objects>();

	// The position the player choose, update when left-click anywhere
	private Vector2f selectPos = new Vector2f(0, 0);

	// Keep track of the current amount of resources the player hold
	private int currMetal = 0;
	private int currUnobtain = 0;

	private int numberOfPylonsActivated = 0;

	// Keep track of the current selected object
	private boolean isAnythingSelected = false;
	private int selectedIndex = -1;

	// Construct the World
	/**
	 * @throws SlickException Constructor of the World
	 */
	public World() throws SlickException {
		map = new TiledMap(mapLocation);
		camera = new Camera(map, map.getWidth() * map.getTileWidth(), map.getHeight() * map.getTileHeight());
		info = new Info();
		// Load the initial objects
		loadInitialObjects();
	}

	/**
	 * @param input input of the user
	 * @param delta time since last update
	 * @throws SlickException Update method of the world
	 */
	public void update(Input input, int delta) throws SlickException {

		lastInput = input;
		lastDelta = delta;

		// Read the mouse
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			camera.setWASD(false);
			// Reset selection each time click left button
			resetSelection();
			boolean isNewPosSelected = false;

			// Calculate the left button position respect to the world
			selectPos.x = camera.calcWorldX(input.getMouseX());
			selectPos.y = camera.calcWorldY(input.getMouseY());

			// Loop to check if anything is to be selected
			for (int i = 0; i < objectsList.size(); i++) {
				if ((objectsList.get(i).getPos().distance(selectPos) <= App.SELECT_DISTANCE)) {
					// If this (unit) is within the click
					if (objectsList.get(i) instanceof Units) {
						resetSelection();
						objectsList.get(i).setSelected(true);
						isAnythingSelected = true;
						selectedIndex = i;
						isNewPosSelected = true;
						// Break if there is a unit within the mouse since we want a unit instead of a
						// building if they appear both
						break;
					}
					// If this (building) is within the click
					else if (objectsList.get(i) instanceof Buildings) {
						objectsList.get(i).setSelected(true);
						isAnythingSelected = true;
						selectedIndex = i;
						isNewPosSelected = true;
					}
				}
				// No unit/building is within the click
				else {
					objectsList.get(i).setSelected(false);
				}
			}
			if (!isNewPosSelected) {
				isAnythingSelected = false;
				selectedIndex = -1;
			}
		}

		// Detect update on keys WASD
		camera.translateWASD(this);

		// Update the object that is currently selected first, then update the rest
		if (isAnythingSelected) {
			objectsList.get(selectedIndex).update(this);
		}

		for (int i = 0; i < objectsList.size(); i++) {
			if (i == selectedIndex) {
				continue;
			}
			objectsList.get(i).update(this);
		}

	}

	/**
	 * @param g Render method of the World
	 */
	public void render(Graphics g) {

		// If the user pressed WASD, the camera should follow WASD primaryly
		if (camera.getWASD()) {
			camera.translate(g, camera.getLastTransPos().x, camera.getLastTransPos().y);
		}
		// If a unit or a building is selected, the camera should follow it
		else if (!camera.getWASD() && isAnythingSelected && (!(objectsList.get(selectedIndex) instanceof Resources))) {
			camera.translate(g, objectsList.get(selectedIndex).getPos().x, objectsList.get(selectedIndex).getPos().y);
		}
		// If click anywhere else and nothing is selected, the camera stop following and
		// just render the last position
		else if (!isAnythingSelected) {
			camera.translate(g, camera.getLastTransPos().x, camera.getLastTransPos().y);
		}

		// Display the map onto the screen
		map.render(0, 0);

		// Loop to render all the objects
		for (int i = 0; i < objectsList.size(); i++) {
			objectsList.get(i).render();
		}

		// Render the info after the objects to make the info stay on top of the obejcts
		info.renderInfo(this, g, objectsList);

	}

	private void loadInitialObjects() throws SlickException {

		// Read CSV
		// Same structure as the code in the lecture slide
		try (BufferedReader br = new BufferedReader(new FileReader(csvLocation))) {
			String text;
			while ((text = br.readLine()) != null) {

				String cells[] = text.split(",");
				String name = cells[0];
				int x = Integer.parseInt(cells[1]);
				int y = Integer.parseInt(cells[2]);

				// Not sure if this can be done better
				if (name.equals("command_centre")) {
					objectsList.add(new Commandcentre(x, y));
				} else if (name.equals("metal_mine")) {
					objectsList.add(new Metal(x, y));
				} else if (name.equals("unobtainium_mine")) {
					objectsList.add(new Unobtainium(x, y));
				} else if (name.equals("pylon")) {
					objectsList.add(new Pylon(x, y));
				} else if (name.equals("engineer")) {
					objectsList.add(new Engineer(x, y));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Check if the current tile is solid or occupied
	// Copied from the model answer, slightly modified
	/**
	 * @param pos
	 * @return true if the potential position is not solid, false otherwise
	 */
	public static boolean isPositionFree(Vector2f pos) {
		int tileId = map.getTileId((int) (pos.x / map.getTileWidth()), (int) (pos.y / map.getTileHeight()), 0);
		return !Boolean.parseBoolean(map.getTileProperty(tileId, World.SOLID_PROPERTY, "false"));
	}

	/**
	 * @param pos
	 * @return true if the potential position is not occupied, false otherwise
	 */
	public static boolean isPositionNotOccupied(Vector2f pos) {
		int tileId = map.getTileId((int) (pos.x / map.getTileWidth()), (int) (pos.y / map.getTileHeight()), 0);
		return !Boolean.parseBoolean(map.getTileProperty(tileId, World.OCCUPIED_PROPERTY, "false"));
	}

	/**
	 * @return the last input by user
	 */
	public Input getInput() {
		return lastInput;
	}

	/**
	 * @return the time passed by the lase update calling
	 */
	public int getDelta() {
		return lastDelta;
	}

	/**
	 * Reset all objects to unselected
	 */
	public void resetSelection() {
		for (int i = 0; i < objectsList.size(); i++) {
			objectsList.get(i).setSelected(false);
		}
	}

	/**
	 * @return the current amount of metal
	 */
	public int getCurrMetal() {
		return currMetal;
	}

	/**
	 * @param currMetal Set the current amount of metal
	 */
	public void setCurrMetal(int currMetal) {
		this.currMetal = currMetal;
	}

	/**
	 * @return the current amount of unobtainium
	 */
	public int getCurrUnobtain() {
		return currUnobtain;
	}

	/**
	 * @param currUnobtain Set the current amount of unobtainium
	 */
	public void setCurrUnobtain(int currUnobtain) {
		this.currUnobtain = currUnobtain;
	}

	/**
	 * @return the camera
	 */
	public Camera getCamera() {
		return this.camera;
	}

	/**
	 * @return the list that contains all the objects
	 */
	public ArrayList<Objects> getList() {
		return this.objectsList;
	}

	/**
	 * @return the number of Pylon activated
	 */
	public int getNumberOfPylonsActivated() {
		return numberOfPylonsActivated;
	}

	/**
	 * @param numberOfPylonsActivated Set the number of Pylon activated
	 */
	public void setNumberOfPylonsActivated(int numberOfPylonsActivated) {
		this.numberOfPylonsActivated = numberOfPylonsActivated;
	}

	/**
	 * @return map
	 */
	public TiledMap getMap() {
		return World.map;
	}
}
