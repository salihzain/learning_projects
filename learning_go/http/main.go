package main

import (
	"fmt"
	"io"
	"net/http"
	"os"
)

type logWriter struct{}

func main() {
	resp, err := http.Get("http://google.com")
	if err != nil {
		fmt.Println("error:", err)
		os.Exit(1)
	}

	// the naive way of getting the stuff
	// bs := make([]byte, 9999) //make sure we have 99999 elements availabe, or emty spaces
	// // we made our empty slice
	// // the read function is going to take the data and stick it inside the slice
	// // the read function is not set up to resize the slice
	// // that's why we set its size
	// resp.Body.Read(bs)
	// fmt.Println(string(bs))

	//second way of getting the response to print out
	// io.Copy(os.Stdout, resp.Body)

	lw := logWriter{}
	io.Copy(lw, resp.Body)

}

func (logWriter) Write(bs []byte) (int, error) {
	//now it's implemnting the write interface
	fmt.Println(string(bs))
	return len(bs), nil
}
