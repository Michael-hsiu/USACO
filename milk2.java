/* Completed 7/21/16. */

/*
ID: michael276
LANG: JAVA
TASK: milk2
*/

import java.io.*;
import java.util.*;

public class milk2 {
    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));

        /* Get all inputs. */
        int N = Integer.parseInt(in.readLine());
        MilkList cow = new MilkList(N);  // Generate new MilkList object.

        /* Insert inputs into array-list. */
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            cow.getOne().add(i, Integer.parseInt(st.nextToken()));   // Add values.
            cow.getTwo().add(i, Integer.parseInt(st.nextToken()));   // Add values.
        }

       /* Sort inputs from smallest to largest by inputs1 array. */
       Collections.sort(cow.getOne());
       Collections.sort(cow.getTwo());

        /* Setup the initial values first. */
        cow.setCurrent_ans(cow.getTwo().get(0) - cow.getOne().get(0));    // Set initial answer.
        cow.setCurrent_max(cow.getTwo().get(0));  // Set initial upper bound.
        cow.setJ(0);    // Set initial 'j' value.

        cow.setCurrent_ans2(0);   // Set initial answer for no-milking.

        for (int i = 1; i < N; i++) {
            cow.overlapTest(i); // Run the test.
        }

        /* Print answer. */
       out.println(cow.getCurrent_ans() + " " + cow.getCurrent_ans2());

       out.close();
    }
}

// Remember to properly set bounds.
class MilkList {
    private int current_ans;   // Answer for continuous milking.
    private int current_ans2;  // Answer for no milking.
    private int current_max;   // Upper bound for detecting overlap. Shared for both problems.

    private int j;             // Upper bound index per overlapped set.
    private ArrayList<Integer> one;     // For input values (start times).
    private ArrayList<Integer> two;      // For input values (end times).

    MilkList(int N) {
          /* Initialize arraylists for inputs. */
        one = new ArrayList<Integer>(N);
        two = new ArrayList<Integer>(N);

    }

    void overlapTest(int i) {

        /* Test if there's an overlap. */
        // There is an overlap.
        if (one.get(i) <= current_max) {
            if ((two.get(i) - one.get(j)) > current_ans) {
                this.current_ans = two.get(i) - one.get(j);
            }
        }
        // There isn't an overlap.
        else {
            j = i;  // Set the 'new segment' index.
            if ((two.get(i) - one.get(i)) > current_ans) {
                this.current_ans = two.get(i) - one.get(i);
            }

            // No-overlap test.
            if (one.get(i) - current_max > current_ans2) {
                this.current_ans2 = one.get(i) - current_max;
            }
        }

        this.current_max = two.get(i);
    }

    /* Getters and setters. */

    public int getCurrent_ans2() {
        return current_ans2;
    }

    public void setCurrent_ans2(int current_ans2) {
        this.current_ans2 = current_ans2;
    }

    public int getCurrent_ans() {
        return current_ans;
    }

    public int getCurrent_max() {
        return current_max;
    }

    public int getJ() {
        return j;
    }

    public void setCurrent_ans(int current_ans) {
        this.current_ans = current_ans;
    }

    public void setCurrent_max(int current_max) {
        this.current_max = current_max;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public ArrayList<Integer> getOne() {
        return this.one;
    }

    public ArrayList<Integer> getTwo() {
        return this.two;
    }

    public void setOne(ArrayList<Integer> one) {
        this.one = one;
    }

    public void setTwo(ArrayList<Integer> two) {
        this.two = two;
    }
}


