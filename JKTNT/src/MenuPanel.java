
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author tover
 *
 */
public class MenuPanel {
    private JPanel mainScreen;
    
    private JButton search = new JButton("Search");
    private JButton clear = new JButton("Clear");
    private JButton loginSet = new JButton("Login");
    private JButton registerSet = new JButton("Register");
    private JButton menuBackBtn = new JButton("Back");
    private JButton registerCheck = new JButton("Register");
    private JButton loginCheck = new JButton("Login");
    private JButton logoutBtn = new JButton("Logout");
    private JButton adminPage = new JButton("Admin Page");
    private JButton submitReq = new JButton("Submit Request");
    
    private TextField searchQuery = new TextField("", 30);
    private TextField user = new TextField("", 20);
    private TextField pass = new TextField("", 20);
    
    private JComboBox<String> box;
    
    public MenuPanel() {
        mainScreen = new JKTNTPanel();
        mainScreen.setLayout(new FlowLayout());
        mainScreen.setPreferredSize(new Dimension(1000, 100));


        JLabel sort = new JLabel("Sort By:");
        mainScreen.add(sort);
        //add a selection box for sorting, to add more item just add to the string [] 
        //and change the filter method
        String[] selection = {"A->Z", "Z->A", "High->Low", "Low->High", "Rate:High->Low", "Rate:Low->High"};
        box = new JComboBox<String>(selection);
        // box.addActionListener(clickListener);
        mainScreen.add(box);

        // rateBox.addActionListener(clickListener);
        // creates a Search button with event listener attached
        mainScreen.add(search);
        mainScreen.add(searchQuery);
        // search.addActionListener(clickListener);

        // creates a button to clear the search bar
        mainScreen.add(clear);
        
        // creates a Login button with event listener attached
        loginSet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent click) {
                login();
            }
        });
        mainScreen.add(loginSet);
        

        // Create a register button with event listener
        registerSet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent click) {
                registerUser();
            }
        });
        mainScreen.add(registerSet);

        mainScreen.setVisible(true);

    }
    /*
     * Make sure to call read games and load games after calling this function.
     */
    public void registerUser() {
        mainScreen.removeAll();

        JLabel uname = new JLabel("Enter Username");
        JLabel pwd = new JLabel("Enter Password");
        mainScreen.add(uname);
        mainScreen.add(user);
        mainScreen.add(pwd);
        mainScreen.add(pass);

        mainScreen.add(registerCheck);
        // registerCheck.addActionListener(clickListener);

        // menuBackBtn.addActionListener(clickListener);
        mainScreen.add(menuBackBtn);
        mainScreen.setVisible(true);

        mainScreen.revalidate();
        mainScreen.repaint();
    }
    
    public void login() {
        mainScreen.removeAll();
        user = new TextField("", 20);
        pass = new TextField("", 20);

        mainScreen.add(new JLabel("Username"));
        mainScreen.add(user);
        mainScreen.add(new JLabel("Password"));
        mainScreen.add(pass);

        // creates a Login button with event listener attached
        mainScreen.add(loginCheck);
        // loginCheck.addActionListener(clickListener);

        // menuBackBtn.addActionListener(clickListener);
        mainScreen.add(menuBackBtn);
        mainScreen.setVisible(true);

        mainScreen.revalidate();
        mainScreen.repaint();
    }

    public void menuBack(final String userName, final String modName, final String adminName) {
        mainScreen.removeAll();     
        JLabel sort = new JLabel("Sort By:");
        mainScreen.add(sort);

        mainScreen.add(box);
        // creates a Search button with event listener attached
        mainScreen.add(search);
        // creates a empty 20px wide textField
        mainScreen.add(searchQuery);
        // creates a button to clear the search bar
        mainScreen.add(clear);
        // creates a Login button with event listener attached
        if (userName.isEmpty() && modName.isEmpty() && adminName.isEmpty()) {
            mainScreen.add(loginSet);                
            mainScreen.add(registerSet);
        } else {
            mainScreen.add(logoutBtn);
            // logoutBtn.addActionListener(clickListener);
        }
        if (adminName.isEmpty() && (!modName.isEmpty() || !userName.isEmpty())) {
            mainScreen.add(submitReq);
            // submitReq.addActionListener(clickListener);
        } else if (!adminName.isEmpty()){
            mainScreen.add(adminPage);
            // adminPage.addActionListener(clickListener);
        }
        mainScreen.revalidate();
        mainScreen.repaint();
    }
    
    public String getUserText() {
        return user.getText();
    }
    
    public String getPassText() {
        return pass.getText();
    }
    
    public String getSearchText() {
        return searchQuery.getText();
    }
    
    public JPanel getMainScreenPanel() {
        return mainScreen;
    }
    
    public JButton getSearch() {
        return search;
    }
    
    public JButton getClear() {
        return clear;
    }
    
    public JButton getLoginCheck() {
        return loginCheck;
    }
    
    public JButton getRegisterCheck() {
        return registerCheck;
    }
    
    public JButton getAdminPage() {
        return adminPage;
    }
    
    public JButton getSubmitReq() {
        return submitReq;
    }
    
    public JButton getLogoutBtn() {
        return logoutBtn;
    }
    
    public JComboBox<String> getSortBox() {
        return box;
    }
    
    public void addActListMenuBack(ActionListener e) {
        menuBackBtn.addActionListener(e);
    }
    
    public void addActListSearch(ActionListener e) {
        search.addActionListener(e);
    }
    
    public void addActListClear(ActionListener e) {
        clear.addActionListener(e);;
    }
    
    public void addActListLoginCheck(ActionListener e) {
        loginCheck.addActionListener(e);
    }
    
    public void addActListRegisterCheck(ActionListener e) {
        registerCheck.addActionListener(e);
    }
    
    public void addActListAdminPage(ActionListener e) {
        adminPage.addActionListener(e);
    }
    
    public void addActListSubmitReq(ActionListener e) {
        submitReq.addActionListener(e);
    }
    
    public void addActListLogoutBtn(ActionListener e) {
        logoutBtn.addActionListener(e);
    }
    
    public void addActListBox(ActionListener e) {
        box.addActionListener(e);
    }
    
    public void resetQuery(final String u, final String m, final String a) {
        searchQuery.setText("");
        menuBack(u, m, a);
    }
    
    public void resetUserPassFields() {
        user.setText("");
        pass.setText("");
    }

    
}

