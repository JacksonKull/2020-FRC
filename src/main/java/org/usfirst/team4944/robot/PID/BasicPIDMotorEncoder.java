package org.usfirst.team4944.robot.PID;

import org.usfirst.frc.team4944.robot.custom.Motor;

import edu.wpi.first.wpilibj.Encoder;
/*
 * This class is used for a basic PID control loop not specific to driving but still using motors and encoders
 * 
 * Creating one : BasicPIDMotorEncoder elevatorMotorPID = new BasicPIDMotorEncoder(P, I, D, Motor motor, Encoder encoder);
 * 
 */
public class BasicPIDMotorEncoder extends BasicPID {
	Motor motor;
	Encoder encoder;
	double power;
	
	public BasicPIDMotorEncoder(double P, double I, double D, Motor motor, Encoder encoder) {
		super(P, I, D);
		this.motor = motor;
		this.encoder = encoder;
		power = 0;
	}
	public void update() {
		error = this.getSetPoint() - encoder.get();
		if(prevError == NOT_SET) {
			prevError = error;
		}
		sumErrors += (error * .02);
		double power = error * P + (sumErrors * I) + D * ((error - prevError) / .02);
		prevError = error;
		this.power = bound(-1, power, 1);
		motor.setPower(this.power);
		
	}
	
	public double getMotorPower() {
		return this.power;
	}
	
}
