# TIPS
* index in array
```
 array

  ........... length ..........
  :                           :
 [0][1][2] ... [length-2][length-1]

* the last index is 'length-1'
* for loop
  for (int i=0; i<array.length; i++)
  iterates from 0 to length-1 
```

* for loop
```
- loop is executed until the second condition meets.

for (int i=0; i<array.length; i++)
- loops 0 --> length-1

for (int i=array.length-1; -1<i; i++)
- loops length-1 --> -1
```

* array shifting
```
make i as a shifting-target
then each loop in 'for (int i=0; ~~~)' will assign 'elements[i]=something'
```

* java allows an array of length zero

* @TOSTUDY
* casting
    * checked casting?
    * unchecked casting?