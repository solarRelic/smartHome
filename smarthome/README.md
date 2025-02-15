# Semester project OMO

## Name
Smart Home

## Description
Virtual simulation of a smart home, where we simulate the operation of a household.
Reports are generated in the reports folder.

## Requirements:
+ F1: All required entities are implemented.
    + Home, Devices, Sensors packages;
+ F2: Every device can be used and will be operated depending on different states.
    + Devices package, Usable interface;
+ F3: Consumption of devices based on their states.
    + Consumption class;
    + attribute: double[] consumption; (idx 0 -> ON state, idx 1 -> OFF, idx 2 -> IDLE)
+ F4: Every device is attached to sensors measuring consumption and deterioration.
    + ElectricitySensor, WaterMeasurer, DeviceHealthSensor
+ F5: Residents interact with each other (i.e. mom or dad -> baby) and devices (i.e residents -> fridge).
    + Usable interface (for devices) and alert handling of residents( i.e. father put outs fire).
+ F6: Residents randomly use devices and equipment which are attached to appropriate rooms.
    + Human class, Equipment package;
+ F7: Events are handled by the appropriate residents.
    + Events package;
+ F8: Reports are generated either to CL or to the file.
    + Reports package;
+ F9: After a device has broken a resident looks for a manual and repairs the device.
    + Father class (only father repairs the broken device);
    + Manual class;
+ F10: With 50% probability human uses either devices at home or sport equipment outside.
    + Human class, Equipment package;

## Design Patterns:
+ Singleton. Initialisation, Manual classes;
+ Lazy Evaluation. Initialisation, Manual classes (we're not instantiating it until we need to use it);
+ Observer. Used to notify subscribers after a change;
+ State. Devices state (ON, OFF, IDLE);
+ Factory. RoomFactory interface, InsideRoomFactory and OutsideRoomFactory classes;
+ Strategy. Strategy class in sensors.temperature_strategy pkg.
+ Streamy.