import hotel.Guest;
import hotel.Hotel;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Hotel hotel = new Hotel(3, 4);

        int menu;
        do {
            System.out.println("1. Find Guest");
            System.out.println("2. Add group of guests");
            System.out.println("3. Show Hotel Data");
            System.out.println("4. Floor with max free rooms");
            System.out.println("-1. Exit");
            menu = scanner.nextInt();
            scanner.nextLine();
            switch (menu) {
                case 1:
                    findGuest(hotel);
                    break;
                case 2:
                    addGroup(hotel);
                    break;
                case 3:
                    showHotelData(hotel);
                    break;
                case 4:
                    showMaxFreeRoomsFloor(hotel);
                    break;
            }
        } while (menu != -1);
    }

    private static void showMaxFreeRoomsFloor(Hotel hotel) {
        int floorWithMaxFreeRooms = hotel.findFloorWithMaxFreeRooms();
        System.out.println("Floor with max free rooms is " + floorWithMaxFreeRooms);
    }

    private static void showHotelData(Hotel hotel) {
        hotel.showData();
    }

    private static void addGroup(Hotel hotel) {
        System.out.print("How many people?");
        int n = scanner.nextInt();
        scanner.nextLine();
        Guest[] guests = new Guest[n];
        for (int i = 0; i < guests.length; i++) {
            System.out.print("Guest name:");
            String name = scanner.nextLine();
            System.out.print("Guest passport:");
            int passport = scanner.nextInt();
            scanner.nextLine();
            guests[i] = new Guest(name, passport);
        }
        int numOfRoom = hotel.accomodate(guests);
        if (numOfRoom > 0) {
            System.out.println("Guests in room " + numOfRoom);
        } else {
            System.out.println("There is no room for this group");
        }
    }

    private static void findGuest(Hotel hotel) {
        System.out.print("Passport: ");
        int passport = scanner.nextInt();
        scanner.nextLine();
        int roomOfGuestByPassport = hotel.findRoomOfGuestByPassport(passport);
        if (roomOfGuestByPassport > 0) {
            System.out.println("Guest with passport stay at " + roomOfGuestByPassport);
        } else {
            System.out.println("Guest not found");
        }
    }
}
