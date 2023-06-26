package com.comino.mavcom.model.buffers;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * A {@code Map} implementation that grows to a fixed size and then retains only
 * a fixed number of the highest (largest) keys.  All keys used in this class
 * must implements {@link Comparable}.
 *
 * @see BoundedSortedMultiMap
 */
public class BoundedSortedMap<K,V> extends TreeMap<K,V> {

	private static final long serialVersionUID = 1;

	/**
	 * The maximum number of mappings to retain.
	 */
	private final int bound;

	/**
	 * Creatins an instance that will only retain the specified number of the
	 * largest (highest) keys.
	 *
	 * @param bound the number of mappings to retain
	 */
	public BoundedSortedMap(int bound) {
		this(bound, true);
	}

	/**
	 * Creatins an instance that will only retain the specified number of keys,
	 * where the largest (highest) keys.
	 *
	 * @param bound the number of mappings to retain
	 * @param retainHighest {@code true} if the highest elements are to be
	 *        retained, {@code false} if the lowest keys are to be retained
	 */
	public BoundedSortedMap(int bound, boolean retainHighest) {
		super(((retainHighest) ? null : new ReverseComparator<K>()));
		this.bound = bound;
	}

	/**
	 * Adds the key-value mapping to this map, and if the total number of
	 * mappings exceeds the bounds, removes either the currently lowest element,
	 * or if reversed, the currently highest element.
	 *
	 * @param key {@inheritDoc}
	 * @param value {@inheritDoc}
	 */
	public V put(K key, V value) {
		V old = super.put(key, value);
		if (size() > bound) {
			remove(firstKey());
		}
		return old;
	}

	/**
	 * Adds all of the key-value mapping to this map, and if the total number of
	 * mappings exceeds the bounds, removes mappings until the size is within
	 * bounds.
	 *
	 * @param m {@inheritDoc}
	 */
	public void putAll(Map<? extends K,? extends V> m) {
		for (Map.Entry<? extends K,? extends V> e : m.entrySet()) {
			put(e.getKey(), e.getValue());
		}
	}

	/**
	 * A comparator that results in the opposite ordering of the natural
	 * ordering from {@link Comparator#compareTo(Object,Object) compareTo}.
	 */
	static final class ReverseComparator<K> 
	implements Comparator<K>, java.io.Serializable {

		private static final long serialVersionUID = 1;

		// Assume that if the comparator is being used that the objects are
		// instances of Comparable
		@SuppressWarnings("unchecked")
		public int compare(K c1, K c2) {
			return -(((Comparable)c1).compareTo(c2));
		}
	}
}