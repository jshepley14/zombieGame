

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class world2 extends gameEntity{

        boolean leftp, rightp, upp, downp; //booleans for the keyEvent methods
        Image still; //image to be painted
        ImageIcon i = new ImageIcon("background1.png"); //what the background looks like

        public world2(int xInit, int yInit)
        {
                        super(xInit,yInit);
                speed = 2; //speed at which the background moves across the screen per 10ms.
                still = i.getImage(); //image = the world image
                x = 0; //initial x position is 0
                y = 0; // '' y
        }

public void update(int xOffset, int yOffset)
{
    x += dx;
    y += dy;
    if(x >=0) x = 0; //defines where the player cannot move:
    if(x <=-1500) x = -1500;
    if(y >=0) y = 0;
    if(y <=-900) y = -900;
	xDrawn = (int)x + xOffset;
	yDrawn = (int)y + yOffset;
    if(xDrawn >=0) xDrawn = 0;
    if(xDrawn <=-1500) xDrawn = -1500;
    if(yDrawn >=0) yDrawn = 0;
    if(yDrawn <=-900) yDrawn = -900;
}
        public Image getImage() //returns what the world looks like for the paint method in panelthingy
        {
                return still;
        }
     
        public void keyPressed(KeyEvent e) //responds to the keyEvents
        {
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_A) //if A is pressed
                {
                        leftp = true; //left is pressed
                        if(rightp) //if right is also pressed
                                dx = 0; //x motion is 0
                        else
                                dx = speed; //if right isn't also pressed, background speed x = speed
                }
                if(key == KeyEvent.VK_D) // '' D
                {
                        rightp = true; // right is pressed
                        if(leftp) //if left is also pressed
                                dx = 0; //x motion is 0
                        else
                                dx = -speed; //if right isn't also pressed, background speed x = speed
                }
                if(key == KeyEvent.VK_S)
                {
                        downp = true;
                        if(upp)
                                dy = 0;
                        else
                                dy = -speed;
                }
                if(key == KeyEvent.VK_W)
                {
                        upp = true;
                        if(downp)
                                dy = 0;
                        else
                                dy = speed;
                }
               
                // '' for S and W (up and down) ^^^
        }
        public void keyReleased(KeyEvent e)
        {
                // '' for releasing keys
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_A)
                {
                        leftp = false;
                        if(rightp)
                                dx = -speed;
                        else
                        {
                                dx = 0;
                                if(dy == 0)
                                {
                                        still = i.getImage();
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
                                if(dy == 0)
                                {
                                        still = i.getImage();
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
                                if(dy == 0)
                                {
                                        still = i.getImage();
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
                                if(dy == 0)
                                {
                                        still = i.getImage();
                                        movement = 0;
                                }
                        }
                }
        }
}
