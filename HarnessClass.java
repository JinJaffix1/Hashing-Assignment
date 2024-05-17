
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

    static Node n = null, n2 = null, n3 = null, n4 = null;

    public static void main(String[] args) {

        /**
         * testing Task 1
         */
        System.out.println(" ------*****------ Task 1 begins ------*****------");
        System.out.println(" ---- Testing Node and Edge classes begins ----");
        //testTask1();

        System.out.println(" ------*****------ Task 1 ends ------*****------");
        System.out.print("\n \n");
        System.out.println(" ------*****------ Task 2 begins ------*****------");
        System.out.println(" ---- Testing hashing ----");
        //testTask2();
        System.out.println(" ------*****------ Task 2 ends ------*****------");
        System.out.print("\n \n");
        System.out.println(" ------*****------ Task 3 begins ------*****------");
        System.out.println(" ---- Testing graph ----");
        testTask3();
        System.out.println(" ------*****------ Task 3 ends ------*****------");
        System.out.print("\n \n");

        System.out.println(" ------*****------ Task 4-6 begins ------*****------");

        //testSocialNetwork();
        System.out.println(" ------*****------ Task 4-6 ends ------*****------");
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
//    public static void testTask1() {
//
//        try {
//            n = new Node(1, "B", LocalDate.parse("2018-10-30"), "Bonner");
//            n2 = new Node(2, "B", LocalDate.parse("2018-09-30"), "Ford");
//            System.out.print(n.getName()
//                    + "," + n.getDateOB() + "," + n.getSuburb() + "\t");
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        Edge edge;
//        try {
//            edge = new Edge(n2);
//            System.out.print("Actual: " + edge.friend.getSuburb() + "\t");
//
//        } catch (Exception e) {
//            System.out.println("Actual: " + e.getMessage()
//                    + "Status: Edge test --> FAIL");
//        }
//    }

//    public static void testTask2() {
//
//        System.out.println(n2.hashCode());
//        System.out.println(n2.equals(n));
//
//    }

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
            System.out.print(g.nodeList.size() + "\t");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // then edges between them
        try {
            g.addEdge(v0, v1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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

        try {
            System.out.println(g.getNeighbors(v1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//
//        //return to original -- add v1
//        try {
//            v1 = g.addNode(1, "V1", LocalDate.parse("2010-10-30"), "B");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

//        try {
//
//            Set<Edge> s = g.getNeighbors(v1);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
//
//    public static void testSocialNetwork() {
//        SocialNetwork driver = null;
//
//        // test reading data from file, toString
//        try {
//            driver = new SocialNetwork();
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
//            friendsOffriends = driver.suggestFriends(driver.sn.nodeList.get(1));
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        // test getMutualFriends
//        try {
//            List<String> actualMutual = new LinkedList<>();
//            actualMutual = driver.getMutualFriends(driver.sn.nodeList.get(1),
//                    driver.sn.nodeList.get(5));
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        // check remindBDEvents Task 5
//        try {
//            System.out.print("Actual: "
//                    + driver.remindBDEvents(driver.sn.nodeList.get(1)) + "\t");
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

    }
}
