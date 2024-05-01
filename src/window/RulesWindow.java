package window;
import javax.swing.*;
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
        textArea.setBounds(0, 0, 1050, 700);
       ;

        JScrollPane scroll = new JScrollPane();  //set the scroll bar
        scroll.setBounds(0,0,1050,700);
        scroll.setViewportBorder(null);
        scroll.setViewportView(textArea);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getViewport().setBorder(null);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        //Set a new text area;
        String text_introduction = "Basic Introduction:" + "\n"
                + "Mahjong is a game invented in ancient China. It is a small rectangular piece made of bamboo, bone or plastic and engraved with patterns or characters. In the version of Mahjong used in this game, there are 134 cards per deck. " +
                "The main types of mahjong cards are Bing (Wenqian), Tiao (suozi), Wan (Wan Guan), wind." + "\n" + "\n"
                + "Rules for eat, collision, kong and ting." + "\n" +"\n"
                + "Chi:" + "\n" + " When you can form a sequence with a tile discarded by your previous player, you can choose to eat this tile to complete the sequence. For example, if your hand tiles are 3, 4, 5, and your previous player discards 2, you can eat the 2 to form the sequence 2, 3, 4." + "\n" +"\n"
                + "Peng:" + "\n" + " When a tile discarded by another player matches the two identical tiles in your hand to form a set. Whether it's your turn to play or not, you can choose to peng the set. For example, if you already have two 8 tiles and another player discards an 8, you can collide the 8 to form the set 8, 8, 8." + "\n"+"\n"
                + "Gang: " + "(It divides to overt kong and covert kong)"+ "\n"
                + "Overt Gang: " + "\n" + "When you have three same tiles and another player discards the rest tile, you can choose to kong the forth tile and show them in public." + "\n"
                + "Covert Gang: " +"\n" + "When you have three same tiles and you draw the forth tile by yourself, this make up the covert kong and you don't need to show them in public." + "\n" + "\n"
                + "Ting:" + "\n" + " When you are only one tile away from winning, you can declare a ready hand. After declaring a ready hand, you can only draw tiles from the top of the wall, not peng or chi tiles discarded by others. This is to wait for the tile needed to complete your hand." + "\n"
                + "Basic Introduction:" + "\n"
                + "Mahjong is a game invented in ancient China. It is a small rectangular piece made of bamboo, bone or plastic and engraved with patterns or characters. In the version of Mahjong used in this game, there are 134 cards per deck. " +
                "The main types of mahjong cards are Bing (Wenqian), Tiao (suozi), Wan (Wan Guan), wind." + "\n" + "\n"
                + "Rules for eat, collision, kong and ting." + "\n" +"\n"
                + "Chi:" + "\n" + " When you can form a sequence with a tile discarded by your previous player, you can choose to eat this tile to complete the sequence. For example, if your hand tiles are 3, 4, 5, and your previous player discards 2, you can eat the 2 to form the sequence 2, 3, 4." + "\n" +"\n"
                + "Peng:" + "\n" + " When a tile discarded by another player matches the two identical tiles in your hand to form a set. Whether it's your turn to play or not, you can choose to peng the set. For example, if you already have two 8 tiles and another player discards an 8, you can collide the 8 to form the set 8, 8, 8." + "\n"+"\n"
                + "Gang: " + "(It divides to overt kong and covert kong)"+ "\n"
                + "Overt Gang: " + "\n" + "When you have three same tiles and another player discards the rest tile, you can choose to kong the forth tile and show them in public." + "\n"
                + "Covert Gang: " +"\n" + "When you have three same tiles and you draw the forth tile by yourself, this make up the covert kong and you don't need to show them in public." + "\n" + "\n"
                + "Ting:" + "\n" + " When you are only one tile away from winning, you can declare a ready hand. After declaring a ready hand, you can only draw tiles from the top of the wall, not peng or chi tiles discarded by others. This is to wait for the tile needed to complete your hand." + "\n" ;

        textArea.setText(text_introduction);
        textArea.setCaretPosition(0);
        scroll.getVerticalScrollBar().setValue(0);


        textArea.setFont(new Font("Arial",Font.PLAIN,18));
        textArea.setEditable(false);
        textArea.setBorder(null);
        mainPanel.add(new JScrollPane(textArea),BorderLayout.WEST);
        textArea.setForeground(Color.black);

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
        topPanel.add(switchButton,BorderLayout.EAST);
    }

        /*基本介绍：
        麻将是一种中国古代发明的博弈游戏，牌类娱乐用具，用竹子、骨头或塑料制成的小长方块，上面刻有花纹或字样。在本游戏所使用的麻将版本中，每副共134张。麻将的牌式主要有“饼（文钱）”、“条（索子）”、“万（万贯）”、“风”。

        Basic introduction:
        Mahjong is a game invented in ancient China. It is a small rectangular piece made of bamboo, bone or plastic and engraved with patterns or characters. In the version of Mahjong used in this game, there are 134 cards per deck. The main types of mahjong cards are "Bing (Wenqian)", "Tiao (suozi)", "Wan (Wan Guan)", "wind".
        * */
        /*吃听碰的规则：
        rules for eat, ting and collision.
        吃：当你的前一个玩家所丢出的牌中获得可以组成顺子的牌时，你可以选择吃这些牌来完成顺子。例如，如果你的手牌是345，其他玩家打出了2，这时你可以选择吃这张2来组成顺子234。
        Eat: When you can form a sequence with a tile discarded by your previous player, you can choose to eat this tile to complete the sequence. For example, if your hand tiles are 3, 4, 5, and your previous player discards 2, you can eat the 2 to form the sequence 2, 3, 4.
        听：当你手牌只差一张牌就能胡牌时，你可以宣布听牌。在宣布听牌之后，你只能从牌墙的顶部抓牌，不能碰或吃别人打出的牌。这样做的目的是等待所需的牌来完成手中的牌型。
        Ting: When you are only one tile away from winning, you can declare a ready hand. After declaring a ready hand, you can only draw tiles from the top of the wall, not peng or chi tiles discarded by others. This is to wait for the tile needed to complete your hand.
        碰：当其他三位玩家打出的牌和你手中已有的两张牌组成刻子时，不管是否轮到你出牌，你都可以选择碰这个刻子。例如，如果你的手中已有两个8，其他玩家打出了一个8，这时你可以选择碰这个8来组成刻子888。
        Collision: When a tile discarded by another player matches the two identical tiles in your hand to form a set. Whether it's your turn to play or not, you can choose to peng the set. For example, if you already have two 8 tiles and another player discards an 8, you can collide the 8 to form the set 8, 8, 8.
        操作优先级:
        Behavior priority:

        如果玩家打出来一张牌，下家要吃，而另一家要碰，则碰优先；
        如果玩家打出来一张牌，下家要吃，而另一家要杠，则杠优先；
        If the play discarded a tile, the next player wants to eat, and the other house to touch, touch priority;
        If the player discarded a tile, the next player wants to eat, and the other house wants Gong, Gong first;
        胡的规则:
        rules for Hu:

        如果你的牌满足下面的公式就可以赢
        If your tiles satisfied the following formula, you can win

        n*AAA+m*ABC+DD

        m或n可以等于0，但不能两者都等于0,m+n=4, ABC是按顺序排列的三个贴图的例子，AAA是三个相同的贴图的例子，DD是两个相同的贴图的例子。
        m or n can be equal to 0, but not both, m+n=4, ABC is a example of three tiles in order, AAA a the example of three same tiles, DD is a example of two same tiles.
         */
    }

