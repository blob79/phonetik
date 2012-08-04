/**
 *
 */
package phonetik;


/**
 * @author Jesper Zedlitz &lt;jze@informatik.uni-kiel.de&gt;
 *
 */
public class Phonet2 extends phonetik.Phonet implements Coder {
    /**
     * @see phonetik.Coder#code(java.lang.String)
     */
    public String code(final String input) {
        return this.phonet(input, 2);
    }
}
