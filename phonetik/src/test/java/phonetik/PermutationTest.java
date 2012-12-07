package phonetik;

import static net.java.quickcheck.generator.CombinedGeneratorSamples.anyList;
import static net.java.quickcheck.generator.PrimitiveGeneratorSamples.anyInteger;
import static net.java.quickcheck.generator.PrimitiveGenerators.strings;
import static org.junit.Assert.assertEquals;
import static phonetik.Permutation.kPermutationWithRepetition;
import static phonetik.Permutation.STRING_ADD;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class PermutationTest {

	@Test public void kPermutationWithRepetitionListOfSize1() {
		List<String> vs = anyList(strings());
		assertEquals(vs, Lists.newArrayList(kPermutationWithRepetition(vs, STRING_ADD, 1)));
	}

	@Test public void kPermutationWithRepetitionListSizes() {
		List<String> vs = anyList(strings());
		int size = anyInteger(1,3);
		assertEquals(Math.pow(vs.size(), size),
				Iterables.size(kPermutationWithRepetition(vs, STRING_ADD, size)), 0);
	}

}
