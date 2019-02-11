package main

import (
	"fmt"
)

func main() {
	/*
		//declaring maps
		//1st way
		//all keys are string and values are string in the example below
		colors := map[string]string{
			"key": "value",
		}

		//2nd way
		var colors2 map[string]string

		colors2["hello"] = "hi"

		//3d way
		colors3 := make(map[string]string)

		//delete
		delete(colors, "key")

	*/

	colors := map[string]string{
		"red":   "jsdjf",
		"green": "zero",
		"ccc":   "jsjfs",
	}

	printmap(colors)

}

func printmap(c map[string]string) {
	for key, value := range c {
		fmt.Println(key, value)
	}
}
