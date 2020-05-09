
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BottomPanel {
    private JPanel bottom;

    public BottomPanel(final String userName, final String modName, final String adminName) {
        bottom = new JPanel();
        updateBotPanel(userName, modName, adminName);
    }
    
    public void updateBotPanel(final String userName, final String modName, final String adminName) {
        bottom.removeAll();
        bottom.setLayout(new FlowLayout());
        // MyButtonListener buttonListener = new MyButtonListener();
        JLabel label = new JLabel("Welcome to JKTNT!");
        if (!adminName.isEmpty()) {
            label.setText(adminName + " is logged in and is an Admin!");
        } else if (!modName.isEmpty()) {
            label.setText(modName + " is logged in and is a Moderator!");
        } else if (!userName.isEmpty()){
            label.setText(userName + " is logged in and is a User!");
        }
        // These anonymous JButtons should be replaced with instance variables
        bottom.add(label);
        bottom.setVisible(true);

        bottom.revalidate();
        bottom.repaint();
    }
    
    public JPanel getBottomPanel() {
        return bottom;
    }
}
