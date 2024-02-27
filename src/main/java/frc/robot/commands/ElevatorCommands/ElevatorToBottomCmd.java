package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorToBottomCmd extends Command {

  private ElevatorSubsystem elevSub;

  public ElevatorToBottomCmd(ElevatorSubsystem newElevSub) {

    elevSub = newElevSub;

    addRequirements(elevSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    // FIXME Change encoder setpoint for bottom
    elevSub.setSetpoint(0);
  }

  @Override
  public void end(boolean interrupted) {
    elevSub.elevStop();
  }

  @Override
  public boolean isFinished() {
    return elevSub.getBottomLimitSwitch() || elevSub.isAtSetpoint();
  }
}
