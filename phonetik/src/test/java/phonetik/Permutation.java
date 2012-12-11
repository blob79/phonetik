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

public class Permutation<T> implements Iterable<T> {

	public interface Add<T> {
		T add(T l, T r);
	}
	
	public final static Add<String> STRING_ADD = new Add<String>() {
		@Override public String add(String l, String r) { return l + r;}
	};
	
	private int size;
	private Iterable<T> values;
	private Iterable<T> child;
	protected T next;
	private Add<T> op;
	
	/**
	 * Permutation of k elements from a list of values with repetition.
	 */
	public static <T> Iterable<T> kPermutationWithRepetition(
			Iterable<T> values, Add<T> op, int k) {
		return new Permutation<T>(values, op, k);
	}
	
	private Permutation(Iterable<T> values, Add<T> op, int size) {
		this.values = values;
		this.op = op;
		this.child = size > 1 ? 
			new Permutation<T>(values, op, size - 1) : 
			Collections.<T> emptyList();
		this.size = size;
	}

	@Override public Iterator<T> iterator() {
		return new Iterator<T>() {
			Iterator<T> head = values.iterator();
			Iterator<T> childState = Collections.<T> emptyList().iterator();
			@Override public boolean hasNext() {
				return head.hasNext() || childState.hasNext();
			}

			@Override public T next() {
				if(next == null || ! childState.hasNext()) {
					next = head.next();
					childState = child.iterator();
				}
				return size == 1 ? next : op.add(next, childState.next());
			}

			@Override public void remove() { }
		};
	}
	
}