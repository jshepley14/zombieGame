
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class panelthingy extends JPanel{

        player p; //player
        public world2 b; //new map
        ArrayList<bullet> bullets = new ArrayList<bullet>(); //bullet arraylist
        ArrayList<enemy> enemies = new ArrayList<enemy>(); //enemy arraylist
        ArrayList<AmmoPack> ammopacks = new ArrayList<AmmoPack>(); //ammopacks arraylist
        ArrayList<HealthPack> healthpacks = new ArrayList<HealthPack>(); //healthpacks arraylist
        int xmouse, ymouse, score, enemiesKilled, times, enemyRate, level, highScore, maxZombies; //coordinates relative to player center
        boolean alive, takingDamage, pause, reset, dropPack;
     
        //game constructor
        public panelthingy(int hiScore)
        {
        		highScore = hiScore; //allows the panel to display the high score
        		dropPack = true; //variable to make a pack drop every other enemy killed
        		level = 1; //level that the player is at
        		enemiesKilled = 15000; //# of enemies that have been killed
        		maxZombies = 6;
        		pause = false; //pause functions
        		reset = false; //reset function (when GameFrame deletes this panelthingy)
        		times = 0; //keeps track of time
        		enemyRate = 200; //rate of enemy spawning ( every 2 seconds )
        		score = 0; //score variable
        		alive = true; //checks if the player is alive
        		setLayout(null); 
                p = new player(400,350); //creates new player object
                b = new world2(0,0); //creates new map object
                addKeyListener(new AL()); //adds keyListener
                setFocusable(true); //sets focus
                addMouseListener(new ML());
        }
     
        public void Perform()
        {
        	if(alive)
        	{
        	if(!pause) //if the game is not paused:
        	{
        	times ++; //update the times variable
        	score = times/25 + enemiesKilled; //update the score variable
        	if(score > 2000) //update level according to score
        	{
        		level = 2;
        		maxZombies = 12;
        	}
        	if(score > 5000)
        	{
        		level = 3;
        		maxZombies = 18;
        	}
        	if(score > 12000)
        	{
        		level = 4;
        		maxZombies = 30;
        	}
        	if(times%enemyRate*6 == 0 && enemies.size() < maxZombies) //spawn regular zombies
        	{
        		int xSpawn = 200+(int)(2000*(Math.random()));
        		int ySpawn = 200+(int)(1400*(Math.random()));
        		int xDistance = xSpawn - (p.getX() - b.getX());
        		int yDistance = ySpawn - (p.getY() - b.getY());
        		if(Math.sqrt((xDistance*xDistance) + (yDistance*yDistance)) > 300)
        			addEnemy(new enemy(xSpawn, ySpawn, "zombie"));
        	}
        	if(level == 3) //if level three or four, spawn boss zombies
        	{
        		if(times%(enemyRate*12) == 0)
        		{
            		int xSpawn = 200+(int)(2000*(Math.random()));
            		int ySpawn = 200+(int)(1400*(Math.random()));
            		int xDistance = xSpawn - (p.getX() - b.getX());
            		int yDistance = ySpawn - (p.getY() - b.getY());
            		if(Math.sqrt((xDistance*xDistance) + (yDistance*yDistance)) > 300)
            			addEnemy(new enemy(xSpawn, ySpawn, "boss"));
        		}
        	}

        	if(level == 4)
        	{
        		if(times%(enemyRate*8) == 0)
        		{
            		int xSpawn = 200+(int)(2000*(Math.random()));
            		int ySpawn = 200+(int)(1400*(Math.random()));
            		int xDistance = xSpawn - (p.getX() - b.getX());
            		int yDistance = ySpawn - (p.getY() - b.getY());
            		if(Math.sqrt((xDistance*xDistance) + (yDistance*yDistance)) > 300)
            			addEnemy(new enemy(xSpawn, ySpawn, "boss"));
        		}
        	}
            Point c = getLocationOnScreen(); //makes coordinate for the top left of the JPanel
            PointerInfo a = MouseInfo.getPointerInfo(); //makes a mouse coordinate point
            Point point = a.getLocation();
            xmouse = (int) (point.getX() - (c.getX() + 450)); //makes the x coordinate of the pointer relative to the center of the player
            ymouse = (int) (point.getY() - (c.getY() + 400)); //y coordinate ''
                for (int i = 0; i < bullets.size(); i++) //for loop to update all the bullets on the screen
                {
                        bullet m = (bullet) bullets.get(i);
                        if (m.getVisible()) //if they're in the screen update their position
                        {
                                m.update();
                                m.giveRealX( (int)(m.getX()) - (m.getXBack() - b.getX()));
                                m.giveRealY( (int)(m.getY()) - (m.getYBack() - b.getY()));
                        }
                        else bullets.remove(i); //if they're not in the screen delete them
                }
                
                for (int i=0; i<bullets.size(); i++) //bullets hitting enemies
                {
                	bullet m = (bullet) bullets.get(i);
                	for(int j=0; j<enemies.size(); j++)
                	{
                		enemy en = (enemy) enemies.get(j);
                		if( m.getBounds().intersects(en.getBounds()))
                		{
                			en.takeDamage(m.getDamage());
                			bullets.remove(m);
                			System.out.print(m.getDamage());
                			System.out.print("hit, HP = ");
                			System.out.println(en.getHP());
                		}
                	}
                }

                for (int i=0; i < enemies.size(); i++) //check if enemies are dead or not
                {      
                        enemy en = enemies.get(i);
                    	if(en.getVisible())
                    	{
                    			en.update(b.getX(),b.getY(),p.getX() - b.getX(),p.getY() - b.getY());
                    	}
                    	else 
                    	{
                    		if(dropPack) //if it should now drop a pack
                    		{
                    			int what = (int)(5*(Math.random())); //determines what should be dropped
                    			if(what >= 1) //add an ammopack
                    			{
                    				if(level < 4) //if it is not level four
                    				{
                    					AmmoPack pack = new AmmoPack(en.getX(), en.getY(), (int)(level*Math.random())); //make an ammopack of type
                    					//according to the level you're at.
                        				ammopacks.add(pack);
                    				}
                    				else
                    				{
                    					//if level four, spawn any ammopack other than pistol
                    					AmmoPack pack = new AmmoPack(en.getX(), en.getY(), 1 + (int)((level-1)*Math.random()));
                        				ammopacks.add(pack);
                    				}
                    			}
                    			else //add a healthpack
                    			{
                    				HealthPack pack = new HealthPack(en.getX(), en.getY(), 25);
                    				healthpacks.add(pack); 
                    			}
                				dropPack = false; //make dropPack false for next enemy killed
                    		}
                    		else
                    		{
                    			dropPack = true; //makes dropPack true for next enemy killed
                    		}
                			enemiesKilled += en.getPoints(); //add points to score
                			enemies.remove(i); //deletes enemy
                    	}
                }
                for (int i=0; i < ammopacks.size(); i++) //check if player is touching ammopacks
                {
                		AmmoPack pack = ammopacks.get(i);
                		if(pack.getBounds().intersects(p.getBounds()))
                		{
                			p.recieveAmmo(pack.getAmount(), pack.getType()); //add ammo to player
                			ammopacks.remove(i);
                		}
                		else pack.update(b.getX(), b.getY());
                }
                for (int i=0; i < healthpacks.size(); i++) //check if player is touching healtpacks
                {
                		HealthPack pack = healthpacks.get(i);
                		if(pack.getBounds().intersects(p.getBounds()) && !(p.getHP() == 100))
                		{
                			p.heal(pack.getAmount()); //add health to the player
                			healthpacks.remove(i);
                		}
                		else pack.update(b.getX(), b.getY());
                }
        		boolean hit = false;
                for (int i=0; i < enemies.size(); i++) //check if taking damage
                {      
                        enemy en = enemies.get(i);
                    	if(en.getBounds().intersects(p.getBounds()))
                    	{
                    		hit = true;
                    		p.takeDamage(0.3);
                    	} 
                }
            	if(hit) // if the player has been hit, then you're taking damage
            		takingDamage = true; 
            	else 
            		takingDamage = false;
                p.update(b.getX(),b.getY(), (xmouse <= 0)); //update the player's motion
                b.update(b.getX(),b.getY()); //update the background's position
                p.updateGun(); //update the player's gun
                if(p.isFiring()) //if the player is firing
                {
                        fire("player"); //say the player is doing the firing
                }
        	}
        	}
                repaint(); //run the paint method
                if(!p.isAlive()) //make the game end if the player isn't alive
                {
                	alive = false;
                } 
        	
        }
        
        public int getScore()
        {
        	return score;
        }
     
        public boolean reset() //see if the player wants to reset the game
        {
        	return reset;
        }
        
        public void addEnemy(enemy poop) //add an enemy
        {
                enemies.add(poop);
        }
       
        public void fire(String who) //firing method (creates new bullets)
        {
                if(who.equals("player")) //if the player is doing the firing
                {
                        bullet z = new bullet(p.gunDamage(), xmouse, ymouse, 15, b.getX(), b.getY(), (int)p.getDX(), (int)p.getDY());
                        bullets.add(z); //add a new bullet with the player's gun's speed & damage, at the direction of the mouse pointer relative
                }                       //to the player, and adding the player's velocity as the bullet's initial velocity
        }
       
        public void setCustomCursor() //sets custom cursor
        {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                  Image image = toolkit.getImage("target.png");
                  Point hotSpot = new Point(16,0);
                  Cursor c = toolkit.createCustomCursor(image , hotSpot, "target");
                  setCursor (c);
        }
       
        public void resetCursor() //resets custom cursor
        {
                setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        
        public void pressKey(KeyEvent e) //if space is pressed, pause, and if lost, reset game
        {
        	int key = e.getKeyCode();
        	if(alive)
        	{
            if(key == KeyEvent.VK_ESCAPE)
            {
            	if(pause)
            	{
            		pause = false;
            		setCustomCursor();
            	}
            	else
            	{
            		pause = true;
            		resetCursor();
            	}
            }
    		if(key == KeyEvent.VK_SPACE && pause)
    		{
    			reset = true;
    		}
        	}
        	else
        	{
        		if(key == KeyEvent.VK_SPACE)
        		{
        			reset = true;
        		}
        	}
        }
     
        public void paint(Graphics g) //paint method
        {
                super.paint(g);
                Graphics2D g2d = (Graphics2D) g; //graphics variable
             
                g2d.drawImage(b.getImage(), b.getX(), b.getY(),null); //draw background
                for (int i=0; i < ammopacks.size(); i++)//draw ammopacks
                {
                		AmmoPack pack = ammopacks.get(i);
                		g2d.drawImage(pack.getImage(), pack.getXDrawn(), pack.getYDrawn(), null);
                }
                for (int i=0; i < healthpacks.size(); i++)//draw healthpacks
                {
                		HealthPack pack = healthpacks.get(i);
                		g2d.drawImage(pack.getImage(), pack.getXDrawn(), pack.getYDrawn(), null);
                }
                g2d.drawImage(p.getImage(), p.getXDrawn(), p.getYDrawn(),null); //draw player
                for (int i = 0; i < bullets.size(); i++) //draw bullets
                {
                        bullet m = (bullet) bullets.get(i);
                        g2d.drawImage(m.getImage(), (int)(m.getX()) - (m.getXBack() - b.getX()) -5, (int)(m.getY()) - (m.getYBack() - b.getY()) -12,null);
                }
                g2d.drawImage(p.getGunImage(),p.getXDrawn(), p.getYDrawn() + 50,null);
                for (int i=0; i < enemies.size(); i++) //draw enemies
                {
                        enemy en = (enemy) enemies.get(i);
                        g2d.drawImage(en.getImage(), (int)(en.getX() + b.getX()), (int)(en.getY() + b.getY()),null);
                }
                g2d.drawString("Gun: " + p.getGun() + ", " + p.getClip() + "/" + p.getAmmo(), 10, 20); //display gun, clip, and ammo
                g2d.drawString("Score: " + score, 10, 40); //display score
                g2d.drawString("Health: " + p.getHP(), 10, 60); //display health
                g2d.drawString("Level: " + level, 10, 80); //display level
                g2d.drawString("HighScore: " + highScore, 10, 100); //display high score
                if(takingDamage) //if taking damage, tint screen red
                	{
                    g2d.setColor(new Color(255, 0, 0,50));
                	g2d.fillRect(0, 0, 900, 900);
                    g2d.setColor(Color.black);
                	}
                if(pause) // if paused, tint screen black
                {
                    g2d.setColor(new Color(0, 0, 0,50));
                	g2d.fillRect(0, 0, 900, 900);
                    g2d.setColor(Color.black);
                    g2d.drawString("PAUSED", 430, 320);
                    g2d.drawString("PRESS SPACE FOR NEW GAME", 360, 340);
                }
                
                if(!alive) // if game is lost, tint screen black & dislpay play again
                {
                    g2d.setColor(new Color(0, 0, 0,100));
                	g2d.fillRect(0, 0, 900, 900);
                    g2d.setColor(Color.black);
                    g2d.drawString("GAME OVER", 430, 330);
                    g2d.drawString("PRESS SPACE FOR NEW GAME", 360, 350);
                }
        }
        
     
         private class AL extends KeyAdapter //keyListener class
     {
             public void keyReleased(KeyEvent e) {
            	 if(!pause){
                     p.keyReleased(e);
                     b.keyReleased(e);
            	 }
             }

             public void keyPressed(KeyEvent e) {
            	 	 pressKey(e);
            	 if(!pause){
                     p.keyPressed(e);
                     b.keyPressed(e);
            	 }
             }
     }
      
         private class ML implements MouseListener //mouse listener class
         {
                
                    public void mousePressed(MouseEvent e) {
                        p.mousePress(e);
                    }
                    public void mouseReleased(MouseEvent e) {
                        p.mouseRelease(e);
                    }
                    public void mouseEntered(MouseEvent e)
                    {
                    	if(!pause)
                    		setCustomCursor();
                    }
                    public void mouseExited(MouseEvent e)
                    {
                        resetCursor();
                    }
                    public void mouseClicked(MouseEvent e){}
                }
         }