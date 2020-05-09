
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 
 */

/**
 * @author tover
 *
 */
public class GamesPanel {
    private ArrayList<Game> g;
    private JPanel gamePanel;
	private JScrollPane scroll;

    public GamesPanel() {
    	gamePanel = new JPanel();		
		updateGames();
    }
    
    public void updateGames() {     
        // gamePanel.removeAll();
        gamePanel.setBackground(Color.GRAY);
        gamePanel.setPreferredSize(new Dimension(1000, 800));
        scroll = new JScrollPane(gamePanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
        g = readGames();
        loadGames(g);
        filterPrice("");
        // Change the scroll bar dynamically corresponding to the window size and game
        // entries.
        scroll.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                int len;
                if (scroll.getWidth() < 530) {
                    //System.out.println("1");
                    len = g.size() * 370;
                    gamePanel.setPreferredSize(new Dimension(400, len));
                } else if (scroll.getWidth() >= 530 && scroll.getWidth() <= 790) {
                    //System.out.println("2");
                    len = g.size() * 185;
                    gamePanel.setPreferredSize(new Dimension(400, len));
                } else if (scroll.getWidth() >= 790 && scroll.getWidth() <= 1040) {
                    //System.out.println("3");
                    len = g.size() * 135;
                    gamePanel.setPreferredSize(new Dimension(400, len));
                } else if (scroll.getWidth() >= 1040 && scroll.getWidth() <= 1290) {
                    //System.out.println("4");
                    len = g.size() * 105;
                    gamePanel.setPreferredSize(new Dimension(400, len));
                }else if (scroll.getWidth() >= 1290 && scroll.getWidth() <= 1560) {
                    //System.out.println("5");
                    len = g.size() * 90;
                    gamePanel.setPreferredSize(new Dimension(400, len));
                } else if (scroll.getWidth() >= 1560 && scroll.getWidth() <= 1820) {
                    //System.out.println("6");
                    len = g.size() * 80;
                    gamePanel.setPreferredSize(new Dimension(400, len));
                } else {
                    //System.out.println("big");
                    len = g.size() * 65;
                    gamePanel.setPreferredSize(new Dimension(400, len));
                }
            }
        });
        gamePanel.revalidate();
        gamePanel.repaint();
    }
    
    /*
     * Empty case will be handled where this is called. Takes query and list of visible games
     * and then filters the game based on the query provided
     * 
     * @param query - this is retrieved from the text box containing the search query.
     */
    public void searchGames(final String query, final ArrayList<Game> g) {
        gamePanel.removeAll();
        filter sort = new filter(g);
        ArrayList<Game> gameList = sort.search(query);
        for (int i = 0; i < gameList.size(); i++) {
            gamePanel.add(gameList.get(i));
        }
        gamePanel.revalidate();
        gamePanel.repaint();
    }
    /*
     * Reads games and displays the games that are to be visible.
     */
    private ArrayList<Game> readGames() {
        ArrayList<Game> games = new ArrayList<Game>(0);
        try {
            BufferedReader read = new BufferedReader(new FileReader("gameList.csv"));
            String dummy;
            while ((dummy = read.readLine()) != null) {
                String[] gameInfo = dummy.split(",");
                games.add(new Game(Integer.parseInt(gameInfo[0]), gameInfo[1].equals("true"), gameInfo[2], gameInfo[3], 
                        Double.parseDouble(gameInfo[4]), gameInfo[5], gameInfo[6]));
            }
            g = games;
            read.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return games;

    }
    /*
     * Provided an array or an arrayList, this method will load all the games
     * from the given list to the screen.
     */
    public void loadGames(ArrayList<Game> gameList) {
        gamePanel.removeAll();

        for (int i = 0; i < gameList.size(); i++) {
            if (gameList.get(i).getShowGame()) {
                gamePanel.add(gameList.get(i));
            }
        }
        gamePanel.revalidate();
        gamePanel.repaint();
    }
    /*
     * Will filter the game accordingly with user selection
     */
    public void filterPrice(String btn) {
        gamePanel.removeAll();
        filter sort = new filter(g);
        ArrayList<Game> gameList = new ArrayList<Game>(0);
        if (btn.equals("High->Low")) {
            gameList = sort.priceHitoLo();
        } else if (btn.equals("Low->High")){
            gameList = sort.priceLotoHi();
        } else if (btn.equals("Z->A")) {
            gameList = sort.ztoA();
        } else if (btn.equals("Rate:High->Low")) { 
            gameList = sort.rateHitoLo();
        } else if (btn.equals("Rate:Low->High")) { 
            gameList = sort.rateLotoHi();
        } else {
            gameList = sort.atoZ();
        }
        loadGames(gameList);
    }
    
    public JScrollPane getGamePanel() {
        return scroll;
    }
    
    public ArrayList<Game> getGameList() {
        return g;
    }
}
