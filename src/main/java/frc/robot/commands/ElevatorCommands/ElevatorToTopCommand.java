package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorToTopCommand extends Command {

  private ElevatorSubsystem elevSub;

  // Command moves elevator to top until encoder value is met or limit swtich is pressed

  public ElevatorToTopCommand(ElevatorSubsystem newElevSub) {
    
    elevSub = newElevSub;
    
    addRequirements(elevSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    elevSub.toTop();
  }

  @Override
  public void end(boolean interrupted) {
    elevSub.elevStop();
  }

  @Override
  public boolean isFinished() {
    // Add limit switch condiiton
    return elevSub.getEnc() > ElevatorConstants.TOP_ENC_LIMIT;
  }
}
