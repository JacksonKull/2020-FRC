/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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

  // Called once when the command executes
  @Override
  protected void initialize() {
    if(this.triggerState){
      this.cmd.start();
      System.out.println("Starting Commands");
    }
    System.out.println(this.triggerState);
  }
}
