This is the <font color="red">documentation</font> regarding the present cycle of code. I realize that the current setup of the github documentation is inconvenient, but I would recommend forking this branch, and opening a repository with the aforementioned fork. Sorry.

This code is volatile, like my threads. It changes all time, because I have a habit of changing my mind. If you have suggestions, feel free to throw an issue on this branch, and I'll see it promptly. The code is not well commented, apart from hardware and teleop. This will be addressed when build season ends, but, given that I'm the only programmer, it's not a priority. 

In short, the code works thus:
1. Robot.java is called automatically by the DriverStation software. Robot.java calls Hardware.java to set up all the components, noting which components are unavailable. 

2. Robot.java is told to start teleop. It uses Input.java to choose the current teleop in action, and executes the corresponding teleop class. The classes are defined by a Abstract class in package.templates: TeleopProgram. The individual teleop classes are located in package.teleop. These classes, when executed, take controller input roughly ever 20 ms, and call predefined methods for each corresponding function. These functions could range from setting power, using Actions.java (explained later in this document), or alternative methods that will change as the drivers request additional features. 

3. Robot.java is told to start Autonomous. Like teleop, it is chosen by the Input.java object, using FMS and SmartDashboard input. FMS stands for Field Management System, and it provides the information regarding scale/switch ownership. The documentation can be found on the FIRST website. // insert link here // The autonomous class, extending AutoProgram, executes a predefined list of Actions using Sequence.java. The object of Sequence.java is commands, which executes the list of Actions added to it.

What are Actions?
They are fully autonomous methods of actuation, ranging from driving to vision-based clamping. They can be executed in teleop and autonomous, giving them additional versatality. In fact, the entire purpose of Actions was creating an iterative-command based robot, though I did not know the terminology at the time. Actions are great, powerful, buggy as hell, and the core of the code.

There are several other concepts that could be explained in the code for hours, but this is a brief summary of how code works. If you have questions, just ask me. There's nothing I enjoy more than showing off to people :)

I'm slightly kidding haha
