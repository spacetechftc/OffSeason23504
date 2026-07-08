package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.extensions.pedro.PedroDriverControlled;
import dev.nextftc.ftc.ActiveOpMode;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.hardware.driving.DriverControlledCommand;
import dev.nextftc.hardware.impl.MotorEx;

public class MecanumDrive {

    private final MotorEx frontLeftMotor = new MotorEx("fl").reversed().brakeMode(); // Port 3
    private final MotorEx frontRightMotor = new MotorEx("fr").brakeMode(); // Port 0

    private final MotorEx backLeftMotor = new MotorEx("rl").brakeMode(); // Port 2
    private final MotorEx backRightMotor = new MotorEx("rr").reversed().brakeMode(); // Port 1

    public void start() {
        DriverControlledCommand driverControlled = new PedroDriverControlled(
                () -> (double) -ActiveOpMode.gamepad1().left_stick_y, // Forward e Backward
                () -> (double) -ActiveOpMode.gamepad1().left_stick_x, // Strafe
                () -> (double) -ActiveOpMode.gamepad1().right_stick_x, // Turn
                true
        );
        driverControlled.schedule();
    }

    public void driveRobotCentric(double forward, double strafe, double turn) {
        double fl = forward + strafe + turn;
        double bl = forward - strafe + turn;
        double fr = forward - strafe - turn;
        double br = forward + strafe - turn;

        double max = Math.max(1.0, Math.max(
                Math.abs(fl),
                Math.max(Math.abs(bl), Math.max(Math.abs(fr), Math.abs(br)))
        ));

        frontLeftMotor.setPower(fl / max);
        backLeftMotor.setPower(bl / max);
        frontRightMotor.setPower(fr / max);
        backRightMotor.setPower(br / max);
    }



    public void stop() {
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
    }

}
