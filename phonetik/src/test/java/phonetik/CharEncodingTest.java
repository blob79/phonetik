package phonetik;

import static java.lang.Character.MAX_VALUE;
import static java.lang.Character.MIN_VALUE;
import static java.util.Arrays.asList;
import static net.java.quickcheck.generator.CombinedGenerators.ensureValues;
import static net.java.quickcheck.generator.CombinedGenerators.excludeValues;
import static net.java.quickcheck.generator.CombinedGenerators.oneOf;
import static net.java.quickcheck.generator.PrimitiveGenerators.characters;
import static net.java.quickcheck.generator.PrimitiveGenerators.integers;
import static net.java.quickcheck.generator.iterable.Iterables.toIterable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import net.java.quickcheck.Generator;

import org.junit.Test;

public class CharEncodingTest {

	@Test public void toCharToDecimal() {
		for (int i = 0; i < CharEncoding.NCHARS; i++) {
			if (! CharEncoding.isDefined(i)) continue;
			assertEquals(i, CharEncoding.toDecimal(CharEncoding.toChar(i)));
		}
	}
	
	@Test public void toDecimalToChar() {
		for (char c : CharEncoding.ALL_CHARS) {
			assertEquals(c, CharEncoding.toChar(CharEncoding.toDecimal(c)));
		}
	}
	
	@Test public void toCharUndefinedCharacter() {
		for (int i = 0; i < CharEncoding.NCHARS; i++) {
			if (CharEncoding.isDefined(i)) continue;
			try {
				CharEncoding.toChar(i);
				fail();
			} catch (IllegalArgumentException e) {
			}
		}
	}
	
	@Test public void toCharInvalidDecimal() {
		Generator<Integer> invalidDecimals = 
			oneOf(integers(Integer.MIN_VALUE, -1))
			.add(integers(1 << 8));
		for(int invalid : toIterable(invalidDecimals)) {
			try {
				CharEncoding.toChar(invalid);
				fail();
			} catch (IllegalArgumentException e) {
			}
		}
	}
	
	@Test public void toDecimalInvalidChar() {
		Generator<Character> invalidValues =
			ensureValues(asList('\0'),
			excludeValues(characters(MIN_VALUE, MAX_VALUE), 
				CharEncoding.ALL_CHARS));
		
		for (Character invalid : toIterable(invalidValues)) {
			try {
				CharEncoding.toDecimal(invalid);
				fail();
			} catch (IllegalArgumentException e) {
			}
		}
		
	}

}
