import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
        int uncovered = 0;
        public void mousePressed(MouseEvent e) {
                switch (e.getButton()) {
                case 1://Left mouse button
                        Component c = e.getComponent();
                        while (!(c instanceof JFrame)) {
                                c = c.getParent();
                                if (c == null) {
                                        return;
                                }
                        }
                        JFrame myFrame = (JFrame) c;
                        MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
                        Insets myInsets = myFrame.getInsets();
                        int x1 = myInsets.left;
                        int y1 = myInsets.top;
                        e.translatePoint(-x1, -y1);
                        int x = e.getX();
                        int y = e.getY();
                        myPanel.x = x;
                        myPanel.y = y;
                        myPanel.mouseDownGridX = myPanel.getGridX(x, y);
                        myPanel.mouseDownGridY = myPanel.getGridY(x, y);
                        myPanel.repaint();
                        break;
                case 3://Right mouse button
                        Component k = e.getComponent();
                        while (!(k instanceof JFrame)) {
                                k = k.getParent();
                                if (k == null) 
                                {
                                        return;
                                }
                        }
                        JFrame myFrame1 = (JFrame) k;
                        MyPanel myPanel1 = (MyPanel) myFrame1.getContentPane().getComponent(0);
                        Insets myInsets1 = myFrame1.getInsets();
                        int x11 = myInsets1.left;
                        int y11 = myInsets1.top;
                        e.translatePoint(-x11, -y11);
                        int x2 = e.getX();
                        int y2 = e.getY();
                        myPanel1.x = x2;
                        myPanel1.y = y2;
                        myPanel1.mouseDownGridX = myPanel1.getGridX(x2, y2);
                        myPanel1.mouseDownGridY = myPanel1.getGridY(x2, y2);
                        myPanel1.repaint();
                        break;
                default://Some other button (2 = Middle mouse button, etc.)
                        //Do nothing
                        break;
                }
        }
        public void mouseReleased(MouseEvent e) 
        {
                switch (e.getButton()) 
                {
                case 1://Left mouse button
                        Component c = e.getComponent();
                        while (!(c instanceof JFrame)) 
                        {
                                c = c.getParent();
                                if (c == null) 
                                {
                                        return;
                                }
                        }
                        JFrame myFrame = (JFrame)c;
                        MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
                        Insets myInsets = myFrame.getInsets();
                        int x1 = myInsets.left;
                        int y1 = myInsets.top;
                        e.translatePoint(-x1, -y1);
                        int x = e.getX();
                        int y = e.getY();
                        myPanel.x = x;
                        myPanel.y = y;
                        int gridX = myPanel.getGridX(x, y);
                        int gridY = myPanel.getGridY(x, y);

                        if ((gridX == -1) || (gridY == -1)) {
                                //Is releasing outside
                                //Do nothing
                        } else {
                                if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
                                        //Released the mouse button on a different cell where it was pressed
                                        //Do nothing
                                } else {
                                        //Released the mouse button on the same cell where it was pressed
                                        for(int i = 0; i < myPanel.mineArray.length;i++)
                                        {
                                                if(myPanel.mineArray[i][0] == myPanel.mouseDownGridX && myPanel.mineArray[i][1] == myPanel.mouseDownGridY)
                                                {
                                                        System.out.println("YOU LOSE " + myPanel.mineArray[i][0] + "," + myPanel.mineArray[i][1]);
                                                        Color newColor = Color.BLACK;
                                                        myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
                                                        myPanel.repaint();
                                                        myPanel.gameOver();
                                                        break;
                                                }
                                                else
                                                {
                                                        Color newColor = null;
                                                        newColor = Color.GRAY;
                                                        Color oldColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];
                                                        if(oldColor.equals(Color.RED)){
                                                                //do nothing
                                                        } else
                                                        if(oldColor.equals(Color.GRAY))
                                                        {
                                                                //do nothing

                                                        } else
                                                        if(oldColor.equals(Color.YELLOW))
                                                        {
                                                                //do nothing

                                                        } else
                                                        if(oldColor.equals(Color.BLUE))
                                                        {
                                                                //do nothing
                                                        } else
                                                        if(oldColor.equals(Color.CYAN))
                                                        {
                                                                //do nothing
                                                        } else
                                                        if(oldColor.equals(Color.GREEN))
                                                        {
                                                                //do nothing
                                                        } else
                                                        if(oldColor.equals(Color.MAGENTA))
                                                        {
                                                                //do nothing
                                                        } else
                                                        if(oldColor.equals(Color.PINK))
                                                        {
                                                                //do nothing
                                                        } else
                                                        if(oldColor.equals(Color.ORANGE))
                                                        {
                                                                //do nothing
                                                        } else
                                                        if(oldColor.equals(Color.DARK_GRAY))
                                                        {
                                                                //do nothing
                                                        } else
                                                        if(oldColor.equals(Color.GRAY))
                                                        {
                                                                //do nothing
                                                        }
                                                        else
                                                        {
                                                                myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
                                                                myPanel.repaint();
                                                                uncovered++;
                                                                myPanel.hasWon(uncovered);
                                                                myPanel.aroundMines();
                                                                myPanel.repaint();
                                                        }
                                                }
                                        }
                                }
                        }
                        myPanel.repaint();
                        break;
                case 3://Right mouse button: Handling of "flags"
                        Component k = e.getComponent();
                        while (!(k instanceof JFrame)) {
                                k = k.getParent();
                                if (k == null) 
                                {
                                        return;
                                }
                        }
                        JFrame myFrame1 = (JFrame) k;
                        MyPanel myPanel1 = (MyPanel) myFrame1.getContentPane().getComponent(0);
                        Insets myInsets1 = myFrame1.getInsets();
                        int x11 = myInsets1.left;
                        int y11 = myInsets1.top;
                        e.translatePoint(-x11, -y11);
                        int x2 = e.getX();
                        int y2 = e.getY();
                        myPanel1.x = x2;
                        myPanel1.y = y2;
                        int gridX1 = myPanel1.getGridX(x2, y2);
                        int gridY1 = myPanel1.getGridY(x2, y2);
                        //Had pressed outside
                        //Do nothing
                        if ((myPanel1.mouseDownGridX == 0) || (myPanel1.mouseDownGridY == 0)) {
                                Color newColor = null; 
                                newColor = Color.RED;
                                Color oldColor = myPanel1.colorArray[myPanel1.mouseDownGridX][myPanel1.mouseDownGridY];
                                if(oldColor.equals(Color.RED)){
                                        newColor = Color.WHITE;
                                }
                                if(oldColor.equals(Color.GRAY)){
                                        //do nothing
                                }
                                else{
                                        myPanel1.colorArray[myPanel1.mouseDownGridX][myPanel1.mouseDownGridY] = newColor;
                                        myPanel1.repaint();
                                }
                        }
                        else {
                                if ((gridX1 == -1) || (gridY1 == -1)) {
                                        //Is releasing outside
                                        //Do nothing
                                } else {
                                        if ((myPanel1.mouseDownGridX != gridX1) || (myPanel1.mouseDownGridY != gridY1)) {
                                                //Released the mouse button on a different cell where it was pressed
                                                //Do nothing
                                        } else {
                                                //Released the mouse button on the same cell where it was pressed
                                                if ((gridX1 == 0) || (gridY1 == 0)) {
                                                        //On the left column and on the top row... do nothing
                                                } else {
                                                        //On the grid other than on the left column and on the top row:
                                                        Color newColor = null;
                                                        Color oldColor = myPanel1.colorArray[myPanel1.mouseDownGridX][myPanel1.mouseDownGridY];
                                                        newColor = Color.RED;
                                                        if(oldColor.equals(Color.RED)){
                                                                newColor = Color.WHITE;
                                                        }
                                                        if(oldColor.equals(Color.GRAY)){
                                                                //do nothing
                                                        }
                                                        else{
                                                                myPanel1.colorArray[myPanel1.mouseDownGridX][myPanel1.mouseDownGridY] = newColor;
                                                                myPanel1.repaint();
                                                        }
                                                }
                                        }
                                }
                                myPanel1.repaint();
                                break;
                        }
                }
        }
}