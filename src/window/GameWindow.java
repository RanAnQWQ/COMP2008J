package window;


import GameTable.ShuffleMajiang;
import HuHelper.Hu;
import TingHelper.TingListener;
import tiles.Tilemap;
import Player.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.Timer;


public class GameWindow extends JFrame {
    private ImagePanel gamePanel;
    public JTextField nameField;
    public Tilemap tilemap;
    public ArrayList<Integer> tileNumber;
    public int cardToDiscard;



/*

    public static void main(String[] args) {
        new GameWindow();

    }
*/

    public void setTileNumber(ArrayList<Integer> tileNumber) {
        this.tileNumber = tileNumber;
    }

    public static void agame() throws InterruptedException {  // the methods to create a game
        GameWindow gameWindow = new GameWindow();  // initial the game window

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
        Collections.sort(player.getPlayerMajiangs());
        Collections.sort(computer1.getPlayerMajiangs());
        Collections.sort(computer2.getPlayerMajiangs());
        Collections.sort(computer3.getPlayerMajiangs());

        // in the first turn, the host will gain one more card to discard first
        initPlayer.players.get(0).gainMajiang(1);  // index are got from the window


        System.out.println("player: "+player.playerMajiangs);
        System.out.println("size: "+player.playerMajiangs.size());


        gameWindow.tilemap = new Tilemap();
        gameWindow.setTileNumber(player.getPlayerMajiangs());
        System.out.println("tile: "+gameWindow.tileNumber);

        gameWindow.addTileToWindow();
        gameWindow.hideTiles();


        // in the first turn, the host will discard a card
        System.out.println(gameWindow.cardToDiscard);
        //initPlayer.players.get(0).discardMajiang(player.playerMajiangs.indexOf(gameWindow.cardToDiscard));  // index are got from the window



        // to judge if any of the players will Hu or not
        boolean playerHu = Hu.isHu(player.getPlayerMajiangs(), player.getCardsToDisplay(), player.ChiNumber, player.PengNumber, player.GangNumber);
        boolean computer1Hu = Hu.isHu(computer1.getPlayerMajiangs(), computer1.getCardsToDisplay(), computer1.ChiNumber, computer1.PengNumber, computer1.GangNumber);
        boolean computer2Hu = Hu.isHu(computer2.getPlayerMajiangs(), computer2.getCardsToDisplay(), computer2.ChiNumber, computer2.PengNumber, computer2.GangNumber);
        boolean computer3Hu = Hu.isHu(computer3.getPlayerMajiangs(), computer3.getCardsToDisplay(), computer3.ChiNumber, computer3.PengNumber, computer3.GangNumber);



        if (ShuffleMajiang.river.size() > 0){
            // take turns to play, always start with the host
            for (int turn = 1; turn < 4; turn++) {
                // if any of the players Hu, the game will end
                if ((!playerHu) && (!computer1Hu) && (!computer2Hu) && (!computer3Hu)) {
                    // in one's turn, the movement of Chi, Peng, Gang will happen
                    int riverLastCard = ShuffleMajiang.river.get(ShuffleMajiang.riverIndex);
                    Movement movement1 = new Movement(riverLastCard, initPlayer.players.get(turn).ChiNumber, initPlayer.players.get(turn).PengNumber, initPlayer.players.get(turn).GangNumber);

                    // in one's turn, the player will gain a card and discard one
                    initPlayer.players.get(turn).gainMajiang(1);  // index are got from the window
                    //initPlayer.players.get(turn).discardMajiang(player.playerMajiangs.indexOf(gameWindow.cardToDiscard));  // index are got from the window

                    //and for others, the possible movements like Peng, Gang will be detected
                    Movement movement2 = new Movement(initPlayer.players.get((turn + 1) % 4).getPlayerMajiangs(), initPlayer.players.get((turn + 1) % 4).ChiNumber, initPlayer.players.get((turn + 1) % 4).PengNumber, initPlayer.players.get((turn + 1) % 4).GangNumber);
                    Movement movement3 = new Movement(initPlayer.players.get((turn + 2) % 4).getPlayerMajiangs(), initPlayer.players.get((turn + 2) % 4).ChiNumber, initPlayer.players.get((turn + 2) % 4).PengNumber, initPlayer.players.get((turn + 2) % 4).GangNumber);
                    Movement movement4 = new Movement(initPlayer.players.get((turn + 3) % 4).getPlayerMajiangs(), initPlayer.players.get((turn + 3) % 4).ChiNumber, initPlayer.players.get((turn + 3) % 4).PengNumber, initPlayer.players.get((turn + 3) % 4).GangNumber);

                /*
        // after a Peng or Gang happens, the order of turn will change
        if ( this.PengNumber != PengNumber || this.GangNumber != GangNumber ){
             currentPlayer = ;
        }*/
                }
            }
        }
    }


