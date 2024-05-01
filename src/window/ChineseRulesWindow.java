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

                JLabel titleLabel = new JLabel("规则说明", JLabel.CENTER);
                titleLabel.setFont(new Font("华文中宋", Font.BOLD, 22));
                titleLabel.setForeground(Color.red);
                topPanel.add(titleLabel);
                mainPanel.add(topPanel, BorderLayout.NORTH);
                // Title of rules;

                JTextArea textArea = new JTextArea();
                textArea.setPreferredSize(new Dimension(1040,500));
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                //Set a new text area;

                String text_introduction = "基本介绍：" + "\n" +
                        "麻将是一种中国古代发明的博弈游戏，牌类娱乐用具，用竹子、骨头或塑料制成的小长方块，上面刻有花纹或字样。在本游戏所使用的麻将版本中，每副共134张。麻将的牌式主要有“饼（文钱）”、“条（索子）”、“万（万贯）”、“风”。\n"+
                        "吃听碰的规则：\n"+"";
                textArea.setText(text_introduction);


                textArea.setFont(new Font("宋体",Font.PLAIN,16));
                textArea.setEditable(false);
                textArea.setBorder(null);
                mainPanel.add(new JScrollPane(textArea),BorderLayout.WEST);
                textArea.setForeground(Color.black);

                getContentPane().add(mainPanel);

                setResizable(false);
                setLocationRelativeTo(null);
                setVisible(true);

                JButton switchButton = new JButton("Rules in English");
                switchButton.setFocusPainted(false);
                switchButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {switchButton.setFocusPainted(false);
                        new RulesWindow();//if click the English rules botton, the Chinese rules window will be closed and create an English rules window
                        dispose();
                    }
                });
                topPanel.add(switchButton,BorderLayout.EAST);
            }
        }


