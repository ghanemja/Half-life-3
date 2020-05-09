import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class JKTNTFrame extends JFrame {

	private ArrayList<Game> g;
	private int currGame = 0;
	private User u;
	private Admin a;
	private Moderator m;
	private static JKTNTFrame frame;

	protected MenuPanel main;
	protected BottomPanel bottom;
	protected GamesPanel gamePanel;
	protected DetailedGamesPage detail;

	protected JPanel game;
	
	protected UserRequest re;
    protected AdminPanel ad;

	public static void main(String[] args) throws InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, ClassNotFoundException {
		// gets the sleek look
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}
		frame = new JKTNTFrame();

	}

	private JKTNTFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		// creates the window
		super("JKTNT Window");
		// Line of code to set the size and location

		this.setSize(new Dimension(800, 800));
		this.setLocationRelativeTo(null); // starts center screen
		this.setLayout(new BorderLayout());
		// clickListener = new btnListener();
		// Line of code that says what happens when you click close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		u = new User("");
		a = new Admin("");
		m = new Moderator("");
		// label = new JLabel("Welcome to JKTNT!");
		bottom = new BottomPanel(u.getUserName(), m.getUserName(), a.getUserName());
		this.add(bottom.getBottomPanel(), BorderLayout.SOUTH);
		// Call helper methods to set up various sections
		main = new MenuPanel(); // Top menu panel
		this.add(main.getMainScreenPanel(), BorderLayout.NORTH);

		gamePanel = new GamesPanel();
		
		this.add(gamePanel.getGamePanel(), BorderLayout.CENTER);

		//comment out for original games
		ad = new AdminPanel();
        // this.add(ad.getPanel());
        
        re = new UserRequest();