    Random number = new Random();
    public int sum = number.nextInt(12) + 2;


    // set the number of dices;

    public GameWindow() {
        window_frame();
        helpButtons();
        dice_button(sum);

        this.setVisible(true);
        chooseAvatar();


        headShot_M("src/profilephoto/bear.png");
        headShot_M("src/profilephoto/cat.png");
        headShot_M("src/profilephoto/cow.png");
        // Add the headShots of 3 machine players;

        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(11, 11, 13, 12, 12));
        // TESTTTTTTTTTTTTTTTT--------------!!!!!! ting



        chi_button();
        peng_button();
        gang_button();
        ting_button(a,  new ArrayList<>(Arrays.asList( 21, 21, 21, 23, 24, 25, 31, 32, 33)), 2, 2, 0);
        skip_button();
        hu_button(a, new ArrayList<>(Arrays.asList(12,12,12,21,21,21,23,24,25,31,32,01)),2,2,0);
        // Add 5 prompt buttons to option;

        tileNumber = new ArrayList<>();
//        tilemap = new Tilemap();
//        addTileToWindow();
//        hideTiles();
//        addTileToWindow(11,220,660);

//        tileLabel.setVisible(false);
//        tileLabel.setEnabled(false);

    }
    private void window_frame() {
        setTitle("Game Window");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        gamePanel = new ImagePanel("src/window/background3.jpg");
        gamePanel.setLayout(null);
        add(gamePanel);
    }

/////////////////////////add or delete tiles/////////////////////////////////////////////////////////////////////////

    int startX = 250; //the position of use's tiles in hand
    int startY = 640;
    public void addTileToWindow() {  //written by Siying.Li
        //tileNumber = new ArrayList<>(Arrays.asList());
//        Random random = new Random();
//        for (int i = 0; i < 13; i++) {
//            int randomNumber;
//            do {
//                randomNumber = random.nextInt(37) + 11; // 生成 11 到 47 之间的随机数
//            } while (randomNumber == 20 || randomNumber == 30 || randomNumber == 40);
//            tileNumber.add(randomNumber);
//        }
//        System.out.print("Generated Random Numbers:");
//        for (int number : tileNumber) {
//            System.out.print(number+" ");
//        }

        //Collections.sort(tileNumber);
        listTiles(tileNumber, startX, startY);


        gamePanel.revalidate();
        gamePanel.repaint();

    }

    private void hideTiles(){
        for (Component comp : gamePanel.getComponents()) {
            if (comp instanceof JLabel && ((JLabel) comp).getClientProperty("tileNumber") != null) {
                comp.setVisible(false);
                comp.setEnabled(false);
            }
        }
    }

    public void listTiles(List<Integer> tileNumber, int startX, int startY) {
//        for (int i = 0; i < gamePanel.getComponentCount(); i++) {
//            Component comp = gamePanel.getComponent(i);
//            if (comp instanceof JLabel && ((JLabel) comp).getClientProperty("tileNumber") != null) {
//                gamePanel.remove(comp);
//                i--;//唱注：这里i--是因为此处的牌会一张张减少。若保持13张牌则再修改
//            }
//        }
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

        tileLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                JLabel clickedTile = (JLabel) e.getSource();
                ImageIcon tileIcon = (ImageIcon) clickedTile.getIcon();
                int tileNum = (int) clickedTile.getClientProperty("tileNumber");
                // tileNum is the card clicked by the user to discard
                cardToDiscard = tileNum;

                int tileWidth = tileIcon.getIconWidth();
                int tileHeight = tileIcon.getIconHeight();
                int tileX = clickedTile.getX();
                int tileY = clickedTile.getY();

                int newWidth = tileWidth / 2;  // set the new size of the tile in the river
                int newHeight = tileHeight / 2;

                JLabel newTile = new JLabel(new ImageIcon(tileIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)));
                newTile.setBounds(tileX, tileY, tileWidth, tileHeight);

                int newTileX = 420;//the position of user's discarded tiles
                int newTileY = 420;

                boolean foundAdjacentPosition = false;
                for (Component comp : gamePanel.getComponents()) {
                    if (comp instanceof JLabel && comp != newTile) {
                        JLabel existingTile = (JLabel) comp;
                        if (existingTile.getX() == newTileX && existingTile.getY() == newTileY) {
                            newTileX += existingTile.getWidth()-27; // Increment X position by width of existing tile
                        }
                    }
                }

                newTile.setBounds(newTileX, newTileY,tileWidth,tileHeight);

                gamePanel.add(newTile);  //make the smaller clicked tile in the river position

                // written by Qiyue Zhu.........................
                // pick a card to put it into the river
                ShuffleMajiang.river.add(ShuffleMajiang.riverIndex, tileNum);
                ShuffleMajiang.riverIndex++;
                // for testing
                System.out.println("@@@"+ShuffleMajiang.riverIndex + " in " + ShuffleMajiang.river);
                // written by Qiyue Zhu.........................

                tileNumber.remove(Integer.valueOf(tileNum));  //remove the clickedtile's number and relist the rest tiles
                //discarded.add(tileNum);
