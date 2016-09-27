package application;

import java.util.Scanner;

public class Heads_and_Tails {
	public static void main(String[] args) {
		Heads_and_Tails game = new Heads_and_Tails();
		game.coinFlip();
	}

	public void coinFlip() {
		try (Scanner scanner = new Scanner(System.in)) {
			while (scanner.hasNextLine()) {
				String input = scanner.nextLine();
				String result = input.toUpperCase().replaceAll("[^HT]", "");
				char c = longestSubsequence(result);
				switch (c) {
				case 'H':
					System.out.println("H wins!");
					break;
				case 'T':
					System.out.println("T wins!");
					break;
				case 'D':
					System.out.println("Draw!");
					break;
				}
			}
		}
	}

	static char longestSubsequence(String str) {
		char c = 0;
		int left = 0, curLength = 0, hLength = 0, tLength = 0, len = str.length();
		for (int i = 0; i < len; i++) {
			if (i + 1 == len || str.charAt(i) != str.charAt(i + 1)) {
				c = str.charAt(i);
				curLength = i + 1 - left;
				left = i + 1;

				if (c == 'H') {
					if (hLength < curLength) {
						hLength = curLength;
					}
				} else if (c == 'T') {
					if (tLength < curLength) {
						tLength = curLength;
					}
				}
			}
		}
		if (hLength == tLength) {
			c = 'D';
		} else if (hLength > tLength) {
			c = 'H';
		} else {
			c = 'T';
		}
		return c;
	}
}
