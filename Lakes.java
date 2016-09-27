package application;

import java.util.Scanner;

public class Lakes {
	public static void main(String[] args) {
		Lakes lake = new Lakes();
		lake.Volume();

	}

	public void Volume() {
		try (Scanner scanner = new Scanner(System.in)) {
			while (scanner.hasNextLine()) {
				String lakeStructure = scanner.nextLine();
				int level = 0;
				double squares = 0;
				for (char c : lakeStructure.toCharArray()) {
					switch (c) {
					case 'd':
						if (level >= 0) {
							squares += level + 0.5;
						}
						level++;
						break;
					case 'h':
						if (level > 0) {
							squares += level;
						}
						break;
					case 'u':
						level--;
						if (level < 0)
							break;
						squares += level + 0.5;
						break;
					}
				}
				System.out.println("liters: " + (int) squares * 1000);
			}
		}
	}
}
