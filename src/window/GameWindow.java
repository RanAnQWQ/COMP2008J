package window;

import tiles.Tilemap;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class GameWindow extends JFrame {
    private ImagePanel gamePanel;
    private Tilemap tilemap;

    public static void main(String[] args) {
        new GameWindow();
    }
    Random number = new Random();
    public int sum = number.nextInt(12) + 2;


    public GameWindow() {
        helpButtons();
        window_frame();
        dice_button(sum);

//        tilemap = new Tilemap();
//        addTileToWindow(111);
        this.setVisible(true);
        chooseAvatar();

        headShot_M("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\bear.png");
        headShot_M("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\cat.png");
        headShot_M("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\cow.png");
        // Add the headShots of 3 machine players;


    }

    private void window_frame(){
        setTitle("Game Window");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        gamePanel = new ImagePanel("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\window\\background2.jpg");
        add(gamePanel);
    }

//////////////////////profile photo///////////////////////////////
    private void headShot_M(String imagePath){
        ImageIcon com;
        Image scaledCom;
        ImageIcon scaledComIcon;
        JLabel headLabel;
        //create the image object;

        JLabel playerLabel;
        //create player label to display name;

        com = new ImageIcon(imagePath);
        scaledCom = com.getImage().getScaledInstance(com.getIconWidth() / 9, com.getIconHeight() / 9, Image.SCALE_DEFAULT);
        scaledComIcon= new ImageIcon(scaledCom);
        headLabel = new JLabel(scaledComIcon);
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setVerticalAlignment(SwingConstants.CENTER);
        // adjust the image;

        // Create a JLabel to display "player"
        playerLabel = new JLabel("Player");
        playerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create a JPanel to hold the image and "Player" label
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(headLabel, BorderLayout.CENTER);
        imagePanel.add(playerLabel, BorderLayout.SOUTH);

        gamePanel.setLayout(null);

        switch (imagePath){
            case "C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\bear.png":
                // set the "North" headshot;

                int labelWidth1 = scaledComIcon.getIconWidth();
                int labelHeight1 = scaledComIcon.getIconHeight();
                int panelWidth1 = gamePanel.getWidth();
                int panelHeight1 = gamePanel.getHeight();
                int x_1 = ((panelWidth1 - labelWidth1) / 64) * 47;
                int y_1 = ((panelHeight1 - labelHeight1) / 64) * 25;
                headLabel.setBounds(x_1, y_1, labelWidth1, labelHeight1);
                imagePanel.setBounds(x_1, y_1, labelWidth1, labelHeight1 + 20);
                // Increase height to accommodate "Player" label

                gamePanel.add(headLabel);
                gamePanel.add(imagePanel);


                break;

            case "C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\cat.png":
                //set the "South" headshot;

                int labelWidth2 = scaledComIcon.getIconWidth();
                int labelHeight2 = scaledComIcon.getIconHeight();
                int panelWidth2 = gamePanel.getWidth();
                int panelHeight2 = gamePanel.getHeight();
                int x_2 = ((panelWidth2 - labelWidth2) / 64) * 21;
                int y_2 = ((panelHeight2 - labelHeight2) / 64) * 38;
                headLabel.setBounds(x_2, y_2, labelWidth2, labelHeight2);
                imagePanel.setBounds(x_2, y_2, labelWidth2, labelHeight2 + 20); // Increase height to accommodate "Player" label
                gamePanel.add(headLabel);
                gamePanel.add(imagePanel);

                break;

            case "C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\cow.png":
                //set the "West" headshot;

                int labelWidth3 = scaledComIcon.getIconWidth();
                int labelHeight3 = scaledComIcon.getIconHeight();
                int panelWidth3 = gamePanel.getWidth();
                int panelHeight3 = gamePanel.getHeight();
                int x_3 = ((panelWidth3 - labelWidth3) / 64) * 30;
                int y_3 = ((panelHeight3 - labelHeight3) / 64) * 15;
                headLabel.setBounds(x_3, y_3, labelWidth3, labelHeight3);
                imagePanel.setBounds(x_3, y_3, labelWidth3, labelHeight3 + 20); // Increase height to accommodate "Player" label
                gamePanel.add(headLabel);
                gamePanel.add(imagePanel);
                break;

        }

    }



    private void chooseAvatar(){
        JDialog avatarDialog= new JDialog(this,"Player Settings",true);
        avatarDialog.setSize(600,400);
        avatarDialog.setLocationRelativeTo(this);
        avatarDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel avatarPanel = new JPanel();
        avatarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // 添加文字标识
        JLabel chooseLabel = new JLabel("Choose your headshot:                                                                                                     ");
        avatarPanel.add(chooseLabel);


        JButton crocodile = new JButton();
        crocodile.setBorderPainted(false); //set the button frame invisible;
        crocodile.setFocusPainted(false);
        crocodile.setContentAreaFilled(false);
        crocodile.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\crocodile.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));

        JButton fox = new JButton();
        fox.setBorderPainted(false);
        fox.setFocusPainted(false);
        fox.setContentAreaFilled(false);
        fox.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\fox.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));

        JButton hamster = new JButton();
        hamster.setBorderPainted(false);
        hamster.setFocusPainted(false);
        hamster.setContentAreaFilled(false);
        hamster.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\hamster.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));

        JButton hedgehog = new JButton();
        hedgehog.setBorderPainted(false);
        hedgehog.setFocusPainted(false);
        hedgehog.setContentAreaFilled(false);
        hedgehog.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\hedgehog.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));

        JButton rabbit = new JButton();
        rabbit.setBorderPainted(false);
        rabbit.setFocusPainted(false);
        rabbit.setContentAreaFilled(false);
        rabbit.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\rabbit.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));


        crocodile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Image scaledArrow;
                updateAvatar("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\crocodile.png");
                avatarDialog.dispose();
            }
        });

        fox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Image scaledArrow;
                updateAvatar("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\fox.png");
                avatarDialog.dispose();
            }
        });

        hamster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Image scaledArrow;
                updateAvatar("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\hamster.png");
                avatarDialog.dispose();
            }
        });

        hedgehog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Image scaledArrow;
                updateAvatar("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\hedgehog.png");
                avatarDialog.dispose();
            }
        });

        rabbit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Image scaledArrow;
                updateAvatar("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\profilephoto\\rabbit.png");
                avatarDialog.dispose();
            }
        });

        avatarPanel.add(crocodile);
        avatarPanel.add(fox);
        avatarPanel.add(hamster);
        avatarPanel.add(hedgehog);
        avatarPanel.add(rabbit);

        avatarDialog.add(avatarPanel);
        avatarDialog.setVisible(true);
    }

    private void updateAvatar(String imagePath){
        ImageIcon avatarIcon = new ImageIcon(imagePath);
        Image originalImage = avatarIcon.getImage();

        int scaledWidth = originalImage.getWidth(null) / 9;
        int scaledHeight = originalImage.getHeight(null) / 9;

        Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel avatarLabel = new JLabel(scaledIcon);

        avatarLabel.setBounds(660, 537, scaledWidth, scaledHeight);

        gamePanel.add(avatarLabel);

        gamePanel.revalidate();
        gamePanel.repaint();
    }

