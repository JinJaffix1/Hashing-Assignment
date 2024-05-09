
import java.util.List;

/**
 * represents accounts and their relationship as graph
 *
 * @author Saber Elsayed
 * @version Feb 2024
 */
public interface SocialNetworkInterface {

    /**
     * reads the data file and builds the graph accordingly; remember to call
     * this method in the constructor
     */
    public void processFile();

    /**
     * returns a list of suggested friends. This will return a List<Node> such
     * that each Node in the list is a friend of a friend that lives in the same
     * suburb as currentPerson;
     *
     * @param currentPerson @Node
     * @return a list of no more than 5 friends
     */
    public List<Node> suggestFriends(Node currentPerson);

    /**
     * it returns all of friends of a given account sorted based on their
     * birthday with a string representing time until their birthday
     *
     * @param currentPerson
     * @return a string with friends of a given account sorted based on their
     * birthday with a string representing time until their birthday
     */
    public String remindBDEvents(Node currentPerson);

    /**
     * get a list of all names of mutual friends of two friends x and y
     *
     * @param x first person
     * @param y second person
     * @return a List<String> with all mutual friends, it should contain only
     * names
     */
    public List<String> getMutualFriends(Node x, Node y);
}