//        this.add(re.getRequest());

		this.pack();
		this.setVisible(true);

		g = gamePanel.getGameList();
		detail = new DetailedGamesPage(null, u.getUserName(), m.getUserName(), a.getUserName());

		loadAdReButtons();
		loadButtons();
		loadGameButtons();
		loadDetailButtons();
		// Initiate the user object.
	}
	
	private void loadDetailButtons() {
	    detail.addActListSubmit(new ActionListener() {
            public void actionPerformed(ActionEvent click) {
                if (!u.getUserName().isEmpty())
                    u.postComment(detail.getComment(), g.get(currGame), detail.getRating());
                else if (!m.getUserName().isEmpty())
                    m.postComment(detail.getComment(), g.get(currGame), detail.getRating());
                else if (!a.getUserName().isEmpty())
                    a.postComment(detail.getComment(), g.get(currGame), detail.getRating());
                //g = gamePanel.readGames();
                gamePanel.updateGames();
                g = gamePanel.getGameList();
                detail.updateDetails(g.get(currGame), u.getUserName(), m.getUserName(), a.getUserName());
                loadGameButtons();
                // reloadPage();
            }
        });
	    detail.addActListRemoveBtn(new ActionListener() {
	        public void actionPerformed(ActionEvent click) {
	            removeComment();
	        }
	    });
	}
	
	
	private void loadAdReButtons() {
	    ad.setActListBack(new ActionListener() {
	        public void actionPerformed(ActionEvent click) {
	            frame.remove(ad.getPanel());
	            frame.add(gamePanel.getGamePanel());
	            reloadPage();
	        }
	    });
	    re.setActListBack(new ActionListener() {
	        public void actionPerformed(ActionEvent click) {
	            frame.remove(re.getRequest());
	            frame.add(gamePanel.getGamePanel());
	            reloadPage();
	        }
	    });
	}
	
	private void reloadPage() {
		this.revalidate();
		this.repaint();
	}

	private void loadButtons() {
		main.addActListBox(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				gamePanel.filterPrice((String) main.getSortBox().getSelectedItem());
			}
		});
		main.addActListAdminPage(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				frame.remove(detail.getDetails());
				frame.remove(gamePanel.getGamePanel());
				frame.add(ad.getPanel());
				reloadPage();
			}
		});
		main.addActListClear(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				gamePanel.filterPrice("");
				main.resetQuery(u.getUserName(), m.getUserName(), a.getUserName());
			}
		});
		main.addActListLoginCheck(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				checkLogin(main.getUserText(), main.getPassText());
			}
		});
		main.addActListLogoutBtn(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				logoutMsg();
			}
		});
		main.addActListSearch(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				gamePanel.searchGames(main.getSearchText(), g);
			}
		});
		main.addActListSubmitReq(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				frame.remove(gamePanel.getGamePanel());
				frame.remove(detail.getDetails());
				frame.add(re.getRequest());
				reloadPage();
			}
		});
		main.addActListRegisterCheck(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				checkReg(main.getUserText(), main.getPassText());
			}
		});
		main.addActListMenuBack(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				main.menuBack(u.getUserName(), m.getUserName(), a.getUserName());
			}
		});
	}

	private void loadGameButtons() {	    
		for (int i = 0; i < g.size(); i++) {
			g.get(i).setButtonList(new ActionListener() {
				public void actionPerformed(ActionEvent click) {				
					for (int i = 0; i < g.size(); i++) {
						if (g.get(i) == null) {
							break;
						} else {
							if(g.get(i).getButton().equals(click.getSource())) {
								frame.remove(gamePanel.getGamePanel());
								currGame = i;
								detail.updateDetails(g.get(i), u.getUserName(), m.getUserName(), a.getUserName());					
								detail.addActListBack(new ActionListener() {
						            public void actionPerformed(ActionEvent click) {
						                 gamePanel.filterPrice("");
						                 frame.remove(detail.getDetails());
						                 frame.add(gamePanel.getGamePanel(), BorderLayout.CENTER);
						                 reloadPage();
						                 //loadGameButtons();
						            }
						        }); 
						        							
								frame.add(detail.getDetails());
								reloadPage();
							}
						}
					}
				}
			});
		}
	}
	
	public void postLogin() {
	    String uName = u.getUserName();
	    String mName = m.getUserName();
	    String aName = a.getUserName();
	    main.menuBack(uName, mName, aName);
        bottom.updateBotPanel(uName, mName, aName);
        main.resetUserPassFields();
        if (detail.getDetails().isShowing()) {
            detail.updateDetails(g.get(currGame), uName, mName, aName);
        }
        g = gamePanel.getGameList();
        reloadPage();
	}
	
	/*
	 * This checks if the user successfully logged in, we should use regex to check
	 * validity of the string.
	 * 
	 * Need option to log a user out
	 */
	private void checkLogin(final String userN, final String passW) {
		if (userN.isEmpty() || passW.isEmpty()) {
			JOptionPane.showMessageDialog(main.getMainScreenPanel(), "Please enter in valid user/pass!");
		} else if (!u.getUserName().isEmpty() || !m.getUserName().isEmpty() || !a.getUserName().isEmpty()) {
			JOptionPane.showMessageDialog(main.getMainScreenPanel(), "User already logged in!");
		} else {
			if (u.loginUser(userN, passW)) {
				try {
					if (u.isMod(userN)) {
						u.logout();
						m.loginUser(userN, passW);
						JOptionPane.showMessageDialog(main.getMainScreenPanel(), "Successfully Logged In as Mod!");
					} else if (u.isAdmin(userN)) {
						u.logout();
						a.loginUser(userN, passW);
						JOptionPane.showMessageDialog(main.getMainScreenPanel(), "Successfully Logged In as Admin!");
					} else {
						JOptionPane.showMessageDialog(main.getMainScreenPanel(), "Successfully Logged In as User!");
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				postLogin();
				
			} else {
				JOptionPane.showMessageDialog(main.getMainScreenPanel(), "User name does not exist!");
			}
		}
	}

	/*
	 * This checks if the registration is good and then lets the user know if
	 * registration was successful.
	 */
	private void checkReg(final String userN, final String passW) {
		// Can add more checking here if we want to define a password by a regex
		// expression.
		if (userN.isEmpty() || passW.isEmpty()) {
			JOptionPane.showMessageDialog(main.getMainScreenPanel(), "Please enter in valid user/pass!");
		} else {
			if (u.registerUser(userN, passW)) {
				JOptionPane.showMessageDialog(main.getMainScreenPanel(), "Successfully Registered!");
				postLogin();
			} else {
				JOptionPane.showMessageDialog(main.getMainScreenPanel(), "User name already taken!");
			}
		}
	}

	/*
	 * Logs out the user and displays respective message.
	 */
	private void logoutMsg() {
		if (u.logout() || a.logout() || m.logout()) {
			main.menuBack(u.getUserName(), m.getUserName(), a.getUserName());
			bottom.updateBotPanel("", "", "");
			frame.remove(detail.getDetails());
			g = gamePanel.getGameList();
			frame.add(gamePanel.getGamePanel(), BorderLayout.CENTER);
			reloadPage();
			JOptionPane.showMessageDialog(main.getMainScreenPanel(), "Successful logout");
		} else {
			JOptionPane.showMessageDialog(main.getMainScreenPanel(), "Already logged out or something");
		}
	}
	/*
	 * If you enter a non int it will go breaky
	 */
	private void removeComment() {
	    int id = Integer.parseInt(detail.removeCommAText());
	    boolean success = m.deleteComment(g.get(currGame), id);
	    if (success) {
	        gamePanel.updateGames();
            g = gamePanel.getGameList();
            detail.updateDetails(g.get(currGame), u.getUserName(), m.getUserName(), a.getUserName());
            loadGameButtons();
	    } else {
	        JOptionPane.showMessageDialog(main.getMainScreenPanel(), "Enter a valid comment ID!");
	    }
	    
	}

}