package window;

import Player.HumanPlayer;
import tiles.Tilemap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;

public class NoviceTutorial extends JFrame {
    private ImagePanel novicePanel;
    public AddComputerTile addComputerTile;
    JButton chi = new JButton();
    JButton peng = new JButton();
    JButton gang = new JButton();
    JButton ting = new JButton();
    JButton skip = new JButton();

    public static void main(String[] args) {
        new NoviceTutorial();
    }

    public NoviceTutorial() {
        //ArrayList<Integer> maJiang = new ArrayList<>(Arrays.asList(11, 11, 14,15,18,23,23,25,31,34,35,36,43,43,44));
        addComputerTile = new AddComputerTile();
        window_frame();
        exitButtons();
        dice_button();

        this.setVisible(true);
        TutorialDialog();
        DiceButtonDialog();


        ting_button(true);

    }

    private void window_frame() {
        setTitle("Novice Tutorial");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        novicePanel = new ImagePanel("src/window/background/background4.jpg");
        novicePanel.setLayout(null);
        add(novicePanel);
    }


//////////////////////tutorial dialogs////////////////////////////////////////
    private void TutorialDialog() {
        JDialog tutorialDialog = new JDialog(this, "Novice Tutorial", true);
        tutorialDialog.setLayout(new BorderLayout());
        tutorialDialog.setSize(450, 200);
        tutorialDialog.setLocationRelativeTo(this);

        JLabel tutorialLabel = new JLabel("<html>  Welcome to the Novice Tutorial!<br><br>" +
                "  This tutorial will guide you through the basics.<br>" +
                "  Hope you can learn how to play Mahjong quickly :D<br>" + "<br>"+
                "  Click OK to continue.</html>");
        tutorialLabel.setFont(new Font("Arial", Font.BOLD, 16));
        tutorialLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tutorialDialog.add(tutorialLabel, BorderLayout.CENTER);

        JButton okButton = new JButton("OK!!");
        okButton.setFocusPainted(false);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tutorialDialog.dispose(); // close the dialog

            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        tutorialDialog.add(buttonPanel, BorderLayout.SOUTH);

        tutorialDialog.setVisible(true);
    }

    private void  DiceButtonDialog(){
        JOptionPane Dialog = new JOptionPane(
                "<html><font size='4'>You can click 'Throw Dice'↑↑ to decide host in official game: <br>  Click on the 'Throw Dice' button to roll the dice :P<br><br>It will decide the boss for this new game.<br> Let's suppose you are the host, now try to throw your tile.</html>",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION);

        JDialog diceButtonDialog = Dialog.createDialog("First Step");
        diceButtonDialog.setBounds(570, 150,400,240);
        Dialog.setOptions(new Object[]{});  //remove the original button

        JButton okButton = new JButton("OK!!");
        okButton.setFocusPainted(false);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diceButtonDialog.dispose(); // close the dialog

            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        diceButtonDialog.add(buttonPanel, BorderLayout.SOUTH);
        diceButtonDialog.setVisible(true);
    }

    private void Discard1Dialog(){
        JOptionPane Dialog = new JOptionPane(
                "<html><font size='4'>You will get a new tile in each turn.<br><br>Now you have two 1tiao. So let's discard an useless tile.<br><br>Please click 8tiao here. ↓↓</html>",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION);

        JDialog Discard1Dialog = Dialog.createDialog("Discard Step");
        Discard1Dialog.setBounds(360, 500,350,200);
        Dialog.setOptions(new Object[]{});  //remove the original button

        JButton okButton = new JButton("OK!!");
        okButton.setFocusPainted(false);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Discard1Dialog.dispose(); // close the dialog

            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        Discard1Dialog.add(buttonPanel, BorderLayout.SOUTH);
        Discard1Dialog.setVisible(true);
    }

