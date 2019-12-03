# CH1. Data Types

## Primitive Types
* Java has 8 predefined primitive types.
* Primitive Types always has a value.
* Primitive Type starts with a lowercase letter.
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

## Reference Types (Non-Primitive Types)
* Java reference types are String, Array, Class, Interface ... etc 
* Reference Type refers to an object.
* Reference Type is nullable. (ok not to have a value)
* Reference Type starts with an uppercase letter.

## String
* Java String
    ```
    # string is basically an array of characters
    # s1 is the same as s2
    
    String s1="javastring";
    
    char[] ch={'j','a','v','a','s','t','r','i','n','g'};  
    String s2=new String(ch); 
    ```
* String, StringBuilder and StringBuffer
    * String: immutable
        ```
        String a = "hi";
        a = a + " there";   # a is still "hi" (immutable)
        ```
    * StringBuilder: mutable, faster, not-synchronous, preferred in single threaded program.
        ```
        StringBuilder b = new StringBuilder("hi");
        b.append(" there"); # b is "hi there" (mutable)
        ```
    * StringBuffer: mutable, slower, synchronous(thread-safe).
        ```
        StringBuffer c = new StringBuffer("hi");
        c.append(" there"); # c is "hi there" (mutable)
        ```

## Array
* Array, List, data_structures.ArrayList and data_structures.LinkedList?
    ```
    Array     : Array is an object that represents indexed collection of same-kind items.
    List      : List is an interface that represents indexed collection of items.
    data_structures.ArrayList : data_structures.ArrayList is an implementation of List using dynamic array concept.
    data_structures.LinkedList: data_structures.LinkedList is an implementation of List using chained node concept.
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
    ```
    * Access: O(1)
    * Search: O(n)     # sequential search
              O(log n) # binary search on sorted array
    * Insert: O(n)     # worst case is insertion on array[0]
    * Delete: O(n)     # worst case is deletion on array[0]
    ```