package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.UnderIntakeSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeCmd extends Command {

  private UnderIntakeSubsystem u_Subsystem;

  private boolean triggered;
  private boolean finished;

  private Timer timer;

  public IntakeCmd(UnderIntakeSubsystem uSubs) {
    u_Subsystem = uSubs;
    triggered = false;
    finished = false;
    timer = new Timer();
    addRequirements(u_Subsystem);
  }

  @Override
  public void initialize() {
    u_Subsystem.intake();
    triggered = false;
    finished = false;
    timer.stop();
    timer.reset();
  }

  @Override
  public void execute() {
    if (u_Subsystem.getOpticalSensor() && !triggered){
      triggered = true;
      timer.start();
    }
    SmartDashboard.putNumber("[I] Timer", timer.get());
    if (triggered && timer.get() > 0.04){
      finished = true;
    }

  }
  
  @Override
  public void end(boolean interrupted) {
    // FIXME Fix encoder count with actual intake
    //u_Subsystem.toEncoder(1.5);
    u_Subsystem.stopIntake();
    timer.stop();
    timer.reset();
  }

  @Override
  public boolean isFinished() {
    return finished;
  }
}
