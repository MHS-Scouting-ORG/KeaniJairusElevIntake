package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorToBottomPIDCommand extends Command {

  private ElevatorSubsystem elevSub;

  public ElevatorToBottomPIDCommand(ElevatorSubsystem newElevSub) {

    elevSub = newElevSub;

    addRequirements(elevSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    elevSub.toBottomPID();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    // Add limit switch condiiton
    return elevSub.getEnc() < ElevatorConstants.BOTTOM_ENC_LIMIT;
  }
}
