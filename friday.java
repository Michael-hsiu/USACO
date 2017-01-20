/* Completed 7/10/16. */

/*
ID: michael276
LANG: JAVA
TASK: friday
*/

// LESSONS LEARNED:
// Carefully check for differences in outputs  - if there are any changes between one set of outputs and the next,
// it may be based on user input.
// Check ALL VARIABLES that work as loop conditionals or are involved and make sure they're the right ones.
// Otherwise, logic might be right, but output may present something entirely different.
// Also, brute-forcing is sometimes the answer (check N)!
// Lastly, remember to check the print format.

import java.io.*;
import java.util.*;

// total # of days appears to be right . . . # per day of wk is wrong
class friday {
    public static void main(String args[]) throws IOException {
        int i, j;
        int[] counts = new int[7];


        Scanner in = new Scanner(new File("friday.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));

        int years = 1900 + in.nextInt() - 1;

        int n = 0;

        for (i = 1900; i <= years; i++) {  // loop for years


            for (j = 1; j <= 12; j++) {        // loop for months in each year

                int[] month = new int[60];

                int end = 31;

                if ((j == 4) || (j == 6) || (j == 9) || (j == 11)) {    // months with 30 days
                    end = 30;
                }

                if (j == 2) {          // Feb case
                    if ((i % 4) == 0) {        // could be a leap year
                        end = 29;
                        if ((i % 100) == 0) {   // is it a century year?
                            if ((i % 400) == 0) {       // if so, does it qualify to be a leap year?
                                end = 29;               // if yes, then it's a leap year!
                            } else {
                                end = 28;               // if no, it's not a leap year :O
                            }
                        }
                    }

                    else {
                        end = 28;                   // not a non-leap year
                    }
                }


                int k;
                for (k = 1; k <= end; k++) {            // end is based on month

                    month[(k - 1) + n] = k;              // k starts at 1 for convenience sake

                    if (k == 13) {
                        counts[(n + k) % 7] += 1;
                    }
                }


                n = ((end + n) % 7);     // makes sure next month begins at proper index
            }


        }

        // proper format of answer w/ sat first
        out.print(counts[6] + " ");  //sat
        out.print(counts[0] + " ");     // sun
        out.print(counts[1] + " ");      //mon
        out.print(counts[2] + " ");
        out.print(counts[3] + " ");
        out.print(counts[4] + " ");
        out.println(counts[5]);

       out.close();

    }
}