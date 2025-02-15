package reports;

import devices.Device;
import devices.equipment.Equipment;
import events.Event;
import events.EventType;
import events.ReportInfo;
import home.Home;
import resident.pet.Pet;
import resident.Resident;
import resident.human.Human;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Generates an activity and usage report for a specified range of iterations.
 */
public class ActivityAndUsageReport extends HomeTimeReport{

    /**
     *  The file name for the generated report.
     */
    static final  String FILE_NAME = "ActivityAndUsageReport";

    /**
     * Generates the activity and usage report for the specified home, iteration range, and writes it.
     * @param home  The home for which the report is generated.
     * @param from The starting iteration for the report.
     * @param to The ending iteration  for the report.
     * @param writer The PrintWriter to write the report to.
     */

    @Override
    public void generateReport(Home home, int from, int to, PrintWriter writer) {
        List<Event> allEvents = home
                .getEventPublisher()
                .getEvents()
                .stream()
                .filter(event -> event.getIteration() >= from && event.getIteration() < to)
                .toList();

        writer.println("\t\t\t\t\t\t\t\tActivity and usage report from iteration " + from + " to " + to + ":");
        List<ReportInfo> reportInfos = (ArrayList<ReportInfo>) allEvents.stream()
                .filter(ReportInfo.class::isInstance)
                .map(ReportInfo.class::cast)
                .filter(repInfo -> repInfo.getSource() instanceof Resident)
                .filter(repInfo -> repInfo.getType().equals(EventType.DEVICE_USAGE))
                .sorted(Comparator.comparing((ReportInfo repInfo) -> repInfo.getSource().toString())
                        .thenComparing(repInfo -> repInfo.getTarget().toString()))
                .collect(toList());

        if(!reportInfos.isEmpty()) {
            writer.println("\t\t\t\t\tUsages");

            Resident user = (Resident) reportInfos.get(0).getSource();
            Device targetDevice = (Device) reportInfos.get(0).getTarget();
            int count = 0;

            writer.println("\t\tPerson: " + user.toString());
            for (ReportInfo info : reportInfos) {
                if (info.getSource() != user) {
                    writer.println("\t\t\tUsed device: " + targetDevice.toString() + count + " times");
                    targetDevice = (Device) info.getTarget();
                    user = (Resident) info.getSource();
                    writer.println("Person: " + user.toString());
                    count = 0;
                }
                if (info.getTarget() != targetDevice) {
                    writer.println("\t\t\tUsed device: " + targetDevice.toString() + " " + count + " times");
                    targetDevice = (Device) info.getTarget();
                    count = 1;
                } else  count++;
            }
            writer.println("\t\t\tUsed device: " + targetDevice.toString() + " " + count + " times");
        }
        ArrayList<ReportInfo> petActivity = (ArrayList<ReportInfo>) allEvents.stream()
                .filter(ReportInfo.class::isInstance)
                .map(ReportInfo.class::cast)
                .filter(activity -> activity.getSource() instanceof Pet)
                .sorted(Comparator.comparing((ReportInfo rep) ->rep.getSource().toString()))
                .collect(toList());
        if (!petActivity.isEmpty()) {
            writer.println("\t\t\t\t\tPet activities");
            Pet user = (Pet) petActivity.get(0).getSource();
            EventType eventType = petActivity.get(0).getType();
            int count = 0;

            writer.println("\t\tPet: " + user.toString());
            for (ReportInfo reportInfo : petActivity) {
                if (reportInfo.getSource() != user) {
                    writer.println("\t\t\tDid: " + eventType.toString() + " " + count + " times");
                    eventType = reportInfo.getType();
                    user = (Pet) reportInfo.getSource();
                    writer.println("Pet: " + user.toString());
                    count = 0;
                }
                if (reportInfo.getType() != eventType) {
                    writer.println("\t\t\tDid: " + eventType.toString() + " " + count + " times");
                    eventType = reportInfo.getType();
                    count = 1;
                } else count++;
            }
            writer.println("\t\t\tDid: " + eventType.toString() + " " + count+ " times");
        }

        ArrayList<ReportInfo> sportInfos = (ArrayList<ReportInfo>) allEvents.stream()
                .filter(ReportInfo.class::isInstance)
                .map(ReportInfo.class::cast)
                .filter(repInfo -> repInfo.getSource() instanceof Human)
                .filter(repInfo -> repInfo.getType().equals(EventType.EQUIPMENT_USAGE))
                .sorted(Comparator.comparing((ReportInfo repInfo) -> repInfo.getSource().toString())
                        .thenComparing(repInfo -> repInfo.getTarget().toString()))
                .collect(toList());

        if(!sportInfos.isEmpty()) {
            writer.println("\t\t\t\t\tOutdoor activity");

            Resident user = (Resident) sportInfos.get(0).getSource();
            Equipment equipment = (Equipment) sportInfos.get(0).getTarget();
            int count = 0;

            writer.println("\t\tPerson: " + user.toString());
            for (ReportInfo info : sportInfos) {
                if (info.getSource() != user) {
                    writer.println("\t\t\tUsed equipment: " + equipment.toString() + count + " times");
                    equipment = (Equipment) info.getTarget();
                    user = (Resident) info.getSource();
                    writer.println("Person: " + user.toString());
                    count = 0;
                }
                if (info.getTarget() != equipment) {
                    writer.println("\t\t\tUsed equipment: " + equipment.toString() + " " + count + " times");
                    equipment = (Equipment) info.getTarget();
                    count = 1;
                } else  count++;
            }
            writer.println("\t\t\tUsed equipment: " + equipment.toString() + " " + count + " times");
        }
    }
}