# Visma-Bootcamp-Elevator

### Author infromation
Martin Paľo, Visma-Bootcamp candidate. Primarily specializes on frontend but also opened to a full stack or other specializations.

### Description of solution
First things first I focused on movement of elevator (up and down) and then i applied my algorithm to transport people between the floors.

### Used technologies
As it is a logical problem assignment, I have decided to program it in Java. It was a nice memory of the first year of university where we were solving similar logical problems and algorithms right in Java. 

### Algorithm
First, the program reads the passengers from the input.
Then it will divide them into two lists according to whether they want to go upwards or downwards.
After sorting the lists, the algorithm decides whether to take passengers downwards or upwards first.
It depends on which of the very last passenger (first of people going downwards/upwards) is closer.
If the elevator unloaded all the passengers from input, it waits for another input of passengers.

### Compilation and debugging
- Make sure you have your JDK installed.
- Select your favourite IDE for Java. I recommend you IntelliJ or Eclipse.
- Clone my code into your IDE.
- Run the application.
- Insert your data this way:
- 1+
- 9-
- 5+
- 11-
- -1
- Number means floor passenger are calling elevator from.
- Sign (+/-) means whether he/she wants to go upwards or downwards.
- To end your input insert -1

 