package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.ManualElevatorIntakeCmd;

public class RobotContainer {
  private final IntakeSubsystem i_subsystem = new IntakeSubsystem();

  private final XboxController xboxController = new XboxController(0);

  public RobotContainer() {
    i_subsystem.setDefaultCommand(new ManualElevatorIntakeCmd(i_subsystem, () -> xboxController.getLeftY()));
    configureBindings();
  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
