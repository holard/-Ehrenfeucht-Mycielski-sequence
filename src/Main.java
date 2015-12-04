
public class Main {
	public static void main(String[] args)
	{
		// Different alphabet sizes 
		Generator g = new Generator("0", new ModAlphabet(8));
		for (int i = 0; i < 100; i++)
		{
			System.out.print(g.next());
		}
		
		System.out.println();
		System.out.println(g.getDensity("2"));
		
		
		// Different seeds
		g = new Generator("010011", new ModAlphabet(2));
		for (int i = 0; i < 100; i++)
		{
			System.out.print(g.next());
		}
		System.out.println();
	}
}
