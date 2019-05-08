import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Builder extends Units {

	public static final String imageLocation = "assets/units/builder.png";
	public static final float BUILDER_SPEED = 0.1f;
	
	public Builder(float x, float y) throws SlickException {
		super(x, y);
		this.setImage(new Image(imageLocation));
		this.setSpeed(BUILDER_SPEED);
	}

	@Override
	public void update(World world) {
		// TODO Auto-generated method stub
		
	}
	
}
