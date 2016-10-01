package bottles;

public final class Point implements Comparable<Point> {

	public static void main(String[] args) {
		Point p1 = new Point();
		Point p2 = new Point(2, 3);
		Point p3 = new Point(p2);
		Point p4 = new Point(p1);
		System.out.println(p3.equals(p2));
		System.out.println(getOrigin().toString());
		System.out.println(p3.toString());
		System.out.println(p4.toString());
		System.out.println();
	}

	private double x, y;

	public Point() {
		this(0, 0);
	}

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point p) {
		x = p.x;
		y = p.y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public static Point getOrigin() {
		return new Point();
	}

	@Override
	public boolean equals(Object obj) {
		Point p = (Point) obj;
		return x == p.getX() && y == p.getY();
	}

	public static String format(double d) {
		if (d == (long) d)
			return String.format("%d", (long) d);
		else
			return String.format("%s", d);
	}

	@Override
	public String toString() {
		return "(" + format(getX()) + ", " + format(getY()) + ")";
	}

	@Override
	public int compareTo(final Point p) {
		if (p == null) {
			return 1;
		}

		final int xcomp = Double.compare(x, p.x);

		return xcomp == 0 ? Double.compare(y, p.y) : xcomp;
	}

}