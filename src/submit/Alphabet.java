package submit;

/**
 * Interface that takes one symbol and gets the next. 
 */
public interface Alphabet {
	String next(String s);
	int getSize();
}
