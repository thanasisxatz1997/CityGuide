package MainGui.Panels.TestPanels;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ApplicationButtonPanel extends JPanel {

    public ApplicationButtonPanel()
    {
        Load();
    }
    private void Load()
    {
        this.setMaximumSize(new Dimension(145,40));
        //this.setLayout(new GridBagLayout());
        Border margin=new EmptyBorder(5,5,5,5);
        this.setBorder(margin);

        this.add(new JButton());

        this.setVisible(true);
    }
}
