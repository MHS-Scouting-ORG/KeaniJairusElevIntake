package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.UnderIntakeSubsystem;

public class OuttakeCmd extends Command {

  private UnderIntakeSubsystem u_subsystem;

  //Runs the under the bumper intake to "outtake" notes
  
  public OuttakeCmd(UnderIntakeSubsystem uSubs) {
    u_subsystem = uSubs;
    addRequirements(u_subsystem);
  }
  
  @Override  
  public void initialize() {

  }

  @Override
  public void execute() {
    // FIXME Add double param
    //u_subsystem.outtake();
  }

  @Override
  public void end(boolean interrupted) {
    u_subsystem.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
