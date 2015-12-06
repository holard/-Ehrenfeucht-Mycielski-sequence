package submit;


public class Main {
	public static void main(String[] args)
	{
		String seed = "0";
		
		// DO NOT GO PAST 10, the density counts are character-based
		Alphabet A = new ModAlphabet(10);
		
		// EM sequence generator based on Inverted Radix Trees
		RadixNumGenerator g = new RadixNumGenerator(seed, A);
		for (int i = 0; i < 15000; i++)
		{
			if (i % 200 == 0)
				System.out.println("\n" + i/200 + ", size = " + g.size);
			System.out.print(g.next());
		}
		
		// Print densities of each symbol
		for (int j = 0; j < A.getSize(); j++)
		{
			System.out.println("Density of " + j + ": " + g.getDensity(String.valueOf(j)));
		}
	}
}
