import java.util.*;

class ReservationSystem {
    static Scanner scanner = new Scanner(System.in);
    static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("----- Online Reservation System -----");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    loggedIn = login();
                    break;
                case 2:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("\n----- Reservation Menu -----");
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel a Reservation");
            System.out.println("3. View All Reservations");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    viewAllReservations();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    static boolean login() {
        System.out.println("\n----- Login -----");
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        // You can implement your own logic for authentication
        // For simplicity, let's consider a single hardcoded user
        if (username.equals("admin") && password.equals("admin123")) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }
    }

    static void makeReservation() {
        System.out.println("\n----- Make a Reservation -----");
        System.out.print("Enter train number: ");
        int trainNumber = scanner.nextInt();
        System.out.print("Enter class type: ");
        String classType = scanner.next();
        System.out.print("Enter date of journey (DD/MM/YYYY): ");
        String dateOfJourney = scanner.next();
        System.out.print("Enter from (place): ");
        String from = scanner.next();
        System.out.print("Enter to (destination): ");
        String to = scanner.next();

        Reservation reservation = new Reservation(trainNumber, classType, dateOfJourney, from, to);
        reservations.add(reservation);

        System.out.println("Reservation created successfully!");
    }

    static void cancelReservation() {
        System.out.println("\n----- Cancel a Reservation -----");
        System.out.print("Enter PNR number to cancel: ");
        int pnrNumber = scanner.nextInt();

        boolean found = false;
        for (Reservation reservation : reservations) {
            if (reservation.getPnrNumber() == pnrNumber) {
                System.out.println("Reservation Details:");
                System.out.println(reservation);
                System.out.print("Press 'OK' to confirm cancellation (any other key to cancel): ");
                String confirmation = scanner.next();
                if (confirmation.equalsIgnoreCase("OK")) {
                    reservations.remove(reservation);
                    System.out.println("Reservation cancelled successfully!");
                } else {
                    System.out.println("Cancellation aborted.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Reservation with PNR number " + pnrNumber + " not found.");
        }
    }

    static void viewAllReservations() {
        System.out.println("\n----- All Reservations -----");
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}

class Reservation {
    private static int nextPnrNumber = 1;
    private int pnrNumber;
    private int trainNumber;
    private String classType;
    private String dateOfJourney;
    private String from;
    private String to;

    public Reservation(int trainNumber, String classType, String dateOfJourney, String from, String to) {
        this.pnrNumber = nextPnrNumber++;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }

    public int getPnrNumber() {
        return pnrNumber;
    }

    @Override
    public String toString() {
        return "PNR: " + pnrNumber +
                ", Train Number: " + trainNumber +
                ", Class: " + classType +
                ", Date: " + dateOfJourney +
                ", From: " + from +
                ", To: " + to;
    }
}
