/** The ArrayDeque stores items in a resizing and
 *  circular Array structure.
 * @author Najiyu Sanee. */
public class ArrayDeque<T> implements Deque61B<T> {

    /**
     * Array to store items.
     */
    private T[] items;
    /**
     * Total size of stored items.
     */
    private int size;
    /**
     * Index of next open position at
     * the front of the deque.
     */
    private int nextFirst;
    /**
     * Index of next open position at
     * the back of the deque.
     */
    private int nextLast;


    /**
     * Helper method to get the index
     * of item currently at front of deque.
     * @return int index.
     */
    private int getCurrFirst() {
        if (nextFirst + 1 == items.length) {
            return 0;
        }
        return nextFirst + 1;
    }

    /**
     * Helper method to get index of item at the
     * back of the deque.
     * @return int index.
     */
    private int getCurrLast() {
        if (nextLast - 1 < 0) {
            return items.length - 1;
        }
        return nextLast - 1;
    }

    /**
     * Constructor for the ArrayDeque with able to store
     * eight items.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = (items.length / 2) - 1;
        nextLast = nextFirst;
        size = 0;
    }

    /**
     * Constructor for an arrayDeque using copy
     * of an existing arrayDeque.
     * @param other ArrayDeque.
     */
    public ArrayDeque(ArrayDeque<T> other) {
        items = (T[]) new Object[other.size()];
        nextFirst = (items.length / 2) - 1;
        nextLast = nextFirst;
        size = 0;

        for (int i = 0; i < other.size(); i += 1) {
            this.addLast(other.get(i));
        }
    }

    /**
     * Resize the ArrayDeque to a new size,
     * then copy items to the new deque.
     * @param newSize int size to expand/contract to.
     */
    private void resize(int newSize) {
        /*
         *  Copy items from old array to new array, in correct order.
         *  When circular, copy first segment till end of array
         *  then second segment from the start of array.
         * */
        T[] newItems = (T[]) new Object[newSize];
        int first = getCurrFirst();
        int last = getCurrLast();
        int newStart = (newSize / 2) - 1;

        if ((last > first) && last < items.length) {
            System.arraycopy(items,
                    first,
                    newItems,
                    newStart,
                    last + 1 - first);
        } else {
            System.arraycopy(items,
                    first,
                    newItems,
                    newStart,
                    items.length - first);
            System.arraycopy(items,
                    0,
                    newItems,
                    newStart + (items.length - first),
                    nextLast);
        }

        /**
         *  Adjust indices for next and last items as per new items array.
         *  */
        nextFirst = newStart - 1;
        if (newStart + size >= newItems.length) {
            nextLast = size - (newItems.length - newStart);
        } else {
            nextLast = newStart + size;
        }
        items = newItems;
    }

    /**
     * Return total number of items in the deque.
     * @return int size.
     */
    @Override
    public int size() {
        return size;
    }

    /** Compares two ArrayDeque objects by size.
     * @param o another ArrayDeque.
     * @return size difference.
     * */
    @Override
    public int compareTo(Object o) {
        ArrayDeque<T> deck = (ArrayDeque<T>) o;
        return this.size() - deck.size;
    }

    /**
     * Check if an ArrayDeque is equal to another object.
     * Equality only if exactly the same in every way.
     * @param o another object.
     * @return true or false.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        ArrayDeque<T> obj = (ArrayDeque<T>) o;
        if (this.size() != obj.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(obj.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add item to the front of the deque.
     * @param item to add.
     */
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            this.resize(size * 2);
        }
        if (size == 0) {
            nextLast += 1;
        }
        items[nextFirst] = item;
        size += 1;

        if (nextFirst - 1 < 0) {
            nextFirst = items.length - 1;
            return;
        }
        nextFirst -= 1;
    }

    /**
     * Add item to the back of the deque.
     * @param item to add.
     */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            this.resize(size * 2);
        }
        if (size == 0) {
            nextFirst -= 1;
        }
        items[nextLast] = item;
        size += 1;

        if (nextLast + 1 == items.length) {
            nextLast = 0;
            return;
        }
        nextLast += 1;
    }

    /**
     * Remove front item from the deque.
     * @return removed item.
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int i = getCurrFirst();
        T oldFirst = items[i];
        nextFirst = i;
        size -= 1;
        if ((size < items.length * 0.25) && items.length > 16) {
            this.resize(items.length / 2);
        }
        return oldFirst;
    }

    /**
     * Remove item from the back of the deque.
     * @return removed item.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int i = getCurrLast();
        T oldLast = items[i];
        nextLast = i;
        size -= 1;
        if ((size < items.length * 0.25) && items.length > 16) {
            this.resize(items.length / 2);
        }
        return oldLast;
    }

    /**
     * Retrieve item from a given position in the deque.
     * @param index of item to get.
     * @return
     */
    @Override
    public T get(int index) {
        if (index > this.size()) {
            return null;
        }
        int first = getCurrFirst();
        if (first + index < items.length) {
            return items[first + index];
        }
        return items[first + index - items.length];
    }

    /**
     * Print all items in the deque from front to back.
     */
    @Override
    public void printDeque() {
        int pointer = 0;
        while (pointer < this.size) {
            System.out.print(this.get(pointer) + " ");
            pointer += 1;
        }
        System.out.println();
    }
}
