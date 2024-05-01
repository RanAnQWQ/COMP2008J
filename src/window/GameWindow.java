package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameWindow extends JFrame {
    private JPanel cardPanel;
    private JLabel label;
    private ImagePanel gamePanel;

    public static int getDiceNum() {
        return diceNum;
    }

    static int diceNum;
    private ImageIcon icon = new ImageIcon("D:\\SHITS\\SE Project\\Majiang1\\Majiang\\src\\window\\background2.jpg");

    public GameWindow() {
        helpButtons();


        setTitle("Game Window");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        gamePanel = new ImagePanel("Majiang/src/window/background2.jpg");
        add(gamePanel);
        setLocationRelativeTo(null);

        dice_button();


        //cardPanel = (JPanel) getContentPane();
        //cardPanel.setOpaque(false);//将面板设置为透明
        label = new JLabel(icon);
        //getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
        label.setBounds(0, 0, 1, 10);
        //cardPanel.setLayout(null);
        gamePanel.add(label);
    }

    private void dice_button(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10)); //3,1,10,10
        buttonPanel.setOpaque(false);

        Random number = new Random();
        int first_dice = number.nextInt(6) + 1;
        int second_dice = number.nextInt(6) + 1;
        int sum = first_dice + second_dice;
        diceNum=sum;
        // set the number of dices;

        JButton num = new JButton("THROW DICE");
        num.setFocusPainted(false);
        Font dice_font = new Font("Arial", Font.BOLD, 24);
        buttonPanel.setFont(dice_font);
        // set the button;

        //num.setToolTipText("Deciding the banker:\n Toss two dice at the same time.\n Following the resulting number, count clockwise from the player,\n and eventually pointing to the banker");

        // 同时抛两个色子，按得到的数字从玩家开始顺时针数，最终指向的就是庄家
        String text= "Deciding the banker: Toss two dice at the same time.";
        JLabel label = new JLabel(text);
        label.setForeground(Color.ORANGE);
        label.setBounds(400, 600, 10, 1);
        Font font = new Font("Arial", Font.BOLD, 12);
        label.setFont(font);
        gamePanel.add(label);


        num.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //gamePanel.remove(label);

                ImageIcon north = new ImageIcon("Majiang/src/window/RightArrow.png");
                Image arrow1 = north.getImage().getScaledInstance(north.getIconWidth()/15, north.getIconHeight()/15, Image.SCALE_DEFAULT);
                ImageIcon set_arr1 = new ImageIcon(arrow1);
                JLabel rightArrowLabel = new JLabel(set_arr1);

                // Calculate the position to display the image in the center of the window
                /*int x = (1200 - set_arr1.getIconWidth()) / 2;
                int y = (800 - set_arr1.getIconHeight()) / 2*/;
                rightArrowLabel.setBounds(600, 400, set_arr1.getIconWidth(), set_arr1.getIconHeight());
                gamePanel.add(rightArrowLabel);

                JLabel sumLabel = new JLabel("" + sum);
                Font label_font = new Font("Arial", Font.BOLD, 30);
                sumLabel.setFont(label_font);
                sumLabel.setBounds(400, 500,100, 50);
                sumLabel.setBackground(Color.WHITE);
                sumLabel.setOpaque(true);
                gamePanel.add(sumLabel);

                String text=" Following the resulting number, count clockwise from the player, and eventually pointing to the banker";
                JLabel label = new JLabel(text);
                label.setForeground(Color.ORANGE);
                label.setBounds(400, 1000, 10, 1);
                Font font = new Font("Arial", Font.BOLD, 12);
                label.setFont(font);
                gamePanel.add(label);

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
                            revalidate();
                            repaint();
                        });
                        timer.cancel();
                    }
                }, 5000);
                // set a timer, let the number of dice remain 5s and remove;

                gamePanel.remove(label);
            }

        });

        num.setBounds(400, 500, 100, 50);
        gamePanel.add(num);



        // sum mode 4 to choose a host;
    }
    // set the dice to choose the host;


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

                JButton menuButton = new JButton("Main Menu");
                menuButton.setFocusPainted(false);
                Font backFont = new Font("Arial", Font.BOLD, 30);
                menuButton.setSize(80, 80);
                menuButton.setFont(backFont);
                menuButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        helpWindow.dispose();
                        dispose();
                        new mainMenuWindow();
                    }
                });
                helpWindow.add(menuButton);

                JButton restartButton = new JButton("Restart");
                restartButton.setFocusPainted(false);
                Font restartFont = new Font("Arial", Font.BOLD, 30);
                restartButton.setSize(80, 80);
                restartButton.setFont(restartFont);
                restartButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        helpWindow.dispose();
                        dispose();
                        new GameWindow().setVisible(true);
                    }
                });
                helpWindow.add(restartButton);

                JButton rulesButton = new JButton("Rules");
                rulesButton.setFocusPainted(false);
                Font rulesFont = new Font("Arial", Font.BOLD, 30);
                rulesButton.setSize(80, 80);
                rulesButton.setFont(rulesFont);
                rulesButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
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




}