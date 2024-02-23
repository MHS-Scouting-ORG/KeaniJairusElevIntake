package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorStoragePositionCmd extends Command {
  
  public ElevatorSubsystem elevSub;

  //Runs the elevator to the position where we shoot from outside the wing

  public ElevatorStoragePositionCmd(ElevatorSubsystem newElevSub) {
    elevSub = newElevSub;
    addRequirements(elevSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    //FIXME Change the encoder setpoint for storage position
    elevSub.toSetpoint(0);
  }

  @Override
  public void end(boolean interrupted) {
    elevSub.elevStop();
  }

  @Override
  public boolean isFinished() {
    return elevSub.isAtSetpoint() || elevSub.getTopLimitSwitch();
  }
}
