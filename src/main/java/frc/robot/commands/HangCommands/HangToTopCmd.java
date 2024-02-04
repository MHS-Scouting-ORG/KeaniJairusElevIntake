package frc.robot.commands.HangCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.HangConstants;
import frc.robot.subsystems.HangSubsystem;

public class HangToTopCmd extends Command {

  private HangSubsystem hangSub;

  // Command moves hangator to top until encoder value is met or limit swtich is pressed

  public HangToTopCmd(HangSubsystem newHangSub) {
    
    hangSub = newHangSub;
    
    addRequirements(hangSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    hangSub.toTop();
  }

  @Override
  public void end(boolean interrupted) {
    hangSub.elevStop();
  }

  @Override
  public boolean isFinished() {
    // Add limit switch condiiton
    return hangSub.getEnc() > HangConstants.TOP_ENC_LIMIT || hangSub.getTopLimitSwitch();
  }
}
