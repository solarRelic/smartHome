package reports;

import events.*;
import home.Home;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Generates an event report for a specified range of iterations.
 */
public class EventReport extends HomeTimeReport{
	static final  String FILE_NAME = "EventReport";

	public EventReport() {
		super.name = FILE_NAME;
	}

	@Override
	public void generateReport(Home home, int from, int to, PrintWriter writer) {
		List<Event> allEvents = home
				.getEventPublisher()
				.getEvents()
				.stream()
				.filter(event -> event.getIteration() >= from && event.getIteration() < to)
				.collect(Collectors.toList());

		writer.println("\t\t\t\t\t\t\t\tEventReport from iteration " + from + " to " + to + ":");
		writer.println("\t\t\t\t Grouping by type");
		ArrayList<ReportInfo> reportInfos = (ArrayList<ReportInfo>) allEvents.stream()
				.filter(ReportInfo.class::isInstance)
				.map(ReportInfo.class::cast)
				.collect(Collectors.toList());

		Map<EventType, List<ReportInfo>> reportMap = reportInfos.stream()
				.collect(Collectors.groupingBy(ReportInfo::getType));

		for (Map.Entry<EventType, List<ReportInfo>> entry : reportMap.entrySet()) {
			for (ReportInfo reportInfo : entry.getValue()) {
				writer.println("\t" + reportInfo.toString());
			}
		}
	}
}