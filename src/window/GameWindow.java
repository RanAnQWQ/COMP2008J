package window;


import GameTable.ShuffleMajiang;
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
    public ImagePanel gamePanel;
    public JTextField nameField;
    public Tilemap tilemap;
    public Computer computer1;
    public Computer computer2;
    public Computer computer3;

    public HumanPlayer player;
    public int cardToDiscard;

    public JButton chi = new JButton();
    public JButton skipChi = new JButton();

    public JButton peng = new JButton();
    public JButton skipPeng = new JButton();

    public JButton gang = new JButton();
    public JButton skipGang = new JButton();

    public JButton ting = new JButton();
    public JButton skipTing = new JButton();

    GameContent gameContent = new GameContent();

    // set the four players
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
    // Decide the host;

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

        gamePanel = new ImagePanel("src/window/background/background4.jpg");
        gamePanel.setLayout(null);
        add(gamePanel);
    }
    // Set the outlook of gamewindow;


    public void chi_button(HumanPlayer player, ArrayList<Integer> set, int card) {
        // Set the icon for the Chi button
        chi.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Chi.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        chi.setBorderPainted(false);
        chi.setFocusPainted(false);
        chi.setContentAreaFilled(false);

        boolean judge;
        if (set != null){
            judge = true;
        } else {
            judge = false;
        }
        // Determine if the Chi action is possible

        int[] result = {100}; // Result to determine if skip is chosen

        CountDownLatch latch = new CountDownLatch(1); // Latch to wait for user action

        if (judge) {
            chi.setVisible(true);
            skipChi.setVisible(true);

            skipChi_button(judge); // Call skipChi_button to set up the skip button
            for (ActionListener al : chi.getActionListeners()) {
                chi.removeActionListener(al);
            }
            // Remove any existing action listeners from the Chi button

            chi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Display available");
                    int choice = chi_choice(player, set, card);
                    System.out.println(choice);
                    result[0] = choice;

                    latch.countDown();
                    chi.setVisible(false);
                    skipChi.setVisible(false);
                }
            });
        } else {
            chi.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Chi_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }
        chi.setBounds(695, 570, 45, 57); // Set the position of the Chi button

        gamePanel.add(chi); // Add the Chi button to the game panel
    }

    private int chi_choice(HumanPlayer player, ArrayList<Integer> option, int card) {
        int num = option.size(); // Number of options for Chi

        CountDownLatch latch = new CountDownLatch(1); // Latch to wait for user choice
        int[] index = new int[1]; // To store the index of the chosen option

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
            panel.putClientProperty("index", i); // Set the index property of the panel

            for (int j = 0; j < 3; j++) {
                System.out.println("option of chi window: " + option);
                int tileNum = option.get(i + j);
                System.out.println("tilenum " + tileNum);
                String tilePath = tilemap.getTilePath(tileNum);
                ImageIcon icon = new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(132 / 3, 191 / 3, Image.SCALE_SMOOTH));
                JLabel label = new JLabel(icon);
                panel.add(label); // Add the tile image to the panel
                System.out.println("Tile to add: " + tileNum);
            }

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Window Available");
                    JPanel clickedPanel = (JPanel) e.getSource();
                    index[0] = (int) clickedPanel.getClientProperty("index");
                    // Determine which option was clicked based on the index property
                    if (index[0] == 0) { //the player chosed the first 3 option;
                        if (option.size() == 3) { //if there are 1 possibility to Chi;

                            System.out.println("Player has already click chi");
                            player.Chi(player.isChi(card), 3, card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 4, addComputerTile);
                            System.out.println("Perform Chi");
                            computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                            robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                            listTiles(player.playerMajiangs, startX, startY);

                        } else if (option.size() == 6) { //if there are 2 possibilities to Chi;
                            System.out.println("Player has already click chi");
                            player.Chi(player.isChi(card), 1, card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 4, addComputerTile);
                            System.out.println("Perform Chi");
                            computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                            robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                            listTiles(player.playerMajiangs, startX, startY);

                        } else {//if there are 3 possibilities to Chi;
                            System.out.println("Player has already click chi");
                            player.Chi(player.isChi(card), 2, card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 4, addComputerTile);
                            System.out.println("Perform Chi");
                            computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                            robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                            listTiles(player.playerMajiangs, startX, startY);
                        }
                    }

                    if (index[0] == 3) { //if chosed the second 3 tiles;
                        if (option.size() == 6) { //if there are 2 possibilities to Chi;
                            System.out.println("Player has already click chi");
                            player.Chi(player.isChi(card), 2, card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 4, addComputerTile);
                            System.out.println("Perform Chi");
                            computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                            robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);

                        } else { //if there are 3 possibilities to Chi;
                            System.out.println("Player has already click chi");
                            player.Chi(player.isChi(card), 1, card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 4, addComputerTile);
                            System.out.println("Perform Chi");
                            computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                            robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                        }
                    }

                    if (index[0] == 6) { //if there are 3 possibilities to Chi and choosed last;
                        System.out.println("Player has already click chi");
                        player.Chi(player.isChi(card), 0, card, gameWindow.scaledWidth, gameWindow.scaledHeight, gameWindow.gamePanel, 4, addComputerTile);
                        System.out.println("Perform Chi");
                        computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                        robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                    }

                    System.out.println("Clicked panel index: " + index[0]);
                    choice.dispose(); // Close the choice window
                    latch.countDown(); // Release the latch
                    for (int i : player.cardsToDisplay) {
                        System.out.println(i);
                    }
                }
            });

            mainPanel.add(panel); // Add the option panel to the main panel
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        choice.add(scrollPane, BorderLayout.CENTER);
        choice.setVisible(true); // Display the choice window
        choice.setResizable(false);

        return 2 - index[0] / 3; // Return the chosen index
    }




    public boolean peng_button(boolean judge, int card, int computerNumber) {
        // Configure the Peng button appearance
        peng.setBorderPainted(false);
        peng.setFocusPainted(false);
        peng.setContentAreaFilled(false);

        final boolean[] result = {false}; // Result to determine if Peng is chosen
        CountDownLatch latch = new CountDownLatch(1); // Latch to wait for user action

        // Remove existing action listeners from the Peng button
        for (ActionListener al : peng.getActionListeners()) {
            peng.removeActionListener(al);
        }

        if (judge) { // If Peng action is possible
            peng.setVisible(true);
            skipPeng.setVisible(true);
            skipPeng_button(judge); // Call skipPeng_button to set up the skip button

            // Set the icon for the Peng button
            peng.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Peng.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            peng.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Peng is available");
                    result[0] = true; // Set result to true indicating Peng action
                    player.Peng(card, scaledWidth, scaledHeight, gamePanel, 4, addComputerTile);
                    // Perform Peng action

                    // Remove the last tile from the appropriate computer's river
                    if (computerNumber == 1) {
                        computer1.playerRiver.remove(computer1.playerRiver.size() - 1);
                        robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);
                    } else if (computerNumber == 2) {
                        computer2.playerRiver.remove(computer2.playerRiver.size() - 1);
                        robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);
                    } else {
                        computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                        robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                    }

                    // Hide Peng and skip buttons and start the robot play sequence
                    peng.setVisible(false);
                    skipPeng.setVisible(false);
                    startRobotPlaySequence(4);
                    latch.countDown(); // Release the latch
                }
            });
        } else { // If Peng action is not possible
            peng.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Peng_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }
        peng.setBounds(745, 570, 45, 57); // Set the position of the Peng button
        gamePanel.add(peng); // Add the Peng button to the game panel
        peng.requestFocus(); // Request focus for the Ting button

        return result[0]; // Return the result indicating if Peng was chosen
    }

    public boolean gang_button(boolean judge, int card, int computernumber) {
        // Configure the Gang button appearance
        gang.setBorderPainted(false);
        gang.setFocusPainted(false);
        gang.setContentAreaFilled(false);

        final boolean[] result = {false}; // Result to determine if Gang is chosen
        CountDownLatch latch = new CountDownLatch(1); // Latch to wait for user action

        // Remove existing action listeners from the Gang button
        for (ActionListener al : gang.getActionListeners()) {
            gang.removeActionListener(al);
        }

        // If Gang action is possible
        if (judge) {
            gang.setVisible(true);
            skipGang.setVisible(false);
            skipGang_button(judge); // Call skipGang_button to set up the skip button

            // Set the icon for the Gang button
            gang.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Gang.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            gang.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Gang is available");
                    result[0] = true; // Set result to true indicating Gang action

                    player.Gang(card, scaledWidth, scaledHeight, gamePanel, 4, addComputerTile); // Perform Gang action

                    // Remove the last tile from the appropriate computer's river
                    System.out.print("computer2.playerRiver.size() - 1:" + (computer2.playerRiver.size() - 1));
                    if (computernumber == 1) {
                        computer1.playerRiver.remove(computer1.playerRiver.size() - 1);
                        robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);
                    } else if (computernumber == 2) {
                        computer2.playerRiver.remove(computer2.playerRiver.size() - 1);
                        robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);
                    } else {
                        computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                        robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                    }
                    player.gainMajiang();
                    gang.setVisible(false);
                    skipGang.setVisible(false);
                    startRobotPlaySequence(4);
                    latch.countDown(); // Release the latch
                }
            });
            gamePanel.add(gang); // Add the Gang button to the game panel
            return true; // Return true indicating Gang action is possible
        } else {
            // If Gang action is not possible
            gang.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Gang_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }

        gang.setBounds(795, 570, 45, 57); // Set the position of the Gang button
        gamePanel.add(gang); // Add the Gang button to the game panel
        gang.requestFocus(); // Request focus for the Ting button

        return result[0]; // Return the result indicating if Gang was chosen
    }

    public boolean ting_button(ArrayList<Integer> option) {
        // Configure the Ting button appearance
        ting.setBorderPainted(false);
        ting.setFocusPainted(false);
        ting.setContentAreaFilled(false);

        boolean judge = option != null; // Determine if Ting action is possible
        final boolean[] result = {false}; // Result to determine if Ting is chosen
        CountDownLatch latch = new CountDownLatch(1); // Latch to wait for user action

        // Remove existing action listeners from the Ting button
        for (ActionListener al : ting.getActionListeners()) {
            ting.removeActionListener(al);
        }

        if (judge) { // If Ting action is possible
            ting.setVisible(true);
            skipTing.setVisible(true);
            skipTing_button(judge); // Call skipTing_button to set up the skip button

            // Set the icon for the Ting button
            ting.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Ting.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            ting.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Ting is available");
                    int index = ting_choice(option, true); // Get the user's choice for Ting
                    System.out.println("Ting choice index: " + index);
                    result[0] = true; // Set result to true indicating Ting action
                    latch.countDown(); // Release the latch
                    ting.setVisible(false);
                    skipTing.setVisible(false);
                    player.Tinging = true; // Set the player's Tinging state to true
                }
            });
        } else { // If Ting action is not possible
            ting.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Ting_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }
        ting.setBounds(845, 570, 45, 57); // Set the position of the Ting button
        gamePanel.add(ting); // Add the Ting button to the game panel
        ting.requestFocus(); // Request focus for the Ting button

        try {
            latch.await(); // Wait for the button click
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0]; // Return the result indicating if Ting was chosen
    }


    public int ting_choice(ArrayList<Integer> option,boolean isPlayer) {
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
            label.putClientProperty("index", i); // set the index of label;

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel clickedLabel = (JLabel) e.getSource();
                    index[0] = (int) clickedLabel.getClientProperty("index");
                    System.out.println("Clicked tile index: " + index[0]);
                    choice.dispose();
                    latch.countDown();
                    if(isPlayer) {
                        //To delete it from the player's hand it still has to play and replay the hand;
                        player.playerMajiangs.remove(option.get(index[0]));
                        player.Tinging = true;
                        player.playerRiver.add(option.get(index[0]));
                        gameContent.addComputerTile.addTileToRiverX(new ImageIcon(new ImageIcon(tilemap.getTilePath(option.get(index[0]))).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), tileNum, discardStartX, discardStartY, maxTilesPerRow, gamePanel, 4);

                        int next = gameContent.decideNextComputer(option.get(index[0]), computer1, computer2, computer3, player);
                        //decide next computer
                        startRobotPlaySequence(next);
                    }
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
        for (ActionListener al : hu.getActionListeners()) {
            hu.removeActionListener(al);
        }

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
        hu.requestFocus(); // Request focus for the Hu button
        return result[0];
    }

    public int skipChi_button(boolean ifExist) {
        // Set the icon and appearance of the skipChi button
        skipChi.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
        skipChi.setBorderPainted(false);
        skipChi.setFocusPainted(false);
        skipChi.setContentAreaFilled(false);

        int[] result = {50}; // Default result if skip is not chosen
        CountDownLatch latch = new CountDownLatch(1); // Latch to wait for user action

        // Remove existing action listeners from the skipChi button
        for (ActionListener al : skipChi.getActionListeners()) {
            skipChi.removeActionListener(al);
        }

        if (ifExist) { // If skip action is possible
            skipChi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Skip is available");
                    result[0] = 100; // Set result to 100 indicating skip action
                    player.gainMajiang(); // Player draws a new tile
                    listTiles(player.playerMajiangs, startX, startY); // List the player's tiles
                    latch.countDown(); // Release the latch
                    skipChi.setVisible(false); // Hide the skipChi button
                    chi.setVisible(false); // Hide the chi button
                }
            });
        } else { // If skip action is not possible
            skipChi.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo_unable.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
        }
        skipChi.setBounds(945, 570, 42, 55);
        // Set the position of the skipChi button

        gamePanel.add(skipChi);

        return result[0]; // Return the result indicating if skip was chosen
    }

    public int skipPeng_button(boolean ifExist) {
        // Set the icon and appearance of the skipPeng button
        skipPeng.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
        skipPeng.setBorderPainted(false);
        skipPeng.setFocusPainted(false);
        skipPeng.setContentAreaFilled(false);

        int[] result = {50}; // Default result if skip is not chosen
        CountDownLatch latch = new CountDownLatch(1); // Latch to wait for user action

        // Remove existing action listeners from the skipPeng button
        for (ActionListener al : skipPeng.getActionListeners()) {
            skipPeng.removeActionListener(al);
        }

        if (ifExist) { // If skip action is possible
            skipPeng.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Skip is available");
                    result[0] = 100; // Set result to 100, skip action
                    player.gainMajiang(); // Player draws a new tile
                    listTiles(player.playerMajiangs, startX, startY); // List the player's tiles
                    latch.countDown(); // Release the latch
                    skipPeng.setVisible(false);
                    peng.setVisible(false);
                    // Hide the buttons
                }
            });
        } else { // If skip action is not possible
            skipPeng.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo_unable.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
        }
        skipPeng.setBounds(945, 570, 42, 55);

        gamePanel.add(skipPeng);
        skipPeng.requestFocus(); // Request focus for the Ting button

        return result[0]; // Return the result indicating if skip was chosen
    }

    public int skipGang_button(boolean ifExist) {
        // Set the icon and appearance of the skipGang button
        skipGang.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
        skipGang.setBorderPainted(false);
        skipGang.setFocusPainted(false);
        skipGang.setContentAreaFilled(false);

        int[] result = {50}; // Default result if skip is not chosen
        CountDownLatch latch = new CountDownLatch(1); // Latch to wait for user action

        // Remove existing action listeners from the skipGang button
        for (ActionListener al : skipGang.getActionListeners()) {
            skipGang.removeActionListener(al);
        }

        if (ifExist) { // If skip action is possible
            skipGang.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Skip is available");
                    result[0] = 100;
                    player.gainMajiang();
                    listTiles(player.playerMajiangs, startX, startY);
                    latch.countDown();
                    skipGang.setVisible(false);
                    gang.setVisible(false);
                    // Hide the gang buttons
                }
            });
        } else { // If skip action is not possible
            skipGang.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo_unable.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
        }
        skipGang.setBounds(945, 570, 42, 55); // Set the position of the skipGang button

        gamePanel.add(skipGang); // Add the skipGang button to the game panel
        skipGang.requestFocus(); // Request focus for the Ting button

        return result[0]; // Return the result indicating if skip was chosen
    }

    public int skipTing_button(boolean ifExist) {
        // Set the icon and appearance of the skipTing button
        skipTing.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
        skipTing.setBorderPainted(false);
        skipTing.setFocusPainted(false);
        skipTing.setContentAreaFilled(false);

        int[] result = {50}; // Default result if skip is not chosen
        CountDownLatch latch = new CountDownLatch(1); // Latch to wait for user action

        // Remove existing action listeners from the skipTing button
        for (ActionListener al : skipTing.getActionListeners()) {
            skipTing.removeActionListener(al);
        }

        if (ifExist) { // If skip action is possible
            skipTing.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Skip is available");
                    result[0] = 100;
                    player.gainMajiang();
                    listTiles(player.playerMajiangs, startX, startY);
                    latch.countDown();
                    skipTing.setVisible(false); // Hide the skipTing button
                    ting.setVisible(false); // Hide the ting button
                }
            });
        } else { // If skip action is not possible
            skipTing.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo_unable.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
        }
        skipTing.setBounds(945, 570, 42, 55); // Set the position of the skipTing button

        gamePanel.add(skipTing); // Add the skipTing button to the game panel
        skipTing.requestFocus(); // Request focus for the Ting button

        return result[0]; // Return the result indicating if skip was chosen
    }


