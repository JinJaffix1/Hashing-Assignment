import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * represents accounts and their relationship as a graph;
 *
 * @author Saber Elsayed
 * @version Feb 2024
 */
public class SocialNetwork implements SocialNetworkInterface
{

	protected Graph sn;

	/**
	 * constructs a social network analyser object by reading data files
	 */
	public SocialNetwork()
	{
		sn = new Graph();
		processFile();

	}

	@Override
	public void processFile()
	{
		try
		{
			// Read in the data.txt file
			BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
			String line = reader.readLine();

			while (line != null)
			{
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

			while (line2 != null)
			{
				String[] parts = line2.split("[,\t]");
				int id = Integer.parseInt(parts[0]);
				System.out.println(id);

				for (int i = 4; i < parts.length; i++)
				{
					int friendId = Integer.parseInt(parts[i].trim());
					System.out.println(friendId);

					try
					{
						sn.addEdge(sn.nodeList.get(id), sn.nodeList.get(friendId));
					}
					catch (IllegalArgumentException iae)
					{
						System.out.println(iae.getMessage());
					}

				}

				// Read next line
				line2 = reader2.readLine();
			}

			reader2.close();
		}

		catch (IOException e)
		{
			System.err.println("Error reading file: " + e);
			System.exit(100);
		}

	}

	@Override
	public List<Node> suggestFriends(Node currentPerson)
	{
		List<Node> suggestFriendList = new LinkedList<Node>();
		for (Edge e : sn.getNeighbors(currentPerson))
		{
			for (Edge d : sn.getNeighbors(e.getFriend()))
			{
				if (d.getFriend().getSuburb().equals(currentPerson.getSuburb()) && (d.getFriend().getId() != currentPerson.getId()))
				{
					suggestFriendList.add(d.getFriend());
				}
			}
		}
		return suggestFriendList;
	}

	@Override
	public String remindBDEvents(Node currentPerson)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMutualFriends(Node x, Node y)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args)
	{
		SocialNetwork driver = new SocialNetwork();
		System.out.println(driver.sn);

	}
}
