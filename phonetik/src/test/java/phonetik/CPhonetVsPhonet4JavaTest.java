package phonetik;

import junit.framework.TestCase;
import net.java.quickcheck.generator.PrimitiveGeneratorSamples;
import phonet.CPhonet;


public class CPhonetVsPhonet4JavaTest extends TestCase {

	public void test1() {
		Phonet1 phonet1 = new Phonet1();
		CPhonet cPhonet = new CPhonet();
		String in = PrimitiveGeneratorSamples.anyLetterString();
		assertEquals(phonet1.code(in), cPhonet.phonet(in));
	}
}
