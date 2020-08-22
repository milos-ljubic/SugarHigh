
import java.util.Arrays;

public class SugarHigh {

	public static void main(String[] args) {

//		// example from assignment
//		int[] candies = { 33, 20, 12, 19, 29 };
//		int threshold = 33;
//		sugarHigh(candies, threshold); // [2, 3]

//		// example from assignment 
//		int[] candies = { 62, 67, 100 }; 
//		int threshold = 8;
//		sugarHigh(candies, threshold); // []

//		// example from assignment
//		int[] candies = { 16, 39, 67, 16, 38, 71 };
//		int threshold = 17;
//		sugarHigh(candies, threshold); // [0]

//		// example from assignment
//		int[] candies = { 16, 3, 14, 17, 11 };
//		int threshold = 99;
//		sugarHigh(candies, threshold); // [0, 1, 2, 3, 4]

//		// happy path
//		int[] candies = { 9, 30, 12, 19, 56, 85, 29, 12 };
//		int threshold = 83;
//		sugarHigh(candies, threshold); // [0, 2, 3, 6, 7]

// 		corner case - two candies with same amount of sugar - one eaten
		int[] candies = { 9, 30, 11, 19, 29, 85, 29, 12 };
		int threshold = 83;
		sugarHigh(candies, threshold); // [0, 2, 3, 4, 7]

//		// corner case - five candies with same amount of sugar - three eaten
//		int[] candies = { 19, 33, 19, 19, 27, 85, 19, 19 };
//		int threshold = 63;
//		sugarHigh(candies, threshold); // [0, 2, 3]

//		// corner case - empty array
//		int[] candies = {};
//		int threshold = 63;
//		sugarHigh(candies, threshold); // []

//		// error handling - invalid length of array
//		int[] candies = new int [(int) (Math.pow(10, 5) + 1)];
//		int threshold = 63;
//		sugarHigh(candies, threshold); // Illegal value for a length for an array of candies. Allowed interval is [0, 10^5].

//		// error handling - threshold == 0
//		int[] candies = { 19, 33, 19, 19, 27, 85, 19, 19 };
//		int threshold = 0;
//		sugarHigh(candies, threshold); // Illegal value for the threshold. Allowed interval is [1, 10^9].

//		// error handling - value for sugar == -1
//		int[] candies = { 19, 33, 19, -1, 27, 85, 19, 19 };
//		int threshold = 55;
//		sugarHigh(candies, threshold); // Illegal value for sugar per candy. Allowed interval is [0, 100].		

	}

	static void sugarHigh(int[] candies, int threshold) {

		isInputAllowed(candies, threshold);

		// copy of array of candies
		int[] unsortedCandies = Arrays.copyOf(candies, candies.length);

		// count a max numbers of candies we can eat
		int numberOfEatenCandies = countMaxCandies(candies, threshold);

		// output array
		int[] output = makeArrayOfIndexes(candies, unsortedCandies, numberOfEatenCandies);

		// print
		printArray(output);

	}

	static void isInputAllowed(int[] candies, int allowedSugar) {

		// the invalid number for threshold
		if (allowedSugar < 1 || allowedSugar > Math.pow(10, 9))
			throw new IllegalArgumentException("Illegal value for the threshold. Allowed interval is [1, 10^9].");

		// the invalid length for an array of candies
		if (candies.length < 0 || candies.length > Math.pow(10, 5))
			throw new IllegalArgumentException(
					"Illegal value for a length for an array of candies. Allowed interval is [0, 10^5].");

		// the invalid value for sugar per candy
		for (int i = 0; i < candies.length; i++) {
			int tempSugar = candies[i];

			if (tempSugar < 0 || tempSugar > 100)
				throw new IllegalArgumentException("Illegal value for sugar per candy. Allowed interval is [0, 100].");
		}

	}

	// method for counting the max number of candies we can eat
	static int countMaxCandies(int[] candies, int allowedSugar) {

		Arrays.sort(candies);
		int sum = 0;
		int count = 0;
		boolean oneMore = false;

		while (!oneMore && count < candies.length) {
			if (sum + candies[count] <= allowedSugar) {
				sum += candies[count];
				count++;
			} else {
				oneMore = true;
			}
		}

		return count;
	}

	// method for making subarray of candies indexes we ate
	static int[] makeArrayOfIndexes(int[] sortedArray, int[] unsortedArray, int numberOfEatenCandies) {

		int[] arrayWithIndexes = new int[numberOfEatenCandies];

		for (int i = 0; i < numberOfEatenCandies; i++) {
			boolean found = false;
			int j = 0;

			while (!found && j < unsortedArray.length)
				if (sortedArray[i] == unsortedArray[j]) {
					arrayWithIndexes[i] = j;
					unsortedArray[j] = -1;
					found = true;
				} else
					j++;
		}

		return arrayWithIndexes;
	}

	// method for printing array
	static void printArray(int[] arrayWithIndexes) {
		Arrays.sort(arrayWithIndexes);
		String output = Arrays.toString(arrayWithIndexes);
		System.out.println(output);
	}

}
