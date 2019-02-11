package main

import (
	"fmt"
	"io"
	"os"
)

// import (
// 	"fmt"
// )

// type shape interface {
// 	getArea() float64
// }

// type triangle struct {
// 	height float64
// 	base   float64
// }
// type square struct {
// 	sideLength float64
// }

// func (t triangle) getArea() float64 {
// 	return (0.5 * t.base * t.height)
// }

// func (s square) getArea() float64 {
// 	return (s.sideLength * s.sideLength)
// }

// func printArea(s shape) {
// 	fmt.Println(s.getArea())
// }

// func main() {
// 	s := square{sideLength: 13}
// 	t := triangle{base: 10, height: 12}

// 	printArea(s)
// 	printArea(t)

// }

func main() {
	file, err := os.Open(os.Args[1])
	if err != nil {
		fmt.Println("error:", err)
	}
	io.Copy(os.Stdout, file)
}
