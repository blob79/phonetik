package phonetik;

import static java.lang.Character.MAX_VALUE;
import static java.lang.Character.MIN_VALUE;
import static java.util.Arrays.asList;
import static net.java.quickcheck.generator.CombinedGenerators.ensureValues;
import static net.java.quickcheck.generator.CombinedGenerators.excludeValues;
import static net.java.quickcheck.generator.PrimitiveGenerators.characters;
import static net.java.quickcheck.generator.PrimitiveGenerators.strings;
import static net.java.quickcheck.generator.iterable.Iterables.toIterable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.URL;

import net.java.quickcheck.Generator;

import org.junit.Test;

import phonet.CPhonet;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;

//TODO test long strings
//TODO generated all strings you can create with an byte
public class CPhonetVsPhonet4JavaTest {

	@Test public void phonetDefinedCharacters() {
		Phonet1 phonet1 = new Phonet1();
		CPhonet cPhonet = new CPhonet();
		for(char c: CharEncoding.ALL_CHARS) {
			String in = Character.toString(c);
			assertEquals(cPhonet.phonet(in), phonet1.code(in));
		}
	}
	
	@Test public void phonetUndefinedCharacters() {
		Generator<Character> invalidValues =
				ensureValues(asList('\0'),
				excludeValues(characters(MIN_VALUE, MAX_VALUE), 
					CharEncoding.ALL_CHARS));
		Phonet1 phonet1 = new Phonet1();
		for (char c : toIterable(invalidValues)) {
			String in = Character.toString(c);
			try {
				phonet1.code(in);
				fail();
			} catch(IllegalArgumentException e) { }
		}
	}
	
	@Test public void generated() {
		Phonet1 phonet1 = new Phonet1();
		CPhonet cPhonet = new CPhonet();
		for (String in : toIterable(strings(), 1<<13)) {
			assertEquals(">" + in + "<", phonet1.code(in), cPhonet.phonet(in));
		}
	}
	
	@Test public void phoneticTestData() throws Exception {
		CPhonet cPhonet = new CPhonet();
		Phonet1 phonet1 = new Phonet1();
		for(String resource: new String[] { "/german.txt", "/surnames.txt" }) {
			URL url = getClass().getResource(resource);
			for (String nextLine : CharStreams.readLines(
					Resources.newReaderSupplier(url, Charsets.UTF_8))){
		        String c = cPhonet.phonet(nextLine);
				String java = phonet1.code(nextLine);
				assertEquals(c, java);
		    }
		}
    }
	
}
