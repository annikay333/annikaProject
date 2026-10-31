// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  // Motor controlers for left and right sides of the drivetrain
  // each side has a leader and a follower
  private final SparkMax leftLeader = new SparkMax(Constants.DrivetrainConstants.kFrontLeftCanID, MotorType.kBrushless);
  private final SparkMax leftFollower = new SparkMax(Constants.DrivetrainConstants.kBackLeftCanID, MotorType.kBrushless);
  private final SparkMax rightLeader = new SparkMax(Constants.DrivetrainConstants.kFrontRightCanID, MotorType.kBrushless);
  private final SparkMax rightFollower = new SparkMax(Constants.DrivetrainConstants.kBackRightCanID, MotorType.kBrushless);

  // for controling a differential drive robot
  private final DifferentialDrive diffDrive = new DifferentialDrive(leftLeader, rightLeader);

  // constructor
  public Drivetrain() {
    // config objects to configure
    SparkMaxConfig leftLeaderConfig = new SparkMaxConfig();
    SparkMaxConfig rightLeaderConfig = new SparkMaxConfig();
    SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
    SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();

    //invert one side to make both sides (left and right) drive in the same direction
    //configure followers to follow leaders
    rightLeaderConfig.inverted(true);
    leftFollowerConfig.follow(leftLeader);
    rightFollowerConfig.follow(rightLeader);

    // limit current to 40A
    leftLeaderConfig.smartCurrentLimit(40);
    rightLeaderConfig.smartCurrentLimit(40);
    leftFollowerConfig.smartCurrentLimit(40);
    rightFollowerConfig.smartCurrentLimit(40);

    // apply configurations to each  motor controller
    // reset safe parameters to defaults before applying the new configuration.
    // save the settings permanently to the motor controller's memory.
    leftLeader.configure(leftLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightLeader.configure(rightLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    leftFollower.configure(leftFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightFollower.configure(rightFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  }

  // Passes left and right speeds to differential drive
  public void tankDrive(double leftSpeed, double rightSpeed){
    diffDrive.tankDrive(leftSpeed, rightSpeed);
  }

  public void stop() {
    diffDrive.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
