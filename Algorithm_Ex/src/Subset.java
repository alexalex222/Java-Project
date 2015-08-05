/**
 * Created by Kuilin on 7/4/2015.
 */
public class Subset {
    public static void main(String[] args) {
        if(args == null || args.length != 1) {
            System.err.println("Require one arg: single int k");
        }

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<String>();

        while(!StdIn.isEmpty()) {
            q.enqueue(StdIn.readString());
        }

        for(int i = 0; i < k; i++){
            System.out.println(q.dequeue());
        }
    }
}
