package assignment05;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Andy Dao
 */
public class SortUtilTests {
	SortUtilComparator<Integer> compInteger;
	SortUtilComparator<Character> compCharacter;
	
	ArrayList<Integer> integerArrayList;
	ArrayList<Character> charArrayList;
	
	ArrayList<Integer> expectedValuesArrayList;
	ArrayList<Character> expectedCharArrayList;
	
	ArrayList<Integer> expectedBestCaseList;
	Integer[] expectedBestCaseArray;
	
	Integer[] expectedWorstCaseArray;
	ArrayList<Integer> expectedWorstCaseList;
	
	Integer[] sortedArrayListOfSizeTen; // this sorted array (ascending order) will be used in the generate___CaseTest methods
	ArrayList sortedArrayOfSizeTen; // the sorted ArrayList using the Integer array above
	/**
	 * Initiate objects in this setup method / setup test fixtures
	 */
	@Before
	public void setUp() throws Exception {
		// 2 Comparator objects, 1 for Integers, 1 for Characters
		compInteger = new SortUtilComparator<>();
		compCharacter = new SortUtilComparator<>();
		
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
		integerArrayList = new ArrayList<Integer>();
		integerArrayList.add(1);
		integerArrayList.add(5);
		integerArrayList.add(7);
		integerArrayList.add(2);
		integerArrayList.add(10);
		
		// Expected integer ArrayList order
		expectedValuesArrayList = new ArrayList<Integer>();
		expectedValuesArrayList.add(1);
		expectedValuesArrayList.add(2);
		expectedValuesArrayList.add(5);
		expectedValuesArrayList.add(7);
		expectedValuesArrayList.add(10);
		
		// Add char values to an ArrayList in non-sorted order
		charArrayList = new ArrayList<Character>();
		charArrayList.add('A');
		charArrayList.add('D');
		charArrayList.add('B');
		charArrayList.add('C');
		charArrayList.add('E');
		
		// Expected char ArrayList order
		expectedCharArrayList = new ArrayList<Character>();
		expectedCharArrayList.add('A');
		expectedCharArrayList.add('B');
		expectedCharArrayList.add('C');
		expectedCharArrayList.add('D');
		expectedCharArrayList.add('E');
	}

	/**
	 * This section will test mergeSort with Integers and Characters.
	 */
	@Test
	public void mergeSort_WithIntegerArray() {
		SortUtil.mergesort(integerArrayList, compInteger);
		Assert.assertEquals(expectedValuesArrayList, integerArrayList);
	}
	
	@Test
	public void mergeSort_WithChararacterArray() {
		SortUtil.mergesort(charArrayList, compCharacter);
		Assert.assertEquals(expectedCharArrayList, charArrayList);
	}
	
	/**
	 * This section will test quickSort with Integers and Characters.
	 */
	@Test
	public void quickSort_WithIntegerArray() {
		SortUtil.quicksort(integerArrayList, compInteger);
		Assert.assertEquals(expectedValuesArrayList, integerArrayList);
	}
	
	@Test
	public void quickSort_WithCharacterArray() {
		SortUtil.quicksort(charArrayList, compCharacter);
		Assert.assertEquals(expectedValuesArrayList, charArrayList);
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