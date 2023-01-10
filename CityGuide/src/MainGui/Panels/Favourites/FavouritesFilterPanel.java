package MainGui.Panels.Favourites;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FavouritesFilterPanel extends JPanel {
    public FavouritesDisplayPanel connectedFavouritesDisplayPanel;
    private JButton applyFiltersButton;
    private JTextField searchTextField;
    private JLabel typeLabel;
    private JLabel ratingsLabel;
    private JComboBox typeComboBox;
    private JComboBox ratingsComboBox;

    public FavouritesFilterPanel()
    {
        Load();
    }

    private void Load()
    {
        this.setPreferredSize(new Dimension(200,400));
        this.setMaximumSize(new Dimension(200,400));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        LoadFilterButton(c);
        LoadSearchTextField(c);
        LoadTypeLabel(c);
        LoadRatingsLabel(c);
        LoadTypeComboBox(c);
        LoadRatingsComboBox(c);
        //LoadActionListener();

        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    public void SetConnectedFavouritesDisplayPanel(FavouritesDisplayPanel favouritesDisplayPanel)
    {
        connectedFavouritesDisplayPanel=favouritesDisplayPanel;
    }
    private void LoadFilterButton(GridBagConstraints c)
    {
        applyFiltersButton= new JButton("");
        applyFiltersButton.setIcon(new ImageIcon("src/resources/Icons/searchIcon.png"));
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(10,10,5,5);  //padding
        c.weightx = 0.1;
        c.weighty =0.1;
        c.gridx = 0;
        c.gridy = 0;
        this.add(applyFiltersButton, c);
        applyFiltersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    private void LoadSearchTextField (GridBagConstraints c)
    {
        searchTextField=new JTextField();
        searchTextField.setFont(new Font("Times Roman",Font.BOLD,16));
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx = 0.9;
        c.weighty=0.1;
        c.ipadx=5;
        c.ipady=5;
        c.gridwidth=2;
        c.gridx = 1;
        c.gridy = 0;
        this.add(searchTextField,c);
    }
    private void LoadTypeLabel(GridBagConstraints c)
    {
        typeLabel=new JLabel("Type:");
        typeLabel.setFont(new Font("Times Roman",Font.BOLD,16));
        c.weightx=0.1;
        c.weighty=1;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=1;
        this.add(typeLabel,c);

    }
    private void LoadRatingsLabel(GridBagConstraints c)
    {
        ratingsLabel=new JLabel("Rating:");
        ratingsLabel.setFont(new Font("Times Roman",Font.BOLD,16));
        c.weightx=0.1;
        c.weighty=1;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx=0;
        c.gridy=2;
        this.add(ratingsLabel,c);
    }
    private void LoadTypeComboBox(GridBagConstraints c)
    {
        typeComboBox=new JComboBox();
        typeComboBox.addItem("");
        typeComboBox.addItem("Cafe");
        typeComboBox.addItem("Restaurant");
        typeComboBox.addItem("Market");
        typeComboBox.addItem("Night Life");
        c.weightx=0.9;
        c.weighty=1;
        c.gridwidth=2;
        c.gridheight=1;
        c.gridx=1;
        c.gridy=1;
        this.add(typeComboBox,c);
    }

    private void LoadRatingsComboBox(GridBagConstraints c)
    {
        ratingsComboBox=new JComboBox();
        ratingsComboBox.addItem("");
        ratingsComboBox.addItem("4+");
        ratingsComboBox.addItem("5+");
        c.insets = new Insets(10,10,450,5);
        c.weightx=0.9;
        c.weighty=1;
        c.gridwidth=2;
        c.gridheight=1;
        c.gridx=1;
        c.gridy=2;
        this.add(ratingsComboBox,c);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        this.revalidate();
        this.repaint();
    }
}
