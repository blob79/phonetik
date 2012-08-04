/**
 *
 */
package phonetik;

import phonetik.Phonet1;
import junit.framework.TestCase;


/**
 * @author Jesper Zedlitz &lt;jze@informatik.uni-kiel.de&gt;
 *
 */
public class Phonet1Test extends TestCase {
    Phonet1 coder = new Phonet1();

    public void testEmpty() {
	assertEquals("", coder.code(""));
    }	

    public void testNull() {
	assertEquals("", coder.code(null));
    }	

    public void testWithTrace() {
	coder.trace = true;
	assertEquals("ZETLIZ", coder.code("Zedlitz"));
    }
    
    public void testZedlitz() {
        assertEquals("ZETLIZ", coder.code("Zedlitz"));
    }

    public void testBremerhaven() throws Exception {
        assertEquals("BREMAHAFN", coder.code("Bremerhaven"));
    }

    public void testHamburgerHafen() {
        assertEquals("HAMBURGA HAFN", coder.code("Hamburger Hafen"));
    }

    public void testJesper() {
        assertEquals("IESPA", coder.code("Jesper"));
    }

    public void testElisabeth() {
        assertEquals("ELISABET", coder.code("elisabeth"));
    }

    public void testElisabet() {
        assertEquals("ELISABET", coder.code("elisabet"));
    }

    public void testZiegler() {
        assertEquals("ZIKLA", coder.code("Ziegler"));
    }

    public void testScherer() {
        assertEquals("SHERA", coder.code("Scherer"));
    }

    public void testBartels() {
        assertEquals("BARTLS", coder.code("Bartels"));
    }

    public void testJansen() {
        assertEquals("IANSN", coder.code("Jansen"));
    }

    public void testSievers() {
        assertEquals("SIWAS", coder.code("Sievers"));
    }

    public void testMichels() {
        assertEquals("MICHLS", coder.code("Michels"));
    }

    public void testEwers() {
        assertEquals("EWERS", coder.code("Ewers"));
    }

    public void testEvers() {
        assertEquals("EWERS", coder.code("Evers"));
    }
    public void testWessels() {
	assertEquals("WESLS", coder.code("Wessels"));
    }
    
    public void testGottschalk() {
	assertEquals("GOSHALK", coder.code("Gottschalk"));
    }
    public void testBrückmann() {
	assertEquals("BRÜKMAN", coder.code("Brückmann"));
    }
    	
    public void testBlechschmidt() {
	assertEquals("BLECHSHMIT", coder.code("Blechschmidt"));
    }

    public void testKolodziej() {
	assertEquals("KOLOTZI", coder.code("Kolodziej"));
    }	
    public void testKrauße() {
	assertEquals("KRAUSE", coder.code("Krauße"));
    }	
    public void testCachel() {
	assertEquals("KESHL", coder.code("Cachel"));
    }
}
