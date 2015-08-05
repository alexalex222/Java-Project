/**
 * Created by Kuilin on 6/29/2015.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] values;
    private int index;
    private int size;

    public RandomizedQueue() {
        values = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public int size() {
        return this.size;
    }

    public void enqueue(Item item) {
        if(item == null) {
            throw new NullPointerException("Cannot enqueue null!");
        }

        if(index == values.length) {
            resizeSimple(values.length*2);
        }

        values[index] = item;
        index++;
        size++;
    }

    public Item dequeue() {
        if(index > 0 && index == (values.length)/4) {
            resizeSimple(values.length/2);
        }

        if(size == 0) {
            throw new NoSuchElementException();
        }

        Item val = sample(true);
        size--;
        return val;
    }

    public Item sample() {
        return sample(false);
    }

    private Item sample(boolean delete) {
        if(size == 0) {
            throw new NoSuchElementException();
        }

        // we pick a random element
        int sampleIndex = StdRandom.uniform(index);
        Item val = values[sampleIndex];

        if(delete) {
            index--;
            Item finalValue = values[index];
            values[index] = null;
            values[sampleIndex] = finalValue;
        }

        return val;
    }

    private void resizeSimple(int newArraySize) {
        Item[] newArray = (Item[]) new Object[newArraySize];
        for(int i = 0; i < index; i++) {
            newArray[i] = values[i];
        }
        values = newArray;
    }


    @Override
    public Iterator<Item> iterator() {
        return new InternalIterator();
    }

    private class InternalIterator implements Iterator<Item> {

        // NOItemE NOItem threadsafe

        // each iterator must return items in a RANDOM order
        private int[] order;
        private int itIndex;

        public InternalIterator() {
            itIndex = 0;
            order = new int[size];
            for (int i = 0; i < order.length; i++) {
                order[i] = i;
            }
            StdRandom.shuffle(order);
        }

        @Override
        public boolean hasNext() {
            return itIndex < order.length;
        }

        @Override
        public Item next() {

            if (itIndex >= order.length) {
                throw new NoSuchElementException();
            }

            Item val = values[order[itIndex]];
            itIndex++;
            return val;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    //
    // main
    //

    public static void main(String[] args) {
        System.out.println("RandomizedQueue");
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        System.out.println("Enter element to push, or \"-\" to pop, or \"ss\" to sample");
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) {
                queue.dequeue();
            } else if (s.equals("ss")) {
                System.out.println("sample:" + queue.sample());
            } else {
                queue.enqueue(s);
            }
            System.out.println(queue.toString());
        }
    }
}