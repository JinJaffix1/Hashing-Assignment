import java.time.LocalDate;
import java.util.Set;

/**
 * a Java Interface that holds some of the methods used to define an undirected
 * graph (nodes+edges)
 *
 * @author Saber Elsayed
 * @version Feb 2024
 */
public interface GraphInterface {

    /**
     * creates a new Node based on the data provided, then adds it to a graph if
     * it does not exist, and returns the Node created; otherwise, throw an
     * exception
     *
     * @param id id
     * @param name account name
     * @param dob date of birth
     * @param suburb suburb
     * @return the added vertex
     */
    public Node addNode(Integer id, String name, LocalDate dob, String suburb);

    /**
     * creates and adds an edge between both nodes if it does not exist;
     * otherwise, throw an exception; remember, this is an undirected graph, so
     * you need to add the Edge to both nodes
     *
     * @param from vertex the edge runs from
     * @param to vertex the edge runs to
     */
    public void addEdge(Node from, Node to);

    /**
     * removes an edge between two nodes if it exists; No action is needed if
     * the edge does not exist
     *
     * @param from source
     * @param to destination
     */
    public void removeEdge(Node from, Node to);

    /**
     * removes a node from nodeList and any edge between this node and other
     * nodes; if a node does not exist, throw an exception;
     *
     * @param node node to be deleted
     */
    public void removeNode(Node node);

    /**
     * get a set of friends of a given node; otherwise, throw an exception
     * if the node does not exist
     *
     * @param node node of vertex to get
     * @return a set of keys of all friends; throw an exception if the node does
     * not exist
     */
    public Set<Edge> getNeighbors(Node node);

    /*  
     * display this graph and its vertices; the output string should look like:
     *  person 1 --> friend1    friend2 ..
     *  person 2 --> friend1    friend2 ..
     * @return a string representation of the graph.
     */
    @Override
    public String toString();

    public static void main(String[] args) {
        System.out.println("Test");
    }

}
