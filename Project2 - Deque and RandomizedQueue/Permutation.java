import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        int k = Integer.parseInt(args[0]);
        if (k > 0) {
            int size = 0;
            while (!StdIn.isEmpty()) {
                size++;
                if (size <= k) {
                    queue.enqueue(StdIn.readString());


                } else {
                    int random = StdRandom.uniform(0, size);
                    String in = StdIn.readString();
                    if (random < k) {
                        queue.dequeue();
                        queue.enqueue(in);
                    }

                }

            }

            for (int j = 0; j < k; j++) {
                StdOut.println(queue.dequeue());
            }


        }

    }
}
