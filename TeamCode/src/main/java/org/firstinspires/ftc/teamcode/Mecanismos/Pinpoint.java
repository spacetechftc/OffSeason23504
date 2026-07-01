package org.firstinspires.ftc.teamcode.Mecanismos;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Pinpoint {
    GoBildaPinpointDriver odo;

    public void init(HardwareMap hwMap){
        odo = hwMap.get(GoBildaPinpointDriver.class, "pinpoint");
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.REVERSED);
        odo.setOffsets(-85, -90, DistanceUnit.MM);
    }

    public void start(){
        odo.resetPosAndIMU();
    }
    public double getX(){
       return odo.getPosX(DistanceUnit.CM);
    }

    public double getY(){
        return odo.getPosY(DistanceUnit.CM);
    }

    public double getHeading(){
        return odo.getHeading(AngleUnit.DEGREES);
    }

    public String getStatus() {
        return odo.getDeviceStatus().toString();
    }

    public void update(Telemetry telemetry){
        telemetry.update();
    }


    public void updatePinpoint(){
        odo.update();
    }


}
