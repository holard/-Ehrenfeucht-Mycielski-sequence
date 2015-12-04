import java.util.HashMap;
import java.util.Map;


/**
 * Node structure for Inverted Prefix Trees. The 'val' field denotes the
 * 		symbol on the edge from this Node to its parent.
 */
public class Node {
	public Node parent;
	public Map<String,Node> children;
	public String val;
	public int label;
	
	/**
	 * Initializes a node with empty values and a label of -1.
	 */
	public Node() {
		val = "";
		children = new HashMap<String,Node>();
		label = -1;
	}
	
	
	/**
	 * @param edge
	 * 		The symbol representing the edge to follow.
	 * @return
	 * 		The child node corresponding to the given symbol, or null
	 * 		if no child exists.
	 */
	public Node find(String edge)
	{
		return children.get(edge);
	}
	
	
}
