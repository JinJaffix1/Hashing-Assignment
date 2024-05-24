import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Represents a vertex in the graph with its adjacency list of edges.
 *
 * @version April 2024
 * @author Saber Elsayed
 */
class Node implements NodeInteface, Comparable<Node>
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

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		return customHash(name, dateOB, id);
	}

	/**
	 * Creates a custom hash code value of length 4 using Node attributes
	 * 
	 * @param name   - Node name
	 * @param dateOB - Node date of birth
	 * @param id     - Node id
	 * @return hash code value of Node
	 */
	private int customHash(String name, LocalDate dateOB, int id)
	{
		int nameAscii = 0;
		String str = "";
		
		// Convert name to ASCII
		for (int i = 0; i < name.length(); i++)
		{
			nameAscii = name.charAt(i);
			str += nameAscii;
		}

		// Add 0's in front of the ASCII name value if is less than 8 digits
		if (str.length() < 8)
		{
			str = String.format("%08d", Integer.parseInt(str));
		}

		// Take a substring of the last 6 digits of the ASCII name value 
		int length = str.length();
		String subString = str.substring(length - 6, length);

		// Convert date of birth from a LocalDate to an integer
		String dateString = String.format("%04d%02d%02d", dateOB.getYear(), dateOB.getMonthValue(), dateOB.getDayOfMonth());
		int dob = Integer.parseInt(dateString);

		// Create a hash key of length 4, using extraction and manipulation
		int newID = id * dob * dob;
		int result = Integer.parseInt(Integer.toString(Integer.parseInt(subString) * Integer.parseInt(subString) + newID).substring(2, 6));

		return result;
	}

	/**
	 * Override equals method. Compare if date of birth, id 
	 * and name are equal to other instance
	 * @param obj - Object getting compared
	 * @return boolean - equal or not 
	 */
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
		return Objects.equals(dateOB, other.dateOB) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	/**
	 * checks for how many unique hashes are created 
	 * when a hash is generated for each Node object
	 */
	private static void checkHashDuplicates()
	{
		BufferedReader reader;
		try
		{
			// Read the data.txt, line by line
			reader = new BufferedReader(new FileReader("data.txt"));
			String line = reader.readLine();

			// Set for removing hash duplicates
			Set<Integer> set = new HashSet<Integer>();

			while (line != null)
			{
				String[] parts = line.split("[,\t]");

				// Extracting node attributes
				int id = Integer.parseInt(parts[0]);
				String name = parts[1].trim();
				LocalDate dob = LocalDate.parse(parts[2]);
				String suburb = parts[3].trim();

				// Create new Node with attributes
				Node newEntry = new Node(id, name, dob, suburb);

				try
				{
					// Retrieve the node's hashcode and add to the set
					set.add(newEntry.hashCode());
				}

				catch (IllegalArgumentException iae)
				{
					System.out.println(iae.getMessage());
				}

				// Read next line
				line = reader.readLine();

			}
			reader.close();

			// Print the set size showing how many unique hashes are created
			System.out.println("No. of Unique Hashes: " + set.size());

		}
		catch (IOException e)
		{
			System.err.println("Error reading file: " + e);
			System.exit(100);
		}
	}
	
	/**
	 * get the date of a person's next birthday 
	 * based on their date of birth
	 * 
	 * @return date of next birthday of type LocalDate
	 */
	public LocalDate getNextBirthday()
	{
		// Get the current month and day value
		MonthDay currentMonthDay = MonthDay.of(LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
		// Get a person's date of birth month and day value
		MonthDay otherMonthDay = MonthDay.of(dateOB.getMonthValue(), dateOB.getDayOfMonth());

		// Construct next birthday 
		LocalDate nextBirthday = LocalDate.now().withDayOfMonth(dateOB.getDayOfMonth()).withMonth(dateOB.getMonthValue());

		// Workout whether to put this year or next year as the next birthday year
		if (otherMonthDay.isBefore(currentMonthDay))
		{
			// already had birthday this year, so birthday next year
			nextBirthday = nextBirthday.plusYears(1);
		}

		return nextBirthday;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(Node other)
	{
		// Calculates next birthday date 
		LocalDate thisNextBirthday = this.getNextBirthday();
		LocalDate otherNextBirthday = other.getNextBirthday();

		LocalDate today = LocalDate.now();
		
		// Calculates days until next birthday
		long days = today.until(thisNextBirthday, DAYS);
		long otherDays = today.until(otherNextBirthday, DAYS);

		if (days < otherDays)
		{
			return -1;
		}
		else if (days > otherDays)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	public static void main(String[] args)
	{
		checkHashDuplicates();
	}
}
