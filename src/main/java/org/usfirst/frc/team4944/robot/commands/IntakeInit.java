package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.ArmSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class IntakeInit extends InstantCommand {

  IntakeSubsystem intake;
  double intakePow;
  ArmSubsystem arms;

  public IntakeInit(double pow) {
    super();
    this.intake = new IntakeSubsystem();
    requires(intake);
    this.intakePow = pow;
    this.arms = new ArmSubsystem();
    requires(arms);
  }

  @Override
  protected void initialize() {
    this.arms.disableBreak();
    this.intake.setIntakeMotor(this.intakePow);
  }
}
