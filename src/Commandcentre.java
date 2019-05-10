import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Commandcentre extends Buildings {
	
	public static final String IMAGE_LOCATION = "assets/buildings/command_centre.png";
	public static final int TRAINING_TIME = 5;
	
	public static final int SCOUT_COST = 5;
	public static final int BUILDER_COST = 10;
	public static final int ENGINEER_COST = 20;
	
	private int trainingTime = 0;
	private boolean isTraining = false;
	private int trainingType = 0;
	
	public Commandcentre(float x, float y) throws SlickException {
		super(x, y);
		super.setImage(new Image(IMAGE_LOCATION));
	}

	@Override
	public void update(World world) throws SlickException {
		
		// Read the keyboard
		// Here, if I put the isSelected before the three if conditions, the code doesn't work, it works 
		if(world.getInput().isKeyPressed(Input.KEY_1)&&super.isSelected()&&world.getCurrMetal()>=SCOUT_COST) {
			trainingTime = 0;
			isTraining = true;
			trainingType = 1;
		}
		else if(world.getInput().isKeyPressed(Input.KEY_2)&&super.isSelected()&&world.getCurrMetal()>=BUILDER_COST) {
			trainingTime = 0;
			isTraining = true;
			trainingType = 2;
		}
		else if(world.getInput().isKeyPressed(Input.KEY_3)&&super.isSelected()&&world.getCurrMetal()>=ENGINEER_COST) {
			trainingTime = 0;
			isTraining = true;
			trainingType = 3;
		}
		
		if(isTraining) {
			trainingTime += world.getDelta();
			// delta is in millsec so divide it by 1000
			if(trainingTime/1000==TRAINING_TIME) {
				
				trainingTime = 0;
				isTraining = false;
				
				if(trainingType==1) {
					world.getList().add(new Scout(super.getPos().x, super.getPos().y));
					world.setCurrMetal(world.getCurrMetal()-SCOUT_COST);
				}
				else if(trainingType==2) {
					world.getList().add(new Builder(super.getPos().x, super.getPos().y));
					world.setCurrMetal(world.getCurrMetal()-BUILDER_COST);
				}
				else if(trainingType==3) {
					world.getList().add(new Engineer(super.getPos().x, super.getPos().y));
					world.setCurrMetal(world.getCurrMetal()-ENGINEER_COST);
				}
				
			}
		}

	}
}
