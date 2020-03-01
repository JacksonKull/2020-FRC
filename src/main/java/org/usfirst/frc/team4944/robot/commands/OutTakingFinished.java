package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.HopperSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class OutTakingFinished extends InstantCommand {

  IntakeSubsystem intake;
  HopperSubsystem hopper;
  ShooterSubsystem shooter;

  public OutTakingFinished() {
    super();
    this.intake = new IntakeSubsystem();
    requires(intake);
    this.hopper = new HopperSubsystem();
    requires(hopper);
    this.shooter = new ShooterSubsystem();
    requires(shooter);
  }

  @Override
  protected void initialize() {
    this.intake.setIntakeMotor(0);
    this.hopper.setBeltMotor(0);
    this.hopper.setHopperMotor(0);
    this.shooter.setManualShooterPower(0);
  }
}