///////////////////////dice button/////////////////////////

    private void dice_button(int sum){

        JButton num = new JButton("THROW DICE");
        Font dice_font = new Font("Arial", Font.BOLD, 24);
        num.setFocusPainted(false);
        num.setFont(dice_font);
        //num.setHorizontalAlignment(SwingConstants.CENTER);
        num.setBounds(700,0,200,50);
        gamePanel.add(num);
        // set the button;

        num.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                sumLabel.setLocation(560,250);
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
                // remove the original button once the label occur;

                Timer timer = new Timer();

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        SwingUtilities.invokeLater(() -> {
                            gamePanel.remove(sumLabel);
                            gamePanel.remove(arrowLabel);
                            revalidate();
                            repaint();
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
                arrowIcon = new ImageIcon("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\window\\ArrowDown.png");
                break;
                // East;

            case 2:
                arrowIcon = new ImageIcon("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\window\\ArrowRight.png");
                break;
                // North;

            case 3:
                arrowIcon = new ImageIcon("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\window\\ArrowTop.png");
                break;
                // West;

            default:
                arrowIcon = new ImageIcon("C:\\Users\\dell\\Desktop\\Majiang\\Majiang\\src\\window\\ArrowLeft.png");
                break;
                // South;
        }
        return arrowIcon;
    }

    // This is the way to choose the host in first game;
    // Set the arrow to choose the host. According to rules, from the East player, counting n times (n is the sum of 2 dices) in counter-clockwise, the last one is the host of this game;

    //---------------------not in first game--------------------

    public String getFirstHost(){
        String host;
        /////////////////////add/////////////////////
        switch (sum % 4){
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
    private void helpButtons() {
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
                        helpWindow.dispose();
                        dispose();
                        new GameWindow().setVisible(true);
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
        add(helpButton);
    }

/////////////////////////add or delete tiles/////////////////////////////////////////////////////////////////////////
    public void addTileToWindow(int tileNum) {
        String tilePath = tilemap.getTilePath(tileNum);
        if(tilePath != null) {
            ImageIcon imageIcon = new ImageIcon(tilePath);
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(imageIcon);

            imageLabel.setBounds(550,350,100,100);//set the size of image
            gamePanel.add(imageLabel);
            gamePanel.revalidate();
            gamePanel.repaint();
        }else {
            System.out.println("Tile not found");
        }
    }
}