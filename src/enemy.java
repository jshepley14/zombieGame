
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class enemy extends gameEntity {

	private boolean visible; //variable for if the enemy is alive
	private int HP, points, stopFor; //stopFor dictates how long it should stop for when hit by a bullet
    private Image still;
    private ImageIcon i;
    private String name;
    private double speed; // speed of movement (only relevant for bosses)
    
        public enemy(int xInit, int yInit, String kind)
        {
                super(xInit, yInit);
        		stopFor = 0;
                name = kind;
                if(name.equals("zombie")) //if enemy is a zombie
                {
                	i = new ImageIcon("zombie.png");
                	visible = true;
                	HP = 100;
                	dx = (2*Math.random());
                	dy = (2*Math.random());
                	speed = .35;
                	still = i.getImage();
                	points = 100;
                }
                if(name.equals("boss")) //if enemy is a boss
                {
                	i = new ImageIcon("boss zombie.png");
                	visible = true;
                	HP = 400;
                	speed = 1.1;
                	dx = 1;
                	dy = 1;
                	still = i.getImage();
                	points = 2500;
                }
        }

    public Rectangle getBounds() //gets hitbox
    {
        return new Rectangle(xDrawn,yDrawn, getImage().getWidth(null), getImage().getHeight(null));
    }
    
    /*
    public void checkCollision() {
    	panelthingy.getEnemyList()
    }
*/

    public void update(int xOffset, int yOffset, int xPlayer, int yPlayer)
    {
    	 //make the enemy move towards the player
    	dx = xPlayer - x;
    	dy = yPlayer - y;
    	double dist = Math.sqrt(dx*dx + dy*dy);
    	dx = dx*(speed/dist);
    	dy = dy*(speed/dist);
    		// updates position
    	if(stopFor == 0)
    	{
    		x += dx;
    		y += dy;
    	}
    	else stopFor --;
    	xDrawn = (int)x + xOffset;
    	yDrawn = (int)y + yOffset;
    }
    
    public String who() //specifies which type of enemy it is
    {
    	return name;
    }
    public int getPoints() //gets the points that should be added when the enemy is killed
    {
    	return points;
    }
    
    public int getHP() //gets the HP of the enemy
    {
    	return HP;
    }
    
    public void takeDamage(int damage)
    {
    	if(name.equals("boss") && damage == 50) //bosses are twice as affected by sniper rifles
    		damage = 100;
    	HP -= damage;
    	if(!(name.equals("boss") && (damage == 6)))
    	stopFor = 12;
    	if(HP <= 0)
    	{
    		die();
    	}
    }
    
    public boolean getVisible() //returns if the enemy is alive
    {
    	return visible;
    }
    
    public void die() //makes the enemy invisible (dead)
    {
    	visible = false;
    }
    
    public Image getImage() //returns the image of the enemy
    {
        return still;
    }



}
