

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class AmmoPack extends gameEntity{
	
	Image still;
	ImageIcon i;
	int amount, kind; //defines the type of ammo it is. 0 is pistol, 1 is AK, 2 is rifle, and 3 is minigun
	
	public AmmoPack(int xInit, int yInit, int type)
	{
		super(xInit,yInit);
		kind = type;
		if(kind == 0)
		{
			i = new ImageIcon("ammoboxPistol.png");
			amount = 25;
		}
		if(kind == 1)
		{
			i = new ImageIcon("ammoboxAK.png");
			amount = 50;
		}
		if(kind == 2)
		{
			i = new ImageIcon("ammoboxRifle.png");
			amount = 8;
		}
		if(kind == 3)
		{
			i = new ImageIcon("ammoboxMG.png");
			amount = 200;
		}
		still = i.getImage();
	}
	
    public Rectangle getBounds()
    {
        return new Rectangle(xDrawn,yDrawn, getImage().getWidth(null), getImage().getHeight(null));
    }
	
	public int getAmount()
	{
		return amount;
	}
	
	public Image getImage()
	{
		return still;
	}
	
	public int getType()
	{
		return kind;
	}
}