    private void Chi13Dialog(){
        JOptionPane Dialog = new JOptionPane(
                "<html><font size='4'>In Chi performance, you can only get tile from your left player.<br>Left player just throw a 3Tiao tile. <br><br>Now you can do 'Chi' performance! <br><br>Please click 'Chi' button here",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION);

        JDialog Chi13Dialog = Dialog.createDialog("Chi Step");
        Chi13Dialog.setBounds(700, 460,350,200);
        Dialog.setOptions(new Object[]{});  //remove the original button

        JButton okButton = new JButton("OK!!");
        okButton.setFocusPainted(false);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Chi13Dialog.dispose(); // close the dialog

            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        Chi13Dialog.add(buttonPanel, BorderLayout.SOUTH);
        Chi13Dialog.setVisible(true);
    }

    private void ChiThrowDialog(){
        JOptionPane Dialog = new JOptionPane(
                "<html><font size='4'>Please remove 5 Bing.</html>",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION);

        JDialog Chi13Dialog = Dialog.createDialog("Chi Step");
        Chi13Dialog.setBounds(700, 460,350,200);
        Dialog.setOptions(new Object[]{});  //remove the original button

        JButton okButton = new JButton("OK!!");
        okButton.setFocusPainted(false);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Chi13Dialog.dispose(); // close the dialog

            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        Chi13Dialog.add(buttonPanel, BorderLayout.SOUTH);
        Chi13Dialog.setVisible(true);
    }

    private void Peng23Dialog(){
        JOptionPane Dialog = new JOptionPane(
                "<html><font size='4'>In Peng performance, you can get tile from all other players.<br>Now a player throw a 3Bing tile. <br><br>Now you can do 'Peng' performance! <br><br>Please click 'Peng' button here. ↓↓</html>",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION);

        JDialog Peng23Dialog = Dialog.createDialog("Gang Step");
        Peng23Dialog.setBounds(740, 460,350,200);
        Dialog.setOptions(new Object[]{});  //remove the original button

        JButton okButton = new JButton("OK!!");
        okButton.setFocusPainted(false);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Peng23Dialog.dispose(); // close the dialog

            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        Peng23Dialog.add(buttonPanel, BorderLayout.SOUTH);
        Peng23Dialog.setVisible(true);
    }
    private void PengThrowDialog(){
        JOptionPane Dialog = new JOptionPane(
                "<html><font size='4'>Please throw 6 Wan</html>",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION);

        JDialog Peng23Dialog = Dialog.createDialog("Gang Step");
        Peng23Dialog.setBounds(740, 460,350,200);
        Dialog.setOptions(new Object[]{});  //remove the original button

        JButton okButton = new JButton("OK!!");
        okButton.setFocusPainted(false);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Peng23Dialog.dispose(); // close the dialog

            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        Peng23Dialog.add(buttonPanel, BorderLayout.SOUTH);
        Peng23Dialog.setVisible(true);
    }

    private void Gang34Dialog(){
        JOptionPane Dialog = new JOptionPane(
                "<html><font size='4'>In Gang performance, you can get tile from all other players.<br>Now a player throw a 4 Wan tile. <br><br>Please click 'Gang' button here. ↓↓</html>",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION);

        JDialog Gang34Dialog = Dialog.createDialog("Gang Step");
        Gang34Dialog.setBounds(800, 460,350,200);
        Dialog.setOptions(new Object[]{});  //remove the original button

        JButton okButton = new JButton("OK!!");
        okButton.setFocusPainted(false);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gang34Dialog.dispose(); // close the dialog

            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        Gang34Dialog.add(buttonPanel, BorderLayout.SOUTH);
        Gang34Dialog.setVisible(true);
    }

