package bottles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameOfBottles {
	public static void main(String[] args) {
		GameOfBottles game = new GameOfBottles();
		game.parse();
	}

	String points = "ABCDEFGHI";

	public void parse() {
		try (BufferedReader br = new BufferedReader(
				new FileReader("C:\\Users\\Mary\\workspace\\APP2016\\src\\bottles\\Grid.txt"))) {
			int numberOfPoints = 0;
			String line = br.readLine();
			if (tryParseInt(line)) {
				numberOfPoints = Integer.parseInt(line);
			}
			Graph g = new Graph(numberOfPoints);
			List<Point> list = new ArrayList<>();

			for (int i = 0; i < numberOfPoints; i++) {
				line = br.readLine();
				if (line != null) {
					String[] parts = line.split(" ");
					double x = Double.parseDouble(parts[0]);
					double y = Double.parseDouble(parts[1]);
					Point p = new Point(x, y);
					System.out.println(points.charAt(i) + " = " + p.toString());
					g.setLabel(i, points.charAt(i));
					list.add(p);
				}

			}
			for (int i = 0; i < numberOfPoints; i++) {
				for (int j = numberOfPoints - 1; j > i; j--) {
					g.addEdge(i, j, getLength(list.get(i), list.get(j)));
					g.addEdge(j, i, getLength(list.get(i), list.get(j)));
				}
			}

			StringBuilder strBuilder = new StringBuilder();
			for (int i = 0; i < numberOfPoints; i++) {
				strBuilder.append(i);
			}
			String str = strBuilder.toString();
			List<Pair<String, Integer>> paths = new ArrayList<Pair<String, Integer>>();
			permutation(str);
			StringBuilder path = new StringBuilder();
			int sum = 0, min = Integer.MAX_VALUE;
			for (String s : permutations) {
				sum = 0;
				path.setLength(0);
				for (int i = 0; i < numberOfPoints - 1; i++) {
					sum += g.getWeight(s.charAt(i) - '0', s.charAt(i + 1) - '0');
					path.append(g.getLabel(s.charAt(i) - '0') + " -> ");

				}
				path.append(g.getLabel(s.charAt(numberOfPoints - 1) - '0'));
				if (sum <= min) {
					min = sum;
					paths.add(new Pair<String, Integer>(path.toString(), min));
				}
			}

			for (Pair<String, Integer> pair : paths) {
				if (pair.getRight().equals(min)) {
					System.out.println(pair.getLeft());
				}
			}
			System.out.println("Total distance: " + min);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}

	}

	boolean tryParseInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public int getLength(Point p1, Point p2) {
		double x = Math.abs(p2.getX() - p1.getX());
		double y = Math.abs(p2.getY() - p1.getY());
		return (int) (x + y);
	}

	public static void permutation(String str) {
		permutation("", str);
	}

	static List<String> permutations = new ArrayList<>();

	private static void permutation(String prefix, String str) {
		int n = str.length();
		if (n == 0) {
			permutations.add(prefix);
		} else {
			for (int i = 0; i < n; i++)
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
		}
	}

}
