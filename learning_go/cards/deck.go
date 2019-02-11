package main

import (
	"fmt"
	"io/ioutil"
	"math/rand"
	"os"
	"strings"
	"time"
)

//Create a new type of 'deck'
// which is a slice of strings

//it's kinda like creating a class
type deck []string // this type of deck extends the behavior of string

func newDeck() deck {
	cards := deck{}

	cardSuits := []string{"Spades", "Dimanods", "Hearts", "Clubs"}
	cardValues := []string{"Ace", "Two", "Three", "Four"}

	for _, suit := range cardSuits {
		for _, value := range cardValues {
			cards = append(cards, value+" "+suit)
		}
	}

	return cards
}

// you can think of d as this or self f
// you can think of this as a method
func (d deck) print() {
	for i, card := range d {
		fmt.Println(i, card)
	}
}

// you can think of this file as a class, really!! we defined a new
//type, then we assigned a method to that type

func deal(d deck, handSize int) (deck, deck) {
	return d[:handSize], d[handSize:]
	//:handsize everything from the beginning to handsize
	//handsize: everything from handsize to the ened
}

func (d deck) toString() string {

	return strings.Join([]string(d), ",")

}

func (d deck) saveToFile(filename string) error {
	return ioutil.WriteFile(filename, []byte(d.toString()), 0666)

}

func newDeckFromFile(filename string) deck {
	bs, err := ioutil.ReadFile(filename)
	if err != nil {
		fmt.Println("error", err)
		os.Exit(1)

	}

	s := strings.Split(string(bs), ",")
	return deck(s)

}

func (d deck) shuffle() {

	//every single time, we create a new rand
	source := rand.NewSource(time.Now().UnixNano())

	r := rand.New(source)

	for i := range d {
		newPosition := r.Intn(len(d) - 1)

		//swapping the elements
		d[i], d[newPosition] = d[newPosition], d[i]
	}
}
