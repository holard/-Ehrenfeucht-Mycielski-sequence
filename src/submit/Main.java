package submit;


public class Main {
	public static void main(String[] args)
	{
		String seed = "0";
		// DO NOT GO PAST 10, the density counts and string-comparisons
		//     are character-based
		Alphabet A = new ModAlphabet(2);
		
		// EM sequence generator based on Inverted Radix Trees
		RadixNumGenerator g = new RadixNumGenerator(seed, A);
		for (int i = 0; i < 128; i++)
		{
			Bundle next = g.next();
			System.out.println(next.symbol + ", match length = " + next.length + 
								", match position = " + next.position);
		}
		
		// Print densities of each symbol
		for (int j = 0; j < A.getSize(); j++)
		{
			System.out.println("Density of " + j + ": " + g.getDensity(String.valueOf(j)));
		}
	}
}
