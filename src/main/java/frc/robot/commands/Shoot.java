package frc.robot.commands;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterFlyWheel;

public class Shoot extends Command{
    private ShooterFlyWheel shooter;
    public Shoot(ShooterFlyWheel shooter){
        this.shooter = shooter;
    }

    // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  }
