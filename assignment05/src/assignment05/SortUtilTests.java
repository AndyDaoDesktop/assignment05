package assignment05;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Test Cases for SortUtil
 * 
 * @author Andy Dao and Jonathan Boyle
 */
public class SortUtilTests {
	SortUtilComparator<Integer> compInteger; // Comparator obj for Integers
	SortUtilComparator<Character> compCharacter; // Comparator obj for Characters
	SortUtilComparator<String> compString; // Comparator obj for Strings
	
	ArrayList<Integer> integerArrayListToSort; // Integer ArrayList to sort
	ArrayList<Character> charArrayListToSort; // Character ArrayList to sort 
	ArrayList<String> stringArrayListToSort; // String ArrayList to sort
	
	ArrayList<Integer> expectedValuesArrayList; // The expected Integer ArrayList 
	ArrayList<Character> expectedCharArrayList; // The expected Character ArrayList
	
	Integer[] expectedBestCaseArray; // Will be used to check Best Case ordering (ascending order)
	ArrayList<Integer> expectedBestCaseList; 
	
	Integer[] expectedWorstCaseArray; // Will be used to check Worst Case ordering (random order of integers 1 to size)
	ArrayList<Integer> expectedWorstCaseList;
	
	Integer[] sortedArrayListOfSizeTen; // this sorted array (ascending order) will be used in the generate___CaseTest methods
	ArrayList<Integer> sortedArrayOfSizeTen; // the sorted ArrayList using the Integer array above
	
	String[] expectedStringArray; // The expected String ArrayList
	ArrayList<String> expectedStringArrayList;

	
	
	/**
	 * Initiate objects in this setup method / setup test fixtures
	 */
	@Before
	public void setUp() throws Exception {
		// Create 3 Comparator objects, 1 for Integers, 1 for Characters, 1 for Strings
		compInteger = new SortUtilComparator<>();
		compCharacter = new SortUtilComparator<>();
		compString = new SortUtilComparator<>();
		
// BestCaseExpected
		expectedBestCaseArray = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		expectedBestCaseList = new ArrayList<>(Arrays.asList(expectedBestCaseArray));
		
// WorstCaseExpected
		expectedWorstCaseArray = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		expectedWorstCaseList = new ArrayList<>(Arrays.asList(expectedWorstCaseArray));
		
// Sorted ArrayList
		sortedArrayListOfSizeTen = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		sortedArrayOfSizeTen = new ArrayList<>(Arrays.asList(sortedArrayListOfSizeTen));
		
		
// Add integer values to an ArrayList in non-sorted order
		integerArrayListToSort = new ArrayList<Integer>();
		integerArrayListToSort.add(51);
		integerArrayListToSort.add(43);
		integerArrayListToSort.add(45);
		integerArrayListToSort.add(19);
		integerArrayListToSort.add(39);
		integerArrayListToSort.add(40);
		integerArrayListToSort.add(20);
		integerArrayListToSort.add(46);
		integerArrayListToSort.add(18);
		integerArrayListToSort.add(40);
		// Expected integer ArrayList order
		expectedValuesArrayList = new ArrayList<Integer>();
		expectedValuesArrayList.add(18);
		expectedValuesArrayList.add(19);
		expectedValuesArrayList.add(20);
		expectedValuesArrayList.add(39);
		expectedValuesArrayList.add(40);
		expectedValuesArrayList.add(40);
		expectedValuesArrayList.add(43);
		expectedValuesArrayList.add(45);
		expectedValuesArrayList.add(46);
		expectedValuesArrayList.add(51);

// Add char values to an ArrayList in non-sorted order
		charArrayListToSort = new ArrayList<Character>();
		charArrayListToSort.add('A');
		charArrayListToSort.add('D');
		charArrayListToSort.add('B');
		charArrayListToSort.add('C');
		charArrayListToSort.add('E');
		// Expected char ArrayList order
		expectedCharArrayList = new ArrayList<Character>();
		expectedCharArrayList.add('A');
		expectedCharArrayList.add('B');
		expectedCharArrayList.add('C');
		expectedCharArrayList.add('D');
		expectedCharArrayList.add('E');
		
// Sort this String ArrayList
		stringArrayListToSort = new ArrayList<String>();
		stringArrayListToSort.add("DEF");
		stringArrayListToSort.add("GHI");
		stringArrayListToSort.add("ABC");
		stringArrayListToSort.add("JKL");
		// Sorted String array
		expectedStringArray = new String[]{"ABC", "DEF", "GHI", "JKL"};
		expectedStringArrayList = new ArrayList<>(Arrays.asList(expectedStringArray));
		
	}

