package submit;
import java.util.HashMap;


/**
 * EM-Sequence generator using Inverted Radix Tries, as specified in 
 * Herman-Soltys '07. Has O(log(n)) time complexity per bit.
 */
public class RadixNumGenerator {

	String seed;
	RadixNumNode IPT;
	String w;
	int index;
	String a;
	Alphabet alph;
	String e;
	HashMap<String,Integer> counts;
	public int size;
	
	/**
	 * @param seed
	 * 		Seed string for the sequence.
	 * @param al
	 * 		Alphabet object to generate the next bit in the sequence.
	 */
	public RadixNumGenerator(String seed, Alphabet al)
	{
		this.seed = seed;
		IPT = new RadixNumNode(al.getSize());
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
		RadixNumNode curr = IPT;
		RadixNumNode prev = null;
		int currindex = 0;
		int previndex = 0;
		int k = -1;

		while (i < w.length())
		{
			char c = w.charAt(i);
			
			if (curr != null) // Following existing branches
			{
				if (curr.label != index)
					k = curr.label;

				curr.label = index;
				prev = curr;
				Pair<RadixNumNode,Integer> pair = curr.find(c, currindex);
				previndex = currindex;
				currindex = pair.second();
				curr = pair.first();
			} 
			if (curr == null) // Adding a new branch.
			{
				size += 1;
				prev = prev.insert(w.substring(i), previndex,k);
				prev.label = index;
				break;
			}
			i += 1;
		}
		
		String toret = "";
		toret += a;
		logHit(toret);

		e += a;
		a = alph.next("" + e.charAt(k+1));

		index += 1;
		return toret;
	}
}
