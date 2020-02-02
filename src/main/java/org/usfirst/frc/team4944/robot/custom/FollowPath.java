package org.usfirst.frc.team4944.robot.custom;
import org.usfirst.frc.team4944.robot.subsystems.DriveSystem;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class FollowPath extends Command {
  // ENCODER FOLLOWERS
  EncoderFollower leftFollower;
  EncoderFollower rightFollower;
  // FILES
  public File leftCSV;
  public File rightCSV;
  // PATHS
  Paths leftPath;
  Paths rightPath;
  // TRAJECTORIES
  Trajectory rightTrajectory;
  Trajectory leftTrajectory;
  // NOTIFIERS
  Notifier notifier;
  // CONSTANTS
  double dt;
  final double p = 1000;
  final double i = 1000;
  final double d = 1000;
  final double v = 1 / 12;
  final double a = 1000;
  final int ticksPerRevolution = 128;
  final double wheelDiameter = 6/12;
  // SUBSYSTEMS
  DriveSystem driveSystem;
  double leftEncoder;
  double rightEncoder;
  org.usfirst.frc.team4944.robot.Robot robot;


public FollowPath(String pathName) throws IOException {
  requires(driveSystem);
  // Commented Code below is prior testing left in case refrence is needed
  /*************************************************************************************************************
  leftPath.get("C:\\Users\\Jacks\\Desktop\\Path Files\\output\\TestPath.left.pf1.csv").toString();
  rightPath.get("C:\\Users\\Jacks\\Desktop\\Path Files\\output\\TestPath.right.pf1.csv").toString();
  leftCSV = new File(leftPath.get("C:\\Users\\Jacks\\Desktop\\Path Files\\output\\TestPath.left.pf1.csv").toString());
  rightCSV = new File(rightPath.get("C:\\Users\\Jacks\\Desktop\\Path Files\\output\\TestPath.right.pf1.csv").toString());
  
  rightCSV = new File("C:\\Users\\Jacks\\Desktop\\Path_Files\\output\\TestPath.right.pf1.csv");
  leftCSV = new File("C:\\Users\\Jacks\\Desktop\\Path_Files\\output\\TestPath.left.pf1.csv");
  //C:\Users\Public\frc2019\frccode\Pathweaver Testing\Pathweaver Testing\src\main\java\org\ usfirst\frc\team4944\robot\Paths
  //rightCSV = new File("src/main/java/org/usfirst/frc/team4944/robot/Paths/output/TestPath.right.pf1.csv");
  //leftCSV = new File("src/main/java/org/usfirst/frc/team4944/robot/Paths/output/TestPath.left.pf1.csv");
  ***************************************************************************************************************/

  //Checking if the path provided is Null
  if(pathName == null){
    System.out.println("pathName is NULL");
  }else{
    System.out.println("pathName is GOOD");
  }

  //Setting the location for the file
  this.rightCSV = new File(Filesystem.getDeployDirectory().getAbsolutePath() + "/Paths/output/" + pathName + ".right.pf1.csv");
  this.leftCSV = new File(Filesystem.getDeployDirectory().getAbsolutePath() + "/Paths/output/" + pathName + ".left.pf1.csv");
 
  //Telling the trajectories where to find the .csv files
  this.leftTrajectory = Pathfinder.readFromCSV(this.leftCSV);
  this.rightTrajectory = Pathfinder.readFromCSV(this.rightCSV);
  
  this.notifier = new Notifier(new RunProfile());
  this.dt = leftTrajectory.get(0).dt;
  System.out.println("CSV has been locked and loaded " + this.leftCSV.exists());
}

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    //Initializes required Subsystems
    this.driveSystem = new DriveSystem();

    //Instantiates the Encoder Followers
    this.leftFollower = new EncoderFollower(this.leftTrajectory);
    this.rightFollower = new EncoderFollower(this.rightTrajectory);

    //Resets Encoder follower to 0
    this.leftFollower.reset();
    this.rightFollower.reset();

    //Debug out put in order to determine that encoder values are changing properly
    System.out.println(driveSystem.getLeftEncoder());
    System.out.println(driveSystem.getRightEncoder());

    //Sets up the encoders (Starting Position, Ticks Per Revolution, Wheel Diameter)
    this.leftFollower.configureEncoder(driveSystem.getLeftEncoder(), this.ticksPerRevolution, this.wheelDiameter);
    this.rightFollower.configureEncoder(driveSystem.getRightEncoder(), this.ticksPerRevolution, this.wheelDiameter);

    //Configures PIDVA values for the encoder followers
    this.leftFollower.configurePIDVA(p, i, d, v, a);
    this.rightFollower.configurePIDVA(p, i, d, v, a);
    this.notifier.startPeriodic(dt);

    System.out.println("Initialized");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
@Override
protected boolean isFinished() {
    System.out.println("Finished");
    return this.leftFollower.isFinished() && rightFollower.isFinished();
}

  // Called once after isFinished returns true
  @Override
  protected void end() {
    this.notifier.stop();
    this.driveSystem.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }

  class RunProfile implements java.lang.Runnable {
    int segmentNumber = 0;

    @Override
    public void run() {

        double leftOutput = leftFollower.calculate(driveSystem.getLeftEncoder());
        double rightOutput = -rightFollower.calculate(driveSystem.getRightEncoder());

        double gyroHeading = -driveSystem.getAngle();

        double desiredHeading = Pathfinder.d2r(leftFollower.getHeading());

        //System.out.println(desiredHeading);

        //double angleDifference = gyroHeading + desiredHeading;

        double angleDifference = -Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);

        double turn = 0.08 * (-1. / 80.) * angleDifference;

        System.out.println(turn);

        driveSystem.setPower(leftOutput - turn, rightOutput + turn);

        segmentNumber++;
    }
  }
}