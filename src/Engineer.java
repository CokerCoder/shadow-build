import java.util.ArrayList;

import org.newdawn.slick.Graphics;
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

	// Time spent near a resource
	int timeNearResource;
	
	public Engineer(float x, float y) throws SlickException {
		super(x, y);
		this.setImage(new Image(imageLocation));
		this.setSpeed(ENGINEER_SPEED);
		this.targetMine = new Vector2f(-1, -1);
		this.targetCC = new Vector2f(-1, -1);
	}
	

	@Override
	public void update(World world) {
		super.update(world);
	}
	
	// Return the vector position of the nearest coomand centre
	public Vector2f findNearestCC(ArrayList<Objects> list) {
		// Set the current minimum distance to infinity for now
		double distance = Double.POSITIVE_INFINITY;
		int nearestIndex = -1;
		int i;
		for(i=0;i<list.size();i++) {
			if(list.get(i) instanceof Commandcentre) {
				double newDistance = super.getPos().distance(list.get(i).getPos());
				if(newDistance<distance) {
					distance = newDistance;
					nearestIndex = i;
				}
			}
		}
		targetCC = list.get(nearestIndex).getPos();
		return targetCC;
	}
	
	public void isNearResource(Objects[] objectList, int numberOfObjects) {
		int i;
		for(i=0;i<numberOfObjects;i++) {
			if(objectList[i] instanceof Resources) {
				Resources check = (Resources)objectList[i];
				if(super.getPos().distance(objectList[i].getPos())<=10) {
					
					System.out.println("Target mine position: "+" "+targetMine.x+" "+targetMine.y);
					
					targetMine = objectList[i].getPos();	
					targetMineIndex = i;
					isMining = true;
				}
				else {
				}
			}
		}
		isMining = false;
	}
	
	public void resetAmount() {
		this.amountCarried = 0;
	}



}
