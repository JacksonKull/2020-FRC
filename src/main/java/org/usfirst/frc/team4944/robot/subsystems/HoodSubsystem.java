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
  // final double hood_i = 0.0000001;
  final double hood_i = 0.0001;
  final double hood_d = 0;
  // Constants
  final int hoodOffset = 100;
  // final int maxHoodEncoder = 4880;
  final int maxHoodEncoder = 5160;
  final int minHoodEncoder = 0;
  // final double minHoodAngle = 20;
  final double minHoodAngle = 20.4;
  // final double maxHoodAngle = 45;
  final double maxHoodAngle = 47.4;
  final double maxHoodPow = 0.3;
  double hoodAngleOffset = 0.0;
  final double encoderConst = 195.2;
  // Double
  double vx, vy, lmAngle;
  // Encoder Count = (Theta - LowerLimAng)*maxEncoderCount/(UpperLimAng -
  // LowerLimAng)

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
      System.out.println(power + " Power");
      this.setHoodMotorPower(power);
    } else {
      System.out.println("Hood Within Range");
      this.setHoodMotorPower(0);
    }
  }

  public int getHoodEncoderValue() {
    return this.hoodMotor.getSelectedSensorPosition();
  }

  public double convertDistToAngle(double distance) {
    // double angle =
    // (0.0161904762*(distance))-(0.7085714186*((distance)*(distance)))+(9.223809524*(distance))-(5.571428571);
    double angle = ((-.7489177489) * distance) + 39.13419913;
    System.out.println(distance + " Distance");
    System.out.println(angle + " Angle");
    return angle;
    // return
    // (0.0161904762*(distance))-(0.7085714186*((distance)*(distance)))+(9.223809524*(distance))-(5.571428571);
  }

  // Encoder Count = (Theta - LowerLimAng)*maxEncoderCount/(UpperLimAng -
  // LowerLimAng)
  private int convertHoodAngleToEncoder(double desiredAngle) {
    // double ticksPerDegree = (maxHoodEncoder - minHoodEncoder) / (maxHoodAngle -
    // minHoodAngle);
    // return (int) (desiredAngle * ticksPerDegree);
    // double encoderCounts = (this.maxHoodAngle - desiredAngle) *
    // (this.encoderConst);
    // double encoderCounts = (desiredAngle -
    // this.minHoodAngle)*(this.maxHoodEncoder/(this.maxHoodAngle-this.minHoodAngle));
    double encoderCounts = (desiredAngle) * 191.111;
    return (int) encoderCounts;
  }

  public double convertEncoderToAngle(double encoder) {
    return (0);
  }

  public void setHoodAngle(double desiredAngle) {
    int encoderGoal = (this.convertHoodAngleToEncoder(desiredAngle));
    // System.out.println(desiredAngle + " Angle");
    // System.out.println(encoderGoal + " SetPoint");
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

  // public double getRequiredAngle() {
  // this.lmAngle = Math.atan((this.vy) / (this.vx));
  // return Math.toDegrees(this.lmAngle) + this.getHoodAngleOffset();
  // }

  public double getRequiredAngle() {
    this.lmAngle = this.convertDistToAngle(this.lm.getDistInFeet());
    // System.out.println(lmAngle);
    return this.lmAngle;
  }

  public void updateValues() {
    this.vx = 32 * ((this.lm.getDistInFeet() + 2.5) / (this.vy));
    this.vy = 8 * (Math.sqrt(8.2 - this.lm.getHeighOffGroudLM()));
    this.lmAngle = Math.toDegrees(Math.atan((this.vx) / (this.vy)));
  }

  public void setAngleByLM() {
    this.setHoodAngle(this.getRequiredAngle());
  }

  @Override
  public void initDefaultCommand() {
  }
}