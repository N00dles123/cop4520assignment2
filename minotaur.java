import java.util.*;
import java.util.concurrent.atomic.*;
// make a set number of guests
// to simulate a guests turn to go find the cupcake since a guest can go multiple times, we will use random number generation
//
public class minotaur {
    // you can change the number of guests
    public static int numGuests = 10;
    // labrynth will start with 1 cupcake
    public AtomicBoolean cupcakePresent = new AtomicBoolean(true);
    // checking if guests ate cupcakes yet
    // 0 will be false and 1 will be true
    public AtomicIntegerArray guestEntered = new AtomicIntegerArray(numGuests);
    public static void main(String[] args){
        
    }
    public static int randomGuest(){
        Random rand = new Random();
        int randomNum = rand.nextInt(numGuests);
        return randomNum;
    }
}

class guests implements Runnable{
    // no communication between threads during execution communicate before execution
    // make a strategy for each thread
    // N is the number of guests and also represents N threads
    // 
    minotaur m;
    
    public void run(){

    }
}
