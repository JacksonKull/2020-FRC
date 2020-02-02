package org.usfirst.team4944.robot.PID;

import org.usfirst.frc.team4944.robot.custom.Motor;

import edu.wpi.first.wpilibj.Encoder;
/*
 * This class lets you input a motor and its' encoder as well as PID values
 * 
 * Creating one : DrivePIDMotorEncoder leftMotor = new DrivePIDMotorEncoder(P, I, D, valMin, valMax, Motor motor, Encoder encoder);
 * 
 */
public class DrivePIDMotorEncoder extends DrivePID{

	Motor motor;
	Encoder encoder;
	double power;
	
	public DrivePIDMotorEncoder(double P, double I, double D, double valMin, double valMax, Motor motor, Encoder encoder) {
		super(P, I, D, valMin, valMax);
		this.motor = motor;
		this.encoder = encoder;
		power = 0;
		
	}

	public void update() {
		double error = this.getSetPoint() - encoder.get();
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
		motor.setPower(bound(-1, power, 1));
		this.power = bound(-1, power, 1);
	}
	
	public double getPower() {
		return this.power;
	}
	
}
