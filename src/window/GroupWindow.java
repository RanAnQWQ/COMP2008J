package window;
import javax.swing.*;
import java.awt.*;
public class GroupWindow extends JFrame {
    public GroupWindow() {
        setTitle("Group List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        getContentPane().add(panel);

        JTextArea textArea = new JTextArea("Here is the group list: \n\n 22372122 An Ran \n\n 22372124 Shen Jinyan\n\n 22372126 Zhu Qiyue\n\n 22372130 Li Siying");
        textArea.setFont(new Font("Arial",Font.PLAIN,18));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        setLocationRelativeTo(null);
    }
}
