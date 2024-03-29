import java.util.ArrayList;

import org.newdawn.slick.Graphics;

/**
 * Contains all information the user need to be displayed on the screen
 *
 */
public class Info {

	/**
	 * Drawing position 1
	 */
	public static final int DRAW_STRING_A = 32;
	/**
	 * Drawing position 2
	 */
	public static final int DRAW_STRING_B = 100;

	/**
	 * A constructor, do nothing
	 */
	public Info() {
	}
	
	/**
	 * @param world
	 * @param g
	 * @param list
	 * Rendering all onto the screen
	 */
	public void renderInfo(World world, Graphics g, ArrayList<Objects> list) {

		g.drawString("Metal:  " + world.getCurrMetal() + "\nUnobtainium:  " + world.getCurrUnobtain(),
				world.getCamera().calcWorldX(DRAW_STRING_A), world.getCamera().calcWorldY(DRAW_STRING_A));

		for (int i = 0; i < list.size(); i++) {

			Objects currObject = list.get(i);

			if (currObject.isSelected()) {
				if (currObject instanceof Builder) {
					g.drawString("1- Build Factory (cost: " + Builder.FACTORY_COST + ")\n",
							world.getCamera().calcWorldX(DRAW_STRING_A), world.getCamera().calcWorldY(DRAW_STRING_B));
				} else if (currObject instanceof Commandcentre) {
					g.drawString(
							"1- Create Scout (cost: " + Commandcentre.SCOUT_COST + ")\n" + "2- Create Builder (cost: "
									+ Commandcentre.BUILDER_COST + ")\n" + "3- Create Engineer (cost: "
									+ Commandcentre.ENGINEER_COST + ")\n",
							world.getCamera().calcWorldX(DRAW_STRING_A), world.getCamera().calcWorldY(DRAW_STRING_B));
				} else if (currObject instanceof Factory) {
					g.drawString("1- Train Truck (cost: " + Factory.TRUCK_COST + ")\n",
							world.getCamera().calcWorldX(DRAW_STRING_A), world.getCamera().calcWorldY(DRAW_STRING_B));
				} else if (currObject instanceof Truck) {
					g.drawString("1- Build Command Centre (No cost)\n", world.getCamera().calcWorldX(DRAW_STRING_A),
							world.getCamera().calcWorldY(DRAW_STRING_B));
				}
			}
		}
	}
}
