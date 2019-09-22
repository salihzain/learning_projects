*One of the first games that I built as I was learning java*

Hello World!!

**Here's what you need to know about my code:** 

I created six classes in total as follows: 

Start.java ===> the single line class to initiate the game. 
Golf.java ===> where all the mess is, including variables and loops. I tried to avoid creating methods in that class. 
Courses.java ===> where I stored the Golf courses as arrays to make the process dynamic in case i wanted to add more courses in the future. 
Shot.java ===> where I take the player's choice of club and power and calculate the shot distance. 
Putting.java ===> When the player is on the green, I take his choice of power and calculate the shot distance according to the given power table. 
Methods.java ===> the class where I stored all the methods that I need like copying an array or exiting the program and so on. 

**To run the game:**

`javac *.java`

`java Start`



**How does the game work? **

As I said, all the magic lies in Golf.java 
In general, the structure can be found here if my description below is not that clear https://www.lucidchart.com/invitations/accept/fd375e49-195f-4db0-b7e8-3325975c1d8a
I start by creating instances from the other four classes. 
Once the player selects a course, I make a copy of the player's choice to a local array arr to make the process more dynamic. 
Then, I start the outer for loop that counts until 18 to cover all the 18 holes. 
I ask the player for his choice of club and power for the first shot, then I calculate the distance using nextDistance() found in shot. 
Then, I start a while loop that is only broken if the distance is less than 20 yards. 
in that loop, I allow the player to take as many as shots as he wants until the distance is less than 20 yards. 
Once the loop is broken, I convert the remaining distance from yards to feet because now the player is on the green 
then i start another loop, the green loop. that is only broken if the ball is in the hall. 
and allow the player to take as many shots as he wants until he puts the ball in the hole. 
then the loop is broken, the player is informed of his hole score and then he moves on to the next hole. 
Once the player finishes the 18 holes, he gets informed of his round score and gets the choice to play again or quit using playAgain() 

I have commented on the indiviual classes to make sure that my code is clear. 

Notable things about my code
---------------------------------------------------------------------------------------------------
1- The awesome one line Start class that inititate the game!! I mean, it couldn't be simpler than that :) 
2- My organization procss, how I plan my code before I evne start. I mean, yes, the diagram that you find here took me a lot of time https://www.lucidchart.com/invitations/accept/fd375e49-195f-4db0-b7e8-3325975c1d8a
3- The dynamic code that allows many courses to be added to the game as 2D arrays without much efforts. 
4- The organization of methods and classes. I really used the concepts we learned of OOP. Please reward me for that. 
5- Validate() method to validate the player's input and make use that he didn't input a value that doesn't make anysense. 
6- onExit() I think this one is pretty awesome! Allowing the player to quit at any single point. 
7- playAgain() yup!!! this one is also cool! if the player wants to play a million times, he surely can! 
8- copyArray() and sumArray() were nice methods too. 
9- And last but not least, my awesome Welcome and see you banners!! You can't ignore that!!! That one alone deserves a 20% added credit :D 

Stay awesome, 
Salih Zainulabdeen 
