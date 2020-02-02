package org.usfirst.team4944.robot.PID;
/*
 * BasicPID class
 * 
 * This class has all the basic values and operations required for PID to function 
 * 
 * When Creating a BasicPID : BasicPID basicPID = new BasicPID(double P, double I, double D);
 * 
 * When setting a setPoint : basicPID.setSetPoint(double setPoint);
 * 
 */
public class BasicPID {

	double P, I, D, setPoint, sumErrors, prevError, error;
	public static final double NOT_SET = -Double.MAX_VALUE;
	public BasicPID(double P, double I, double D) {
		this.P = P;
		this.I = I;
		this.D = D;
		this.setPoint = 0;
		this.sumErrors = 0;
		this.prevError = NOT_SET;
		this.error = NOT_SET;
	}

	public double getP() {
		return P;
	}

	public void setP(double p) {
		P = p;
	}

	public double getI() {
		return I;
	}

	public void setI(double i) {
		I = i;
	}

	public double getD() {
		return D;
	}

	public void setD(double d) {
		D = d;
	}

	public double getSetPoint() {
		return setPoint;
	}

	public void setSetPoint(double setPoint) {
		this.setPoint = setPoint;
	}
	public double getError() {
		return prevError;
	}
	public double getPower(double currentValue) {
		error = this.getSetPoint() - currentValue;
		if(prevError == NOT_SET) {
			prevError = error;
		}
		sumErrors += (error * .02);
		double power = error * P + (sumErrors * I) + D * ((error - prevError) / .02);
		prevError = error;
		return bound(-1, power, 1);
		
	}
	
	public double bound(double min, double val, double max) {
		return Math.min(Math.max(val, min), max);
	}

}
