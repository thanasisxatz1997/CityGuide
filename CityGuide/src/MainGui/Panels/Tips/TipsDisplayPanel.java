package MainGui.Panels.Tips;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TipsDisplayPanel extends JPanel {
    Font customSmallFont;
    Font customLargeFort;

    public TipsDisplayPanel()
    {
        Load();
    }

    private void Load()
    {
        this.setPreferredSize(new Dimension(730,580));
        this.setMinimumSize(new Dimension(730,580));
        this.setMaximumSize(new Dimension(730,580));
        LoadSmallFont("src/resources/Fonts/CaviarDreams.ttf");
        customLargeFort=LoadFontWithFontSize(20);
        StyleContext sc = new StyleContext();
        DefaultStyledDocument doc=new DefaultStyledDocument();
        JTextPane textPane=new JTextPane(doc);

        Style heading2Style = sc.addStyle("Heading2", null);
        heading2Style.addAttribute(StyleConstants.FontSize, new Integer(20));
        heading2Style.addAttribute(StyleConstants.FontFamily, "src/resources/Fonts/Roman SD.ttf");
        heading2Style.addAttribute(StyleConstants.Bold, new Boolean(true));

        String[] initString={"Free entrance"+"\n",
                "The following people can have free entrance to the main museums and public monuments:\n" +
                "\n" +
                "-Youths under 18 and seniors over 65\n" +
                "-People with disabilities and a carer if necessary\n" +
                "-Journalists\n" +
                "-Groups of students that have booked in advance\n" +
                "-Tour guides and interpreters\n" +
                "-Professors and students of Architecture, Archaeology, Art History and Fine Arts\n" +
                "-There are certain days in which the entrance is free for all the public, such as Christmas Day. Don't forget that \n" +
                "on the last Sunday of each month,\n" +
                " the entrance to the Vatican Museums and to the Sistine Chapel is free.\n" +
                "\n",
                "Discount tickets\n",
                "Students (aged 18-25) will, in most cases, enjoy discounts when buying tickets to the major attractions in Rome.\n" +
                "\n" +
                "The Roma Pass is a tourist card designed specifically for those over 25 and less than 65. It gives free access to two museums \n" +
                "and/or archaeological sites and reductions to other exhibitions and top attractions.  \n" +
                "  https://www.rome.net/roma-pass?_gl=1*t1fn1b*_up*MQ..*_ga*MTU1OTU5NDQ1My4xNjY4NTM5MTIx*_ga_0L41Q9EE60*MTY2ODUzOTEyMC4xLjAuMTY2ODUzOTEyMC4wLjAuMA..\n" +
                "OMNIA Vatican and Rome card\n" +
                "https://www.rome.net/omnia-rome-card?_gl=1*q3qz0j*_up*MQ..*_ga*MTU1OTU5NDQ1My4xNjY4NTM5MTIx*_ga_0L41Q9EE60*MTY2ODUzOTEyMC4xLjAuMTY2ODUzOTEyMC4wLjAuMA..\n" +
                "\n" ,
                "Saving on food\n" ,
                "Eating out every day on holidays is one of the biggest expenses on a holiday. If the weather is good, you can buy pizza \n" +
                " by weight or \n" +
                " order a pizza to go and eat in one of the city’s squares or parks.The paninis are also excellent and very cheap.\n" +
                "\n" +
                "Saving on public transport\n" ,
                "The city centre is easily accessible by foot so, unless you are staying far from the heart of Rome, there is no need to use \n " +
                "the public transport or taxis to get around.\n" +
                "If you realise that you do need to use the metro, bus or tram to get to the city centre every day, it is worth taking a look \n" +
                " at the different travel cards available or purchasing the Roma Pass.\n" +
                "\n" +
                "More tips\n" ,
                "\n" +
                "1. Visit the Colosseum for free\n" +
                "Admission to the iconic Colosseum is free the first Sunday of each month. To avoid the crowds and long ticket waiting arrive \n " +
                "at 8.00 a.m. \n" +
                "The Ticket to the Colosseum also allows entrance to the Roman Forum and Palentine Hill. \n" +
                "\n" +
                "2. Visit the Vatican Museums and Sistine Chapel for free\n" +
                "Every last Sunday of the month, the entrance is free to the Vatican Museums and the Sistine Chapel.\n" +
                "On days where the entrance is free, inevitably this attracts large crowds so get there early (7:30 a.m) to avoid  \n" +
                "a long wait to enter.\n" +
                "3. Free museums on 1st Sunday of the month (Domenica al Museo)\n" +
                "The first Sunday on each month is called Domenica al Museo, where many museums, monuments and art galleries in Rome \n" +
                " offer a free entrance.\n" +
                "Here is a list of the some poplar attractions and museums offering free entrance:\n" +
                "Borghese Gallery\n" +
                "The Baths of Caracalla \n" +
                "The Victor Emmanuel II National Monument\n" +
                "National gallery of modern and contemporary art\n" +
                "Capitoline Museums\n" +
                "4. Drink your coffee by the bar\n" +
                "In bars and cafès, table-service prices are usually at least double the price, compared to if you stand at the bar. Do as  \n" +
                "the locals do \n" +
                "and drink your coffee standing by the bar.\n" +
                "5. Have Aperitivo with free buffet food\n" +
                "A fantastic and easy way to save money in Rome is by taking advantage of aperitivo, which is basically happy hour \n" +
                " with free food.\n" +
                "Most bars and cafes will offer a buffet of free food from 6pm – 9 pm, with the purchase of a drink.\n" +
                "Some bars offer basic snacks including potato chips, olives and nuts, but others offer a huge selection of mouth-watering \n" +
                "canapés,\n" +
                " including pizza, pasta, couscous, salad, salami, cheese, cured meats and more.\n" +
                "Popular bars for this include\n" +
                "Freni e Frizione (Trastevere)\n" +
                "Momart (Piazza Bologna)\n" +
                "Salotto 42 (PANTHEON)\n" +
                "Gusto (PAZZA NAVONA)\n" +
                "Il Sorpasso (PRATI)\n" +
                "Blackmarket Hall (MONTI)\n" +
                "6. Drink from the Water fountains (Nasoni)\n" +
                "There are water fountains scattered all over the city called Nasoni.The Water is good drinking water coming from the \n" +
                "AppeninI Mountains. \n" +
                "Buy a bottle or bring your own flask to fill up, to save money not having to purchase pricey bottled water.\n" +
                "To find your closest Nasoni, download the app called drinking fountains.\n" +
                "\n" +
                "\n" +
                "TICKET PRICES\n" ,
                "\n" +
                "A single ticket – The price is €1.50. The ticket which can be used multiple times on all forms of public transport within \n" +
                "100 minutes.\n" +
                "\n" +
                "The following tickets offer unlimited trips on all Rome public transport upon the first time of usage\n" +
                "\n" +
                "A 24-hours ticket – The price is €7.\n" +
                "\n" +
                "A 48-hour ticket – The price is €12.50.\n" +
                "\n" +
                "A 72-hours ticket – The price is €18.\n" +
                "\n" +
                "A Weekly ticket – The price is €24.\n" +
                "\n" +
                "A Monthly ticket – The price is €35. (Valid from the first day of the month until the last day of the month)\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n"};;
        for(int i=0;i<initString.length;i++)
        {
            try {
                doc.insertString(doc.getLength(),initString[i],null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
        doc.setCharacterAttributes(0,13,heading2Style,true);
        doc.setCharacterAttributes(initString[0].length()+initString[1].length(),16,heading2Style,true);
        doc.setCharacterAttributes(1257,15,heading2Style,true);
        doc.setCharacterAttributes(1522,28,heading2Style,true);
        doc.setCharacterAttributes(1918,11,heading2Style,true);
        doc.setCharacterAttributes(4157,14,heading2Style,true);
        //doc.setParagraphAttributes(1,0,heading2Style,true);
        //doc.setParagraphAttributes(4,0,heading2Style,true);
        //doc.setParagraphAttributes(0,0,heading2Style,false);
        /*textPane.setText(textPane.getText()+"\n" +
                "The following people can have free entrance to the main museums and public monuments:\n" +
                "\n" +
                "-Youths under 18 and seniors over 65\n" +
                "-People with disabilities and a carer if necessary\n" +
                "-Journalists\n" +
                "-Groups of students that have booked in advance\n" +
                "-Tour guides and interpreters\n" +
                "-Professors and students of Architecture, Archaeology, Art History and Fine Arts\n" +
                "-There are certain days in which the entrance is free for all the public, such as Christmas Day. Don't forget that \n" +
                "on the last Sunday of each month,\n" +
                " the entrance to the Vatican Museums and to the Sistine Chapel is free.\n" +
                "\n" +
                "Discount tickets\n" +
                "Students (aged 18-25) will, in most cases, enjoy discounts when buying tickets to the major attractions in Rome.\n" +
                "\n" +
                "The Roma Pass is a tourist card designed specifically for those over 25 and less than 65. It gives free access to two museums \n" +
                "and/or archaeological sites and reductions to other exhibitions and top attractions.  \n" +
                "  https://www.rome.net/roma-pass?_gl=1*t1fn1b*_up*MQ..*_ga*MTU1OTU5NDQ1My4xNjY4NTM5MTIx*_ga_0L41Q9EE60*MTY2ODUzOTEyMC4xLjAuMTY2ODUzOTEyMC4wLjAuMA..\n" +
                "OMNIA Vatican and Rome card\n" +
                "https://www.rome.net/omnia-rome-card?_gl=1*q3qz0j*_up*MQ..*_ga*MTU1OTU5NDQ1My4xNjY4NTM5MTIx*_ga_0L41Q9EE60*MTY2ODUzOTEyMC4xLjAuMTY2ODUzOTEyMC4wLjAuMA..\n" +
                "\n" +
                "Saving on food\n" +
                "Eating out every day on holidays is one of the biggest expenses on a holiday. If the weather is good, you can buy pizza \n" +
                " by weight or \n" +
                " order a pizza to go and eat in one of the city’s squares or parks.The paninis are also excellent and very cheap.\n" +
                "\n" +
                "Saving on public transport\n" +
                "The city centre is easily accessible by foot so, unless you are staying far from the heart of Rome, there is no need to use \n " +
                "the public transport or taxis to get around.\n" +
                "If you realise that you do need to use the metro, bus or tram to get to the city centre every day, it is worth taking a look \n" +
                " at the different travel cards available or purchasing the Roma Pass.\n" +
                "\n" +
                "More tips\n" +
                "\n" +
                "1. Visit the Colosseum for free\n" +
                "Admission to the iconic Colosseum is free the first Sunday of each month. To avoid the crowds and long ticket waiting arrive \n " +
                "at 8.00 a.m. \n" +
                "The Ticket to the Colosseum also allows entrance to the Roman Forum and Palentine Hill. \n" +
                "\n" +
                "2. Visit the Vatican Museums and Sistine Chapel for free\n" +
                "Every last Sunday of the month, the entrance is free to the Vatican Museums and the Sistine Chapel.\n" +
                "On days where the entrance is free, inevitably this attracts large crowds so get there early (7:30 a.m) to avoid  \n" +
                "a long wait to enter.\n" +
                "3. Free museums on 1st Sunday of the month (Domenica al Museo)\n" +
                "The first Sunday on each month is called Domenica al Museo, where many museums, monuments and art galleries in Rome \n" +
                " offer a free entrance.\n" +
                "Here is a list of the some poplar attractions and museums offering free entrance:\n" +
                "Borghese Gallery\n" +
                "The Baths of Caracalla \n" +
                "The Victor Emmanuel II National Monument\n" +
                "National gallery of modern and contemporary art\n" +
                "Capitoline Museums\n" +
                "4. Drink your coffee by the bar\n" +
                "In bars and cafès, table-service prices are usually at least double the price, compared to if you stand at the bar. Do as  \n" +
                "the locals do \n" +
                "and drink your coffee standing by the bar.\n" +
                "5. Have Aperitivo with free buffet food\n" +
                "A fantastic and easy way to save money in Rome is by taking advantage of aperitivo, which is basically happy hour \n" +
                " with free food.\n" +
                "Most bars and cafes will offer a buffet of free food from 6pm – 9 pm, with the purchase of a drink.\n" +
                "Some bars offer basic snacks including potato chips, olives and nuts, but others offer a huge selection of mouth-watering \n" +
                "canapés,\n" +
                " including pizza, pasta, couscous, salad, salami, cheese, cured meats and more.\n" +
                "Popular bars for this include\n" +
                "Freni e Frizione (Trastevere)\n" +
                "Momart (Piazza Bologna)\n" +
                "Salotto 42 (PANTHEON)\n" +
                "Gusto (PAZZA NAVONA)\n" +
                "Il Sorpasso (PRATI)\n" +
                "Blackmarket Hall (MONTI)\n" +
                "6. Drink from the Water fountains (Nasoni)\n" +
                "There are water fountains scattered all over the city called Nasoni.The Water is good drinking water coming from the \n" +
                "AppeninI Mountains. \n" +
                "Buy a bottle or bring your own flask to fill up, to save money not having to purchase pricey bottled water.\n" +
                "To find your closest Nasoni, download the app called drinking fountains.\n" +
                "\n" +
                "\n" +
                "TICKET PRICES\n" +
                "\n" +
                "A single ticket – The price is €1.50. The ticket which can be used multiple times on all forms of public transport within \n" +
                "100 minutes.\n" +
                "\n" +
                "The following tickets offer unlimited trips on all Rome public transport upon the first time of usage\n" +
                "\n" +
                "A 24-hours ticket – The price is €7.\n" +
                "\n" +
                "A 48-hour ticket – The price is €12.50.\n" +
                "\n" +
                "A 72-hours ticket – The price is €18.\n" +
                "\n" +
                "A Weekly ticket – The price is €24.\n" +
                "\n" +
                "A Monthly ticket – The price is €35. (Valid from the first day of the month until the last day of the month)\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n");*/

        textPane.setPreferredSize(new Dimension(textPane.getPreferredSize().width, textPane.getPreferredSize().height+180));
        textPane.setMinimumSize(new Dimension(textPane.getPreferredSize().width, textPane.getPreferredSize().height+180));
        //textArea.setPreferredSize(new Dimension(730,1800));
        //textArea.setMinimumSize(new Dimension(730,1800));
        textPane.setFont(customSmallFont);
        TipsTextDisplayPanel tipsTextDisplayPanel=new TipsTextDisplayPanel();
        textPane.setBackground(new Color(0,0,0,0f));
        JScrollPane scrollPane=new JScrollPane(tipsTextDisplayPanel);
        scrollPane.setPreferredSize(new Dimension(730,580));
        scrollPane.setMaximumSize(new Dimension(730,580));
        scrollPane.setMinimumSize(new Dimension(730,580));
        tipsTextDisplayPanel.add(textPane);
        tipsTextDisplayPanel.setPreferredSize(tipsTextDisplayPanel.getPreferredSize());
        //scrollPane.add(textArea);
        this.add(scrollPane);

    }

    private void LoadSmallFont(String path)
    {
        try {
            InputStream myStream= new BufferedInputStream(new FileInputStream(path));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            customSmallFont = ttfBase.deriveFont(Font.BOLD,16);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Font LoadFontWithFontSize(int fontSize) {
        Font customFont;
        try {
            InputStream myStream = new BufferedInputStream(new FileInputStream("src/resources/Fonts/Roman SD.ttf"));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            customFont = ttfBase.deriveFont(Font.BOLD, fontSize);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customFont;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        //g2d.setColor(new Color());
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
    }
}
