# COP4520 Assignment 2
Jason Lin COP 4520
## Compilation and Running in Command Line
To compile Minotaur's birthday party, cd to the file directory of minotaur.java, then type in "javac minotaur.java". To run the program type "java minotaur" <br/>
The program will ask in the terminal how many guests you want to test and then just input a number <br/>
To compile Minotaur's Crystal Vase, cd to the file directory of crystalVase.java and then type in "javac crystalVase.java" to compile the program then to run the program, type in "java crystalVase" <br/>
The program will ask in the terminal how many guests you want to test and then just input a number. <br/>
## Problem 1: Minotaur's Birthday Party solution and proof of correctness
In this program, the protocol where every guest will enter the labrynth includes N guests and each thread represents a guest in the program. <br/>
The strategy is to dedicate one guest to counting the number of guests that have been in the labrynth and each of the rest of the threads to go through the labrynth when called upon randomly since guests can go enter the labrynth multiple times <br/>
The one dedicated counting guest will also get a chance to take a cupcake <br/>
Once every thread has been called upon once, and has had an opportunity to get a cupcake, the program terminates. To do this, we just make sure the counted guests that have entered the labrynth are equal to the set number of guests in the program and then we terminate once they are equal. <br/>
Experimentally, this protocol should run in factorial time since we are randomly choosing a thread each time to visit the labrynth <br/>
In order to test, I changed up the number of guests in 5 separate runs, I tested with 10, 50, 100, 200, and 400 guests. <br/>
The times I got were 14ms, 66ms, 222ms, 1305ms, and 12656ms. The timer starts after you choose the number of guests. <br/>
## Problem 2: Minotaur’s Crystal Vase and proof of correctness
In this program, the protocol is to make use of a global boolean to see whether the room is available or busy and to have each thread continually check the room to see if the room is available and if not another thread may show up and "go" in the room once its available. All threads will continue this until every guest has had one chance of going in the room and then the program will terminate. <br/>
The second strategy works best because of how simple it is by basically using a global boolean to check whether the room is open. The other strategies would involve using either a queue or a complex way of managing who gets in the door. The second strategy is also a better version of the first where the first doesn't have a sign so that the guests cant tell whether the room is available or not. The second strategy can also ensure that the guests do not visit the room twice in order to quickly ensure that all guests can admire the crystal once.  <br/>
In order to test this, I used 10, 50, 100, 200, and 400 guests. <br/>
The times I ended up getting were 5ms, 16ms, 40ms, 80ms, and 136ms, the timer for this one also starts after you choose the number of guests