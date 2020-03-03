package org.usfirst.frc.team4944.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team4944.robot.custom.Limelight;
import org.usfirst.team4944.robot.PID.BasicPID;

import edu.wpi.first.wpilibj.command.Subsystem;

public class HoodSubsystem extends Subsystem {

  // Motors
  TalonSRX hoodMotor;
  // Limelight
  Limelight lm;
  // PID
  BasicPID hoodPID;
  // PID Values
  final double hood_p = 0.00009;
  final double hood_i = 0.0002;
  final double hood_d = 0;
  // Constants
  final int hoodOffset = 100;
  final int maxHoodEncoder = 5800;
  final int minHoodEncoder = 0;

  final double minHoodAngle = 20;
  final double maxHoodAngle = 47;
  final double maxHoodPow = 0.3;
  double hoodAngleOffset = 0.0;
  final double encoderConst = 214.8148;
  // Double
  double vx, vy, lmAngle;

  public HoodSubsystem() {
    // Motors
    this.hoodMotor = new TalonSRX(9);
    // Encoder
    this.hoodMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    this.hoodMotor.setSelectedSensorPosition(0);
    // Limelight
    this.lm = new Limelight();
    // PID
    this.hoodPID = new BasicPID(this.hood_p, this.hood_i, this.hood_d);
  }

  public void setHoodAngleOffset(double offset) {
    this.hoodAngleOffset = offset;
  }

  public double getHoodAngleOffset() {
    return this.hoodAngleOffset;
  }

  public void setHoodMotorPower(double power) {
    this.hoodMotor.set(ControlMode.PercentOutput, Math.min(Math.max(power, -this.maxHoodPow), this.maxHoodPow));
  }

  public double getHoodMotorPower() {
    return this.hoodMotor.getMotorOutputPercent();
  }

  private void setHoodSetPoint(int encoderSetPoint) {
    if (encoderSetPoint < minHoodEncoder) {
      this.hoodPID.setSetPoint(minHoodEncoder);
    } else if (encoderSetPoint > maxHoodEncoder) {
      this.hoodPID.setSetPoint(maxHoodEncoder);
    } else {
      this.hoodPID.setSetPoint(encoderSetPoint);
    }
  }

  public double getHoodSetPoint() {
    return this.hoodPID.getSetPoint();
  }

  public void driveHoodPID() {
    double error = Math.abs(this.getHoodSetPoint()) - Math.abs(this.getHoodEncoderValue());
    if (error > 30 || error < -30) {
      double power = this.hoodPID.getPower(this.getHoodEncoderValue());
      this.setHoodMotorPower(power);
    } else {
      this.setHoodMotorPower(0);
    }
  }

  public int getHoodEncoderValue() {
    return this.hoodMotor.getSelectedSensorPosition();
  }

  public double convertDistToAngle(double distance) {
    double angle = ((-.7489177489) * distance) + 39.13419913;
    return angle;
  }

  // Encoder Count = (UpperLimAng - Theta)*maxEncoderCount/(UpperLimAng -
  // LowerLimAng)
  public int convertHoodAngleToEncoder(double desiredAngle) {
    double encoderCounts = ((maxHoodAngle - desiredAngle) * ((maxHoodEncoder) / (maxHoodAngle - minHoodAngle)));
    return (int) encoderCounts;
  }

  public int convertEncoderToAngle(int encoder) {
    double encoderCounts = (this
        .convertHoodAngleToEncoder(convertDistToAngle(this.lm.getDistInFeet()) - this.minHoodAngle)
        * (this.maxHoodEncoder / (this.maxHoodAngle - this.minHoodAngle)));
    return (int) encoderCounts;
  }

  public void setHoodAngle(double desiredAngle) {
    int encoderGoal = this.convertHoodAngleToEncoder(desiredAngle);
    this.setHoodSetPoint(encoderGoal);
  }

  public double getVx() {
    this.vx = 32 * ((this.lm.getDistInFeet() + 2.5) / (this.vy));
    return this.vx;
  }

  public double getVy() {
    this.vy = 8 * (Math.sqrt(8.2 - this.lm.getHeighOffGroudLM()));
    return this.vy;
  }

  public double getRequiredAngle() {
    this.lmAngle = this.convertDistToAngle(this.lm.getDistInFeet());
    return this.lmAngle;
  }

  public void updateValues() {
    this.vx = 32 * ((this.lm.getDistInFeet() + 2.5) / (this.vy));
    this.vy = 8 * (Math.sqrt(8.2 - this.lm.getHeighOffGroudLM()));
    this.lmAngle = Math.toDegrees(Math.atan((this.vx) / (this.vy)));
  }

  public void setAngleByLM() {
    this.setHoodAngle(this.getRequiredAngle());
    System.out.println(this.getRequiredAngle() + " Required Angle");
  }

  @Override
  public void initDefaultCommand() {
  }
}