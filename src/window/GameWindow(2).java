package window;


import GameTable.ShuffleMajiang;
import HuHelper.Hu;
import Player.*;
import tiles.Tilemap;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;


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


    //    static {
    GameContent gameContent = new GameContent();

    //    }
    //public void setTileNumber(ArrayList<Integer> tileNumber) {
/*        this.tileNumber = tileNumber;
    }*/

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

    // set the number of dices;

    public GameWindow() {
        window_frame();
//        GameContent.helpB
        gameContent.helpButtons(gamePanel);
        gameContent.dice_button(sum, gamePanel);

        this.setVisible(true);
        chooseAvatar();


        headShot_M("src/profilephoto/bear.png");
        headShot_M("src/profilephoto/cat.png");
        headShot_M("src/profilephoto/cow.png");
        // Add the headShots of 3 machine players;

        //tileNumber = new ArrayList<>();
    }

    private void window_frame() {  //written by Siying.Li
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
    public void setbuttons(Player player, ArrayList<Integer> chiSet, boolean pengJudge, boolean gangJudge, ArrayList<Integer> tingSet, boolean huJudge, boolean iftrue) {
        chi_button(player,chiSet);
        peng_button(pengJudge);
        gang_button(gangJudge);
        ting_button(tingSet);
        hu_button(huJudge);

        boolean ifExist = chi_button(player, chiSet) || peng_button(pengJudge) || gang_button(gangJudge) || ting_button(tingSet) || hu_button(huJudge);

        skip_button(ifExist);
    }



    public boolean chi_button(Player player, ArrayList<Integer> set) {  //written by Jinyan.Shen
        JButton chi = new JButton();
        chi.setBorderPainted(false);
        chi.setFocusPainted(false);
        chi.setContentAreaFilled(false);

        boolean judge = set != null;
        final boolean[] result = {false};
        CountDownLatch latch = new CountDownLatch(1);
        if (skipClicked) {
            return false; // If skip was clicked, return false
        }

        if (judge) {
            chi.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Chi.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            chi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Display available");
                    chi_choice(player, set);
                    result[0] = true;
                    latch.countDown();
                }
            });
        } else {
            chi.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Chi_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }
        chi.setBounds(695, 570, 45, 57);

        gamePanel.add(chi);

        return result[0];
    }


    private int chi_choice(Player player, ArrayList<Integer> option) {  //written by Jinyan.Shen
        int num = option.size();

        CountDownLatch latch = new CountDownLatch(1);
        int[] index = new int[1];

        if (num == 3) {
            for(int i:player.cardsToDisplay){
                System.out.println(i);
            }
            return 3;
        }
        //if exist 1 chi type, return 3 directly;

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
                int tileNum = option.get(i + j);
                String tilePath = tilemap.getTilePath(tileNum);
                ImageIcon icon = new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(132 / 3, 191 / 3, Image.SCALE_SMOOTH));
                JLabel label = new JLabel(icon);
                panel.add(label);
            }

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Window Available");
                    JPanel clickedPanel = (JPanel) e.getSource();
                    index[0] = (int) clickedPanel.getClientProperty("index");
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

        return 2 - index[0] / 3;
    }


    public boolean peng_button(boolean judge) {  //written by Jinyan.Shen
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


    public boolean gang_button(boolean judge) {  //Jinyan.Shen
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
                    result[0] = true;
                    latch.countDown();
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

    private boolean isAvailable() {
        return true;
    }

    public boolean ting_button(ArrayList<Integer> option) { //written by Jinyan.Shen
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

    public int ting_choice(ArrayList<Integer> option) {  //written by Jinyan.Shen
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


    public boolean hu_button(boolean judge) {  //written by Jinyan.Shen
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

    public boolean skip_button(boolean ifExist) { //written by Jinyan.Shen
        JButton skip = new JButton();
        skip.setBorderPainted(false);
        skip.setFocusPainted(false);
        skip.setContentAreaFilled(false);

        final boolean[] result = {false};
        CountDownLatch latch = new CountDownLatch(1);

        if (ifExist) {
            skip.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
            skip.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Skip is available");
                    result[0] = true;
                    latch.countDown();
                }
            });
        } else {
            skip.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo_unable.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
        }
        skip.setBounds(945, 570, 42, 55);

        gamePanel.add(skip);

        return result[0];
    }

    private void disableOtherButtons() { //written by Jinyan.Shen
        // Logic to disable other buttons
        for (Component comp : gamePanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setEnabled(false);
            }
        }
    }

////////////////////////add or delete tiles/////////////////////////////////////////////////////////////////////////

    int startX = 230; //the position of use's tiles in hand
    int startY = 640;
    int maxTilesPerRow = 12; // 每行最多显示的牌数
    int discardStartX = 418; // 弃牌的起始X位置
    int discardStartY = 420;

    int scaledWidth = 0;
    int scaledHeight = 0;
    public boolean canClick = true;

    public void addTileToWindow(ArrayList<Integer> tileNumber) {  //written by Siying.Li
        listTiles(tileNumber, startX, startY);
        gamePanel.revalidate();
        gamePanel.repaint();
    }


    void hideTiles() {  //written by Siying.Li
        for (Component comp : gamePanel.getComponents()) {
            if (comp instanceof JLabel && ((JLabel) comp).getClientProperty("tileNumber") != null) {
                comp.setVisible(false);
                comp.setEnabled(false);
            }
        }
    }

    public void listTiles(ArrayList<Integer> tileNumber, int startX, int startY) {  //written by Siying.Li
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

    //written by Siying.Li
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
                    gameContent.addComputerTile.addTileToRiverX(tileIcon, tileNum, discardStartX, discardStartY, maxTilesPerRow, gamePanel,4);

                    // 移除被点击的牌号并重新列出剩余的牌
                    tileNumber.remove(Integer.valueOf(tileNum));
                    Collections.sort(tileNumber);
                    listTiles(tileNumber, startX, startY);
                    int next=gameContent.decideNextComputer(tileNum,computer1,computer2,computer3,player);
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

    public void resetTurn() {  //written by Siying.Li
        canClick = true;
        for (Component comp : gamePanel.getComponents()) {
            if (comp instanceof JLabel && ((JLabel) comp).getClientProperty("tileNumber") != null) {
                comp.setEnabled(true);
            }
        }
    }
    //////////////////////start the turn//////////////////////////////
    AddComputerTile addComputerTile = new AddComputerTile();

    public void startRobotPlaySequence(int index) {  //written by Ran.An

        Timer timer = new Timer();
        TimerTask robotPlayTask = new TimerTask() {
            int robotIndex = index;

            @Override
            public void run() {
                if (robotIndex%4!= 0) {
                    System.out.println(robotIndex);
                    if (robotIndex %4== 1) {
                        computer1.gainMajiang();
                        System.out.println("computer1size"+computer1.playerMajiangs.size());
                        addComputerTile.addComputer1Tile(computer1.playerMajiangs.size(), gamePanel);
                        if (computer1.isHu) {
                            computer1.isHu = true;
                            System.out.println("hu");
                        } else if (computer1.Tinging) {
                            computer1.discardAfterTing();
                        } else if (computer1.isTing()) {
                            computer1.isTing = true;
                            System.out.println("ting");
                        } else {
                            System.out.println("进循环2");
                            int card = computer1.discardMajiang(computer1.nextCard());
                            System.out.println(card);

                            //boolean isSkip = chi_button(player, player.isChi(card)) || peng_button(player.isPeng(card)) || gang_button(player.isGang(card)) || ting_button(player.TingTiles) || hu_button(player.isHu);
                            System.out.println("computer1:"+computer1.getPlayerMajiangs());
                            addComputerTile.addComputer1Tile(computer1.playerMajiangs.size(), gamePanel);
                            robotPlayTile(computer1.getPlayerMajiangs(), 700, 210+260, Boolean.FALSE, 1, card);
                            computer1.playerRiver.add(card);
                            if(computer2.isGang(card)) {
                                computer2.Gang(card);
                                robotIndex=robotIndex+1;
                                computer1.playerRiver.remove(computer1.playerRiver.size()-1);
                            }else if(computer3.isGang(card)){
                                computer3.Gang(card);
                                robotIndex=robotIndex+2;
                                computer1.playerRiver.remove(computer1.playerRiver.size()-1);
                            }else if(gang_button(player.isGang(card))){
                                player.Gang(card);
                                robotIndex=robotIndex+3;
                                computer1.playerRiver.remove(computer1.playerRiver.size()-1);
                            }else if (computer2.isPeng(card)){
                                computer2.Peng(card);
                                robotIndex=robotIndex+1;
                                computer1.playerRiver.remove(computer1.playerRiver.size()-1);
                            }else if (computer3.isPeng(card)){
                                computer3.Peng(card);
                                robotIndex=robotIndex+2;
                                computer1.playerRiver.remove(computer1.playerRiver.size()-1);
                            }else if (peng_button(player.isPeng(card))){
                                player.Peng(card);
                                robotIndex=robotIndex+3;
                                computer1.playerRiver.remove(computer1.playerRiver.size()-1);
                            }else if(!computer2.isChi(card).isEmpty()){
                                computer2.Chi(card);
                                robotIndex=robotIndex+1;
                                computer1.playerRiver.remove(computer1.playerRiver.size()-1);
                            }else{
                                robotIndex=robotIndex+1;
                            }
                        }

                    } else if (robotIndex%4 == 2) {
                        computer2.gainMajiang();
                        System.out.println("computer2size"+computer1.playerMajiangs.size());
                        addComputerTile.addComputer2Tile(computer2.playerMajiangs.size(), gamePanel);
                        if (computer2.isHu) {
                            computer2.isHu = true;
                        } else if (computer2.Tinging) {
                            computer2.discardAfterTing();
                        } else if (computer1.isTing()) {
                            computer2.isTing = true;
                        } else {
                            System.out.println("进循环3");
                            int card = computer2.discardMajiang(computer2.nextCard());
                            addComputerTile.addComputer2Tile(computer2.playerMajiangs.size(), gamePanel);
                            computer2.playerRiver.add(card);
                            robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);
                            if(computer3.isGang(card)) {
                                computer3.Gang(card);
                                System.out.println("gang23");
                                robotIndex=robotIndex+1;
                                computer2.playerRiver.remove(computer2.playerRiver.size()-1);
                            }else if(gang_button(player.isGang(card))){
                                player.Gang(card);
                                robotIndex=robotIndex+2;
                                computer2.playerRiver.remove(computer2.playerRiver.size()-1);
                            }else if(computer1.isGang(card)){
                                computer1.Gang(card);
                                System.out.println("gang21");
                                robotIndex=robotIndex+3;
                                computer2.playerRiver.remove(computer2.playerRiver.size()-1);
                            }else if (computer3.isPeng(card)){
                                computer3.Peng(card);
                                System.out.println("peng23");
                                robotIndex=robotIndex+1;
                                computer2.playerRiver.remove(computer2.playerRiver.size()-1);
                            }else if (peng_button(player.isPeng(card))){
                                player.Peng(card);
                                robotIndex=robotIndex+2;
                                computer2.playerRiver.remove(computer2.playerRiver.size()-1);
                            }else if (computer1.isPeng(card)){
                                computer1.Peng(card);
                                System.out.println("peng21");
                                robotIndex=robotIndex+3;
                                computer2.playerRiver.remove(computer2.playerRiver.size()-1);
                            }else if(!computer3.isChi(card).isEmpty()){
                                computer3.Chi(card);
                                System.out.println("chi23");
                                robotIndex=robotIndex+1;
                                computer2.playerRiver.remove(computer2.playerRiver.size()-1);
                            }else{
                                robotIndex=robotIndex+1;
                                System.out.println("xiayige");
                            }
                        }


                    } else if (robotIndex%4 == 3) {
                        computer3.gainMajiang();
                        System.out.println("computer3size"+computer1.playerMajiangs.size());
                        addComputerTile.addComputer3Tile(computer3.playerMajiangs.size(), gamePanel);
                        if (computer3.isHu) {
                            computer3.isHu = true;
                            System.out.println(computer3.isHu + "hu");
                        } else if (computer3.Tinging) {
                            computer3.discardAfterTing();
                            addComputerTile.addComputer1Tile(computer1.playerMajiangs.size(), gamePanel);
                        } else if (computer3.isTing()) {
                            computer3.isTing = true;
                            System.out.println(computer3.isTing() + "ting");
                        } else {
                            System.out.println("进循环4");
                            int card = computer3.discardMajiang(computer3.nextCard());
                            addComputerTile.addComputer3Tile(computer3.playerMajiangs.size(), gamePanel);
                            computer3.playerRiver.add(card);
                            robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                            if (gang_button(player.isGang(card))) {
                                System.out.println("playergang");
                                player.Gang(card);
                                robotIndex = robotIndex + 1;
                                computer3.playerRiver.remove(computer3.playerRiver.size()-1);
                            } else if (computer1.isGang(card)) {
                                computer1.Gang(card);
                                System.out.println("gang31");
                                robotIndex = robotIndex + 2;
                                computer3.playerRiver.remove(computer3.playerRiver.size()-1);
                            } else if (computer2.isGang(card)) {
                                computer2.Gang(card);
                                System.out.println("gang32");
                                robotIndex = robotIndex + 3;
                                computer3.playerRiver.remove(computer3.playerRiver.size()-1);
                            } else if (peng_button(player.isPeng(card))) {
                                System.out.println("playerpeng");
                                player.Peng(card);
                                robotIndex = robotIndex + 1;
                                computer3.playerRiver.remove(computer3.playerRiver.size()-1);
                            } else if (computer1.isPeng(card)) {
                                computer1.Peng(card);
                                System.out.println("peng21");
                                robotIndex = robotIndex + 2;
                                computer3.playerRiver.remove(computer3.playerRiver.size()-1);
                            } else if (computer2.isPeng(card)) {
                                computer2.Peng(card);
                                System.out.println("peng32");
                                robotIndex = robotIndex + 3;
                                computer3.playerRiver.remove(computer3.playerRiver.size()-1);
                            } else if (!player.isChi(card).isEmpty() ) {
                                System.out.println("playerchi");
                                chi_button(player, player.isChi(card));
                                player.Chi(player.isChi(card), chi_choice(player, player.isChi(card)), card);
                                for (int i : player.cardsToDisplay) {
                                    System.out.print(i + "card to display");
                                }
                                //show(player.cardsToDisplay);
                                computer3.playerRiver.remove(computer3.playerRiver.size()-1);
                                listTiles(player.playerMajiangs, startX, startY);
                                robotIndex = robotIndex + 1;
                            } else {
                                System.out.println("循环4结束");
                                robotIndex = robotIndex + 1;
                                System.out.println("结束后index" + robotIndex);
                            }
                        }
                    }
                }else {
                    System.out.println("lundaowol");
                    player.playerMajiangs.add(ShuffleMajiang.maJiangs.get(0));
                    ShuffleMajiang.maJiangs.remove(0);
                    System.out.println("playersize"+player.playerMajiangs.size());
                    if (player.isHu(player.playerMajiangs, player.cardsToDisplay)) {
                        //hu_button
                    } else if (player.Tinging) {
                        player.discardAfterTing();
                    } else if (player.isTing()) {
                        ting_button(player.TingTiles);
                    }else{
                        canClick = true;
                        listTiles(player.playerMajiangs, startX, startY);
                        System.out.println("轮到我了");
                        timer.cancel();
                    }

                }

            }
        };
        timer.schedule(robotPlayTask,1000,1000);  // 每1秒执行一次任务


    }

    //written by Siying.Li
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
                int x_1 = ((panelWidth1 - labelWidth1) / 64) * 49;
                int y_1 = ((panelHeight1 - labelHeight1) / 64) * 40;
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
                int x_2 = ((panelWidth2 - labelWidth2) / 64) * 18-15;
                int y_2 = ((panelHeight2 - labelHeight2) / 64) * 15-25;
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
                int x_3 = ((panelWidth3 - labelWidth3) / 64) * 43+30;
                int y_3 = ((panelHeight3 - labelHeight3) / 64) * 12;
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

