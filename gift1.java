/* Completed 7/7/16. */

/*
ID: michael276
LANG: JAVA
TASK: gift1
*/

// LESSONS LEARNED: The for-each loop is very powerful. Use objects and methods to streamline and organize code.

import java.io.*;
import java.util.*;

class Friend {
	String name;	// name of friend
	int accti;		// initial account
	int acctf;		// final account

	Friend(String x) {
		name = x;
	}

	String getName() {
		return this.name;
	}

	int getAccntInitial() {
		return this.accti;
	}

	int getAccntFinal() {
		return this.acctf;
	}

	void calcChange(int amnt1, int amnt2) {
		if (amnt2 == 0) {
			return;
		}
		this.accti -= amnt1 - (amnt1 % amnt2);
	} 

	void gainGift(int amnt1, int amnt2) {
		if (amnt2 == 0) {
			return;
		}
		else {
			this.acctf += amnt1 / amnt2;
		}
	} 
}

class gift1 {
	public static void main(String args[]) throws IOException {
		int i, j, amnt1, amnt2;
		String a;
		ArrayList<Friend> listNames = new ArrayList<Friend>();

		Scanner in = new Scanner(new File("gift1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		int numFriends = in.nextInt();		// number of total friends (NP)
		in.nextLine();

		for (i = 0; i < numFriends; i++) {
			// create new Friend object w/ NAME of friend
			String name = in.nextLine();
			// reading 2 ... NP + 1 lines of input	
			listNames.add(new Friend(name));									
		} 

		for (j = 0; j < numFriends; j++) {
			String name1 = in.nextLine();
		
			amnt1 = in.nextInt();
			amnt2 = in.nextInt();

			in.nextLine();

			for (Friend f : listNames) {
			    if (f.getName().equals(name1)) {
			    	f.calcChange(amnt1, amnt2);

				}
			}
	    	for (i = 1; i <= amnt2; i++) {	// how many inputs? amnt2 inputs
				a = in.nextLine();	// changes w/ each cycle
				for (Friend g : listNames) {
					if (g.getName().equals(a)) {
						g.gainGift(amnt1, amnt2); // add that amnt to each of them
					}
				}
			}
		}

		for (Friend f : listNames) {
			out.println(f.getName() + " " + (f.getAccntInitial() + f.getAccntFinal()));
		}

		out.close();

	}
}