import home.Home;
import reports.ActivityAndUsageReport;
import reports.ConsumptionReport;
import reports.EventReport;
import reports.HomeConfigurationReport;

import java.io.PrintWriter;

public class Initialisation {
    private Home home;
    private static Initialisation instance;


    private Initialisation(Home home) {
        this.home = home;
    }

    public static Initialisation getInstance(Home home) {
        if (instance == null) {
            instance = new Initialisation(home);
        }
        return instance;
    }

    public void addHome(Home home) {
        this.home = home;
    }

    public void startSimulation() {
        for (int i = 0; i < 150; i++) {
            home.nextIteration();
        }
    }

    public void generateReports() {
        HomeConfigurationReport homeConfigurationReport = new HomeConfigurationReport();
        homeConfigurationReport.generateReport(home, 0, 150, new PrintWriter(System.out, true));
        homeConfigurationReport.writeReportToFile(home, 0, 150, "HomeConfigurationReport");

        EventReport eventReport = new EventReport();
        eventReport.generateReport(home, 0, 150, new PrintWriter(System.out, true));
        eventReport.writeReportToFile(home, 0, 150, "EventReport");

        ActivityAndUsageReport activityReport = new ActivityAndUsageReport();
        activityReport.generateReport(home,0, 150, new PrintWriter(System.out, true));
        activityReport.writeReportToFile(home, 0, 150, "ActivityReport");

        ConsumptionReport consumptionReport = new ConsumptionReport();
        consumptionReport.generateReport(home, 0, 150, new PrintWriter(System.out, true));
        consumptionReport.writeReportToFile(home, 0, 150, "ConsumptionReport");
    }
}


