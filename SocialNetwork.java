import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;


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
     *<b>Returns a list of suggested friends who are in the same suburb as currentPerson and are friend's of currentPerson's friends.</b>
     * <p></p>
     * <p>This method takes a node, currentPerson, as an input and iterates through every edge in currentPerson's
     * getNeighbors list. For every edge in currentPerson's getNeighbors list, the edge's friends are retrieved. If the
     * suburb of every edge's friend is the same as currentPerson's suburb and does not have the same ID as currentPerson,
     * the edge's friend is added to the list of suggested friends.</p>
     * @param currentPerson @Node
     * @return a list of suggested friends in the same suburb as currentPerson
     */
    public List<Node> suggestFriends(Node currentPerson)
    {
        // Creating a list to store all suggested friends in
        List<Node> suggestFriendList = new LinkedList<Node>();
        // Iterating through every edge in currentPerson's getNeighbors list
        for (Edge e : sn.getNeighbors(currentPerson)) {
            // Iterate through every edge's friend in their getNeighbors list
            for (Edge d : sn.getNeighbors(e.getFriend())) {
                // If the edge's friend's suburb is the same as currentPerson and the ID is not the same as currentPerson,
                if (d.getFriend().getSuburb().equals(currentPerson.getSuburb()) && (d.getFriend().getId() != currentPerson.getId())) {
                    suggestFriendList.add(d.getFriend()); // add the edge's friend to the list of suggested friends
                }
            }
        }
        return suggestFriendList;
    }

    /**
     * <b>Returns a list of mutual friends between two nodes</b>
     * <p></p>
     * <p>This method takes in two nodes, x and y, and creates two sets containing all of their edges. It then iterates
     * through each Edge set and retrieves each of the node's names before storing them in two new ArrayLists. An
     * intersection between the two ArrayLists is found and stored in a new list as a String.</p>
     * @param x first person
     * @param y second person
     * @return a list of mutual friends between Node x and Node y
     */
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
     * <b>Returns a list of friends' upcoming birthdays</b>
     * <p></p>
     * <p>Returns all friends of a given person with the amount of days and months until
     * each friends' next birthday. It is sorted based on who is the closest to their next birthday.</p>
     *
     * @param currentPerson of type Node
     * @return String of currentPerson's friends and period until their next birthday
     */
    @Override
    public String remindBDEvents(Node currentPerson)
    {
        String upcomingBDays = currentPerson.getName() + ":-> \n";
        // Current date
        LocalDate currentDate = LocalDate.now();

        // Create Priorty Queue, sorted based on next birthday
        PriorityQueue<Node> queue = new PriorityQueue<>();

        // Iterate through currentPerson's friends
        for (Edge e : sn.getNeighbors(currentPerson))
        {
            // Add friend to the queue
            queue.add(e.getFriend());
        }

        // Iterate through the sorted priority queue and calualate how
        // many days until each person's birthday, add it to the string
        while (!queue.isEmpty())
        {
            Node n = queue.poll();
            // Calcuate period until next birthday
            Period period = Period.between(currentDate, n.getNextBirthday());

            // Construct string of sorted next birthdays
            upcomingBDays += n.getName() + " has their birthday in " + period.getMonths() + " Months, " +
                    period.getDays() + " Days\n";
        }
        return upcomingBDays;
    }

}