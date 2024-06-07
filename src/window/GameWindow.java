package window;


import Player.Computer;
import Player.HumanPlayer;
import tiles.Tilemap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.*;
import java.util.concurrent.CountDownLatch;

import static window.GameContent.gameWindow;


public class GameWindow extends JFrame {
    private boolean skipClicked = false;
    public ImagePanel gamePanel;
    public JTextField nameField;
    public Tilemap tilemap;
    public ArrayList<Integer> computer1Number;
    public ArrayList<Integer> computer2Number;
    public ArrayList<Integer> computer3Number;
    public Computer computer1;
    public Computer computer2;
    public Computer computer3;

    public HumanPlayer player;
    public int cardToDiscard;
    public JButton chi = new JButton();
    public JButton skip = new JButton();

    GameContent gameContent = new GameContent();


    public void setComputer1(Computer computer1Number) {
        this.computer1 = computer1Number;
    }

    public void setComputer2(Computer computer2Number) {
        this.computer2 = computer2Number;
    }

    public void setComputer3(Computer computer3Number) {
        this.computer3 = computer3Number;
    }
    public void setHuman(HumanPlayer humanPlayer) {
        this.player = humanPlayer;
    }


    Random number = new Random();
    public int sum = number.nextInt(12) + 2;

    public int getsum() {
        return sum;
    }

    public GameWindow() {
        window_frame();
        gameContent.helpButtons(gamePanel);
        gameContent.dice_button(sum, gamePanel);

        this.setVisible(true);
        chooseAvatar();


        headShot_M("src/profilephoto/bear.png");
        headShot_M("src/profilephoto/cat.png");
        headShot_M("src/profilephoto/cow.png");
        // Add the headShots of 3 machine players;

    }

    private void window_frame() {
        setTitle("Game Window");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        gamePanel = new ImagePanel("src/window/background/background3.jpg");
        gamePanel.setLayout(null);
        add(gamePanel);
    }


    ////////////////////////////set buttons///////////////////////
    public void setbuttons(HumanPlayer player, ArrayList<Integer> chiSet, boolean pengJudge, boolean gangJudge, ArrayList<Integer> tingSet, boolean huJudge, boolean iftrue) {
        //chi_button(player,chiSet, );
        peng_button(pengJudge);
        //gang_button(gangJudge,);
        ting_button(tingSet);
        hu_button(huJudge);

        //boolean ifExist = chi_button(player, chiSet) || peng_button(pengJudge) || gang_button(gangJudge) || ting_button(tingSet) || hu_button(huJudge);

        //skip_button(ifExist);
    }



    public void chi_button(HumanPlayer player, ArrayList<Integer> set, int card) {
        chi.setBorderPainted(false);
        chi.setFocusPainted(false);
        chi.setContentAreaFilled(false);

        boolean judge;
        if (set != null){
            judge = true;
        }else {
            judge = false;
        }

        int[] result = {100};//set for skip;

        CountDownLatch latch = new CountDownLatch(1);


        if (judge) {
            chi.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Chi.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            chi.setVisible(true);
            skip.setVisible(true);
            skip_button(judge);
            chi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Display available");
                    int choice = chi_choice(player, set,card);
                    System.out.println(choice);
                    result[0] = choice;
                    latch.countDown();
                    chi.setVisible(false);
                    skip.setVisible(false);
                }
            });
        } else {
            chi.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Chi_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }
        chi.setBounds(695, 570, 45, 57);

        gamePanel.add(chi);

    }


    private int chi_choice(HumanPlayer player, ArrayList<Integer> option,int card) {
        int num = option.size();

        CountDownLatch latch = new CountDownLatch(1);
        int[] index = new int[1];

        JFrame choice = new JFrame("Choice of Chi Method");
        choice.setSize(480, 210);
        choice.setLocationRelativeTo(null);
        choice.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        choice.setLayout(new BorderLayout());

        JLabel hint = new JLabel("<html><br>Please choose your deck:<br><br>");
        hint.setFont(new Font("Consolas", Font.BOLD, 24));
        hint.setForeground(Color.GRAY);
        hint.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel hintPanel = new JPanel(new BorderLayout());
        hintPanel.add(hint, BorderLayout.SOUTH);
        choice.add(hintPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        mainPanel.setOpaque(false);

        Tilemap tilemap = new Tilemap();

        for (int i = 0; i < num; i += 3) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panel.setOpaque(false);
            panel.putClientProperty("index", i); // 设置面板的索引属性

            for (int j = 0; j < 3; j++) {
                System.out.println("option of chi window: " + option);
                int tileNum = option.get(i + j);
                System.out.println("tilenum "+tileNum);
                String tilePath = tilemap.getTilePath(tileNum);
                ImageIcon icon = new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(132 / 3, 191 / 3, Image.SCALE_SMOOTH));
                JLabel label = new JLabel(icon);
                panel.add(label);
                System.out.println("按理说加完牌了 "+ tileNum);
            }

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    System.out.println("Window Available");
                    JPanel clickedPanel = (JPanel) e.getSource();
                    index[0] = (int) clickedPanel.getClientProperty("index");
                    //0,3,6/0,3
                    if(index[0]==0){
                            if(option.size()==3){
                                System.out.println("玩家已经点了chi");
                                player.Chi(player.isChi(card),3, card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 4, addComputerTile);
                                System.out.println("进行吃牌操作");
                                computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                                robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                                listTiles(player.playerMajiangs, startX, startY);
                            }else if(option.size()==6){
                                System.out.println("玩家已经点了chi");
                                player.Chi(player.isChi(card),1, card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 4, addComputerTile);
                                System.out.println("进行吃牌操作");
                                computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                                robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                                listTiles(player.playerMajiangs, startX, startY);
                            }else {
                                System.out.println("玩家已经点了chi");
                                player.Chi(player.isChi(card),2, card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 4, addComputerTile);
                                System.out.println("进行吃牌操作");
                                computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                                robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                                listTiles(player.playerMajiangs, startX, startY);
                            }

                    }
                    if(index[0]==3){
                        if(option.size()==6){
                            System.out.println("玩家已经点了chi");
                            player.Chi(player.isChi(card),2, card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 4, addComputerTile);
                            System.out.println("进行吃牌操作");
                            computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                            robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                        }else {
                            System.out.println("玩家已经点了chi");
                            player.Chi(player.isChi(card),1, card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 4, addComputerTile);
                            System.out.println("进行吃牌操作");
                            computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                            robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                        }
                    }
                    if(index[0]==6){
                            System.out.println("玩家已经点了chi");
                            player.Chi(player.isChi(card),0, card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 4, addComputerTile);
                            System.out.println("进行吃牌操作");
                            computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                            robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                    }

                    System.out.println("Clicked panel index: " + index[0]);
                    choice.dispose();
                    latch.countDown();
                    for(int i:player.cardsToDisplay){
                        System.out.println(i);
                    }
                }
            });

            mainPanel.add(panel);
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        choice.add(scrollPane, BorderLayout.CENTER);
        choice.setVisible(true);
        choice.setResizable(false);

