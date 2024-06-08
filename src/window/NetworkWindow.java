package window;


import Player.Computer;
import Player.NetworkPlayer;
import Player.Player;
import tiles.Tilemap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;


public class NetworkWindow extends JFrame {
    private boolean skipClicked = false;
    public ImagePanel gamePanel;
    public JTextField nameField;
    public Tilemap tilemap = new Tilemap();
    public ArrayList<Integer> computer1Number;
    public ArrayList<Integer> computer2Number;
    public ArrayList<Integer> computer3Number;
    public Computer computer1;
    public Computer computer2;
    public Computer computer3;

    public NetworkPlayer player;
    public int cardToDiscard;
    public static String host = "localhost";
    public static int port = 12345;
    public JButton gangButton = new JButton();
    public JButton pengButton = new JButton();
    public JButton skipButton = new JButton();
    public JButton chiButton = new JButton();
    public JButton tingButton = new JButton();
    public JButton huButton = new JButton();
    public static List<JLabel> rivers = new ArrayList<>();


    //    static {
    NetworkContent networkContent;

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
    public void setHuman(NetworkPlayer humanPlayer) {
        this.player = humanPlayer;
    }


    Random number = new Random();
    public int sum = number.nextInt(12) + 2;

    public int getsum() {
        return sum;
    }

    // set the number of dices;

    public NetworkWindow(NetworkContent networkContent) {
        this.networkContent = networkContent;

        window_frame();
        networkContent.helpButtons(gamePanel);
        networkContent.dice_button(sum, gamePanel);

        this.setVisible(true);
        chooseAvatar();
    }

    private void window_frame() {
        setTitle("Game Window");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        gamePanel = new ImagePanel("src/window/background/background4.jpg");
        gamePanel.setLayout(null);
        add(gamePanel);
    }

    public boolean chi_button(ArrayList<Integer> set) {
        chiButton.setBorderPainted(false);
        chiButton.setFocusPainted(false);
        chiButton.setContentAreaFilled(false);

        boolean judge = set.size()>0;

        boolean result = false;

        if (judge) {
            chiButton.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Chi.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            result = true;
        } else {
            chiButton.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Chi_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }
        chiButton.setBounds(695, 570, 45, 57);

        gamePanel.add(chiButton);
        gamePanel.validate();
        gamePanel.repaint();

        return result;
    }


    public int chi_choice(Player player, ArrayList<Integer> option) {
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


    public boolean peng_button(boolean judge) {
        pengButton.setBorderPainted(false);
        pengButton.setFocusPainted(false);
        pengButton.setContentAreaFilled(false);

        boolean result = false;
        if (judge) {
            pengButton.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Peng.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            result = true;
        } else {
            pengButton.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Peng_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }
        pengButton.setBounds(745, 570, 45, 57);
        gamePanel.add(pengButton);
        gamePanel.validate();
        gamePanel.repaint();

        return result;
    }


    public boolean gang_button(boolean judge) {
        gangButton.setBorderPainted(false);
        gangButton.setFocusPainted(false);
        gangButton.setContentAreaFilled(false);
        //set the button of Gang;

        boolean result = false;

        //judge if the gang is available;
        if (judge) {
            gangButton.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Gang.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            result = true;
        } else {
            gangButton.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Gang_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }

        gangButton.setBounds(795, 570, 45, 57);
        gamePanel.add(gangButton);
        gamePanel.validate();
        gamePanel.repaint();

        return result;
    }

    private boolean isAvailable() {
        return true;
    }

    public boolean ting_button(ArrayList<Integer> option) {
        tingButton.setBorderPainted(false);
        tingButton.setFocusPainted(false);
        tingButton.setContentAreaFilled(false);

        boolean judge = option.size()>0;
        final boolean[] result = {false};
        CountDownLatch latch = new CountDownLatch(1);

        if (judge) {
            tingButton.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Ting.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            tingButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Ting is available");
                    int index = ting_choice(option);
                    System.out.println("Ting choice index: " + index);
                    result[0] = true;
                    latch.countDown();
                }
            });
        } else {
            tingButton.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Ting_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }
        tingButton.setBounds(845, 570, 45, 57);
        gamePanel.add(tingButton);

        gamePanel.validate();
        gamePanel.repaint();

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

    public void skip_button(boolean ifExist) {
        skipButton.setBorderPainted(false);
        skipButton.setFocusPainted(false);
        skipButton.setContentAreaFilled(false);

        if (ifExist) {
            skipButton.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
            skipButton.addActionListener(e -> {
                System.out.println("Skip is available");
                removeButton();
                sendToServer("Skip", "Skip");
            });
        } else {
            skipButton.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo_unable.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
        }
        skipButton.setBounds(945, 570, 42, 55);

        gamePanel.add(skipButton);

        gamePanel.validate();
        gamePanel.repaint();
    }

