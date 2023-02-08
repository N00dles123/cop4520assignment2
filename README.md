# COP4520 Assignment 2
Jason Lin COP 4520
## Compilation and Running in Command Line
To compile Minotaur's birthday party, cd to the file directory of minotaur.java, then type in "javac minotaur.java". To run the program type "java minotaur"
The program will ask in the terminal how many guests you want to test and then just input a number
To compile Minotaur's Crystal Vase, cd to the file directory of crystalVase.java and then type in "javac crystalVase.java" to compile the program then to run the program, type in "java crystalVase"

## Problem 1: Minotaur's Birthday Party solution and proof of correctness
In this program, the protocol where every guest will enter the labrynth includes N guests and each thread represents a guest in the program. <br/>
The strategy is to dedicate one guest to counting the number of guests that have been in the labrynth and each of the rest of the threads to go through the labrynth when called upon randomly since guests can go enter the labrynth multiple times <br/>
The one dedicated counting guest will also get a chance to take a cupcake <br/>
Once every thread has been called upon once, and has had an opportunity to get a cupcake, the program terminates. To do this, we just make sure the counted guests that have entered the labrynth are equal to the set number of guests in the program and then we terminate once they are equal. <br/>
Experimentally, this protocol should run in factorial time since we are randomly choosing a thread each time to visit the labrynth <br/>
In order to test, I changed up the number of guests in 5 separate runs, I tested with 10, 50, 100, 200, and 400 guests.
The times I got were 14ms, 66ms, 222ms, 1305ms, and 12656ms
## Problem 2: Problem 2: Minotaur’s Crystal Vase and proof of correctness
In this program, 