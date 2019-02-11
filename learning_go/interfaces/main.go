package main

import (
	"fmt"
)

type bot interface {
	getGreeting() string
}

// no fields because we wanna create functions that take those types as recievesrs
type englishBot struct{}
type spanishBot struct{}
type arabicBot struct{}

func (englishBot) getGreeting() string {
	//if we aren't making use of eb, we can remove it
	// very custom logic for generating an egnlish greeting
	return "Hi there!"
}

func (spanishBot) getGreeting() string {
	//very custom logic/ just pretnding
	return "hola!"
}

func (arabicBot) getGreeting() string {
	return "ahlan"
}

//The naive way
// func printGreeting(eb englishBot) {
// 	fmt.Println(eb.getGreeting())

// }

// func printGreeting(sb spanishBot) {
// 	fmt.Println(sb.getGreeting())
// }

//the better way
func pritnGreeting(b bot) {
	fmt.Println(b.getGreeting())
}

func main() {
	eb := englishBot{}
	sb := spanishBot{}
	ab := arabicBot{}

	pritnGreeting(eb)
	pritnGreeting(sb)
	pritnGreeting(ab)

	ct := cat{}
	dt := dog{}

	printSound(dt)
	printSound(ct)
}

//similar to class animal with a trait of makesound
///so every type with make sound is now an animal
type animal interface {
	makeSound() string
}

type cat struct{}
type dog struct{}

func (cat) makeSound() string {
	return "Mehow"
}

func (dog) makeSound() string {
	return "bark"
}

func printSound(a animal) {
	fmt.Println(a.makeSound())
}
