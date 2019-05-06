import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Engineer extends Units {

	public static final String imageLocation = "assets/units/engineer.png";
	public static final float ENGINEER_SPEED = 0.1f;
	
	private boolean isMining = false;
	
	// Current amount of resources the engineer is carrying
	private int amountCarried=0;
	private Vector2f targetMine;
	private int targetMineIndex=0;
	private Vector2f targetCC;
	
	public Engineer(float x, float y) throws SlickException {
		super(x, y);
		this.setImage(new Image(imageLocation));
		this.setSpeed(ENGINEER_SPEED);
		this.targetMine = new Vector2f(-1, -1);
		this.targetCC = new Vector2f(-1, -1);
	}

	@Override
	public void update(World world) {
		
		if(!isMining) {
			super.update(world);
		}
		else if(isNearResource(world.getList(), world.getNumberOfObjects())) {
			world.getList()[targetMineIndex].update(world);
		}
		else if(amountCarried==Resources.CARRY_AMOUNT) {
			super.setTarget(findNearestCC(world.getList(), world.getNumberOfObjects()));
			super.update(world);
		}
		else if(isMining&&amountCarried==0) {
			super.setTarget(targetMine);
			super.update(world);
		}
		// If the engineer reach the CC, drop the resource off
		if(super.getPos().distance(targetCC)<=App.MIN_DISTANCE) {
			resetAmount();
		}
	}
	
	// Return the vector position of the nearest coomand centre
	public Vector2f findNearestCC(Objects[] objectList, int numberOfObjects) {
		double distance = Double.POSITIVE_INFINITY;
		int nearestIndex = -1;
		int i;
		for(i=0;i<numberOfObjects;i++) {
			if(objectList[i] instanceof Commandcentre) {
				double newDistance = super.getPos().distance(objectList[i].getPos());
				if(newDistance<distance) {
					distance = newDistance;
					nearestIndex = i;
				}
			}
		}
		targetCC = objectList[nearestIndex].getPos();
		return targetCC;
	}
	
	public boolean isNearResource(Objects[] objectList, int numberOfObjects) {
		int i;
		for(i=0;i<numberOfObjects;i++) {
			if(objectList[i] instanceof Resources) {
				Resources check = (Resources)objectList[i];
				if(super.getPos().distance(objectList[i].getPos())<=10) {
					
					check.setEngineerNearby(true);
					System.out.println("Target mine position: "+" "+targetMine.x+" "+targetMine.y);
					
					targetMine = objectList[i].getPos();	
					targetMineIndex = i;
					isMining = true;
					return true;
				}
				else {
					check.setEngineerNearby(false);
				}
			}
		}
		return false;
	}
	
	public void resetAmount() {
		this.amountCarried = 0;
	}
}
