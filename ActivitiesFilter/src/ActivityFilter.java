import javax.swing.*;
import java.awt.*;

public class ActivityFilter extends JDialog {
    private JPanel panel1;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton applyFiltersButton;

    public ActivityFilter(JFrame parent) {
        super(parent);
        this.setTitle("Filters");
        this.setContentPane(this.panel1);
        this.setMinimumSize(new Dimension(400,400));
        this.setModal(true);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        ActivityFilter activityFilter = new ActivityFilter((JFrame) null);
    }
}