//        try {
//            latch.await(); // 等待用户选择
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // 返回用户选择的结果，如果选项数量为3且用户选择了唯一的选项，返回3
/*        if (num == 3) {
            for(int i:player.cardsToDisplay){
                System.out.println(i);
            }
            return 3;
        }*/

        return 2 - index[0] / 3;
    }



    public boolean peng_button(boolean judge) {
        JButton peng = new JButton();
        peng.setBorderPainted(false);
        peng.setFocusPainted(false);
        peng.setContentAreaFilled(false);

        final boolean[] result = {false};
        CountDownLatch latch = new CountDownLatch(1);

        if (judge) {
            peng.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Peng.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            peng.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Peng is available");
                    result[0] = true;
                    latch.countDown();
                }
            });
        } else {
            peng.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Peng_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }
        peng.setBounds(745, 570, 45, 57);
        gamePanel.add(peng);

        return result[0];
    }


    public boolean gang_button(boolean judge,int card, int computernumber) {
        JButton gang = new JButton();
        gang.setBorderPainted(false);
        gang.setFocusPainted(false);
        gang.setContentAreaFilled(false);
        //set the button of Gang;

        final boolean[] result = {false};
        CountDownLatch latch = new CountDownLatch(1);

        //judge if the gang is available;
        if (judge) {
            gang.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Gang.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            //gang.setBounds(795, 570, 45, 57);
            gang.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Gang is available");
                    //这里加skip，skip里startRobotPlaySequence();
                    result[0] = true;
                    latch.countDown();
                    player.Gang(card, scaledWidth, scaledHeight, gamePanel, 4, addComputerTile);
                    System.out.print("computer2.playerRiver.size() - 1:" + (computer2.playerRiver.size() - 1));
                    if(computernumber==1){
                        computer1.playerRiver.remove(computer1.playerRiver.size() - 1);
                        robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);
                    }else if(computernumber==2){
                        computer2.playerRiver.remove(computer2.playerRiver.size() - 1);
                        robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);
                    }else{
                        computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                        robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                    }

                    startRobotPlaySequence(4);
                }
            });
            gamePanel.add(gang);
            return true;
            //return true if user choose gang;
        } else {
            gang.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Gang_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            // default mode;
        }

        gang.setBounds(795, 570, 45, 57);
        gamePanel.add(gang);

        return result[0];
    }


    public boolean ting_button(ArrayList<Integer> option) {
        JButton ting = new JButton();
        ting.setBorderPainted(false);
        ting.setFocusPainted(false);
        ting.setContentAreaFilled(false);

        boolean judge = option != null;
        final boolean[] result = {false};
        CountDownLatch latch = new CountDownLatch(1);

        if (judge) {
            ting.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Ting.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            ting.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Ting is available");
                    int index = ting_choice(option);
                    System.out.println("Ting choice index: " + index);
                    result[0] = true;
                    latch.countDown();
                }
            });
        } else {
            ting.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Ting_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }
        ting.setBounds(845, 570, 45, 57);
        gamePanel.add(ting);

        try {
            latch.await(); // 等待按钮点击
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0];
    }

    public int ting_choice(ArrayList<Integer> option) {
        int num = option.size();

        CountDownLatch latch = new CountDownLatch(1);
        int[] index = new int[1];

        //if exist 1 chi type, return 3 directly
        JFrame choice = new JFrame("Choice of Ting Method");
        choice.setSize(480, 210);
        choice.setLocationRelativeTo(null);
        choice.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        choice.setLayout(new BorderLayout());

        JLabel hint = new JLabel("<html><br>Please choose your deck:<br><br>");
        hint.setFont(new Font("Consolas", Font.BOLD, 24));
        hint.setForeground(Color.GRAY);
        hint.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel hintPanel = new JPanel(new BorderLayout());
        hintPanel.add(hint, BorderLayout.SOUTH);
        choice.add(hintPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        mainPanel.setOpaque(false);

        Tilemap tilemap = new Tilemap();

        for (int i = 0; i < num; i++) {
            int tileNum = option.get(i);
            String tilePath = tilemap.getTilePath(tileNum);
            ImageIcon icon = new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(132 / 3, 191 / 3, Image.SCALE_SMOOTH));
            JLabel label = new JLabel(icon);
            label.putClientProperty("index", i); // 设置标签的索引属性

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel clickedLabel = (JLabel) e.getSource();
                    index[0] = (int) clickedLabel.getClientProperty("index");
                    System.out.println("Clicked tile index: " + index[0]);
                    choice.dispose();
                    latch.countDown();
                    //要在玩家手牌里删掉它仍得牌并重新显示一遍手牌
                    player.playerMajiangs.remove(option.get(index[0]));
                    //addComputerTile.addComputerTileToWindow();
                    int next= gameContent.decideNextComputer(option.get(index[0]),computer1,computer2,computer3,player);
                    //决定下一把是谁用decide nextcomputer
                    startRobotPlaySequence(next);
                }
            });

            mainPanel.add(label);
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        choice.add(scrollPane, BorderLayout.CENTER);
        choice.setVisible(true);
        choice.setResizable(false);

        return index[0];
    }


    public boolean hu_button(boolean judge) {
        JButton hu = new JButton();
        hu.setBorderPainted(false);
        hu.setFocusPainted(false);
        hu.setContentAreaFilled(false);

        final boolean[] result = {false};
        CountDownLatch latch = new CountDownLatch(1);

        if (judge) {
            hu.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Hu.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            hu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // create a new frame called score
                    JFrame scoreFrame = new JFrame("Hu Window");
                    scoreFrame.setBounds(550, 300, 400, 300);

                    // suppose the score is 100, you can get the score and show it according to the actual situation
                    JLabel scoreLabel = new JLabel("YOU WIN!");
                    scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

                    scoreFrame.add(scoreLabel);
                    scoreFrame.setVisible(true);

                    result[0] = true;
                    latch.countDown();
                }
            });

        } else {
            hu.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Hu_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }
        hu.setBounds(895, 570, 45, 57);

        gamePanel.add(hu);

        return result[0];
    }

    public int skip_button(boolean ifExist) {
        //JButton skip = new JButton();
        skip.setBorderPainted(false);
        skip.setFocusPainted(false);
        skip.setContentAreaFilled(false);

        int[] result = {50};
        CountDownLatch latch = new CountDownLatch(1);

        if (ifExist) {
            skip.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
            skip.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Skip is available");
                    result[0] = 100;
                    player.gainMajiang();
                    listTiles(player.playerMajiangs, startX, startY);
                    latch.countDown();
                    skip.setVisible(false);
                    chi.setVisible(false);
                }
            });
        } else {
            skip.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo_unable.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
        }
        skip.setBounds(945, 570, 42, 55);

        gamePanel.add(skip);

        return result[0];//if skip,return 100,else, return 50
    }


