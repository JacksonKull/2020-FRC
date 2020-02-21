package org.usfirst.frc.team4944.robot.custom;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class ToggleCommand extends InstantCommand {
    Command cmdA;
    Command cmdB;
    boolean a = true;

    public ToggleCommand(Command a, Command b) {
        cmdA = a;
        cmdB = b;
    }

    @Override
    protected void initialize() {
        if (a) {
            cmdA.start();
        } else {
            cmdB.start();
        }
        a = !a;
    }
}