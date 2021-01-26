import org.junit.Test;
public class ArrayDequeTest {

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    public static void emptyAddRemoveTest(){
        System.out.println("Running emptyAddRemoveTest");
        //test isEmpty check
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        boolean passed = ad1.isEmpty();
        printTestStatus(passed);

        //test add from the back and remove from the back
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.removeLast();
        ad1.removeLast();
        ad1.addLast(3);
        ad1.addLast(4);
        ad1.removeLast();
        passed = (ad1.removeLast() == 3) && ad1.isEmpty();
        printTestStatus(passed);
        if (!passed) {
            return;
        }


        //test adding and removing from the front
        ad1.addFirst(3);
        ad1.addFirst(2);
        ad1.removeFirst();
        ad1.removeFirst();
        ad1.addFirst(1);
        ad1.addFirst(0);
        ad1.removeFirst();
        passed =(ad1.removeFirst() == 1) && ad1.isEmpty();
        printTestStatus(passed);
        if (!passed) {
            return;
        }

        //mixed add/remove test
        ad1.addLast(0);
        ad1.addFirst(-1);
        ad1.addLast(1);
        ad1.addFirst(-2);
        passed = ad1.removeLast() == 1;
        passed = ad1.removeLast() == 0;
        passed = ad1.removeFirst() == -2;
        passed = ad1.removeLast() == -1;
        ad1.addLast(9);
        ad1.addFirst(8);
        ad1.addLast(10);
        passed = ad1.removeFirst() == 8;
        passed = ad1.removeFirst() == 9;
        passed = ad1.removeLast() == 10;
        printTestStatus(passed);
    }


    public static void testResize() {
        System.out.println("Running testResize");
        //test optimization for expanding size when list is full
        //AND then test optimization for 25% usage
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        //BASIC resize TEST! Should've done this AlWAYS, cause I ended up
        //missing a bug that wasn't copying all 8 items at very start.
        //So add 9 items so it expands on the 9th item, then remove all.
        for (int i = 0; i < 9 ; i++) {
            ad1.addLast(i);
        }
        boolean passed = false;
        for (int i = 0; i < 9; i++) {
            passed = i == ad1.removeFirst();
        }

        printTestStatus(passed);
        if (!passed) {
            return;
        }
        //Test resize expansion, add 298 items (Integers -149 to 149)
        for (int i = 1; i < 150; i +=1) {
            ad1.addLast(i);
            ad1.addFirst(-i);
        }

        //297th item is last Index starting from 0
        passed = ad1.get(297) == 149;
        passed = passed && ad1.get(0) == -149;
        printTestStatus(passed);
        if (!passed) {
            return;
        }

        //Test resize contraction, remove 278 items, integers  -149 to -11 removed and same for positives.
        for (int i = 1; i < 140; i +=1) {
            ad1.removeLast();
            ad1.removeFirst();
        }
        //20 items remaining, check if first is -10 and last is 10
        passed = passed && ad1.get(0) == -10;
        passed = passed && ad1.get(19) == 10;
        printTestStatus(passed);
    }

    public static void testCompare() {
        System.out.println("Running testCompare");
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ArrayDeque<String> ad2 = new ArrayDeque<>();
        ad2.addFirst("One");
        ad2.addLast("Two");
        ad1.addFirst(1);

        boolean passed = ad2.compareTo(ad1) == 1;
        printTestStatus(passed);

    }

    public static void testCopy() {
        System.out.println("Running testCopy");
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ad1.addFirst(1);
        ad1.addFirst(0);
        ad1.addLast(3);
        ArrayDeque<Integer> ad1_copy = new ArrayDeque<>(ad1);

        boolean passed = ad1.get(0) == ad1_copy.get(0);
        passed = passed && ad1.get(2) == ad1_copy.get(2);
        printTestStatus(passed);
    }

    public static void testEquals() {
        System.out.println("Running test_equals");
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 11; i++) {
            ad1.addFirst(-i);
            ad1.addLast(i);
        }
        ArrayDeque<Integer> ad2 = new ArrayDeque<>(ad1);
        //this should be equal
        boolean passed = ad2.equals(ad1);
        ArrayDeque<Integer> ad3 = new ArrayDeque<>(ad2);
        ad3.addLast(0);
        //this should not be equal
        passed = passed && (!ad3.equals(ad2));
        printTestStatus(passed);
    }

    public static void testIterator() {
        System.out.println("Running testIterator");
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 11; i++) {
            ad1.addLast(i);
            ad1.addFirst(-i);
        }
        int total = 0;
        for (int num:ad1) {
            total += num;
            System.out.print(num + " + ");
        }
        System.out.println("0 = " + total);
        System.out.println();
    }

    public static void main(String[] args) {
        emptyAddRemoveTest();
        testResize();
        testCompare();
        testCopy();
        testIterator();
        testEquals();
    }
}
