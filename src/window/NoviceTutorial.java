package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class NoviceTutorial extends JFrame {
    private ImagePanel novicePanel;

    public static void main(String[] args) {
        new NoviceTutorial();
    }

    public NoviceTutorial() {
        window_frame();
        exitButtons();
        dice_button();

        this.setVisible(true);

        TutorialDialog();
    }

    private void window_frame() {
        setTitle("Novice Tutorial");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        novicePanel = new ImagePanel("src/window/background2.jpg");
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
                DiceButtonDialog();
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        tutorialDialog.add(buttonPanel, BorderLayout.SOUTH);

        tutorialDialog.setVisible(true);
    }

    private void  DiceButtonDialog(){
        JOptionPane diceDialog = new JOptionPane(
                "<html><font size='4'>Firstly, please click on the 'Throw Dice' button to roll the dice :P<br><br>It will decide the boss for this new game.</html>",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION);

        JDialog diceButtonDialog = diceDialog.createDialog("First Step");
        diceButtonDialog.setBounds(620, 150,300,200);
        diceDialog.setOptions(new Object[]{});  //remove the original button

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
    private void dice_button(){
        Random number = new Random();
        int first_dice = number.nextInt(6) + 1;
        int second_dice = number.nextInt(6) + 1;
        int sum = first_dice + second_dice;
        // set the number of dices;

        JButton num = new JButton("THROW DICE");
        Font dice_font = new Font("Arial", Font.BOLD, 24);
        num.setFocusPainted(false);
        num.setFont(dice_font);
        //num.setHorizontalAlignment(SwingConstants.CENTER);
        num.setBounds(500,0,200,50);
        novicePanel.add(num);
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

                novicePanel.setLayout(null);
                //remove the default lay

                int labelWidth = scaledArrowIcon.getIconWidth();
                int labelHeight = scaledArrowIcon.getIconHeight();
                int panelWidth = novicePanel.getWidth();
                int panelHeight = novicePanel.getHeight();
                int x = (panelWidth - labelWidth) / 2;
                int y = (panelHeight - labelHeight) / 2;
                arrowLabel.setBounds(x, y, labelWidth, labelHeight);
                //compute the position of the arrow;

                novicePanel.add(arrowLabel);
                // add the label picture to game screen;

                JLabel sumLabel = new JLabel("" + sum);
                Font label_font = new Font("Arial", Font.BOLD, 50);
                sumLabel.setLocation(560,250);
                sumLabel.setFont(label_font);
                sumLabel.setHorizontalAlignment(SwingConstants.CENTER);
                sumLabel.setSize(70, 50);
                sumLabel.setBackground(Color.WHITE);
                sumLabel.setOpaque(true);
                novicePanel.add(sumLabel);
                revalidate();
                repaint();
                // set the label, show the sum of 2 dices;

                novicePanel.remove(num);
                // remove the original button once the label occur;

                java.util.Timer timer = new Timer();

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        SwingUtilities.invokeLater(() -> {
                            novicePanel.remove(sumLabel);
                            novicePanel.remove(arrowLabel);
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
                arrowIcon = new ImageIcon("src/window/ArrowDown.png");
                break;
            case 2:
                arrowIcon = new ImageIcon("src/window/ArrowRight.png");
                break;
            case 3:
                arrowIcon = new ImageIcon("src/window/ArrowTop.png");
                break;
            default:
                arrowIcon = new ImageIcon("src/window/ArrowLeft.png");
                break;
        }
        return arrowIcon;
    }

    // This is the way to choose the host in first game;
    // Set the arrow to choose the host. According to rules, from the East player, counting n times (n is the sum of 2 dices) in counter-clockwise, the last one is the host of this game;

    //---------------------not in first game--------------------
}