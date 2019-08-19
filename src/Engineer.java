import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 * Engineer, a type of units, can mine mines automatically
 *
 */
public class Engineer extends Units {

	/**
	 * Location of the engineer's image
	 */
	public static final String imageLocation = "assets/units/engineer.png";
	/**
	 * Speed of a engineer
	 */
	public static final float ENGINEER_SPEED = 0.1f;
	/**
	 * Mining time of a engineer
	 */
	public static final int MINING_TIME = 5;
	/**
	 * Maximum carrying amount when no pylon is activated
	 */
	public static final int STARTING_AMOUNT = 2;

	private boolean isMining = false;
	private int miningTime = 0;
	private int amountCarrying = 0;

	private Resources targetMine;
	private Commandcentre targetCC;

	/**
	 * @param x
	 * @param y
	 * @throws SlickException
	 * Constructor, call when creating a new engineer
	 */
	public Engineer(float x, float y) throws SlickException {
		super(x, y);
		this.setImage(new Image(imageLocation));
		this.setSpeed(ENGINEER_SPEED);
	}

	/* (non-Javadoc)
	 * @see Units#update(World)
	 */
	@Override
	public void update(World world) throws SlickException {
		if (!isMining) {
			super.update(world);
			// When a engineer stays at a position 5 seconds, check if start mining
			if (super.getPos().distance(super.getTarget()) <= App.SELECT_DISTANCE) {
				miningTime += world.getDelta();
				if (miningTime / 1000 == MINING_TIME) {
					isNearResource(world.getList());
				}
			} else {
				miningTime = 0;
			}
		} else {
			// If the user move the engineer away while mining, the engineer stops and move
			// the new position
			if (world.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
				isMining = false;
				super.setTarget(new Vector2f(world.getCamera().calcWorldX(world.getInput().getMouseX()),
						world.getCamera().calcWorldY(world.getInput().getMouseY())));
				super.update(world);
				return;
			}
			mine(world);
			dropMine(world);
			// If the engineer is carrying nothing he should head to the mine
			if (amountCarrying == 0) {
				super.setTarget(targetMine.getPos());
			} else if (amountCarrying > 0) {
				super.setTarget(targetCC.getPos());
			}
			super.update(world);
		}
	}

	// Method to mine
	private void mine(World world) throws SlickException {
		if (super.getPos().distance(targetMine.getPos()) <= App.SELECT_DISTANCE) {
			miningTime += world.getDelta();
			if (miningTime / 1000 == MINING_TIME) {
				if (this.targetMine.getAmount() >= STARTING_AMOUNT + world.getNumberOfPylonsActivated()) {
					amountCarrying = STARTING_AMOUNT + world.getNumberOfPylonsActivated();
					targetMine.setAmount(targetMine.getAmount() - amountCarrying);
					miningTime = 0;
					// Check if there is closer Command Centre
					targetCC = findNearestCC(world.getList());

				} else if (this.targetMine.getAmount() >= 0
						&& this.targetMine.getAmount() <= (STARTING_AMOUNT + world.getNumberOfPylonsActivated())) {
					amountCarrying = targetMine.getAmount();
					targetMine.setAmount(0);
					miningTime = 0;

				} else if (this.targetMine.getAmount() <= 0) {
					this.isMining = false;
					this.miningTime = 0;
					
					return;
				}
			}
		}
	}

	private void dropMine(World world) {
		if (super.getPos().distance(targetCC.getPos()) <= App.SELECT_DISTANCE) {
			if (targetMine instanceof Metal) {
				world.setCurrMetal(world.getCurrMetal() + amountCarrying);
			} else if (targetMine instanceof Unobtainium) {
				world.setCurrUnobtain(world.getCurrUnobtain() + amountCarrying);
			}
			amountCarrying = 0;
		}
	}

	// Return the vector position of the nearest coomand centre
	private Commandcentre findNearestCC(ArrayList<Objects> list) {
		// Set the current minimum distance to infinity for now
		double distance = Double.POSITIVE_INFINITY;
		int nearestIndex = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof Commandcentre) {
				// Check the position of the CC and the engineer
				double newDistance = super.getPos().distance(list.get(i).getPos());
				if (newDistance < distance) {
					distance = newDistance;
					nearestIndex = i;
				}
			}
		}
		return (Commandcentre) list.get(nearestIndex);
	}

	private void isNearResource(ArrayList<Objects> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof Resources) {
				if (super.getPos().distance(list.get(i).getPos()) <= App.SELECT_DISTANCE) {
					isMining = true;
					targetMine = (Resources) list.get(i);
					targetCC = findNearestCC(list);
				}
			}
		}
	}
}
