import home.Home;

import java.util.Scanner;

public class SmartHome {

    public static void main(String[] args) {
        Home home;
        Config config = new Config();

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Large home");
        System.out.println("2. Small home");
        System.out.print("Please, enter the number of configuration: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            home = config.getConfig();
        } else if (choice == 2) {
            home = config.getConfig2();
        } else {
            System.out.println("Wrong number. Exiting...");
            return;
        }
        scanner.close();

        Initialisation init = Initialisation.getInstance(home);
        init.startSimulation();
        init.generateReports();
    }
}
