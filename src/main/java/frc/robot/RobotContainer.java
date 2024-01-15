package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ManualElevatorCommand;
import frc.robot.subsystems.ElevatorSubsystem;

public class RobotContainer {

  private final ElevatorSubsystem elevSub = new ElevatorSubsystem();

  private final XboxController xboxController = new XboxController(0);

  public RobotContainer() {
    elevSub.setDefaultCommand(new ManualElevatorCommand(elevSub, () -> xboxController.getRightY()));
    configureBindings();
  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
