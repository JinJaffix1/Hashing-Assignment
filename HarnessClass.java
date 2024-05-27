import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * represents a test harness for all tasks in Ass3. 2019 (graph, hashing,
 * reading data from files, PQ, ... etc.
 *
 * @author Saber Elsayed
 * @version Feb 2024
 */
public class HarnessClass {

    static Node n = null, n2 = null, n3 = null, n4 = null, ntest = null;

    public static void main(String[] args) {

        /**
         * testing Task 1
         */
        System.out.println(" ------*****------ Task 1 begins ------*****------");
        System.out.println(" ---- Testing Node and Edge classes begins ----");
        //testTask1();

        System.out.println("\n ------*****------ Task 1 ends ------*****------");
        System.out.print("\n \n");
        System.out.println(" ------*****------ Task 2 begins ------*****------");
        System.out.println(" ---- Testing hashing ----");
        // testTask2();
        System.out.println("\n ------*****------ Task 2 ends ------*****------");
        System.out.print("\n \n");
        System.out.println(" ------*****------ Task 3 begins ------*****------");
        System.out.println(" ---- Testing graph ----");
        testTask3();
        System.out.println("\n ------*****------ Task 3 ends ------*****------");
        System.out.print("\n \n");

        System.out.println(" ------*****------ Task 4-6 begins ------*****------");

        // testSocialNetwork();
        System.out.println("\n ------*****------ Task 4-6 ends ------*****------");
        System.out.print("\n \n");

        System.out.print("Other things to consider \n"
                + "- testing \n"
                + "- name convention \n"
                + "- java doc \n"
                + "- documentation \n");
    }

    /**
     * Testing Task1: the basic operations in the Node and Edge classes
     */
    public static void testTask1() {
        Edge edge;
        try {
            n = new Node(1, "B", LocalDate.parse("2018-10-30"), "Bonner");
            n2 = new Node(2, "B", LocalDate.parse("1989-03-15"), "Ford");
            System.out.print(n.getId() + ", " + n.getName()
                    + ", " + n.getDateOB() + ", " + n.getSuburb() + "\t");
            ntest = new Node(3, "C", LocalDate.parse("5"), "");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // PERSONAL TEST CASES FOR TASK 1, NODE CLASS:
        try {
            System.out.println("");
            System.out.println("\n********* TEST CASES FOR TASK 1 - NODE CLASS *********");
            System.out.println("|+----- Testing get methods... -----+|");

            // Testing get methods for a valid node
            System.out.println(n.getId());
            System.out.println(n.getName());
            System.out.println(n.getDateOB());
            System.out.println(n.getSuburb());
            // Testing toString method for a valid node
            System.out.println("\nDetails of node: " + n.toString());

            // Testing with an invalid date format: "5"
            System.out.println(ntest.getDateOB()); // fails as date is invalid

            // Testing with an empty string as suburb: ""
            System.out.println(ntest.getSuburb()); // still works? put in empty string exception??

        } catch (Exception e) {
            System.out.print("\nStatus - FAIL: " + e.getMessage());
        }

        // PERSONAL TEST CASES FOR TASK 1, EDGE CLASS:
        try {
            System.out.println("");
            System.out.println("\n********* TEST CASES FOR TASK 1 - EDGE CLASS *********");
            System.out.println("|+----- Testing get methods for edges... -----+|\n");

            // Creating an edge instance
            edge = new Edge(n2);

            // Retrieving friend's DOB
            System.out.println("Friend's DOB: " + edge.friend.getDateOB() + "\t");
            // Retrieving friend's suburb
            System.out.println("Friend's suburb: " + edge.friend.getSuburb() + "\t");
            // Returning friend's details
            System.out.println(n2.toString());

            // Testing with an invalid edge (node is null)
            System.out.println(n4.toString());

        } catch (Exception e) {
            System.out.println("\nStatus - FAIL: " + e.getMessage());
        }
    }

    // TASK 2: HASHING
    public static void testTask2() {
        System.out.println(n2.hashCode());
        System.out.println(n2.equals(n));
    }

    /**
     * testing the Graph class
     */
    public static void testTask3() {
        Graph g = new Graph();
        Node v0 = null, v1 = null, v2 = null, v3 = null, v4 = null, v5 = null, v6 = null;
        // build a very simple graph,
        try {
            // add more nodes to make your testing better
            v0 = g.addNode(0, "V0", LocalDate.parse("2010-10-30"), "A");
            v1 = g.addNode(1, "V1", LocalDate.parse("2010-10-30"), "B");
            v2 = g.addNode(2, "V2", LocalDate.parse("2010-10-30"), "C");
            //System.out.print(g.nodeList.size() + "\t");

        } catch (Exception e) {
            System.out.println("\nSTATUS - FAIL: " + e.getMessage());
        }

//        // then edges between them
//        try {
//            g.addEdge(v0, v1);
//            g.addEdge(v1, v2);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            g.removeEdge(v0, v1);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        // add it back for more testing
//        try {
//            g.addEdge(v3, v2);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            g.removeNode(v1);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            System.out.println(g.getNeighbors(v1));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        //return to original -- add v1
//        try {
//            v1 = g.addNode(1, "V1", LocalDate.parse("2010-10-30"), "B");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//
//            Set<Edge> s = g.getNeighbors(v1);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        // PERSONAL TEST CASES FOR TASK 3, GRAPH CLASS:
        System.out.println("\n********* TEST CASES FOR TASK 3 - GRAPH CLASS *********\n");
        try {
            System.out.println("");
            System.out.println("|+----- Testing addNode method... -----+|");

            // Adding a valid node with correct formatting
            v3 = g.addNode(3, "V3", LocalDate.parse("2010-09-30"), "D");
            // Adding a node that already exists
            v3 = g.addNode(3, "V1", LocalDate.parse("2000-04-17"), "F");

        } catch (IllegalArgumentException e) {
            System.out.println("\nSTATUS - FAIL: " + e.getMessage());
        }

        try {
            System.out.println("");
            System.out.println("|+----- Testing addEdge method... -----+|");

            // Adding an edge between two valid nodes
            g.addEdge(v2, v3);

            // Retrieving neighbours of node v2
            System.out.println(g.getNeighbors(v2));
            // Retrieving neighbours of node v3
            System.out.println(g.getNeighbors(v3));

            // Adding an already existing edge between two nodes
            g.addEdge(v3, v2);

            // Adding an edge between one/two invalid/invalid nodes
            g.addEdge(v6, v2);

        } catch (IllegalArgumentException e) {
            System.out.println("\nSTATUS - FAIL: " + e.getMessage());
        }

        try {
            System.out.println("");
            System.out.println("|+----- Testing removeEdge method... -----+|");

            // Removing an edge between two valid nodes
            g.removeEdge(v2, v3);
            // Removing an edge that does not exist between two nodes
            g.removeEdge(v2, v3);
            // Removing an edge between one/two null/invalid nodes
            g.removeEdge(v6, v3);

        } catch (Exception e) {
            System.out.println("\nSTATUS - FAIL: " + e.getMessage());
        }

        try {
            System.out.println("");
            System.out.println("|+----- Testing removeNode method... -----+|");

            // Re-adding edges for testing
            g.addEdge(v3, v1);
            g.addEdge(v2, v1);
            System.out.println("");

            // Removing a valid node
            g.removeNode(v3);
            // Removing a null/invalid node
            g.removeNode(v5);

        } catch (Exception e) {
            System.out.println("\nSTATUS - FAIL: " + e.getMessage());
        }

        try {
            System.out.println("");
            System.out.println("|+----- Testing getNeighbors method... -----+|");

            // Getting the neighbours of a valid node
            System.out.println("Neighbours: \n" + g.getNeighbors(v2));
            // Getting the neighbours of a null/invalid node
            System.out.println("Neighbours: \n" + g.getNeighbors(v6));

        } catch (Exception e) {
            System.out.println("\nSTATUS - FAIL: " + e.getMessage());
        }

        try {
            System.out.println("");
            System.out.println("|+----- Testing toString method... -----+|");

            // Testing toString method for a valid node
            String graphString = g.toString();
            System.out.println(graphString);

        } catch (Exception e) {
            System.out.println("\nSTATUS - FAIL: " + e.getMessage());
        }
    }

    public static void testSocialNetwork() {

        // test reading data from file, toString
//        try {
//            SocialNetwork driver = new SocialNetwork();
//            System.out.println(driver.sn);
//            Set<Edge> s = driver.sn.getNeighbors(driver.sn.nodeList.get(1));
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        // test suggestFriends
//        List<Node> friendsOffriends = new ArrayList<>();
//        try {
//            SocialNetwork driver = new SocialNetwork();
//            friendsOffriends = driver.suggestFriends(driver.sn.nodeList.get(1));
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        // test getMutualFriends
//        try {
//            List<String> actualMutual = new LinkedList<>();
//            SocialNetwork driver = new SocialNetwork();
//            actualMutual = driver.getMutualFriends(driver.sn.nodeList.get(32),
//                    driver.sn.nodeList.get(8));
//            System.out.println(actualMutual);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        // PERSONAL TESTS FOR TASK 4, SOCIAL NETWORK CLASS
        System.out.println("\n********* TEST CASES FOR TASK 4 - SOCIALNETWORK CLASS *********\n");
        List<Node> suggestedFriends = new ArrayList<>();
        List<String> mutualFriends = new LinkedList<>();
        SocialNetwork driver = new SocialNetwork();

        try {
            System.out.println("");
            System.out.println("|+----- Testing processFile and reading data from data.txt... -----+|");

            // Retrieving node 337 from data file
            Node node = driver.sn.nodeList.get(377);
            System.out.println(node);

            // Finding how many friends node 377 has
            Set<Edge> s = driver.sn.getNeighbors(node);
            int numberOfFriends = s.size();
            System.out.println("Number of friends node " + node.getId() + " has: " + numberOfFriends);

            // Testing with an invalid node
            Node nodetest = driver.sn.nodeList.get(8000);
            Set<Edge> stest = driver.sn.getNeighbors(nodetest);
            System.out.println(stest);

        } catch (Exception e) {
            System.out.println("\nSTATUS - FAIL: " + e.getMessage());
        }

        try {
            System.out.println("");
            System.out.println("|+----- Testing suggestFriends method... -----+|");

            // Finding the suggested friends for node 271
            Node node = driver.sn.nodeList.get(271);
            suggestedFriends = driver.suggestFriends(node);
            System.out.println("Suggested friends for " + node.getId() + " are: \n" + suggestedFriends);

            // Testing an invalid node
            Node node2 = driver.sn.nodeList.get(8000);
            suggestedFriends = driver.suggestFriends(node2);

        } catch (Exception e) {
            System.out.println("\nSTATUS - FAIL: " + e.getMessage());
        }

        try {
            System.out.println("");
            System.out.println("|+----- Testing getMutualFriends method... -----+|");

            // Testing two valid nodes
            Node NodeX = driver.sn.nodeList.get(18);
            Node NodeY = driver.sn.nodeList.get(315);

            // Retrieving mutual friends between 18 and 315
            mutualFriends = driver.getMutualFriends(NodeX, NodeY);
            System.out.println("Mutual friends between " + NodeX.getId() + " and " + NodeY.getId() + " are: " + mutualFriends);

            // Testing an invalid node
            Node NodeF = driver.sn.nodeList.get(100000);
            mutualFriends = driver.getMutualFriends(NodeX, NodeF);
            System.out.println("Mutual friends between " + NodeX.getId() + " and " + NodeF.getId() + " are: " + mutualFriends);

        } catch (Exception e) {
            System.out.println("\nSTATUS - FAIL: " + e.getMessage());
        }

     //    check remindBDEvents Task 5

//        try {
//        System.out.print("Actual: "
//                + driver.remindBDEvents(driver.sn.nodeList.get(1)) + "\t");
//
//         } catch(Exception e) {
//            System.out.println(e.getMessage());
//        }

        try {
            System.out.println("");
            System.out.println("|+----- Testing remindBDEvents method... -----+|");

            // Testing with a valid node
            Node node = driver.sn.nodeList.get(26);
            System.out.println("Upcoming birthdays for " + driver.remindBDEvents(node));

            // Testing with an invalid node
            Node nodeTest = driver.sn.nodeList.get(9000);
            System.out.println("Upcoming birthdays for " + driver.remindBDEvents(nodeTest));


        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}



