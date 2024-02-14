package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.UnderIntakeSubsystem;

public class IntakeCmd extends Command {

  private UnderIntakeSubsystem u_Subsystem;

  //Runs the under the bumper intake to "intake" notes

  public IntakeCmd(UnderIntakeSubsystem uSubs) {
    u_Subsystem = uSubs;
    addRequirements(u_Subsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    u_Subsystem.intake();
  }
  
  @Override
  public void end(boolean interrupted) {
    // FIXME Fix encoder count with actual intake
    u_Subsystem.toEncoder(3.8);
  }

  @Override
  public boolean isFinished() {
    return u_Subsystem.getOpticalSensor();
  }
}
