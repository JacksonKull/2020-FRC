package org.usfirst.frc.team4944.robot.custom;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class TriggerCommand extends InstantCommand {

  Command cmd;
  boolean triggerState;

  public TriggerCommand(Command c, boolean triggerOn) {
    super();
    this.cmd = c;
    this.triggerState = triggerOn;
  }

  @Override
  protected void initialize() {
    if (this.triggerState) {
      this.cmd.start();
      System.out.println("Starting Commands");
    }
    System.out.println(this.triggerState);
  }
}
