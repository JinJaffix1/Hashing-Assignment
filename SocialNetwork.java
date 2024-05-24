import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ArrayList;
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
        processFile();
    }

    public void processFile() {
        try {
            // Read in the data.txt file
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
            String line = reader.readLine();

            while (line != null) {
                String[] parts = line.split("[,\t]");

                // Extracting parameters
                int id = Integer.parseInt(parts[0]);
                String name = parts[1].trim();
                LocalDate dob = LocalDate.parse(parts[2]);
                String suburb = parts[3].trim();

                // Adds a node to the graph
                sn.addNode(id, name, dob, suburb);

                // Read next line
                line = reader.readLine();
            }

            reader.close();

            // Add all edges
            BufferedReader reader2 = new BufferedReader(new FileReader("data.txt"));
            String line2 = reader2.readLine();

            while (line2 != null) {
                String[] parts = line2.split("[,\t]");
                int id = Integer.parseInt(parts[0]);
                System.out.println(id);

                for (int i = 4; i < parts.length; i++) {
                    int friendId = Integer.parseInt(parts[i].trim());
                    System.out.println(friendId);

                    try {
                        sn.addEdge(sn.nodeList.get(id), sn.nodeList.get(friendId));
                    }
                    catch (IllegalArgumentException iae) {
                        System.out.println(iae.getMessage());
                    }
                }
                // Read next line
                line2 = reader2.readLine();
            }
            reader2.close();
        }

        catch (IOException e) {
            System.err.println("Error reading file: " + e);
            System.exit(100);
        }
    }

    /**
     *
     * @param currentPerson @Node
     * @return a list of suggested friends in the same suburb as currentPerson
     */
    public List<Node> suggestFriends(Node currentPerson)
    {
        List<Node> suggestFriendList = new LinkedList<Node>();
        for (Edge e : sn.getNeighbors(currentPerson)) {
            for (Edge d : sn.getNeighbors(e.getFriend())) {
                if (d.getFriend().getSuburb().equals(currentPerson.getSuburb()) && (d.getFriend().getId() != currentPerson.getId())) {
                    suggestFriendList.add(d.getFriend());
                }
            }
        }
        return suggestFriendList;
    }

    @Override
    public String remindBDEvents(Node currentPerson) {
        return "";
    }

    /**
     *
     * @param x first person
     * @param y second person
     * @return a list of mutual friends between Node x and Node y
     */
	//
    public List<String> getMutualFriends(Node x, Node y) {
        // Creating an Edge set of friends of Node x
        Set<Edge> friendsOfX = sn.getNeighbors(x); // getting all friends of Node x
        // Creating an Edge set of friends of Node y
        Set<Edge> friendsOfY = sn.getNeighbors(y); // getting all friends of Node y

        // Creating a String list of Node x's friend names
        ArrayList<String> nodesOfX = new ArrayList<String>();
        for (Edge e : friendsOfX) { // for every friend of x,
            nodesOfX.add(e.getFriend().getName()); // retrieve the name of the friend and add it to the list
        }

        // Creating a String list of Node y's friend names
        ArrayList<String> nodesOfY = new ArrayList<String>();
        for (Edge e : friendsOfY) { // for every friend of y,
            nodesOfY.add(e.getFriend().getName()); // retrieve the name of the friend and add it to the list
        }

        // Finding the intersection (mutual friends) of nodesOfX and nodesOfY
        nodesOfX.retainAll(nodesOfY);

        // Store the intersection (mutuals) in a new list
        List<String> mutualFriendsList = new ArrayList<>(nodesOfX);

        return mutualFriendsList;
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