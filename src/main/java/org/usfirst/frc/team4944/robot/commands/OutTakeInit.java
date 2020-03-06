package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.HopperSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class OutTakeInit extends InstantCommand {

  IntakeSubsystem intake;
  HopperSubsystem hopper;
  ShooterSubsystem shooter;
  double intakePow, hopperPow, beltPow, shooterPow;
  public OutTakeInit(double intakePow, double hopperPow, double beltPow, double shooterPow) {
    super();
    this.intake = new IntakeSubsystem();
    requires(intake);
    this.intakePow = intakePow;
    this.hopper = new HopperSubsystem();
    requires(hopper);
    this.hopperPow = hopperPow;
    this.beltPow = beltPow;
    this.shooter = new ShooterSubsystem();
    requires(shooter);
    this.shooterPow = shooterPow;
  }

  @Override
  protected void initialize() {
    // this.intake.setIntakeMotor(-this.intakePow);
    // this.hopper.setHopperMotor(-this.hopperPow);
    // this.hopper.setBeltMotor(-this.beltPow);
    this.intake.setIntakeMotor(this.intakePow);
    this.hopper.setHopperMotor(this.hopperPow);
    this.hopper.setBeltMotor(this.beltPow);
    this.shooter.setManualShooterPower(this.shooterPow);
  }
}
