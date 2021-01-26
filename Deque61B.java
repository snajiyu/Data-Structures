/** Deque interface provides API for any type of list deque.
 * @author Najiyu Sanee. */
public interface Deque61B<T> extends Comparable<T> {
    /**
     * Adds an item of type T to the front of the deque.
     * @param item to add.
     */
    public void addFirst(T item);

    /**
     * Adds an item of type T to the back of the deque.
     * @param item to add.
     */
    public void addLast(T item);

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     * @return
     */
    public T removeFirst();

    /**
     *  Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     * @return
     */
    public T removeLast();

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * @param index of item to get.
     * @return
     */
    public T get(int index);

    /**
     * Returns the number of items in the deque.
     * @return
     */
    public int size();

    /**
     * Returns true if deque is empty, false otherwise.
     * @return
     */
    default public boolean isEmpty() {
        return size() == 0;
    };

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque();
}
