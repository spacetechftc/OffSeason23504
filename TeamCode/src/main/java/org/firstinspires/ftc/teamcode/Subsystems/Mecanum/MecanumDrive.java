package org.firstinspires.ftc.teamcode.Subsystems.Mecanum;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class MecanumDrive {
    DcMotor back_left_motor, back_right_motor, front_left_motor, front_right_motor;
    GoBildaPinpointDriver odo;

    public void init(HardwareMap hwMap){
        back_left_motor = hwMap.get(DcMotor.class, "bl");
        back_right_motor = hwMap.get(DcMotor.class, "br");
        front_left_motor = hwMap.get(DcMotor.class, "fl");
        front_right_motor = hwMap.get(DcMotor.class, "fr");


        back_right_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back_left_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        front_left_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        front_right_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        back_left_motor.setDirection(DcMotor.Direction.FORWARD);
        back_right_motor.setDirection(DcMotor.Direction.FORWARD);
        front_left_motor.setDirection(DcMotor.Direction.FORWARD);
        front_right_motor.setDirection(DcMotor.Direction.FORWARD);

        back_right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        odo = hwMap.get(GoBildaPinpointDriver.class, "odo");
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.REVERSED);
        odo.setOffsets(-85, -90, DistanceUnit.MM);


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

    public void switchZeroModeBehavior(DcMotor.ZeroPowerBehavior zeroPower){
        back_right_motor.setZeroPowerBehavior(zeroPower);
        back_left_motor.setZeroPowerBehavior(zeroPower);
        front_left_motor.setZeroPowerBehavior(zeroPower);
        front_right_motor.setZeroPowerBehavior(zeroPower);
    }
}
