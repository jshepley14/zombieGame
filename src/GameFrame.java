


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



//import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.Timer;


public class GameFrame implements ActionListener{

	private ArrayList<panelthingy> games = new ArrayList<panelthingy>();
	private JFrame frame;
	private Timer time;
	private int highScore;
	
        public GameFrame()
        {
        		highScore = 0;
                frame = new JFrame("Test Game");
                frame.setVisible(true);
                frame.setLayout(null);
                panelthingy hello = new panelthingy(highScore);
                games.add(hello);
                panelthingy hai = games.get(0);
                frame.add(hai);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(900,900);
                hai.setBounds(0,0,900,900);
                frame.setLocationRelativeTo(null);
                hai.requestFocus();
                time = new Timer(10, this); //interval at which the actionPerformed method fires
                time.start();
        }
        public void actionPerformed(ActionEvent e)
        {
        	if(e.getActionCommand() == null)
        	{
        		if(games.size() > 0)
        		{
        			panelthingy pt = games.get(0);
        			pt.Perform();
        			if(pt.reset())
        			{
        				if(pt.getScore() > highScore)
        					highScore = pt.getScore();
        				System.out.println(pt.getScore());
        				System.out.println(highScore);
        				games.remove(pt);
        				System.out.println("OH NOOOOOO ZAMBIES");
            			panelthingy newpt = new panelthingy(highScore);
            			frame.remove(pt);
            			frame.add(newpt);
                        newpt.setBounds(0,0,900,900);
            			newpt.setVisible(true);
            			games.add(newpt);
            			newpt.requestFocus();
        			}
        		}
        	}
        }
        

}
