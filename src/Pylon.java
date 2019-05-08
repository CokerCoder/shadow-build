import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Pylon extends Buildings{
	
	public static final String imageLocation = "assets/buildings/pylon.png";
	public static final String ACTIVATED_IMAGE = "assets/buildings/pylon_active.png";
	
	private boolean isActived = false;
	
	private Image activedImage = new Image(ACTIVATED_IMAGE);
	
	public Pylon(float x, float y) throws SlickException {
		super(x, y);
		super.setImage(new Image(imageLocation));
	}

	@Override
	public void update(World world) {
		// TODO Auto-generated method stub
		
	}
	
	public void render() {
		super.render();
		if(isActived) {
			activedImage.drawCentered(super.getPos().x, super.getPos().y);
		}
	}

}
