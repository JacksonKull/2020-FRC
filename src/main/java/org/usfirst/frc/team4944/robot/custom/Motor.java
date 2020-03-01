package org.usfirst.frc.team4944.robot.custom;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.PWMTalonFX;

public class Motor {

	MotorType type;
	PWMSpeedController controller;
	// TODO Add in Can TalonSRX and VictorSPX
	Talon talon;
	PWMTalonSRX talonSRX; // Only for PWM NOT Can
	Jaguar jag;
	VictorSP vicSP;
	PWMVictorSPX vicSPX; // Only for PWM NOT Can
	Spark spark;
	PWMTalonFX talonFX;

	public Motor(MotorType type, int port) {
		this.type = type;
		if (type == MotorType.Talon) {
			talon = new Talon(port);
			controller = (PWMSpeedController) talon;
		} else if (type == MotorType.PWMTalonSRX) {
			talonSRX = new PWMTalonSRX(port);
			controller = (PWMSpeedController) talonSRX;
		} else if (type == MotorType.Jaguar) {
			jag = new Jaguar(port);
			controller = (PWMSpeedController) jag;
		} else if (type == MotorType.SPARK) {
			spark = new Spark(port);
			controller = (PWMSpeedController) spark;
		} else if (type == MotorType.VictorSP) {
			vicSP = new VictorSP(port);
			controller = (PWMSpeedController) vicSP;
		} else if (type == MotorType.PWMVictorSPX) {
			vicSPX = new PWMVictorSPX(port);
			controller = (PWMSpeedController) vicSPX;
		} else if (type == MotorType.PWMTalonFX) {
			talonFX = new PWMTalonFX(port);
		}
	}

	public void setPower(double power) {
		controller.set(power);
	}

	public void setInverted(boolean set) {
		controller.setInverted(set);
	}

	public double getPower() {
		return controller.get();
	}

	public boolean getInverted() {
		return controller.getInverted();
	}

	public boolean isAlive() {
		return controller.isAlive();
	}

	public void stopMotor() {
		controller.stopMotor();
	}

	// TODO Add Custom talonSRX functions

}
