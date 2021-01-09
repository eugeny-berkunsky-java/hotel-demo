package hotel;

import java.util.Random;

public class Hotel {
    private int numOfUsedRooms;
    private Room[][] allRooms;

    public Hotel(int floors, int roomsOnFloor) {
        allRooms = new Room[floors][roomsOnFloor];
        numOfUsedRooms = 0;
        createRooms(allRooms, 1, 4);
    }

    private void createRooms(Room[][] allRooms, int min, int max) {
        Random rnd = new Random();
        for (int i = 0; i < allRooms.length; i++) {
            Room[] floor = allRooms[i];
            for (int j = 0; j < floor.length; j++) {
                int k = rnd.nextInt(max-min+1)+min; // min = 1, max = 4  (4)
                floor[j] = new Room(k);
            }
        }
    }

    public int accomodate(Guest[] guests) {
        for (int i = 0; i < allRooms.length; i++) {
            Room[] floor = allRooms[i];
            for (int j = 0; j < floor.length; j++) {
                if (floor[j].getNumOfBeds() == guests.length && floor[j].getCount() == 0) {
                    for (int k = 0; k < guests.length; k++) {
                        floor[j].addGuest(guests[k]);
                    }
                    // i - номер этажа
                    // j - номер комнаты
                    int n = (i + 1) * 100 + (j + 1);
                    return n;
                }
            }
        }
        for (int i = 0; i < allRooms.length; i++) {
            Room[] floor = allRooms[i];
            for (int j = 0; j < floor.length; j++) {
                if (floor[j].getNumOfBeds() > guests.length && floor[j].getCount() == 0) {
                    for (int k = 0; k < guests.length; k++) {
                        floor[j].addGuest(guests[k]);
                    }
                    // i - номер этажа
                    // j - номер комнаты
                    int n = (i + 1) * 100 + (j + 1);
                    return n;
                }
            }
        }
        return -1;
    }

    public int findRoomOfGuestByPassport(int passport) {
        for (int i = 0; i < allRooms.length; i++) {
            Room[] floor = allRooms[i];
            for (int j = 0; j < floor.length; j++) {
                Room room = floor[j];
                if (room.containsGuestWithPassport(passport)) {
                    return (i+1)*100 + (j+1);
                }
            }
        }
        return -1;
    }

    public int findFloorWithMaxFreeRooms() {
        int result = 0;
        int max = countFreeRoomsOnFloor(allRooms[0]);
        for (int i = 1; i < allRooms.length; i++) {
            Room[] floor = allRooms[i];
            int k = countFreeRoomsOnFloor(floor);
            if (k > max) {
                max = k;
                result = i;
            }
        }
        return result + 1;
    }

    private int countFreeRoomsOnFloor(Room[] floor) {
        int count = 0;
        for (int i = 0; i < floor.length; i++) {
            Room room = floor[i];
            if (room.isFree()) {
                count++;
            }
        }
        return count;
    }

    public void showData() {
        System.out.println(" -- Welcome to the Hotel --");
        for (int i = 0; i < allRooms.length; i++) {
            Room[] floor = allRooms[i];
            System.out.println("Floor " + (i+1));
            for (int j = 0; j < floor.length; j++) {
                Room room = floor[j];
                System.out.print("Room " + ((i+1)*100 + (j+1)) + " for " + room.getNumOfBeds() + " guests ");
                if (room.isFree()) System.out.println("is empty");
                else {
                    System.out.println("has " + room.getCount()+ " guests");
                    room.printAllGuestsInfo();
                }
                System.out.println("----------");
            }
        }
    }
}
