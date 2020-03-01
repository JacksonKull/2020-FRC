package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class IntakingFinished extends InstantCommand {

  IntakeSubsystem intake;

  public IntakingFinished() {
    super();
    this.intake = new IntakeSubsystem();
    requires(intake);
  }

  @Override
  protected void initialize() {
    this.intake.setIntakeMotor(0);
  }
}
