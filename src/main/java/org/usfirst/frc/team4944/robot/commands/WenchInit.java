package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.WenchSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class WenchInit extends InstantCommand {

  WenchSubsystem wench;
  double wenchPow;

  public WenchInit(double pow) {
    super();
    this.wench = new WenchSubsystem();
    requires(wench);
    this.wenchPow = pow;
  }

  @Override
  protected void initialize() {
    this.wench.setWenchMotor(this.wenchPow);
  }

}
