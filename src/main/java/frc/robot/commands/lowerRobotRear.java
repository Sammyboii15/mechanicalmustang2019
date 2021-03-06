/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class lowerRobotRear extends Command {

  boolean isCountReached = false;

  public lowerRobotRear() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_dropArms);
    // setTimeout(RobotMap.REAR_ARM_TIME);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_dropArms.resetRearClimbingArmCount();
    isCountReached = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.m_dropArms.lowerRobot() >= RobotMap.CLIMBING_ARMS_REAR_COUNT) {
      isCountReached = true;
    } 
    System.out.println(Robot.m_dropArms.lowerRobot());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isCountReached;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_dropArms.armsStop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
