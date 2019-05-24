import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

/**
 * This class should be used to restrict the game's view to a subset of the
 * entire world.
 **/

public class Camera {

	public static final float CAMERA_MOVING_SPEED = 0.4f;

	// Amount of pixels need to be transfered when renedering the map
	private float transX, transY;
	// Size of the map used to check if out of bound
	private float mapWidth, mapHeight;
	// Position of the last camera position
	private Vector2f lastTransPos;

	// Camera position in a World coordinates
	// Define a rectangle with the size of our window, this represents our camera
	// "range"
	// So everything inside the viewport will be drawn on the screen
	// The camera starting position is its top-left corner
	private Rectangle viewPort;

	private boolean isFollowingWASD = false;

	// Camera constructor
	public Camera(TiledMap map, int mapWidth, int mapHeight) {
		transX = 0;
		transY = 0;

		// The initial window is the top left corner of the whole map which starts at
		// (0, 0)
		viewPort = new Rectangle(0, 0, App.WINDOW_WIDTH, App.WINDOW_HEIGHT);

		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;

		this.lastTransPos = new Vector2f(App.WINDOW_WIDTH / 2, App.WINDOW_HEIGHT / 2);
	}

	// Calculate the movement along the x and y axis
	public void translate(Graphics g, float x, float y) {

		/*
		 * The number 16 means the number of pixels of the map that we need to let the
		 * render method to render this much more which will look more smooth when the
		 * player is about to the edge I don't know if this count as a constant becasue
		 * everyone has different definition for what is smooth. However I do think 16
		 * fits the best
		 */

		// If the player is at left edge of the map, keep the camera at left so the
		// player cannot go across the boundry
		if (x - App.WINDOW_WIDTH / 2 + 16 < 0)
			transX = 0;
		// ...right edge
		else if (x + App.WINDOW_WIDTH / 2 + 16 > mapWidth)
			transX = -mapWidth + App.WINDOW_WIDTH;
		// Otherwise update the movement
		else
			transX = -x + App.WINDOW_WIDTH / 2 - 16;

		// Vise versa
		if (y - App.WINDOW_HEIGHT / 2 + 16 < 0)
			transY = 0;
		else if (y + App.WINDOW_HEIGHT / 2 + 16 > mapHeight)
			transY = -mapHeight + App.WINDOW_HEIGHT;
		else
			transY = -y + App.WINDOW_HEIGHT / 2 - 16;

		lastTransPos.x = x;
		lastTransPos.y = y;

		// Apply the transformation and update the camera location
		g.translate(transX, transY);
		viewPort.setX(-transX);
		viewPort.setY(-transY);
	}

	// Method to translate the map based on the WASD keys
	public void translateWASD(World world) {
		if (world.getInput().isKeyDown(Input.KEY_A)) {
			this.isFollowingWASD = true;
			if (this.getLastTransPos().x > App.WINDOW_WIDTH / 2) {
				this.getLastTransPos().x -= Camera.CAMERA_MOVING_SPEED * world.getDelta();
			}
		} else if (world.getInput().isKeyDown(Input.KEY_D)) {
			this.isFollowingWASD = true;
			if (this.getLastTransPos().x + App.WINDOW_WIDTH / 2 < world.getMap().getTileWidth()
					* world.getMap().getWidth()) {
				this.getLastTransPos().x += Camera.CAMERA_MOVING_SPEED * world.getDelta();
			}
		} else if (world.getInput().isKeyDown(Input.KEY_S)) {
			this.isFollowingWASD = true;
			if (this.getLastTransPos().y + App.WINDOW_HEIGHT / 2 < world.getMap().getTileHeight()
					* world.getMap().getHeight()) {
				this.getLastTransPos().y += Camera.CAMERA_MOVING_SPEED * world.getDelta();
			}
		} else if (world.getInput().isKeyDown(Input.KEY_W)) {
			this.isFollowingWASD = true;
			if (this.getLastTransPos().y > App.WINDOW_HEIGHT / 2) {
				this.getLastTransPos().y -= Camera.CAMERA_MOVING_SPEED * world.getDelta();
			}
		}
	}

	// Hepler method to calculate a point position in a World perpective (the total
	// map)
	// based on its window position and the camera position in the World
	public float calcWorldX(float cameraX) {
		return cameraX + viewPort.getX();
	}

	public float calcWorldY(float cameraY) {
		return cameraY + viewPort.getY();
	}

	public Vector2f getLastTransPos() {
		return lastTransPos;
	}

	public boolean getWASD() {
		return this.isFollowingWASD;
	}

	public void setWASD(boolean isFollowingWASD) {
		this.isFollowingWASD = isFollowingWASD;
	}
}