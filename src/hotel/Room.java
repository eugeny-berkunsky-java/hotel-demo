package hotel;

public class Room {
    private int numOfBeds;
    private Guest[] allGuests;
    private int count;

    public Room(int numOfBeds) {
        this.numOfBeds = numOfBeds;
        allGuests = new Guest[numOfBeds];
        count = 0;
    }

    public int getNumOfBeds() {
        return numOfBeds;
    }

    public int getCount() {
        return count;
    }

    public void addGuest(Guest guest) {
        allGuests[count++] = guest;
//        count++;
    }

    public boolean containsGuestWithPassport(int passport) {
        for (int i=0; i<count; i++) {
            if (allGuests[i].getPassportNumber()==passport) {
                return true;
            }
        }
        return false;
    }

    public boolean isFree() {
        return count == 0;
    }

    public void printAllGuestsInfo() {
        for (int i = 0; i < count; i++) {
            System.out.println("   " + (i+1) + ": " + allGuests[i]);
        }
    }
}
