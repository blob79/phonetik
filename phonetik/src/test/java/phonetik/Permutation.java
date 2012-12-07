package phonetik;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
			List<T> values, Add<T> op, int k) {
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