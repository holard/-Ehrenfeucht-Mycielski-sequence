package submit;

/**
 * Alphabet implementation with a given size. Uses strings "0", "1", etc.
 * Returns the next integer mod its base.
 * Example: binary ModAlphabet (with base 2) just flips the given bit.
 */
public class ModAlphabet implements Alphabet {

	int base;
	
	public ModAlphabet(int base)
	{
		this.base = base;
	}
	@Override
	public String next(String s) {
		int val = Integer.valueOf(s);
		val = (val+1) % base;
		return String.valueOf(val);
	}
	@Override
	public int getSize() {
		return base;
	}

}
