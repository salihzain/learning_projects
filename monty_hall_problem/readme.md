Today in my probability class we discussed the Monty hall probelm that blew my mind. I just couldn't belive how changing my choice would allow me to win twice. 


Before I go on, the monty problem is stated as follows by brilliant.org: 


"n the problem, you are on a game show, being asked to choose between three doors. Behind each door, there is either a car or a goat. You choose a door. The host, Monty Hall, picks one of the other doors, which he knows has a goat behind it, and opens it, showing you the goat. (You know, by the rules of the game, that Monty will always reveal a goat.) Monty then asks whether you would like to switch your choice of door to the other remaining door. Assuming you prefer having a car more than having a goat, do you choose to switch or not to switch?"


I argued with my professor that it just doesn't make any sense that changing my choice would allow my chances of winning to double. So he suggested that if I don't believe him, I should experiment on my own by writing code. So that's what I have been doing for the last hour, after midnight, trying to come up with a neat way. 


It turned out, by running the method many times, that indeed when we change choice after the given information, our chances of winning are doubled. 

therefore, the program the I wrote shows me that if I change my mind, my chances of winning varies between 60-70%. 

However, if I stick to my choice, my probability of winning varies between 30-40%. Which is half! 

To run the program, simply download prop class, adjust the count in the method call, which is how many times you want to experiment to run, keeping in mind that experiment has a time complexity of O(n). Then run the program by opening a command line window or a terminal window and first writing 
``` javac prob.java ```  and then ``` java prob``` 

Enjoy!!! 