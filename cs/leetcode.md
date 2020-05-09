# Leet Code

## Easy
* Two Sum
```
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        for (int i=0; i<nums.length; i++) {
            for (int j=i+1; j<nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i,j};
                }
            }
        }
        
        throw new IllegalArgumentException("No two sum solution");
    }
}
```
* Reverse Integer
```
class Solution {
    public int reverse(int x) {
        // inputs
        int input = x;
        int result = 0;
        
        // min max values
        int maxOthers = Integer.MAX_VALUE / 10;
        int maxLast = Integer.MAX_VALUE % 10;
        int minOthers = Integer.MIN_VALUE / 10;
        int minLast = Integer.MIN_VALUE % 10;
        

        while (input != 0) {
            int remainder = input % 10;

            if (0 < x && (result > maxOthers || (result == maxOthers &&  maxLast < remainder))) {
                return 0; // 1. positive overflow
            } else if (x < 0 && ((result < minOthers || (result == minOthers &&  remainder < minLast)))) {
                return 0; // 2. negative overflow
            }

            result = result*10 + remainder; // shift int to left and add the remainder
            input = input / 10;             // shift int to right
        }
        
        return result;
    }
}
/*
1. consider overflow cases
   - positive overflow:
     result is bigger than 214748364 OR result is 214748364 and remainder is bigger than 7
   - negative overflow
     result is bigger than 214748364 OR result is 214748364 and remainder is bigger than 8
*/
```
* Palindrome Number
```
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else {
            int input = x;
            int reverse = 0;
            
            while (input > 0) {
                reverse = reverse*10 + input%10;
                input = input/10;
            }
            
            return reverse == x;
        }
    }
}

/*
1. solution with string conversion
    // String
    if (x < 0) {
        return false;
    } else {
        String original = String.valueOf(x);
        String reverse = original
    }
*/
```
* Roman to Integer
```
public class Solution {
    
       
    
    // subtract twice when necessary to make it even and subtract on top of it.
    public int romanToInt(String s) {
        int result = 0;
        String input = s;
        int prev = 9999;
                
        for (int i=0; i<input.length(); i++) {
            int curr = getInt(input.charAt(i));
            // 1. First, add all
            result += curr;
            
            if (prev < curr) {
                // 2. Second, subtract twice when necessary (to make it even and subtract)
                result -= prev*2;        
            }
            prev = curr;
        }
        return result;
    }
    
    // handling last seperately
    public int romanToInt2(String s) {
        int result = 0;
        String input = s;
                
        for (int i=0; i<input.length()-1; i++) {
            int r1 = getInt(input.charAt(i));
            int r2 = getInt(input.charAt(i+1));
            
            if (r1 < r2) {
                result -= r1;
            } else {
                result += r1;
            }
        }
        result += getInt(input.charAt(input.length() - 1));
        return result;
    }
    
    private int getInt(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
/*
1. logic
   if char[i] <  char[i+1] then subtract char[i]
   if char[i] >= char[i+1] then add char[i] 
   
2. other logic
    add all anyway while adding subtract twice if the next char is larger

2. HashMap method
   HashMap<String, Integer> riMap = new HashMap();
   riMap.put("I",1);        

3. substring method
   string.subString(start, end) returns string from start to end-1

4. charAt method
   string.charAt(1);
   
5. think about creating a private method instead of HashMap (faster)
*/
```
* Longeset Common Prefix
```
class Solution {
    
    // trying without firstStr and add
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        // i is index in string
        for (int i=0; i<strs[0].length(); i++) {
            char curr = strs[0].charAt(i);
            
            // j index in string array
            for (int j=1; j<strs.length; j++) {
                if (strs[j].length()-1 < i || curr != strs[j].charAt(i)) {
                    // found unmatch at char i
                    return strs[0].substring(0,i);
                }
            }
        }
        // everything matched
        return strs[0];
    }
    
    // original attempt
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length < 1) {
            return "";
        }
        
        String result = "";
        String firstStr = strs[0];
        boolean add = true;
        
        // i is index in string
        for (int i=0; i<firstStr.length() && add; i++) {
            char curr = firstStr.charAt(i);
            
            // j index in string array
            for (int j=1; j<strs.length && add; j++) {
                
                if (strs[j].length()-1 < i || curr != strs[j].charAt(i)) {
                    add = false;
                }
            }
            
            if (add) {
                result += curr;
            }
        }
        return result;
    }
}
```
* Valid Parenthesis
```
class Solution {
    public boolean isValid(String s) {
        String stack = "";
        
        // closings are inproper
        for (char c : s.toCharArray()) {
            if (c == '(' || c== '{' || c== '[') {
                stack = c + stack;
            } else {
                if (stack.length() == 0 || getClosing(stack.charAt(0)) != c) {
                    // closing is remaining or not proper
                    return false;
                } else {
                    stack = stack.substring(1);
                }
            }
        }
        
        if (stack.length() > 0) {
            // opening is remaining;
            return false;
        }
        return true;
    }
    
    private char getClosing(char open) {
        switch (open) {
            case '(':
                return ')';
            case '{':
                return '}';
            case '[':
                return ']';
            default:
                throw new IllegalArgumentException("Wrong bracket type");
        }
    }
}

/*
1. logic
   keep starting brackets until closing bracket if found
   
2. .legnth vs .length() vs .size()
   .length: java array     (ex int[])
   .length(): java String 
   .size(): java object

3. String to chars
   "Ho".toCharArray()
   for (char c : String.toCharArray(s))
   
4. char + String does auto conversion
    'c' + "hihi" => "chihi"
    
5. substring
   "str".substring(0,2) => "st"
   "str".substring(1) => "tr"
   "t".substring(1) => ""         
   if you do substring(length) it returns the emtpy string.
   if you go over it throws index range exception
*/
```
* Merge Two Sorted List
```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    
    // recursive solution
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
		if (l1 == null) {
            return l2;
        }
        
		if (l2 == null) {
            return l1;
        }
        
		if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
    }
    
    // iterative solution
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode from = l1;
        ListNode insert = null;
        ListNode to1 = null;
        ListNode to2 = l2;
        
        while (from != null) {
            if (to2 == null) {
                // reached the tail of to2;
                if (to1 == null) {
                    // l2 is empty
                    l2 = from;
                } else {
                    // l2 is not-empty
                    to1.next = from;
                }
                break;
            }
            
            if (from.val <= to2.val) {
                // insert
                insert = from;
                from = from.next;
                insert.next = to2;
                
                if (to1 != null) {
                    // insert a non-head
                    to1.next = insert;
                    to1 = to1.next;
                } else {
                    // insert a head
                    to1 = insert;
                    l2 = to1;
                }
            } else {
                // traverse
                to1 = to2;
                to2 = to2.next;
            }
        }
        
        return l2;
    }
}

/*
recursion

iteration
1. logic (non-head)
   l1:     insert
   l2: to1   -   to2
   
2. logic (head)
   l1:      insert
   l2: null  -   to2
   
   1 2 4
   1
*/
```
