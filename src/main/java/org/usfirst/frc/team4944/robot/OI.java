package org.usfirst.frc.team4944.robot;

import org.usfirst.frc.team4944.robot.commands.IntakeInit;
import org.usfirst.frc.team4944.robot.commands.IntakingFinished;
import org.usfirst.frc.team4944.robot.commands.ShootingCommandGroup;
import org.usfirst.frc.team4944.robot.commands.ShootingFinished;
import org.usfirst.frc.team4944.robot.custom.XboxController;

public class OI {
    XboxController driver;
    XboxController operator;

    public OI() {
        this.driver = new XboxController(0);
        this.operator = new XboxController(1);

        driver.addCommandToA(new ShootingCommandGroup());
        driver.addWhenReleasedToA(new ShootingFinished());

        driver.addCommandToB(new IntakeInit(1));
        driver.addWhenReleasedToB(new IntakingFinished());
    }

}
