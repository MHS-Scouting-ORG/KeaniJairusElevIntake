package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.UnderIntakeSubsystem;
import edu.wpi.first.wpilibj.Timer;

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
    //u_Subsystem.toEncoder(1.5);
    while(u_Subsystem.getOpticalSensor()){
      u_Subsystem.intake();
    }
    Timer.delay(0.05);
    u_Subsystem.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return u_Subsystem.getOpticalSensor();
  }
}
