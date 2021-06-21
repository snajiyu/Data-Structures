# Data-Structures
----

In project 1A, we will build implementations of a “Double Ended Queue” using both lists and arrays. In project 1B (next week), we will learn how to write our own tests for those data structures, and will use the Double Ended Queue to solve some small real world probelms.

The double ended queue is very similar to the standard SLList and AList . 

 Deque (usually pronounced like “deck”) is an irregular acronym of double-ended queue. Double-ended queues are sequence containers with dynamic sizes that can be expanded or contracted on both ends (either its front or its back).


### Linked List Implementation
----
Create a LinkedListDeque implementation of the DequeAPI. All methods that involve finding, adding, removing items must take "constant time".

The amount of memory that your program uses at any given time must be proportional to the number of items. For example, if you add 10,000 items to the deque, and then remove 9,999 items, the resulting size should be more like a deque with 1 item than 10,000. Do not maintain references to items that are no longer in the deque.



### Array Implementation
----

Create an ArrayDeque implementation of the DequeAPI. All methods that involve finding, adding, removing items must take "constant time" except durign resizing operations. 

The amount of memory that your program uses at any given time must be proportional to the number of items. Starting array size is 8 and must expand or contract accordingly to for efficient memory usage. For arrays of length 16 or more, your usage factor should always be at least 25%. For smaller arrays, your usage factor can be arbitrarily low.
