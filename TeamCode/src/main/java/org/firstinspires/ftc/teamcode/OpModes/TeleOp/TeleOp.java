package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Mecanismos.Pinpoint;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Mecanum.MecanumDriveFieldOriented;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {
    MecanumDriveFieldOriented drive = new MecanumDriveFieldOriented();
    Pinpoint odo = new Pinpoint();
    Intake intake = new Intake();

    private Servo angulagemCapuz;


    @Override
    public void init() {
        drive.init(hardwareMap);
        intake.init(hardwareMap);
        odo.init(hardwareMap);
        angulagemCapuz = hardwareMap.get(Servo.class, "S1");
    }

    @Override
    public void start() {
        odo.start();
    }

    @Override
    public void loop() {

        odo.updatePinpoint();
        double forward = -gamepad1.left_stick_y;
        double turn = gamepad1.left_stick_x;
        double rotate = -gamepad1.right_stick_x;
        drive.driveFieldRelative(forward, turn, rotate);

        if(gamepad1.right_bumper){
            drive.switchZeroModeBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } else if (gamepad1.left_bumper) {
            drive.switchZeroModeBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }

        if(gamepad1.a){
            intake.ligarIntake();
        } else if (gamepad1.b) {
            intake.inverterIntake();
        } else{
            intake.desligarIntake();
        }


        odo.Telemetry(telemetry);
        odo.update(telemetry);
    }
}
