package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotorEx motor_intake;

    public void init(HardwareMap hwMap){
        motor_intake = hwMap.get(DcMotorEx.class, "motor_intake");
        motor_intake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motor_intake.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor_intake.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

    }

    public static final double VELOCIDADE_MAXIMA = 2700;

    public void ligarIntake() {
        motor_intake.setVelocity(VELOCIDADE_MAXIMA);
    }

    public void desligarIntake(){
        motor_intake.setVelocity(0);
    }

    public void inverterIntake(){
        motor_intake.setVelocity(-VELOCIDADE_MAXIMA);
    }


}