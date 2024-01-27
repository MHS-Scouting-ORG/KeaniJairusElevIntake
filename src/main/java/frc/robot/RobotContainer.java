package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.commands.ElevatorCommands.ElevatorToBottomCommand;
import frc.robot.commands.ElevatorCommands.ElevatorToTopCommand;
import frc.robot.commands.ElevatorCommands.ManualElevatorCommand;
import frc.robot.commands.IntakeCommands.IntakeCmd;
import frc.robot.commands.IntakeCommands.OuttakeCmd;
import frc.robot.commands.IntakeCommands.PivotCommands.IntakePositionCmd;
import frc.robot.commands.IntakeCommands.PivotCommands.RestingPositionCmd;
import frc.robot.commands.IntakeCommands.PivotCommands.ManualIntakePivot;
import frc.robot.commands.IntakeCommands.PivotCommands.TransferPositionCmd;

public class RobotContainer {
  private final IntakeSubsystem i_subsystem = new IntakeSubsystem();
  private final ElevatorSubsystem elevSub = new ElevatorSubsystem();

  //private final XboxController xboxController = new XboxController(0);
  private final Joystick joystick = new Joystick(1);

  public RobotContainer() {
     i_subsystem.setDefaultCommand(new ManualIntakePivot(i_subsystem, () -> joystick.getY()));
    //elevSub.setDefaultCommand(new ManualElevatorCommand(elevSub, () -> joystick.getY()));

    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(joystick, 3).onTrue(new RestingPositionCmd(i_subsystem));
    new JoystickButton(joystick, 5).onTrue(new TransferPositionCmd(i_subsystem));
    new JoystickButton(joystick, 4).onTrue(new IntakePositionCmd(i_subsystem));

    // new JoystickButton(joystick,   6).onTrue(new ElevatorToTopCommand(elevSub));
    // new JoystickButton(joystick, 4).onTrue(new ElevatorToBottomCommand(elevSub));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
