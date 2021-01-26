/** LinkedListDeque stores items in linked nodes.
 * @author Najiyu Sanee. */
public class LinkedListDeque<T> implements Deque61B<T> {
    /** Item nodes subclass used to store and link items in the list.
     * */
    private class ItemNode {
        /**
         * Item at a node.
         */
        private T item;
        /**
         * Previous node.
         */
        private ItemNode prev;
        /**
         * Next node.
         */
        private ItemNode next;

        /** Constructor for itemNode.
         * @param itemEntry is item to store at node.
         * @param nextNd is the next node.
         *
         * @param prevNd is the previous node.
         * */
        private ItemNode(T itemEntry, ItemNode nextNd, ItemNode prevNd) {
            item = itemEntry;
            prev = prevNd;
            next = nextNd;
        }

        /** Recursive helper to get items from a list.
         *  @param startPoint is a given index decreasing to zero.
         *  @param startDir Character 'B' to start from the back of list
         *  or 'F' to start from the front of list.
         * @return item at a node when startPoint is 0.
         * */
        private T getHelper(int startPoint, Character startDir) {
            if (startPoint > 0 && startDir == 'F') {
                return this.next.getHelper(startPoint - 1, startDir);
            } else if (startPoint > 0 && startDir == 'B') {
                return this.prev.getHelper(startPoint - 1, startDir);
            }
            return this.item;
        }
    }

    /**
     * Size of the deque.
     */
    private int size;
    /**
     * Sentinel node is a link connecting the front and back nodes.
     */
    private ItemNode sentinel;
    /**
     * Node at the back of the deque.
     */
    private ItemNode last;

    /**
     * Constructor for an empty linked list deque.
     * */
    public LinkedListDeque() {
        sentinel = new ItemNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        last = sentinel.next;
        size = 0;
    }

    /** Constructor for linked list deque with single item.
     * @param entry item to initialize.
     * */
    public LinkedListDeque(T entry) {
        sentinel = new ItemNode(null, null, null);
        sentinel.next = new ItemNode(entry, sentinel, sentinel);
        last = sentinel.next;
        sentinel.prev = last;
        size = 1;
    }

    /** Constructor for a copy of another deque.
     * @param other is LinkedListDeque to copy.
     * */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new ItemNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        last = sentinel.next;
        size = 0;

        ItemNode pointer = other.sentinel.next;
        while (pointer.item != null) {
            this.addLast(pointer.item);
            pointer = pointer.next;
        }
    }


    /**
     * Returns size of a LinkedListDeque.
     * */
    @Override
    public int size() {
        return size;
    }


    /** Compares two LinkedListDeque objects by size.
     * @param o Another LinkedListDeque.
     * @return size difference.
     * */
    @Override
    public int compareTo(Object o) {
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        return this.size - other.size;
    }

    /** Add a given item to deque beginning.
     * @param item is the given item.
     * */
    @Override
    public void addFirst(T item) {
        ItemNode oldFirst = sentinel.next;
        sentinel.next = new ItemNode(item, oldFirst, sentinel);
        oldFirst.prev = sentinel.next;
        size += 1;
        if (last == sentinel) {
            last = sentinel.next;
        }
    }

    /** Add a given item to the end of a deque.
     * @param item is the given item. */
    @Override
    public void addLast(T item) {
        ItemNode oldLast = last;
        last = new ItemNode(item, sentinel, oldLast);
        oldLast.next = last;
        size += 1;
    }

    /** Removes first item from the deque.
     * @return removed item. */
    @Override
    public T removeFirst() {
        T firstItem = sentinel.next.item;
        if (sentinel.next != sentinel) {
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            if (sentinel.next == sentinel) {
                last = sentinel.next;
            }
        }
        return firstItem;
    }

    /** Remove last item from the deque.
     * @return removed item. */
    @Override
    public T removeLast() {
        /* Removes last item from the list and returns it*/
        T lastItem = last.item;
        if (last != sentinel) {
            last = last.prev;
            last.next = sentinel;
            size -= 1;
        }
        return lastItem;
    }


    /** Get an item from the deque.
     * @param index of item to get.
     * @return item at given index. */
    @Override
    public T get(int index) {
        /* Iteratively get item at given index */
        ItemNode pointer = this.sentinel.next;
        if (pointer == sentinel) {
            return null;
        }
        while (index > 0) {
            pointer = pointer.next;
            index -= 1;
        }
        return pointer.item;
    }

    /** Recursively get item from the deque.
     * @param index of an item to get.
     * @return item at given index. */
    public T getRecursive(int index) {
        /* Recursively get item at given index*/
        if (this.isEmpty()) {
            return null;
        }

        if (index > ((size - 1) / 2)) {
            return last.getHelper(size - 1 - index, 'B');
        }
        return sentinel.next.getHelper(index, 'F');
    }

    /** Prints out all items in the deque. */
    @Override
    public void printDeque() {
        ItemNode pointer = sentinel.next;
        while (pointer.item != null) {
            String itemString = String.valueOf(pointer.item);
            System.out.print(itemString + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }
}
