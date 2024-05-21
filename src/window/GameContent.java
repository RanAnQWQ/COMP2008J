package window;

import GameTable.ShuffleMajiang;
import HuHelper.Hu;
import Player.Computer;
import Player.InitPlayer;
import Player.Movement;
import Player.Player;
import tiles.Tilemap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameContent extends JFrame {

    public static GameWindow gameWindow;

    //Player player = new Player();
    public static AddComputerTile addComputerTile;

    public Lock lock = new ReentrantLock();
    public Condition game = lock.newCondition();
    public Condition click = lock.newCondition();

    public GameContent() {
        addComputerTile = new AddComputerTile();
    }
    public void aGame() throws InterruptedException {  // the methods to create a game
        //GameWindow gameWindow = new GameWindow();  // initial the game window
        int playerIndex;
        gameWindow = new GameWindow();


        // initial the players
        InitPlayer initPlayer = new InitPlayer();

        // create 4 players, and 3 of them are computers
        Player player = initPlayer.player;
        Computer computer1 = initPlayer.computer1, computer2 = initPlayer.computer2, computer3 = initPlayer.computer3;
        // create and shuffle the Majiang cards
        ShuffleMajiang shuffleMajiang = new ShuffleMajiang();


        // deal the cards for the first time evenly (13 cards each)
        ShuffleMajiang.maJiangsIndex=0;
        initPlayer.haveFirstBoard();


        // sort the cards
        Collections.sort(player.getPlayerMajiangs());  //list the players tile list from small to large
        Collections.sort(computer1.getPlayerMajiangs());
        Collections.sort(computer2.getPlayerMajiangs());
        Collections.sort(computer3.getPlayerMajiangs());


        gameWindow.tilemap = new Tilemap();

        //gameWindow.runButton(shuffleMajiang.river.get(shuffleMajiang.river.size()-1));
        gameWindow.setTileNumber(player.getPlayerMajiangs());
        // display the computers' tiles
        addComputerTile.addComputerTileToWindow(computer1.getPlayerMajiangs().size(),gameWindow.gamePanel);
        addComputerTile.addComputerTileToWindow(computer2.getPlayerMajiangs().size(),gameWindow.gamePanel);
        addComputerTile.addComputerTileToWindow(computer3.getPlayerMajiangs().size(),gameWindow.gamePanel);


        System.out.println("player: "+gameWindow.tileNumber);  //print the players tiles
        System.out.println("playersize: "+player.playerMajiangs.size());

        System.out.println("computer1: "+computer1.getPlayerMajiangs());
        System.out.println("size1: "+computer1.getPlayerMajiangs().size());

        System.out.println("computer2: "+computer2.getPlayerMajiangs());
        System.out.println("size2: "+computer2.getPlayerMajiangs().size());

        System.out.println("computer3: "+computer3.getPlayerMajiangs());
        System.out.println("size3: "+computer3.getPlayerMajiangs().size());



        //ArrayList<Integer> b =new ArrayList<>(Arrays.asList(11, 12, 13, 12, 13, 14));//test chi
        //player.isPeng();
        //boolean pengJudge = true;
        //boolean gangJudge = true;




        // to judge if any of the players will Hu or not
        boolean playerHu = Hu.isHu(player.getPlayerMajiangs(), player.getCardsToDisplay());
        boolean computer1Hu = Hu.isHu(computer1.getPlayerMajiangs(), computer1.getCardsToDisplay());
        boolean computer2Hu = Hu.isHu(computer2.getPlayerMajiangs(), computer2.getCardsToDisplay());
        boolean computer3Hu = Hu.isHu(computer3.getPlayerMajiangs(), computer3.getCardsToDisplay());

        //while ((!playerHu) && (!computer1Hu) && (!computer2Hu) && (!computer3Hu)) {
            int turn =initPlayer.playerIndex;
            //for (turn = 0; turn < 4; turn++){
                // if it's player's turn
                if (turn == initPlayer.playerIndex){
                    // gain a card
                    player.gainCard(0);

                    lock.lock();
                    try {
                        click.await();
                        gameWindow.addTileToWindow(lock, game, click); // add user player's tiles to window
                    } finally {
                        lock.unlock();
                    }

                    gameWindow.hideTiles();
                    //gameWindow.setbuttons(b, pengJudge, gangJudge);

                    // in the first turn, the host will discard a card
                    System.out.println(gameWindow.cardToDiscard);
                    System.out.println("Clicked!!");


                    // conduct gw 644

                    // add the card into the river (GameWindow line 683-689)


                    gameWindow.resetTurn();

                }

                // computers' turns
                else{
                    //gameWindow.setbuttons(b, pengJudge, gangJudge);

                    // in the first turn, the host will discard a card
                    System.out.println(gameWindow.cardToDiscard);


                    // conduct gw 644

                    // add the card into the river (GameWindow line 683-689)


                    gameWindow.resetTurn();

                }
            //}
            turn++;
        //}


        if (ShuffleMajiang.river.size() > 0){// 暂时没有进循环//////////////////////
            System.out.println("size: "+ ShuffleMajiang.river.size());
            System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");

            // remove the card
            initPlayer.players.get(initPlayer.playerIndex).discardMajiang(ShuffleMajiang.riverIndex);



        }


        //gameWindow.addTileToWindow(player); // add user player's tiles to window
        //gameWindow.hideTiles();
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
    public void dice_button(int sum,ImagePanel gamePanel) {  //written by Jinyan.Shen

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
                       addComputerTile.showComputerTile();
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
                }, 5000);
                // set a timer, let the number of dice and the arrow label remain 5s and remove;
            }
        });
    }

    public static ImageIcon choose_arrow(int sum) {
        ImageIcon arrowIcon;

        switch (sum % 4) {
            case 1:
                arrowIcon = new ImageIcon("Majiang/src/window/Arrow/ArrowDown.png");
                break;
            //East;

            case 2:
                arrowIcon = new ImageIcon("Majiang/src/window/Arrow/ArrowRight.png");
                break;
            //North;

            case 3:
                arrowIcon = new ImageIcon("Majiang/src/window/Arrow/ArrowTop.png");
                break;
            //West;

            default:
                arrowIcon = new ImageIcon("Majiang/src/window/Arrow/ArrowLeft.png");
                break;
            //South;

        }
        return arrowIcon;
    }
    //---------------------not the first game--------------------
    public String getFirstHost() {
        String host;
        int sum = gameWindow.getsum();
        /////////////////////add/////////////////////
        switch (sum % 4) {
            case 1:
                host = "East";
                break;
            case 2:
                host = "North";
                break;
            case 3:
                host = "West";
                break;
            default:
                host = "South";
                break;
        }
        return host;

    }
    // Store the chosen host in the first round;
}
