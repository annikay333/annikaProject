package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterHood;

public class HoodCommand extends Command{

    private final ShooterHood hood;
    private final boolean movingUp;

    public HoodCommand(ShooterHood hood, boolean movingUp){
        this.hood = hood;
        this.movingUp = movingUp;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        hood.moveHood(Constants.ShooterFlyWheelConstants.kShooterFlywheelSpeed, movingUp);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        hood.stop();
    }
}