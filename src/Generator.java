import java.util.HashMap;


/**
 * EM-Sequence generator using Inverted Prefix Tries, as specified in 
 * 		Herman-Soltys '07
 */
public class Generator {

	String seed;
	Node IPT;
	String w;
	int index;
	String a;
	Alphabet alph;
	String e;
	HashMap<String,Integer> counts;
	
	/**
	 * @param seed
	 * 		Seed string for the sequence.
	 * @param al
	 * 		Alphabet object to generate the next bit in the sequence.
	 */
	public Generator(String seed, Alphabet al)
	{
		this.seed = seed;
		IPT = new Node();
		w = "";
		IPT.label = 0;
		index = 1;
		alph = al;
		e = "#";
		counts = new HashMap<String,Integer>();
	}
	
	
	/**
	 * @param c
	 * 		Symbol to find the density for.
	 * @return
	 * 		The density of 'c'
	 */
	public double getDensity(String c)
	{
		return (double)counts.get(c)/(e.length()-1);
	}
	
	/**
	 * Increments the count for symbol c
	 * @param c
	 * 		The symbol to increment
	 */
	private void logHit(String c)
	{
		if (!counts.containsKey(c))
		{
			counts.put(c, 0);
		}
		counts.put(c,counts.get(c)+1);
	}
	
	/**
	 * Gets the next symbol.
	 * @return
	 * 		The next symbol.
	 */
	public String next()
	{
		if (index <= seed.length())
		{
			a = "" + seed.charAt(index-1);
		}

		w = a+w;
		int i = 0;
		Node curr = IPT;
		Node prev = null;
		int k = -1;

		while (i < w.length())
		{
			String c = w.substring(i, i+1);
			
			if (curr != null) // Following existing branches
			{
				k = curr.label;
				curr.label = index;
				prev = curr;
				curr = curr.find(c);
			} 
			if (curr == null) // Adding a new branch.
			{
				Node next = new Node();
				next.val = c;
				next.label = index;
				prev.children.put(c,next);
				prev = next;
			}
			i += 1;
		}
		
		String toret = "";
		toret += a;
		logHit(toret);
		
		e += a;
		a = alph.next(e.substring(k+1, k+2));

		index += 1;
		return toret;
	}
}
