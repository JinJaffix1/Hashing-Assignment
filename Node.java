import java.time.LocalDate;
import java.util.HashMap;

/**
 * Represents a vertex in the graph with its adjacency list of edges.
 *
 * @version April 2024
 * @author Saber Elsayed
 */
class Node implements NodeInteface
{

	// id
	private Integer id;
	
	// person name
	private String name;

	// date of birth
	private LocalDate dateOB;
	
	// suburb a person lives at
	private String suburb;

	// contains a list of all friends of a person object
	protected HashMap<Integer, Edge> adj;

	/**
	 * Construct a new vertex in the graph with the supplied id, name, DOB and suburb.
	 */
	public Node(Integer id, String name, LocalDate dob, String suburb)
	{
		this.id = id;
		this.name = name;
		this.dateOB = dob;
		this.suburb = suburb;
		this.adj = new HashMap<>();
	}

	@Override
	public Integer getId()
	{
		return id;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String getSuburb()
	{
		return suburb;
	}

	@Override
	public LocalDate getDateOB()
	{
		return dateOB;
	}

	public String toString()
	{
		String str = "id = " + getId() + "\nname = " + getName() + "\nDOB = " + getDateOB() + "\nsuburb = " + getSuburb();
		return str;
	}

}
