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
import static net.java.quickcheck.generator.PrimitiveGeneratorSamples.anyString;
import static net.java.quickcheck.generator.PrimitiveGenerators.strings;
import static net.java.quickcheck.generator.iterable.Iterables.toIterable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static phonetik.Permutation.STRING_ADD;
import static phonetik.Permutation.kPermutationWithRepetition;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Functions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;

public class PermutationTest {

	@Test public void kPermutationWithRepetitionListOfSize1() {
		List<String> vs = anyList(strings());
		assertEquals(vs, Lists.newArrayList(kPermutationWithRepetition(vs, STRING_ADD, 1)));
	}

	@Test public void kPermutationWithRepetitions() {
		for(String allowedChars : toIterable(strings(1, 6))) {
			String expected = anyString(
					allowedChars, allowedChars.length(), allowedChars.length());
			ImmutableSet<String> vs = 
				FluentIterable.from(Chars.asList(expected.toCharArray()))
					.transform(Functions.toStringFunction())
					.toImmutableSet();
			
			int k = expected.length();
			Iterable<String> actual = 
				kPermutationWithRepetition(vs, STRING_ADD, k);
			
			assertEquals(Math.pow(vs.size(), k), Iterables.size(actual), 0);
			assertTrue(Iterables.contains(actual, expected));
		}
	}
}
