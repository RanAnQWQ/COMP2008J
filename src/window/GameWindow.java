package window;


import GameTable.ShuffleMajiang;
import HuHelper.Hu;
import Player.Player;
import tiles.Tilemap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//import static window.GameContent.agame;
//import static window.GameContent.helpButtons;


public class GameWindow extends JFrame {
    public ImagePanel gamePanel;
    public JTextField nameField;
    public Tilemap tilemap;
    public ArrayList<Integer> tileNumber;
    public int cardToDiscard;



    //    static {
    GameContent gameContent = new GameContent();
    //    }
    public void setTileNumber(ArrayList<Integer> tileNumber) {
        this.tileNumber = tileNumber;
    }


    Random number = new Random();
    public int sum = number.nextInt(12) + 2;
    public int getsum(){
        return sum;
    }


    // set the number of dices;

    public GameWindow() {
        window_frame();
//        GameContent.helpB
        gameContent.helpButtons(gamePanel);
        gameContent.dice_button(sum,gamePanel);

        this.setVisible(true);
        chooseAvatar();

        headShot_M("Majiang/src/profilephoto/bear.png");
        headShot_M("Majiang/src/profilephoto/cat.png");
        headShot_M("Majiang/src/profilephoto/cow.png");
        // Add the headShots of 3 machine players;

        tileNumber = new ArrayList<>();

    }


    private void window_frame() {
        setTitle("Game Window");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        gamePanel = new ImagePanel("Majiang/src/window/background/background3.jpg");
        gamePanel.setLayout(null);
        add(gamePanel);
    }

    ////////////////////////////set buttons///////////////////////
    public void setbuttons(ArrayList<Integer> set, boolean pengJudge, boolean gangJudge){
        chi_button(set);
        peng_button(pengJudge);
        gang_button(gangJudge);
    }

