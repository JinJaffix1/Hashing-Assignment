
public class Edge
{
	protected Node friend;
	
	public Edge(Node friend) {
		this.friend = friend;
	}
	
	public String toString() {
		String str ="friend" + friend.toString();
		return str;
	}
	
}