    private void GangThrowDialog(){
        JOptionPane Dialog = new JOptionPane(
                "<html><font size='4'> Please throw 7 Bing.</html>",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION);

        JDialog Gang34Dialog = Dialog.createDialog("Gang Step");
        Gang34Dialog.setBounds(800, 460,350,200);
        Dialog.setOptions(new Object[]{});  //remove the original button

        JButton okButton = new JButton("OK!!");
        okButton.setFocusPainted(false);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gang34Dialog.dispose(); // close the dialog

            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        Gang34Dialog.add(buttonPanel, BorderLayout.SOUTH);
        Gang34Dialog.setVisible(true);
    }


///////////////////////////exit button////////////////////////////////////////
    private void exitButtons() {
        JButton exitButton = new JButton("EXIT");
        Font exitFont = new Font("Arial", Font.BOLD, 24);
        exitButton.setFocusPainted(false);
        exitButton.setFont(exitFont);
        exitButton.setBounds(1100, 0, 100, 50);  //set the button site and size
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame sureWindow = new JFrame("Sure to exit to the main menu?");
                sureWindow.setSize(240, 180);
                sureWindow.setLocationRelativeTo(null);

                JLabel question = new JLabel("<html>Are you sure you want to<br>return to the main menu?</html>");
                question.setFont(new Font("Arial", Font.BOLD, 16));
                question.setHorizontalAlignment(SwingConstants.CENTER);
                question.setBounds(10, 10, 200, 100);
                sureWindow.add(question);

                JButton yesButton = new JButton("YES");
                Font yesFont = new Font("Arial", Font.BOLD, 12);
                yesButton.setFocusPainted(false);
                yesButton.setFont(yesFont);
                yesButton.setBounds(30, 100, 80, 30);
                sureWindow.add(yesButton);
                yesButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        sureWindow.dispose();
                        dispose();
                        new MainMenuWindow();
                    }
                });

                JButton noButton = new JButton("NO");
                Font noFont = new Font("Arial", Font.BOLD, 12);
                noButton.setFocusPainted(false);
                noButton.setFont(noFont);
                noButton.setBounds(120, 100, 80, 30);
                sureWindow.add(noButton);
                noButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        sureWindow.dispose();
                    }
                });
                sureWindow.setLayout(null);
                sureWindow.setVisible(true);
            }
        });
        novicePanel.add(exitButton);
    }


////////////////////////////////dice button///////////////////////////////////////////
        private void dice_button() {
            JButton num = new JButton("THROW DICE");
            Font dice_font = new Font("Arial", Font.BOLD, 24);
            num.setFocusPainted(false);
            num.setFont(dice_font);
            num.setBounds(500, 0, 200, 50);
            novicePanel.add(num);
            // set the button;

            num.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addTileToWindow(maJiang);
                    Timer timer = new Timer();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            SwingUtilities.invokeLater(() -> {
                                novicePanel.remove(num);
                                novicePanel.revalidate();
                                novicePanel.repaint();
                                canClick=true;
                                maJiang.add(11);
                                addTileToWindow(maJiang);
                                Discard1Dialog();
                                //displayMahjongTiles();
                            });
                            timer.cancel();
                        }
                    }, 500);
                }
            });
        }
    // set the dice to choose the host;



    ////////create a player//////////////////////

    Tilemap tilemap = new Tilemap();

    ArrayList<Integer> maJiang = new ArrayList<>(Arrays.asList(11,14,15,18,23,23,25,34,34,34,36,43,43));
    //先摸了11，然后throw18,,,,，上家仍13，chi: (13),14,15，THROW 25,,,,,,,,,,下家扔23，peng:23,23,(23),throw 36,,,,34，gang:34,发27throw27,,,,,hu: add 43

    HumanPlayer aPlayer = new Player.HumanPlayer();
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
        novicePanel.revalidate();
        novicePanel.repaint();
    }


    public void listTiles(ArrayList<Integer> tileNumber, int startX, int startY) {
        if (tileNumber == null) {
            System.out.println("tileNumber is null");
        }

        for (int i = 0; i < novicePanel.getComponentCount(); i++) {
            Component comp = novicePanel.getComponent(i);
            if (comp instanceof JLabel && ((JLabel) comp).getClientProperty("tileNumber") != null) {
                novicePanel.remove(comp);
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
                novicePanel.add(tileLabel);

            }
        }

        novicePanel.revalidate();
        novicePanel.repaint();

    }

    public int cardToDiscard;

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

                    if(tileNum==18||tileNum==25||tileNum==27||tileNum==36) {
                        // add card to river
                        aPlayer.playerRiver.add(cardToDiscard);
                        // Remove the clicked card and re-list the remaining cards
                        tileNumber.remove(Integer.valueOf(tileNum));
                    }else{
                        JOptionPane.showMessageDialog(null, "Please follow the guidance ^^", "Message", JOptionPane.INFORMATION_MESSAGE);
                        canClick=true;
                    }
                    Collections.sort(tileNumber);
                    listTiles(tileNumber, startX, startY);
                    addToRiver();
                    testSequence();
                    //addComputerTile.addTileToRiverX(tileIcon, tileNum, discardStartX, discardStartY, maxTilesPerRow, novicePanel,4);

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (canClick) {
                    tileLabel.setLocation(tileLabel.getX(), tileLabel.getY() - 10);
                    novicePanel.revalidate();
                    novicePanel.repaint();
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (canClick) {
                    tileLabel.setLocation(tileLabel.getX(), tileLabel.getY() + 10);
                    novicePanel.revalidate();
                    novicePanel.repaint();
                }
            }
        });

        return tileLabel;
    }

    int newWidth=26;
    int newHeight=38;

    public void addToRiver(){
        for (int i = 0; i < aPlayer.playerRiver.size(); i++) {  //get the list's tile image and show
            int tileNum = aPlayer.playerRiver.get(i);
            String tilePath = tilemap.getTilePath(tileNum);
            ImageIcon tileIcon = new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
            addTileToRiver(tileIcon, tileNum, discardStartX + i * newWidth, discardStartY);
        }
    }
    private void addTileToRiver(ImageIcon tileIcon, int tileNum, int startX, int startY){
        JLabel tileLabel = new JLabel(tileIcon);  // insert the tile images to the window
        tileLabel.setBounds(startX, startY, newWidth, newHeight);
        tileLabel.putClientProperty("tileNumber", tileNum);
        novicePanel.add(tileLabel);
        novicePanel.revalidate();
        novicePanel.repaint();
    }
