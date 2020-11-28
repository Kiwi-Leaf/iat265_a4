package util;

public class Util {

	public Util() {
		// TODO Auto-generated constructor stub
	}
	public static int random(int x) {
		return (int)(Math.random()*x);
	}
	public static double radians(double angle){
		return angle/180*Math.PI;		
	}
	
	public static float radians(float angle){
		return angle/180*(float)Math.PI;		
	}
	public static int random(int min,int max) {
		return (int)(min+Math.random()*(max-min));
	}

}
