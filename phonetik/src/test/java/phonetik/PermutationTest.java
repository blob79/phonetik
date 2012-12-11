/*
 * PermutationTest.java
 *
 * Copyright (c) 2012, Thomas Jung. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package phonetik;

import static net.java.quickcheck.generator.CombinedGeneratorSamples.anyList;
import static net.java.quickcheck.generator.CombinedGenerators.sets;
import static net.java.quickcheck.generator.PrimitiveGeneratorSamples.anyString;
import static net.java.quickcheck.generator.PrimitiveGenerators.characters;
import static net.java.quickcheck.generator.PrimitiveGenerators.fixedValues;
import static net.java.quickcheck.generator.iterable.Iterables.toIterable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static phonetik.Permutation.STRING_ADD;
import static phonetik.Permutation.kPermutationWithRepetition;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.Functions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class PermutationTest {

	@Test public void kPermutationWithRepetitionListOfSize1() {
		List<Character> vs = anyList(characters());
		List<String> actual = 
				Lists.newArrayList(kPermutationWithRepetition(vs, STRING_ADD, 1));
		ImmutableList<String> expected = 
			FluentIterable.from(vs)
				.transform(Functions.toStringFunction())
				.toImmutableList();
		assertEquals(expected, actual);
	}

	@Test public void kPermutationWithRepetitions() {
		for(Set<Character> allowedChars : toIterable(sets(characters(), 1, 6))) {
			String expected = anyString(
					fixedValues(allowedChars.size()), fixedValues(allowedChars));
			int k = expected.length();
			Iterable<String> actual = 
				kPermutationWithRepetition(allowedChars, STRING_ADD, k);
			
			assertEquals(Math.pow(allowedChars.size(), k), Iterables.size(actual), 0);
			assertTrue(Iterables.contains(actual, expected));
		}
	}
}