/////////////////////chi/peng/gang tiles//////////////////////////////
    private void testSequence(){
        if (aPlayer.playerRiver.equals(Arrays.asList(18))){
            Chi13();
         }else if (aPlayer.playerRiver.equals(Arrays.asList(18,25))){
            Peng23();
         }else if (aPlayer.playerRiver.equals(Arrays.asList(18,25,36))){
            Gang34();
         }else if (aPlayer.playerRiver.equals(Arrays.asList(18,25,36,27))){
            maJiang.add(43);
            addTileToWindow(maJiang);
            hu_button(true);
         }else{
            JOptionPane.showMessageDialog(null, "Please follow the guidance ^^", "Message", JOptionPane.INFORMATION_MESSAGE);
            canClick=true;
        }
    }

    private JLabel tileLabel;
    private void Chi13(){
        String tilePath = tilemap.getTilePath(13);
        ImageIcon tileIcon = new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
        Image rotateImage = addComputerTile.rotateImage(tileIcon.getImage(), 90);

        tileLabel = new JLabel(new ImageIcon(rotateImage));
        tileLabel.setBounds(370,200,38,26);
        novicePanel.add(tileLabel);

        chi_button(13);
        Chi13Dialog();
    }

    private void Peng23(){
        String tilePath = tilemap.getTilePath(23);
        ImageIcon tileIcon = new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
        Image rotateImage = addComputerTile.rotateImage(tileIcon.getImage(), -90);

        tileLabel = new JLabel(new ImageIcon(rotateImage));
        tileLabel.setBounds(830,200,38,26);
        novicePanel.add(tileLabel);

        peng_button(23);
        Peng23Dialog();
    }

    private void Gang34(){
        String tilePath = tilemap.getTilePath(34);
        ImageIcon tileIcon = new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
        Image rotateImage = addComputerTile.rotateImage(tileIcon.getImage(), 180);

        tileLabel = new JLabel(new ImageIcon(rotateImage));
        tileLabel.setBounds(600,100,26,38);
        novicePanel.add(tileLabel);

        gang_button(34);
        Gang34Dialog();
    }


