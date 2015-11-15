package org.mta.javacourse;

public class CalculationManager {
	
	private double radius;
	private double area;
	private double angleB;
	private double hypotenuse;
	private double opposite;
	private double base;
	private double exp;
	private double resPower;
	
	public CalculationManager(){
		radius = 50;
		angleB = 30;
		hypotenuse = 50;  
		base=20;
		exp=13;
		
	}
	public double areaOfCircle(){
		
		area = Math.pow(radius, 2) * Math.PI;
		return area;
	}
	
	public double calcOpposite() {
		opposite = Math.toRadians(angleB) * hypotenuse;
		return opposite;
	}
	
	public double calcPower() {
	
		resPower=Math.pow(base, exp);
		return resPower;
	
	}
	
	
	public String getResults() {
		
		String resultStr;
		
		String line1 = new String("1. Area of circle with radius "+radius+ " is "+areaOfCircle())+ " square cm";
		String line2 = new String("2. Length of opposite where angle B is "+ angleB+" degrees and Hypotenuse length is "+hypotenuse+ " cm is "+calcOpposite());
		String line3 = new String("3. Power of " +base+" with exp of "+exp+" is "+calcPower());
		
		resultStr = line1 + "<br>" + line2 + "<br>" + line3;		
		return resultStr;	
	}
	
			
		
			
}

