package org.usfirst.team4944.robot.PID;
/*
 * This class contains everything needed to use PID for driving 
 * 
 * It also lets you set at what point you want the I to take effect
 * 
 * Setting min and max for the I : setValMin(double valMin);
 */
public class DrivePID extends BasicPID{
	
	double valMin, valMax;
	public DrivePID(double P, double I, double D, double valMin, double valMax) {
		super(P, I, D);
		this.valMin = valMin;
		this.valMax = valMax;
	}
	public double getValMin() {
		return valMin;
	}
	public void setValMin(double valMin) {
		this.valMin = valMin;
	}
	public double getValMax() {
		return valMax;
	}
	public void setValMax(double valMax) {
		this.valMax = valMax;
	}
	public double getPower(double currentValue) {
		double error = this.getSetPoint() - currentValue;
		if(prevError == NOT_SET) {
			prevError = error;
		}
		if(Math.signum(error) != Math.signum(prevError)) {
			sumErrors = 0;
		}
		if(error>= valMin && error<= valMax) {
			sumErrors += (error * .02);
		}
		
		double pPower = error * P;
		double iPower = sumErrors * I;
		double dPower = D * ((error - prevError) / .02);
		double power = pPower + iPower + dPower;
		prevError = error;
		return bound(-1, power, 1);
	}
	
	public double getPrevError() {
		return prevError;
	}
}
