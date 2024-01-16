package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorToBottomCommand extends Command {

  private ElevatorSubsystem elevSub;

  // Command moves elevator to bottom until encoder value is met or limit swtich is pressed

  public ElevatorToBottomCommand(ElevatorSubsystem newElevSub) {
    
    elevSub = newElevSub;
    
    addRequirements(elevSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    elevSub.toBottom();
  }

  @Override
  public void end(boolean interrupted) {
    elevSub.elevStop();
  }

  @Override
  public boolean isFinished() {
    return elevSub.getBottomLimitSwitch();
  }
}
