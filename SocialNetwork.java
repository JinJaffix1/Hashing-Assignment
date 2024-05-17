import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;

/**
 * represents accounts and their relationship as a graph; 
 *
 * @author Saber Elsayed
 * @version Feb 2024
 */
public class SocialNetwork implements SocialNetworkInterface {

    protected Graph sn;

    /**
     * constructs a social network analyser object by reading data files
     */
    public SocialNetwork() {
        sn = new Graph();

    }

    public void processFile() {
    }

    public List<Node> suggestFriends(Node currentPerson) {
        List<Node> suggestFriendList = new LinkedList<Node>();
        return suggestFriendList;
    }

    @Override
    public String remindBDEvents(Node currentPerson) {
        return "";
    }

    public List<String> getMutualFriends(Node x, Node y) {
        List<String> friendList = new LinkedList<String>();
        return friendList;
    }


    /**
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        SocialNetwork driver = new SocialNetwork();
        System.out.println(driver.sn);
    }
}
