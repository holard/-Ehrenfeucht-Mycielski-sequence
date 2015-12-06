package submit;




/**
 * Node structure for Inverted Prefix Trees. This implementation
 * 		assumes a char alphabet, and compresses paths into a string.
 */
public class RadixNumNode {
	public RadixNumNode parent;
	public RadixNumNode[] children;
	public String val;
	public int label;
	
	/**
	 * Initializes a node with empty values and a label of -1.
	 */
	public RadixNumNode(int base) {
		val = "";
		label = -1;
		children = new RadixNumNode[base];
	}
	
	public RadixNumNode(String value, int base) {
		val = value;
		label = -1;
		children = new RadixNumNode[base];
	}
	
	
	/**
	 * Get the new state after following an (implicit) edge.
	 * @param edge
	 * 		The symbol representing the edge to follow.
	 * @return
	 * 		A pair of: 1. The child node corresponding to the given symbol,
	 * 		or null if no child exists. 2. The new index.
	 */
	public Pair<RadixNumNode, Integer> find(char edge, int index)
	{
		// Case where we are following an edge at the end of our String.
		if (index >= val.length()-1)
		{
			RadixNumNode r = children[edge - '0'];
			return new Pair<RadixNumNode, Integer>(r,0);
		}
		
		// Case where we are following an edge in the middle of the String.
		char next = val.charAt(index + 1);
		if (edge == next)
			return new Pair<RadixNumNode,Integer>(this,index+1);
		return new Pair<RadixNumNode,Integer>(null,0);
	}
	
	/**
	 * Adds a new node and returns it.
	 * @param edge
	 * 		The string to insert
	 * @param index
	 * 		The index (of the current node's string) to split at 
	 * @param oldlabel
	 * 		The label to assign the leftover portion of the node
	 * 		that results from splitting in the middle of the string.
	 * @return		The new node we inserted.
	 */
	public RadixNumNode insert(String edge, int index, int oldlabel)
	{
		// Case where we are inserting at the end of the node (no splitting)
		if (index >= val.length()-1)
		{
			RadixNumNode n = new RadixNumNode(edge, children.length);
			children[edge.charAt(0) - '0'] = n;
			return n;
		}
		
		// Case where we are inserting mid-string, so we need to split our old
		// 		node into two nodes
		String next = val.substring(index+1);
		val = val.substring(0,index+1);
		RadixNumNode n2 = new RadixNumNode(next, children.length);
		n2.children = this.children;
		children = new RadixNumNode[children.length];
		n2.label = oldlabel;
		children[next.charAt(0) - '0'] = n2;
		RadixNumNode ret = new RadixNumNode(edge, children.length);
		children[edge.charAt(0) - '0'] = ret;
		return ret;
	}
}
