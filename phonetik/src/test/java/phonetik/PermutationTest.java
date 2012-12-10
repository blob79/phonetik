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
