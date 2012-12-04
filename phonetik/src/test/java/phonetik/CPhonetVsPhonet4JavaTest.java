package phonetik;

import static net.java.quickcheck.generator.PrimitiveGenerators.letterStrings;
import static net.java.quickcheck.generator.iterable.Iterables.toIterable;
import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.junit.Test;

import phonet.CPhonet;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;


public class CPhonetVsPhonet4JavaTest {

	@Test public void generated() {
		Phonet1 phonet1 = new Phonet1();
		CPhonet cPhonet = new CPhonet();
		for (String in : toIterable(letterStrings())) {
			assertEquals(phonet1.code(in), cPhonet.phonet(in));
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
