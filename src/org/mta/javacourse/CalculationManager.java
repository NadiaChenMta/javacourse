package org.mta.javacourse;

public class CalculationManager {
	private static double RADIUS = 50;
	private static double HYPOTENUSE = 50;
	private static double ANGLE = 30;
	private static double BASE = 20;
	private static double EXP = 13;
	
	private double area;
	private double opposite;
	private double powerRes;
		
	public double getArea(double radius) {
		area = Math.PI * Math.pow(radius, 2);
		return area;
	}
	
	public double getOpposite(double hypotenuse, double angle) {
		opposite = Math.sin(angle * Math.PI / 180) * hypotenuse;
		return opposite;
	}
	
	public double getPower(double base, double exp) {
		powerRes = Math.pow(base, exp);
		return powerRes;
	}

	public String getResults() {
		String line1 = new String("1. The area of a circle with radius " + RADIUS + " is " + getArea(RADIUS));
		String line2 = new String("2. The lenght of opposite where angle B is " + ANGLE + " and hypotenuse is " + HYPOTENUSE + " is " + getOpposite(HYPOTENUSE, ANGLE));
		String line3 = new String("1. Power of " + BASE + " with exponent of " + EXP + " is " + getPower(BASE, EXP));
		
		return line1 + "<br>" + line2 + "<br>" + line3;
	}	
}
