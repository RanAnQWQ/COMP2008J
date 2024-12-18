package window;

import GameTable.ShuffleMajiang;
import tiles.Tilemap;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static window.GameContent.gameWindow;


public class AddComputerTile extends AddTile {
    public JLabel tileLabel;


    /////////////////////////////computer tiles////////////////////////
    public void addComputer1Tile(int size, ImagePanel gamePanel){
        removeComputerTiles(gamePanel,1);
        addComputerTileToWindow(size, gamePanel, 1, 840, 270);
    }
    public void addComputer2Tile(int size, ImagePanel gamePanel){
        removeComputerTiles(gamePanel,2);
        addComputerTileToWindow(size, gamePanel, 2, 800, 20);
    }
    public void addComputer3Tile(int size, ImagePanel gamePanel){
        removeComputerTiles(gamePanel,3);
        addComputerTileToWindow(size, gamePanel, 3, 50, 270);
    }

    public void addComputerTileToWindow(int size, ImagePanel gamePanel, int computerName, int startX, int startY) {  //written by Siying.Li
        ImageIcon computerIcon = null;

        if (computerName == 1) {
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/1right.png");
        } else if (computerName == 2) {
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/1back.png");
        } else if (computerName == 3) {
            computerIcon = new ImageIcon("src/tiles/tileBackAndSide/1left.png");
        } else {
            return;
        }

        int width = computerIcon.getIconWidth() / 4;
        int height = computerIcon.getIconHeight() / 4;
        Image scaledComputerImage = computerIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        computerIcon = new ImageIcon(scaledComputerImage);

        for (int i = 0; i < size; i++) {
//            JLabel tileLabel = new JLabel(computerIcon);
            tileLabel = new JLabel(computerIcon);
            tileLabel.setName("computer" + computerName);
            int tileX = startX;
            int tileY = startY;

            if (computerName == 1) {
                tileX = startX - 5 * i;  // 每张牌水平向左移动5个像素
                tileY = startY - 26 * i;  // 每张牌垂直向上移动52个像素
            } else if (computerName == 2) {
                tileX = startX - 35 * i;  // 每张牌水平向左移动35个像素
                tileY = startY;
            } else {
                tileX = startX + 5 * i;  // 每张牌水平向左移动5个像素
                tileY = startY - 26 * i;  // 每张牌垂直向下移动26个像素
            }

            tileLabel.setBounds(tileX, tileY, computerIcon.getIconWidth(), computerIcon.getIconHeight());
            gamePanel.add(tileLabel);
            //tileLabel.setVisible(false);
//            if(computerName == 3){
//                gamePanel.setComponentZOrder(tileLabel,0);
//            }

        }

        gamePanel.revalidate();
        gamePanel.repaint();
    }
    public void removeComputerTiles(ImagePanel gamePanel,int computerName){
        String labelName = "computer"+computerName;
        Component[] components = gamePanel.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel && labelName.equals(component.getName())) {
                gamePanel.remove(component);
            }
        }
        gamePanel.revalidate();
        gamePanel.repaint();
    }





    /**
     * create new smaller tiles and set them in the river
     *
//     * @param tileIcon       tile image
//     * @param tileNum        tilemap
     * @param discardStartX  the site in X for river tiles
     * @param discardStartY   the site in Y for river tiles

     */


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


    @Override
    public void addTileToRiverX(ImageIcon tileIcon, int tileNum, int discardStartX, int discardStartY, int maxTilesPerRow, JPanel gamePanel, int computerName) {
        ArrayList<Integer> riverList;
        removeRiverTile(gamePanel, computerName);

        if (computerName == 2) {
            riverList = gameWindow.computer2.playerRiver;
            System.out.println("computer2 river list: " + riverList);
        } else {
            riverList = gameWindow.player.playerRiver;
            System.out.println("human river list: " + riverList);
        }


        int newTileX = discardStartX;  // set the new site
        int newTileY = discardStartY;
        int tilesInRow = 0;  // the number of tiles in the line currently

        Tilemap tilemap = gameWindow.tilemap;
        for (int i = 0; i < riverList.size(); i++) {
            Integer currentTileNum = riverList.get(i);
            String currentTilePath = tilemap.getTilePath(currentTileNum);
            tileIcon = new ImageIcon(currentTilePath);
            int angle = (computerName == 2) ? 180 : 0;
            Image rotatedImage = rotateImage(tileIcon.getImage(), angle);

            int newWidth=26;
            int newHeight=38;

            JLabel newTile = new JLabel(new ImageIcon(rotatedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)));
            newTile.setName("riverTile" + computerName + "_" + i);

            newTile.setBounds(newTileX, newTileY, newWidth, newHeight); // 修改这里的尺寸以符合 newWidth 和 newHeight
            gamePanel.add(newTile);
            if (computerName == 2) {
                newTileX -= newWidth;
            } else {
                newTileX += newWidth;
            }
            tilesInRow++;
            if (tilesInRow >= 10) {
                tilesInRow = 0;
                newTileX = discardStartX;
                newTileY += (computerName == 2) ? -38 : 38;
            }
        }

        gamePanel.revalidate();
        gamePanel.repaint();
    }



    @Override
    public void addTileToRiverY(ImageIcon tileIcon, int tileNum, int discardStartX, int discardStartY, int maxTilesPerCol, JPanel gamePanel, int computerName) {
        ArrayList<Integer> riverList;
        removeRiverTile(gamePanel,computerName);
        if(computerName==1){
            riverList = gameWindow.computer1.playerRiver;
            System.out.println("computer1 river list: "+riverList);
        }else{
            riverList = gameWindow.computer3.playerRiver;
            System.out.println("computer3 river list: "+riverList);
        }

        int newTileX = discardStartX;
        int newTileY = discardStartY;  // set the new site
        int tilesInCol = 0;  // the number of tiles in the column currently

        Tilemap tilemap = gameWindow.tilemap;
        for (int i = 0; i < riverList.size(); i++) {
            Integer currentTileNum = riverList.get(i);
            String currentTilePath = tilemap.getTilePath(currentTileNum);
            tileIcon = new ImageIcon(currentTilePath);
            int angle = (computerName == 1) ? -90:90;
            Image rotatedImage = rotateImage(tileIcon.getImage(), angle);

            int newWidth = 26;  // set the new size
            int newHeight = 38;

            JLabel newTile = new JLabel(new ImageIcon(rotatedImage.getScaledInstance(newHeight, newWidth, Image.SCALE_SMOOTH)));
            newTile.setName("riverTile"+computerName + "_" + i);

            newTile.setBounds(newTileX, newTileY, newHeight, newWidth);
            gamePanel.add(newTile);  // show the clicked tiles
            if(computerName ==1){
                newTileY -= newWidth;
            }else{
                newTileY += newWidth;
            }
            tilesInCol++;
            if (tilesInCol >= 10) {  // change the col if the tiles are too many in current col
                tilesInCol = 0;
                newTileY = discardStartY;
                newTileX += (computerName == 1) ? 38 : -38;  // change the col and site in X

            }
        }

        // choose one card into the river\
        //ShuffleMajiang.river.add(tileNum);
        //ShuffleMajiang.riverIndex++;
        // test
        System.out.println("@@@" + ShuffleMajiang.riverIndex + " in " + ShuffleMajiang.river);

        gamePanel.revalidate();
        gamePanel.repaint();
    }

