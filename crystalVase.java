import java.util.*;
import java.util.concurrent.Semaphore;
// will choose strategy 2 because it is the simplest as guests will come and go as the room becomes available
public class crystalVase {
    public Semaphore lock = new Semaphore(1);
    public HashSet<Integer> visitedGuests = new HashSet<Integer>();
    public static int numGuests;
    // room starts out as available
    public boolean roomAvailable = true;
    public static void main(String[] args){
        crystalVase room = new crystalVase();
        Scanner sc = new Scanner(System.in);
        System.out.println("How many guests do you want to test?");
        numGuests = Integer.parseInt(sc.nextLine());
        long start = System.currentTimeMillis();
        Thread[] guests = new Thread[numGuests];
        for(int i = 0; i < numGuests; i++){
            guests[i] = new Thread(new Guest(i, room));
        }
        for(int i = 0; i < numGuests; i++){
            guests[i].start();
        }
        for(int i = 0; i < numGuests; i++){
            try{
                guests[i].join();
            } catch (InterruptedException e){
                System.out.println(e);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
        //System.out.println(room.visitedGuests.toString());
        sc.close();
    }
}
class Guest implements Runnable{
    public int id;
    public crystalVase room;
    public Guest(int id, crystalVase room){
        this.id = id;
        this.room = room;
    }
    public void run(){
        // guest will try to enter the room will continue running while all guests have not visited the room
        while(room.visitedGuests.size() < crystalVase.numGuests){ 
            try{
                room.lock.acquire();
                // if the room is available, the guest will enter the room
                // if not the thread/guest will eventually have a chance to enter the room
                if(room.roomAvailable && !room.visitedGuests.contains(id)){
                    room.roomAvailable = false;
                    // guest will look at the vase and make the room unavailable
                    System.out.println("Guest " + (id + 1) + " is looking at the vase.");
                    // you can uncomment the following line to simulate 100ms of time for guest to look at the vase
                    //Thread.sleep(100);
                    // guest will leave the room
                    room.visitedGuests.add(id);
                    room.roomAvailable = true;
                    
                }
                // every guest will get a chance to enter the room
                room.lock.release();
            } catch (InterruptedException e){
                System.out.println(e);
            }
        }
    }
}