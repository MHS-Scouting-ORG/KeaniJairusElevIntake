package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.UnderIntakeSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeCmd extends Command {

  private UnderIntakeSubsystem u_Subsystem;

  private boolean triggered;
  private boolean finished;

  private DoubleSupplier doubleSuppY;
  private DoubleSupplier doubleSuppX;

  private Timer timer;

  public IntakeCmd(UnderIntakeSubsystem uSubs, DoubleSupplier newDoubleSuppY, DoubleSupplier newDoubleSuppX) {
    u_Subsystem = uSubs;
    timer = new Timer();
    doubleSuppY = newDoubleSuppY;
    doubleSuppX = newDoubleSuppX;
    addRequirements(u_Subsystem);
  }

  @Override
  public void initialize() {
    triggered = false;
    finished = false;
    timer.stop();
    timer.reset();
  }

  @Override
  public void execute() {
    u_Subsystem.intake(Math.hypot(doubleSuppX.getAsDouble(), doubleSuppY.getAsDouble()));
    if (u_Subsystem.getOpticalSensor() && !triggered){
      triggered = true;
      timer.start();
    }
    SmartDashboard.putNumber("[I] Timer", timer.get());
    SmartDashboard.putNumber("[I] Speed", Math.hypot(doubleSuppX.getAsDouble(), doubleSuppY.getAsDouble()));
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
  }

  @Override
  public boolean isFinished() {
    return finished;
  }
}
