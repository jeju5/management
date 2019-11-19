# Data Types

## Primitive Types
* Java has 8 primitive types
    ```
    * Boolean
        * boolean  size: 1bit, default: false
    * Character
        * char     size: 2byte, default: '\u0000' (null character)
                   char is unicode character ('\u0000' ~ '\uffff')  
    * Integer
        * byte     size: 1byte, default: 0
                   signed 8bits two's complement number
                   range: -128(0x10000000) ~ 127(0x01111111) 
        * short    size: 2byte, default: 0
                   signed 16bits two's complement number
        * int      size: 4byte, default: 0
                   signed 32bits two's complement number
        * long     size: 8byte, default: 0L
                   signed 64bits two's complement number
    
    * Floating Point
        * float    size: 4byte, default: 0.0f
        * double   size: 8byte, default: 0.0d
    
    (1byte = 8bits)
    ```
  
## String
```
# string is basically an array of characters
# s1 is the same as s2

String s1="javastring";

char[] ch={'j','a','v','a','s','t','r','i','n','g'};  
String s2=new String(ch); 
```

## Array
* Array, List, ArrayList and LinkedList?
    ```
    Array     : Array is an object that represents indexed collection of same-kind items.
    List      : List is an interface that represents indexed collection of items.
    ArrayList : ArrayList is an implementation of List using dynamic array concept.
    LinkedList: LinkedList is an implementation of List using chained node concept.
    ```

* Java Array
    ```
    int arr[]=new int[5];
    int arr[]={1,2,31,412,255}
    ```
  
* Java Multidimensional Array
    ```
    int arr[][]=new int[5][5]; // 5x5 integer array
    ``` 

* Big-O
    * Access: O(1)
    * Search
        * O(n)     # sequential search
        * O(log n) # binary search on sorted array
    * Insert: O(n) # worst case is insertion on array[0]
    * Delete: O(n) # worst case is deletion on array[0]