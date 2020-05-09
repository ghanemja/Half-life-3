import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

public class UserRequest {
    private JPanel requestPanel = new JKTNTPanel();
    private JButton requestBtn = new JButton("Request");
    private JButton back = new JButton("Back");
    private TextField gameTitle = new TextField("", 30);
    private boolean passed;

    public UserRequest() {
        requestPanel.setLayout(new FlowLayout());
        requestPanel.setPreferredSize(new Dimension(1000, 800));

        JLabel title = new JLabel("Game Title:");
        requestPanel.add(title);

        // creates a Request button with event listener attached
        requestBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                requestGame(gameTitle.getText());
            }
        });

        requestPanel.add(gameTitle);
        requestPanel.add(requestBtn);
        requestPanel.add(back);

    }

    public boolean requestGame(final String title) {
        try {
            int count = 0;
            File req = new File("requests.csv");
            FileWriter write = new FileWriter(req, true);
            Scanner reader = new Scanner(req);
            String dummy = "";
            passed = true;
            while (reader.hasNextLine()) {
                dummy = reader.nextLine();
                String[] s = dummy.split(",");
                if(s[1].equals(title)) {
                    JOptionPane.showMessageDialog(null, "Request already exists, please wait for approval.");
                    passed = false;
                }
                count++; 
            }
            if(passed) {
                if(!title.equals("")) {
                    write.append(count + "," + title +  "\n");
                    count++;
                    JOptionPane.showMessageDialog(null, "Request Success.");

                } else {
                    JOptionPane.showMessageDialog(null, "Please enter valid game title.");
                }
            }
            write.close();
            reader.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    public void setActListBack(ActionListener e) {
        back.addActionListener(e);
    }

    public JPanel getRequest() {
        return requestPanel;
    }

    public JButton getButton() {
        return requestBtn;
    }
}
