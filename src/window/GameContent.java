package window;

import GameTable.ShuffleMajiang;
import Player.Computer;
import Player.HumanPlayer;
import Player.InitPlayer;
import tiles.Tilemap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.*;

public class GameContent extends JFrame {

    public static GameWindow gameWindow;

    public AddComputerTile addComputerTile;

    public static String host;
    public static int hostNumber;

    public GameContent() {
        addComputerTile = new AddComputerTile();
    }

    public  void aGame() throws InterruptedException {  // the methods to create a game

        //GameWindow gameWindow = new GameWindow();  // initial the game window
        gameWindow = new GameWindow();
        //dice_button(sum, gameWindow.gamePanel);
        // initial the players
        InitPlayer initPlayer = new InitPlayer();


        // create 4 players, and 3 of them are computers
        HumanPlayer player = initPlayer.player;

        Computer computer1 = initPlayer.computer1, computer2 = initPlayer.computer2, computer3 = initPlayer.computer3;
        ShuffleMajiang shuffleMajiang = new ShuffleMajiang();

        // deal the cards for the first time evenly (13 cards each)
        ShuffleMajiang.maJiangsIndex=0;
        initPlayer.haveFirstBoard();


        // sort the cards
        Collections.sort(player.getPlayerMajiangs());  //list the players tile list from small to large
        Collections.sort(computer1.getPlayerMajiangs());
        Collections.sort(computer2.getPlayerMajiangs());
        Collections.sort(computer3.getPlayerMajiangs());

        String host=getFirstHost();
        System.out.println(host);
        int index;

        // in the first turn, the host will gain one more card to discard first
        System.out.println("aaaaaaaaaaaaaa");
        // index are got from the window

        gameWindow.tilemap = new Tilemap();

        //gameWindow.runButton(shuffleMajiang.river.get(shuffleMajiang.river.size()-1));
        //gameWindow.setTileNumber(player.getPlayerMajiangs());
        System.out.println("player: "+player.playerMajiangs);  //print the players tiles
        System.out.println("playersize: "+player.playerMajiangs.size());

        gameWindow.setComputer1(computer1);
        System.out.println("computer1: "+computer1.playerMajiangs);
        System.out.println("size1: "+computer1.playerMajiangs.size());

        gameWindow.setComputer2(computer2);
        System.out.println("computer2: "+computer2.getPlayerMajiangs());
        System.out.println("size2: "+computer2.getPlayerMajiangs().size());

        gameWindow.setComputer3(computer3);
        gameWindow.setHuman(player);
        System.out.println("computer3: "+computer3.getPlayerMajiangs());
        System.out.println("size3: "+computer3.getPlayerMajiangs().size());


        addComputerTile.addComputer1Tile(computer1.getPlayerMajiangs().size(),gameWindow.gamePanel);
        addComputerTile.addComputer2Tile(computer2.getPlayerMajiangs().size(),gameWindow.gamePanel);
        addComputerTile.addComputer3Tile(computer3.getPlayerMajiangs().size(),gameWindow.gamePanel);



        gameWindow.addTileToWindow(player.playerMajiangs); // add user player's tiles to window
        gameWindow.hideTiles();
        // gameWindow.setbuttons(b, pengJudge, gangJudge,c, false, false);

        // in the first turn, the host will discard a card
        System.out.println(",,,,,,"+gameWindow.cardToDiscard);

    }


    public void init(String host){
        if(host.equals("East")){
            //hostNumber=0;
            hostNumber=4;

        }else if(host.equals("South")){
            //hostNumber=1;
            hostNumber=3;

        }else if(host.equals("West")){
            //hostNumber=2;
            hostNumber=2;

        }else if(host.equals("North")){
            //hostNumber=3;
            hostNumber=1;

        }
        gameWindow.startRobotPlaySequence(hostNumber);
    }


