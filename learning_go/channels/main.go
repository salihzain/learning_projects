package main

import (
	"fmt"
	"net/http"
	"time"
)

func main() {
	links := []string{
		"http://google.com",
		"http://facebook.com",
		"http://golang.org",
		"http://amazon.com",
		"http://yahoo.com",
	}

	c := make(chan string)

	for _, link := range links {
		go checkLink(link, c)
	}
	// at this point, our program has craeted all the go routines taht we have asked it to do
	// since there's a blcking get at each cehcklink(), the program will get back to the main routine
	// then the main routine is going to be blocked by the following loop

	// for i := 0; i < len(links); i++ {
	// 	fmt.Println(<-c) // blocking call
	// 	// first, the program is going to be blocked, it will wait until the first message is recieved
	// 	// tehn it will go to the second iteration
	// 	// and will be blocked too, until a message is recieved by the channel
	// 	// the program will exit the loop once all the routines send back messages
	// }

	// // for repeatdely checking the status of websites
	// for {
	// 	go checkLink(<-c, c)
	// }

	// alternative syntax to the for loop above
	// for l := range c {
	// 	//using range with chanell,
	// 	// it's saying wait for the channel to receive a value
	// 	//
	// 	go checkLink(l, c)
	// }

	for l := range c {
		// this is called a function literal, just like ananoyomus function
		go func(link string) {
			// pause before follwing up without pausing the main routine
			time.Sleep(5 * time.Second) // if we wanna pause for multiple seconds, we can multiply it by seconds
			checkLink(link, c)
		}(l) // to direclty invoke it
	}

}

func checkLink(link string, c chan string) {
	_, err := http.Get(link)
	if err != nil {
		fmt.Println(link, "might be down!")
		c <- link
		return // to make sure we don't do anything else in this function
	}
	fmt.Println(link, "is up")
	c <- link

}
