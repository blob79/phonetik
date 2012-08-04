/**
 *
 */
package phonetik;

import phonetik.Coder;
import phonetik.Phonet2;

import junit.framework.TestCase;


/**
 * @author Jesper Zedlitz &lt;jze@informatik.uni-kiel.de&gt;
 *
 */
public class Phonet2Test extends TestCase {
    Coder coder = new Phonet2();

    public void testZedlitz() throws Exception {
        assertEquals("ZETLIZ", coder.code("Zedlitz"));
    }

    public void testBremerhaven() throws Exception {
        assertEquals("BRENAFN", coder.code("Bremerhaven"));
    }
    public void testSchönberg() {
	assertEquals("ZÖNBAK",  coder.code("Schönberg"));
    }
    
    public void testHamburgerHafen() {
	assertEquals("ANBURKA AFN", coder.code("Hamburger Hafen"));
    }
    

    public void testZiegler() {
	assertEquals("ZIKLA", coder.code("Ziegler"));
    }
    
    public void testScherer() {
	assertEquals("ZERA", coder.code("Scherer"));
    }
    
    public void testJansen() {
	assertEquals("IANZN", coder.code("Jansen"));
    }
    
    public void testEberhardt() {
	assertEquals("EBART", coder.code("Eberhardt"));
    }
    
    public void testGottschalk() {
	assertEquals("KUZALK", coder.code("Gottschalk"));
    }
    
    public void testBrückmann() {
	assertEquals("BRIKNAN", coder.code("Brückmann"));
    }public void testBlechschmidt() {
	assertEquals("BLEKZNIT", coder.code("Blechschmidt"));
    }
    public void testKolodziej() {
	assertEquals("KULUTZI", coder.code("Kolodziej"));
    }	
    public void testKrauße() {
	assertEquals("KRAUZE", coder.code("Krauße"));
    }	

    
}
