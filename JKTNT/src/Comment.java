import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;

public class Comment {
    private String user;
    private String comment;
    private int rating;
    private int commentNum;
    private JButton upDoot;
    private JButton downDoot;
    private JButton reply;
    
    public Comment(String user, String comment, int rating, int commNum) {
        setUser(user);
        setComment(comment);
        setRating(rating);
        setCommentNum(commNum);
        upDoot = new JButton("/\\");
        upDoot.setPreferredSize(new Dimension(5, 5));
        downDoot = new JButton("\\/");
        downDoot.setPreferredSize(new Dimension(5, 5));
        reply = new JButton("reply");
        reply.setPreferredSize(new Dimension(5, 5));
        
    }
    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }
    /**
     * @param user the user to set
     */
    private void setUser(String user) {
        this.user = user;
    }
    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }
    /**
     * @param comment the comment to set
     */
    private void setComment(String comment) {
        this.comment = comment;
    }
    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }
    /**
     * @param rating the rating to set
     */
    private void setRating(int rating) {
        this.rating = rating;
    }
    /**
     * @return the commentNum
     */
    public int getCommentNum() {
        return commentNum;
    }
    /**
     * @param commentNum the commentNum to set
     */
    private void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    
}
