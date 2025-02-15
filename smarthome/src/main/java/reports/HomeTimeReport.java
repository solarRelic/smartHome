package reports;

import home.Home;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Abstract class for generating reports to the command line or file.
 */
public abstract class HomeTimeReport {
    protected String path = "reports/";
    protected String name;

    public abstract void generateReport(Home home, int from, int to, PrintWriter writer);

    public void writeReportToFile(Home home, int from, int to, String name) {

        String fileName = path + name + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            generateReport(home, from, to, writer);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