    public void chi_button(ArrayList<Integer> set) {//the index should be int
        JButton chi = new JButton();
        chi.setBorderPainted(false);
        chi.setFocusPainted(false);
        chi.setContentAreaFilled(false);
        //chi.setVisible(true);

        boolean judge;
        if (set != null){
            judge = true;
        }else {
            judge = false;
        }

        if (judge){
            chi.setIcon(new ImageIcon(new ImageIcon("Majiang/src/PromtButton/Chi.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            chi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println(" Display available");
                    // test, num == 3 right now
                    chi_choice(set);
                }
            });
        }else {
            chi.setIcon(new ImageIcon(new ImageIcon("Majiang/src/PromtButton/Chi_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }
        chi.setBounds(695, 570, 45, 57);

        gamePanel.add(chi);
    }




    private int chi_choice(ArrayList<Integer> option){
        int num = option.size();

        CountDownLatch latch = new CountDownLatch(1);
        int[] index = new int[1];

        if (num == 3){
            return 3;
        }
        //if exist 1 chi type, return 3 directly
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
                    JPanel clickedPanel = (JPanel) e.getSource();
                    index[0] = (int) clickedPanel.getClientProperty("index");
                    System.out.println("Clicked panel index: " + index[0]);
                    choice.dispose();
                    latch.countDown();
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

        return index[0];
    }


    public boolean peng_button(boolean judge) {
        JButton peng = new JButton();
        peng.setBorderPainted(false);
        peng.setFocusPainted(false);
        peng.setContentAreaFilled(false);
        // set the button of "peng";

        //judge if peng is available;
        if (judge){
            peng.setIcon(new ImageIcon(new ImageIcon("Majiang/src/PromtButton/Peng.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            peng.setBounds(745, 570, 45, 57);
            peng.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Peng is available");
                }
            });
            gamePanel.add(peng);
            System.out.println("Peng is chosen");
            return true;
            //return true if the user chosed peng;
        }else {
            peng.setIcon(new ImageIcon(new ImageIcon("Majiang/src/PromtButton/Peng_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            peng.setBounds(745, 570, 45, 57);
            gamePanel.add(peng);
            //default mode;
        }
        return false;
    }

    public boolean gang_button(boolean judge) {
        JButton gang = new JButton();
        gang.setBorderPainted(false);
        gang.setFocusPainted(false);
        gang.setContentAreaFilled(false);
        //set the button of Gang;

        //judge if the gang is available;
        if (judge){
            gang.setIcon(new ImageIcon(new ImageIcon("Majiang/src/PromtButton/Gang.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            gang.setBounds(795, 570, 45, 57);
            gang.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Gang is available");
                }
            });
            gamePanel.add(gang);
            return true;
            //return true if user choose gang;
        }else {
            gang.setIcon(new ImageIcon(new ImageIcon("Majiang/src/PromtButton/Gang_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            gang.setBounds(795, 570, 45, 57);
            gamePanel.add(gang);
            // default mode;
        }
        return false;
    }
    private boolean isAvailable(){
        return true;
    }

    public void ting_button(Player player){
        JButton ting = new JButton();
        ting.setBorderPainted(false);
        ting.setFocusPainted(false);
        ting.setContentAreaFilled(false);

//        if (player.isTing()){
//            ting.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Ting.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
//            ting.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    System.out.println("Ting is available");
//                }
//            });
//        }else {
//            ting.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Ting_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
//
//        }
        ting.setBounds(845, 570, 45, 57);

        gamePanel.add(ting);
    }


    public void hu_button(ArrayList<Integer> hand,ArrayList<Integer> HuTiles,int Chi,int Peng,int Gang, ImagePanel gamePanel) {
        Hu judge = new Hu();

        JButton hu = new JButton();
        hu.setBorderPainted(false);
        hu.setFocusPainted(false);
        hu.setContentAreaFilled(false);
//        if (judge.isHu(hand, HuTiles, Chi, Peng, Gang)) {
//            hu.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Hu.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
//            hu.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    // create a new frame called score
//                    JFrame scoreFrame = new JFrame("Score");
//                    scoreFrame.setBounds( 550, 300, 400, 300);
//
//                    //suppose the score is 100, you can get the score and show it according to the actual situation
//                    JLabel scoreLabel = new JLabel("Score: 100");
//                    scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
//
//                    scoreFrame.add(scoreLabel);
//                    scoreFrame.setVisible(true);
//                }
//            });
//
//        } else {
//            hu.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Hu_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
//        }
        hu.setBounds(895, 570, 45, 57);

        gamePanel.add(hu);

    }

    public void skip_button(ImagePanel gamePanel) {
        JButton skip = new JButton();
        skip.setBorderPainted(false);
        skip.setFocusPainted(false);
        skip.setContentAreaFilled(false);

        if (isAvailable()){
            skip.setIcon(new ImageIcon(new ImageIcon("Majiang/src/PromtButton/Guo.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
            skip.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Skip is available");
                }
            });
        }else {
            skip.setIcon(new ImageIcon(new ImageIcon("Majiang/src/PromtButton/Guo_unable.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
        }
        skip.setBounds(945, 570, 42, 55);

        gamePanel.add(skip);
    }

    //////////////////////profile photo///////////////////////////////
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
            case "Majiang/src/profilephoto/bear.png":
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

            case "Majiang/src/profilephoto/cat.png":
                //set the "South" headshot;

                int labelWidth2 = scaledComIcon.getIconWidth();
                int labelHeight2 = scaledComIcon.getIconHeight();
                int panelWidth2 = gamePanel.getWidth();
                int panelHeight2 = gamePanel.getHeight();
                int x_2 = ((panelWidth2 - labelWidth2) / 64) * 18;
                int y_2 = ((panelHeight2 - labelHeight2) / 64) * 15;
                headLabel.setBounds(x_2, y_2, labelWidth2, labelHeight2);
                imagePanel.setBounds(x_2, y_2, labelWidth2, labelHeight2 + 20); // Increase height to accommodate "Player" label
                gamePanel.add(headLabel);
                gamePanel.add(imagePanel);
                break;

            case "Majiang/src/profilephoto/cow.png":
                //set the "West" headshot;

                int labelWidth3 = scaledComIcon.getIconWidth();
                int labelHeight3 = scaledComIcon.getIconHeight();
                int panelWidth3 = gamePanel.getWidth();
                int panelHeight3 = gamePanel.getHeight();
                int x_3 = ((panelWidth3 - labelWidth3) / 64) * 43;
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

        ImageIcon Icon = new ImageIcon("Majiang/src/profilephoto/crocodile.png");

        JButton crocodile = new JButton();
        crocodile.setBorderPainted(false); //set the button frame invisible;
        crocodile.setFocusPainted(false);
        crocodile.setContentAreaFilled(false);
        crocodile.setIcon(new ImageIcon(new ImageIcon("Majiang/src/profilephoto/crocodile.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        crocodile.setBounds(65, 240, 60, 60);
        avatarPanel.add(crocodile);

        JButton fox = new JButton();
        fox.setBorderPainted(false);
        fox.setFocusPainted(false);
        fox.setContentAreaFilled(false);
        fox.setIcon(new ImageIcon(new ImageIcon("Majiang/src/profilephoto/fox.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        fox.setBounds(165, 240, 60, 60);
        avatarPanel.add(fox);

        JButton hamster = new JButton();
        hamster.setBorderPainted(false);
        hamster.setFocusPainted(false);
        hamster.setContentAreaFilled(false);
        hamster.setIcon(new ImageIcon(new ImageIcon("Majiang/src/profilephoto/hamster.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        hamster.setBounds(265, 240, 60, 60);
        avatarPanel.add(hamster);

        JButton hedgehog = new JButton();
        hedgehog.setBorderPainted(false);
        hedgehog.setFocusPainted(false);
        hedgehog.setContentAreaFilled(false);
        hedgehog.setIcon(new ImageIcon(new ImageIcon("Majiang/src/profilephoto/hedgehog.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        hedgehog.setBounds(365, 240, 60, 60);
        avatarPanel.add(hedgehog);

        JButton rabbit = new JButton();
        rabbit.setBorderPainted(false);
        rabbit.setFocusPainted(false);
        rabbit.setContentAreaFilled(false);
        rabbit.setIcon(new ImageIcon(new ImageIcon("Majiang/src/profilephoto/rabbit.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        rabbit.setBounds(465, 240, 60, 60);
        avatarPanel.add(rabbit);

        crocodile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image scaledArrow;
                updateAvatar("Majiang/src/profilephoto/crocodile.png");
                avatarDialog.dispose();
            }
        });

        fox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image scaledArrow;
                updateAvatar("Majiang/src/profilephoto/fox.png");
                avatarDialog.dispose();
            }
        });

        hamster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image scaledArrow;
                updateAvatar("Majiang/src/profilephoto/hamster.png");
                avatarDialog.dispose();
            }
        });

        hedgehog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image scaledArrow;
                updateAvatar("Majiang/src/profilephoto/hedgehog.png");
                avatarDialog.dispose();
            }
        });

        rabbit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image scaledArrow;
                updateAvatar("Majiang/src/profilephoto/rabbit.png");
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

////////////////////////add or delete tiles/////////////////////////////////////////////////////////////////////////

    int startX = 230; //the position of use's tiles in hand
    int startY = 640;
    int maxTilesPerRow = 12; // 每行最多显示的牌数
    int discardStartX = 418; // 弃牌的起始X位置
    int discardStartY = 420;
    public boolean canClick = true;

    //public Condition condition;

    public Lock lock;
    public Condition game;
    public Condition click;

    public void addTileToWindow(Lock lock, Condition game, Condition click) {  //written by Siying.Li
        this.lock = lock;
        this.game = game;
        this.click = click;
        lock.lock();


        listTiles(tileNumber, startX, startY);
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    void hideTiles(){
        for (Component comp : gamePanel.getComponents()) {
            if (comp instanceof JLabel && ((JLabel) comp).getClientProperty("tileNumber") != null) {
                comp.setVisible(false);
                comp.setEnabled(false);
            }
        }
    }

    public void listTiles(List<Integer> tileNumber, int startX, int startY) {
        if(tileNumber == null){
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

                int scaledWidth = TileIcon.getIconWidth() / 5 * 2;  //reset the size of the tile
                int scaledHeight = TileIcon.getIconHeight() / 5 * 2;

                int currentX = startX + i * (scaledWidth);  //reset the site of the tile
                int currentY = startY;
                JLabel tileLabel = createTileLabel(TileIcon, scaledWidth, scaledHeight, currentX, currentY,tileNum);
                gamePanel.add(tileLabel);

            }
        }
    }

    private JLabel createTileLabel(ImageIcon TileIcon, int scaledWidth, int scaledHeight, int currentX, int currentY,int tileNum) {
        JLabel tileLabel = new JLabel(new ImageIcon(TileIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)));
        tileLabel.setBounds(currentX, currentY, scaledWidth, scaledHeight);
        tileLabel.putClientProperty("tileNumber", tileNum);


        try {

            tileLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (canClick) {
                        canClick = false;
                        tileLabel.setEnabled(false);//every turn, only one tile can be discarded

                        JLabel clickedTile = (JLabel) e.getSource();//get the clicked tile
                        ImageIcon tileIcon = (ImageIcon) clickedTile.getIcon();
                        int tileNum = (int) clickedTile.getClientProperty("tileNumber");// tileNum is the card clicked by the user to discard
                        cardToDiscard = tileNum;

                        int tileWidth = tileIcon.getIconWidth();
                        int tileHeight = tileIcon.getIconHeight();
                        int newWidth = tileWidth / 2;  // set the new size of the tile in the river
                        int newHeight = tileHeight / 2;

                        JLabel newTile = new JLabel(new ImageIcon(tileIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)));
                        int newTileX = discardStartX; //set the position of the tile in the river
                        int newTileY = discardStartY;
                        int tilesInRow = 0;

                        for (Component comp : gamePanel.getComponents()) {
                            if (comp instanceof JLabel && comp != newTile) {
                                JLabel existingTile = (JLabel) comp;
                                if (existingTile.getX() == newTileX && existingTile.getY() == newTileY) {
                                    newTileX += existingTile.getWidth() - 27; // add the X position
                                    tilesInRow++;
                                    if (tilesInRow >= maxTilesPerRow) { //when the tile in river > 12, change the line
                                        tilesInRow = 0;
                                        newTileX = discardStartX;
                                        newTileY += 38; // add the Y position and change the line
                                    }
                                }
                            }
                        }

                        newTile.setBounds(newTileX, newTileY, tileWidth, tileHeight);

                        gamePanel.add(newTile);  //make the smaller clicked tile in the river position

                        // written by Qiyue Zhu.........................
                        // pick a card to put it into the river
                        ShuffleMajiang.river.add(ShuffleMajiang.riverIndex, tileNum);
                        ShuffleMajiang.riverIndex++;
                        // for testing
                        System.out.println("@@@" + ShuffleMajiang.riverIndex + " in " + ShuffleMajiang.river);
                        // written by Qiyue Zhu..........................

                        tileNumber.remove(Integer.valueOf(tileNum));  //remove the clickedtile's number and relist the rest tiles
                        //discarded.add(tileNum);
                        listTiles(tileNumber, startX, startY);

                        // written by Qiyue Zhu.........................
                        // pick a card to put it into the river
                        ShuffleMajiang.river.add(ShuffleMajiang.riverIndex, tileNum);
                        ShuffleMajiang.riverIndex++;
                        // for testing
                        System.out.println("@@@" + ShuffleMajiang.riverIndex + " in " + ShuffleMajiang.river);
                        // written by Qiyue Zhu..........................


                        gamePanel.revalidate();
                        gamePanel.repaint();

                        // wake up all threads
                        game.signal();
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


        } finally {

        }
        return tileLabel;
    }
    public void  resetTurn() {
        canClick = true;
        for (Component comp : gamePanel.getComponents()) {
            if (comp instanceof JLabel && ((JLabel) comp).getClientProperty("tileNumber") != null) {
                comp.setEnabled(true);
            }
        }
    }
}

