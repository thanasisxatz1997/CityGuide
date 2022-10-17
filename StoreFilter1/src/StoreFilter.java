import javax.swing.*;
import java.awt.*;

public class StoreFilter extends JDialog {
    private JPanel panel1;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JButton ApplyFiltersbutton;
    private JTextField textField1;

    public StoreFilter(JFrame parent) {
        super(parent);
        this.setTitle("Filters");
        this.setContentPane(this.panel1);
        this.setMinimumSize(new Dimension(400,400));
        this.setModal(true);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        StoreFilter storeFilter = new StoreFilter((JFrame) null);
    }
}


