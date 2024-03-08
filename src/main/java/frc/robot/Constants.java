package frc.robot;

public final class Constants {
  public static class IntakeConstants {
    public static final int INTAKE_PORT = 9;
    
    public static final int INTAKE_OPTICAL_PORT = 1;

    public static final double INTAKE_MAXSPEED = 0.6;

    public static final int SMART_CURRENT_LIMIT = 20;
  }

  public static class HangConstants {
    public static final int HANG_MOTOR_PORT1 = 5;
    public static final int HANG_MOTOR_PORT2 = 8;

    public static final int TOP_MRS_PORT = 4;
    public static final int BOTTOM_MRS_PORT = 5;

    public static final double SPEED_CAP = 0.5;

    public static final double TOP_ENC_LIMIT = 50;
    public static final double BOTTOM_ENC_LIMIT = -50;

    public static final double HANG_KP = 0.01;
    public static final double HANG_KI = 0;
    public static final double HANG_KD = 0;

    public static final int SMART_CURRENT_LIMIT = 20;
    public static final int PID_TOLERANCE = 1;
  }

  public static class ElevatorConstants {
    public static final int ELEVATOR_MOTOR_PORT = 12;
    public static final int TOP_LS_PORT = 3;
    public static final int BOTTOM_LS_PORT = 0;

    //FIXME Up speed after testing
    public static final double SPEED_CAP = 0.7;

    public static final double ELEV_KP = 0.03;
    public static final double ELEV_KI = 0.001;
    public static final double ELEV_KD = 0;

    public static final int SMART_CURRENT_LIMIT = 20;
    public static final double PID_TOLERANCE = 2.5;
  }
}