/* Completed 8/6/16. */
/*
ID: michael276
LANG: JAVA
TASK: transform
*/

// Lessons Learned: Learned how to use static methods; searched up simpler method / algorithm on Github, learned how to
// be accountable for multiple validity tests (#6 and 7 appearing in multiple places).
// Should look more into static methods/classes, using fewer variables to not get them mixed up.
// More concise code, better, more overreaching algorithms. Multiple methods in one step, rather than split into loops.
// Test method on multiple cases; if it works, assume so and apply it with other methods (ex. cw(cw(cw(reflect(input)))))
// Better plans = less time spent on code.

import java.io.*;
import java.util.*;

class SquareStorage {

    /* Transformation methods. */

    static boolean matchTest(int[][] test, int[][] finals, int i) {
        // Tests for match between transformed and final state.
        // If true, return the current transformation #.
        for (int e = 0; e < test.length; e++) {
            for (int r = 0; r < test.length; r++) {
                if (test[e][r] != finals[e][r]) {
                    return false;
                }
            }
        }
        return true;
    }

    static int[][] clockwiseRotation(int[][] initial, int N) {
        // Rotates the char array 90 degrees CW.
        int[][] rotatedArray = new int[N][N];

        int lim;
        if (N % 2 == 1) {
            lim = (N - 1) / 2;
            rotatedArray[N / 2][N / 2] = initial[N / 2][N / 2]; // Middle element stays the same.
        }
        else {
            lim = N / 2;
        }

        // Var val changes, limit also changes - top and bottom come in for each square.
        for (int i = 1; i <= lim; i++) {
            int x = 1 + (i - 1);
            int r = i - 1;
            int z = i - 1;
            int k = N - 1 - (i - 1);
            int a = k - 1;

            while (r <= k) {     // Top and bottom rows
                rotatedArray[r][k] = initial[z][r]; // Top row rotation
                rotatedArray[r][z] = initial[k][r]; // Bottom row rotation
                r++;    // Moving down the rows w/ cap of N - 1
            }

            while (x <= k - 1) {     // Left and right rows
                rotatedArray[z][a] = initial[x][z];     // Left row rotation
                rotatedArray[k][a] = initial[x][k]; // Right row rotation
                x++;
                a--;
            }
        }

        return rotatedArray;
    }

    static int[][] reflection(int[][] initial, int N) {
        // Reflects the array over vertical axis (horizontally).

        int[][] reflectedArray = new int[N][N];

        int a = 0;
        while (a < N) {
            int y = 0;
            int b = N - 1;

            while (y < N) {
                reflectedArray[a][y] = initial[a][b];
                y++;
                b--;
            }

            a++;
        }
        return reflectedArray;
    }
}

