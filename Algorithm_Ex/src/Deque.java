/**
 * Created by Kuilin on 7/4/2015.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private static class Node<Item> {
        private Item value;
        private Node<Item> before;
        private Node<Item> after;

        public Node(Item value, Node<Item> before, Node<Item> after) {
            this.value = value;
            this.after = after;
            this.before = before;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Node [value=");
            builder.append(this.value);
            builder.append(", before=");
            builder.append(this.before);
            builder.append(", after=");
            builder.append(this.after);
            builder.append("]");
            return builder.toString();
        }
    }

    // pointer to header
    private Node<Item> head;    //  the front of the line
    private Node<Item> tail;    // the back of the line

    // size of line as add/remove items
    private  int size;

    public Deque() {

    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if(item == null) {
            throw new NullPointerException("Cannot add null item!");
        }

        if(head == null) {
            head = new Node<Item>(item, null, null);
            tail = head;
            size++;
            return;
        }

        Node<Item> oldHead = head;
        Node<Item> newHead = new Node<Item>(item, null, oldHead);
        oldHead.before = newHead;
        head = newHead;
        size++;
    }

    public void addLast(Item item) {
        if(item == null) {
            throw new NullPointerException("Cannot add null item!");
        }

        if(tail == null) {
            tail = new Node<Item> (item, null, null);
            head = tail;
            size++;
            return;
        }

        Node<Item> oldTail = tail;
        Node<Item> newTail = new Node<Item> (item, oldTail, null);
        oldTail.after = newTail;
        tail = newTail;
        size++;
    }

    public Item removeFirst() {
        if(head == null) {
            throw new NoSuchElementException("Nothing to remove!");
        }

        Item val = head.value;
        head = head.after;
        if(head != null) {
            head.before = null;
        } else {
            tail = null;
        }
        size--;
        return val;
    }

    public Item removeLast() {
        if(tail == null) {
            throw new NoSuchElementException("Nothing to remove!");
        }

        Item val = tail.value;
        tail = tail.before;
        if(tail != null) {
            tail.after = null;
        } else {
            head = null;
        }
        size--;
        return val;
    }

    @Override
    public Iterator<Item> iterator() {
        return new InternalIterator();
    }

    private class InternalIterator implements Iterator<Item> {
        private Node<Item> index = head;

        @Override
        public boolean hasNext() {
            return index !=null;
        }

        @Override
        public Item next() {
            if(index != null) {
                Item val = index.value;
                index = index.after;
                return val;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        if (head != null) {
//            sb.append("head:" + head.value);
//            Node<Item> current = head.after;
//            // iterate
//            while (current != null) {
//                if (current.after != null) {
//                    sb.append(" node:" + current.value);
//                } else {
//                    //don't print tail here, print below
//                    // (so also prints when only 1 element)
//                }
//                current = current.after;
//            }
//            // if there is a head, should always be a tail
//            sb.append(" tail:" + tail.value);
//        } else {
//            sb.append("head:null ");
//            sb.append("tail:null ");
//        }
//        return sb.toString();
//    }

    // unit test

    public static void main(String[] args) {
        System.out.println("Deque");
        Deque<String> deque = new Deque<String>();
        System.out.println("Enter element to add/remove prefixed by command af/al/rf/rl");
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.startsWith("rf")) {
                deque.removeFirst();
            } else if (s.startsWith("rl")) {
                deque.removeLast();
            } else if (s.startsWith("af")) {
                deque.addFirst(s.substring(2, s.length()));
            } else if (s.startsWith("al")) {
                deque.addLast(s.substring(2, s.length()));
            } else {
                System.out.println("unknown command, use prefix");
            }
            System.out.println(deque.toString());
        }
    }
}
