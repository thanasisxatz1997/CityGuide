package Forms;
import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame{
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton button1;
    private JScrollPane filterPane;
    private JScrollPane storePane;
    private JPanel storePanel;
    private JPanel filterPanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextArea tandemSkydivingIsTheTextArea;
    private JButton detailsButton;
    private JButton button3;
    private JButton button4;
    private JButton button2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JTextField textField1;
    private JTextField textField2;
    private JTable table1;
    private JButton saveButton;
    private JButton registerButton;
    private JButton logInButton;

    public MainForm()
    {
        this.setTitle("CityGuide");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.add(panel1);
        this.setSize(new Dimension(800,800));
    }


}
