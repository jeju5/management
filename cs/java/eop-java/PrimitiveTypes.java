public class PrimitiveTypes {
    // java int is 32 bits
    // java short is 16 bits

    // << >> is signed shifting
    // <<< >>> is unsigned shifting (looks more brutal. just pushes without considering sign)
    
    // & | ~ is bitwise operator    (^ is XOR)
    // && || ! is logical operator
    
    // pass-by-value
    // passing copied value to a function
    // in java, when primitive type variable is passed, its copied-value is passed.
    // so no change is made to the original variable

    // pass-by-reference
    // passing reference to a function
    // in java, when reference type variable is passed, its reference is passed.
    // so change may be made to the original variable.
    // String is a reference type.

    // Exp XOR q === (~p & q) | (p & ~q)
    // to get binary System.out.println(Integer.toBinaryString(-62));

    // wrapper type keywords
    // Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.SIZE

    // autoBoxing
    // java automatically converts primitive types to its wrapper type. (not all cases)
    // Character c = 'a' valid autoboxing
    // Character[] c = new char[]{'a', 'b'} invalid autoboxing
    
    // @brief counts bits (p24)
    public static int countBits(int x) {
        int count = 0;

        while (x != 0) {
            // in Java you can't take 'x & 1' as boolean, not like C.
            // if (1) <-- invalid syntax
            count += x & 1; 
            x >>>= 1;
        }
        return count;
    }

    // @brief returns parity
    // O(n); n is word size
    public static int parity1(int x) {
        int result = 0;

        while (x != 0) {
            result ^= (x & 1);
            x >>>= 1;
        }
        return result;
    }

    // @brief parity with better approach
    // O(k); k is number of set bit
    // improves best case & average case
    public static int parity2(int x) {
        int result = 0;

        while (x != 0) {
            // 0 -> 1 -> 0 -> 1
            result ^= (1);
            // erases lowest bit set as 1(lowest set bit) in x
            // this is because to perform x-1
            // the lowest set bit of x will be decomposed as
            // 1111 -> 0111 (example)
            x = x & (x-1);
        }
        return result;
    }

    // parity of 11011111 is same as parity of (1101 XOR 1111)
    // -> parity(1101111) == parity(1101^1111)
    // because XOR sets a bit to 0 for paired 1s.
    public static int parity3(int x) {
        // this makes right 16 bits have (left-16-bit XOR right-16-bit)
        // the key is we don't care about left 16 bits after ^= operation
        x ^= x >>> 16;

        // this makes right  8 bits have (left-8-bit  XOR right-8-bit)
        // the key is we don't care about left 24 bits after ^= operation
        x ^= x >>> 8;
        x ^= x >>> 4;
        x ^= x >>> 2;
        x ^= x >>> 1;

        // the right most bit has the pairty of x
        return (int)(x & 0x1);
    }



    


    
    
    public static void main(String[]bargs){
        System.out.println("Testing countBits");
        System.out.println("expected: 5, actual: " + countBits(62));   // 00000000000000000000000000111110 (5)
        System.out.println("expected: 27, actual: " + countBits(-62)); // 11111111111111111111111111000010 (27)
        System.out.println();

        System.out.println("Testing parity1");
        System.out.println("expected: 1, actual: " + parity1(62));
        System.out.println("expected: 0, actual: " + parity1(63));
        System.out.println();

        System.out.println("Testing parity2");
        System.out.println("expected: 1, actual: " + parity2(62));
        System.out.println("expected: 0, actual: " + parity2(63));
        System.out.println();

        System.out.println("Testing parity3");
        System.out.println("expected: 1, actual: " + parity3(-62));
        System.out.println("expected: 0, actual: " + parity3(63));
        System.out.println();
    }
}