////////////////////////add or delete tiles/////////////////////////////////////////////////////////////////////////

    public int startX = 230; //the position of use's tiles in hand
    public int startY = 640;
    public int maxTilesPerRow = 12; // 每行最多显示的牌数
    public int discardStartX = 418; // 弃牌的起始X位置
    public int discardStartY = 420;
    public int scaledWidth = 0;
    public int scaledHeight = 0;
    public boolean canClick = false;

    public void addTileToWindow(List<Integer> tileNumber) {  //written by Siying.Li
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

    public void listTiles(List<Integer> tileNumber, int startX, int startY) {
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
                JLabel tileLabel = createTileLabel(TileIcon, scaledWidth, scaledHeight, currentX, currentY,
                        tileNum,tileNumber);
                gamePanel.add(tileLabel);
            }
        }

//        if (player.isHu(player.playerMajiangs, player.cardsToDisplay)) {
//            //hu_button
//        } else if (player.Tinging) {
//            player.discardAfterTing();
//        } else if (player.isTing()) {
//            ting_button(player.TingTiles);
//        }

        gamePanel.revalidate();
        gamePanel.repaint();

    }

    private JLabel createTileLabel(ImageIcon TileIcon, int scaledWidth, int scaledHeight, int currentX, int currentY, int tileNum, List<Integer> tileNumber) {
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
                    networkContent.addNetworkTile.addTileToRiverX(tileIcon, tileNum, discardStartX, discardStartY, maxTilesPerRow, gamePanel,4);

                    // 移除被点击的牌号并重新列出剩余的牌
                    tileNumber.remove(Integer.valueOf(tileNum));
                    Collections.sort(tileNumber);
                    listTiles(tileNumber, startX, startY);

                    player.playerMajiangs.remove(Integer.valueOf(tileNum));

                    //判断是否听牌
                    if (!player.Tinging) {
                        if (player.isTing()) {
                            System.out.println("判断玩家能ting了，按钮应该能亮");
                            if(ting_button(player.TingThrowTiles)){
                                ting_choice(player.TingThrowTiles);
                                System.out.println("玩家点了听了");
                                player.Tinging=true;
                            }
                        }
                    }

                    sendToServer("Discard", String.valueOf(cardToDiscard));
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

    public void resetTurn() {
        canClick = true;
        for (Component comp : gamePanel.getComponents()) {
            if (comp instanceof JLabel && ((JLabel) comp).getClientProperty("tileNumber") != null) {
                comp.setEnabled(true);
            }
        }
    }
    //////////////////////start the turn////////////////////////
    public AddNetworkTile addNetworkTile = new AddNetworkTile();

    public void startRobotPlaySequence(int index) {


    }

    // 其他人打牌的方法
    public void otherPlayTile(int x, int y, Boolean isX, int computerName, int num) {

        String tilePath = tilemap.getTilePath(num);
        if (tilePath != null) {
            if(isX){
                if (computerName == 2) {
                    networkContent.addNetworkTile.addTileToRiverX(new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), num, x, y, 10, gamePanel, 2);
                } else {
                    networkContent.addNetworkTile.addTileToRiverX(new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), num, x, y, 10, gamePanel, 4);
                }
            }else {
                if (computerName == 1) {
                    networkContent.addNetworkTile.addTileToRiverY(new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), num, x, y, 10, gamePanel, 1);
                } else {
                    networkContent.addNetworkTile.addTileToRiverY(new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), num, x, y, 10, gamePanel, 3);

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
                int x_1 = ((panelWidth1 - labelWidth1) / 64) * 49+150;
                int y_1 = ((panelHeight1 - labelHeight1) / 64) * 40-80;
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
                int x_2 = ((panelWidth2 - labelWidth2) / 64) * 18-80;
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
                int x_2 = ((panelWidth2 - labelWidth2) / 64) * 18-130;
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
                int x_3 = ((panelWidth3 - labelWidth3) / 64) * 43+130;
                int y_3 = ((panelHeight3 - labelHeight3) / 64) * 12-80;
                headLabel.setBounds(x_3, y_3, labelWidth3, labelHeight3);
                imagePanel.setBounds(x_3, y_3, labelWidth3, labelHeight3 + 20); // Increase height to accommodate "Player" label
                gamePanel.add(headLabel);
                gamePanel.add(imagePanel);
                break;

        }

        gamePanel.repaint();
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
        sendToServer("Avatar", imagePath);


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

    public void sendToServer(String prefix, String msg){
        try {
            PrintWriter out = new PrintWriter(networkContent.socket.getOutputStream(), true);
            out.println(prefix + "-" + networkContent.clientId + "-" + msg);
            System.out.println("sendToServer-"+prefix + "-" + networkContent.clientId + "-" + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeButton() {
        gamePanel.remove(skipButton);
        gamePanel.remove(pengButton);
        gamePanel.remove(gangButton);
        gamePanel.remove(chiButton);
    }
}