////////////////////////add or delete tiles/////////////////////////////////////////////////////////////////////////

    int startX = 230; //the position of use's tiles in hand
    int startY = 640;
    int maxTilesPerRow = 12; // maximum tiles to display in one row
    int discardStartX = 435; // initial position to put disposed cards
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
                    canClick = false;  // Forbid clicking again until the operation is complete
                    tileLabel.setEnabled(false);  // Only one card can be discarded per turn

                    JLabel clickedTile = (JLabel) e.getSource();  // Gets the card that was clicked
                    ImageIcon tileIcon = (ImageIcon) clickedTile.getIcon();
                    int tileNum = (int) clickedTile.getClientProperty("tileNumber");  // Gets the number of the card the user clicked on
                    cardToDiscard = tileNum;

                    // add card to river
                    player.playerRiver.add(cardToDiscard);
                    gameContent.addComputerTile.addTileToRiverX(tileIcon, tileNum, discardStartX, discardStartY, maxTilesPerRow, gamePanel,4);

                    // Remove the clicked card and re-list the remaining cards
                    tileNumber.remove(Integer.valueOf(tileNum));
                    Collections.sort(tileNumber);
                    listTiles(tileNumber, startX, startY);
                    int next=gameContent.decideNextComputer(tileNum,computer1,computer2,computer3,player);
                    gameContent.addComputerTile.addTileToRiverX(tileIcon, tileNum, discardStartX, discardStartY, maxTilesPerRow, gamePanel,4);
                    startRobotPlaySequence(next);  // Start the robot play sequence

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
                if(ShuffleMajiang.maJiangs.isEmpty()){
                    System.out.println("no card, draw");
                    drawTileWindow();
                    timer.cancel();
                    return;
                }
                if (robotIndex % 4!= 0) {
                    System.out.println(robotIndex);

                    // computer1 turn
                    if (robotIndex % 4 == 1) {
                        System.out.println("computer1's tiles size" + computer1.playerMajiangs.size());
                        addComputerTile.addComputer1Tile(computer1.playerMajiangs.size(), gamePanel);
                        boolean computer1Hu = computer1.isHu(computer1.playerMajiangs);
                        if ( computer1Hu) {
                            computer1.isHu = true;
                            System.out.println("computer1 can hu");
                            timer.cancel();
                            LossWindow();
                            return;
                        }
                        else if (computer1.isTing()&&!computer1.Tinging) {
                            computer1.isTing = true;
                            System.out.println("computer1 can ting");

                        }

                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                int card;
                                if(computer1.Tinging){
                                    addComputerTile.addComputer1Tile(computer1.playerMajiangs.size(), gamePanel);
                                    card = computer1.discardAfterTing();
                                    String tilePath = tilemap.getTilePath(card);
                                    addComputerTile.addTileToRiverY(new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), card, discardStartX, discardStartY, maxTilesPerRow, gamePanel, 1);
                                    addComputerTile.addComputer1Tile(computer1.playerMajiangs.size(), gamePanel);
                                }else if(computer1.isTing&&!computer1.Tinging){
                                    Random random=new Random();
                                    int index =random.nextInt(computer1.TingThrowTiles.size()-1);
                                    card=computer1.discardMajiang(computer1.playerMajiangs.indexOf(computer1.TingThrowTiles.get(index)));
                                    computer1.Tinging=true;
                                }else{
                                    card = computer1.discardMajiang(computer1.nextCard());
                                }

                                //Replay the player's hand after throwing
                                addComputerTile.addComputer1Tile(computer1.playerMajiangs.size(), gamePanel);
                                robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);

                                if (computer2.isGang(card)) {
                                    System.out.println("computer2 gang computer1's tile");
                                    computer2.Gang(card, scaledWidth, scaledHeight, gamePanel, 2, addComputerTile);
                                    robotIndex = robotIndex + 1;
                                    computer1.playerRiver.remove(computer1.playerRiver.size() - 1);
                                    computer2.gainMajiang();
                                    robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);
                                    gamePanel.repaint();
                                } else if (computer3.isGang(card)) {
                                    System.out.println("computer3 gang computer1's tile");
                                    computer3.Gang(card, scaledWidth, scaledHeight, gamePanel, 3, addComputerTile);
                                    robotIndex = robotIndex + 2;
                                    addComputerTile.removeRiverTile(gamePanel,1);
                                    computer1.playerRiver.remove(computer1.playerRiver.size() - 1);
                                    computer3.gainMajiang();
                                    robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);
                                    gamePanel.repaint();
                                } else if (player.isGang(card)) {
                                    System.out.println("player can gang");
                                    gang_button(true,card,1);
                                    timer.cancel();
                                } else if (computer2.isPeng(card)&&!computer2.Tinging) {
                                    System.out.println("computer2 peng computer1's tile");
                                    computer2.Peng(card, scaledWidth, scaledHeight, gamePanel, 2, addComputerTile);
                                    robotIndex = robotIndex + 1;
                                    computer1.playerRiver.remove(computer1.playerRiver.size() - 1);
                                    robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);
                                } else if (computer3.isPeng(card)&&!computer3.Tinging) {
                                    System.out.println("computer3 peng computer1's tile");
                                    computer3.Peng(card, scaledWidth, scaledHeight, gamePanel, 3, addComputerTile);
                                    robotIndex = robotIndex + 2;
                                    computer1.playerRiver.remove(computer1.playerRiver.size() - 1);
                                    robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);
                                } else if (player.isPeng(card)&&!player.Tinging) {
                                    System.out.println("player can peng");
                                    peng_button(true,card,4);
                                    timer.cancel();
                                } else if (!computer2.isChi(card).isEmpty()&&!computer2.Tinging) {
                                    System.out.println("computer2 chi computer1's tile");
                                    computer2.Chi(card,gameWindow.scaledWidth,gameWindow.scaledHeight,gameWindow.gamePanel,2,addComputerTile);
                                    robotIndex = robotIndex + 1;
                                    computer1.playerRiver.remove(computer1.playerRiver.size() - 1);
                                    robotPlayTile(computer1.getPlayerMajiangs(), 740, 210 + 260, Boolean.FALSE, 1, card);
                                } else {
                                    robotIndex = robotIndex + 1;
                                    computer2.gainMajiang();
                                    System.out.println("next com2");
                                }
                            }
                        }, 1000); // delay 1 sec
                    }

                    // computer2 turn
                    else if (robotIndex % 4 == 2) {
                        System.out.println("computer2's tiles size" + computer2.playerMajiangs.size());
                        addComputerTile.addComputer2Tile(computer2.playerMajiangs.size(), gamePanel);
                        boolean computer2Hu = computer2.isHu(computer2.playerMajiangs);

                        if (computer2Hu) {
                            computer2.isHu = true;
                            System.out.println("computer2 can hu");
                            LossWindow();
                            timer.cancel();
                            return;
                        }
                        else if (computer2.isTing()&&!computer2.Tinging) {
                            computer2.isTing = true;
                            System.out.println("computer2 can ting");
                        }

                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                int card;
                                if(computer2.Tinging){
                                    addComputerTile.addComputer2Tile(computer2.playerMajiangs.size(), gamePanel);
                                    card = computer2.discardAfterTing();
                                    String tilePath = tilemap.getTilePath(card);
                                    addComputerTile.addTileToRiverX(new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), card, discardStartX, discardStartY, maxTilesPerRow, gamePanel, 2);
                                    addComputerTile.addComputer2Tile(computer2.playerMajiangs.size(), gamePanel);

                                }else if(computer2.isTing&&!computer2.Tinging){
                                    Random random=new Random();
                                    int index =random.nextInt(computer2.TingThrowTiles.size()-1);
                                    card=computer2.discardMajiang(computer2.playerMajiangs.indexOf(computer2.TingThrowTiles.get(index)));
                                    computer2.Tinging=true;
                                }else{
                                    card = computer2.discardMajiang(computer1.nextCard());
                                }

                                //Replay the player's hand after throwing
                                addComputerTile.addComputer2Tile(computer2.playerMajiangs.size(), gamePanel);
                                robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);
                                if (computer3.isGang(card)) {
                                    computer3.Gang(card, scaledWidth, scaledHeight, gamePanel, 3, addComputerTile);
                                    System.out.println("computer2 gang computer3's tile");
                                    robotIndex = robotIndex + 1;
                                    computer2.playerRiver.remove(computer2.playerRiver.size() - 1);
                                    computer3.gainMajiang();
                                    robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);

                                } else if (player.isGang(card)) {
                                    System.out.println("player can gang");
                                    gang_button(true,card,4);
                                    timer.cancel();

                                } else if (computer1.isGang(card)) {
                                    computer1.Gang(card, scaledWidth, scaledHeight, gamePanel, 1, addComputerTile);
                                    System.out.println("computer1 gang computer2's tile");
                                    robotIndex = robotIndex + 3;
                                    computer2.playerRiver.remove(computer2.playerRiver.size() - 1);
                                    computer1.gainMajiang();
                                    robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);

                                } else if (computer3.isPeng(card)&&!computer3.Tinging) {
                                    computer3.Peng(card, scaledWidth, scaledHeight, gamePanel, 3, addComputerTile);
                                    System.out.println("computer3 peng computer2's tile");
                                    robotIndex = robotIndex + 1;
                                    computer2.playerRiver.remove(computer2.playerRiver.size() - 1);
                                    robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);

                                } else if (player.isPeng(card)&&!player.Tinging) {
                                    peng_button(true,card,2);
                                    System.out.println("player can peng");
                                    timer.cancel();
                                } else if (computer1.isPeng(card)&&!computer1.Tinging) {
                                    computer1.Peng(card, scaledWidth, scaledHeight, gamePanel, 1, addComputerTile);
                                    System.out.println("computer1 peng computer2's tile");                                    robotIndex = robotIndex + 3;
                                    computer2.playerRiver.remove(computer2.playerRiver.size() - 1);
                                    robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);

                                } else if (!computer3.isChi(card).isEmpty()&&!computer3.Tinging) {
                                    computer3.Chi(card,gameWindow.scaledWidth,gameWindow.scaledHeight,gameWindow.gamePanel,3,addComputerTile);
                                    System.out.println("computer3 chi computer1's tile");
                                    robotIndex = robotIndex + 1;
                                    computer2.playerRiver.remove(computer2.playerRiver.size() - 1);
                                    System.out.println(computer3.cardsToDisplay.size()+"chi23 ctd");
                                    robotPlayTile(computer2.getPlayerMajiangs(), 678, 200, Boolean.TRUE, 2, card);

                                } else {
                                    robotIndex = robotIndex + 1;
                                    computer3.gainMajiang();
                                    System.out.println("next player is computer3");
                                }

                            }
                        }, 1000); // delay 1 sec
                    }

                    // computer3 turn
                    else if (robotIndex % 4 == 3) {
                        System.out.println("computer3's tiles size" + computer3.playerMajiangs.size());
                        addComputerTile.addComputer3Tile(computer3.playerMajiangs.size(), gamePanel);
                        boolean computer3Hu = computer3.isHu(computer3.playerMajiangs);
                        if (computer3Hu) {
                            computer3.isHu = true;
                            System.out.println("computer3 can hu");
                            LossWindow();
                            timer.cancel();
                            return;
                        }
                        else if (computer3.isTing()&&!computer3.Tinging) {
                            computer3.isTing = true;
                            System.out.println("computer3 can ting");
                        }

                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                int card;
                                if(computer3.Tinging){
                                    addComputerTile.addComputer3Tile(computer3.playerMajiangs.size(), gamePanel);
                                    card = computer3.discardAfterTing();
                                    String tilePath = tilemap.getTilePath(card);
                                    addComputerTile.addTileToRiverY(new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), card, discardStartX, discardStartY, maxTilesPerRow, gamePanel, 3);
                                    addComputerTile.addComputer3Tile(computer3.playerMajiangs.size(), gamePanel);
                                }else if(computer3.isTing&&!computer3.Tinging){
                                    Random random=new Random();
                                    int index =random.nextInt(computer3.TingThrowTiles.size()-1);
                                    card=computer3.discardMajiang(computer3.playerMajiangs.indexOf(computer3.TingThrowTiles.get(index)));
                                    computer3.Tinging=true;
                                }else{
                                    card = computer3.discardMajiang(computer3.nextCard());
                                }

                                //Replay the player's hand after throwing
                                addComputerTile.addComputer3Tile(computer3.playerMajiangs.size(), gamePanel);
                                robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);

                                if (player.isGang(card)) {
                                    gang_button(true,card,3);
                                    timer.cancel();

                                } else if (computer1.isGang(card)) {
                                    computer1.Gang(card, scaledWidth, scaledHeight, gamePanel, 1, addComputerTile);
                                    System.out.println("player can gang");
                                    robotIndex = robotIndex + 2;
                                    computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                                    computer1.gainMajiang();
                                    robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);

                                } else if (computer2.isGang(card)) {
                                    computer2.Gang(card, scaledWidth, scaledHeight, gamePanel, 2, addComputerTile);
                                    System.out.println("computer1 gang computer3's card");
                                    robotIndex = robotIndex + 3;
                                    computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                                    computer2.gainMajiang();
                                    robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);

                                } else if (player.isPeng(card)&&!player.Tinging) {
                                    peng_button(true,card,3);
                                    System.out.println("player can peng");
                                    timer.cancel();
                                } else if (computer1.isPeng(card)&&!computer1.Tinging) {
                                    computer1.Peng(card, scaledWidth, scaledHeight, gamePanel, 1, addComputerTile);
                                    robotIndex = robotIndex + 2;
                                    computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                                    robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                                    System.out.println("computer1 peng computer3's tile");
                                } else if (computer2.isPeng(card)&&!computer2.Tinging) {
                                    computer2.Peng(card, scaledWidth, scaledHeight, gamePanel, 2, addComputerTile);
                                    robotIndex = robotIndex + 3;
                                    computer3.playerRiver.remove(computer3.playerRiver.size() - 1);
                                    robotPlayTile(computer3.getPlayerMajiangs(), 380, 210, Boolean.FALSE, 3, card);
                                    System.out.println("computer2 peng computer3's tile");

                                } else if (!player.isChi(card).isEmpty()&&!player.Tinging) {
                                    System.out.println("Players can eat\n");
                                    chi_button(player, player.isChi(card),card);
                                    listTiles(player.playerMajiangs, startX, startY);
                                    robotIndex = robotIndex + 1;
                                } else {
                                    robotIndex = robotIndex + 1;
                                    player.gainMajiang();
                                    listTiles(player.playerMajiangs, startX, startY);
                                    System.out.println("next player is human player");
                                }

                            }
                        }, 1000); // delay 1 sec
                    }
                }

                // player's turn
                else {
                    System.out.println("player's tiles size"+player.playerMajiangs.size());
                    listTiles(player.playerMajiangs, startX, startY);
                    boolean playerHu = player.isHu(player.playerMajiangs);
                    if (playerHu) {
                        player.isHu = true;
                        hu_button(player.isHu);
                        System.out.println("player hu");
                        timer.cancel();
                    }
                    else if (player.Tinging&&!playerHu) {
                        listTiles(player.playerMajiangs, startX, startY);
                        int card = player.discardAfterTing();
                        String tilePath = tilemap.getTilePath(card);
                        addComputerTile.addTileToRiverX(new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH)), card, discardStartX, discardStartY, maxTilesPerRow, gamePanel, 4);
                        listTiles(player.playerMajiangs, startX, startY);
                        System.out.println("player finish automatically discard tile");
                        timer.cancel(); // stop timer
                        int next=gameContent.decideNextComputer(card,computer1,computer2,computer3,player);
                        startRobotPlaySequence(next);  // Start the robot play sequence

                    }
                    else if (player.isTing()) {
                        System.out.println("player can ting");
                        ting_button(player.TingThrowTiles);
                        timer.cancel();
                    } else {
                        canClick = true;
                        listTiles(player.playerMajiangs, startX, startY);
                        timer.cancel();
                    }

                }
            }
        };
        timer.schedule(robotPlayTask,2000,2000);  // Execute the task every 2 seconds
    }

    //Update the cards of each player river on the table

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
    private void drawTileWindow(){
        JFrame draw = new JFrame("Game result");
        draw.setSize(480, 210);
        draw.setLocationRelativeTo(null);
        draw.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        draw.setLayout(new BorderLayout());

        JLabel hint = new JLabel("<html><br> Draw! <br><br>");
        hint.setFont(new Font("Consolas", Font.BOLD, 24));
        hint.setForeground(Color.GRAY);
        hint.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel hintPanel = new JPanel(new BorderLayout());
        hintPanel.add(hint, BorderLayout.SOUTH);
        draw.add(hintPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        mainPanel.setOpaque(false);

        // Main Menu Button
        JButton menuButton = new JButton("Main Menu");
        menuButton.setFocusPainted(false);
        Font backFont = new Font("Arial", Font.BOLD, 24);
        menuButton.setSize(80, 80);
        menuButton.setFont(backFont);
        menuButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.dispose();
                gameWindow.dispose(); // Close the game window (assumes the game window is the top-level ancestor)

                new MainMenuWindow(); // Opens the main menu window
            }
        });
        mainPanel.add(menuButton);

        // Restart Button
        JButton restartButton = new JButton("Restart Game");
        restartButton.setFocusPainted(false);
        Font restartFont = new Font("Arial", Font.BOLD, 24);
        restartButton.setSize(80, 80);
        restartButton.setFont(restartFont);
        restartButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.dispose();
                gameWindow.dispose(); // Close the game window (assumes the game window is the top-level ancestor)
                try {
                    gameContent.aGame(); // Starts a new game
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        mainPanel.add(restartButton);

        draw.add(mainPanel, BorderLayout.CENTER);
        draw.setVisible(true);
    }


    private void LossWindow(){
        JFrame loss = new JFrame("Game result");
        loss.setSize(480, 210);
        loss.setLocationRelativeTo(null);
        loss.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        loss.setLayout(new BorderLayout());

        JLabel hint = new JLabel("<html><br> You Loss! <br><br>");
        hint.setFont(new Font("Consolas", Font.BOLD, 24));
        hint.setForeground(Color.GRAY);
        hint.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel hintPanel = new JPanel(new BorderLayout());
        hintPanel.add(hint, BorderLayout.SOUTH);
        loss.add(hintPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        mainPanel.setOpaque(false);

        // Main Menu Button
        JButton menuButton = new JButton("Main Menu");
        menuButton.setFocusPainted(false);
        Font backFont = new Font("Arial", Font.BOLD, 24);
        menuButton.setSize(80, 80);
        menuButton.setFont(backFont);
        menuButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loss.dispose();
                gameWindow.dispose(); // Close the game window (assumes the game window is the top-level ancestor)

                new MainMenuWindow(); // Opens the main menu window
            }
        });
        mainPanel.add(menuButton);

        // Restart Button
        JButton restartButton = new JButton("Restart Game");
        restartButton.setFocusPainted(false);
        Font restartFont = new Font("Arial", Font.BOLD, 24);
        restartButton.setSize(80, 80);
        restartButton.setFont(restartFont);
        restartButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loss.dispose();
                gameWindow.dispose(); // Close the game window (assumes the game window is the top-level ancestor)
                try {
                    gameContent.aGame(); // Starts a new game
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        mainPanel.add(restartButton);

        loss.add(mainPanel, BorderLayout.CENTER);
        loss.setVisible(true);
        gamePanel.add(mainPanel);
    }

}

