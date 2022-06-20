import java.util.Scanner;

public class Elevator {

    Scanner sc = new Scanner(System.in);
    final int highestFloor = 10;
    final int lowestFloor = 1;
    int currentFloor = 0;
    int desiredFloor = 0;


    void display(Object o) {
        System.out.println(o);
    }

    void ask(Object o) {
        System.out.println(o);
    }

    void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    void goDown() {
        while (currentFloor-- > desiredFloor) {
            display("going down... current floor: " + currentFloor + " | DF: " + desiredFloor);
            delay(600);
        }
        currentFloor++;
    }

    void goUp() {
        while (currentFloor++ < desiredFloor) {
            display("going up... current floor: " + currentFloor + " | DF: " + desiredFloor);
            delay(600);
        }
        currentFloor--;
    }

    void ask() {
        ask("Current floor: " + currentFloor + " | Enter destination floor: ");
        desiredFloor = sc.nextInt();
        if (desiredFloor < lowestFloor || desiredFloor > highestFloor || desiredFloor == currentFloor) {
            display("error, floor out of range or selected floor is current floor.");
            ask();
        } else {
            display("door closing...");
            delay(600);
            if (desiredFloor < currentFloor) {
                goDown();
            } else if (desiredFloor > currentFloor) {
                goUp();

            }
        }
        display("door opening...");
        ask();
    }


    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        elevator.ask();
    }

}
