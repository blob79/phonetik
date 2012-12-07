package phonetik;

import static java.lang.String.format;
import static java.util.Arrays.binarySearch;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The <a href=
 * "http://en.wikipedia.org/w/index.php?title=Windows-1252&oldid=516505638#Code_page_layout">
 * CP1252 character encoding</a>.
 */
class CharEncoding {
	
	// the byte values not defined by the encoding (plus \0)
	private static final int[] UNDEFINED = { 0, 129, 141, 143, 144, 157 };
	static final Set<Character> ALL_CHARS;
    static final int NCHARS = 1<<8;
    
    private static final char[] DECIMAL_2_CHAR = new char[NCHARS];
    private static final int[] CHAR_2_DECIMAL = new int[1<<14];
    
	static {
		Arrays.sort(UNDEFINED);
    	
		char[] allChars = encodeBytes(allBytes()).toCharArray();
    	
		for (int i = 1; i < NCHARS; i++) {
			if (! isDefined(i)) {
				allChars[i] = 0;
				continue;
			}
    		CHAR_2_DECIMAL[allChars[i]] = i;
    		DECIMAL_2_CHAR[i] = allChars[i];
    	}
		
		Set<Character> charsSet = toSet(allChars);
		charsSet.remove((char) 0);
		ALL_CHARS = Collections.unmodifiableSet(charsSet);
    }

	private static String encodeBytes(byte[] bytes) {
		try {
			String encoded = new String(bytes, "CP1252");
			assert encoded.length() == NCHARS;
			return encoded;
		} catch (UnsupportedEncodingException e) { 
			throw new RuntimeException(e); 
		}
	}

	private static Set<Character> toSet(char[] allChars) {
		Set<Character> cs = new HashSet<Character>();
		for(char c : allChars) cs.add(c);
		return cs;
	}

	private static byte[] allBytes() {
		byte[] bytes = new byte[NCHARS];
    	for(int i = 0; i < NCHARS; i++) bytes[i] = (byte) i;
		return bytes;
	}
	
	
	static boolean isDefined(int i) {
		return binarySearch(CharEncoding.UNDEFINED, i) < 0;
	}

	public static int toDecimalOrZero(char c) {
    	return c < CHAR_2_DECIMAL.length ? CHAR_2_DECIMAL[c] :  0;
	}
    	
	/**
	 * Convert the character the position in the encoding (n-th byte).
	 */
	public static int toDecimal(char c) {
		int d = toDecimalOrZero(c);
		if (d == 0) {
			String msg = format("Character %d undefined.", (int) c);
			throw new IllegalArgumentException(msg);
		}
		return d;
    }
    
	/**
	 * Convert the position in the encoding (n-th byte) to a character.
	 */
	public static char toChar(int d) {
		if (d > 0 && d < NCHARS && DECIMAL_2_CHAR[d] != 0)
			return DECIMAL_2_CHAR[d];
		throw new IllegalArgumentException(format("Character %d undefined.", d));
		
	}
}