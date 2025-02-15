package reports;

import devices.Device;
import devices.TypesOfConsumption;
import events.Consumption;
import events.Event;
import home.Home;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generates a consumption report for a specified range of iterations.
 */
public class ConsumptionReport extends HomeTimeReport{
	static final  String FILENAME = "ConsumptionReport";
	private static final int ELECTRICITY_PRICE = 4;
	private static final int WATER_PRICE = 66;

	private double calculatePrice(double amount, TypesOfConsumption type) {
		if (type.equals(TypesOfConsumption.ELECTRICITY)) {
			return Math.round((amount * ELECTRICITY_PRICE) * 100) / 100.0 ;
		} else if (type.equals(TypesOfConsumption.WATER)) {
			return Math.round((amount * WATER_PRICE) * 100) / 100.0 ;

		}
		return 0;
	}


	@Override
	public void generateReport(Home home, int from, int to, PrintWriter writer) {
		writer.println("\t\t\t\t\t\t\t\t\t\tConsumption report");
		List<Event> events = home.getEventPublisher().getEvents();
		ArrayList<Consumption> consumptions = (ArrayList<Consumption>) events.stream()
				.filter(Consumption.class::isInstance)
				.map(Consumption.class::cast)
				.filter(consumption -> consumption.getType() == TypesOfConsumption.ELECTRICITY ||
						consumption.getType() == TypesOfConsumption.WATER)
				.filter(consumption -> consumption.getIteration() >= from && consumption.getIteration() < to)
				.sorted(Comparator.comparing((Consumption consumption) -> consumption.getSource().toString())
						.thenComparing(Consumption::getType))
				.collect(Collectors.toList());
		if (!consumptions.isEmpty()) {
			Device device = (Device) consumptions.get(0).getSource();
			TypesOfConsumption type = consumptions.get(0).getType();
			double counter = 0;
			double totalElecricity = 0;
			double totalWater = 0;

			writer.println("\tDevice: " + device.toString());
			for (Consumption consumption : consumptions) {
				if (consumption.getSource() != device) {
					writer.println("\t\tWas consumed " + String.format("%.2f" , counter) + " of " + type + ". Total price: " + calculatePrice(counter, type));
					type = consumption.getType();
					device = (Device) consumption.getSource();
					writer.println("\tDevice: " + device.toString());
					if (type.equals(TypesOfConsumption.ELECTRICITY)) {
						totalElecricity += counter;
						continue;
					} else if (type.equals(TypesOfConsumption.WATER)) {
						totalWater += counter;
						continue;
					}
					counter = 0;
				}
				if (consumption.getType() != type) {
					writer.println("\t\tWas consumed " + String.format("%.2f" , counter) + " of " + type + ". Total price: " + calculatePrice(counter, type));
					type = consumption.getType();
					counter = consumption.getConsumption();
				} else counter += consumption.getConsumption();
			}
			writer.println("\t\tWas consumed " + String.format("%.2f" , counter) + " of " + type + ". Total price: " + calculatePrice(counter, type));
			writer.println();
			writer.println("Total electricity consumption: ");
			writer.println("\t\tWas consumed " + String.format("%.2f", totalElecricity) + " of " + TypesOfConsumption.ELECTRICITY + ". Total price: " +
					calculatePrice(totalElecricity, TypesOfConsumption.ELECTRICITY));
			writer.println("Total water consumption: ");
			writer.println("\t\tWas consumed " + String.format("%.2f", totalWater)+ " of " + TypesOfConsumption.WATER + ". Total price: " +
					calculatePrice(totalWater, TypesOfConsumption.WATER));
		}
	}
}