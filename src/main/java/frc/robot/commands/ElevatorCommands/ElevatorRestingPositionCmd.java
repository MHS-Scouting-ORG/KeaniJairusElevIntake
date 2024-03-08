package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorRestingPositionCmd extends Command {

  private ElevatorSubsystem elevSub;

  public ElevatorRestingPositionCmd(ElevatorSubsystem newElevSub) {

    elevSub = newElevSub;

    addRequirements(elevSub);
  }

  @Override
  public void initialize() {
    elevSub.enablePID();
  }

  @Override
  public void execute() {
    //Bottom/Resting is 0
    elevSub.setSetpoint(-1);
  }

  @Override
  public void end(boolean interrupted) {
    elevSub.disablePID();
    elevSub.elevStop();
  }

  @Override
  public boolean isFinished() {
    return elevSub.getBottomLimitSwitch() || elevSub.isAtSetpoint();
  }
}
