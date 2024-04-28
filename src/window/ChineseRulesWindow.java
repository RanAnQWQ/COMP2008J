package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChineseRulesWindow extends JFrame {
    public ChineseRulesWindow(){
        setTitle("Rules in Chinese");
        setSize(1050,700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        // Title of rules
        JLabel titleLabel = new JLabel("规则说明", JLabel.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 22));
        titleLabel.setForeground(Color.red);
        topPanel.add(titleLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        //Set a new text area
        JTextArea textArea = new JTextArea();
        textArea.setBounds(0, 0, 1070, 700);

        //set the scroll bar
        JScrollPane scroll2 = new JScrollPane();
        scroll2.setBounds(0,0,1070,700);
        scroll2.setViewportBorder(null);
        scroll2.setViewportView(textArea);
        scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll2.getViewport().setBorder(null);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        String text_introduction = "基本介绍：" + "\n" +
                "  麻将是一种中国古代发明的四人博弈游戏，牌类娱乐用具，用竹子、骨头或塑料制成的小长方块，上面刻有花纹或字样。在本游戏所使用的麻将版本中，每副共134张。麻将的牌式主要有“饼（文钱）”、“条（索子）”、“万（万贯）”、“风”。\n"+"\n"

                +"--------------------玩家介绍--------------------\n"
                +"  在你左手边是玩家被叫做你的上家。\n"
                +"  在你右手边是玩家被叫做你的下家。\n"
                +"  在你对面的玩家被叫做你的对家。\n\n"

                +"--------------------吃、碰、杠、听、胡的规则--------------------\n"
                +"1.吃：\n  当你的上家所丢出的牌中获得可以组成顺子的牌时，你可以选择吃这些牌来完成顺子。例如，如果你的手牌是345，其他玩家打出了2，这时你可以选择吃这张2来组成顺子234，也可以选择不吃2组成顺子345。\n\n"
                +"2.碰：\n  当其他三位玩家打出的牌和你手中已有的两张牌组成刻子时，不管是否轮到你出牌，你都可以选择碰这个刻子。例如，如果你的手中已有两个8，其他玩家打出了一个8，这时你可以选择碰这个8来组成刻子888。\n\n"
                +"3.杠：（分为明杠和暗杠）\n"
                +"3.1.明杠：\n  当你手中已有三张相同的牌，其他玩家打出了第四张相同的牌时，你可以选择明杠这个刻子，并把它们展示出来。例如，如果你的手中已有三个8，其他玩家打出了一个8，这时你可以选择明杠这个8来组成刻子8888，并把它们展示在牌桌上。\n"
                +"3.2.暗杠：\n  当你手中已有三张相同的牌时，并且自己摸到了第四张相同的牌，你可以选择暗杠这个刻字，并且可以不用展示。例如，如果你的手中已有三个8，自己摸到了第四个8，这时你可以选择暗杠这个8来组成刻子8888，并且可以不用在牌桌上展示。\n\n"
                +"4.听：\n  当你手牌只差一张牌就能胡牌时，你可以宣布听牌。在宣布听牌之后，你只能从牌墙的顶部抓牌，不能碰或吃别人打出的牌。这样做的目的是等待所需的牌来完成手中的牌型。\n\n"
                +"5.胡：\n  当你的牌型满足胡牌的格式时，你可以选择胡牌,即游戏胜利。\n"
                +"  胡牌的格式：    n*ABC+m*AAA+DD  （例如：123+345+456+777+99）\n"
                +"  m+n=4(n或m可以等于0，但不能两者都等于0)，ABC是按顺序排列的三张牌，AAA是三张相同的牌，DD是两个相同牌。\n\n"
                +"6.流局：\n  当不再有新牌且没有玩家胡牌时，游戏结束，称为流局（平局）。\n\n"

                +"注意：吃、碰、杠、听、胡的优先级：\n"
                +"  如果玩家打出来一张牌，下家要吃，而另一家要碰，则碰优先；\n"+"如果玩家打出来一张牌，下家要吃，而另一家要杠，则杠优先。\n"
                +"  胡优先于其他四项拿牌规则。\n\n"

                +"--------------------定庄的规则--------------------\n"
                +"  每局第一个丢牌的人被叫做庄家。庄家初始有14张牌，其他人有13张牌。\n"
                +"  定庄是由两个骰子的点数之和决定的（在我们的游戏中没有设置两个骰子，而是设置了一个骰子按钮，它可以直接显示两个骰子随机点数之和）。从坐在东位的玩家起，按逆时针顺序数，最后指向的人即为庄家。" +
                "例如：假设骰子点数之和为4，东（1）->北（2）->西（3）->南（4），则南位的玩家为庄家。\n"
                +"  如果庄家胡牌，庄家可以获得更多分数且继续当庄家；如果庄家没能胡牌，庄家会丢掉更多的分数，且下一局的庄家变更为本局庄家的下家。\n"
                ;
        textArea.setText(text_introduction);
        textArea.setCaretPosition(0);
        scroll2.getVerticalScrollBar().setValue(0);

        textArea.setFont(new Font("微软雅黑",Font.PLAIN,16));
        textArea.setEditable(false);
        textArea.setBorder(null);
        mainPanel.add(new JScrollPane(textArea),BorderLayout.WEST);
        textArea.setForeground(Color.black);

        getContentPane().add(mainPanel);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        JButton switchButton2 = new JButton("Rules in English");
        switchButton2.setFocusPainted(false);
        switchButton2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {switchButton2.setFocusPainted(false);
                new RulesWindow();//if click the English rules botton, the Chinese rules window will be closed and create an English rules window
                dispose();
            }
        });
        topPanel.add(switchButton2,BorderLayout.EAST);
    }
}


