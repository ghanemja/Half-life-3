import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class JKTNTFrame extends JFrame {

	private JLabel label;
	private JKTNTPanel mainScreen;
	private JKTNTPanel gamePanel;
	private JPanel bottom;
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private ArrayList<TextField> textFields = new ArrayList<TextField>();
	private btnListener clickListener;
	private ArrayList<Game> g;
	private User u;

	public static void main(String[] args) throws InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, ClassNotFoundException {
		// gets the sleek look
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}
		new JKTNTFrame();

	}

	public JKTNTFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		// creates the window
		super("JKTNT Window");
		// Line of code to set the size and location

		this.setSize(new Dimension(800, 800));
		this.setLocationRelativeTo(null); // starts center screen
		this.setLayout(new BorderLayout());
		clickListener = new btnListener();
		// Line of code that says what happens when you click close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    u = new User("");
	    label = new JLabel("Welcome to JKTNT!");
	    bottom = new JPanel();
		// Call helper methods to set up various sections
		setupBottomPanel();
		setupMiddlePanel();
		setupGames();
		this.pack();
		this.setVisible(true);
		// Initiate the user object. 
	}

    /**
     * Returns the position of a button in the array list
     * @param s the name of the button to be searched for
     * @return returns the index of the button
     * returns -1 if not found
     */
	public int buttonPosition(String s) {
		int index = -1;
		for (int i = 0; i < buttons.size(); i++) {
			if (s == buttons.get(i).getText()) {
				index = i;
			}
		}
		return index;
	}
	
	 /**
     * Returns the position of a text field in the array list
     * @param t the name of the text field to be searched for
     * @return returns the index of the text field
     * returns -1 if not found
     */
	public int textFieldPosition(String t) {
		int index = -1;
		for (int i = 0; i < textFields.size(); i++) {
			if (t == textFields.get(i).getName()) {
				index = i;
			}
		}
		return index;
	}
	
	// Sets up the top panel where some graphics-related buttons
	// will go.
	private void setupBottomPanel() {
	    bottom.removeAll();
        bottom.setLayout(new FlowLayout());
        // MyButtonListener buttonListener = new MyButtonListener();
        if (u.getUserName().isEmpty()) {
            label.setText("Welcome to JKTNT!");
        } else {
            if (u.isAdmin(u.getUserName())) {
                label.setText(u.getUserName() + " is logged in and is an Admin!");
            } else if (u.isMod(u.getUserName())) {
                label.setText(u.getUserName() + " is logged in and is a Moderator!");
            } else {
                label.setText(u.getUserName() + " is logged in and is a User!");
            }
        }
        // These anonymous JButtons should be replaced with instance variables
        bottom.add(label);
        bottom.setVisible(true);
        
        bottom.revalidate();
        bottom.repaint();
        // Once the panel is set up, add it to the frame
        this.add(bottom, BorderLayout.SOUTH);

	}
	
	private void setupMiddlePanel() {
        mainScreen = new JKTNTPanel();
        mainScreen.setLayout(new FlowLayout());
        mainScreen.setPreferredSize(new Dimension(1000, 100));
        
        buttons.add(new JButton("A->Z"));
        buttons.add(new JButton("Z->A"));
        buttons.add(new JButton("High->Low"));
        buttons.add(new JButton("Low->High"));
        buttons.add(new JButton("Search"));
        buttons.add(new JButton("Clear"));
        buttons.add(new JButton("Login"));
        buttons.add(new JButton("register"));
        
        TextField searchQuery = new TextField("", 30);
        textFields.add(searchQuery);

        for (TextField t : textFields) {
        	mainScreen.add(t);
        }
        
        for (JButton b : buttons) {
        	mainScreen.add(b);
        	b.addActionListener(clickListener);
        }
        
        mainScreen.setVisible(true);
        
        this.add(mainScreen, BorderLayout.NORTH);
	}

	// Sets up the middle panel where the graphics will go


	private void setupGames() {
        // set up a panel inside the mainScreen panel to display games
        gamePanel = new JKTNTPanel();
        // JScrollPane scrollFrame = new JScrollPane(gamePanel);
        gamePanel.setPreferredSize(new Dimension(400, 1500));
        // scrollFrame.setPreferredSize(mainScreen.getMaximumSize());
        JScrollPane scroll = new JScrollPane(gamePanel);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setLayout(new ScrollPaneLayout());
        scroll.setPreferredSize(new Dimension(1000, 800));
        gamePanel.setLayout(new FlowLayout());
        // mainScreen.setPreferredSize(mainScreen.getPreferredSize());
        gamePanel.setVisible(true);
        this.add(scroll, BorderLayout.CENTER);
        g = readGames();
		loadGames(g);
	}
	
	@SuppressWarnings("null")
    private ArrayList<Game> readGames() {
	    ArrayList<Game> games = new ArrayList<Game>(0);
	    try {
            BufferedReader read = new BufferedReader(new FileReader("gameList.csv"));
            String dummy;
            for (int i = 0; (dummy = read.readLine()) != null; i++) {
                String[] gameInfo = dummy.split(",");
                games.add(new Game(gameInfo[0], gameInfo[1], Double.parseDouble(gameInfo[2]), gameInfo[3], gameInfo[4]));
                games.get(i).getButton().addActionListener(clickListener);
            }
            read.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	   
        return games;
	    
	}

	// testing for changing panels
	private void displayDetailedPage(Game g) {
		gamePanel.removeAll();
		gamePanel.add(g);

		// make the text area scrollable
		JScrollPane scroll = new JScrollPane(g.getGamePanel());
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(500, 500));

		JScrollPane scroll1 = new JScrollPane(g.getCommPanel());
        scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll1.setPreferredSize(new Dimension(500, 500));
        
		gamePanel.add(scroll);
		gamePanel.add(scroll1);
		
		if (!u.getUserName().isEmpty()) {
		   TextField commArea = new TextField("", 100);
		   textFields.add(commArea);
		   gamePanel.add(commArea);
		}

		// create a back button to go back to home page
		buttons.add(new JButton("Back"));
		
		
		gamePanel.add(buttons.get(buttonPosition("Back")));
		gamePanel.revalidate();
		gamePanel.repaint();

	}
	
	private void registerUser() {
	    mainScreen.removeAll();
	    
	    JLabel uname = new JLabel("Enter Username");
	    JLabel pwd = new JLabel("Enter Password");
	    TextField userR = new TextField("", 20);
	    TextField passR = new TextField("", 20);
	    textFields.add(userR);
	    textFields.add(passR);
	    mainScreen.add(uname);
	    mainScreen.add(pwd);
	    
	    buttons.add(new JButton("Register "));
	    buttons.get(buttonPosition("Register")).addActionListener(clickListener);
	    buttons.add(new JButton("Back "));
	    buttons.get(buttonPosition("Back ")).addActionListener(clickListener);
	    mainScreen.add(buttons.get(buttonPosition("Back ")));
	    mainScreen.add(buttons.get(buttonPosition("Register ")));
	    mainScreen.setVisible(true);
	    mainScreen.revalidate();
	    mainScreen.repaint();
	    
	    g = readGames();
	    loadGames(g);
	}

	/*
	 * This method simply displays the login screen and allows the user 
	 * to login to their account.
	 * 
	 */
	private void login() {
		mainScreen.removeAll();
        TextField user = new TextField("", 20);
        TextField pass = new TextField("", 20);
		// creates the username and password text fields
		JLabel uname = new JLabel("Username");
		JLabel psw = new JLabel("Password");
		textFields.add(user);
		textFields.add(pass);
		
		mainScreen.add(uname);
		mainScreen.add(user);
		mainScreen.add(psw);
		mainScreen.add(pass);

		// creates a Login button with event listener attached
		buttons.add(new JButton("Login "));
	    buttons.get(buttonPosition("Login ")).addActionListener(clickListener);
	    mainScreen.add(buttons.get(buttonPosition("Login ")));
	    
	    buttons.add(new JButton("Back"));
	    buttons.get(buttonPosition("Back")).addActionListener(clickListener);
	    mainScreen.add(buttons.get(buttonPosition("Back")));
		
	    mainScreen.setVisible(true);	
		mainScreen.revalidate();
        mainScreen.repaint();
        
        g = readGames();
		loadGames(g);
	}
	/*
	 * This checks if the user successfully logged in, we should use regex to check validity of the string.
	 * 
	 * Need option to log a user out
	 */
	private void checkLogin() {
		if (textFields.get(textFieldPosition("Username")).getText().isEmpty() || 
	    		textFields.get(textFieldPosition("Password")).getText().isEmpty()) {   
	        JOptionPane.showMessageDialog(mainScreen, "Please enter in valid user/pass!");
	    } else if (!u.getUserName().isEmpty()) {
	        JOptionPane.showMessageDialog(mainScreen, "User already logged in!");
	    } else{
	        if (u.loginUser(textFields.get(textFieldPosition("Username")).getText(), 
	        		textFields.get(textFieldPosition("Password")).getText())) {
                JOptionPane.showMessageDialog(mainScreen, "Successfully Logged In!");
                menuBack();
                setupBottomPanel();
	        } else {
                JOptionPane.showMessageDialog(mainScreen, "User name does not exist!");	            
	        }
	    }
	}
	/*
     * This checks if the registration is good and then lets the user know if registration was successful.
     */
	private void checkReg() {
	    // Can add more checking here if we want to define a password by a regex expression.
	    if (textFields.get(textFieldPosition("userR")).getText().isEmpty() || 
	    		textFields.get(textFieldPosition("passR")).getText().isEmpty()) {
	        JOptionPane.showMessageDialog(mainScreen, "Please enter in valid user/pass!");
	    } else {
	        if (u.registerUser(textFields.get(textFieldPosition("userR")).getText(),
	        		textFields.get(textFieldPosition("passR")).getText())) {
	            JOptionPane.showMessageDialog(mainScreen, "Successfully Registered!");
	            menuBack();
	            setupBottomPanel();
	        } else {
	            JOptionPane.showMessageDialog(mainScreen, "User name already taken!");
	        }
	    }
	}
	/*
	 * Logs out the user and displays respective message. 
	 */
	private void logoutMsg() {
	    boolean goodLogOut = u.logout();
	    if (goodLogOut) {
	        setupBottomPanel();
	        menuBack();
	        JOptionPane.showMessageDialog(mainScreen, "Successful logout");
	    } else {
            JOptionPane.showMessageDialog(mainScreen, "Already logged out or something");
	    }
	}
	/*
	 * Searches games and filters the games to display by the query
	 */
	private void searchGames() {
	    if (textFields.get(textFieldPosition("searchQuery")).getText().isEmpty()) {
	        JOptionPane.showMessageDialog(mainScreen, "Please enter in valid query!");
	    } else {
	        gamePanel.removeAll();
	        filter sort = new filter(g);
	        ArrayList<Game> gameList = sort.search(textFields.get(textFieldPosition("searchQuery")).getText());
	        for (int i = 0; i < gameList.size(); i++) {
	            gamePanel.add(gameList.get(i));
	        }
	        gamePanel.revalidate();
	        gamePanel.repaint();
	    }
	}
	/*
	 * Will filter the game list by high to low price or low to high price.
	 * Or alphabetically 
	 */
	private void filterPrice(JButton btn) {
	    gamePanel.removeAll();
	    filter sort = new filter(g);
	    if (btn.getText() == buttons.get(buttonPosition("hiToLo")).getText()) {
	    	loadGames(sort.priceHitoLo());
	    } else if (btn.getText() == buttons.get(buttonPosition("loToHi")).getText()) {
	    	loadGames(sort.priceLotoHi());
	    } else if (btn.getText() == buttons.get(buttonPosition("atoZ")).getText()) {
	    	loadGames(sort.atoZ());
	    }
	    loadGames(sort.ztoA());
	}
	/*
	 * Provided an array or an arrayList, this method will load all the games
	 * from the given list to the screen.
	 */
	private void loadGames(ArrayList<Game> gameList) {
	    gamePanel.removeAll();
	    for (int i = 0; i < gameList.size(); i++) {
	        gamePanel.add(gameList.get(i));
	    }
	    gamePanel.revalidate();
	    gamePanel.repaint();
	}

	private void menuBack() {
		mainScreen.removeAll();
	    
		for (JButton b : buttons) {
			if (b.getText() == "atoZ" || b.getText() == "ztoA" ||
					b.getText() == "hiToLo" || b.getText() == "loToHi" ||
					b.getText() == "search" || b.getText() == "searchQuery"
					|| b.getText() == "clear") {
				mainScreen.add(b);
			}
		}
		
		// creates a Login button with event listener attached
		if (u.getUserName().isEmpty()) {
		    mainScreen.add(buttons.get(buttonPosition("Login")));
		    mainScreen.add(buttons.get(buttonPosition("Register")));
		} else {
			buttons.add(new JButton("Logout"));
			mainScreen.add(buttons.get(buttonPosition("Logout")));
		    buttons.get(buttonPosition("Logout")).addActionListener(clickListener);
		}
		
		mainScreen.revalidate();
        mainScreen.repaint();
        
        g = readGames();
		loadGames(g);
	}

	// Sets up the bottom panel with buttons

	// button listener for Search, Login, etc.
	public class btnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object btn = e.getSource();
			if (btn == buttons.get(buttonPosition("Search"))) {
				searchGames();
			} else if (btn == buttons.get(buttonPosition("Register"))) {
			    registerUser();
			} else if (btn == buttons.get(buttonPosition("Register "))) {
			    checkReg();
			} else if (btn == buttons.get(buttonPosition("Login"))) {
				login();
		    } else if (btn == buttons.get(buttonPosition("Clear"))) {
				// do nothing yet, need diagram
				loadGames(g);
				textFields.get(textFieldPosition("searchQuery")).setText("");
			} else if (btn == textFields.get(textFieldPosition("Back "))) {
				menuBack();
			} else if (btn == textFields.get(textFieldPosition("Back"))) {
				loadGames(g);
			} else if (btn == textFields.get(textFieldPosition("Login "))) {
			    checkLogin();
			} else if (btn == textFields.get(textFieldPosition("hiToLo")) || btn == textFields.get(textFieldPosition("loToHi")) 
					|| btn == textFields.get(textFieldPosition("atoZ")) || btn == textFields.get(textFieldPosition("ztoA"))) {
			    filterPrice((JButton)(btn));
			} else if (btn == textFields.get(textFieldPosition("Logout"))) {
			    logoutMsg();
			} else {
				// react based on the game clicked
				for (int i = 0; i < g.size(); i++) {
					if (g.get(i) == null) {
						break;
					}
					if (btn.equals(g.get(i).getButton())) {
						displayDetailedPage(g.get(i));
					}
				}
				// trying to get to a new page
			}
		}

	}

}