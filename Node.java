import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

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
	
	
	
	@Override
	public int hashCode()
	{
		return customHash(name, dateOB, id);
	}
	
	private int customHash(String name, LocalDate dateOB, int id) {
		int nameAscii = 0;
		String str = "";
			// Convert name to ASCI
			for (int i = 0; i < name.length(); i++) 
			{
				nameAscii = name.charAt(i);
				str += nameAscii;
			}
		
		String subString = str.substring(8, 15);
		
		// Convert date of birth to integer
		String dateString = String.format("%04d%02d%02d", dateOB.getYear(), dateOB.getMonthValue(), dateOB.getDayOfMonth());
        int dob = Integer.parseInt(dateString);
        
        //Create hash key of length 4, using extraction and manipulation
        int newID = id * dob * dob;
        int result = Integer.parseInt(Integer.toString(Integer.parseInt(subString)* Integer.parseInt(subString)+newID).substring(2,6));
        
        return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		return Objects.equals(adj, other.adj) && Objects.equals(dateOB, other.dateOB) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(suburb, other.suburb);
	}
	
	private static void checkHashDuplicates()
	{
		Scanner dataIn;
		try
		{
			dataIn = new Scanner(new File("data.txt"));
			
			//Set for checking hashcode duplicates
			Set<Integer> set = new HashSet<Integer>(); 
			
			while (dataIn.hasNextLine())
			{
				String line = dataIn.nextLine();
				String[] parts = line.split("[,\t]");
                
                // Extracting parameters
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                LocalDate dob = LocalDate.parse(parts[2]);
                String suburb = parts[3];
                
				Node newEntry = new Node(id, name, dob, suburb);
				
				try
				{
					set.add(newEntry.hashCode());
				}

				catch (IllegalArgumentException iae)
				{
					System.out.println(iae.getMessage());
				}
			}
			
			System.out.println("NON DUPLICATES: " + set.size());
			
		}
		catch (IOException e)
		{
			System.err.println("Error reading file: " + e);
			System.exit(100);
		} 
	}
	
	public static void main(String[] args) {
		checkHashDuplicates();
	}
	
	
	
	
}
