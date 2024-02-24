package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorToAmpCmd extends Command {
  
  public ElevatorSubsystem elevSub;

  //Runs the elevator to the position where we shoot into the amp

  public ElevatorToAmpCmd(ElevatorSubsystem newElevSub) {
    elevSub = newElevSub;
    addRequirements(elevSub);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    //17 Inches from the ground
    //FIXME Change the encoder setpoint for the AMP position
    elevSub.setSetpoint(0);
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
