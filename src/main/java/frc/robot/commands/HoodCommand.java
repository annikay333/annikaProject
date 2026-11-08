package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterHood;

public class HoodCommand extends Command{

    private final ShooterHood hood;
    private final double targetPosition;

    public HoodCommand(ShooterHood hood, double targetPosition){
        this.hood = hood;
        this.targetPosition = targetPosition;

        addRequirements(hood);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        hood.setPosition(targetPosition);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}
}