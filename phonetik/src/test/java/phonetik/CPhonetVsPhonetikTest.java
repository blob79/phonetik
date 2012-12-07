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
import static phonetik.Permutation.kPermutationWithRepetition;
import static phonetik.Permutation.STRING_ADD;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.java.quickcheck.Generator;

import org.junit.Test;

import phonet.CPhonet;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;

//TODO test long strings
public abstract class CPhonetVsPhonetikTest {

	abstract Coder createCoder();
	abstract int rules(); 

	@Test public void phonetDefinedCharacters() {
		Coder jphonet = createCoder();
		CPhonet cPhonet = new CPhonet();
		
		List<String> all = new ArrayList<String>();
		for(char c : CharEncoding.ALL_CHARS) all.add(Character.toString(c));
		
		for (int size = 1; size <= 2; size++) {
			for (String in : 
					kPermutationWithRepetition(all, STRING_ADD, size)) {
				assertEquals(in, cPhonet.phonet(in, rules()), jphonet.code(in));
			}
		}
	}
	

	@Test public void phonetUndefinedCharacters() {
		Generator<Character> invalidValues =
				ensureValues(asList('\0'),
				excludeValues(characters(MIN_VALUE, MAX_VALUE), 
					CharEncoding.ALL_CHARS));
		Coder jphonet = createCoder();
		for (char c : toIterable(invalidValues)) {
			String in = Character.toString(c);
			try {
				jphonet.code(in);
				fail();
			} catch(IllegalArgumentException e) { }
		}
	}
	
	
	@Test public void generated() {
		Coder jphonet = createCoder();
		CPhonet cPhonet = new CPhonet();
		for (String in : toIterable(strings(), 1<<13)) {
			assertEquals(">" + in + "<", jphonet.code(in), cPhonet.phonet(in, rules()));
		}
	}
	
	@Test public void phoneticTestData() throws Exception {
		CPhonet cPhonet = new CPhonet();
		Coder jphonet = createCoder();
		for(String resource: new String[] { "/german.txt", "/surnames.txt" }) {
			URL url = getClass().getResource(resource);
			for (String nextLine : CharStreams.readLines(
					Resources.newReaderSupplier(url, Charsets.UTF_8))){
		        String c = cPhonet.phonet(nextLine, rules());
				String java = jphonet.code(nextLine);
				assertEquals(c, java);
		    }
		}
    }
}
