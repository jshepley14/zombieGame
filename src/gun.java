import java.awt.Image;

import javax.swing.ImageIcon;


public class gun {
     
        boolean loaded;
        int frate, damage, clip, status, ammo;
        String name;
        Image still;
        ImageIcon i, i2;
     
     
        public gun(String naem)
        {
                loaded = true;
        		name = naem;
        		ammo = 700;
                if(naem.equals("Pistol")) //specifying which kind of gun it is
                {
                        damage = 15;
                        frate = 40;
                        clip = 10;
                        i = new ImageIcon("pistol.png");
                        i2 = new ImageIcon("pistolleft.png");
                }
                if(naem.equals("AK"))
                {
                		damage = 10;
                		frate = 15;
                		clip = 25;
                		i = new ImageIcon("AK.png");
                		i2 = new ImageIcon("AKleft.png");
                }
                if(naem.equals("Minigun"))
                {
                		damage = 6;
                		frate = 3;
                		clip = 1000;
                		i = new ImageIcon("Minigunright.png");
                		i2 = new ImageIcon("Minigunleft.png");
                }
                if(naem.equals("Rifle"))
                {
                		damage = 50;
                		frate = 100;
                		clip = 4;
                		i = new ImageIcon("rifle.png");
                		i2 = new ImageIcon("rifleleft.png");
                }
            	status = frate;
        }
       
        public Image getImage(boolean left) //gets gun image depending on direction facing
        {
        	if(left)
        		still = i2.getImage();
        	else
        		still = i.getImage();
        	return still;
        }
        
        public int getClip() //gets ammo in clip
        {
        	return ammo;
        }
        
        public int reload(int ammoTotal) //reloads ammo in clip and returns ammo remaining
        {
        	if(ammo != clip)
        	{
        		int ammoNeeded = clip - ammo;
        		if(ammoTotal - ammoNeeded > 0)
        		{
        			ammo = clip;
        			return ammoTotal-ammoNeeded;
        		}
        		else
        		{
        			ammo += ammoTotal;
        			return 0;
        		}
        	}
        	return ammoTotal;
        }
        
        public String getName() //gives name of gun
        {
        	return name;
        }
        
        public void updateGun() //updates gun to allow for a firing rate
        {
                if(status < frate)
                {
                        status ++;
                        loaded = false;
                }
                else loaded = true;
        }

        public void fire()
        {
                status = 0;
                ammo --;
        }
       
        public int getDamage()
        {
                return damage;
        }
     
        public boolean loaded()
        {
                return loaded;
        }
      
        public boolean readyToFire()
        {
                return true;
        }
     
}