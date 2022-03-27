# DivideAndConquer
•Program runs three different algorithms and outputs them to .csv file to compare the time complexity. <br>
•The number of iterations can be set and each iteration outputs an array of random integers and a random search key. <br>
•The size of the array is increased every iteration. <br>
•User can select the block size of the array. <br>
•In a sorted array containing n elements, the number of times a value k occurs is outputted. <br>
•Three aproaches are implemented: <br>
-O(n) approach which sequentially tests every element <br>
-O(m + log n) approach which uses binary search to find location of k and then sequentially counts further occurences, m is total occurences <br>
-O(log + n) approach which locates the boundaries of k and subtracts them to find occurences.
•The number of comparisons in each approach is counted and saved in a csv file. <br>
•Excel files showcasing small blocks and large blocks are attached.
