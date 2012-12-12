/*
 * Permutation.java
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

import java.util.Collections;
import java.util.Iterator;

public class Permutation<I, T> implements Iterable<T> {

	public interface Add<I, T> {
		T identity();
		T prepend(I r, T l);
	}
	
	public final static Add<Character, String> STRING_ADD = 
			new Add<Character, String>() {
		@Override public String prepend(Character r, String l) { return r + l; }
		@Override public String identity() { return ""; }
	};
	
	private int size;
	private Iterable<I> values;
	private Iterable<T> child;
	protected I next;
	private Add<I, T> op;
	
	/**
	 * Permutation of k elements from a list of values with repetition.
	 */
	public static <I, T> Iterable<T> kPermutationWithRepetition(
			Iterable<I> values, Add<I, T> op, int k) {
		return new Permutation<I, T>(values, op, k);
	}
	
	private Permutation(Iterable<I> values, Add<I, T> op, int size) {
		this.values = values;
		this.op = op;
		this.child = size > 1 ? 
			new Permutation<I, T>(values, op, size - 1) : 
			Collections.<T> emptyList();
		this.size = size;
	}

	@Override public Iterator<T> iterator() {
		return new Iterator<T>() {
			Iterator<I> head = values.iterator();
			Iterator<T> childState = Collections.<T> emptyList().iterator();
			@Override public boolean hasNext() {
				return head.hasNext() || childState.hasNext();
			}

			@Override public T next() {
				if(next == null || ! childState.hasNext()) {
					next = head.next();
					childState = child.iterator();
				}
				return size == 1 ? 
						op.prepend(next, op.identity()) : 
						op.prepend(next, childState.next());
			}

			@Override public void remove() { }
		};
	}
	
}