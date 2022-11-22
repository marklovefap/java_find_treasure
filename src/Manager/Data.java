package Manager;

public class Data {
	public static long time;
	public static int diamond;
	public static int death;

	public static void setTime(long l) { time = l; }

	public static long getTime() { return time; }

	public static void setDia(int i) { diamond = i; }

	public static int getDia() { return diamond; }

	public static void setDeath() { death++;}

	public static int getDeath() { return death; }

	public static void resetDeath() { death = 0; }
}
