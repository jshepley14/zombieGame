


import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class bullet {

        int xbackinit, ybackinit, xvelinit, yvelinit, xDrawn, yDrawn, damage;
        double dist, x, y, dx, dy;
        boolean visible = true;
        Image still;
        ImageIcon i = new ImageIcon("bullet.png");
     
        public bullet(int daemage, double xangle, double yangle, double speed, int xback, int yback, int xvelin, int yvelin)
        {
        		damage = daemage;
                xvelinit = xvelin;
                yvelinit = yvelin;
                xbackinit = xback;
                ybackinit = yback;
                x = 450;
                y = 425;
                dist = Math.sqrt( (xangle*xangle) + (yangle*yangle) );
                dx = ((speed/dist)*(xangle)) - xvelinit;
                dy = ((speed/dist)*(yangle)) - yvelinit;
                still = i.getImage();
        }

        public Rectangle getBounds()
        {
                return new Rectangle((int)xDrawn - 5,(int)yDrawn - 5, getImage().getWidth(null), getImage().getHeight(null));
        }
       
        public void update()
        {
                x += dx;
                y += dy;
                if(xDrawn > 900 || yDrawn > 900 || xDrawn < 0 || yDrawn < 0)
                        visible = false;
        }
     
        public int getDamage()
        {
        	return damage;
        }
        
        public void giveRealX(int realx)
        {
                xDrawn = realx;
        }
     
        public void giveRealY(int realy)
        {
                yDrawn = realy;
        }
     
        public int getYBack()
        {
                return ybackinit;
        }
     
        public int getXBack()
        {
                return xbackinit;
        }
     
        public double getX()
        {
                return x;
        }
        public double getY()
        {
                return y;
        }
     
        public boolean getVisible()
        {
                return visible;
        }
     
        public Image getImage()
        {
                return still;
        }
}