////////////////////////add or delete tiles/////////////////////////////////////////////////////////////////////////

    int startX = 230; //the position of use's tiles in hand
    int startY = 640;
    int maxTilesPerRow = 12; // 每行最多显示的牌数
    int discardStartX = 418; // 弃牌的起始X位置
    int discardStartY = 420;

    int scaledWidth = 0;
    int scaledHeight = 0;
    public boolean canClick = false;

    public void addTileToWindow(ArrayList<Integer> tileNumber) {  //written by Siying.Li
        listTiles(tileNumber, startX, startY);
        gamePanel.revalidate();
        gamePanel.repaint();
    }


    void hideTiles() {
        for (Component comp : gamePanel.getComponents()) {
            if (comp instanceof JLabel && ((JLabel) comp).getClientProperty("tileNumber") != null) {
                comp.setVisible(false);
                comp.setEnabled(false);
            }
        }
    }

    public void listTiles(ArrayList<Integer> tileNumber, int startX, int startY) {
        if (tileNumber == null) {
            System.out.println("tileNumber is null");
        }

        for (int i = 0; i < gamePanel.getComponentCount(); i++) {
            Component comp = gamePanel.getComponent(i);
            if (comp instanceof JLabel && ((JLabel) comp).getClientProperty("tileNumber") != null) {
                gamePanel.remove(comp);
                i--;
            }
        }

        for (int i = 0; i < tileNumber.size(); i++) {  //get the tileNumber
            int tileNum = tileNumber.get(i);
            String tilePath = tilemap.getTilePath(tileNum);

            if (tilePath != null) {
                ImageIcon TileIcon = new ImageIcon(tilePath);

                scaledWidth = TileIcon.getIconWidth() / 5 * 2;  //reset the size of the tile
                scaledHeight = TileIcon.getIconHeight() / 5 * 2;

                int currentX = startX + i * (scaledWidth);  //reset the site of the tile
                int currentY = startY;
                JLabel tileLabel = createTileLabel(TileIcon, scaledWidth, scaledHeight, currentX, currentY, tileNum,tileNumber);
                gamePanel.add(tileLabel);

            }
        }

        gamePanel.revalidate();
        gamePanel.repaint();

    }

    private JLabel createTileLabel(ImageIcon TileIcon, int scaledWidth, int scaledHeight, int currentX, int currentY, int tileNum,ArrayList<Integer> tileNumber) {
        JLabel tileLabel = new JLabel(new ImageIcon(TileIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)));
        tileLabel.setBounds(currentX, currentY, scaledWidth, scaledHeight);
        tileLabel.putClientProperty("tileNumber", tileNum);

        tileLabel.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (canClick) {
                    canClick = false;  // 禁止再次点击，直到操作完成
                    tileLabel.setEnabled(false);  // 每回合只能弃一张牌

                    JLabel clickedTile = (JLabel) e.getSource();  // 获取被点击的牌
                    ImageIcon tileIcon = (ImageIcon) clickedTile.getIcon();
                    int tileNum = (int) clickedTile.getClientProperty("tileNumber");  // 获取用户点击的牌的编号
                    cardToDiscard = tileNum;

                    // 将牌添加到河中
                    player.playerRiver.add(cardToDiscard);
                    gameContent.addComputerTile.addTileToRiverX(tileIcon, tileNum, discardStartX, discardStartY, maxTilesPerRow, gamePanel,4);

                    // 移除被点击的牌号并重新列出剩余的牌
                    tileNumber.remove(Integer.valueOf(tileNum));
                    Collections.sort(tileNumber);
                    listTiles(tileNumber, startX, startY);
                    int next=gameContent.decideNextComputer(tileNum,computer1,computer2,computer3,player);
                    gameContent.addComputerTile.addTileToRiverX(tileIcon, tileNum, discardStartX, discardStartY, maxTilesPerRow, gamePanel,4);

                    startRobotPlaySequence(next);  // 开始机器人出牌序列

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (canClick) {
                    tileLabel.setLocation(tileLabel.getX(), tileLabel.getY() - 10);
                    gamePanel.revalidate();
                    gamePanel.repaint();
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (canClick) {
                    tileLabel.setLocation(tileLabel.getX(), tileLabel.getY() + 10);
                    gamePanel.revalidate();
                    gamePanel.repaint();
                }
            }
        });

        return tileLabel;
    }

    //////////////////////start the turn////////////////////////
    AddComputerTile addComputerTile = new AddComputerTile();


    public void startRobotPlaySequence(int index) {

        Timer timer = new Timer();
        TimerTask robotPlayTask = new TimerTask() {
            int robotIndex = index;

            @Override
            public void run() {
                if (robotIndex%4!= 0) {
                    System.out.println(robotIndex);

                    // computer1 turn
                    if (robotIndex % 4 == 1) {
                        System.out.println("computer1第一次加牌");
                        System.out.println("computer1size" + computer1.playerMajiangs.size());
                        addComputerTile.addComputer1Tile(computer1.playerMajiangs.size(), gamePanel);


                        // check Hu for everyone in every turn
                        boolean computer1Hu = computer1.isHu(computer1.playerMajiangs,computer1.cardsToDisplay);
                        boolean computer2Hu = computer2.isHu(computer2.playerMajiangs,computer2.cardsToDisplay);
                        boolean computer3Hu = computer3.isHu(computer3.playerMajiangs,computer3.cardsToDisplay);
                        boolean playerHu = player.isHu(player.playerMajiangs, player.cardsToDisplay);

                        if ( computer1Hu) {
                            computer1.isHu = true;
                            System.out.println("1：computer 1 hu");
                            timer.cancel();
                            return;
                        } else if (computer2Hu) {
                            computer2.isHu = true;
                            System.out.println("1：computer 2 hu");
                            timer.cancel();
                            return;
                        } else if (computer3Hu) {
                            computer3.isHu = true;
                            System.out.println("1: computer 3 hu");
                            timer.cancel();
                            return;
                        } else if (playerHu) {
                            player.isHu = true;
                            hu_button(player.isHu);
                            System.out.println("1: player hu");
                            timer.cancel();
                            return;
                        }
                        else if (computer1.isTing()&&!computer1.Tinging) {
                            computer1.isTing = true;
                            System.out.println("ting");
                            System.out.println(computer1.isTing() + "ting1");

                        }
                        System.out.println("进循环2");

                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                int card;
                                if(computer1.Tinging){
                                    card=computer1.discardAfterTing();
                                }else if(computer1.isTing&&!computer1.Tinging){
                                    Random random=new Random();
                                    int index =random.nextInt(computer1.TingThrowTiles.size()-1);
                                    card=computer1.discardMajiang(computer1.playerMajiangs.indexOf(computer1.TingThrowTiles.get(index)));
                                    computer1.Tinging=true;
                                }else{
                                    card = computer1.discardMajiang(computer1.nextCard());
                                }

                                System.out.println(card);

                                addComputerTile.addComputer1Tile(computer1.playerMajiangs.size(), gamePanel);

                                robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);

                                if (computer2.isGang(card)) {
                                    computer2.Gang(card, scaledWidth, scaledHeight, gamePanel, 1, addComputerTile);
                                    robotIndex = robotIndex + 1;
                                    System.out.println("computer1.playerRiver.size() - 1:" + (computer1.playerRiver.size() - 1));

                                    computer1.playerRiver.remove(computer1.playerRiver.size() - 1);
                                    computer2.gainMajiang();

                                    robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);
                                    gamePanel.repaint();
                                } else if (computer3.isGang(card)) {
                                    computer3.Gang(card, scaledWidth, scaledHeight, gamePanel, 3, addComputerTile);
                                    robotIndex = robotIndex + 2;
                                    System.out.println("computer1.playerRiver.size() - 1:" + (computer1.playerRiver.size() - 1));
                                    addComputerTile.removeRiverTile(gamePanel,1);
                                    computer1.playerRiver.remove(computer1.playerRiver.size() - 1);
                                    computer3.gainMajiang();

                                    robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);
                                    gamePanel.repaint();
                                } else if (player.isGang(card)) {
                                    gang_button(true,card,1);
                                    player.Gang(card, scaledWidth, scaledHeight, gamePanel, 4, addComputerTile);
                                    robotIndex = robotIndex + 3;
                                    System.out.println("computer1.playerRiver.size() - 1:" + (computer1.playerRiver.size() - 1));

                                    computer1.playerRiver.remove(computer1.playerRiver.size() - 1);
                                    player.gainMajiang();

                                    robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);

                                } else if (computer2.isPeng(card)) {
                                    computer2.Peng(card, scaledWidth, scaledHeight, gamePanel, 2, addComputerTile);
                                    robotIndex = robotIndex + 1;
                                    System.out.println("computer1.playerRiver.size() - 1:" + (computer1.playerRiver.size() - 1));

                                    computer1.playerRiver.remove(computer1.playerRiver.size() - 1);
                                    robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);
                                } else if (computer3.isPeng(card)) {
                                    computer3.Peng(card, scaledWidth, scaledHeight, gamePanel, 3, addComputerTile);
                                    robotIndex = robotIndex + 2;
                                    System.out.println("computer1.playerRiver.size() - 1:" + (computer1.playerRiver.size() - 1));

                                    computer1.playerRiver.remove(computer1.playerRiver.size() - 1);
                                    robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);
                                } else if (peng_button(player.isPeng(card))) {
                                    player.Peng(card, scaledWidth, scaledHeight, gamePanel, 4, addComputerTile);
                                    robotIndex = robotIndex + 3;
                                    System.out.println("computer1.playerRiver.size() - 1:" + (computer1.playerRiver.size() - 1));
                                    computer1.playerRiver.remove(computer1.playerRiver.size() - 1);
                                    robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);
                                } else if (!computer2.isChi(card).isEmpty()) {
                                    System.out.println("chi21");
                                    computer2.Chi(card,gameWindow.scaledWidth,gameWindow.scaledHeight,gameWindow.gamePanel,2,addComputerTile);
                                    robotIndex = robotIndex + 1;
                                    System.out.println("computer1.playerRiver.size() - 1:" + (computer1.playerRiver.size() - 1));
                                    computer1.playerRiver.remove(computer1.playerRiver.size() - 1);
                                    System.out.println(computer2.cardsToDisplay.size()+"chi21 ctd");
                                    robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);
                                } else {
                                    robotIndex = robotIndex + 1;
                                    computer2.gainMajiang();
                                    System.out.println("next com2");
                                }


                                //重新打一边computer1的river牌
                            }
                        }, 2000); // 延迟3秒
                    }

                    // computer2 turn
                    else if (robotIndex % 4 == 2) {
                        System.out.println("computer2第一次加牌");
                        System.out.println("computer2size" + computer2.playerMajiangs.size());
                        addComputerTile.addComputer2Tile(computer2.playerMajiangs.size(), gamePanel);


                        // check Hu for everyone in every turn
                        boolean computer1Hu = computer1.isHu(computer1.playerMajiangs,computer1.cardsToDisplay);
                        boolean computer2Hu = computer2.isHu(computer2.playerMajiangs,computer2.cardsToDisplay);
                        boolean computer3Hu = computer3.isHu(computer3.playerMajiangs,computer3.cardsToDisplay);
                        boolean playerHu = player.isHu(player.playerMajiangs, player.cardsToDisplay);

                        if ( computer1Hu) {
                            computer1.isHu = true;
                            System.out.println("2：computer 1 hu");
                            timer.cancel();
                            return;
                        } else if (computer2Hu) {
                            computer2.isHu = true;
                            System.out.println("2：computer 2 hu");
                            timer.cancel();
                            return;
                        } else if (computer3Hu) {
                            computer3.isHu = true;
                            System.out.println("2: computer 3 hu");
                            timer.cancel();
                            return;
                        } else if (playerHu) {
                            player.isHu = true;
                            hu_button(player.isHu);
                            System.out.println("2: player hu");
                            timer.cancel();
                            return;
                        }
                        else if (computer2.isTing()&&!computer2.Tinging) {
                            computer2.isTing = true;
                            System.out.println(computer2.isTing() + "ting2");
                        }
                        System.out.println("进循环3");


                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                int card;
                                if(computer2.Tinging){
                                    card=computer2.discardAfterTing();
                                }else if(computer2.isTing&&!computer2.Tinging){
                                    Random random=new Random();
                                    int index =random.nextInt(computer2.TingThrowTiles.size()-1);
                                    card=computer2.discardMajiang(computer2.playerMajiangs.indexOf(computer2.TingThrowTiles.get(index)));
                                    computer2.Tinging=true;
                                }else{
                                    card = computer2.discardMajiang(computer1.nextCard());
                                }
                                addComputerTile.addComputer2Tile(computer2.playerMajiangs.size(), gamePanel);

                                robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);
                                if (computer3.isGang(card)) {
                                    computer3.Gang(card, scaledWidth, scaledHeight, gamePanel, 3, addComputerTile);
                                    System.out.println("gang23");
                                    robotIndex = robotIndex + 1;
                                    System.out.print("computer2.playerRiver.size() - 1:" + (computer2.playerRiver.size() - 1));

                                    computer2.playerRiver.remove(computer2.playerRiver.size() - 1);
                                    computer3.gainMajiang();

                                    robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);

                                } else if (player.isGang(card)) {
                                    gang_button(true,card,2);

                                    timer.cancel();

                                } else if (computer1.isGang(card)) {
                                    computer1.Gang(card, scaledWidth, scaledHeight, gamePanel, 1, addComputerTile);
                                    System.out.println("gang21");
                                    robotIndex = robotIndex + 3;
                                    System.out.print("computer2.playerRiver.size() - 1:" + (computer2.playerRiver.size() - 1));

                                    computer2.playerRiver.remove(computer2.playerRiver.size() - 1);
                                    computer1.gainMajiang();
                                    robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);

                                } else if (computer3.isPeng(card)) {
                                    computer3.Peng(card, scaledWidth, scaledHeight, gamePanel, 3, addComputerTile);
                                    System.out.println("peng23");
                                    robotIndex = robotIndex + 1;
                                    System.out.print("computer2.playerRiver.size() - 1:" + (computer2.playerRiver.size() - 1));

                                    computer2.playerRiver.remove(computer2.playerRiver.size() - 1);
                                    robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);

                                } else if (peng_button(player.isPeng(card))) {
                                    player.Peng(card, scaledWidth, scaledHeight, gamePanel, 4, addComputerTile);
                                    robotIndex = robotIndex + 2;
                                    System.out.print("computer2.playerRiver.size() - 1:" + (computer2.playerRiver.size() - 1));

                                    computer2.playerRiver.remove(computer2.playerRiver.size() - 1);
                                    robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);

                                } else if (computer1.isPeng(card)) {
                                    computer1.Peng(card, scaledWidth, scaledHeight, gamePanel, 1, addComputerTile);
                                    System.out.println("peng21");
                                    robotIndex = robotIndex + 3;
                                    System.out.print("computer2.playerRiver.size() - 1:" + (computer2.playerRiver.size() - 1));

                                    computer2.playerRiver.remove(computer2.playerRiver.size() - 1);
                                    robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);

                                } else if (!computer3.isChi(card).isEmpty()) {
                                    computer3.Chi(card,gameWindow.scaledWidth,gameWindow.scaledHeight,gameWindow.gamePanel,3,addComputerTile);
                                    System.out.println("chi23");
                                    robotIndex = robotIndex + 1;
                                    System.out.print("computer2.playerRiver.size() - 1:" + (computer2.playerRiver.size() - 1));

                                    computer2.playerRiver.remove(computer2.playerRiver.size() - 1);
                                    System.out.println(computer3.cardsToDisplay.size()+"chi23 ctd");
                                    robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);

                                } else {
                                    robotIndex = robotIndex + 1;
                                    computer3.gainMajiang();
                                    System.out.println("next com3");
                                }
                                //重新打一边computer2的river牌

                            }
                        }, 3000); // 延迟3秒
                    }

                    // computer3 turn
                    else if (robotIndex % 4 == 3) {
                        System.out.println("computer3 第一次加牌");
                        System.out.println("computer3size" + computer3.playerMajiangs.size());
                        addComputerTile.addComputer3Tile(computer3.playerMajiangs.size(), gamePanel);


                        // check Hu for everyone in every turn
                        boolean computer1Hu = computer1.isHu(computer1.playerMajiangs,computer1.cardsToDisplay);
                        boolean computer2Hu = computer2.isHu(computer2.playerMajiangs,computer2.cardsToDisplay);
                        boolean computer3Hu = computer3.isHu(computer3.playerMajiangs,computer3.cardsToDisplay);
                        boolean playerHu = player.isHu(player.playerMajiangs, player.cardsToDisplay);

                        if ( computer1Hu) {
                            computer1.isHu = true;
                            System.out.println("3：computer 1 hu");
                            timer.cancel();
                        } else if (computer2Hu) {
                            computer2.isHu = true;
                            System.out.println("3：computer 2 hu");
                            timer.cancel();
                        } else if (computer3Hu) {
                            computer3.isHu = true;
                            System.out.println("3: computer 3 hu");
                            timer.cancel();
                            return;
                        } else if (playerHu) {
                            player.isHu = true;
                            hu_button(player.isHu);
                            System.out.println("3: player hu");
                            timer.cancel();
                            return;
                        }
                        else if (computer3.Tinging) {
                            computer3.discardAfterTing();
                            addComputerTile.addComputer3Tile(computer3.playerMajiangs.size(), gamePanel);
                            robotIndex = robotIndex + 1;

                        } else if (computer3.isTing()&&!computer3.Tinging) {
                            computer3.isTing = true;
                            System.out.println(computer3.isTing() + "ting3");
                        }
                        System.out.println("进循环4");

                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                int card;
                                if(computer3.Tinging){
                                    card=computer3.discardAfterTing();
                                }else if(computer3.isTing&&!computer3.Tinging){
                                    Random random=new Random();
                                    int index =random.nextInt(computer3.TingThrowTiles.size()-1);
                                    card=computer3.discardMajiang(computer3.playerMajiangs.indexOf(computer3.TingThrowTiles.get(index)));
                                    computer3.Tinging=true;
                                }else{
                                    card = computer3.discardMajiang(computer3.nextCard());
                                }
                                //扔牌以后重新打玩家手牌
                                addComputerTile.addComputer3Tile(computer3.playerMajiangs.size(), gamePanel);

                                robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                                if (player.isGang(card)) {
                                    gang_button(true,card,3);
                                    System.out.println("playergang");
                                    player.Gang(card, scaledWidth, scaledHeight, gamePanel, 4, addComputerTile);
                                    robotIndex = robotIndex + 1;
                                    System.out.print("computer3.playerRiver.size() - 1:" + (computer3.playerRiver.size() - 1));

                                    computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                                    player.gainMajiang();

                                    robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);

                                } else if (computer1.isGang(card)) {
                                    computer1.Gang(card, scaledWidth, scaledHeight, gamePanel, 1, addComputerTile);
                                    System.out.println("gang31");
                                    robotIndex = robotIndex + 2;
                                    System.out.print("computer3.playerRiver.size() - 1:" + (computer3.playerRiver.size() - 1));

                                    computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                                    computer1.gainMajiang();

                                    robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);

                                } else if (computer2.isGang(card)) {
                                    computer2.Gang(card, scaledWidth, scaledHeight, gamePanel, 2, addComputerTile);
                                    System.out.println("gang32");
                                    robotIndex = robotIndex + 3;
                                    System.out.print("computer3.playerRiver.size() - 1:" + (computer3.playerRiver.size() - 1));

                                    computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                                    computer2.gainMajiang();

                                    robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);

                                } else if (peng_button(player.isPeng(card))) {
                                    System.out.println("playerpeng");
                                    player.Peng(card, scaledWidth, scaledHeight, gamePanel, 4, addComputerTile);
                                    robotIndex = robotIndex + 1;
                                    System.out.print("computer3.playerRiver.size() - 1:" + (computer3.playerRiver.size() - 1));

                                    computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                                    robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);

                                } else if (computer1.isPeng(card)) {
                                    computer1.Peng(card, scaledWidth, scaledHeight, gamePanel, 1, addComputerTile);
                                    System.out.println("peng31");
                                    robotIndex = robotIndex + 2;
                                    System.out.print("computer3.playerRiver.size() - 1:" + (computer3.playerRiver.size() - 1));

                                    computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                                    robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);

                                } else if (computer2.isPeng(card)) {
                                    computer2.Peng(card, scaledWidth, scaledHeight, gamePanel, 2, addComputerTile);
                                    System.out.println("peng32");
                                    robotIndex = robotIndex + 3;
                                    System.out.print("computer3.playerRiver.size() - 1:" + (computer3.playerRiver.size() - 1));

                                    computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                                    robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);

                                } else if (!player.isChi(card).isEmpty()) {
                                    System.out.println("玩家能吃但是没点");
                                    chi_button(player, player.isChi(card),card);
                                    for (int i : new ArrayList<Integer>()) {
                                        System.out.print(i + " display");
                                    }
                                    for (int i : player.cardsToDisplay) {
                                        System.out.print(i + " card to display");
                                    }
                                    listTiles(player.playerMajiangs, startX, startY);
                                    robotIndex = robotIndex + 1;
                                } else {
                                    System.out.println("循环4结束");
                                    robotIndex = robotIndex + 1;
                                    player.gainMajiang();
                                    System.out.println("结束后index" + robotIndex);
                                }
                                //重新打一边computer3的river牌

                            }
                        }, 2000); // 延迟3秒
                    }
                }

                // player's turn
                else {
                    System.out.println("lundaowol");
                    System.out.println("playersize"+player.playerMajiangs.size());

                    // check Hu for everyone in every turn
                    boolean computer1Hu = computer1.isHu(computer1.playerMajiangs,computer1.cardsToDisplay);
                    boolean computer2Hu = computer2.isHu(computer2.playerMajiangs,computer2.cardsToDisplay);
                    boolean computer3Hu = computer3.isHu(computer3.playerMajiangs,computer3.cardsToDisplay);
                    boolean playerHu = player.isHu(player.playerMajiangs, player.cardsToDisplay);

                    if ( computer1Hu) {
                        computer1.isHu = true;
                        System.out.println("computer 1 hu");
                        timer.cancel();
                    } else if (computer2Hu) {
                        computer2.isHu = true;
                        System.out.println("computer 2 hu");
                        timer.cancel();
                    } else if (computer3Hu) {
                        computer3.isHu = true;
                        System.out.println("computer 3 hu");
                        timer.cancel();
                    } else if (playerHu) {
                        player.isHu = true;
                        hu_button(player.isHu);
                        System.out.println("player hu");
                        timer.cancel();
                    }
                    else if (player.Tinging) {
                        player.discardAfterTing();
                        robotIndex = robotIndex + 1;
                    } else if (player.isTing()) {
                        System.out.println("判断玩家能ting了，按钮应该能亮");
                        if(ting_button(player.TingThrowTiles)){
                            ting_choice(player.TingThrowTiles);
                            System.out.println("玩家点了听了");
                            player.Tinging=true;
                        }
                    } else {
                        canClick = true;
                        listTiles(player.playerMajiangs, startX, startY);
                        timer.cancel();
                    }

                }
            }
        };
        timer.schedule(robotPlayTask,3000,4000);  // 每1秒执行一次任务
    }

    // 机器人打牌的方法
    public void robotPlayTile(ArrayList<Integer> computerNumber, int x, int y, Boolean isX, int computerName,int num) {

        String tilePath = tilemap.getTilePath(num);
        if (tilePath != null) {
            if(isX){
                if (computerName == 2) {
                    gameContent.addComputerTile.addTileToRiverX(new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), num, x, y, 10, gamePanel, 2);
                } else {
                    gameContent.addComputerTile.addTileToRiverX(new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), num, x, y, 10, gamePanel, 4);
                }
            }else {
                if (computerName == 1) {
                    gameContent.addComputerTile.addTileToRiverY(new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), num, x, y, 10, gamePanel, 1);
                } else {
                    gameContent.addComputerTile.addTileToRiverY(new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), num, x, y, 10, gamePanel, 3);

                }
            }
            gamePanel.revalidate();
            gamePanel.repaint();
        }
    }
    ///////////////////////////profile photo///////////////////////////////
    private void headShot_M(String imagePath) {  //written by Jinyan.Shen
        ImageIcon com;
        Image scaledCom;
        ImageIcon scaledComIcon;
        JLabel headLabel;
        //create the image object;
        com = new ImageIcon(imagePath);
        scaledCom = com.getImage().getScaledInstance(com.getIconWidth() / 9, com.getIconHeight() / 9, Image.SCALE_DEFAULT);
        scaledComIcon = new ImageIcon(scaledCom);
        headLabel = new JLabel(scaledComIcon);
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setVerticalAlignment(SwingConstants.CENTER);
        // adjust the image;

        // Create a JPanel to hold the image and "Player" label
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(headLabel, BorderLayout.CENTER);

        // Create a JLabel to display "player"
        Random random = new Random();  //written by Siying.Li
        String[] playerNames = {"Sing", "Eszter", "Antheia", "Victoria", "Sean", "Henry", "Barbie", "Tom", "Jerry", "Mickey", "Minnie", "Anna", "Elsa", "Max", "Hari", "Allen", "Antoni", "Jack", "Rose"};

        int start = 0;
        int end = playerNames.length;

        JLabel[] playerLabels = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            int index = start + random.nextInt(end - start);
            String selectedName = playerNames[index];
            playerLabels[i] = new JLabel(selectedName);
            playerLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            imagePanel.add(playerLabels[i], BorderLayout.SOUTH);

            String[] tempArray = new String[end - start - 1];
            System.arraycopy(playerNames, start, tempArray, 0, index - start);
            System.arraycopy(playerNames, index + 1, tempArray, index - start, end - start - index - 1);
            playerNames = tempArray;

            end--;
        }


        switch (imagePath) {
            case "src/profilephoto/bear.png":
                // set the "North" headshot;

                int labelWidth1 = scaledComIcon.getIconWidth();
                int labelHeight1 = scaledComIcon.getIconHeight();
                int panelWidth1 = gamePanel.getWidth();
                int panelHeight1 = gamePanel.getHeight();
                int x_1 = ((panelWidth1 - labelWidth1) / 64) * 49+200;
                int y_1 = ((panelHeight1 - labelHeight1) / 64) * 40-70;
                headLabel.setBounds(x_1, y_1, labelWidth1, labelHeight1);
                imagePanel.setBounds(x_1, y_1, labelWidth1, labelHeight1 + 20);
                // Increase height to accommodate "Player" label

                gamePanel.add(headLabel);
                gamePanel.add(imagePanel);
                break;

            case "src/profilephoto/cat.png":
                //set the "South" headshot;

                int labelWidth2 = scaledComIcon.getIconWidth();
                int labelHeight2 = scaledComIcon.getIconHeight();
                int panelWidth2 = gamePanel.getWidth();
                int panelHeight2 = gamePanel.getHeight();
                int x_2 = ((panelWidth2 - labelWidth2) / 64) * 18-200;
                int y_2 = ((panelHeight2 - labelHeight2) / 64) * 15+80;
                headLabel.setBounds(x_2, y_2, labelWidth2, labelHeight2);
                imagePanel.setBounds(x_2, y_2, labelWidth2, labelHeight2 + 20); // Increase height to accommodate "Player" label
                gamePanel.add(headLabel);
                gamePanel.add(imagePanel);
                break;

            case "src/profilephoto/cow.png":
                //set the "West" headshot;

                int labelWidth3 = scaledComIcon.getIconWidth();
                int labelHeight3 = scaledComIcon.getIconHeight();
                int panelWidth3 = gamePanel.getWidth();
                int panelHeight3 = gamePanel.getHeight();
                int x_3 = ((panelWidth3 - labelWidth3) / 64) * 43-50;
                int y_3 = ((panelHeight3 - labelHeight3) / 64) * 12-80;
                headLabel.setBounds(x_3, y_3, labelWidth3, labelHeight3);
                imagePanel.setBounds(x_3, y_3, labelWidth3, labelHeight3 + 20); // Increase height to accommodate "Player" label
                gamePanel.add(headLabel);
                gamePanel.add(imagePanel);
                break;

        }
    }

    public void addOtherAvatar(String clientId, String avatar){
        ImageIcon com;
        Image scaledCom;
        ImageIcon scaledComIcon;
        JLabel headLabel;
        //create the image object;
        com = new ImageIcon(avatar);
        scaledCom = com.getImage().getScaledInstance(com.getIconWidth() / 9, com.getIconHeight() / 9, Image.SCALE_DEFAULT);
        scaledComIcon = new ImageIcon(scaledCom);
        headLabel = new JLabel(scaledComIcon);
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setVerticalAlignment(SwingConstants.CENTER);
        // adjust the image;

        // Create a JPanel to hold the image and "Player" label
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(headLabel, BorderLayout.CENTER);
        switch (clientId) {
            case "1":
                int labelWidth1 = scaledComIcon.getIconWidth();
                int labelHeight1 = scaledComIcon.getIconHeight();
                int panelWidth1 = gamePanel.getWidth();
                int panelHeight1 = gamePanel.getHeight();
                int x_1 = ((panelWidth1 - labelWidth1) / 64) * 49+150;
                int y_1 = ((panelHeight1 - labelHeight1) / 64) * 40-80;
                headLabel.setBounds(x_1, y_1, labelWidth1, labelHeight1);
                imagePanel.setBounds(x_1, y_1, labelWidth1, labelHeight1 + 20);
                // Increase height to accommodate "Player" label

                gamePanel.add(headLabel);
                gamePanel.add(imagePanel);
                break;

            case "2":
                //set the "South" headshot;

                int labelWidth2 = scaledComIcon.getIconWidth();
                int labelHeight2 = scaledComIcon.getIconHeight();
                int panelWidth2 = gamePanel.getWidth();
                int panelHeight2 = gamePanel.getHeight();
                int x_2 = ((panelWidth2 - labelWidth2) / 64) * 18-80;
                int y_2 = ((panelHeight2 - labelHeight2) / 64) * 15+80;
                headLabel.setBounds(x_2, y_2, labelWidth2, labelHeight2);
                imagePanel.setBounds(x_2, y_2, labelWidth2, labelHeight2 + 20); // Increase height to accommodate "Player" label
                gamePanel.add(headLabel);
                gamePanel.add(imagePanel);
                break;

            case "3":
                //set the "West" headshot;

                int labelWidth3 = scaledComIcon.getIconWidth();
                int labelHeight3 = scaledComIcon.getIconHeight();
                int panelWidth3 = gamePanel.getWidth();
                int panelHeight3 = gamePanel.getHeight();
                int x_3 = ((panelWidth3 - labelWidth3) / 64) * 43-50;
                int y_3 = ((panelHeight3 - labelHeight3) / 64) * 12-80;
                headLabel.setBounds(x_3, y_3, labelWidth3, labelHeight3);
                imagePanel.setBounds(x_3, y_3, labelWidth3, labelHeight3 + 20); // Increase height to accommodate "Player" label
                gamePanel.add(headLabel);
                gamePanel.add(imagePanel);
                break;

        }
    }

    ///////////////////user profile photo///////////////////////////////
    private void chooseAvatar() {  //written by Siying.Li
        JDialog avatarDialog = new JDialog(this, "Player Settings", true);
        avatarDialog.setSize(600, 400);  //set choose window
        avatarDialog.setLocationRelativeTo(this);
        avatarDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel avatarPanel = new JPanel();
        avatarPanel.setLayout(null);

        JLabel tips = new JLabel("<html>Please enter your name and choose your headshot ovo<br>Just enter your name(no 'ok' button)<br>and click the headshot button directly!</html>");
        tips.setFont(new Font("Consolas", Font.BOLD, 16));
        tips.setForeground(Color.GRAY);
        tips.setBounds(50, 20, 500, 60);
        tips.setHorizontalAlignment(SwingConstants.CENTER);
        avatarPanel.add(tips);

        JLabel nameLabel = new JLabel("<html>Please enter your name: <br>(6 character maximum)</html>");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setBounds(50, 90, 500, 60);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        avatarPanel.add(nameLabel);

        nameField = new JTextField(8);
        nameField.setBounds(230, 155, 150, 30);
        avatarPanel.add(nameField);

        JLabel chooseLabel = new JLabel("Please choose your headshot: ");
        chooseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chooseLabel.setFont(new Font("Arial", Font.BOLD, 20));
        chooseLabel.setBounds(50, 200, 500, 30);
        avatarPanel.add(chooseLabel);

        ImageIcon Icon = new ImageIcon("src/profilephoto/crocodile.png");

        JButton crocodile = new JButton();
        crocodile.setBorderPainted(false); //set the button frame invisible;
        crocodile.setFocusPainted(false);
        crocodile.setContentAreaFilled(false);
        crocodile.setIcon(new ImageIcon(new ImageIcon("src/profilephoto/crocodile.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        crocodile.setBounds(65, 240, 60, 60);
        avatarPanel.add(crocodile);

        JButton fox = new JButton();
        fox.setBorderPainted(false);
        fox.setFocusPainted(false);
        fox.setContentAreaFilled(false);
        fox.setIcon(new ImageIcon(new ImageIcon("src/profilephoto/fox.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        fox.setBounds(165, 240, 60, 60);
        avatarPanel.add(fox);

        JButton hamster = new JButton();
        hamster.setBorderPainted(false);
        hamster.setFocusPainted(false);
        hamster.setContentAreaFilled(false);
        hamster.setIcon(new ImageIcon(new ImageIcon("src/profilephoto/hamster.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        hamster.setBounds(265, 240, 60, 60);
        avatarPanel.add(hamster);

        JButton hedgehog = new JButton();
        hedgehog.setBorderPainted(false);
        hedgehog.setFocusPainted(false);
        hedgehog.setContentAreaFilled(false);
        hedgehog.setIcon(new ImageIcon(new ImageIcon("src/profilephoto/hedgehog.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        hedgehog.setBounds(365, 240, 60, 60);
        avatarPanel.add(hedgehog);

        JButton rabbit = new JButton();
        rabbit.setBorderPainted(false);
        rabbit.setFocusPainted(false);
        rabbit.setContentAreaFilled(false);
        rabbit.setIcon(new ImageIcon(new ImageIcon("src/profilephoto/rabbit.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        rabbit.setBounds(465, 240, 60, 60);
        avatarPanel.add(rabbit);

        crocodile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image scaledArrow;
                updateAvatar("src/profilephoto/crocodile.png");
                avatarDialog.dispose();
            }
        });

        fox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image scaledArrow;
                updateAvatar("src/profilephoto/fox.png");
                avatarDialog.dispose();
            }
        });

        hamster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image scaledArrow;
                updateAvatar("src/profilephoto/hamster.png");
                avatarDialog.dispose();
            }
        });

        hedgehog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image scaledArrow;
                updateAvatar("src/profilephoto/hedgehog.png");
                avatarDialog.dispose();
            }
        });

        rabbit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image scaledArrow;
                updateAvatar("src/profilephoto/rabbit.png");
                avatarDialog.dispose();
            }
        });

        avatarDialog.add(avatarPanel);
        avatarDialog.setVisible(true);
    }

    public void updateAvatar(String imagePath) {
        /////for headshot/////
        ImageIcon avatarIcon = new ImageIcon(imagePath);
        Image originalImage = avatarIcon.getImage();

        int scaledWidth = originalImage.getWidth(null) / 9;
        int scaledHeight = originalImage.getHeight(null) / 9;

        Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel avatarLabel = new JLabel(scaledIcon);

        avatarLabel.setBounds(270, 540, scaledWidth, scaledHeight);

        gamePanel.add(avatarLabel);

        /////for input player's name/////
        String inputName = nameField.getText();
        JLabel nameLabel = new JLabel(inputName);

        JPanel avatarPanel = new JPanel();
        avatarPanel.setLayout(new BorderLayout());
        avatarPanel.add(avatarLabel, BorderLayout.CENTER);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        avatarPanel.add(nameLabel, BorderLayout.SOUTH);
        avatarPanel.setBounds(270, 560, scaledWidth, scaledHeight + 17); // Increase height to accommodate "Player" label
        gamePanel.add(avatarPanel);

        //////////////////////////////////////////
        gamePanel.revalidate();
        gamePanel.repaint();
    }
}