	/**
	 * This section will test mergeSort with Integers, Characters, and Strings.
	 */
	@Test
	public void mergeSort_WithIntegerArray() {
		SortUtil.mergesort(integerArrayListToSort, compInteger);
		Assert.assertEquals(expectedValuesArrayList, integerArrayListToSort);
	}
	
	@Test
	public void mergeSort_WithChararacterArray() {
		SortUtil.mergesort(charArrayListToSort, compCharacter);
		Assert.assertEquals(expectedCharArrayList, charArrayListToSort);
	}
	
	@Test
	public void mergeSort_WithStringArray() {
		SortUtil.mergesort(stringArrayListToSort, compString);
		Assert.assertEquals(expectedStringArrayList, stringArrayListToSort);		
	}
	
	/**
	 * This section will test quickSort with Integers, Characters, and Strings.
	 */
	@Test
	public void quickSort_WithIntegerArray() {
		SortUtil.quicksort(integerArrayListToSort, compInteger);
		Assert.assertEquals(expectedValuesArrayList, integerArrayListToSort);
	}
	
	@Test
	public void quickSort_WithCharacterArray() {
		SortUtil.quicksort(charArrayListToSort, compCharacter);
		Assert.assertEquals(expectedCharArrayList, charArrayListToSort);
	}
	
	@Test
	public void quickSort_WithStringArray() {
		SortUtil.quicksort(stringArrayListToSort, compString);
		Assert.assertEquals(expectedStringArrayList, stringArrayListToSort);
	}
	
	/**
	 * These next tests will use merge sort on the 3 types of generated methods from Integers 1-10:
	 * BestCase generation
	 * AverageCase generation
	 * WorstCase generation
	 * 
	 * They will be compared to a already sorted Integer ArrayList of size 10 (ascending order).
	 */
	@Test
	public void mergeSort_BestCaseTest(){
		ArrayList<Integer> bestCaseTestArray = SortUtil.generateBestCase(10); // creates an ArrayList of size 10, will generate best case of Integers 1 - 10
		SortUtil.mergesort(bestCaseTestArray, compInteger); // use mergesort on that ArrayList
		Assert.assertEquals(sortedArrayOfSizeTen, bestCaseTestArray);
	}
	
	@Test
	public void mergeSort_AverageCaseTest(){
		ArrayList<Integer> averageCaseTestArray = SortUtil.generateAverageCase(10); // creates an ArrayList of size 10, will generate average case of Integers 1 - 10
		SortUtil.mergesort(averageCaseTestArray, compInteger); // use mergesort on that ArrayList
		Assert.assertEquals(sortedArrayOfSizeTen, averageCaseTestArray);
	}
	
	@Test
	public void mergeSort_WorstCaseTest() {
		ArrayList<Integer> worstCaseTestArray = SortUtil.generateAverageCase(10); // creates an ArrayList of size 10, will generate worst case of Integers 1 - 10
		SortUtil.mergesort(worstCaseTestArray, compInteger); // use mergesort on that ArrayList
		Assert.assertEquals(sortedArrayOfSizeTen, worstCaseTestArray);
	}
	
	/**
	 * This section of tests will be to prove that the ArrayLists will generate their type of case correctly.
	 */
	@Test
	public void generate_BestCase_Test() { // Will generate an Integer ArrayList: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
		Assert.assertEquals(expectedBestCaseList, SortUtil.generateBestCase(10));
	}
	
	@Test
	public void generate_AverageCase_Test() { // Will generate an Integer ArrayList: numbers 1-15, in random order.
		SortUtil.generateAverageCase(15);
		// Not too sure on how we will test a random order (we might not need this test)
	}
	
	@Test
	public void generate_WorstCase_Test() { // Will generate an Integer ArrayList: 10, 9, 8, 7, 6, 5, 4, 3, 2, 1
		Assert.assertEquals(expectedWorstCaseList, SortUtil.generateWorstCase(10));
	}
	
	/**
	 * Releases external resources that was done in the @before class
	 */
	@After
	public void tearDown() throws Exception {
		
	}
}