//////////////////////////////display discarded tiles////////////////////////////////
    public void testDisplay(String type){

        novicePanel.setLayout(null);
        if (type == "Chi"){
            for (int i = 13; i <= 15; i++) {
                String tilePath = tilemap.getTilePath(i);
                ImageIcon tileIcon = new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
                JLabel label = new JLabel(tileIcon);
                label.setBounds(250 + 26 * (i-13), 570,newWidth, newHeight);
                maJiang.remove(Integer.valueOf(i));
                novicePanel.add(label);
            }
        }else if(type == "Peng"){
            for (int i = 0; i < 3; i++) {
                String tilePath = tilemap.getTilePath(23);
                ImageIcon tileIcon = new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
                JLabel label = new JLabel(tileIcon);
                label.setBounds(350 + 26 * i, 570,newWidth, newHeight);
                maJiang.remove(Integer.valueOf(23));
                novicePanel.add(label);
            }
        }else if (type == "Gang"){
            for (int i = 0; i < 4; i++) {
                String tilePath = tilemap.getTilePath(34);
                ImageIcon tileIcon = new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
                JLabel label = new JLabel(tileIcon);
                label.setBounds(450 + 26 * i, 570,newWidth, newHeight);
                maJiang.remove(Integer.valueOf(34));
                novicePanel.add(label);
            }
        }
        novicePanel.revalidate();
        novicePanel.repaint();

    }
    ////////////////////////////////////////////buttons/////////////////////////////////////////
    public void chi_button(int card) {
        // Set the icon for the Chi button
        boolean judge;
        if (card == 13){
            judge = true;
        }else {
            judge = false;
        }

        if (judge) {
            chi.setVisible(true);
            skip.setVisible(true);
            skip_button(judge);

            chi.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Chi.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            chi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    boolean choice = chi_choice(judge); // Call chi_choice to show the choice window
                    if (choice) {
                        // Perform the chi action
                        System.out.println("Perform Chi");

                        chi.setVisible(false);
                        skip.setVisible(false);
                    }
                }
            });
        } else {
            chi.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Chi_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }

        chi.setBorderPainted(false);
        chi.setFocusPainted(false);
        chi.setContentAreaFilled(false);
        chi.setBounds(695, 570, 45, 57); // Set the position of the Chi button

        novicePanel.add(chi); // Add the Chi button to the game panel
    }

    private boolean chi_choice(boolean judge) {

        final boolean[] index = {false};
        CountDownLatch latch = new CountDownLatch(1);

        if (judge) {
            JFrame choiceFrame = new JFrame("Chi Choice");
            choiceFrame.setSize(300, 200);
            choiceFrame.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 3));

            // Add labels for tiles 13, 14, 15
            for (int i = 13; i <= 15; i++) {
                String tilePath = tilemap.getTilePath(i);
                ImageIcon icon = new ImageIcon(new ImageIcon(tilePath).getImage().getScaledInstance(132 / 3, 191 / 3, Image.SCALE_SMOOTH));
                JLabel label = new JLabel(icon);
                panel.add(label);
            }

            // Add mouse listener to handle selection
            panel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    index[0] = true; // Set the choice to 1 if any tile is clicked
                    testDisplay("Chi");
                    canClick = true;
                    novicePanel.remove(tileLabel);
                    novicePanel.revalidate();
                    novicePanel.repaint();
                    ChiThrowDialog();
                    choiceFrame.dispose();// Close the choice window
                    latch.countDown();
                    chi.setVisible(false);

                }
            });

            choiceFrame.add(panel);
            choiceFrame.setVisible(true);
        }

        return index[0];
    }



    public void peng_button(int card) {
        // Configure the Peng button appearance
        peng.setBorderPainted(false);
        peng.setFocusPainted(false);
        peng.setContentAreaFilled(false);

        boolean judge;

        if(card == 23){
            judge = true;
        }else{
            judge = false;
        }

        if (judge) { // If Peng action is possible
            peng.setVisible(true);
            skip.setVisible(true);
            skip_button(judge); // Call skipPeng_button to set up the skip button

            // Set the icon for the Peng button
            peng.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Peng.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            peng.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Peng is available");
                    testDisplay("Peng");
                    canClick=true;
                    novicePanel.remove(tileLabel);
                    novicePanel.revalidate();
                    novicePanel.repaint();
                    PengThrowDialog();
                    // Hide Peng and skip buttons and start the robot play sequence
                    peng.setVisible(false);

                }
            });
        } else { // If Peng action is not possible
            peng.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Peng_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }
        peng.setBounds(745, 570, 45, 57); // Set the position of the Peng button
        novicePanel.add(peng); // Add the Peng button to the game panel
        peng.requestFocus(); // Request focus for the Ting button

    }

    public void gang_button(int card) {
        // Configure the Gang button appearance
        gang.setBorderPainted(false);
        gang.setFocusPainted(false);
        gang.setContentAreaFilled(false);


        boolean judge;

        if(card == 34){
            judge = true;
        }else{
            judge = false;
        }

        // If Gang action is possible
        if (judge) {
            gang.setVisible(true);
            skip.setVisible(true);
            skip_button(judge); // Call skipGang_button to set up the skip button

            // Set the icon for the Gang button
            gang.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Gang.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
            gang.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Gang is available");
                    testDisplay("Gang");

                    novicePanel.remove(tileLabel);
                    novicePanel.revalidate();
                    novicePanel.repaint();
                    maJiang.add(27);
                    addTileToWindow(maJiang);
                    canClick=true;
                    GangThrowDialog();

                    gang.setVisible(false);

                }
            });
            novicePanel.add(gang); // Add the Gang button to the game panel

        } else {
            // If Gang action is not possible
            gang.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Gang_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }

        gang.setBounds(795, 570, 45, 57); // Set the position of the Gang button
        novicePanel.add(gang); // Add the Gang button to the game panel
        gang.requestFocus(); // Request focus for the Ting button


    }

    public void ting_button(boolean judge) {
        // Configure the Ting button appearance
        ting.setBorderPainted(false);
        ting.setFocusPainted(false);
        ting.setContentAreaFilled(false);


        if (judge) { // If Ting action is possible
            ting.setVisible(true);
            skip.setVisible(true);
            skip_button(judge); // Call skipTing_button to set up the skip button
            ting.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Ting.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));

        } else { // If Ting action is not possible
            ting.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Ting_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }
        ting.setBounds(845, 570, 45, 57); // Set the position of the Ting button
        novicePanel.add(ting); // Add the Ting button to the game panel

    }
    public void skip_button(boolean ifExist) {
        // Set the icon and appearance of the skipChi button
        skip.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
        skip.setBorderPainted(false);
        skip.setFocusPainted(false);
        skip.setContentAreaFilled(false);

        // Remove existing action listeners from the skipChi button

        if (ifExist) { // If skip action is possible
            skip.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Skip is available");
                    skip.setVisible(false); // Hide the skipChi button

                }
            });
        } else { // If skip action is not possible
            skip.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Guo_unable.png").getImage().getScaledInstance(42, 55, Image.SCALE_SMOOTH)));
        }
        skip.setBounds(945, 570, 42, 55);
        // Set the position of the skipChi button

        novicePanel.add(skip);
    }

    public void hu_button(boolean judge) {
        JButton hu = new JButton();
        hu.setBorderPainted(false);
        hu.setFocusPainted(false);
        hu.setContentAreaFilled(false);

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

                    JButton mainMenuButton = new JButton("Main Menu");
                    mainMenuButton.setFont(new Font("Arial", Font.BOLD, 18));
                    mainMenuButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Close the Hu window
                            scoreFrame.dispose();
                            dispose();
                            // Here you should add the code to return to the main menu
                            new MainMenuWindow();
                        }
                    });

                    // Create a panel for the button and add it to the frame
                    JPanel buttonPanel = new JPanel();
                    buttonPanel.add(mainMenuButton);
                    scoreFrame.add(buttonPanel, BorderLayout.SOUTH);

                    scoreFrame.setVisible(true);

                }
            });

        } else {
            hu.setIcon(new ImageIcon(new ImageIcon("src/PromtButton/Hu_unable.png").getImage().getScaledInstance(45, 57, Image.SCALE_SMOOTH)));
        }
        hu.setBounds(895, 570, 45, 57);

        novicePanel.add(hu);
    }




}