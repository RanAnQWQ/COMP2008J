package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenuWindow extends JFrame {
    public MainMenuWindow() {
        setTitle("Majiang");
        setSize(1200, 800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("src/window/background/background0.png");
        add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("Majiang", SwingConstants.CENTER);
        label.setForeground(Color.ORANGE);
        label.setBounds(200, 300, 800, 100);
        Font font = new Font("Segoe Script", Font.BOLD, 96);
        label.setFont(font);
        panel.add(label);

        String text = String.format("%58s", "麻将");
        JLabel label1 = new JLabel(text, SwingConstants.CENTER);
        label1.setForeground(Color.YELLOW);
        label1.setBounds(60, 300, 1000, 100);
        Font font1 = new Font("华文中宋", Font.BOLD, 32);
        label1.setFont(font1);
        panel.add(label1);

///////////////////////Game button/////////////////////////////////////////////
        JButton gameButton = new JButton("Start Game");
        gameButton.setFocusPainted(false);
        Font gameFont = new Font("Arial", Font.BOLD, 24);
        gameButton.setFont(gameFont);

        gameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> {
                    try {
                        GameContent gameContent=new GameContent();
                        gameContent.aGame();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        });
        gameButton.setBounds(500, 500, 190, 50);
        panel.add(gameButton);

///////////////////////network button///////////////////////////////////////////
        JButton networkButton = new JButton("Network Game");
        networkButton.setFocusPainted(false);
        networkButton.setFont(gameFont);

        networkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();

                SwingUtilities.invokeLater(() -> {
                    try {
                        NetworkContent gameContent=new NetworkContent();
                        gameContent.aGame(NetworkWindow.host, NetworkWindow.port);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        });
        networkButton.setBounds(485, 600, 220, 50);
        panel.add(networkButton);




///////////////////////rules button///////////////////////////////////////////
        JButton rulesButton = new JButton("Game Rules");
        rulesButton.setFocusPainted(false);
        Font rulesFont = new Font("Arial", Font.BOLD, 24);
        rulesButton.setFont(rulesFont);

        rulesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RulesWindow rulesWindow = new RulesWindow();
                rulesWindow.setVisible(true);
            }
        });
        rulesButton.setBounds(250, 500, 190, 50);
        panel.add(rulesButton);

///////////////////////NoviceTutorial button///////////////////////////////////////////
        JButton noviceButton = new JButton("Novice Tutorial");
        noviceButton.setFocusPainted(false);
        Font noviceFont = new Font("Arial", Font.BOLD, 24);
        noviceButton.setForeground(Color.BLACK);
        noviceButton.setFont(noviceFont);

        noviceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new NoviceTutorial().setVisible(true);
            }
        });
        noviceButton.setBounds(750, 500, 220, 50);
        panel.add(noviceButton);

///////////////////////GroupList button///////////////////////////////////////////
        JButton groupButton = new JButton("Group List :D");
        groupButton.setFocusPainted(false);
        Font groupFont = new Font("Arial", Font.BOLD, 24);
        groupButton.setForeground(Color.PINK);
        groupButton.setFont(groupFont);

        groupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GroupWindow groupWindow = new GroupWindow();
                groupWindow.setVisible(true);
            }
        });
        groupButton.setBounds(0, 0, 190, 50);
        panel.add(groupButton);

///////////////////////show all the objects///////////////////////////////////////////////////
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