    int decideNextComputer(Integer card, Computer computer1, Computer computer2, Computer computer3,HumanPlayer player){
        if (computer1.isGang(card)) {
            computer1.Gang(card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 1, addComputerTile);
            player.playerRiver.remove(card);
            addComputerTile.addComputer1Tile(computer1.playerMajiangs.size(),gameWindow.gamePanel);
            gameWindow.addTileToWindow(player.playerMajiangs);
            return 1;
        }else if(computer2.isGang(card)){
            computer2.Gang(card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 2, addComputerTile);
            player.playerRiver.remove(card);
            addComputerTile.addComputer2Tile(computer2.playerMajiangs.size(),gameWindow.gamePanel);
            gameWindow.addTileToWindow(player.playerMajiangs);
            return 2;
        }else if(computer3.isGang(card)){
            computer3.Gang(card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 3, addComputerTile);
            player.playerRiver.remove(card);
            addComputerTile.addComputer3Tile(computer3.playerMajiangs.size(),gameWindow.gamePanel);
            gameWindow.addTileToWindow(player.playerMajiangs);
            return 3;
        }else if(computer1.isPeng(card)){
            computer1.Peng(card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 1, addComputerTile);
            player.playerRiver.remove(card);
            addComputerTile.addComputer1Tile(computer1.playerMajiangs.size(),gameWindow.gamePanel);
            gameWindow.addTileToWindow(player.playerMajiangs);
            return 1;
        }else if(computer2.isPeng(card)){
            computer2.Peng(card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 2, addComputerTile);
            player.playerRiver.remove(card);
            addComputerTile.addComputer2Tile(computer2.playerMajiangs.size(),gameWindow.gamePanel);
            return 2;
        }else if(computer3.isPeng(card)){
            computer3.Peng(card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 3, addComputerTile);
            player.playerRiver.remove(card);
            addComputerTile.addComputer3Tile(computer3.playerMajiangs.size(),gameWindow.gamePanel);
            gameWindow.addTileToWindow(player.playerMajiangs);
            return 3;
        }else if(!computer1.isChi(card).isEmpty()){
            System.out.println("chi41");
            computer1.Chi(card,gameWindow.scaledWidth,gameWindow.scaledHeight,gameWindow.gamePanel,1,addComputerTile);
            player.playerRiver.remove(card);
            addComputerTile.addComputer1Tile(computer1.playerMajiangs.size(),gameWindow.gamePanel);
            gameWindow.addTileToWindow(player.playerMajiangs);
            System.out.println(computer1.cardsToDisplay.size()+"chi41 ctd");
            return 1;
        }else {
            computer1.gainMajiang();
            return 1;
        }

    }


