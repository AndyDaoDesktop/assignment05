package assignment05;

import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.ReverbType;

public class SortUtilTiming {
	
	private static Random rand = new Random();
	
	private static ArrayList<ArrayList<Integer>> masterSortedSet = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<ArrayList<Integer>> masterReverseSet = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<ArrayList<Integer>> masterRandomSet = new ArrayList<ArrayList<Integer>>();
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		long startTime, midTime, stopTime;
		
		
		int problemSize;
		
		for(int pivot = 0; pivot < 3; pivot++) {		//thresh when doing merge test
			for (problemSize = 100; problemSize <= 1000; problemSize += 100)		//problem size loop	//problemSize = (int) Math.pow(2.0, 12.0); problemSize <= Math.pow(2.0, 22.0); problemSize *= 2
			{
				//master lists
				//masterSortedSet.add(SortUtil.generateBestCase(problemSize));		//will add a new list of the desired form at each iteration of the problem size loop
				//masterReverseSet.add(SortUtil.generateWorstCase(problemSize));		//the last most contained list is the current problem size list
				masterRandomSet.add(SortUtil.generateAverageCase(problemSize));



				for(int i = 0; i < 1; i++)
				{
					//options here for different pivot methods
					//other options for different experiment types as well.
					//quick sort pivot selection SortUtil.setPivotType(pivot);

					// First, spin computing stuff until one second has gone by.
					// This allows this thread to stabilize.
					startTime = System.nanoTime();
					while(System.nanoTime() - startTime < 1000000000) { // empty block
					}

					// Now, run the test.
					long timesToLoop = 10;

//					ArrayList<Integer> listS1 = new ArrayList<Integer>();
//					ArrayList<Integer> listD1 = new ArrayList<Integer>();
					ArrayList<Integer> listR1 = new ArrayList<Integer>();




//					listS1 = masterSortedSet.get(masterSortedSet.size()-1);	//the last most item in the list set
//					listD1 = masterReverseSet.get(masterSortedSet.size()-1);
					listR1 = masterRandomSet.get(masterRandomSet.size()-1);

					//threshold selection for merge timing
//					if(thresh == 0)
//						SortUtil.setThreshold(1); // First threshold value, full mergesort
//					else if(thresh == 1)
//						SortUtil.setThreshold(10); // Second threshold value
//					else if(thresh == 2)
//						SortUtil.setThreshold(100); // Third threshold
//					else if(thresh == 3)  
//						SortUtil.setThreshold(listR1.size()/6); // Fourth threshold
//					else if(thresh == 4)
//						SortUtil.setThreshold(listR1.size()/10); // Fifth threshold
					
					SortUtil.setPivot(0);
					
					SortUtilComparator<Integer> comp = new SortUtilComparator<Integer>();

					startTime = System.nanoTime();

					//type of list selected to be sorted dictates what type of mergesort we are doing
					for(int j = 0; j < timesToLoop; j++)	//the actions we are timing/interested
					{
						ArrayList<Integer> temp = listR1;
						//SortUtil.mergesort(temp, comp);
						SortUtil.quicksort(temp, comp);
					}

					midTime = System.nanoTime();

					for(int g = 0; g < timesToLoop; g++) {
						ArrayList<Integer> temp = listR1;
					}

					stopTime = System.nanoTime();

					// Compute the time, subtract the cost of running the loop
					// from the cost of running the loop and computing square roots.
					// Average it over the number of runs.
					double averageTime = ((midTime - startTime) - (stopTime - midTime)) / timesToLoop;

					System.out.println(problemSize + "\t" + averageTime + "\t" + averageTime/(problemSize * (Math.log(problemSize)/Math.log(2.0))) + "\t" + averageTime/problemSize + "\t" + averageTime/Math.pow(problemSize, 2) + "\t" + averageTime/(Math.log(problemSize)/Math.log(2)));	//prints out results, get threshold shows what value we had for our switching point
					//System.out.println(averageTime + "\t" + averageTime/p + "\t" + averageTime/Math.pow(p, 2) + "\t" + averageTime/(p*(Math.log(p)/Math.log(2))) + "\t" + averageTime/(Math.log(p)/Math.log(2)));

				}
				//not sure if necessary
				//masterSet.clear();
			}
			//System.out.println("-----------End of Threshold--------------");
			System.out.println("-----------End of Pivotsize" + pivot + " Test--------------");
		}
	}
}
