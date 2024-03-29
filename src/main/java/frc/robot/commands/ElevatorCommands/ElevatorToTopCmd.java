package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorToTopCmd extends Command {

  private ElevatorSubsystem elevSub;

  public ElevatorToTopCmd(ElevatorSubsystem newElevSub) {
    elevSub = newElevSub;
  
    addRequirements(elevSub);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    //Hard Top is 126
    // Top/Amp is 118
    elevSub.setSetpoint(118);
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
