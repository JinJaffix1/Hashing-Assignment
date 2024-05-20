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

    public Node addNode(Integer id, String name, LocalDate dob, String suburb) throws IllegalArgumentException {
        Node newNode = new Node(id, name, dob, suburb);

        if (nodeList.containsKey(id)) {
            throw new IllegalArgumentException("Node already exists");
        } else {
            nodeList.put(id, newNode);
        }
        return newNode;
    }

    public void addEdge(Node from, Node to) throws IllegalArgumentException {
        if (from.adj.containsKey(to.getId()) && to.adj.containsKey(from.getId())) {
            throw new IllegalArgumentException("Edge already exists.");
        }
        else if (!from.adj.containsKey(to.getId()) && to.adj.containsKey(from.getId())) {
            from.adj.put(to.getId(), new Edge(to));
            System.out.println("Edge added to node: " + from);
        }
        else if (from.adj.containsKey(to.getId()) && !to.adj.containsKey(from.getId())) {
            to.adj.put(from.getId(), new Edge(to));
            System.out.println("Edge added to node: " + to);
        } else {
            from.adj.put(to.getId(), new Edge(to));
            to.adj.put(from.getId(), new Edge(from));
            System.out.println("Edge successfully added.");
        }
    }

    public void removeEdge(Node from, Node to) {
        if (from.adj.containsKey(to.getId()) && to.adj.containsKey(from.getId())) {
            from.adj.remove(to.getId());
            to.adj.remove(from.getId());
            System.out.println("Edge successfully removed.");
        }
        else if (from.adj.containsKey(to.getId()) && !to.adj.containsKey(from.getId())) {
            from.adj.remove(to.getId());
            System.out.println("Edge removed from node: " + to);
        }
        else if (!from.adj.containsKey(to.getId()) && to.adj.containsKey(from.getId())) {
            to.adj.remove(from.getId());
            System.out.println("Edge removed from node: " + from);
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
