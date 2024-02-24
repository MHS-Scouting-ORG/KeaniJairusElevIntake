package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorToSourceCmd extends Command {

  public ElevatorSubsystem elevSub;

  //Runs the elevator to the position where we intake from the source through the shooter

  public ElevatorToSourceCmd(ElevatorSubsystem newElevSub) {
    elevSub = newElevSub;
    addRequirements(elevSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    //Default Position?? || ??
    //FIXME Change the encoder setpoint for the Source position
    elevSub.setSetpoint(0);
  }

  @Override
  public void end(boolean interrupted) {
    elevSub.elevStop();
  }

  @Override
  public boolean isFinished() {
    //Or elevSub.getBottomLimitSwitch if its the default position
    return elevSub.isAtSetpoint() || elevSub.getTopLimitSwitch();
  }
}
