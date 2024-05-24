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

    // Method to add a node to the graph
    public Node addNode(Integer id, String name, LocalDate dob, String suburb) throws IllegalArgumentException {
        // Create a new node, newNode
        Node newNode = new Node(id, name, dob, suburb);
        // If the nodeList contains given node ID, throw an exception
        if (nodeList.containsKey(id)) {
            throw new IllegalArgumentException("Node already exists");
        } else {
            // Otherwise, add the node to the nodeList
            nodeList.put(id, newNode);
        }
        return newNode;
    }

    // Method to add an edge between two given nodes
    public void addEdge(Node from, Node to) throws IllegalArgumentException {
        // If the "Node from" and "Node to" already exists, throw an exception
        if (from.adj.containsKey(to.getId()) && to.adj.containsKey(from.getId())) {
            throw new IllegalArgumentException("Edge already exists.");
        }
        // If the "Node from" does not exist but "Node to" does exist, add the "Node from" to adj list as an Edge
        else if (!from.adj.containsKey(to.getId()) && to.adj.containsKey(from.getId())) {
            from.adj.put(to.getId(), new Edge(to));
            System.out.println("Edge added to node: " + from);
        }
        // If the "Node from" exists but "Node to" does not exist, add the "Node to" to adj list as an Edge
        else if (from.adj.containsKey(to.getId()) && !to.adj.containsKey(from.getId())) {
            to.adj.put(from.getId(), new Edge(to));
            System.out.println("Edge added to node: " + to);
        // Otherwise, if "Node from" and "Node to" does not exist, add both "from" and "to" to adj list as Edges
        } else {
            from.adj.put(to.getId(), new Edge(to)); // add "Node to" to adj list of "Node from"
            to.adj.put(from.getId(), new Edge(from)); // "Node from" to adj list of "Node to"
            System.out.println("Edge successfully added.");
        }
    }

    // Method to remove an edge between two given nodes
    public void removeEdge(Node from, Node to) {
        // If adj list contains "Node from" and "Node to", remove the edges between them
        if (from.adj.containsKey(from.getId()) && to.adj.containsKey(to.getId())) {
            from.adj.remove(from.getId());
            to.adj.remove(to.getId());
            System.out.println("Edge successfully removed.");
        }
        // If adj list contains "Node from" but not "Node to", remove the edge from "Node from"
        else if (from.adj.containsKey(to.getId()) && !to.adj.containsKey(from.getId())) {
            from.adj.remove(to.getId()); // Removing "Node from"
            System.out.println("Edge removed from node: " + to);
        }
        // If adj list contains "Node to" but not "Node from", remove the edge from "Node to"
        else if (!from.adj.containsKey(to.getId()) && to.adj.containsKey(from.getId())) {
            to.adj.remove(from.getId()); // Removing "Node to"
            System.out.println("Edge removed from node: " + from);
        // If adj list does not contain an edge between "Node from" and "Node to", print error message
        } else {
            System.out.println("Edge does not exist.");
        }
    }

	public void removeNode(Node node)
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
			//Once all neighbouring edges, remove the node
			nodeList.remove(node);

			System.out.println("Node removed.");

		}
		else
		{
			System.out.println("Node does not exist.");
		}

	}
	
	public Set<Edge> getNeighbors(Node node) throws IllegalArgumentException
	{
		if (!nodeList.containsKey(node.getId()))
		{
			throw new IllegalArgumentException("Node does not exist.");
		}
		return new HashSet<Edge>(node.adj.values());
	}
	
	@Override
	public String toString() {
		String s = "";
		for(Node i: nodeList.values()) {
			s += i.getName() + ": --> ";
			for(Edge e: getNeighbors(i)) {
				s += e.getFriend().getName() + "\t";
			}
			s+="\n";
		}
		return s;		
	}

    /**
     * Test main that creates a graph,
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Graph g = new Graph();
    }
}
