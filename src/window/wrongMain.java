package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class wrongMain {

    public static void main(String[] args) {
        int width=1200;
        int height=800;
        JFrame frame = new JFrame("window");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set the size of the window
        frame.setResizable(false);
        ImagePanel panel = new ImagePanel("src/window/background1.png");
        frame.add(panel); //set the background

        GridBagLayout layout = new GridBagLayout();  //regulate the title site
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Majiang", SwingConstants.CENTER);
        label.setForeground(Color.ORANGE);
        Font font = new Font("Segoe Script", Font.BOLD, 96);
        label.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        layout.setConstraints(label, gbc);
        panel.add(label);

        String text = String.format("%50s", "麻将");
        JLabel label1 = new JLabel(text, SwingConstants.CENTER);
        label1.setForeground(Color.YELLOW);
        Font font1 = new Font("华文中宋", Font.BOLD, 32);
        label1.setFont(font1);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        layout.setConstraints(label1, gbc);
        panel.add(label1);


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

/////////////////////Game button/////////////////////////////////////////////

        JButton gameButton = new JButton("Start Game");
        Font gameFont = new Font("Arial", Font.BOLD, 24);
        //gameButton.setBackground(Color.ORANGE);
        gameButton.setFont(gameFont);

        gameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // close the menu window
                new GameWindow().setVisible(true);
            }
        });
        gameButton.setBounds(700,500, 180, 50);
        panel.add(gameButton);
        System.out.println("gameButton: "+ gameButton.getX()+" + "+gameButton.getY());

/////////////////////rules button///////////////////////////////////////////

        JButton rulesButton = new JButton("Rules");
        Font rulesFont = new Font("Arial", Font.BOLD, 24);
        rulesButton.setFont(rulesFont);

        rulesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RulesWindow rulesWindow = new RulesWindow();
                rulesWindow.setVisible(true);
            }
        });
        rulesButton.setBounds(400, 500, 100, 50);
        panel.add(rulesButton);
        System.out.println("RulesButton: "+ rulesButton.getX()+" + "+rulesButton.getY());

/////////////////////GroupList button///////////////////////////////////////////

        JButton groupButton = new JButton("Group List :D");
        Font groupFont = new Font("Arial", Font.BOLD, 24);
        groupButton.setForeground(Color.PINK);
        groupButton.setFont(groupFont);

        groupButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                GroupWindow groupWindow = new GroupWindow();
                groupWindow.setVisible(true);
            }
        });
        groupButton.setBounds(0, 0, 190, 50);
        panel.add(groupButton);
        System.out.println("groupButton: "+ groupButton.getX()+" + "+groupButton.getY());
    }

}
