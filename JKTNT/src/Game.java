import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.print.DocFlavor.URL;
import javax.swing.*;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/*
 * This is how we draw graphics on an object.
 * We want to draw things on a JButton, so:
 * 1. Extend JButton
 * 2. Override paintComponent 
 * 3. Draw our graphics in the paintComponent method
 */

public class Game extends JPanel {
    private boolean showGame = true;
    private double gameRating;
    private int gameId;
    private Image image;
    private JLabel priceTag;
    private JButton btn;
    private String name;
    private String description;
    private String organization;
    private String storeLink;
    private String[] platforms;
    private double price = 0.00;
    private JTextArea gamePage;
    private JTextArea commPanel;
    private JPanel gamePanel;
    private String commentFile;

    /**
     * Creates a Game object as a panel, for users in the future
     * @param name Name of the game, for display and other use
     * @param path Path to the picture of the game
     */
    public Game(String name, String path) {
        super();
        this.name = name;
        this.setPreferredSize(new Dimension(250,320));
        this.setLayout(new BorderLayout());

        JPanel gameInfo = new JPanel();
        
        btn = new JButton(name);
        btn.setPreferredSize(new Dimension(250, 20));
        priceTag = new JLabel("Price: $" + price);
        priceTag.setPreferredSize(new Dimension(250, 20));

        image = new ImageIcon(path).getImage();
        //Image newImg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        //btn.setIcon(new ImageIcon(image));
        gameInfo.add(btn, BorderLayout.NORTH);
        gameInfo.add(priceTag, BorderLayout.SOUTH);
        this.add(gameInfo, BorderLayout.SOUTH);
    }

    /**
     * Creates a Game object as a panel
     * @param name Name of the game, for display and other use
     * @param path Path to the picture of the game
     * @param price Price of the game, the administrator can add price
     */
    public Game(int id, boolean show, String name, String path, double price, String cFile, String description) {
        super();
        this.showGame = show;
        this.setGameId(id);
        this.name = name;
        this.price = price;
        this.description = description;
        this.commentFile = ".\\comments\\" + cFile;
        this.setPreferredSize(new Dimension(250,360));
        this.setLayout(new BorderLayout());
        JPanel gameInfo = new JPanel();
        gameInfo.setPreferredSize(new Dimension(250, 60));
        ArrayList<Comment> commentsL = readComments();
        btn = new JButton(name);
        btn.setPreferredSize(new Dimension(250, 20));
        priceTag = new JLabel("Price: $" + price + "      Rating: " + gameRating + "/5 Star(s)");
        priceTag.setPreferredSize(new Dimension(250, 20));

        image = new ImageIcon(path).getImage();
        //Image newImg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        //btn.setIcon(new ImageIcon(image));
        gameInfo.add(btn, BorderLayout.NORTH);
        gameInfo.add(priceTag, BorderLayout.CENTER);
        this.add(gameInfo, BorderLayout.SOUTH);
        createGamePanel(description, commentsL);
    }

    private void createGamePanel(String desc, ArrayList<Comment> commentsList) {
        JTextArea comments = new JTextArea();
        gamePanel = new JPanel();
        
        gamePanel.setPreferredSize(new Dimension(1000,500));
        comments.setSize(450, 500);
        String dummy = "";
        for (int i = 0; i < commentsList.size(); i++) {
            dummy += commentsList.get(i).getUser() + ": " 
                    + commentsList.get(i).getComment() + "\n Game Rating: "
                    + commentsList.get(i).getRating() + "      ID: " + commentsList.get(i).getCommentNum() + "\n";
        }
        comments.setText("Comments:\n\n" + dummy);
        comments.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        comments.setEditable(false);
        comments.setLineWrap(true);
        comments.setWrapStyleWord(true);
        JTextArea description = new JTextArea();
        description.setSize(450, 500);
        // temporary text for 1 game
        description.setText("Game Description:\n" + desc + "\n\nUser Rating: " + this.getGameRating() + "/5 star(s)");
        description.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        description.setEditable(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        gamePage = description;
        commPanel = comments;
        gamePanel.add(gamePage, BorderLayout.WEST);
        gamePanel.add(commPanel, BorderLayout.EAST);
    }

    private ArrayList<Comment> readComments() {
        ArrayList<Comment> comments = new ArrayList<Comment>(0);
        try {
            BufferedReader read = new BufferedReader(new FileReader(commentFile));
            String dummy;
            int commCount = 0;
            this.gameRating = 0;
            while ((dummy = read.readLine()) != null) {
                String[] commentInfo = dummy.split(",");
                Comment comm = new Comment(commentInfo[0], commentInfo[1], Integer.parseInt(commentInfo[2]),
                        Integer.parseInt(commentInfo[3]));
                comments.add(comm);
                commCount++;
                this.gameRating += comm.getRating();
            }
            Collections.reverse(comments);
            this.gameRating = Math.round(gameRating / commCount);
            read.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return comments;
    }

    @Override // This method already exists in JButton
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //Tells the JButton to do its own drawing

        // Starting here, draw our graphics
        // All graphics get drawn by starting with g.
        g.drawImage(image, 0, 0, 250,300, this);	
    }
    public void setButtonList(ActionListener e) {
        btn.addActionListener(e);
    }
    public JButton getButton() {
        return btn;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    
    public String getCommentFile() {
        return commentFile;
    }
    
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * @return the storeLink
     */
    public String getStoreLink() {
        return storeLink;
    }

    /**
     * @param storeLink the storeLink to set
     */
    public void setStoreLink(String storeLink) {
        this.storeLink = storeLink;
    }

    /**
     * @return the platforms
     */
    public String[] getPlatforms() {
        return platforms;
    }

    /**
     * @param platforms the platforms to set
     */
    public void setPlatforms(String[] platforms) {
        this.platforms = platforms;
    }

    /**
     * @return the gameId
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    /**
     * @return the gameRating
     */
    public double getGameRating() {
        return gameRating;
    }
    /**
     * @return the gamePanel
     */
    public JPanel getGamePanel() {
        return gamePanel;
    }
    /**
     * @return whether or not to show the game
     */
    public boolean getShowGame() {
        return showGame;
    }
}
