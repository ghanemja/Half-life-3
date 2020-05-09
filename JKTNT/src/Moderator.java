import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Moderator extends User {

    public Moderator(String user) {
        super(user);
        // TODO Auto-generated constructor stub
    }
    /*
     * Given a comment section this allows the moderator to delete comments 
     * 
     * This will break later because there will be repeat IDs
     */
    public boolean deleteComment(final Game g, final int commId) {
        boolean success = false;
        try {
            
            File input = new File(g.getCommentFile());
            File temp = new File("temp.csv");
        
            Scanner reader = new Scanner(input);
            FileWriter write = new FileWriter(temp, true);
            
            String dummy;
            int i = 0;
            while (reader.hasNextLine()) {
                dummy = reader.nextLine();
                String[] commInfo = dummy.split(",");
                if (Integer.parseInt(commInfo[3]) == commId) {
                    success = true;
                    continue;
                } else {
                    write.append(commInfo[0] + "," + commInfo[1] + "," + commInfo[2] + "," + i + "\n");
                    i++;
                }
            }
            write.close();
            reader.close();
            input.delete();
            temp.renameTo(input);           
        } catch (IOException e) {           
            // TODO Auto-generated catch block
            e.printStackTrace();
            return success;
        }
        
        
        return success;
    }
    
}
