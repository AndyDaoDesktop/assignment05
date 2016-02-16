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
	
	
	/**
	 * Initiate objects in this setup method / setup test fixtures
	 */
	@Before
	public void setUp() throws Exception {
		compInteger = new SortUtilComparator<>();
		compCharacter = new SortUtilComparator<>();
		
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

	@Test
	public void mergeSort_Test1() {
		SortUtil.mergesort(integerArrayList, compInteger);
		Assert.assertEquals(expectedValuesArrayList, integerArrayList);
	}
	
	@Test
	public void mergeSort_Test2() {
		SortUtil.mergesort(charArrayList, compCharacter);
		Assert.assertEquals(expectedCharArrayList, charArrayList);
	}
	
	@Test
	public void quickSort_Test1() {
		
	}
	
	@Test
	public void insertion_Sort() {
		
	}
	
	@Test
	public void generate_BestCase_Test1() {
		
	}
	
	@Test
	public void generate_AverageCase_Test1() {
		System.out.println(SortUtil.generateAverageCase(15).toString());
	}
	
	@Test
	public void generate_WorstCase_Test1() {
		//System.out.println(SortUtil.generateWorstCase(50).toString());
	}
	
	/**
	 * Releases external resources that was done in the @before class
	 */
	@After
	public void tearDown() throws Exception {
	}
}