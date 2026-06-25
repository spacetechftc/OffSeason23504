package org.firstinspires.ftc.teamcode.Subsystems.Mecanum;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class MecanumDriveFieldOriented {
    DcMotor back_left_motor, back_right_motor, front_left_motor, front_right_motor;
    GoBildaPinpointDriver odo;


    private static final double XOFFSET_MM = 90;
    private static final double YOFFSET_MM = 80;

    public void init(HardwareMap hwMap){
        back_left_motor = hwMap.get(DcMotor.class, "back_left_motor");
        back_right_motor = hwMap.get(DcMotor.class, "back_right_motor");
        front_left_motor = hwMap.get(DcMotor.class, "front_left_motor");
        front_right_motor = hwMap.get(DcMotor.class, "front_right_motor");


        back_right_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back_left_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        front_left_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        front_right_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        back_right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        front_left_motor.setDirection(DcMotor.Direction.FORWARD);
        back_left_motor.setDirection(DcMotor.Direction.FORWARD);
        front_right_motor.setDirection(DcMotor.Direction.REVERSE);
        back_right_motor.setDirection(DcMotor.Direction.FORWARD);

        odo = hwMap.get(GoBildaPinpointDriver.class, "odo");
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.REVERSED);
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setOffsets(XOFFSET_MM, YOFFSET_MM, DistanceUnit.MM);


    }

    public void drive(double forward, double turn, double rotate){
        double flPower = forward + turn + rotate;
        double frPower = forward - turn - rotate;
        double brPower = forward + turn - rotate;
        double blPower = forward - turn + rotate;

        double maxPower = 1.0;
        double maxSpeed = 0.7;

        maxPower = Math.max(maxPower, Math.abs(flPower));
        maxPower = Math.max(maxPower, Math.abs(frPower));
        maxPower = Math.max(maxPower, Math.abs(brPower));
        maxPower = Math.max(maxPower, Math.abs(blPower));

        front_left_motor.setPower(maxSpeed * (flPower / maxPower));
        front_right_motor.setPower(maxSpeed * (frPower / maxPower));
        back_left_motor.setPower(maxSpeed * (blPower / maxPower));
        back_right_motor.setPower(maxSpeed * (brPower / maxPower));
    }

    public void driveFieldRelative(double forward, double right, double rotate) {
        // First, convert direction being asked to drive to polar coordinates
        double theta = Math.atan2(forward, right);
        double r = Math.hypot(right, forward);

        // Second, rotate angle by the angle the robot is pointing
        theta = AngleUnit.normalizeRadians(theta - odo.getHeading(AngleUnit.RADIANS));

        // Third, convert back to cartesian
        double newForward = r * Math.sin(theta);
        double newRight = r * Math.cos(theta);

        // Finally, call the drive method with robot relative forward and right amounts
        drive(newForward, newRight, rotate);
    }


    public void switchZeroModeBehavior(DcMotor.ZeroPowerBehavior zeroPower){
        back_right_motor.setZeroPowerBehavior(zeroPower);
        back_left_motor.setZeroPowerBehavior(zeroPower);
        front_left_motor.setZeroPowerBehavior(zeroPower);
        front_right_motor.setZeroPowerBehavior(zeroPower);
    }

}
