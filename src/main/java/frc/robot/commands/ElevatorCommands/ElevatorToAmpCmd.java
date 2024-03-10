package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorToAmpCmd extends Command {

  private ElevatorSubsystem elevSub;

  public ElevatorToAmpCmd(ElevatorSubsystem newElevSub) {
    elevSub = newElevSub;
  
    addRequirements(elevSub);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    // Amp is 155
    elevSub.setSetpoint(155);
  }
 
  @Override
  public void end(boolean interrupted) {
    elevSub.elevStop();
  }  

  @Override
  public boolean isFinished() {
    return elevSub.getTopLimitSwitch() || elevSub.isAtSetpoint();
  }
}
