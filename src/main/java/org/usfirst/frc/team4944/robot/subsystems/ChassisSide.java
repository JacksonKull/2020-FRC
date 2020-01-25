package org.usfirst.frc.team4944.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team4944.robot.custom.Motor;

import edu.wpi.first.wpilibj.Encoder;

public class ChassisSide {

	ArrayList<Motor> motorList;
	Encoder encoder;
	double power;
	
	public ChassisSide() {
		motorList = new ArrayList<Motor>();
		this.encoder = null;
		power = 0;
	}
	
	public ChassisSide(Encoder encoder) {
		motorList = new ArrayList<Motor>();
		this.encoder = encoder;
		power = 0;
	}
	
	public void addMotor(Motor motor) {
		this.motorList.add(motor);
	}

	public void setPower(double power) {
		for(Motor m : motorList) {
			this.power = power;
			m.setPower(power);
		}
	}
	
	public double getPower() {
		return this.power;
	}
	
	public int getEncoder() {
		return encoder.get();
	}
	
}

