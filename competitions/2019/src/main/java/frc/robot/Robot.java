/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.RobotConstants.WheelConstants;
import frc.robot.RobotConstants.BallCollectorConstants;
import frc.robot.RobotConstants.HatchCollectorConstants;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.cscore.UsbCamera;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive rbt;
  private Joystick logitechCtlr;
  private Joystick logitechCtlr2;
  private Spark ballCollectorCtrl;
  private Spark diskCollectorCtrl;

  @Override
  public void robotInit() {
    System.out.println("[robotInit()]");

    // UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(1);
    CameraServer.getInstance().startAutomaticCapture();

    // initialize drive speed controllers for the 4 wheels.
    // The numbers 0-3 tie the Spark controllers to the 
    // Engines that are wired to the RoboRio controller.  

    // The RoboRio will route signals to a specific Spark engine, according to the assigned number.
    Spark leftDrive1 = new Spark(0);  // This tells the Spark controller to route signals to Engine 0.
    Spark leftDrive2 = new Spark(1);
    Spark rightDrive1 = new Spark(2);
    Spark rightDrive2 = new Spark(3);
    
    // enable dead band protection on all drive motors
    // leftDrive1.enableDeadbandElimination(true);
    // leftDrive2.enableDeadbandElimination(true);
    // rightDrive1.enableDeadbandElimination(true);
    // rightDrive2.enableDeadbandElimination(true);
    
    // Create a group for the left side
    // This allows all left-side engines to get the same forward/reverse commands.
    SpeedControllerGroup leftDriveCtrls = new SpeedControllerGroup(leftDrive1, leftDrive2);

    // Create a group for the right side
    // This allows all right-side engines to get the same forward/reverse commands.
    SpeedControllerGroup rightDriveCtrls = new SpeedControllerGroup(rightDrive1, rightDrive2);

    // Define the drive train:
    // the DifferentialDrive controller will route Joystick signals to the left and right wheels
    // defined in leftDriveCtrls and rightDriveCtrls
    rbt = new DifferentialDrive(leftDriveCtrls, rightDriveCtrls);

    // Instantiate the controller for Spark Engine #4. This is the Disk Collector Engine 
    // This will send signals to port 4 of the RoboRIO controller.
    diskCollectorCtrl = new Spark(4);


    // Instantiate the controller for Spark Engine #5. This is the Ball Collector Engine 
    ballCollectorCtrl = new Spark(5);
    
    // Instantiate the controller for the logitech Joystick plugged into USB Port 0 on the computer.
    logitechCtlr = new Joystick(0);

    // Instantiate the controller for the logitech Joystick plugged into USB Port 1 on the computer.
    logitechCtlr2 = new Joystick(1);
  }

  @Override
  public void autonomousInit() {
    System.out.println("Autonomous On");
  }

  /*
    Test all of the buttons and axises and report which ones are pressed.
    This was used to verify the numbers to use in the Button.java and Axis.java files.
    It could be used to test whether the robot is getting signals from the joysticks as expected.  
  */
  private void testController(Joystick controller, int controlerNumber){
    for (int button=1; button<11; button++){

      try{
        if (controller.getRawButton(button)){
          System.out.println("Controller " + controlerNumber + ", Button # " + button + " pressed");}
      }
      catch(Exception e) {
        System.out.println("FAIL ON Controller " + controlerNumber + ", Button # " + button);
      }

    }

    for (int axis=0; axis<6; axis++){
      try{
        double signal = controller.getRawAxis(axis);
        if (signal > 0.1  || signal < -0.1){
          System.out.println("Controller " + controlerNumber + ", Axis # " + axis + " = " + signal);
        }
      }
      catch(Exception e) {
        System.out.println("FAIL ON Controller " + controlerNumber + ", Axis # " + axis);
      }
    }

  }

  @Override
  public void autonomousPeriodic() {
    //System.out.println("[teleopPeriodic] L: " + logitechCtlr.getRawAxis(1) + " R: " + logitechCtlr.getRawAxis(5));
    //rbt.tankDrive(-0.65 * logitechCtlr.getRawAxis(1), -0.65 * logitechCtlr.getRawAxis(5));

    //Uncomment this code to test the controllers output.
    this.testController(this.logitechCtlr, 1);    
    this.testController(this.logitechCtlr2, 2);

  }


  private Joystick getController(int controllerConstant){
    /*
    Return controller 1 or 2, depending on the constant sent in.
    */
    if (controllerConstant==1){
      return this.logitechCtlr;
      }
    else {
      return this.logitechCtlr2;
      }
    }

  private void moveWheels() {
    double wheelConversionFactor = WheelConstants.speed * WheelConstants.direction;
    
    Joystick controller = this.getController(WheelConstants.controller);
 
    double leftSignal = wheelConversionFactor * controller.getRawAxis(WheelConstants.leftControllerAxis);
    double rightSignal = wheelConversionFactor * controller.getRawAxis(WheelConstants.rightControllerAxis);

    rbt.tankDrive(leftSignal, rightSignal);
  }

  public void teleopPeriodic() {
   
    //Uncomment this code to test the controllers output.
    //this.testController(this.logitechCtlr, 1);    
    //this.testController(this.logitechCtlr2, 2);
    
    this.moveWheels();

    this.moveBallCollector();

    this.moveHatchCollector();
  }

  private void moveBallCollector(){

      Joystick controller = this.getController(BallCollectorConstants.controller);

      boolean in = false;
      if (controller.getRawAxis(BallCollectorConstants.rightControllerAxis) > 0.5){
        in = true;}

      boolean out = false;
      if (controller.getRawAxis(BallCollectorConstants.leftControllerAxis) > 0.5){
        out = true;}

      double signal = BallCollectorConstants.speed * BallCollectorConstants.direction;

      
      if (in) {
        ballCollectorCtrl.set(-signal); // PWM value between -1.0, 1.0
        System.out.println("[button X] ball collector in");
      }
  
      // if A is pressed, run the ball collector backwardss
      else if (out) {
        ballCollectorCtrl.set(signal); // PWM value between -1.0, 1.0
        System.out.println("[button A] ball collector out");
      }
  
      else {
        ballCollectorCtrl.set(0.0); // PWM value between -1.0, 1.0
      }

  }


  private void moveHatchCollector(){
      Joystick controller = this.getController(HatchCollectorConstants.controller);

      boolean up = controller.getRawButton(HatchCollectorConstants.upControllerButton);
      boolean down = controller.getRawButton(HatchCollectorConstants.downControllerButton);

      // if X is pressed, run the ball collector forwards
      if (up) {
        diskCollectorCtrl.set(1.0); // PWM value between -1.0, 1.0
        System.out.println("[button Y] ball collector in");
      }

      // if A is pressed, run the ball collector backwardss
      else if (down) {
        diskCollectorCtrl.set(-1.0); // PWM value between -1.0, 1.0
        System.out.println("[button B] ball collector out");
      }

      else {
        diskCollectorCtrl.set(0.0); // PWM value between -1.0, 1.0
      }


    }


}