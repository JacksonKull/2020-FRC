package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class CPFinished extends InstantCommand {
  IntakeSubsystem intake;

  public CPFinished() {
    super();
    this.intake = new IntakeSubsystem();
    requires(intake);
  }

  @Override
  protected void initialize() {
    this.intake.setIntakeMotor(0);
  }
}