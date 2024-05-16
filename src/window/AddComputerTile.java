package window;

import javax.swing.*;
import java.awt.*;

import static window.GameContent.gameWindow;

public class AddComputerTile {
    /////////////////////////////computer tiles////////////////////////
    int Computer1X, Computer1Y;  //set the size for the three kinds pics
    int Computer2X, Computer2Y = 30;
    int Computer3X, Computer3Y;

    public void addComputerTileToWindow(int size, ImagePanel gamePanel) {  //written by Siying.Li
        //-------------computer 1---------------//
        ImageIcon computer1Icon = getComputer1Tile(size); //set computer2 tiles
        JLabel computer1Label = new JLabel(computer1Icon);
        if (size == 13){  // for different numbers of tiles, the picture is in different position
            Computer1X  = 800;
            Computer1Y = 135;
        }else if (size == 10){
            Computer1X = 800 + 5;
            Computer1Y = 135 + 45;
        }else if (size == 7){
            Computer1X = 800 + 5;
            Computer1Y = 135 + 40;
        }else if (size == 4){
            Computer1X = 800 + 5;
            Computer1Y = 135 + 40;
        }else{
            Computer1X = 800 + 5;
            Computer1Y = 135 + 40;
        }
        computer1Label.setBounds(Computer1X,Computer1Y,computer1Icon.getIconWidth(),computer1Icon.getIconHeight());
        gameWindow.gamePanel.add(computer1Label);

        //-------------computer 2---------------//
        ImageIcon computer2Icon = getComputer2Tile(size); //set computer2 tiles
        JLabel computer2Label = new JLabel(computer2Icon);
        if (size == 13){  // for different numbers of tiles, the picture is in different position
            Computer2X = 350;
        }else if (size == 10){
            Computer2X = 350 + 100;
        }else if (size == 7){
            Computer2X = 350 + 200;
        }else if (size == 4){
            Computer2X = 350 + 300;
        }else{
            Computer2X = 350 + 400;
        }
        computer2Label.setBounds(Computer2X,Computer2Y,computer2Icon.getIconWidth(),computer2Icon.getIconHeight());
        gameWindow.gamePanel.add(computer2Label);

        //-------------computer 3---------------//
        ImageIcon computer3Icon = getComputer3Tile(size); //set computer3 tiles
        JLabel computer3Label = new JLabel(computer3Icon);
        if (size == 13){  // for different numbers of tiles, the picture is in different position
            Computer3X = 20;
            Computer3Y = 140;
        }else if (size == 10){
            Computer3X = 20 + 10;
            Computer3Y = 140 - 30;
        }else if (size == 7){
            Computer3X = 20 + 25;
            Computer3Y = 140 - 110;
        }else if (size == 4){
            Computer3X = 20 + 35;
            Computer3Y = 140 - 190;
        }else{
            Computer3X = 20 + 50;
            Computer3Y = 140 - 260;
        }

        computer3Label.setBounds(Computer3X,Computer3Y,computer3Icon.getIconWidth(),computer3Icon.getIconHeight());
        gameWindow.gamePanel.add(computer3Label);

        //---------------------------------------//

        gameWindow.gamePanel.revalidate();
        gameWindow.gamePanel.repaint();
    }

    public ImageIcon getComputer1Tile(int size){  //the method used to get the computer1 tile image
        ImageIcon computerIcon;
        if (size == 13) {  // different images
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/13right.png");
        } else if (size == 10) {
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/10right.png");
        } else if (size == 7){
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/7right.png");
        } else if (size == 4){
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/4right.png");
        } else {
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/1right.png");
        }
        int width = computerIcon.getIconWidth()/3; //get the suitable size
        int height = computerIcon.getIconHeight()/3;
        Image scaledComputerImage = computerIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon (scaledComputerImage);
    }

    public ImageIcon getComputer2Tile(int size){  // the method used to get the computer2 tile image
        ImageIcon computerIcon; //the same as computer1
        if (size == 13) {
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/13back.png");
        } else if (size == 10) {
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/1back.png");
        } else if (size == 7){
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/7back.png");
        } else if (size == 4){
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/4back.png");
        } else {
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/1back.png");
        }
        int width = computerIcon.getIconWidth()/4;
        int height = computerIcon.getIconHeight()/4;
        Image scaledComputerImage = computerIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon (scaledComputerImage);
    }

    public ImageIcon getComputer3Tile(int size){  // the method used to get the computer3 tile image
        ImageIcon computerIcon;  //the sameas computer1
        if (size == 13) {
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/13left.png");
        } else if (size == 10) {
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/10left.png");
        } else if (size == 7){
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/7left.png");
        } else if (size == 4){
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/4left.png");
        } else {
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/1left.png");
        }
        int width = computerIcon.getIconWidth() /3;
        int height = computerIcon.getIconHeight() /3;
        Image scaledComputerImage = computerIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon (scaledComputerImage);
    }
}
