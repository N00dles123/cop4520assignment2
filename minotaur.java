import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.Semaphore;
// make a set number of guests
// to simulate a guests turn to go find the cupcake since a guest can go multiple times, we will use random number generation
// one thread will be used to manage the cupcakes
// maintain a global counter to see 
public class minotaur {
    // this will be used as a lock
    public Semaphore lock = new Semaphore(1);

    // you can change the number of guests
    public static int numGuests = 10;
    // this will keep track of the total amount of guests that have entered the labrynth
    public int count = 0;
    // labrynth will start with 1 cupcake
    public boolean cupcakePresent = true;
 
    // start with no current guest so make it -1
    public int curGuest;
    // checking if guests ate cupcakes yet
    // 0 will be false and 1 will be true
    public boolean[] guestEntered = new boolean[numGuests];
    public static void main(String[] args){
        minotaur labrynth = new minotaur();
        // create a thread for each guest
        Thread[] guests = new Thread[numGuests];
        guests[0] = new Thread(new cupcake(labrynth));

        for(int i = 1; i < numGuests; i++){
            guests[i] = new Thread(new guests(i, labrynth));
        }
        // start all threads
        for(int i = 0; i < numGuests; i++){
            guests[i].start();
        }
        // choose a random guest
        while(labrynth.count < numGuests){
            labrynth.curGuest = randomGuest();
        }

        // wait for threads to finish
        for(int i = 0; i < numGuests; i++){
            try{
                guests[i].join();
            } catch (InterruptedException e){
                System.out.println(e);
            }
        }

        System.out.println(Arrays.toString(labrynth.guestEntered));

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
    int index;
    minotaur m;
    public guests(int index, minotaur m){
        this.index = index;
        this.m = m;
    }
    public void run(){
        while(m.count < minotaur.numGuests){
            try{
                m.lock.acquire();
                // if the cupcake is present and the guest has not entered the labrynth
                if(m.guestEntered[index] == false && index == m.curGuest && m.cupcakePresent){
                    // guest has entered the labrynth
                    m.guestEntered[m.curGuest] = true;
                    // cupcake is no longer present
                    m.cupcakePresent= false;
                    System.out.println("Guest " + index + " has ate the cupcake");
                    System.out.println(m.count);
                }
            } catch (InterruptedException e){
                System.out.println(e);
            } finally {
                m.lock.release();
            }
        }   
    }
}
// this will be used to manage the cupcakes and counting guests

class cupcake implements Runnable{
    minotaur m;
    public cupcake(minotaur m){
        this.m = m;
    }
    public void run(){
        
        while(m.count < minotaur.numGuests){
            try{
                m.lock.acquire();
                if(m.curGuest == 0){
                    // cupcake is unavailable so we have to replace
                    if(m.cupcakePresent == false){
                        m.cupcakePresent = true;
                        m.count += 1;
                    }

                    if(m.cupcakePresent == true && m.guestEntered[0] == false){
                        m.count += 1;
                        m.guestEntered[0] = true;
                        m.cupcakePresent = true;
                    }
                }
            } catch (InterruptedException e){
                System.out.println(e);
            } finally {
                m.lock.release();
        }
        
    }

    }
}