public class transform {
    public static void main(String args[]) throws IOException {
        Scanner in = new Scanner(new File("transform.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));

        int i, j;
        /* Get and store N. */
        int N = in.nextInt();
        in.nextLine();

        System.out.println("This is N: " + N);

        /* Get and store INITIAL ARRAY. */
        char[][] initial = new char[N][N];
        for (i = 0; i < N; i++) {
            String a = in.nextLine();
            for (j = 0; j < N; j++) {
                initial[i][j] = a.charAt(j);
            }
        }

        /* Check INITIAL ARRAY is correct. */
        System.out.println("This is the INITIAL ARRAY: ");
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                System.out.print(initial[i][j]);
            }
            System.out.println();
        }

        /* Get and store FINAL ARRAY. */
        char[][] finals = new char[N][N];
        for (i = 0; i < N; i++) {
            String b = in.nextLine();
            for (j = 0; j < N; j++) {
                finals[i][j] = b.charAt(j);
            }
        }

        /* Check FINAL ARRAY is correct. */
        System.out.println("This is the FINAL ARRAY: ");
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                System.out.print(finals[i][j]);
            }
            System.out.println();
        }

        /* Checks for no changes - may be a #6. (NoChangeTest). */
        /* Also get count to make sure there are no missing or extra '@' or '-' chars. (ValidityTest #1). */
        int total = 0;  // Total matching chars between initial and final.

        int atCounti = 0;
        int dashCounti = 0;
        int atCountf = 0;
        int dashCountf = 0;

        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                if (initial[i][j] == '@') {
                    atCounti += 1;
                }
                if (initial[i][j] == '-') {
                    dashCounti += 1;
                }
                if (finals[i][j] == '@') {
                    atCountf += 1;
                }
                if (finals[i][j] == '-') {
                    dashCountf += 1;
                }
                if (initial[i][j] == finals[i][j]) {
                    total += 1;
                }
            }
        }

        if ((atCounti != atCountf) || (dashCounti != dashCountf)) {
            System.out.println("Transformation was not valid!");
            System.out.println("atCounti: " + atCounti);
            System.out.println("dashCounti: " + dashCounti);
            System.out.println("atCountf: " + atCountf);
            System.out.println("dashCountf: " + dashCountf);

            out.println(7);   // Edit here.
            out.close();
            System.exit(0);
        }

        /* Replace '@' with '1' and '-' with '0' for convenience. */
        int[][] initial2 = new int[N][N];
        int[][] finals2 = new int[N][N];

        System.out.println();
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                if (initial[i][j] == '@') {
                    initial2[i][j] = 1;
                }
                if (initial[i][j] == '-') {
                    initial2[i][j] = 0;
                }
                if (finals[i][j] == '@') {
                    finals2[i][j] = 1;
                }
                if (finals[i][j] == '-') {
                    finals2[i][j] = 0;
                }
            }
        }

        /* Print to make sure replacements were correct. */
        System.out.println();
        System.out.println("This is the INITIAL ARRAY with replacements: ");
        for (int m = 0; m < N; m++) {
            for (int n = 0; n < N; n++) {
                System.out.print(initial2[m][n]);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("This is the FINALS ARRAY with replacements: ");
        for (int m = 0; m < N; m++) {
            for (int n = 0; n < N; n++) {
                System.out.print(finals2[m][n]);
            }
            System.out.println();
        }


        /* Do the 90 degree CW test UP TO 3 TIMES. */
        // Static method

        int[][] a = new int[N][N];

        System.out.println("This is array a: ");
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                a[x][y] = initial2[x][y];
                System.out.print(a[x][y]);
            }
            System.out.println();
        }

        i = 1;  // Denotes transformation number for #1-5.
        while (i <= 3) {
            System.out.println(i);
            int[][] b = SquareStorage.clockwiseRotation(a, N);

            // Begin print test.
            System.out.println("This is i#1: " + i);
            System.out.println("This is the ROTATED ARRAY #" + i + " : ");
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    System.out.print(b[x][y]);
                }
                System.out.println();
            }
            System.out.println();

            // Begin match test.
            System.out.println("This is i#2: " + i);
            System.out.println(SquareStorage.matchTest(b, finals2, i));
            System.out.println("This is i#3: " + i);
            if (SquareStorage.matchTest(b, finals2, i) == true) {
                out.println(i); // Edit here.
                out.close();
                System.exit(0);
            }
            else {
                System.out.println("This is the new a array: ");
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {
                        a[x][y] = b[x][y];
                        System.out.print(a[x][y]);
                    }
                    System.out.println();
                }
                i++;    // Advance the counter.
            }
        }

        // i should now be 4.

        /* Do a reflection. */
        System.out.println("Reflection i: " + i);

        int[][] c = SquareStorage.reflection(initial2, N);

        System.out.println("This is the INITIAL2 ARRAY: ");
        for (int m = 0; m < N; m++) {
            for (int n = 0; n < N; n++) {
                System.out.print(initial2[m][n]);
            }
            System.out.println();
        }

        System.out.println("This is the REFLECTED ARRAY: ");
        for (int m = 0; m < N; m++) {
            for (int n = 0; n < N; n++) {
                System.out.print(c[m][n]);
            }
            System.out.println();
        }

        if (SquareStorage.matchTest(c, finals2, i) == true) {
            out.println(i); // Edit here.
            out.close();
            System.exit(0);
        }
        else {
            i++;    // Advance the counter.
        }

        // i should now be 5.

        /* Combination. */
        while (i <= 7) {
            System.out.println(i);
            int[][] d = SquareStorage.clockwiseRotation(c, N);

            // Begin print test.
            System.out.println("This is i#5: " + i);
            System.out.println("This is the REFLECTED + ROTATED ARRAY #" + i + " : ");
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    System.out.print(d[x][y]);
                }
                System.out.println();
            }
            System.out.println();

            // Begin match test.
            System.out.println("This is i#6: " + i);
            System.out.println(SquareStorage.matchTest(d, finals2, i));
            System.out.println("This is i#7: " + i);
            if (SquareStorage.matchTest(d, finals2, i) == true) {
                out.println(5); // Edit here.
                out.close();
                System.exit(0);
            }
            else {
                System.out.println("This is the new c array: ");
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {
                        c[x][y] = d[x][y];
                        System.out.print(c[x][y]);
                    }
                    System.out.println();
                }
                i++;    // Advance the counter.
            }
        }

        /* Back to no change. Options #1-5 are preferred to #6, no change. */
        if (total == (N * N)) {
            System.out.println("There was no change!");

            out.println(6);    // Edit here.
            out.close();
            System.exit(0);
        }

        /* If it wasn't #1-6, it has to be invalid. All roads lead here. (Validity Test #2). */
        out.println(7);    // Edit here.
        out.close();
        System.exit(0);
    }
}