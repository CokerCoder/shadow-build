import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Pylon extends Buildings {

	public static final String imageLocation = "assets/buildings/pylon.png";
	public static final String ACTIVATED_IMAGE = "assets/buildings/pylon_active.png";

	private boolean isActivated = false;

	private Image activedImage = new Image(ACTIVATED_IMAGE);

	public Pylon(float x, float y) throws SlickException {
		super(x, y);
		super.setImage(new Image(imageLocation));
	}

	@Override
	public void update(World world) {
		// Do not update if the pylon is activated since it is a permanently effect
		if (!isActivated) {
			isActivated = isUnitsNearby(world);
		}
	}

	public void render() {
		if (isActivated) {
			activedImage.drawCentered(super.getPos().x, super.getPos().y);
		} else {
			super.render();
		}
	}

	public boolean isUnitsNearby(World world) {
		for (int i = 0; i < world.getList().size(); i++) {
			// If a unit come nearby, active it
			if (world.getList().get(i) instanceof Units) {
				if (world.getList().get(i).getPos().distance(super.getPos()) <= App.SELECT_DISTANCE) {
					world.setNumberOfPylonsActivated(world.getNumberOfPylonsActivated() + 1);
					return true;
				}
			}
		}
		return false;
	}
}
