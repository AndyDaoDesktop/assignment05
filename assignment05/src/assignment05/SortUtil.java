package assignment05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * [The Problem] We have been asked to provide a robust and efficient routine
 * for sorting Java ArrayLists. Of course, to ensure that our sort routine will
 * be capable of accepting ArrayLists containing objects of any type, the method
 * must be generic.
 * 
 * With so many sorting algorithms to choose from, we are not sure which
 * algorithm will perform the best for any kind of list. We have narrowed our
 * choices down to mergesort and quicksort. We will perform experiments to see
 * which of these two sorting algorithms has the fastest running times for Java
 * ArrayLists of various sizes in the following three categories.
 * 
 * @BestCase: The ArrayList contains objects in sorted order.
 * @AverageCase: The ArrayList contains objects in a permuted order.
 * @WorstCase: The ArrayList contains objects in reverse-sorted order.
 * 
 *             [Implementation Guidelines] Avoid any costly O(N) ArrayList
 *             operations, such as: IndexOf, remove, contains, addAll For
 *             mergesort, pre-allocate a temporary list for merge space once in
 *             the driver method. Avoid allocating and creating enough size for
 *             it during every recursive call. Mergesort should never have
 *             quadratic performance, unless the insertion sort threshold is
 *             very large. When the insertion sort threshold is reached during
 *             mergesort, make sure you only insertion-sort the relevant portion
 *             of the list. Your insertion sort method will have to be modified
 *             to only work on a certain range (i.e., take a left and right
 *             index, and only sort between those indices), instead of sorting
 *             the entire array.
 * 
 *             Quicksort should never have quadratic performance with a decent
 *             pivot selection, such as picking a random index.
 *             
 * @author Andy Dao
 */
public class SortUtil {
	// Add any instance variables here //
	SortUtilComparator<Integer> compInteger = new SortUtilComparator<>();
	SortUtilComparator<Character> compCharacter = new SortUtilComparator<>();

	private static Random randomNum;
	private static int threshhold; 	// threshold on when to switch to insertionSort in mergeSort.
	
	public SortUtil() {

		
	}

	/**
	 * This method performs a mergesort on the generic ArrayList given as input.
	 * 
	 * Your mergesort implementation must switch over to insertion sort when the
	 * size of the sublist to be sorted meets a certain threshold (i.e., becomes
	 * small enough).
	 * 
	 * Make this threshold value a private static variable that you can easily
	 * change. You will perform experiments to determine which threshold value
	 * works best (see the Analysis Document).
	 * 
	 * [Don't forget to include the insertion sort in the program files you
	 * submit.]
	 * 
	 * This is the header method for mergesort
	 * 
	 * @param arrayMerge - the generic ArrayList to sort
	 * @param comparator - comparator to compare the elements
	 */
	public static <T> void mergesort(ArrayList<T> arrayMerge, Comparator<? super T> comparator) {
		ArrayList<T> tempArray = new ArrayList<T>();
		threshhold = 2;
		mergesortRecursive(arrayMerge, tempArray, 0, arrayMerge.size()-1, comparator);
	}
	
	/**
	 * 
	 * @param arrayMerge -  ArrayList to sort
	 * @param tempArrayList - temporary ArrayList used to merge
	 * @param left - the first index of the range to sort
	 * @param right - last index of the array range to sort
	 * @param comparator - comparator to compare the elements
	 */
	public static <T> void  mergesortRecursive(ArrayList<T> arrayMerge, ArrayList<T> tempArrayList, int left, int right, Comparator<? super T> comparator ) {
		if(right - left <= threshhold){ // call insertion sort when size of arraylists are equal or less than the given threshold
			insertionSort(arrayMerge , left, right, comparator);
		}
		if(left < right && (right-left) >= 1){
			int mid = (left + right) / 2;
			
			mergesortRecursive(arrayMerge, tempArrayList, left, mid, comparator); 
			mergesortRecursive(arrayMerge, tempArrayList, mid+1, right, comparator);
			
			tempArrayList.clear(); // The size of the tempArrayList was growing each time merge was getting called, so added a clear, so the tempArrayList was at a size 0 when merging.
			// Sorts the first and second half of the ArrayList
			merge(arrayMerge, tempArrayList, left, mid, right, comparator); // and merges/sorts the array in the merge() method
		}
	}
	
