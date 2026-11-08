package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterFlywheel;

public class ShootCommand extends Command{

    private ShooterFlywheel shooter;

    public ShootCommand(ShooterFlywheel shooter){
        this.shooter = shooter;
        //prevents other commands from using shooter at the same time
        addRequirements(shooter);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        shooter.shoot(Constants.ShooterFlywheelConstants.kShooterFlywheelSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        shooter.stop();
    }

  }
