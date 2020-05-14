import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int size;

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int counter;
        Item[] copy;

        public RandomizedQueueIterator() {
            copy = (Item[]) new Object[queue.length];

            for (int i = 0; i < size; i++) {
                copy[i] = queue[i];

            }

            counter = size;
        }

        public boolean hasNext() {
            return counter > 0;
        }

        public Item next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int random = StdRandom.uniform(counter);

            --counter;

            // Swap this index element with the last element
            Item temp = copy[counter];
            copy[counter] = copy[random];
            copy[random] = temp;

            return copy[counter];


        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) (new Object[1]);
        size = 0;
    }

    private void resizeQueue(int s) {
        Item[] copy = (Item[]) (new Object[s]);

        for (int i = 0; i < size; i++) {
            copy[i] = queue[i];
        }

        queue = copy;

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == queue.length) {

            resizeQueue(2 * queue.length);
        }

        queue[size++] = item;


    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int random = StdRandom.uniform(size);

        // Swap this index element with the last element
        if (size <= 0.25 * queue.length) {
            resizeQueue((int) (0.5 * queue.length));
        }

        --size;

        Item temp = queue[random];

        queue[random] = queue[size];
        queue[size] = null;

        return temp;

    }

    // return a random item (but do not remove it)
    public Item sample() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int random = StdRandom.uniform(size);
        return queue[random];

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();

    }

    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<String> queue = new RandomizedQueue<String>();


        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");
        queue.enqueue("F");
        queue.enqueue("G");
        queue.enqueue("H");
        queue.enqueue("I");
        for (String s : queue) {
            System.out.println(s);
        }


    }

}
