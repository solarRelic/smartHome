import devices.*;
import devices.equipment.Bike;
import devices.equipment.BikeFactory;
import devices.equipment.Ski;
import devices.equipment.SkiFactory;
import home.*;
import resident.Resident;
import resident.human.Baby;
import resident.human.Father;
import resident.human.Mother;
import resident.pet.Cat;
import resident.pet.Dog;
import sensors.*;
import sensors.consumptions.ElectricitySensor;
import sensors.consumptions.WaterMeasurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Config {
    public Config() {}

    public Home getConfig() {
        Home home = new Home("Na Lysine, 12");

        Floor basement = new Floor("basement", home);
        Floor floor1 = new Floor("First floor", home);
        Floor floor2 = new Floor("Second floor", home);

        RoomFactory insideRoomFactory = new InsideRoomFactory();
        RoomFactory outsideRoomFactory = new OutsideRoomFactory();

        OutsideRoom garage = (OutsideRoom) outsideRoomFactory.createRoom("garage", basement, 0, 2);
        OutsideRoom garage1 = (OutsideRoom) outsideRoomFactory.createRoom("garage1", basement, 0, 2);
        OutsideRoom garage2 = (OutsideRoom) outsideRoomFactory.createRoom("garage2", basement, 0, 2);
        OutsideRoom garage3 = (OutsideRoom) outsideRoomFactory.createRoom("garage3", basement, 0, 2);
        OutsideRoom gym = (OutsideRoom) outsideRoomFactory.createRoom("gym", basement, 2, 2);

        InsideRoom kitchen1 = (InsideRoom) insideRoomFactory.createRoom("Kitchen1", floor1, 1, 1);
        InsideRoom kitchen2 = (InsideRoom) insideRoomFactory.createRoom("Kitchen2", floor1, 1, 1);
        InsideRoom livingRoom = (InsideRoom) insideRoomFactory.createRoom("Living Room1", floor1, 2, 2);
        InsideRoom livingRoom2 = (InsideRoom) insideRoomFactory.createRoom("Living Room2", floor2, 2, 1);
        InsideRoom hall = (InsideRoom) insideRoomFactory.createRoom("Hall1", floor1, 0, 1);
        InsideRoom hall2 = (InsideRoom) insideRoomFactory.createRoom("Hall2", floor2, 0, 1);
        InsideRoom bathroom1 = (InsideRoom) insideRoomFactory.createRoom("Bathroom first floor", floor1, 1, 1);
        InsideRoom bathroom2 = (InsideRoom) insideRoomFactory.createRoom("Bathroom second floor1", floor2, 1, 1);
        InsideRoom bathroom3 = (InsideRoom) insideRoomFactory.createRoom("Bathroom second floor2", floor2, 1, 1);
        InsideRoom bedroomAdult1 = (InsideRoom) insideRoomFactory.createRoom("Master bedroom1", floor1, 2, 1);
        InsideRoom bedroomAdult2 = (InsideRoom) insideRoomFactory.createRoom("Master bedroom2", floor2, 1, 1);
        InsideRoom bedroomAdult3 = (InsideRoom) insideRoomFactory.createRoom("Master bedroom3", floor2, 1, 1);
        InsideRoom bedroomKid1 = (InsideRoom) insideRoomFactory.createRoom("Kid bedroom1", floor1, 2, 1);
        InsideRoom bedroomKid2 = (InsideRoom) insideRoomFactory.createRoom("Kid bedroom2", floor2, 1, 1);
        InsideRoom bedroomKid3 = (InsideRoom) insideRoomFactory.createRoom("Kid bedroom3", floor2, 1, 1);

        double[] tvConsumption = new double[] {2, 0.1, 0};
        double[] coffeeMakerCons = new double[] {1.1, 0.01, 0};
        double[] dishWasherCons = new double[] {2, 0, 0};
        double[] fridgeCons = new double[] {2, 0.1, 0.01};
        double[] microwaveOvenCons = new double[] {1, 0.1, 0};
        double[] playStationCons = new double[] {1, 0.1, 0.002};
        double[] washingMachineCons = new double[] {1.5, 0, 0};

        new TV("LG", livingRoom, TypesOfConsumption.ELECTRICITY, tvConsumption);
        new TV("Samsung", livingRoom2, TypesOfConsumption.ELECTRICITY, tvConsumption);
        new TV("Sony", bedroomAdult1, TypesOfConsumption.ELECTRICITY, tvConsumption);
        new TV("Hisense", kitchen1, TypesOfConsumption.ELECTRICITY, tvConsumption);
        new CoffeeMaker("Braun BrewSense", kitchen2, TypesOfConsumption.ELECTRICITY, coffeeMakerCons);
        new CoffeeMaker("Cuisinart", kitchen1, TypesOfConsumption.ELECTRICITY, coffeeMakerCons);
        new Dishwasher("Bosch", bathroom1, TypesOfConsumption.WATER, dishWasherCons);
        new Dishwasher("Whirlpool", kitchen1, TypesOfConsumption.WATER, dishWasherCons);
        new Fridge("Whirlpool", kitchen1, TypesOfConsumption.ELECTRICITY, fridgeCons);
        new Fridge("LG", kitchen2, TypesOfConsumption.ELECTRICITY, fridgeCons);
        new MicrowaveOven("Gorenje", kitchen1, TypesOfConsumption.ELECTRICITY, microwaveOvenCons);
        new MicrowaveOven("Samsung", kitchen2, TypesOfConsumption.ELECTRICITY, microwaveOvenCons);
        new PlayStation("PS5", bedroomKid1, TypesOfConsumption.ELECTRICITY, playStationCons);
        new PlayStation("PS4", bedroomKid2, TypesOfConsumption.ELECTRICITY, playStationCons);
        new WashingMachine("Bosch", hall, TypesOfConsumption.WATER, washingMachineCons);
        new WashingMachine("Electrolux", bathroom2, TypesOfConsumption.WATER, washingMachineCons);

        Car car = new Car("Ferrari", garage);
        Car car1 = new Car("Porsche", garage1);
        Car car2 = new Car("Mercedes", garage2);
        Car car3 = new Car("BMW", garage3);

        BikeFactory factoryBicycle = new BikeFactory(garage);
        SkiFactory factorySki = new SkiFactory(gym);

        Bike bicycle1 = factoryBicycle.createFatherBike();
        Bike bicycle2 = factoryBicycle.createMotherBike();
        Bike bicycle3 = factoryBicycle.createEquipment("Stark");
        Ski ski1 = factorySki.createFatherSki();
        Ski ski2 = factorySki.createMotherSki();
        Ski ski3 = factorySki.createEquipment("Fisher");

        Cat cat = new Cat("Tom");
        Dog dog = new Dog("Rex");
        Dog dog2 = new Dog("Bu");

        Father father = new Father("Peter");
        Father father1 = new Father("John");
        Father father2 = new Father("Chris");
        Mother mother = new Mother("Elena");
        Mother mother1 = new Mother("Kate");
        Mother mother2 = new Mother("Mia");
        Mother mother3 = new Mother("Liz");
        Baby baby = new Baby("Tobias");
        Baby baby1 = new Baby("Andrew");
        Baby baby2 = new Baby("Greg");

        List<Resident> residents = new ArrayList<>(Arrays.asList(dog, dog2, cat, father, father1, father2,
                mother, mother1, mother2, mother3, baby, baby1, baby2));
        for (Resident resident : residents) {
            resident.moveInHome(home);
        }

        SmokeSensor smokeSensor = new SmokeSensor(home);
        WindSensor windSensor = new WindSensor(home);
        BabyMonitoring babyMonitoring = new BabyMonitoring(home);
        DeviceHealthSensor deviceHealthSensor = new DeviceHealthSensor(home);
        WaterMeasurer waterMeasurer = new WaterMeasurer(home);
        TemperatureSensor temperatureSensor = new TemperatureSensor(home);
        ElectricitySensor electricitySensor = new ElectricitySensor(home);

        return home;
    }

    public Home getConfig2() {
        Home home = new Home("Lounskych, 885");

        Floor basement = new Floor("basement", home);
        Floor floor1 = new Floor("First floor", home);


        RoomFactory insideRoomFactory = new InsideRoomFactory();
        RoomFactory outsideRoomFactory = new OutsideRoomFactory();

        OutsideRoom garage = (OutsideRoom) outsideRoomFactory.createRoom("garage", basement, 0, 2);
        OutsideRoom gym = (OutsideRoom) outsideRoomFactory.createRoom("gym", basement, 2, 2);

        InsideRoom kitchen1 = (InsideRoom) insideRoomFactory.createRoom("Kitchen1", floor1, 1, 1);
        InsideRoom livingRoom = (InsideRoom) insideRoomFactory.createRoom("Living Room1", floor1, 2, 2);
        InsideRoom hall = (InsideRoom) insideRoomFactory.createRoom("Hall1", floor1, 0, 1);
        InsideRoom bathroom1 = (InsideRoom) insideRoomFactory.createRoom("Bathroom first floor", floor1, 1, 1);
        InsideRoom bedroomAdult1 = (InsideRoom) insideRoomFactory.createRoom("Master bedroom1", floor1, 2, 1);
        InsideRoom bedroomKid1 = (InsideRoom) insideRoomFactory.createRoom("Kid bedroom1", floor1, 2, 1);

        double[] tvConsumption = new double[] {2, 0.1, 0};
        double[] coffeeMakerCons = new double[] {1, 0.01, 0};
        double[] dishWasherCons = new double[] {2, 0, 0};
        double[] fridgeCons = new double[] {1.2, 0.1, 0.01};
        double[] microwaveOvenCons = new double[] {1, 0.1, 0};
        double[] playStationCons = new double[] {1, 0.1, 0.002};
        double[] washingMachineCons = new double[] {1.5, 0, 0};

        new TV("LG", livingRoom, TypesOfConsumption.ELECTRICITY, tvConsumption);
        new CoffeeMaker("Braun BrewSense", kitchen1, TypesOfConsumption.ELECTRICITY, coffeeMakerCons);
        new Dishwasher("Whirlpool", kitchen1, TypesOfConsumption.WATER, dishWasherCons);
        new Fridge("Samsung", kitchen1, TypesOfConsumption.ELECTRICITY, fridgeCons);
        new MicrowaveOven("LG", kitchen1, TypesOfConsumption.ELECTRICITY, microwaveOvenCons);
        new PlayStation("PS4", bedroomKid1, TypesOfConsumption.ELECTRICITY, playStationCons);
        new WashingMachine("Bosch", hall, TypesOfConsumption.WATER, washingMachineCons);

        Car car = new Car("Ferrari", garage);


        BikeFactory factoryBicycle = new BikeFactory(garage);
        SkiFactory factorySki = new SkiFactory(gym);

        Bike bicycle1 = factoryBicycle.createFatherBike();
        Bike bicycle2 = factoryBicycle.createMotherBike();
        Ski ski1 = factorySki.createFatherSki();
        Ski ski2 = factorySki.createMotherSki();

        Cat cat = new Cat("Tom");
        Dog dog = new Dog("Rex");

        Father father = new Father("John");
        Mother mother = new Mother("Elena");
        Baby baby = new Baby("Tobias");

        List<Resident> residents = new ArrayList<>(Arrays.asList(dog, cat, father,
                mother, baby));
        for (Resident resident : residents) {
            resident.moveInHome(home);
        }

        SmokeSensor smokeSensor = new SmokeSensor(home);
        WindSensor windSensor = new WindSensor(home);
        BabyMonitoring babyMonitoring = new BabyMonitoring(home);
        DeviceHealthSensor deviceHealthSensor = new DeviceHealthSensor(home);
        WaterMeasurer waterMeasurer = new WaterMeasurer(home);
        TemperatureSensor temperatureSensor = new TemperatureSensor(home);
        ElectricitySensor electricitySensor = new ElectricitySensor(home);

        return home;
    }
}
