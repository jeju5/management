# Leet Code

## Easy
### Two Sum
```java
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
#### Reverse Integer
```java
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
* Remove Duplicates from Sorted Array
```
class Solution {
    
    public int removeDuplicates(int[] nums) {        
        int distinctCount = nums.length > 0 ? 1 : 0;
        
        for (int n : nums) {
            if (n > nums[distinctCount-1]) {
                // if n is larger than the last element of distinct elements, insert in the end
                nums[distinctCount++] = n;
            }
        }
            
        return distinctCount;
    }
    
    // with shifting (not efficient approach)
    public int removeDuplicates1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int distinctCount = nums.length;
        int min = nums[0];

        for (int i=1; i<distinctCount; i++) {
            if (min == nums[i]) {
                // found duplicate -> shift left
                for (int j=i; j<distinctCount-1; j++) {
                    nums[j] = nums[j+1];
                }
                distinctCount--;
                i--;
            } else {
                // found distinct -> pass
                min = nums[i];
            }
        }

        return distinctCount;
    }
}
```
* Remove Element
```
class Solution {
    public int removeElement(int[] nums, int val) {
        int keepIndex = 0;
        
        
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != val) {
                // found an element to keep
                nums[keepIndex++] = nums[i];
            }
        }
        
        return keepIndex;
    }
}
```
* Implement strStr()
```
class Solution {
    
    public int strStr(String haystack, String needle) {
        int haystackLength = haystack.length();
        int needleLength = needle.length();
        
        if (haystackLength < needleLength) {
            return -1;
        }
        
        if (needleLength == 0) {
            return 0;
        }
        
        for (int i=0; i<haystackLength; i++) {
            if (haystackLength-1 < i+needleLength-1 ) {
                // than haystack end index smaller than needle end index
                return -1;
            }
            
            if (haystack.charAt(i) == needle.charAt(0)) {
                // first char match
                
                if (needleLength == 1) {
                    return 0;
                }
                
                for (int j=1; j<needleLength; j++) {
                    if (haystack.charAt(i+j) == needle.charAt(j)) {
                        // other char match
                        
                        if (j == needleLength-1) {
                            // last char match
                            return i;
                        }
                    } else {
                        // unmatch
                        break;
                    }
                }
            }
        }
        
        return -1;
    }
}
```
* Search Insert Position
```
class Solution {
    
    // time: O(log n)
    public int searchInsert(int[] nums, int target) {
        int low=0;
        int high=nums.length-1;
        
        while (low < high) {
            int mid = low + (high-low)/2;
            if (nums[mid]<target) {
                low=mid+1;
            } else {
                high=mid;  
            }
        }
        
        return (nums[low] < target) ? low+1 : low;
    }
    
    // time: O(n)
    public int searchInsert1(int[] nums, int target) {
        
        for (int i=0; i<nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }
        return nums.length;
    }
}

/*

1. distance between start,end
    = j-i (becuase "start + distance = end")
   
2. distance to the mid from start
    = (end-start)/2

    ex) [i,*,_,j]    even elements
        0     3

       [i,_,*,_,j]  odd elements
        0   2   4
       
3. mid-left
    = start + (end-start)/2
   
   mid-right
    = start + (end-right+1)/2  (add +1 on distance to get it pass "2" divisor)
   
   why this is better than mid = (start + end)/2?
    => overflow possibility
   
4. where are we inserting? (what answer are we seeking)
    => smallest index such that index-1 is smaller than the target
   
5. divide and conquer approach
    repeat ["smaller or equal" mid, "larger"] divide

    what is i?
    =>i in the index where T is bounnd  (arr[i-1] < T < arr[i+1])
    
    where to insert?
    => case: T < arr[i]  insert at i
    => case: T == arr[i] insert at i
    => case: arr[i] < T  insert at i+1
    
    ex) eventually it will be [4,8] at one point.
    1. searching right means
       - 1 will be i and "T = 5,6,7,8,9,10..."
         - insert happens at i   if (T <= value at i)
         - insert happens at i+1 if (value at i < T ) 
    2. searching left means
       - 0 will be i and "T = ... 2,3,4"
         - T is smaller than value at i
         - insert always happens at i (T <= value at i)
*/
```
* Count and Say
```
class Solution {
    // hybrid (recursion + hybrid)
    // time O(n^2) because O(n) for for-loop but is repeated for all recursive calls.
    public String countAndSay(int n) {
        
        if (n==1) {
            return "1";
        }
        
        // get number to count and say
        String toSay = countAndSay(n-1);
        String result = "";
        
        // iteration
        for (int i=0; i<toSay.length(); i++) {
            String digit = toSay.substring(i,i+1);
            int count = 1;

            // i is the index of 'comparing digit'
            // count is the distance from 'comparing digit' to 'digit to compare'
            while (i+count < toSay.length() && toSay.substring(i+count, i+count+1).equals(digit)) {
                // get occurence of the digit
                count++;
            }
            
            result = result + Integer.toString(count) + digit;
            
            // count is the length of 'counted section'
            // next i should be i+count, but consider i++ in for loop
            i = i+count - 1;
        }
        
        return result;
    }
}
```
* Maximum Subarray
```
class Solution {
    
    /*
    Dynamic Programming
    time: O(n)
    space: O(n)
    
    maxEndsAt[i]: 
    maximum sum of subarray that ends at i (including i)
    it is either continuing or starting
    1. continuing is MaxEndsAt[i-1] + nums[i]
    2. starting is nums[i]
    
    maxWithin[i]: 
    maximum sum of subarray within [0, i]
    it is either keeping or updating
    1. keeping is MaxWithin[i-1]
    2. updating is MaxEndsAt[i-1]
    
    maxWithin[n-1] will be the answer
    maxWithin is the maximum value among maxEndsAt.
    */
    public int maxSubArray1(int[] nums) {
        int[] maxEndsAt = new int[nums.length];
        int[] maxWithin = new int[nums.length];
        
        maxEndsAt[0] = nums[0];
        maxWithin[0] = nums[0];
        
        for (int i=1; i<nums.length; i++) {
            maxEndsAt[i] = Math.max(maxEndsAt[i-1]+nums[i], nums[i]);
            maxWithin[i] = Math.max(maxWithin[i-1], maxEndsAt[i]);
        }
        return maxWithin[nums.length-1];
    }
    
    /*
    Dynamic programming - optimized
    time: O(n)
    space: O(1)
    
    */
    public int maxSubArray(int[] nums) {
        int maxEndsAtI = nums[0];
        int maxWithinI = nums[0];
        
        for (int i=1; i<nums.length; i++) {
            maxEndsAtI = Math.max(maxEndsAtI+nums[i], nums[i]);
            maxWithinI = Math.max(maxWithinI, maxEndsAtI);
        }
        return maxWithinI;
    }
    
    
    /*
    Divide and conquer
    time: O(nlogn)
    space; O(logn)
    */
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        return maxSubArray(nums, 0, n - 1);
    }

    // maximum array with in nums[lo, hi]
    private int maxSubArray(int[] nums, int lo, int hi) {
        // base case
        if (lo == hi) {
            return nums[lo];
        }
        
        // divide & conquer
        int mid = lo + (hi-lo)/2;
        int leftSum = maxSubArray(nums, lo, mid);        // maximum sum of subarray in [low,mid]
        int rightSum = maxSubArray(nums, mid + 1, hi);   // maximum sum of subarray in [mid+1,hi]
        int crossSum = crossSum(nums, lo, hi);           // maximum sum of subarray that contains mid and mid+1
        
        // combine: maximum subarray will be largest among left, cross, right
        
        return Math.max(crossSum, Math.max(leftSum, rightSum));
    }
    
    // maximum array that contains mid and mid+1
    private int crossSum(int[] nums, int lo, int hi) {
        int mid = lo + (hi-lo)/2;
        
        // get the largest subarray that ends with mid in [lo,mid]
        int leftSum = 0;
        int leftMax = Integer.MIN_VALUE;
        for (int i = mid; lo <= i; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }
        
        // get the largest subarray that starts with mid+1 in [mid+1,hi]
        int rightSum = 0;
        int rightMax = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= hi; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }
        
        return leftMax + rightMax;
    }
}
```
* Length of Last Word
```
class Solution {
    
    
    // solution with split()
    public int lengthOfLastWord1(String s) {
        String[] words = s.split(" ");
        
        if (words.length < 1) {
            return 0;
        }
        return words[words.length-1].length();
    }
    
    
    // solution with trim()
    // length of trimmed s -1 => last index of the last word
    // last index of " "      => index before the beginning of the last word
    public int lengthOfLastWord2(String s) {
        return s.trim().length() - 1 - s.trim().lastIndexOf(" ");
    }
    

    // solution without methods (unclean)
    public int lengthOfLastWord3(String s) {
        char[] chars = s.toCharArray();
        int lastIndexOfLastWord = -1;
        
        for (int i=chars.length-1; -1<i; i--) {
            if (chars[i] != ' ') {
                if (lastIndexOfLastWord == -1) {
                    lastIndexOfLastWord = i;
                }
            } else if (lastIndexOfLastWord != -1) {
                return lastIndexOfLastWord - i;
            }
        }
        if (lastIndexOfLastWord != -1) {
            if (chars[0] != ' ') {
                // the last word is the first word
                return lastIndexOfLastWord + 1;
            } else {
                // the last word is one char
                return 1;
            }
        } else {
            return 0;
        }
    }
    
    
    // solution without methods (clean)
    public int lengthOfLastWord(String s) {
        int length = 0;
		
        for (int i = s.length() - 1; -1 < i; i--) {
            if (s.charAt(i) != ' ') {
                // found non-space char
                length++;
            } else if (length > 0) {
                // found space char before last word
                return length;
            } 
        }
        return length;
    }
}

/*
1. trim()
    " HI ".trim() => "HI"

2. split()
    "G,A,G,,B".split() => ["G","A","G","B"];
    
3. in java
    default int value is 0; (not null)
*/
```
* Plus One
```
class Solution {
    
    // elegant solution
    // if digit is 9 make it 0 -> move on
    // if digit is not 9. add 1 and return
    public int[] plusOne(int[] digits) {
        
        for (int i=digits.length-1; -1<i; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else if (-1 < digits[i] && digits[i] < 9) {
                digits[i]++;
                return digits;
            } else {
                throw new IllegalArgumentException();
            }
        }
        
        if (digits[0] == 0) {
            int[] newDigits = new int[digits.length+1];
            newDigits[0] = 1;
            return newDigits;
        }
        return digits;
    }
    
    public int[] plusOne2(int[] digits) {
        int carry=0;
        digits[digits.length-1]++; // add +1 to last digit
        
        for (int i=digits.length-1; -1<i; i--) {
            // add & reset the carry;
            digits[i] += carry;
            carry = 0;
            
            if (digits[i] > 9) {
                // digit overflow
                digits[i] -= 10;
                carry++;
                
                if (i == 0) {
                    // first digit overflow
                    int[] newDigits = new int[digits.length+1];
                    newDigits[0] = 1;
                    for (int j=0; j<digits.length-1; j++) {
                        newDigits[j+1] = digits[j];
                    }
                    return newDigits;
                }
                    
            } else {
                return digits;
            }
        }
        return digits;
    }
}
```
* Add Binary
```
class Solution {
    public String addBinary(String a, String b) {
        StringBuffer result = new StringBuffer();
        
        int i = a.length() - 1, j = b.length() - 1; // a index
        boolean carry = false;
        
        while (-1 < i || -1 < j) {            
            int sum = 0;
            
            if (-1 < i && a.charAt(i) == '1') {
                sum++;
            }
            
            if (-1 < j && b.charAt(j) == '1') {
                sum++;
            }
            
            if (carry) {
                sum++;
                carry = false;
            }
            
            if (1 < sum) {
                sum -= 2;
                carry = true;
            }
            
            // add digit to front
            result = result.insert(0, sum);
            // System.out.println(result + " " + i + " " + j);
            i--;
            j--;
        }
        
        if (carry) {
            result = result.insert(0, "1");
        }
        
        return result.toString();
    }
}


/*
* loop shorter?
  we have to loop longer string becaue in case of 11 + 1
  we loop 1 and update 11 to 10. with carry 1. but loop end since it loops shorter number.

* index?
  * note that if we loop from the end and two chars have different length, it is going to create
    error because last index of shorter and last index of longer will be different.
  * reversing may be a smart approach
  
* StringBuilder
  * in most cases StringBuilder is faster than String.
  * stringBuilder.reverse()
  * stringBuilder.append()
  * stringBuilder.insert(index, String)
  * stringBuilder.toString()
*/
```
* Sqrt(x)
```
class Solution {
    public int mySqrt(int x) {
        if (x==0) {
            return 0;
        }
        long result = x;
        while (result*result > x)
            result = (result + x/result) / 2;
        return (int) result;
    }
    
    // binary search
    // time: O(logn)
    public int mySqrt1(int x) {
        if (x==0) {
            return 0;
        }
        
        // becareful when you start lo as 0.
        // if low is 0 and x is 1, mid will be 0.
        int lo=1;
        int hi= x;
        int mid;
        
        while (true) {
            mid = lo + (hi-lo)/2;
            
            // truncated division is always closer to 0 than actual division.
            if (x/mid < mid) {
                // instead of x < mid*mid (overflow prevention)
                hi = mid;
            } else if ( x/(mid+1) < (mid+1) ) {
                // instead of x < (mid+1)*(mid+1) (overflow prevention)                
                break;
            } else {
                lo = mid;
            }
        }
        return (int) mid;
    }
    
    // brute force
    // time: O(n)
    public int mySqrt2(int x) {
        long original = (long) x;
        long result = x/2;
        
        
        if (original == 1) {
            return 1;
        }
        
        while (0 < result) {
            if (result * result <= x) {
                break;
            } else {
                result--;
            }
        }
        
        return (int) result;
    }
}

/*
* truncated division is always closer to 0 than actual division.
  * 3.0/2.0  = 1.5
  * 3/2      = 1
  * -3/2     = -1
  * -3.0/2.0 = -1.5
  
* overflow prevention
  1. use long
  2. change comparison
     Is x/y < y same as x < y*y when x,y>0 ? Yes
     - Let's prove <=
       x < y*y
       x.0 < (y.0)*(y.0)
       x/y < x.0/y.0 < y.0 = y (because x/y simply removes decimal points)
       x/y < y
     - Let's prove =>
       x/y < y
       x.0/y.0 < y.0 = y (because y>1)
       x.0 < y.0 * y.0 = y * y
       x < y*y  
       
* int to long casting is automatic
  * int y =10;
  * long x = y;  // x is long 10;
*/
```
* Climbing Stairs
```
class Solution {
    
    // Dynamic Programming
    // O(n)
    public int climbStairs(int n) {
        int[] ways = new int[n+1];
        if (n==1) {
            return 1;
        }
        ways[1] = 1;
        ways[2] = 2;
        
        for(int i=3; i<n+1; i++) {
            // one step before reaching i is i-1 or i-2   
            ways[i] = ways[i-1] + ways[i-2];
        }
        return ways[n];
    }
    
    
    // Recursion
    public int climbStairs1(int n) {
        if (n == 1) {
            // base: 1
            return 1; 
        } else if (n == 2) {
            // base: 2
            return 2; // 2 or 1+1
        } else {
            // step: 3+
            return climbStairs(n-2) + climbStairs(n-1);
        }
    }
}

/*
* Dynamic Programming
  * it is ways[i] = ways[i-1] + ways[i-2]
    because one action before reaching i is i-1 or i-2
  * it is not ways[i-1] + 2*ways[i-2], thinking that there is 1option
    from ways[i-1] and 2options(2, 1+1) from ways[i-2] because
    that will case ways[i-2] to include ways[i-1]. (ways[i-2] -> +1 is same as ways[i-1])
    
* Recursion
  * same concept applies to recursion
*/
```
* Remove Duplicates from Sorted List
```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        
        ListNode curr = head;
        
        while (curr != null && curr.next != null) {
            
            if (curr.val == curr.next.val) {
                // duplicate detected
                // in this case you shouldn't move on because you
                // will miss the case where next.next has the same value
                curr.next = curr.next.next;
            } else {
                // move on
                curr = curr.next;
            }
        }
        
        return head;
    }
}
```
* Merge Sorted Array
```
class Solution {
    
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m-1; // last index in nums1
        int i2 = n-1; // last index in nums2
        int i3 = nums1.length - 1; // last index in merged nums1
        
        // while i2 is not done
        while (-1 < i2) {
            if (-1 < i1 && nums2[i2] < nums1[i1]) {
                // nums1 is not done and
                // element in nums1 is larger
                nums1[i3--] = nums1[i1--];                
            } else {
                // element in nums2 is larger
                nums1[i3--] = nums2[i2--];
            }
        }
    }
}

/*
* always think about edge cases
  * will it work with null?
  * will it work with 0 element?
  * will it work with 1 element?
*/
```
* Same Tree
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            // both are leaves
            return true;
        } else if (p != null && q != null && p.val == q.val) {
            // both have the same val and subtree
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            // not the same
            return false;
        }
    }
}
```
* Symmetric Tree
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    // recursive
    // time: O(n)
    // space
    //  - avg: O(logn) balanced tree
    //  - worst: O(n) worst case is linear tree
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }
    
    // recursive helper
    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            // symmetric as leaves
            return true;
        } else if (left == null || right == null) {
            // not symmetric at current level
            return false;
        } else {
            // leftt and right are not leaves
            if (left.val == right.val) {
                return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
            } else {
                // current level is not symmetric
                return false;
            }   
        }
    }
    
    public boolean isSymmetric1(TreeNode root) {
        Queue<TreeNode> q = new LinkedList();
        
        if (root == null) {
            return true;
        }
        
        
        TreeNode left;
        TreeNode right;
        
        q.add(root.left); // add left
        q.add(root.right); // add right
        
        while (!q.isEmpty()) {
            left = q.poll();
            right = q.poll();
            
            if (left == null && right == null) {
                // symmetric as leaves
                // warning: in iterative case this is not the end of it.
                continue;
            } else if (left == null || right == null) {
                // non-symmetric at current level.
                return false;
            } else if (left.val != right.val) {
                // non-symmetric at current level.
                return false;
            } else {
                // investigate
                q.add(left.left);
                q.add(right.right);
                q.add(left.right);
                q.add(right.left);
            }
        }
        return true;
    }
}

/*

* ((left == null && right != null) || (left != null && right == null))
can be simplified as (left == null || right == null) with if (left == null && right == null)

* Queue is an interface
  - Queue<Integer> q = new LinkedList<Integer>();
  - Queue<Integer> q = new ArrayDeque<Integer>();
  
  - q.add()
  - q.poll()
 
*/
```
* Maximum Depth of Binary Tree
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    // Recursive - DFS (Depth First Search)
    // Time: O(logn) ~ O(n)
    public int maxDepthR(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int deeper = Math.max(maxDepthR(root.left),maxDepthR(root.right));
            
            return 1 + deeper;
        }
        
    }
    
    // Iterative - DFS (Depth First Search)
    // Time: O(n)
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> nodes = new Stack<>();
        Stack<Integer> depths = new Stack<>();

        TreeNode currentNode;
        int currentDepth;
        int maxDepth = 0;
        
        // add root
        nodes.push(root);
        depths.push(1);
        
        while(!nodes.isEmpty()) {
            // get current node & depth and update max depth
            currentNode = nodes.pop();
            currentDepth = depths.pop();
            maxDepth = Math.max(currentDepth, maxDepth);
            
            if (currentNode.left != null) {
                // explore left
                nodes.push(currentNode.left);
                depths.push(currentDepth+1);
            }
            if (currentNode.right != null) {
                // explore right
                nodes.push(currentNode.right);
                depths.push(currentDepth+1);
            }
        }
        return maxDepth;
    }
    
    // Iterative - BFS (Breadth First Search)
    // Time: O(n)
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Queue<TreeNode> q = new LinkedList();    
        TreeNode current;
        int maxDepth = 0;
        int countInCurrentDepth;
        q.add(root);
        
        while (!q.isEmpty()) {
            countInCurrentDepth = q.size();
            maxDepth++;
            
            while (0 < countInCurrentDepth--) {
                current = q.poll();
                
                // add nodes (not leaves)
                if (current.left != null) {
                    q.add(current.left);
                }
                if (current.right != null) {
                    q.add(current.right);
                }
            }
        }

        return maxDepth;
    }
}
```
* Binary Tree Level Order Traversal II
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result  = new ArrayList();
        Queue<TreeNode> nodes = new LinkedList();   // nodes at current depth
        TreeNode node;                              // current node
        int count;                                  // count of nodes in current depth
        nodes.add(root);                            // add root
        
        if (root == null) {
            return result;
        }
        
        while (!nodes.isEmpty()) {
            count = nodes.size();
            ArrayList<Integer> currentNodes = new ArrayList();
            
            while (0 < count--) {
                node = nodes.poll();
                currentNodes.add(Integer.valueOf(node.val));
                
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
            
            result.add(0, currentNodes);
        }
        
        
        
        return result;
    }
}
```
