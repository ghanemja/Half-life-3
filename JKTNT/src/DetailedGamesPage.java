import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 * @author tover
 *
 */
public class DetailedGamesPage {
    private JPanel details = new JPanel();
    private JPanel bot = new JPanel();
    
    private TextField commArea = new TextField("", 100);
    private JButton submitComm = new JButton("Submit");
    
    private TextField removeCommA = new TextField("", 10);
    private JButton removeCommB = new JButton("Remove");
    
    private JComboBox<Integer> rateBox;
    
    private JButton back = new JButton("Back");
      
    public DetailedGamesPage(final Game g, final String userName, final String modName,
                             final String adminName) {
        
        Integer[] select = {0, 1, 2, 3, 4, 5};
        rateBox = new JComboBox<>(select);
        if (g != null) {
            updateDetails(g, userName, modName, adminName);
        }

    }
    
    public void updateDetails(final Game g, final String userName, final String modName, final String adminName) {
        details.removeAll();
        bot.removeAll();
        details.add(g, BorderLayout.WEST);

        // make the text area scrollable
        JScrollPane scroll = new JScrollPane(g.getGamePanel());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(1000, 500));
        details.add(scroll, BorderLayout.CENTER);

        if (!userName.isEmpty() || !modName.isEmpty() || !adminName.isEmpty()) {
            if (!modName.isEmpty()) {
                // removeCommB.addActionListener(clickListener);
                bot.add(new JLabel("Enter ID to remove:"), BorderLayout.SOUTH);
                bot.add(removeCommA, BorderLayout.SOUTH);
                bot.add(removeCommB, BorderLayout.SOUTH);
            }
            // submitComm.addActionListener(clickListener);
            bot.add(new JLabel("Comment:"));
            bot.add(commArea, BorderLayout.NORTH);
            bot.add(new JLabel("Rating:"));
            bot.add(rateBox, BorderLayout.NORTH);
            bot.add(submitComm, BorderLayout.NORTH);
        }

        // create a back button to go back to home page
        bot.add(back, BorderLayout.NORTH);
        // back.addActionListener(clickListener);
        bot.revalidate();
        bot.repaint();
        details.add(bot, BorderLayout.SOUTH);
        details.setBackground(Color.darkGray);
        details.revalidate();
        details.repaint();
    }
    
    public void addActListSubmit(ActionListener e) {
        submitComm.addActionListener(e);
    }
    
    public String removeCommAText() {
        return removeCommA.getText();
    }
    
    public void addActListBack(ActionListener e) {
        back.addActionListener(e);
    }
    
    public String getComment() {
        return commArea.getText();
    }
    
    public int getRating() {
        return (int)rateBox.getSelectedItem();
    }
    
    public JPanel getDetails() {
        return details;
    }
    
    public JButton getBackBtn() {
        return back;
    }
    
    public void addActListRemoveBtn(ActionListener e) {
        removeCommB.addActionListener(e);
    }
}
