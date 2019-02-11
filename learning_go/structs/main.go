package main

import "fmt"

type contactInfo struct {
	email   string
	zipCode int
}

type person struct {
	firstName   string
	lastName    string
	contactInfo // same as contactInfo contactInfo
}

func (p person) print() {
	fmt.Printf("%+v", p)
}

func (pointerToPerson *person) updateName(newFirstName string) {
	(*pointerToPerson).firstName = newFirstName
}

func main() {

	/*
		//different ways to create a new person
		//first way // depends on the order
		alex := person{"Alex", "Anderson"}

		//second way --- we can change the order
		alex2 := person{firstName: "Alex", lastName: "Andres"}

		//third way
		var alex3 person
		alex3.firstName = "alex"
		alex3.lastName = "andreeees"
	*/

	jim := person{
		firstName: "jim",
		lastName:  "mahdi",
		contactInfo: contactInfo{
			email:   "jim@jim.com",
			zipCode: 15324,
		},
	}

	/*
		before the shortcut
		jimPointer := &jim
		jimPointer.updateName("jimmy")
	*/

	jim.updateName("jimmy")

	jim.print()

}
