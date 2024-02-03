package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.HangConstants;
import frc.robot.subsystems.HangSubsystem;

public class ElevatorToBottomCommand extends Command {

  private HangSubsystem hangSub;

  // Command moves hangator to bottom until encoder value is met or limit swtich is pressed

  public ElevatorToBottomCommand(HangSubsystem newElevSub) {
    
    hangSub = newElevSub;
    
    addRequirements(hangSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    hangSub.toBottom();
  }

  @Override
  public void end(boolean interrupted) {
    hangSub.elevStop();
  }

  @Override
  public boolean isFinished() {
    // Add limit switch condiiton
    return hangSub.getEnc() < HangConstants.BOTTOM_ENC_LIMIT;
  }
}
