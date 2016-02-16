package assignment05;

import java.util.Comparator;

/**
 * Lexicographical order Comparator Object for use in the getLargestAnagramGroup() that ignores case.
 * @author Andy Dao
 */
public class SortUtilComparator<T extends Comparable<? super T>> implements Comparator<T> {
	/**
	 * Compares two objects (in this case, 2 String objects.
	 * 
	 * @returns 
	 * 			-the order of the 2 objects depending on value. It will ignore all capitalizations.
	 */
	public int compare(T o1, T o2) {
		return o1.compareTo(o2);
	}
}