//        public void removeRiverTile(JPanel gamePanel, int computerName){
//        System.out.println("remove river tile for computer"+computerName);
//        Component[] components = gamePanel.getComponents();
//        for (Component comp : components) {
//            if (comp instanceof JLabel && comp.getName() != null && comp.getName().startsWith("riverTile" + computerName)) {
//                gamePanel.remove(comp);
//            }
//        }
//        gamePanel.revalidate();
//        gamePanel.repaint();
//    }

//    public void removeRiverTile(JPanel gamePanel, int computerName) {
//        Component[] components = gamePanel.getComponents();
//        for (Component component : components) {
//            if (component instanceof JLabel) {
//                String name = component.getName();
//                System.out.println("Component Name: " + name);  // Print all component names
//                if (name != null && name.contains("riverTile" + computerName)) {
//                    System.out.println("Removing: " + name);  // Debugging information
//                    gamePanel.remove(component);
//                }
//            }
//        }
//        gamePanel.revalidate();
//        gamePanel.repaint();
//    }





    public void removeRiverTile(JPanel gamePanel, int computerName) {
        System.out.println("remove river tile for computer" + computerName);
        Component[] components = gamePanel.getComponents();

        for (Component comp : components) {
            if (comp instanceof JLabel && comp.getName() != null && comp.getName().startsWith("riverTile" + computerName)) {
                System.out.println("Removing component: " + comp.getName()); // 调试信息
                gamePanel.remove(comp);
            }
        }
        gamePanel.revalidate();
        gamePanel.repaint();
    }





    @Override
    public BufferedImage rotateImage(Image img, int angle) {
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        double theta = Math.toRadians(angle);
        AffineTransform transform = new AffineTransform();
        transform.rotate(theta, width / 2.0, height / 2.0);

        double cosTheta = Math.abs(Math.cos(theta));
        double sinTheta = Math.abs(Math.sin(theta));
        int newWidth = (int) Math.floor(width * cosTheta + height * sinTheta);
        int newHeight = (int) Math.floor(height * cosTheta + width * sinTheta);

        BufferedImage rotatedBufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - width) / 2, (newHeight - height) / 2);

        int x = width / 2;
        int y = height / 2;
        at.rotate(theta, x, y);
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        op.filter(bufferedImage, rotatedBufferedImage);

        return rotatedBufferedImage;
    }

    @Override
    public void addTileToDisplay(Integer card, int scaledWidth, int scaledHeight, JPanel gamePanel, int computerName){
        Tilemap tilemap = new Tilemap();
        if (computerName == 1) {
            addTileToDisplayY(new ImageIcon(new ImageIcon(tilemap.getTilePath(card)).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), 900,570, gamePanel, 1);
        } else if (computerName == 2) {
            addTileToDisplayX(new ImageIcon(new ImageIcon(tilemap.getTilePath(card)).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), 778,100, gamePanel, 2);
        } else if(computerName == 3) {
            addTileToDisplayY(new ImageIcon(new ImageIcon(tilemap.getTilePath(card)).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), 230,80, gamePanel, 3);
        }else {
            addTileToDisplayX(new ImageIcon(new ImageIcon(tilemap.getTilePath(card)).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), 358,570, gamePanel, 4);
        }

    }

    @Override
    public void addTileToDisplayX(ImageIcon tileIcon, int discardStartX, int discardStartY, JPanel gamePanel, int computerName) {
        int angle = (computerName == 2) ? 180:0;
        Image rotatedImage = rotateImage(tileIcon.getImage(), angle);

        int tileWidth = tileIcon.getIconWidth();
        int tileHeight = tileIcon.getIconHeight();
        int newWidth = tileWidth / 2;  // set the new size
        int newHeight = tileHeight / 2;

        JLabel newTile = new JLabel(new ImageIcon(rotatedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)));
        int newTileX = discardStartX;  // set the new site
        int newTileY = discardStartY;
        int tilesInRow = 0;  // the number of tiles in the line currently

        for (Component comp : gamePanel.getComponents()) {  // reset the new tile's site
            if (comp instanceof JLabel && comp != newTile) {
                JLabel existingTile = (JLabel) comp;
                if (existingTile.getX() == newTileX && existingTile.getY() == newTileY) {
                    //newTileX += existingTile.getWidth() - 26;  // site in X
                    newTileX += (computerName == 2) ? -(existingTile.getWidth() - 26) : (existingTile.getWidth() - 26);  // site in Y
                    tilesInRow++;
                    if (tilesInRow >= 10) {  // change the line if the tiles are too many in current line
                        tilesInRow = 0;
                        newTileX = discardStartX;
                        newTileY += (computerName == 2) ? -38 : 38;
                        // newTileY += 38;  // change the line and site in Y
                    }
                }
            }
        }

        newTile.setBounds(newTileX, newTileY, tileWidth, tileHeight);
        gamePanel.add(newTile);  // show the clcked tiles

        gamePanel.revalidate();
        gamePanel.repaint();
    }


    @Override
    public void addTileToDisplayY(ImageIcon tileIcon, int discardStartX, int discardStartY, JPanel gamePanel, int computerName) {

        int tileWidth = tileIcon.getIconWidth();
        int tileHeight = tileIcon.getIconHeight();
        int newWidth = tileWidth / 2;  // set the new size
        int newHeight = tileHeight / 2;

        int angle = (computerName == 1) ? -90:90;
        Image rotatedImage = rotateImage(tileIcon.getImage(), angle);

        JLabel newTile = new JLabel(new ImageIcon(rotatedImage.getScaledInstance(newHeight, newWidth, Image.SCALE_SMOOTH)));
        int newTileX = discardStartX;
        int newTileY = discardStartY;  // set the new site
        int tilesInCol = 0;  // the number of tiles in the column currently

        for (Component comp : gamePanel.getComponents()) { // reset the new tile's site
            if (comp instanceof JLabel && comp != newTile) {
                JLabel existingTile = (JLabel) comp;
                if (existingTile.getX() == newTileX && existingTile.getY() == newTileY) {
                    newTileY += (computerName == 1) ? -(existingTile.getHeight())  : (existingTile.getHeight());  // site in Y
                    tilesInCol++;
                    if (tilesInCol >= 10) {  // change the col if the tiles are too many in current col
                        tilesInCol = 0;
                        newTileY = discardStartY;
                        newTileX += (computerName == 1) ? 38 : -38;  // change the col and site in X

                    }
                }
            }
        }

        newTile.setBounds(newTileX, newTileY, newHeight, newWidth);
        gamePanel.add(newTile);  // show the clicked tiles
        // choose one card into the river\

        gamePanel.revalidate();
        gamePanel.repaint();
    }

    @Override
    public void addNetwork1Tile(int size, ImagePanel gamePanel) {

    }

    @Override
    public void addNetwork2Tile(int size, ImagePanel gamePanel) {

    }

    @Override
    public void addNetwork3Tile(int size, ImagePanel gamePanel) {

    }

    @Override
    public void addNetworkTileToWindow(int size, ImagePanel gamePanel, int computerName, int startX, int startY) {

    }

    @Override
    public void removeNetworkTiles(ImagePanel gamePanel, int computerName) {

    }

    @Override
    void showNetworkTile() {

    }



}
