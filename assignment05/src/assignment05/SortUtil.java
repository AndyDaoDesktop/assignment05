package assignment05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * We have been asked to provide a robust and efficient routine
 * for sorting Java ArrayLists. Of course, to ensure that our sort routine will
 * be capable of accepting ArrayLists containing objects of any type, the method
 * must be generic.
 * 
 * @BestCase: The ArrayList contains objects in sorted order.
 * @AverageCase: The ArrayList contains objects in a permuted order.
 * @WorstCase: The ArrayList contains objects in reverse-sorted order.
 * 
 * [Implementation Guidelines] 
 * Avoid any costly O(N) ArrayList operations, such as: IndexOf, remove, contains, addAll For mergesort, pre-allocate 
 * a temporary list for merge space once in	the driver method. Avoid allocating and creating enough 
 * size for it during every recursive call. Mergesort should never have quadratic performance, 
 * unless the insertion sort threshold is very large. When the insertion sort threshold is reached during mergesort, 
 * make sure you only insertion-sort the relevant portion  of the list. Your insertion sort method will have to be modified
 * to only work on a certain range (i.e., take a left and right index, and only sort between those indices), instead of sorting 
 * the entire array.
 * 
 * @author Andy Dao and Jonathan Boyle
 */
public class SortUtil {
	// Add any instance variables here //
	private static int threshold = 10; 	// threshold on when to switch to insertionSort in mergeSort.
	private static int pivotStyle = 0;		//variable to select pivot style
	

	
	public static void setThreshold(int _threshold) {
		threshold = _threshold;
	}
	public static int getThreshold() {
		return threshold;
	}
	public static void setPivot(int _pivot){ 
		pivotStyle = _pivot;
	}
	public static int getPivotStyle() {
		return pivotStyle;
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
	 * This is the header method for mergesort
	 * 
	 * @param arrayMerge - the generic ArrayList to sort
	 * @param comparator - comparator to compare the elements
	 */
	public static <T> void mergesort(ArrayList<T> arrayMerge, Comparator<? super T> comparator) {
		ArrayList<T> tempArrayList = new ArrayList<T>();
		setThreshold(10);
		mergesortRecursive(arrayMerge, tempArrayList, 0, arrayMerge.size()-1, comparator);
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
		if(right - left <= getThreshold()){ // call insertion sort when size of arraylists are equal or less than the given threshold
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
	 * @param comparator - generic comparator to compare the elements
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
	public static <T> void insertionSort(ArrayList<T> arrayToSort, int left, int right, Comparator<? super T> comparatorObj) {
		int i, j;
		T index;
		for(i = left+1; i <= right; i++){ //>=
			j = i;
			index = arrayToSort.get(i); // <-- item to be inserted
			
			// This part will shift the items until the insertion position is found
			while(j > 0 && comparatorObj.compare(arrayToSort.get(j - 1), index) > 0){ //>=
				arrayToSort.set(j, arrayToSort.get(j - 1));
				arrayToSort.set(j-1, index); //added this line 
				j--;
			}
			arrayToSort.set(j, index); // insert value of index into index j
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
	 * different pivot selection strategies.) 
	 * You will perform experiments to determine which pivot strategy works best (see the Analysis Document).
	 * Your quicksort may also switch to insertion sort on some small threshold if you wish.
	 * 
	 * 1: select an item in the array to be the pivot 
	 * 2: partition the array so that all items less than the pivot are to the left of the pivot, and all
	 * the items greater than the pivot are to the right. 
	 * 3: take the left half and go back to step 1 
	 * 4: take the right half and go back to step 1
	 * 
	 * This is the header method
	 */
	public static <T> void quicksort(ArrayList<T> arrayToSort, Comparator<? super T> comp) {
		int sizeOfArray = arrayToSort.size()-1;
		setThreshold(10);
		quickSortRecursive(arrayToSort, 0, sizeOfArray, comp);
	}
	
	/**
	 * quicksort recursive method 
	 * 
	 * @param arrayToSort - the array to sort
	 * @param left - left side of array
	 * @param right - right side of array (the end)
	 * @param comp - generic comparator to compare the elements
	 */
	public static <T> void quickSortRecursive(ArrayList<T> arrayToSort, int left, int right, Comparator<? super T> comp){  
		int size = right-left+1;
		int threshold = getThreshold();
		if(left + threshold > right){
			insertionSort(arrayToSort, left, right, comp);
			return; // End. Everything is sorted.
		}
		else{
			
			T thePivot = pivotValue(arrayToSort, getPivotStyle(), comp); // the value of the very end element to compare with, consider hard-coding this value and changing for exerimentation
			//System.out.println(pivot);
			int location = arrayToSort.indexOf(thePivot);
			swapValues(arrayToSort, location, size-1);
			
			//T pivot = arrayToSort.get(right); //consider making the index of
			//int pivotLocation = partitionArrays(arrayToSort, left, right, pivot, comp);
			int leftSide; //= left;
			int rightSide; //= right-1;
			
			//System.out.println(location);
			
			for(leftSide = left, rightSide = right -1;;) { //while(true)
				//System.out.println(leftSide);
				//System.out.println(pivot);
				//System.out.println(arrayToSort.get(arrayToSort.indexOf(pivot)));
				
				while(leftSide < right-1 && comp.compare(arrayToSort.get(++leftSide), thePivot) < 0) {;}
				
				while(rightSide > left && comp.compare(thePivot, arrayToSort.get(--rightSide)) < 0){;}
				
				if(leftSide >= rightSide){
					break;
				}
				//else
				//{
					swapValues(arrayToSort, leftSide, rightSide-1);
				//}
			//}
			}
			swapValues(arrayToSort, leftSide, right-1); //right-1
			quickSortRecursive(arrayToSort, left, leftSide-1,  comp);
			quickSortRecursive(arrayToSort, leftSide+1, right, comp);
		}
	}

	/**
	 * This takes in a pivot point and partitions array with that value.
	 * @param pivot
	 */
//	public static <T> int partitionArrays(ArrayList<T> arrayToSort, int left, int right, T pivot, Comparator<? super T> comp) {
//		
//	
//	return leftSide;
//}
	
	/**
	 * Will swap 2 values with each other
	 * @param value1 - the left value
	 * @param value2 - the right value
	 */
	public static <T> void swapValues(ArrayList<T> arrayToSort, int index1, int index2){
		T tempArraySwap2 = arrayToSort.get(index1);// replace element of arrayToSort index1, with element of arrayToSort index2.
		//System.out.println(index1 + " " + index2);
		arrayToSort.set(index1, arrayToSort.get(index2));
		arrayToSort.set(index2, tempArraySwap2);
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * ascending order.
	 * 
	 * @return an ArrayList of integers 1 to size of ascending order.
	 */
	public static ArrayList<Integer> generateBestCase(int size) {
		ArrayList<Integer> arrayListBest = new ArrayList<Integer>();
		// Add integers 1 to size in ascending order.
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
		ArrayList<Integer> arrayListAverage = new ArrayList<Integer>();
		// Add integers 1 to size in ascending order.
		for(int i = 1; i <= size; i++){
			arrayListAverage.add(i);
		}
		Collections.shuffle(arrayListAverage); // Call shuffle on the ArrayList to mix up the integers that are sorted.
		return arrayListAverage;
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * descending order.
	 * 
	 * @return an ArrayList of integers 1 to size in descending order.
	 */
	public static ArrayList<Integer> generateWorstCase(int size) {
		ArrayList<Integer> arrayListWorst = new ArrayList<Integer>();
		// Add integers 1 to size in descending order.
		for(int i = 1; size >= i; size--){
			arrayListWorst.add(size);
		}
		return arrayListWorst;
	}
	
	public static <T> T pivotValue(ArrayList<T> array, int style, Comparator<? super T> comp) throws IllegalArgumentException {
		
		if(style == 0) {
			return array.get(array.size() / 2);
		}
		
		else if(style == 1) {
			ArrayList<T> teenyList = new ArrayList<T>();
			teenyList.add(array.get(0));
			teenyList.add(array.get(array.size()/2));
			teenyList.add(array.get(array.size()-1));
			SortUtil.insertionSort(teenyList, 0, teenyList.size()-1, comp);
			return teenyList.get(1); //the middle value of the sorted list of components used to calculate pivot value
		}
		
		else if(style == 2) {
			Random rand = new Random();
			
			ArrayList<T> teenyList = new ArrayList<T>();
			teenyList.add(array.get(rand.nextInt(array.size()-1)));
			teenyList.add(array.get(rand.nextInt(array.size()-1)));
			teenyList.add(array.get(rand.nextInt(array.size()-1)));
			SortUtil.insertionSort(teenyList, 0, teenyList.size()-1, comp);
			return teenyList.get(1); //the middle value of the sorted list of components used to calculate pivot value
		}
		
		else {
			throw new IllegalArgumentException();
		}
	}
}
