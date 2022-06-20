import java.util.*;

public class VismaBootcampElevator {

    Scanner scanner = new Scanner(System.in);

    final int maxFloor = 12;
    final int minFloor = 0;
    int currentFloor = 5;
    int elevatorState = 0;
    boolean fix = false;

    List<Integer> downwards = new ArrayList<Integer>();
    List<Integer> upwards = new ArrayList<Integer>();
    List<Integer> finalDestinations = new ArrayList<Integer>();

    void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void goUp() {
        if (currentFloor + 1 <= maxFloor) {
            currentFloor++;
            System.out.println("Current floor: " + currentFloor);
            delay(1000);

        } else {
            System.err.println("Floor out of range!");
        }
    }

    void goDown() {
        if (currentFloor - 1 >= minFloor) {
            currentFloor--;
            System.out.println("Current floor: " + currentFloor);
            delay(1000);

        } else {
            System.err.println("Floor out of range!");
        }
    }

    private String removeLastChar(String s) {
        return s.substring(0, s.length() - 1);
    }

    private boolean check(String s) {
        char sign = lastChar(s);
        String cislo = s.substring(0, s.length() - 1);
        if (cislo.equals("12") && sign == '+' || cislo.equals("0") && sign == '-') {
            System.err.println("Please select valid input");
            return false;
        }
        return true;
    }

    private char lastChar(String s) {
        return s.charAt(s.length() - 1);
    }


    private void getData() {
        System.out.println("Insert data");
        while (true) {
            String a = scanner.next();
            if (a.equals("-1")) {
//                System.out.println("Upwards: " + upwards);
//                System.out.println("Downwards: " + downwards);
                break;
            } else {
                if (check(a)) {
                    char direction = lastChar(a);
//                System.out.println(direction);
                    a = removeLastChar(a);
                    int floor = Integer.parseInt(a);
                    if (direction == '+' && !upwards.contains(floor)) {
                        upwards.add(floor);
                    } else if (direction == '-' && !downwards.contains(floor)) {
                        downwards.add(floor);
                    }
                }
//                else{
//                    System.out.println("Invalid input, please try again.");
//                }
            }

        }
        Collections.sort(upwards);
        Collections.sort(downwards, Collections.reverseOrder());
    }

    void goDownwards() {
        System.out.println("Current floor: " + currentFloor);
        delay(1000);
        while (elevatorState == -1) {
            if (finalDestinations.contains(currentFloor)) {
                System.out.println("Doors are opening... You can leave.");
                delay(600);
//                    https://www.techiedelight.com/remove-all-occurrences-element-from-list-java/
                finalDestinations.removeAll(Collections.singleton(currentFloor));
            }
            if (!downwards.isEmpty()) {
//                if (downwards.get(0) > currentFloor) {
//                    goUp();
//                    fix = false;
//                } else {
                    if (currentFloor == downwards.get(0)) {
                        fix = true;
                        System.out.println("Doors are opening... Enter please.");
                        delay(600);
                        System.out.println("Select your final destination: ");
                        while (true) {
                            int number = scanner.nextInt();
                            if (number >= currentFloor || number < minFloor) {
                                System.err.println("You have to select lower number!");
                            } else {
                                finalDestinations.add(number);
                                break;
                            }
                        }
                        System.out.println("Doors are closing... Hold your hats.");
                        delay(600);
                        downwards.remove(0);
//                    System.out.println("List of downwards:  " + downwards);
                    }
//                }
            }
            if (downwards.isEmpty() && finalDestinations.isEmpty()) {
                if (currentFloor > upwards.get(0)) {
                    goDown();
                    fix = true;
                } else if (currentFloor < upwards.get(0)) {
                    goUp();
                    fix = true;
                } else {
                    elevatorState = 1;
                    fix = false;
                    break;
                }
            }
            if (!fix) goDown();
        }
        System.out.println("Elevator state: " + elevatorState);
//            System.out.println(finalDestinations);
    }

    void goUpwards() {
        System.out.println("Current floor: " + currentFloor);
        delay(1000);
        while (elevatorState == 1) {
            if (finalDestinations.contains(currentFloor)) {
                System.out.println("Doors are opening... You can leave.");
                delay(600);
//                    https://www.techiedelight.com/remove-all-occurrences-element-from-list-java/
                finalDestinations.removeAll(Collections.singleton(currentFloor));
            }
            if (!upwards.isEmpty()) {
//                if (upwards.get(0) < currentFloor) {
//                    goDown();
//                    fix = false;
//                } else {
                    if (currentFloor == upwards.get(0)) {
                        System.out.println("Doors are opening... Enter please.");
                        delay(600);
                        System.out.println("Select your final destination: ");
                        while (true) {
                            int number = scanner.nextInt();
                            if (number <= currentFloor || number > maxFloor) {
                                System.err.println("You have to select higher number!");
                            } else {
                                finalDestinations.add(number);
                                break;
                            }
                        }
                        System.out.println("Doors are closing... Hold your hats.");
                        delay(600);
                        upwards.remove(0);
//                    System.out.println("List of downwards:  " + downwards);
                    }
//                }
            }
            if (upwards.isEmpty() && finalDestinations.isEmpty()) {
                if (currentFloor < downwards.get(0)) {
                    goUp();
                    fix = true;
                } else if (currentFloor > downwards.get(0)) {
                    goDown();
                    fix = true;
                } else {
                    elevatorState = -1;
                    fix = false;
                    break;
                }

            }
            if (!fix) goUp();
        }
        System.out.println("Elevator state: " + elevatorState);
    }

    void run() {
        getData();
        System.out.println("Upwards: " + upwards);
        System.out.println("Downwards: " + downwards);

        int distanceToUpwards = Math.abs(currentFloor - upwards.get(0));
        int distanceToDownwards = Math.abs(currentFloor - downwards.get(0));

        System.out.println(distanceToDownwards);
        System.out.println(distanceToUpwards);

        if (distanceToDownwards < distanceToUpwards) {
            elevatorState = -1;
            goDownwards();
            goUpwards();
        } else {
            elevatorState = 1;
            goUpwards();
            goDownwards();
        }

//        if (elevatorState == -1) {
//            goDownwards();
//            goUpwards();
//        }
//        if (elevatorState == 1) {
//            goUpwards();
//            goDownwards();
//        }


//        System.out.println(distanceToUpwards);
//        System.out.println(distanceToDownwards);

        elevatorState = 0;
        finalDestinations.clear();
        upwards.clear();
        downwards.clear();
        System.out.println("Current floor: " + currentFloor);
        run();
    }

    public static void main(String[] args) {
        VismaBootcampElevator elevator = new VismaBootcampElevator();
        elevator.run();
    }
}
