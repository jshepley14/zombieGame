
public class gameEntity {


    protected double x, dx, y, dy;
    protected int xDrawn, yDrawn, movement, speed;
  
    public gameEntity(int xInit, int yInit)
    {
       x=xInit;
       y=yInit;
    }

    public void update(int xOffset, int yOffset)
    {
    	x += dx;
    	y += dy;
    	xDrawn = (int)x + xOffset;
    	yDrawn = (int)y + yOffset;
    }
    
    public double getDX()
    {
            return dx;
    }
 
    public double getDY()
    {
            return dy;
    }

    public int getX()
    {
            return (int)x;
    }
    public int getY()
    {
            return (int)y;
    }
    public int getXDrawn()
    {
    		return xDrawn;
    }
    public int getYDrawn()
    {
    		return yDrawn;
    }

}
