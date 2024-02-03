package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.HangConstants;
import frc.robot.subsystems.HangSubsystem;

public class ElevatorToBottomPIDCommand extends Command {

  private HangSubsystem hangSub;

  public ElevatorToBottomPIDCommand(HangSubsystem newElevSub) {

    hangSub = newElevSub;

    addRequirements(hangSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    hangSub.toBottomPID();
  }

  @Override
  public void end(boolean interrupted) {
    hangSub.elevStop();
  }

  @Override
  public boolean isFinished() {
    // Add limit switch condiiton
    return hangSub.isAtSetpoint();
  }
}
