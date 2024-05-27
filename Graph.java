import java.util.HashMap;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * constructs an undirected graph with some basic operations: addNode,
 * removeNode, addEdge, getNeighbors, etc.
 *
 * @author Saber Elsayed
 * @version 3.0, Feb 2024
 * @see Edge
 * @see Node
 */
public class Graph implements GraphInterface {

    /**
     * holds all nodes (people) in this graph
     */
    protected HashMap<Integer, Node> nodeList = new HashMap<>();

    /**
     * <b>Add a node to the graph</b>
     * <p></p>
     * <p>This method takes in a node ID, name, date of birth, and suburb. It checks whether the nodeList contains the given
     * ID. If it does, an exception is thrown, otherwise the node is added to the nodeList.</p>
     * @param id id
     * @param name account name
     * @param dob date of birth
     * @param suburb suburb
     * @return the node that was added
     * @throws IllegalArgumentException
     */
    public Node addNode(Integer id, String name, LocalDate dob, String suburb) throws IllegalArgumentException {
        // Create a new node, newNode
        Node newNode = new Node(id, name, dob, suburb);
        // If the nodeList contains given node ID, throw an exception
        if (nodeList.containsKey(id)) {
            throw new IllegalArgumentException("Node already exists");

        } else {
            // Otherwise, add the node to the nodeList
            nodeList.put(id, newNode);
            System.out.println("Node successfully added.");
        }
        return newNode;
    }

    /**
     * <b>Add an edge between two nodes</b>
     * <p></p>
     * <p>This method checks the existence of Node from and Node to with various conditionals.</p>
     * <p>If Node from and Node to are already in each other's adj list, an exception is thrown.</p>
     * <p>If Node "to" does not exist in Node "from" adj list but Node "from" exists in Node "to" adj list, an edge
     * is added from "from" node to "to" node.</p>
     * <p>If Node "to" exists in Node "from" adj list but Node "from" does not exist in Node "to" adj list, an edge
     * is added from "to" node to "from" node.</p>
     * <p>Otherwise, if "from" and "to" are not in either adj list, an edge is added between them.</p>
     * @param from vertex the edge runs from
     * @param to vertex the edge runs to
     * @throws IllegalArgumentException
     */
    public void addEdge(Node from, Node to) throws IllegalArgumentException {
        // If the "Node from" and "Node to" already exists, throw an exception
        if (from.adj.containsKey(to.getId()) && to.adj.containsKey(from.getId())) {
            throw new IllegalArgumentException("Edge already exists.");
        }
        // If Node "to" does not exist in Node "from" adj list but Node "from" exists in Node "to" adj list,
        else if (!from.adj.containsKey(to.getId()) && to.adj.containsKey(from.getId())) {
            from.adj.put(to.getId(), new Edge(to)); // add Node "to" to adj list of Node "from"
            System.out.println("Edge added to node: " + from);
        }

        // If Node "to" exists in Node "from" adj list but Node "from" does not exist in Node "to" adj list,
        else if (from.adj.containsKey(to.getId()) && !to.adj.containsKey(from.getId())) {
            to.adj.put(from.getId(), new Edge(to)); // add Node "from" to adj list of Node "to"
            System.out.println("Edge added to node: " + to);

        // Otherwise, if "Node from" and "Node to" do not exist, add both "from" and "to" to adj list as Edges
        } else {
            from.adj.put(to.getId(), new Edge(to)); // add Node "to" to adj list of Node "from"
            to.adj.put(from.getId(), new Edge(from)); // Node "from" to adj list of Node "to"
            System.out.println("Edges successfully added.");
        }
    }

    /**
     * <b>Remove an edge between two nodes</b>
     * <p></p>
     * <p>This method removes an edge between Node "from" and Node "to".
     * <p>It first checks whether Node "to" exists in Node "from" adj list and Node "from" exists in Node "to" adj list.
     * If it does, Node "to" is removed from Node "from" adj list and Node "from" is removed from Node "to" adj list.</p>
     * <p>Otherwise, if "from" and "to" do not exist in each other's adj list, an error message is printed.</p>
     * @param from source
     * @param to destination
     */
    // Method to remove an edge between two given nodes
    public void removeEdge(Node from, Node to) {
        // If adj list contains "Node from" and "Node to", remove the edges between them
        if (from.adj.containsKey(to.getId()) && to.adj.containsKey(from.getId())) {
            from.adj.remove(to.getId()); // Remove "to" from "from" adj list
            to.adj.remove(from.getId()); // Remove "from" from "to" adj list
            System.out.println("Edge successfully removed.");

        // If adj list does not contain an edge between "Node from" and "Node to", print error message
        } else {
            System.out.println("Edge does not exist.");
        }
    }

    /**
     * <b>Remove a node from the graph</b>
     * <p></p>
     * <p>This method checks whether the given node exists in the nodeList. If it does, all associating edges containing
     * that node are removed and the node itself is then removed.</p>
     * <p>Otherwise, if the node does not exist in nodeList, an exception is thrown.</p>
     * @param node node to be deleted
     * @throws IllegalArgumentException
     */

	public void removeNode(Node node) throws IllegalArgumentException
	{
		Integer nodeId = node.getId();

		// Check if the Node exists
		if (nodeList.containsKey(nodeId))
		{
			// Retrieve all the neighbouring nodes
			Set<Edge> neighbors = getNeighbors(node);

			// Create iterator to remove corresponding edges
			Iterator<Edge> iterator = neighbors.iterator();
			while (iterator.hasNext())
			{
				Edge edge = iterator.next();
				Node neighborNode = edge.getFriend();
				removeEdge(node, neighborNode);
			}
			// Once all neighbouring edges are removed, remove the node
			nodeList.remove(node);

			System.out.println("Node removed.");
		}

		else {
			throw new IllegalArgumentException("Node does not exist.");
		}
	}

    /**
     * <b>Retrieves neighbours of a node</b>
     * <p></p>
     * <p>This method first checks whether the given node is in nodeList. If it does not exist, an exception is thrown</p>
     * <p>Otherwise, a HashSet of the node's neighbouring edges is returned.</p>
     * @param node node of vertex to get
     * @return a set of neighbouring edges
     * @throws IllegalArgumentException
     */
	public Set<Edge> getNeighbors(Node node) throws IllegalArgumentException
	{
        // If the node does not exist in nodeList, throw an exception
		if (!nodeList.containsKey(node.getId()))
		{
			throw new IllegalArgumentException("Node does not exist.");
		}
        // Otherwise, return a set of the node's neighbouring edges
		return new HashSet<Edge>(node.adj.values());
	}

    /**
     * <b>Returns a string representation of each person's (node) name and friends.</b>
     * <p></p>
     * <p>This method iterates through the nodeList to retrieve each person's name and append it to the empty string.
     * It then iterates through each person's getNeighbors list to retrieve the names of all their friends and append
     * the names of their friends to the string.</p>
     * @return a string representation of each person's name and friends
     */
	@Override
	public String toString() {
        // Creating an empty string to store details
		String s = "";
        // Iterating through the nodeList
		for(Node i: nodeList.values()) {
			s += i.getName() + ": --> "; // Retrieve the name of the person and append it to the empty string.
			// Iterating through each person's getNeighbors list
            for(Edge e: getNeighbors(i)) {
				s += e.getFriend().getName() + "\t"; // Retrieve the names of all their friends and append it to the string.
			}
            // Tab to separate each person's name and friends
			s+="\n";
		}
		return s;
	}
}
