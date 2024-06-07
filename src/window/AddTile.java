package window;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AddTile {
    public abstract void addNetwork1Tile(int size, ImagePanel gamePanel);
    public abstract void addNetwork2Tile(int size, ImagePanel gamePanel);
    public abstract void addNetwork3Tile(int size, ImagePanel gamePanel);

    public abstract void addNetworkTileToWindow(int size, ImagePanel gamePanel, int computerName, int startX, int startY);
    public abstract void removeNetworkTiles(ImagePanel gamePanel, int computerName);

    abstract void showNetworkTile();

//    public abstract void addTileToRiverX(ImageIcon tileIcon, int tileNum, int discardStartX, int discardStartY, int maxTilesPerRow, JPanel gamePanel, int computerName);

    public abstract void addTileToRiverX(ImageIcon tileIcon, int tileNum, int discardStartX, int discardStartY, int maxTilesPerRow, JPanel gamePanel, int computerName);


//    @Override
//    public void addTileToRiverX(ImageIcon tileIcon, int tileNum, int discardStartX, int discardStartY, int maxTilesPerRow, JPanel gamePanel, int computerName ) {
//        ArrayList<Integer> riverList;
//        removeRiverTile(gamePanel, computerName);
//
//        if (computerName == 2) {
//            riverList = gameWindow.computer2.playerRiver;
//            System.out.println("computer2 river list: " + riverList);
//        } else {
//            riverList = gameWindow.player.playerRiver;
//            System.out.println("human river list: " + riverList);
//        }
//        //riverList.add(tileNum);
//
//        int newTileX = discardStartX;  // set the new site
//        int newTileY = discardStartY;
//        int tilesInRow = 0;  // the number of tiles in the line currently
//
//        for (int i = 0; i < riverList.size(); i++) {
//
//            int angle = (computerName == 2) ? 180 : 0;
//            Image rotatedImage = rotateImage(tileIcon.getImage(), angle);
//
//            int tileWidth = tileIcon.getIconWidth();
//            int tileHeight = tileIcon.getIconHeight();
//            int newWidth = tileWidth / 2;  // set the new size
//            int newHeight = tileHeight / 2;
//
//            JLabel newTile = new JLabel(new ImageIcon(rotatedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)));
//            newTile.setName("riverTile" );
//
//            newTile.setBounds(newTileX, newTileY, tileWidth, tileHeight);
//            gamePanel.add(newTile);  // show the clcked tiles
//            if (computerName == 2) {
//                newTileX -= newWidth;
//            } else {
//                newTileX += newWidth;
//            }
//            // newTileX += (computerName == 2) ? -(newWidth - 26) : (newWidth - 26);
//            tilesInRow++;
//            if (tilesInRow >= 10) {
//                tilesInRow = 0;
//                newTileX = discardStartX;
//                newTileY += (computerName == 2) ? -38 : 38;
//            }
//        }
//
//
//
//        /*// written by Qiyue Zhu.........................
//        // choose one tile into the river
//        ShuffleMajiang.river.add(tileNum);
//        ShuffleMajiang.riverIndex++;
//        // test
//        System.out.println("@@@" + ShuffleMajiang.riverIndex + " in " + ShuffleMajiang.river);
//*/
//        gamePanel.revalidate();
//        gamePanel.repaint();
//    }


    //    @Override
//    public void addTileToRiverX(ImageIcon tileIcon, int tileNum, int discardStartX, int discardStartY, int maxTilesPerRow, JPanel gamePanel, int computerName ) {
//        ArrayList<Integer> riverList;
//        removeRiverTile(gamePanel, computerName);
//
//        if (computerName == 2) {
//            riverList = gameWindow.computer2.playerRiver;
//            System.out.println("computer2 river list: " + riverList);
//        } else {
//            riverList = gameWindow.player.playerRiver;
//            System.out.println("human river list: " + riverList);
//        }
//        //riverList.add(tileNum);
//
//        int newTileX = discardStartX;  // set the new site
//        int newTileY = discardStartY;
//        int tilesInRow = 0;  // the number of tiles in the line currently
//
//        for (int i = 0; i < riverList.size(); i++) {
//
//            int angle = (computerName == 2) ? 180 : 0;
//            Image rotatedImage = rotateImage(tileIcon.getImage(), angle);
//
//            int tileWidth = tileIcon.getIconWidth();
//            int tileHeight = tileIcon.getIconHeight();
//            int newWidth = tileWidth / 2;  // set the new size
//            int newHeight = tileHeight / 2;
//
//            JLabel newTile = new JLabel(new ImageIcon(rotatedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)));
//            newTile.setName("riverTile" );
//
//            newTile.setBounds(newTileX, newTileY, tileWidth, tileHeight);
//            gamePanel.add(newTile);  // show the clcked tiles
//            if (computerName == 2) {
//                newTileX -= newWidth;
//            } else {
//                newTileX += newWidth;
//            }
//            // newTileX += (computerName == 2) ? -(newWidth - 26) : (newWidth - 26);
//            tilesInRow++;
//            if (tilesInRow >= 10) {
//                tilesInRow = 0;
//                newTileX = discardStartX;
//                newTileY += (computerName == 2) ? -38 : 38;
//            }
//        }
//        gamePanel.revalidate();
//        gamePanel.repaint();
//    }
//    @Override
//    public void addTileToRiverX(ImageIcon tileIcon, int tileNum, int discardStartX, int discardStartY, int maxTilesPerRow, JPanel gamePanel, int computerName ) {
//        ArrayList<Integer> riverList;
//        removeRiverTile(gamePanel, computerName);
//
//        if (computerName == 2) {
//            riverList = gameWindow.computer2.playerRiver;
//            System.out.println("computer2 river list: " + riverList);
//        } else {
//            riverList = gameWindow.player.playerRiver;
//            System.out.println("human river list: " + riverList);
//        }
//        //riverList.add(tileNum);
//        Tilemap tilemap = gameWindow.tilemap;
//
//        int newTileX = discardStartX;  // set the new site
//        int newTileY = discardStartY;
//        int tilesInRow = 0;  // the number of tiles in the line currently
//
//        for (int i = 0; i < riverList.size(); i++) {
//            Integer currentTileNum = riverList.get(i);
//            String currentTilePath = tilemap.getTilePath(currentTileNum);
//            tileIcon = new ImageIcon(currentTilePath);
//
//            int angle = (computerName == 2) ? 180 : 0;
//            Image rotatedImage = rotateImage(tileIcon.getImage(), angle);
//
//            int tileWidth = tileIcon.getIconWidth();
//            int tileHeight = tileIcon.getIconHeight();
//            int newWidth = tileWidth / 4;  // set the new size
//            int newHeight = tileHeight /4;
//
//            JLabel newTile = new JLabel(new ImageIcon(rotatedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)));
//            newTile.setName("riverTile" );
//
//            newTile.setBounds(newTileX, newTileY, tileWidth, tileHeight);
//            gamePanel.add(newTile);  // show the clcked tiles
//            if (computerName == 2) {
//                newTileX -= newWidth;
//            } else {
//                newTileX += newWidth;
//            }
//            // newTileX += (computerName == 2) ? -(newWidth - 26) : (newWidth - 26);
//            tilesInRow++;
//            if (tilesInRow >= 10) {
//                tilesInRow = 0;
//                newTileX = discardStartX;
//                newTileY += (computerName == 2) ? -38 : 38;
//            }
//        }
//        gamePanel.revalidate();
//        gamePanel.repaint();
//    }



    public abstract void addTileToRiverY(ImageIcon tileIcon, int tileNum, int discardStartX, int discardStartY, int maxTilesPerCol, JPanel gamePanel, int computerName);

    public abstract BufferedImage rotateImage(Image img, int angle);

    public abstract void addTileToDisplay(Integer card, int scaledWidth, int scaledHeight, JPanel gamePanel, int computerName);

    public abstract void addTileToDisplayX(ImageIcon tileIcon, int discardStartX, int discardStartY, JPanel gamePanel, int computerName);


    public abstract void addTileToDisplayY(ImageIcon tileIcon, int discardStartX, int discardStartY, JPanel gamePanel, int computerName);

}
