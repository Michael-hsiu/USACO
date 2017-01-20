/* namenum v1
 * 
 * DESCRIPTION:
 * This code passes 8 of the test cases for the USACO 'namenum' problem.
 * 
 * COMMENTARY: 
 * I believe that the reason for the failure of the remaining 2(?) test cases is memory management and efficiency.	
 * 
 * In order to deal with this problem, I plan to look at working code and hopefully learn about more efficient data structures (e.g. HashMaps, Collections) that I can construct and/or use.
 * 
 * I also hope to see other 'algorithmic' ways of going through this problem. Looking at my code, there seem to be far more 'for' and 'while' loops than are probably necessary.
 * 
 * While this approach may have worked for earlier problems, it was probably not efficient. I think that I've reached the limits of what one can do with looping and conditions only, 
 * so I hope that studying data structures further will allow me to make this and future programs more efficient.
 */

/*
ID: michael276
LANG: JAVA
TASK: namenum
*/

import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class namenum {

	// Possible names array.
	static String[] names;

    // Arrays for storing letter values.
    static String[] two = new String[]{"A", "B", "C"};
    static String[] three = new String[]{"D", "E", "F"};
    static String[] four = new String[]{"G", "H", "I"};
    static String[] five = new String[]{"J", "K", "L"};
    static String[] six = new String[]{"M", "N", "O"};
    static String[] seven = new String[]{"P", "R", "S"};
    static String[] eight = new String[]{"T", "U", "V"};
    static String[] nine = new String[]{"W", "X", "Y"};

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner in = new Scanner(new File("namenum.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));

        String input = in.next().trim();
		int capacity = input.length();
		names = new String[(int) Math.pow(3, capacity)];   // The # of possible names = 3 ** capacity
		long[] digits = getDigits(Long.valueOf(input));	// Gets digits of input and puts in array

		int tempLength = names.length;

		for (long x : digits) {
			String[] tone = getNumArray(x);
			populateNames(tempLength, tone, names.length);
			tempLength = tempLength * 1 / 3;
		}

		ArrayList<String> possibleNames = new ArrayList<String>();

		/** Parse code adopted from mkyong.com */
		BufferedReader br = null;
		FileReader fr = null;

		boolean atLeastOneAnswer = false;

		int i = 0;
		int j = 0;

		try {

			fr = new FileReader("dict.txt");
			br = new BufferedReader(fr);

			String sCurrentLine = br.readLine();

			// Go to the first match for the first letters between all possible and all generated names.
			while (sCurrentLine.charAt(0) != names[j].charAt(0)) {
				sCurrentLine = br.readLine();
			}

			// Cycle through appropriate region to find matches.
			while (j < names.length) {
				String possibleName = sCurrentLine;
				String name = names[j];

				while (possibleName.charAt(0) > name.charAt(0)) {
					j++;
					break;
				}
				while (possibleName.charAt(0) < name.charAt(0)) {
					sCurrentLine = br.readLine();
					break;
				} 

				if (possibleName.equals(name)) {
					atLeastOneAnswer = true;
					out.println(name);
					sCurrentLine = br.readLine();
					j++;
					continue;
				}

				char[] a = possibleName.toCharArray();
				char[] b = name.toCharArray();
				String minString = null;

				int min = Math.min(a.length, b.length);
				int max = Math.max(a.length, b.length);

				if (a.length > b.length) {
					minString = possibleName;
				} else if (a.length < b.length) {
					minString = name;
				}

				for (int m = 0; m < max; m++) {
					if (m >= min) {
						if (minString.equals(possibleName)) {
							j++;
							break;
						} else if (minString.equals(name)) {
							sCurrentLine = br.readLine();
							break;
						}
					}
					if (a[m] != b[m]) {
						if (a[m] < b[m]) {
							sCurrentLine = br.readLine();
							break;
						} else if (a[m] > b[m]) {
							j++;
							break;
						}
					}
				}
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

		if (!atLeastOneAnswer) {
			out.println("NONE");
		}

		out.close();
		

	}

	private static void populateNames(int tempLengths, String[] tone, int namesLength) {
		int tempLength = tempLengths;
		int tempTempLength = tempLength;
		int a = 0;
		int processCount = 0;

		while (processCount < namesLength) {
			int toneIndex = 0;

			while (toneIndex < 3) {
				if (processCount == tempTempLength) {
					tempTempLength += tempLength;
				}

				for (int j = a; j < tempLength / 3 + a; j++) {
					if (names[j] != null) {
						names[j] += tone[toneIndex];
					} else {
						names[j] = tone[toneIndex];
					}
					processCount++;
				}
				toneIndex++;
				a += tempLength * 1 / 3;
			}	
		}
	}

	private static long[] getDigits(long N) {
		int arraySize = 0;
		long copyN = N;
		while (copyN != 0) {
			copyN = copyN / 10;
			arraySize++;
		}
		long[] result = new long[arraySize];
		for (int i = arraySize - 1; i >= 0; i--) {
			result[i] = N % 10;
			N = N / 10;
		}
		return result;
	}

	private static String[] getNumArray(long N) {
		if (N == 2) {
			return two;
		} else if (N == 3) {
			return three;
		} else if (N == 4) {
			return four;
		} else if (N == 5) {
			return five;
		} else if (N == 6) {
			return six;
		} else if (N == 7) {
			return seven;
		} else if (N == 8) {
			return eight;
		} else {
			return nine;
		}
	}
}