package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorToTopPIDCommand extends Command {

  private ElevatorSubsystem elevSub;

  public ElevatorToTopPIDCommand(ElevatorSubsystem newElevSub) {

    elevSub = newElevSub;

    addRequirements(elevSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    elevSub.toTopPID();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    // Add limit switch condiiton
    return elevSub.getEnc() > ElevatorConstants.TOP_ENC_LIMIT;
  }
}
