
/**
 * Created by Kuilin on 7/12/2015.
 */

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQ<Key> implements Iterable<Key> {

    private Key[] pq;    // heap-ordered complete binary tree
    private int N;       // in pq[1..N] with pq[0] unused
    private Comparator<Key> comparator;  // optional Comparator


    /**
            * Initializes an empty priority queue with the given initial capacity.
            * @param maxN the initial capacity of the priority queue
    */
    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    /**
     * Initializes an empty priority queue.
     */
    public MaxPQ() {
        this(1);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity,
     * using the given comparator.
     * @param maxN the initial capacity of the priority queue
     * @param comparator the order in which to compare the keys
     */

    public MaxPQ(int maxN, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[maxN + 1];
        N = 0;
    }

    /**
     * Initializes an empty priority queue using the given comparator.
     * @param comparator the order in which to compare the keys
     */

    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    /**
     * Initializes a priority queue from the array of keys.
     * Takes time proportional to the number of keys, using sink-based heap construction.
     * @param keys the array of keys
     */

    public MaxPQ(Key[] keys) {
        N = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < N; i++)
            pq[i+1] = keys[i];
        for (int k = N/2; k >= 1; k--)
            sink(k);
        assert isMaxHeap();
    }


    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Returns a largest key on the priority queue.
     * @return a largest key on the priority queue
     * @throws java.util.NoSuchElementException if the priority queue is empty
     */

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }


    public int size() {
        return N;
    }


    public Key delMax() {
        if(isEmpty()) throw new NoSuchElementException("Priority queue under flow!");
        Key max = pq[1];     // Retrieve max key from top.
        exch(1, N--);        // Exchange with last item.
        pq[N=1] = null;      // Avoid loitering.
        sink(1);             // Restore heap property.
        if((N>0) && (N == (pq.length -1 )/4)) resize(pq.length/2);
        assert isMaxHeap();
        return max;
    }

    // helper function to double the size of the heap array

    private void resize(int capacity) {
        assert capacity > N;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= N; i++) temp[i] = pq[i];
        pq = temp;
    }

    /**
     * Adds a new key to the priority queue.
     * @param x the new key to add to the priority queue
     */

    public void insert(Key x) {
        // double size of array if necessary
        if(N >= pq.length -1) resize(2*pq.length);

        // add x and percolate it to maintain heap invariant
        pq[++N] = x;
        swim(N);
        assert isMaxHeap();
    }


    private void swim(int k) {
        while(k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while(2*k < N) {
            int j = 2*k;
            if(j < N && less(j+1, j)) j++;
            if(!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        if(comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    private boolean isMaxHeap(int k) {
        if(k > N) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= N && less(k, left))  return false;
        if (right <= N && less(k, right)) return false;
        return isMaxHeap(left) && isMaxHeap(right);
    }

    /***********************************************************************
     * Iterator
     **********************************************************************/

    /**
     * Returns an iterator that iterates over the keys on the priority queue
     * in descending order.
     * The iterator doesn't implement remove() since it's optional.
     * @return an iterator that iterates over the keys in descending order
     */

    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        // create a new pq
        private MaxPQ<Key> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            if (comparator == null) copy = new MaxPQ<Key>(size());
            else                    copy = new MaxPQ<Key>(size(), comparator);
            for (int i = 1; i <= N; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }


    /**
     * Unit tests the MaxPQ data type.
     */
    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }


}
