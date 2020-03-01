package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ArmsFinished extends InstantCommand {

    ArmSubsystem arms;
    double armSpeed;

    public ArmsFinished() {
        super();
        this.arms = new ArmSubsystem();
        requires(arms);
    }

    @Override
    protected void initialize() {
        this.arms.setLeftArmMotor(0.0);
        this.arms.setRightArmMotor(0.0);
        this.arms.applyBreak();
    }
}