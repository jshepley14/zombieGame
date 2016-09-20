
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class HealthPack extends gameEntity{

	Image still;
	ImageIcon i;
	int amount, kind; //defines the type of ammo it is. 0 is pistol, 1 is AK, 2 is rifle, and 3 is minigun
	
	public HealthPack(int xInit, int yInit, int number)
	{
		super(xInit,yInit);
		amount = number;
		i = new ImageIcon("health_package.png");
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
}