    ///////////////////////method of help button including menu,restart and rules in game window///////////////////////////
    void helpButtons(ImagePanel gamePanel) {  //written by Siying.Li
        JButton helpButton = new JButton("HELP");  //set the help button including three buttons
        Font helpFont = new Font("Arial", Font.BOLD, 24);
        helpButton.setFocusPainted(false);
        helpButton.setFont(helpFont);
        helpButton.setHorizontalAlignment(SwingConstants.CENTER);
        helpButton.addActionListener(new ActionListener() {  //if click the game button, the menu window will be closed and the game window will be open
            public void actionPerformed(ActionEvent e) {
                JFrame helpWindow = new JFrame("Help");
                helpWindow.setSize(240, 240);
                helpWindow.setLayout(new GridLayout(3, 1));

                JButton menuButton = new JButton("Main Menu");  //set the menu button
                menuButton.setFocusPainted(false);
                Font backFont = new Font("Arial", Font.BOLD, 30);
                menuButton.setSize(80, 80);
                menuButton.setFont(backFont);
                menuButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {  //if click the menu button, the game window will be closed and the main menu window will be open
                        helpWindow.dispose();
                        gameWindow.dispose();

                        new MainMenuWindow();
                    }
                });
                helpWindow.add(menuButton);

                JButton restartButton = new JButton("Restart");  //set the restart button
                restartButton.setFocusPainted(false);
                Font restartFont = new Font("Arial", Font.BOLD, 30);
                restartButton.setSize(80, 80);
                restartButton.setFont(restartFont);
                restartButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {  //if click the restart button, the game window will reopen for a new game
                        helpWindow.dispose();
                        gameWindow.dispose();
                        try {
                            aGame();
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                helpWindow.add(restartButton);

                JButton rulesButton = new JButton("Rules");  //set the rules button
                rulesButton.setFocusPainted(false);
                Font rulesFont = new Font("Arial", Font.BOLD, 30);
                rulesButton.setSize(80, 80);
                rulesButton.setFont(rulesFont);
                rulesButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {  //if click the rules button, the game window will not be closed and the rules window will be open
                        new RulesWindow().setVisible(true);
                    }
                });
                helpWindow.add(rulesButton);
                helpWindow.setLocationRelativeTo(null);
                helpWindow.setVisible(true);
            }
        });
        helpButton.setBounds(1100, 0, 100, 50);  //set the button site and size
        gamePanel.add(helpButton);
    }


    ////////////////////////////dice button///////////////////////////
    public void dice_button(int sum, ImagePanel gamePanel) {  //written by Jinyan.Shen

        JButton num = new JButton("THROW DICE");
        Font dice_font = new Font("Arial", Font.BOLD, 24);
        num.setFocusPainted(false);
        num.setFont(dice_font);
        num.setHorizontalAlignment(SwingConstants.CENTER);
        num.setBounds(475, 0, 200, 50);
        gamePanel.add(num);
        // set the button to notice the host.

        num.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component comp : gamePanel.getComponents()) {// the players' tiles will be showed after clicking the dice button
                    if (comp instanceof JLabel && ((JLabel) comp).getClientProperty("tileNumber") != null && !comp.isVisible()) {
                        comp.setVisible(true);
                        comp.setEnabled(true);
                    }
                }

                for (Component comp : gamePanel.getComponents()) {
                    if (comp instanceof JLabel && ((JLabel) comp).getClientProperty("tileNumber") != null) {
                    }
                }

                Image scaledArrow;
                ImageIcon scaledArrowIcon;
                JLabel arrowLabel;

                ImageIcon arrowIcon = choose_arrow(sum);
                scaledArrow = arrowIcon.getImage().getScaledInstance(arrowIcon.getIconWidth() / 15, arrowIcon.getIconHeight() / 15, Image.SCALE_DEFAULT);
                scaledArrowIcon = new ImageIcon(scaledArrow);
                arrowLabel = new JLabel(scaledArrowIcon);
                // set the arrowLabel object to display (and remove after 5s);

                arrowLabel.setHorizontalAlignment(SwingConstants.CENTER);
                arrowLabel.setVerticalAlignment(SwingConstants.CENTER);
                //set horizontal center & vertical center;

                gamePanel.setLayout(null);
                //remove the default lay

                int labelWidth = scaledArrowIcon.getIconWidth();
                int labelHeight = scaledArrowIcon.getIconHeight();
                int panelWidth = gamePanel.getWidth();
                int panelHeight = gamePanel.getHeight();
                int x = (panelWidth - labelWidth) / 2;
                int y = (panelHeight - labelHeight) / 2;
                arrowLabel.setBounds(x, y, labelWidth, labelHeight);
                //compute the position of the arrow;

                gamePanel.add(arrowLabel);
                // add the label picture to game screen;

                JLabel sumLabel = new JLabel("" + sum);
                Font label_font = new Font("Arial", Font.BOLD, 50);
                sumLabel.setLocation(560, 250);
                sumLabel.setFont(label_font);
                sumLabel.setHorizontalAlignment(SwingConstants.CENTER);
                sumLabel.setSize(70, 50);
                sumLabel.setBackground(Color.WHITE);
                sumLabel.setOpaque(true);
                gamePanel.add(sumLabel);
                revalidate();
                repaint();
                // set the label, show the sum of 2 dices;

                // remove the original button once the label occur;
                Timer timer = new Timer();

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        SwingUtilities.invokeLater(() -> {
                            gamePanel.remove(num);
                            gamePanel.remove(sumLabel);
                            gamePanel.remove(arrowLabel);
                            gamePanel.revalidate();
                            gamePanel.repaint();
                        });
                        timer.cancel();
                    }
                }, 3000);
                // set a timer, let the number of dice and the arrow label remain 5s and remove;
                init(host);
                System.out.println("host:"+host);
                System.out.println("hostnumber"+hostNumber);
            }
        });
    }

    public static ImageIcon choose_arrow(int sum) {
        ImageIcon arrowIcon;

        switch (sum % 4) {
            case 1:
                arrowIcon = new ImageIcon("src/window/Arrow/ArrowDown.png");
                break;
            //East;

            case 2:
                arrowIcon = new ImageIcon("src/window/Arrow/ArrowRight.png");
                break;
            //North;

            case 3:
                arrowIcon = new ImageIcon("src/window/Arrow/ArrowTop.png");
                break;
            //West;

            default:
                arrowIcon = new ImageIcon("src/window/Arrow/ArrowLeft.png");
                break;
            //South;

        }
        return arrowIcon;
    }
    //---------------------not the first game--------------------
    public String getFirstHost() {
        int sum;

        try {
            sum = gameWindow.getsum();
        } catch (NullPointerException e) {
            System.out.println("The game hasn't started yet, no host, waiting for throwing dice!");
            return null;
        }
        System.out.println("sum:"+sum);
        switch (sum % 4) {
            case 1:
                //host = "South";
                host="East";
                break;
            case 2:
                host = "North";
                break;
            case 3:
                //host = "North";
                host="West";
                break;
            default:
                host="South";
                //host = "East";

                break;
        }

        return host;
    }

    // Store the chosen host in the first round;
}
