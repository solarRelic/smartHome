package reports;

import devices.Device;
import devices.equipment.Equipment;
import home.*;
import resident.human.Human;
import resident.pet.Pet;

import java.io.PrintWriter;

/**
 * Generates a home configuration report for a specified range of iterations.
 */
public class HomeConfigurationReport extends HomeTimeReport{
	static final  String FILE_NAME = "HomeConfigurationReport";

	public HomeConfigurationReport() {
	}


	@Override
	public void writeReportToFile(Home home, int from, int to, String name) {
		super.writeReportToFile(home, from, to, name);
	}

	@Override
	public void generateReport(Home home, int from, int to, PrintWriter writer) {
		writer.println("\t\t\t\t\t\t\t\t\t\tHome Configuration Report:");
		writer.println("Our home is located on the street: " + home.toString());
		writer.println("The home has: \n");
		for (Floor floor: home.getFloorList()) {
			writer.println("Floor: " + floor.toString());
			for (Room room : floor.getRooms()) {
				writer.print("\tRoom: " + room.toString() + " contains\n");
				writer.println("\t\t" + room.getWindows().size() + " windows");
				writer.println("\t\t" + room.getDoors().size() + " doors");
				if (room instanceof InsideRoom insideRoom) {
					for (Device device : insideRoom.getDevices()) {
						writer.println("\t\tDevices: " + device.toString());
					}
				} else if (room instanceof OutsideRoom outsideRoom) {
					for (Equipment equipment : outsideRoom.getEquipments()) {
						writer.println("\t\tEquipments: " + equipment.toString());
					}
				}
			}
		}
		writer.println("In the home live:\n" + "\t\tHumans: ");
		for (Human human : home.getHumanList()) {
			writer.println("\t\t\t" + human.toString());
		}
		writer.println("\t\tPets: ");
		for (Pet pet : home.getPetList()) {
			writer.println("\t\t\t" + pet.toString() + " (" + pet.getClass().getSimpleName() + ")");
		}
	}
}