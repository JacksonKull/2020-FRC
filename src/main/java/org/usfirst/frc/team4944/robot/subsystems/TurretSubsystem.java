package org.usfirst.frc.team4944.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team4944.robot.custom.AS5600EncoderPwm;
import org.usfirst.frc.team4944.robot.custom.Limelight;
// import org.usfirst.frc.team4944.robot.custom.Motor;
// import org.usfirst.frc.team4944.robot.custom.MotorType;
import org.usfirst.team4944.robot.PID.BasicPID;

// import edu.wpi.first.networktables.NetworkTable;
// import edu.wpi.first.networktables.NetworkTableInstance;
// import edu.wpi.first.wpilibj.Encoder;
// import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TurretSubsystem extends Subsystem {

	// Limelight

	public Limelight lm;

	// Motors

	TalonSRX turretMotor;

	// Encoder

	AS5600EncoderPwm turretEncoder;

	// Turret Constants

	final int turretOffset = 100;
	final int minTurretEncoder = -1312;
	final int neutralTurretEncoder = 0;
	final int maxTurretEncoder = 1220;
	final double minTurretAngle = -90;
	final double maxTurretAngle = 90;
	final double maxTurretPow = 0.4;
	final double speedThreshold = 1;
	final double acceptableError = 0.15;

	// Total Range of 2532

	final double visionMaxPow = 0.15;

	// PID

	BasicPID turretPID;

	// Vision PID Values

	final double visionP = .28;

	// Turret PID Values

	// final double turret_p = .005;
	final double turret_p = .08;
	// final double turret_i = .1;
	final double turret_i = 0.09;
	final double turret_d = 0;

	// Values

	double currentTur = 0;
	double lastTur = 0;

	public TurretSubsystem() {

		// Motors

		this.turretMotor = new TalonSRX(7);

		// Encoder
		
		this.turretEncoder = new AS5600EncoderPwm(this.turretMotor.getSensorCollection());
		this.turretPID = new BasicPID(this.turret_p, this.turret_i, this.turret_d);
		this.turretPID.setSetPoint(this.neutralTurretEncoder);

		// Limelight

		this.lm = new Limelight();
	}

	public void setTurretMotorPower(double power) {
		this.turretMotor.set(ControlMode.PercentOutput, power);
	}

	public double getTurretMotorPower() {
		return this.turretMotor.getMotorOutputPercent();
	}

	private void setTurretSetPoint(int encoderSetPoint) {
		if (encoderSetPoint < minTurretEncoder) {
			this.turretPID.setSetPoint(minTurretEncoder);
		} else if (encoderSetPoint > maxTurretEncoder) {
			this.turretPID.setSetPoint(maxTurretEncoder);
		} else {
			this.turretPID.setSetPoint(encoderSetPoint);
		}
	}

	public double getTurretSetPoint() {
		return this.turretPID.getSetPoint();
	}

	// Drives the Motors based off of the PID and

	public void driveTurretPID() {
		System.out.println(
				Math.abs(Math.abs(this.getTurretSetPoint()) - Math.abs(this.getTurretEncoderValue())) + " Difference");
		if (Math.abs(Math.abs(this.getTurretSetPoint()) - Math.abs(this.getTurretEncoderValue())) > 0.5) {
			double power = this.turretPID.getPower(this.getTurretEncoderValue());
			System.out.println((-power) * visionMaxPow + " Power");
			this.setTurretMotorPower((-power) * maxTurretPow);
		} else {
			System.out.println("Turret Within Range");
			this.setTurretMotorPower(0);
		}
	}

	// Drives the limelight based purly off of offset (No Encoders)

	public void followLimelightNoEncoder() {
		if (lm.getTargetVisible()) {
			// double turPow = lm.getXOffset() * visionP; // Working Earlier
			double turPow = this.turretPID.getPower(this.lm.getXOffset());
			// double turPow = this.turretPID.getPower(lm.getXOffset());
			System.out.println(turPow * maxTurretPow + " Power");
			this.setTurretMotorPower((turPow) * this.maxTurretPow);
		}else{
			this.setTurretMotorPower(0);
		}
	}

	public double getPIDPower(double input){
		return this.turretPID.getPower(input);
	}

	public double getTurretMaxPow(){
		return this.maxTurretPow;
	}

	public int getTurretEncoderValue() {
		return this.turretEncoder.getPwmPosition();
	}

	// Returns if the shooter is within lineup range

	public boolean followLimelight() {
		System.out.println(lm.getTargetVisible());
		if (!lm.getTargetVisible()) {
			return false;
		} else if (Math.abs(lm.getXOffset()) < 1) {
			this.setTurretSetPoint(this.getTurretEncoderValue());
			return true;
		} else {
			this.addTurretAngle(lm.getXOffset());
			return false;
		}
	}

	private int convertTurretAngleToEncoder(double desiredAngle) {
		double ticksPerDegree = (maxTurretEncoder - minTurretEncoder) / (maxTurretAngle - minTurretAngle);
		return (int) (desiredAngle * ticksPerDegree);
	}

	public void setTurretAngle(double desiredAngle) {
		// 0 is neutral
		// 90 is 90 to the right
		// -90 is 90 to the left
		int encoderGoal = neutralTurretEncoder + this.convertTurretAngleToEncoder(desiredAngle);
		this.setTurretSetPoint(encoderGoal);
	}

	public void addTurretAngle(double desiredAngle) {
		int encoderGoal = this.getTurretEncoderValue() + this.convertTurretAngleToEncoder(desiredAngle);
		this.setTurretSetPoint(encoderGoal);
	}

	public void updateTurretValues(){
		this.lastTur = this.currentTur;
		this.currentTur = this.getTurretEncoderValue();
	}

	public int getTurretSpeed(){
		return 0;
	}

	public boolean getTuretDoneMoving(){
		if(Math.abs(this.getTurretSpeed()) < this.speedThreshold){
			return true;
		}else{
			return false;
		}
	}

	public boolean getWithinRange(){
		if(Math.abs(this.turretPID.getError()) < this.acceptableError){
			return true;
		}else{
			return false;
		}
	}

	public void stop(){
		this.setTurretMotorPower(0);
	}

	@Override
	protected void initDefaultCommand() {

	}
}