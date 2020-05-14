import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node first, last;

    // Linked List implementation with Nodes
    private class Node {
        Item val;
        Node next = null;
        Node prev = null;
    }


    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {

            return current != null;
        }

        public Item next() {

            if (current == null) {
                throw new NoSuchElementException();
            }

            Item val = current.val;

            current = current.next;

            return val;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // construct an empty deque
    public Deque() {

        first = null;
        last = null;
        size = 0;


    }

    // is the deque empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (first != null) {
            Node oldFirst = first;

            // Setting forward pointers
            first = new Node();
            first.val = item;
            first.next = oldFirst;

            // Setting backward pointers
            oldFirst.prev = first;

        } else {
            first = new Node();
            first.val = item;
        }


        if (size == 0) {
            last = first;
        }


        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (last == null) {
            last = new Node();
            last.val = item;
            if (first == null) {
                first = last;
            }
        } else {
            Node oldLast = last;
            last = new Node();
            last.val = item;

            // Setting previous pointers
            last.prev = oldLast;

            // Setting forward pointers
            oldLast.next = last;

        }


        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {

        if (first == null) {
            throw new NoSuchElementException();
        }

        Item item;
        if (size == 1) {
            item = first.val;
            first = null;
            last = null;
        } else {

            item = first.val;

            // Traverse the pointer to next element
            first = first.next;
            first.prev = null;

        }


        size--;

        return item;

    }

    // remove and return the item from the back
    public Item removeLast() {


        if (last == null) {
            throw new NoSuchElementException();
        }


        Item prevLastValue;
        if (first == last) {
            prevLastValue = last.val;
            first = null;
            last = null;

        } else {
            Node temp = last.prev;

            prevLastValue = last.val;
            last = temp;
            last.next = null;

        }

        size--;
        return prevLastValue;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }


    // unit testing (required)
    public static void main(String[] args) {

        Deque<Integer> deck = new Deque<Integer>();
        deck.addFirst(10);
        deck.addLast(20);
        deck.addLast(30);
        deck.addLast(40);
        deck.addLast(50);
        deck.addFirst(60);
        StdOut.println(deck.removeLast());
        StdOut.println(deck.removeFirst());
        StdOut.println(deck.removeLast());
        StdOut.println(deck.removeLast());

    }

}
