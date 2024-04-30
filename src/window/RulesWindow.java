package window;
import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RulesWindow extends JFrame {
    public RulesWindow() {
        setTitle("Rules in English");  //set the English rules window
        setSize(1050, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("RULES INTRODUCTION", JLabel.CENTER);  //set the title
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.red);
        topPanel.add(titleLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea();  //set text area for rules
        textArea.setBounds(0, 0, 1070, 700);

        JScrollPane scroll = new JScrollPane();  //set the scroll bar
        scroll.setBounds(0, 0, 1070, 700);
        scroll.setViewportBorder(null);
        scroll.setViewportView(textArea);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getViewport().setBorder(null);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        //Set a new text area;
        String text_introduction =
                "Basic Introduction:" + "\n"
                        + "  Mahjong is a game for four people invented in ancient China. It is a small rectangular piece made of bamboo, bone or plastic and engraved with patterns or characters. In the version of Mahjong used in this game, there are 134 cards per deck. " +
                        "The main types of mahjong cards are Bing (Wenqian), Tiao (suozi), Wan (Wan Guan), wind." + "\n\n"

                        + "----------------------------------------The player group---------------------------------------- \n"
                        + "  The player on your left is called your last opponent.\n"
                        + "  The player on your right is called your next opponent.\n"
                        + "  The player opposite you is called your opposite opponent.\n\n"

                        + "----------------------------------------Rules for Chi, Peng, Gang, Ting, Hu----------------------------------------" + "\n"
                        + "1. Chi:" + "\n" + "  When you can form a sequence with a tile discarded by your previous player, you can choose to eat this tile to complete the sequence. For example, if your hand tiles are 3, 4, 5, and your previous player discards 2, you can eat the 2 to form the sequence 2, 3, 4. And can also choose not to eat the 2 for the set 3, 4, 5" + "\n" + "\n"
                        + "2. Peng:" + "\n" + "  When a tile discarded by another player matches the two identical tiles in your hand to form a set. Whether it's your turn to play or not, you can choose to peng the set. For example, if you already have two 8 tiles and another player discards an 8, you can collide the 8 to form the set 8, 8, 8." + "\n" + "\n"
                        + "3. Gang: " + " (It divides to overt kong and covert kong)" + "\n"
                        + "3.1. Overt Gang: " + "\n" + "  When you have three same tiles and another player discards the rest tile, you can choose to kong the forth tile and show them in public. For example, you have already had 888, and the forth 8 is discarded by other players, you can choose to overt gang this 8 to form the set 8, 8, 8, 8, and show the set in the public." + "\n"
                        + "3.2. Covert Gang: " + "\n" + "  When you have three same tiles and you draw the forth tile by yourself, this make up the covert kong and you don't need to show them in public. For example, you have already had 888, and the forth 8 is gotten by your self, you can choose to covert gang this 8 to form the set 8, 8, 8, 8 and don't need to show the set in the public." + "\n" + "\n"
                        + "4. Ting:" + "\n" + "  When you are only one tile away from winning, you can declare a ready hand. After declaring a ready hand, you can only draw tiles from the top of the wall, not peng or chi tiles discarded by others. This is to wait for the tile needed to complete your hand." + "\n\n"
                        + "5. Hu:" + "\n" + "  When your tiles are satisfied to the required format, you can 'Hu' which means you can win the game." + "\n"
                        + "  required format:    n*ABC+m*AAA+DD  (Example: 123+345+456+777+99)\n"
                        + "  m+n=4(it is allowed for m=4 & n=0 or m=0 & n=4, but they cannot be 0 at the sametime), ABC means three tiles in order, AAA means three same tiles, DD means two same tiles.\n\n"
                        + "6. Draw:" + "\n" + "  When there is no new tiles and no one wins, the game is a draw." + "\n\n"

                        + "Notice: Priority of Chi, Peng, Gang, Ting, Hu:\n"
                        + "  When a player discard a tile, his next opponent can Chi and another player can Peng/Gang, Peng/Gang is prior to Chi. Only if the other players cannot Peng/Gang or choose not to Peng/Gang, the next opponent can choose to Chi or not." + "\n"
                        + "  Hu is prior to all of the rules.\n\n"

                        + "----------------------------------------Rules of deciding the boss----------------------------------------\n"
                        + "  The first one in a game to discard tile is called 'boss'. The boss player will have 14 tiles, while the other players have 13 tiles.\n"
                        + "  The rules of deciding the boss is by the sum number of two dices (Tips: in our game we don't set two dices but a dice button which can directly show the random sum number). Regard the east player as '1' and count the sum number in a counterclockwise direction." +
                        " For example, if the sum number is 4, then count it as east(1)->north(2)->west(3)->south(4), so the boss is the south player." + "\n"
                        + "  If the boss player win the game, he will gain more scores and still be the boss. But if he lose the game, he will loss more scores and the next boss is his next opponent.\n";


        textArea.setText(text_introduction);
        textArea.setCaretPosition(0);
        scroll.getVerticalScrollBar().setValue(0);


        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setEditable(false);
        textArea.setBorder(null);
        mainPanel.add(new JScrollPane(textArea), BorderLayout.WEST);
        textArea.setForeground(Color.black);
        //set the display area of the rules;

        getContentPane().add(mainPanel);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        JButton switchButton = new JButton("Rules in Chinese");
        switchButton.setFocusPainted(false);
        switchButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChineseRulesWindow();//if click the Chinese rules botton, the English rules window will be closed and create an Chinese rules window
                dispose();
            }
        });
        topPanel.add(switchButton, BorderLayout.EAST);
        //click the button, switch to the page where display the rules in Chinese;
    }
}

