import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class MyPanel extends JPanel {
	private static final long serialVersionUID = 3426940946811133635L;
	private static final int GRID_X = 25;
	private static final int GRID_Y = 25;
	private static final int INNER_CELL_SIZE = 35;
	private static final int TOTAL_COLUMNS = 9;
	private static final int TOTAL_ROWS =9;
	private Random rand = new Random();
	private int bombCount = 10;
	public int x = -1;
	public int y = -1;
	public int mouseDownGridX = 0;
	public int mouseDownGridY = 0;
	//Color and location of mines
	public Color[][] colorArray = new Color[TOTAL_COLUMNS][TOTAL_ROWS];
	public int [][] mineArray = new int [bombCount][2]; 
	public int [][]coordArray = new int[bombCount-1][bombCount-1];
	public int [] xCoord = {37,72,107,142,177,212,247,282,317};
	public int [] yCoord = {50,85,120,155,190,225,260,295,330};
	public MyPanel() {   //This is the constructor... this code runs first to initialize
		if (INNER_CELL_SIZE + (new Random()).nextInt(1) < 1) {        //Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("INNER_CELL_SIZE must be positive!");
		}
		if (TOTAL_COLUMNS + (new Random()).nextInt(1) < 2) {        //Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_COLUMNS must be at least 2!");
		}
		if (TOTAL_ROWS + (new Random()).nextInt(1) < 3) {        //Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_ROWS must be at least 3!");
		}
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //The rest of the grid
			for (int y = 0; y < TOTAL_ROWS; y++) {
				colorArray[x][y] = Color.WHITE;
			}
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Compute interior coordinates
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		int x2 = getWidth() - myInsets.right - 1;
		int y2 = getHeight() - myInsets.bottom - 1;
		int width = x2 - x1;
		int height = y2 - y1;
		//Paint the background
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x1, y1, width + 1, height + 1);
		//Draw the grid minus the bottom row (which has only one cell)
		//By default, the grid will be 10x10 (see above: TOTAL_COLUMNS and TOTAL_ROWS) 
		g.setColor(Color.BLACK);
		for (int y = 0; y <= TOTAL_ROWS ; y++) {
			g.drawLine(x1 + GRID_X, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)), x1 + GRID_X + ((INNER_CELL_SIZE + 1) * TOTAL_COLUMNS), y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)));
		}
		for (int x = 0; x <= TOTAL_COLUMNS; x++) {
			g.drawLine(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y, x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y + ((INNER_CELL_SIZE + 1) * (TOTAL_ROWS )));
		}
		//Paint cell colors
		for (int x = 0; x < TOTAL_COLUMNS; x++) {
			for (int y = 0; y < TOTAL_ROWS; y++) {
				if ((x == 0) || (y != TOTAL_ROWS )) {
					Color c = colorArray[x][y];
					g.setColor(c);
					g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);
				}
			}
		}
		
		
		
	}
	public int getGridX(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 1) {   //Outside the rest of the grid
			return -1;
		}
		return x;
	}
	public int getGridY(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 1) {   //Outside the rest of the grid
			return -1;
		}
		return y;
	}
	
	/**method Mines
	 * Distributes mines randomly, the amount of mines is set by "bombCount"
	 */
	public void Mines()
	{
		for(int i =0; i<bombCount ; i++)
		{
			int nx = rand.nextInt(bombCount-1);
			int ny = rand.nextInt(bombCount-1);
			if((coordArray[nx][ny] == 1) && (i !=0))
			{
				i--;
			}
			else
			{
				mineArray[i][0] = nx;
				mineArray[i][1] = ny;
			}
			coordArray[nx][ny]= 1;
		//	System.out.println(nx+","+ny);
		}
	}  
	/**Method hasWon
	 * Check if game has been won
	 */
	public void hasWon(int j){
		int uncovered = j;
		if(uncovered == ((TOTAL_ROWS*TOTAL_COLUMNS)-bombCount)){
			//win
			JOptionPane.showMessageDialog(this, "You didn't hit a mine!","YOU WIN", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		else{
			//do nothing because game hasn't been won
		}
	}
	/**method GameOver
	 * Reset/redistribute mines and other blocks
	 */
	public void gameOver(){
		for(int i =0;i<bombCount;i++){
			colorArray[mineArray[i][0]][mineArray[i][1]] = Color.BLACK;
		}
		hasWon(0);
		JOptionPane.showMessageDialog(this, "You hit a mine! Game Over!","Game Over", JOptionPane.WARNING_MESSAGE);
		System.exit(0);
	}
	/**Method aroundMines()
	 * Calculates the number of mines around a the uncovered square using colors
	 */
	public void aroundMines()
	{
		int x= mouseDownGridX;
		int y= mouseDownGridY;
		int countMines=0;
//		System.out.println();
//		System.out.println(x+","+y);
		for(int i =0; i<10; i++)
		{
			if(x!=0 && y!=0 && x!=8 && y!=8)  // squares that are not on corners
			{
				if(x-1== mineArray[i][0] && y-1 == mineArray[i][1]) //1
				{
					countMines++;
				}
				if(x== mineArray[i][0] && y-1 == mineArray[i][1]) //2
				{
					countMines++;
				}
				if(x+1== mineArray[i][0] && y-1 == mineArray[i][1]) //3
				{
					countMines++;
				}
				if(x-1== mineArray[i][0] && y == mineArray[i][1]) //4
				{
					countMines++;
				}
				if(x+1== mineArray[i][0] && y == mineArray[i][1]) //5
				{
					countMines++;
				}
				if(x-1== mineArray[i][0] && y+1 == mineArray[i][1]) //6
				{
					countMines++;
				}
				if(x== mineArray[i][0] && y+1 == mineArray[i][1]) //7
				{
					countMines++;
				}
				if(x+1== mineArray[i][0] && y+1 == mineArray[i][1]) //8
				{
					countMines++;
				}
			}
			else if(x==0 && y==0) // square on (0,0)
			{
				if(x+1== mineArray[i][0] && y == mineArray[i][1])
				{
					countMines++;
				}
				if(x== mineArray[i][0] && y+1 == mineArray[i][1])
				{
					countMines++;
				}
				if(x+1== mineArray[i][0] && y+1 == mineArray[i][1])
				{
					countMines++;
				}
			}
			else if(x==0 && y==8) // square on (0,8)
			{
				if(x== mineArray[i][0] && y-1 == mineArray[i][1])
				{
					countMines++;
				}
				if(x+1== mineArray[i][0] && y-1 == mineArray[i][1])
				{
					countMines++;
				}
				if(x+1== mineArray[i][0] && y == mineArray[i][1])
				{
					countMines++;
				}
			}
			else if(x==8 && y==0) // square on (8,0)
			{
				if(x-1== mineArray[i][0] && y == mineArray[i][1])
				{
					countMines++;
				}
				if(x-1== mineArray[i][0] && y+1 == mineArray[i][1])
				{
					countMines++;
				}
				if(x== mineArray[i][0] && y+1 == mineArray[i][1])
				{
					countMines++;
				}
			}
			else if(x==8 && y==8) // square on (8,8)
			{
				if(x-1== mineArray[i][0] && y-1 == mineArray[i][1])
				{
					countMines++;
				}
				if(x== mineArray[i][0] && y-1 == mineArray[i][1])
				{
					countMines++;
				}
				if(x-1== mineArray[i][0] && y == mineArray[i][1])
				{
					countMines++;
				}
			}
			else if(x!=0 && x!=8 && y==0 ) //top corner 
			{
				if(x-1== mineArray[i][0] && y == mineArray[i][1]) //4
				{
					countMines++;
				}
				if(x+1== mineArray[i][0] && y == mineArray[i][1]) //5
				{
					countMines++;
				}
				if(x-1== mineArray[i][0] && y+1 == mineArray[i][1]) //6
				{
					countMines++;
				}
				if(x== mineArray[i][0] && y+1 == mineArray[i][1]) //7
				{
					countMines++;
				}
				if(x+1== mineArray[i][0] && y+1 == mineArray[i][1]) //8
				{
					countMines++;
				}
			}
			else if(x==0 && y!=0 && y!=8) //left corner
			{
				if(x== mineArray[i][0] && y-1 == mineArray[i][1]) //2
				{
					countMines++;
				}
				if(x+1== mineArray[i][0] && y-1 == mineArray[i][1]) //3
				{
					countMines++;
				}
				if(x+1== mineArray[i][0] && y == mineArray[i][1]) //5
				{
					countMines++;
				}
				if(x== mineArray[i][0] && y+1 == mineArray[i][1]) //7
				{
					countMines++;
				}
				if(x+1== mineArray[i][0] && y+1 == mineArray[i][1]) //8
				{
					countMines++;
				}
			}
			else if(x==8 && y!=0 && y!=8) // right  corner
			{
				if(x-1== mineArray[i][0] && y-1 == mineArray[i][1]) //1
				{
					countMines++;
				}
				if(x== mineArray[i][0] && y-1 == mineArray[i][1]) //2
				{
					countMines++;
				}
				if(x-1== mineArray[i][0] && y == mineArray[i][1]) //4
				{
					countMines++;
				}
				if(x-1== mineArray[i][0] && y+1 == mineArray[i][1]) //6
				{
					countMines++;
				}
				if(x== mineArray[i][0] && y+1 == mineArray[i][1]) //7
				{
					countMines++;
				}
			}
			else if(x!=0 && x!=8 && y==8) // bottom corner
			{
				if(x-1== mineArray[i][0] && y-1 == mineArray[i][1]) //1
				{
					countMines++;
				}
				if(x== mineArray[i][0] && y-1 == mineArray[i][1]) //2
				{
					countMines++;
				}
				if(x+1== mineArray[i][0] && y-1 == mineArray[i][1]) //3
				{
					countMines++;
				}
				if(x-1== mineArray[i][0] && y == mineArray[i][1]) //4
				{
					countMines++;
				}
				if(x+1== mineArray[i][0] && y == mineArray[i][1]) //5
				{
					countMines++;
				}
			}
		}
		if (countMines==1)
		{
			colorArray[x][y]=Color.YELLOW; //1 mine	
		}
		else if(countMines==2)
		{
			colorArray[x][y]=Color.GREEN; //2 mines
		}
		else if(countMines==3)
		{
			colorArray[x][y]=Color.CYAN; //3 mines
		}
		else if(countMines==4)
		{
			colorArray[x][y]= Color.BLUE; //4 mines
		}
		else if(countMines==5)
		{
			colorArray[x][y]= Color.MAGENTA; //5 mines
		}
		else if(countMines==6)
		{
			colorArray[x][y]= Color.PINK; //6 mines
		}
		else if(countMines==7)
		{
			colorArray[x][y]= Color.ORANGE; //7 mines
		}
		else if(countMines==8)
		{
			colorArray[x][y]= Color.DARK_GRAY; //8 mines
		}
		//System.out.println("Numero de bombas alrededor:"+countMines);
	}
}