import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		//Game Tittle
		JFrame myFrame = new JFrame("Mine Sweeper Game");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(400, 400);
		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);
		myPanel.Mines();
		myFrame.setVisible(true);
		
		
		// legend
		JFrame myFrame1 = new JFrame("Mines' legend");
		myFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame1.setLocation(810, 150);
		myFrame1.setSize(200, 550);
		MyLegendPanel legend = new MyLegendPanel();
		myFrame1.add(legend);
		myFrame1.setVisible(true);
		
		
		
	}
}