//
                listTiles(tileNumber, startX, startY);

                gamePanel.revalidate();
                gamePanel.repaint();
            }


            @Override
            public void mouseEntered(MouseEvent e) {
                tileLabel.setLocation(tileLabel.getX(), tileLabel.getY() - 10);
                gamePanel.revalidate();
                gamePanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tileLabel.setLocation(tileLabel.getX(), tileLabel.getY() + 10);
                gamePanel.revalidate();
                gamePanel.repaint();
            }
        });

        return tileLabel;
    }


    //////////////////////profile photo///////////////////////////////
    private void headShot_M(String imagePath) {  //written by Jinyan.Shen
        ImageIcon com;
        Image scaledCom;
        ImageIcon scaledComIcon;
        JLabel headLabel;
        //create the image object;

        //Label playerLabel;
        //create player label to display name;

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
                int x_2 = ((panelWidth2 - labelWidth2) / 64) * 18;
                int y_2 = ((panelHeight2 - labelHeight2) / 64) * 15;
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
                int x_3 = ((panelWidth3 - labelWidth3) / 64) * 43;
                int y_3 = ((panelHeight3 - labelHeight3) / 64) * 12;
                headLabel.setBounds(x_3, y_3, labelWidth3, labelHeight3);
                imagePanel.setBounds(x_3, y_3, labelWidth3, labelHeight3 + 20); // Increase height to accommodate "Player" label
                gamePanel.add(headLabel);
                gamePanel.add(imagePanel);
                break;

        }

    }

    // Buttons of Chi Peng Gang Hu;
    private void chi_button() {
        //Movement movement = new Movement(playerMajiangs, numChi, numPeng, numGang);

        JButton chi = new JButton();
        chi.setBorderPainted(false);
        chi.setFocusPainted(false);
        chi.setContentAreaFilled(false);

        if (isAvailable()){
            chi.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Chi.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
            chi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Chi is available");
                }
            });
        }else {
            chi.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Chi_unable.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        }
        chi.setBounds(690, 575, 40, 40);

        gamePanel.add(chi);
    }

    private void peng_button() {
        JButton peng = new JButton();
        peng.setBorderPainted(false);
        peng.setFocusPainted(false);
        peng.setContentAreaFilled(false);
        if (isAvailable()){
            peng.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Peng.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
            peng.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Chi is available");
                }
            });
        }else {
            peng.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Peng_unable.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        }
        peng.setBounds(740, 575, 45, 45);

        gamePanel.add(peng);
    }

    private void gang_button() {
        JButton gang = new JButton();
        gang.setBorderPainted(false);
        gang.setFocusPainted(false);
        gang.setContentAreaFilled(false);
        if (isAvailable()){
            gang.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Gang.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
            gang.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Chi is available");
                }
            });
        }else {
            gang.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Gang_unable.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        }

        gang.setBounds(790, 575, 45, 45);

        gamePanel.add(gang);
    }

    private void ting_button(ArrayList<Integer> currenthand, ArrayList<Integer> HuTiles, int Chi, int Peng, int Gang) {
        TingListener isTing = new TingListener();
        JButton ting = new JButton();
        ting.setBorderPainted(false);
        ting.setFocusPainted(false);
        ting.setContentAreaFilled(false);

        if (isTing.isTing(currenthand, HuTiles, Chi, Peng, Gang)){
            ting.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Ting.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
            ting.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Ting is available");
                }
            });
        }else {
            ting.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Ting_unable.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));

        }
        ting.setBounds(840, 575, 45, 45);

        gamePanel.add(ting);
    }


    private void hu_button(ArrayList<Integer> hand,ArrayList<Integer> HuTiles,int Chi,int Peng,int Gang) {
        Hu judge = new Hu();

        JButton hu = new JButton();
        hu.setBorderPainted(false);
        hu.setFocusPainted(false);
        hu.setContentAreaFilled(false);
        if (judge.isHu(hand, HuTiles, Chi, Peng, Gang)) {
            hu.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Hu.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
            hu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // 创建新窗口
                    JFrame scoreFrame = new JFrame("Score");
                    scoreFrame.setBounds( 550, 300, 400, 300);

                    // 假设分数为100，您可以根据实际情况获取分数并显示
                    JLabel scoreLabel = new JLabel("Score: 100");
                    scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

                    scoreFrame.add(scoreLabel);
                    scoreFrame.setVisible(true);
                }
            });

        } else {
            hu.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Hu_unable.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        }
        hu.setBounds(895, 575, 45, 45);

        gamePanel.add(hu);

    }

    private void skip_button() {
        JButton skip = new JButton();
        skip.setBorderPainted(false);
        skip.setFocusPainted(false);
        skip.setContentAreaFilled(false);

        if (isAvailable()){
            skip.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
            skip.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Skip is available");
                }
            });
        }else {
            skip.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo_unable.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        }
        skip.setBounds(945, 575, 45, 45);

        gamePanel.add(skip);
    }


    private boolean isAvailable(){
        return true;
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
        int Width = Icon.getIconWidth();
        int Height = Icon.getIconHeight();

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

        ///////////////////////////////////

        gamePanel.revalidate();
        gamePanel.repaint();

    }


    ///////////////////////dice button/////////////////////////
    private void dice_button(int sum) {  //written by Jinyan.Shen

        JButton num = new JButton("THROW DICE");
        Font dice_font = new Font("Arial", Font.BOLD, 24);
        num.setFocusPainted(false);
        num.setFont(dice_font);
        num.setHorizontalAlignment(SwingConstants.CENTER);
        num.setBounds(500, 0, 200, 50);
        gamePanel.add(num);
        // set the button;

        num.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component comp : gamePanel.getComponents()) {

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

                gamePanel.remove(num);
//                tileLabel.setEnabled(true);
//                tileLabel.setVisible(true);
                // remove the original button once the label occur;

                Timer timer = new Timer();

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        SwingUtilities.invokeLater(() -> {
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
    // set the dice to choose the host;

    private ImageIcon choose_arrow(int sum) {
        ImageIcon arrowIcon;

        switch (sum % 4) {
            case 1:
                arrowIcon = new ImageIcon("src/window/ArrowDown.png");
                break;
            //East;

            case 2:
                arrowIcon = new ImageIcon("src/window/ArrowRight.png");
                break;
            //North;

            case 3:
                arrowIcon = new ImageIcon("src/window/ArrowTop.png");
                break;
            //West;

            default:
                arrowIcon = new ImageIcon("src/window/ArrowTop.png");
                break;
            //South;

        }
        return arrowIcon;
    }

    // This is the way to choose the host in first game;
    // Set the arrow to choose the host. According to rules, from the East player, counting n times (n is the sum of 2 dices) in counter-clockwise, the last one is the host of this game;

    //---------------------not in first game--------------------

    public String getFirstHost() {
        String host;
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



    ///////////////////////method of help button including menu,restart and rules in game window///////////////////////////
    private void helpButtons() {  //written by Siying.Li
        JButton helpButton = new JButton("HELP");  //set the help button
        Font helpFont = new Font("Arial", Font.BOLD, 24);
        helpButton.setFocusPainted(false);
        helpButton.setFont(helpFont);
        helpButton.setHorizontalAlignment(SwingConstants.CENTER);
        helpButton.addActionListener(new ActionListener() {  //if click the game button, the menu window will be closed and the game window will be open
            public void actionPerformed(ActionEvent e) {
                JFrame helpWindow = new JFrame("Help Window");
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
                        dispose();
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
//                        try {
//                            helpWindow.dispose();
//                            dispose();
//                            //new GameWindow().setVisible(true);
//                            setNewGame();
//                        }catch(InterruptedException ex) {
//                            ex.printStackTrace();
//                        }
                        helpWindow.dispose();
                        dispose();
//                        GameWindow newGame = new GameWindow();
//                        newGame.setVisible(true);
                        try {
                            agame();
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
}

