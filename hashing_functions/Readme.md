Lab 07
Lab Partner Names: Salih Zainulabdeen, Tong Yu

*please see task.pdf to understand what we were asked to do*

In this lab, we first computed the sequence of values hash h(K) for any sequence of integers given in input
based on m, a, and b. We then read the input_sequence file and calculate the hashKey for each input in the input array
and store it in the output array and store the sequence computed in a text file called output_sequence.

We also ploted the pairs(k, h(k)) to see how well/bad elements are distributed.

Task 1: plot for bad distribution
Case 1: m = 100, a = 10, b = 5 : it's bad because it's distributing all the data into only 10 buckets of the 100 we have.
In other words, it's not taking advantage of the other 90 buckets we have.
Case 2: m = 90, a = 15, b = 14 : this one is even worse because we only use 6 buckets out of 90
case 3: m = 100, a = 50, b = 1 : this one is the worst among the three because we're only using 2 buckets out of 95 total.


Task 2: plot for good distribution
case 1: m = 100, a = 7, b = 7 : we're using many buckets and the data is distributed nicely
case 2: m = 95, a = 9, b = 9 : another good distribution
case 3: m = 102, a = 5, b = 7: another good distribution

NOTE: Please see our plots as PNG files placed in the folder. Thank you!

NOTE 2: Each case has its own output sequence. However, the same input sequence was used for all the 6 cases above. 