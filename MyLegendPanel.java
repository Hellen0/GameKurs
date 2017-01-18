import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JPanel;
public class MyLegendPanel extends JPanel
{
	private static final long serialVersionUID = 3426940946811133635L;
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//Compute interior coordinates
				Insets myInsets = getInsets();
				int x1 = myInsets.left;
				int y1 = myInsets.top;
				int x2 = getWidth() - myInsets.right - 1;
				int y2 = getHeight() - myInsets.bottom - 1;
				int width = x2 - x1;
				int height = y2 - y1;
				
		// background
		g.setColor(Color.WHITE);
		g.fillRect(x1,y1,width+1,height+1);
		//tittle
		g.setColor(Color.BLACK);
		g.drawString("LEGEND",37,30);
		
		
		//1 mine
		g.setColor(Color.YELLOW);
		g.fillRect(37,50,35,35);
		g.setColor(Color.BLACK);
		g.drawString("1 mine nearby",72,70);
		
		//2 mines
		g.setColor(Color.GREEN);
		g.fillRect(37,90,35,35);
		g.setColor(Color.BLACK);
		g.drawString("2 mines nearby",72,110);
		// 3 mines
		g.setColor(Color.CYAN);
		g.fillRect(37,130,35,35);
		g.setColor(Color.BLACK);
		g.drawString("3 mines nearby",72,150);
		// 4 mines
		g.setColor(Color.BLUE);
		g.fillRect(37,170,35,35);
		g.setColor(Color.BLACK);
		g.drawString("4 mines nearby",72,190);
		//5 mines
		g.setColor(Color.MAGENTA);
		g.fillRect(37,210,35,35);
		g.setColor(Color.BLACK);
		g.drawString("5 mines nearby",72,230);
		//6 mines
		g.setColor(Color.PINK);
		g.fillRect(37,250,35,35);
		g.setColor(Color.BLACK);
		g.drawString("6 mines nearby",72,270);
		//7 mines
		g.setColor(Color.ORANGE);
		g.fillRect(37,290,35,35);
		g.setColor(Color.BLACK);
		g.drawString("7 mines nearby",72,310);
		// 8 mines
		g.setColor(Color.DARK_GRAY);
		g.fillRect(37,330,35,35);
		g.setColor(Color.BLACK);
		g.drawString("8 mines nearby",72,350);
		//o mines
		g.setColor(Color.GRAY);
		g.fillRect(37,370,35,35);
		g.setColor(Color.BLACK);
		g.drawString("0 mines",72,390);
		// mine
		g.setColor(Color.BLACK);
		g.fillRect(37,410,35,35);
		g.setColor(Color.BLACK);
		g.drawString("MINES",72,430);
		//flags
		g.setColor(Color.RED);
		g.fillRect(37,450,35,35);
		g.setColor(Color.BLACK);
		g.drawString("Flags",72,470);
		
		
	}
}
