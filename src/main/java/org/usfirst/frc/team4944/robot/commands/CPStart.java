package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class CPStart extends InstantCommand {

  IntakeSubsystem intake;
  double cpPower;

  public CPStart(double power) {
    super();
    this.intake = new IntakeSubsystem();
    this.cpPower = power;
    requires(intake);
  }

  @Override
  protected void initialize() {
    this.intake.setIntakeMotor(this.cpPower);
  }
}