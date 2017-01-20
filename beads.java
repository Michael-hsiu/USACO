/*
ID: michael276
LANG: JAVA
TASK: beads
*/

import java.io.*;
import java.util.*;

class beads {
    public static void main(String args[]) throws IOException {

        Scanner in = new Scanner(new File("beads.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
        int N = in.nextInt();
        in.nextLine();

        ArrayList<Character> inputs = new ArrayList<Character>();

        String beadString = in.nextLine();
        for (int i = 0; i < beadString.length(); i++) {
            inputs.add(beadString.charAt(i));      // inputs contains each bead in char form
        }

        int[] counts = new int[N];  // Stores count1 and count2 for each break

        int count1;
        int count2;
        int count3;
        char standard;

        for (int i = 0; i < N; i++) {      // Loop for each break

            standard = inputs.get(i);     // Get char to right of break from 'inputs'

            int j = i + 1;
            int k = 0;
            int m = 0;
            int z = 0;

            count1 = 1;     // Initialize to 1 (since we start on 'i', or the 'standard')
            count2 = 0;     // Initialize to 0 (since we also start on the 'standard')
            count3 = 1;     // This count exists only for if ALL beads are 'w'

            if (j < N) {      // index is valid
                if (standard == 'w') {                  // If we start on a 'w', make next 'r' or 'b' the standard

                    // Start at m = j = i + 1, one after 'i'
                    for (m = j; m < N; m++) {           // Loop forward until end
                        count3++;
                        if (inputs.get(m) != 'w') {     // Find the first non-'w'
                            standard = inputs.get(m);   // Set standard to that
                            break;                      // Only do this once
                        }
                    }
                }

                if (standard == 'w') {                  // If we went to end and didn't find it
                    for (z = 0; z < i; z++) {           // Loop from beginning until before 'i'
                        count3++;
                        if (inputs.get(z) != 'w') {     // Find the first non-'w'
                            standard = inputs.get(z);   // Set standard to that
                            break;                      // Only do this once
                        }
                    }
                }

                // Let's say we still didn't find a new standard
                if (standard == 'w') {
                    count1 = count3;
                }

                // Test for a match; standard must be 'b' or 'r' at this point unless all beads are 'w'
                for (k = j; k < N; k++) {   // Loop forward for beads that work; max for j is 'N' breaks
                    System.out.println(inputs.get(k) + " " + standard);
                    if (standard != 'w' && ( inputs.get(k) == standard || inputs.get(k) == 'w')) {
                        count1++;           // Forward counter increment
                    }
                    else {
                        break;
                    }
                }

                if (k == N) {       // If last bead was still valid, start at beginning again
                    for (k = 0; k < i; k++) {    // Loop from beginning in case we ended with a match at N - 1
                        if (standard != 'w' && (inputs.get(k) == standard || inputs.get(k) == 'w')) {
                            count1++;           // Forward counter increment
                        }
                        else {
                            break;
                        }
                    }
                }
            }

            else if (j == N) {       // The index exceeds string size; must be j = i + 1 = N
                // We have to go back to the beginning of the Input String.
                // Test for a match; standard must be 'b' or 'r' at this point unless all beads are 'w'
                if (standard == 'w') {     // if we start on a 'w' (includes 'i')
                    for (m = 0; m < i; m++) {       // Loop up until right before 'i'
                        count3++;
                        if (inputs.get(m) != 'w') {     // Find the first non-'w'
                            standard = inputs.get(m);   // Set standard to that
                            break;                      // Only do this once
                        }
                    }
                    // Let's say we tried and didn't find a new standard
                    if (standard == 'w') {
                        count1 = count3;
                    }
                }

                if (standard != 'w') {
                    for (m = 0; m < i; m++) {       // Only one case: j = N, so we start at the beginning
                        // Test for a match
                        if (inputs.get(m) == standard || inputs.get(m) == 'w') {
                            count1++;           // If match, forward counter increment
                        }
                        else {
                            break;
                        }
                    }
                }
            }




            // BEGIN THE BACKWARDS LOOPING

            if (count1 < N) {       // If all beads have been removed already, stop.
                j = i - 1;
                count3 = 1;

                if (j < 0) {       // Let's say that index is less than 0 (invalid) - only 1 such case
                    standard = inputs.get(N - 1);
                    if (standard == 'w') {         // Let's say we start on a 'w'
                        for (k = N - 2; k > i + count1 - 1; k--) {   // Loop backward from top until beginning
                            count3++;                   // In case all following beads are 'w'
                            if (inputs.get(k) != 'w') {     // Find the first non-'w'
                                standard = inputs.get(k);   // Set standard to that
                                break;
                            }
                        }
                    }

                    // Let's say we tried and didn't find a new standard
                    if (standard == 'w') {
                        count2 = count3;
                    }

                    // This is for if we DID find a new standard, or if standard was 'b' or 'r' originally.
                    else if (standard != 'w') {
                        for (k = N - 1; k > i + count1 - 1; k--) {      // Prevent intersection
                            if (inputs.get(k) == standard || inputs.get(k) == 'w') {  // If match
                                count2++;           // Backward counter increment
                            }
                            else {    // Match not met
                                break;
                            }
                        }
                    }
                }

                else {     // index is valid
                    standard = inputs.get(j);      // Set standard.

                    if (standard == 'w') {         // If we start on a 'w', start on j
                        for (k = j - 1; k != i + count1 - 1 && k > 0; k--) {   // Loop backward until end (prevent intersection)
                            count3++;
                            if (inputs.get(k) != 'w') {     // Find the first non-'w'
                                standard = inputs.get(z);   // Set standard to that
                                break;
                            }
                        }
                    }

                    if (standard == 'w') {     // If we went to intersection point and didn't find it
                        for (k = N - 1; k > i + count1 - 1; k--) {       // Loop backward from the top to intersection point
                            count3++;
                            if (inputs.get(k) != 'w') {     // Find the first non-'w'
                                standard = inputs.get(m);   // Set standard to that
                                break;
                            }
                        }
                    }

                    // Let's say we tried and didn't find a new standard
                    if (standard == 'w') {  // If we tried both and didn't find it
                        count2 = count3;    // Add to counter2 assuming all beads are 'w'
                    } else if (standard != 'w') {
                        for (k = j; k != i + count1 - 1 && k > -1; k--) {   // Loop backward for beads that work; min for j/k is -1
                            if (inputs.get(k) == standard || inputs.get(k) == 'w') {
                                count2++;           // Forward counter increment
                            } else {
                                break;
                            }
                        }
                    }

                    if (k == -2) {       // If last bead was still valid, start at N - 1 (the top)
                        for (k = N - 1; k > i + count1 - 1 && k > -2; k--) {    // Loop down from top
                            if (standard != 'w' && (inputs.get(k) == standard || inputs.get(k) == 'w')) {
                                count2++;           // Forward counter increment
                            } else {
                                break;
                            }
                        }
                    }
                }

            }
            counts[i] = count1 + count2;        // Add count sums to counts array

        }


        // Find max
        int max = counts[0];
        for (int x : counts) {
            if (x > max) {
                max = x;
            }
        }

        out.println(max);
        out.close();

    }
}