	/**
	 * Sorts the ArrayList using merge sort algorithm.
	 * 
	 * @param arrayValues - the ArrayList needing to be sorted / merged
	 * @param tempArray - temporary ArrayList used for merging
	 * @param start - the first index of the range to sort
	 * @param mid - the first index of the range to sort
	 * @param end - last index of the array range to sort
	 * @param comparator - comparator to compare the elements
	 */
	public static <T> void merge(ArrayList<T> arrayValues, ArrayList<T> tempArrayList, int start, int mid, int end, Comparator<? super T> comparator ){
		// Next element to consider in the first half of arrayValues<T>
		int startValueIndex = start; 
		
		// Next element to consider in the second half of arrayValues<T>
		int midValueIndex = mid + 1;
		
		// As long as neither start or mid pass the end, move the smaller element into tempArrayValues<T>
		while(startValueIndex <= mid && midValueIndex <= end)
		{
			if(comparator.compare(arrayValues.get(startValueIndex), arrayValues.get(midValueIndex)) <= 0){
				tempArrayList.add(arrayValues.get(startValueIndex)); // adds smaller element of the left array on farthest left index into temporary array
				startValueIndex++; // increments this index so it can check
			}
			else{
				tempArrayList.add(arrayValues.get(midValueIndex)); // adds smaller element of the right array on farthest left index into temporary array
				midValueIndex++;
			}
		}
		
		// Only one of the while loops below will execute if there's still remaining elements.
		// Take remaining elements of the first half ArrayList
		while(startValueIndex <= mid){
			tempArrayList.add(arrayValues.get(startValueIndex));
			startValueIndex++;
		}
		
		// Take remaining elements of the second half ArrayList
		while(midValueIndex <= end){
			tempArrayList.add(arrayValues.get(midValueIndex));
			midValueIndex++;
		}
		
		int i = 0;
		int j = start;
		while(i < tempArrayList.size()){
			arrayValues.set(j, tempArrayList.get(i++));
			j++;
		}
	}
	
	/**
	 * This generic method sorts the input array using an insertion sort and the
	 * input Comparator object.
	 */
	public static <T> void insertionSort(ArrayList<T> array, int left, int right, Comparator<? super T> comparatorObj) {
		int i, j;
		T index;
		for(i = 1; i < left; i++){
			j = i;
			index = array.get(i); // <-- item to be inserted
			
			// This part will shift the items until the insertion position is found
			while(j > 0 && comparatorObj.compare(array.get(j - 1), index) > 0){
				array.set(j, array.get(j - 1));
				j--;
			}
			array.set(j, index); // insert value of index into index j
		}
	}
	

	/**
	 * This method performs a quicksort on the generic ArrayList given as input.
	 * 
	 * For the quicksort algorithm, see the class notes and/or the textbook.
	 * There is pseudo code in the slides. You must implement three different
	 * strategies for determining the pivot.
	 * 
	 * Your quicksort implementation should be able to easily switch among these
	 * strategies. (Consider using a few private helper methods for your
	 * different pivot selection strategies.) You will perform experiments to
	 * determine which pivot strategy works best (see the Analysis Document).
	 * Your quicksort may also switch to insertion sort on some small threshold
	 * if you wish.
	 * 
	 * 1: select an item in the array to be the pivot 2: partition the array so
	 * that all items less than the pivot are to the left of the pivot, and all
	 * the items greater than the pivot are to the right. 3: take the left half
	 * and go back to step 1 4: take the right half and go back to step 1
	 * 
	 */
	public static <T> void quicksort(ArrayList<T> arrayQuick, Comparator<? super T> comparator) {

		
		
		
		
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * ascending order.
	 * 
	 * @return an ArrayList of integers 1 to size of ascending order.
	 */
	public static ArrayList<Integer> generateBestCase(int size) {
		ArrayList<Integer> arrayListBest = new ArrayList<Integer>();
		
		for(int i = 1; i <= size; i++){
			arrayListBest.add(i);
		}
		return arrayListBest;
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * permuted order (i,e., randomly ordered).
	 * 
	 * @return an ArrayList of integers 1 to size of random order.
	 */
	public static ArrayList<Integer> generateAverageCase(int size) {
		Integer[] arr = new Integer[size]; // Creates an int array of the given size
		int j = 0;
		// Numbers 1 through the size will be added in the array in ascending order
		for(int i = 1; i <= size; i++){ 
			arr[j] = i; 
			j++;
		}
		Collections.shuffle(Arrays.asList(arr)); // shuffle the array
		ArrayList<Integer> arrayListRandom = new ArrayList<Integer>(Arrays.asList(arr)); // convert the array into an ArrayList
		
//		randomNum = new Random();
//		
//		for(int i = 1; i <= size; i++){
//			arrayListRandom.add(size);
//		}
		
		
		return arrayListRandom;
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * descending order.
	 * 
	 * @return an ArrayList of integers 1 to size in descending order.
	 */
	public static ArrayList<Integer> generateWorstCase(int size) {
		ArrayList<Integer> arrayListWorst = new ArrayList<Integer>();
		
		for(int i = 1; size >= i; size--){
			arrayListWorst.add(size);
		}
		return arrayListWorst;
	}
}
