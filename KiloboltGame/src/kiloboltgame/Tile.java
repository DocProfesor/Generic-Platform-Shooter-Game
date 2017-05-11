package kiloboltgame;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile {

	private int tileX, tileY, speedX, type;
	public Image tileImage;
	private Rectangle r;
	
	private Robot robot = StartingClass.getRobot();
	private Background bg= StartingClass.getBg1();
	
	public Tile(int x, int y, int typeInt) {
		tileX= x*40;
		tileY= y*40;
		
		type= typeInt;
		
		r = new Rectangle();
		
		switch(type){
			case 5:
				tileImage = StartingClass.tiledirt;
				break;
			case 8:
				tileImage = StartingClass.tilegrassTop;
				break;
			case 4:
				tileImage = StartingClass.tilegrassLeft;
				break;
			case 6:
				tileImage = StartingClass.tilegrassRight;
				break;
			case 2:
				tileImage = StartingClass.tilegrassBot;
				break;
			default:
				type=0;
				break;
		}
	}
	
	public void update(){
		speedX= bg.getSpeedX()*5;
		tileX += speedX;
		
		//Checking out for collisions
		r.setBounds(tileX, tileY, 40, 40);
		
		if(r.intersects(Robot.yellowRed) && type != 0){
			//System.out.println("Upper collision with " + tileX);
			checkVerticalCollision(Robot.rect, Robot.rect2);
			checkSideCollision(Robot.rect3, Robot.rect4, Robot.footleft, Robot.footright);
		}
		
		//if(type != 0){
		//	checkVerticalCollision(Robot.rect, Robot.rect2);
		//}
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}
	
	public void checkVerticalCollision(Rectangle rTop, Rectangle rBot){
		if(rTop.intersects(r)){
			//System.out.println("Upper collision");
		}
		if(rBot.intersects(r) && type == 8){
			//System.out.println("Lower collsion");
			robot.setJumped(false);
			robot.setSpeedY(0);
			robot.setCenterY(tileY - 63);
		}
	}
	
	public void checkSideCollision(Rectangle rleft, Rectangle rright, Rectangle leftfoot, Rectangle rightfoot){
		if(type != 5 && type != 2 && type != 0){
			//Slide robot to the left depending on the colliding foot/arm.
			if(rleft.intersects(r)){
				robot.setCenterX(tileX + 102);
				robot.setSpeedX(0);
			}else if(leftfoot.intersects(r)){
				robot.setCenterX(tileX + 85);
				robot.setSpeedX(0);
			}
			
			//Slide robot to the right depending on the colliding foot/arm.
			if(rright.intersects(r)){
				robot.setCenterX(tileX - 62);
				robot.setSpeedX(0);
			}else if(rightfoot.intersects(r)){
				robot.setCenterX(tileX - 45);
				robot.setSpeedX(0);
			}
			
		}
	}

}
