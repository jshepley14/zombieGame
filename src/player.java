

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class player extends gameEntity{

        boolean leftp, rightp, upp, downp, left, firing, loaded, pulling, alive;
        int slot;
        double HP;
        Image still, gunImage;
        ImageIcon i = new ImageIcon("zombiehunter.png");
        ImageIcon i2 = new ImageIcon("zombiehunter2.png");
        ImageIcon[][] images = new ImageIcon[2][4];
        gun[] loadout = new gun[4];
        int[] ammos = new int[4];

        public player(int xInit, int yInit)
        {
            	super(xInit,yInit);
            	left = true;
            	HP = 100;
            	alive = true;
        		slot = 0;
                speed = 2;
                still = i.getImage();
                movement = 0;
                images[0][0] = new ImageIcon("zombiehunter.png");
                images[0][1] = new ImageIcon("zombiehunterwalk12.png");
                images[0][2] = new ImageIcon("zombiehunter.png");
                images[0][3] = new ImageIcon("zombiehunterwalk3.png");
                images[1][0] = new ImageIcon("zombiehunter2.png");
                images[1][1] = new ImageIcon("zombiehunterwalk1.png");
                images[1][2] = new ImageIcon("zombiehunter2.png");
                images[1][3] = new ImageIcon("zombiehunterwalk32.png");
                loadout[0] = new gun("Pistol");
                loadout[1] = new gun("AK");
                loadout[2] = new gun("Rifle");
                loadout[3] = new gun("Minigun");
                ammos[0] = 50;
                ammos[1] = 0;
                ammos[2] = 0;
                ammos[3] = 1000;
                gunImage = loadout[slot].getImage(left);
        }
     
        public void update(int xOffset, int yOffset, boolean leftt) //run every 10ms
        {
        		left = leftt;
                gunImage = loadout[slot].getImage(left); //displays gun image
        		if(left) //checks where it should be facing
        			still = i.getImage();
        		else
        			still = i2.getImage();
        		xDrawn = 400;//sets xDrawn
        		yDrawn = 350;//sets yDrawn
        		if(HP <= 0)//checks if alive
        		{
        			alive = false;
        		}
                if(dx != 0 || dy != 0) //changes images to appear to walk
                {
                	int direction;
                	if(left) direction = 0;
                	else direction = 1;
                still = images[direction][movement/16].getImage();
                if(movement == 63)
                {
                        movement = 0;
                }
                else
                        movement ++;
                }
             
        }
        
        public void heal(int amount) //heal method called when touching healthpack
        {
        	HP += amount;
        	if(HP > 100)
        		HP = 100;
        }
        
        public void recieveAmmo(int amount, int kind) //recieve ammo method when touching ammopack
        {
        	ammos[kind] += amount;
        }
        
        public Image getGunImage() //gives the gun image
        {
        	return gunImage;
        }
        
        public int getHP() //gives the player's HP
        {
        	return (int)HP;
        }
        
        public boolean isAlive()
        {
        	return alive;
        }
        
        public void takeDamage(double dmg) //takes damage when hit by enemy
        {
        	HP -= dmg;
        }
        
        public String getGun() //returns gun name
        {
        	return loadout[slot].getName();
        }
        
        public int getClip() //returns ammo in clip
        {
        	return loadout[slot].getClip();
        }
        
        public int getAmmo() //gives reserve ammo not in clip
        {
        	return ammos[slot];
        }
        
        public Rectangle getBounds()// gives hitbox
        {
                return new Rectangle(xDrawn + 25,yDrawn + 25, getImage().getWidth(null) - 50, getImage().getHeight(null));
        }
       
        public void updateGun() //updates gun due to the timer
        {
        		for(int i=0; i < loadout.length; i++)
        		{
        			loadout[i].updateGun();
        		}
        }
     
        public Image getImage() //returns image
        {
                return still;
        }
     
        public int gunDamage() //gets damage gun is doing
        {
                return loadout[slot].getDamage();
        }
       
        public boolean getLoaded() //gets if gun is loaded
        {
                return loadout[slot].loaded();
        }
     
        public boolean isFiring() //gets if gun is firing
        {
                if(loadout[slot].loaded() && pulling && loadout[slot].getClip() > 0)
                {
                        loadout[slot].fire();
                        return true;
                }
                return false;
        }
     
        public void mousePress(MouseEvent e) //gets if mouse is being pressed
        {
                int button = e.getButton();
                if(button == MouseEvent.BUTTON1)
                {
                        pulling = true;
                }
        }
       
        public void mouseRelease(MouseEvent e)
        {
                int button = e.getButton();
                if(button == MouseEvent.BUTTON1)
                {
                        pulling = false;
                }
        }
       
        public void keyPressed(KeyEvent e) //gets key events to move player
        {
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_A)
                {
                        leftp = true;
                        if(rightp)
                        {
                                dx = 0;
                        		if(left)
                        			still = i.getImage();
                        		else
                        			still = i2.getImage();
                                movement = 0;
                        }
                        else
                                dx = speed;
                }
                if(key == KeyEvent.VK_D)
                {
                        rightp = true;
                        if(leftp)
                        {
                                dx = 0;
                        		if(left)
                        			still = i.getImage();
                        		else
                        			still = i2.getImage();
                                movement = 0;
                        }
                        else
                                dx = -speed;
                }
                if(key == KeyEvent.VK_S)
                {
                        downp = true;
                        if(upp)
                        {
                                dy = 0;
                        		if(left)
                        			still = i.getImage();
                        		else
                        			still = i2.getImage();
                                movement = 0;
                        }
                        else
                                dy = -speed;
                }
                if(key == KeyEvent.VK_W)
                {
                        upp = true;
                        if(downp)
                        {
                                dy = 0;
                        		if(left)
                        			still = i.getImage();
                        		else
                        			still = i2.getImage();
                                movement = 0;
                        }
                        else
                                dy = speed;
                }
                if(key == KeyEvent.VK_Q)
                {
                		if(slot < loadout.length - 1)
                			slot ++;
                		else slot = 0;
                }
                if(key == KeyEvent.VK_1){
                	slot = 0;
                }
                if(key == KeyEvent.VK_2){
                	slot = 1;
                }
                if(key == KeyEvent.VK_3){
                	slot = 2;
                }
                if(key == KeyEvent.VK_4){
                	slot = 3;
                }
                if(key == KeyEvent.VK_R){
                	if(ammos[slot] > 0)
                		ammos[slot] = loadout[slot].reload(ammos[slot]);
                }
        }
        public void keyReleased(KeyEvent e)
        {
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_SPACE)
                {
                        firing = false;
                }
                if(key == KeyEvent.VK_A)
                {
                        leftp = false;
                        if(rightp)
                                dx = -speed;
                        else
                        {
                                dx = 0;
                                if(!downp && !upp)
                                {
                            		if(left)
                            			still = i.getImage();
                            		else
                            			still = i2.getImage();
                                        movement = 0;
                                }
                        }
                }
                if(key == KeyEvent.VK_D)
                {
                        rightp = false;
                        if(leftp)
                                dx = speed;
                        else
                        {
                                dx = 0;
                                if(!downp && !upp)
                                {
                            		if(left)
                            			still = i.getImage();
                            		else
                            			still = i2.getImage();
                                        movement = 0;
                                }
                        }
                }
                if(key == KeyEvent.VK_S)
                {
                        downp = false;
                        if(upp)
                                dy = speed;
                        else
                        {
                                dy = 0;
                                if(!leftp && !rightp)
                                {
                            		if(left)
                            			still = i.getImage();
                            		else
                            			still = i2.getImage();
                                        movement = 0;
                                }
                        }
                }
                if(key == KeyEvent.VK_W)
                {
                        upp = false;
                        if(downp)
                                dy = -speed;
                        else
                        {
                                dy = 0;
                                if(!leftp && !rightp)
                                {
                            		if(left)
                            			still = i.getImage();
                            		else
                            			still = i2.getImage();
                                        movement = 0;
                                }
                        }
                }
        }
}
