package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorToBottom extends Command {

  private ElevatorSubsystem elevSub;

  public ElevatorToBottom(ElevatorSubsystem newElevSub) {

    elevSub = newElevSub;

    addRequirements(elevSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    elevSub.toSetpoint(-50);
  }

  @Override
  public void end(boolean interrupted) {
    elevSub.holdAtPoint();
  }

  @Override
  public boolean isFinished() {
    return elevSub.isAtSetpoint();
  }
}
