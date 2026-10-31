// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;

/** Tank drive command. 
*/
public class TankDrive extends Command {
  private final Drivetrain drivetrain;
  private final DoubleSupplier leftSpeedSupplier;
  private final DoubleSupplier rightSpeedSupplier;

  /**
   * Creates a TankDrive Command.
   * @param drivetrain the drivetrain subsystem to control
   * @param leftSpeedSupplier supplies the driving speed of the left side
   * @param rightSpeedSupplier supplies the driving speed of the right side
   */
  public TankDrive(Drivetrain drivetrain, DoubleSupplier leftSpeedSupplier, DoubleSupplier rightSpeedSupplier) {
    this.drivetrain = drivetrain;
    this.leftSpeedSupplier = leftSpeedSupplier;
    this.rightSpeedSupplier = rightSpeedSupplier;

    // Use addRequirements() here to declare drivetrain as requirement so the scheduler
    // wont run other commands that use drivetrain at the same time
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //read latest joystick values and sends them to drivetrain
    drivetrain.tankDrive(leftSpeedSupplier.getAsDouble(), rightSpeedSupplier.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //stops the drivetrain when command ends or is interupted
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //keep running until interrupted 
    return false;
  }
}
