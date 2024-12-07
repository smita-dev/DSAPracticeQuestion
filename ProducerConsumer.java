import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
     Queue<Integer> q;
    int capacity=5;

    public ProducerConsumer(int capacity){
        q=new LinkedList<>();
        this.capacity=capacity;
    }

    public void enqueue(int element){
        synchronized (q){
            while(q.size()==capacity){
                try {
                    System.out.println("waiting for enqueue");
                    q.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            q.add(element);
            q.notifyAll();

        }

    }

    public void dequeue(){
        synchronized (q){
            while(q.isEmpty()){
                try {
                    System.out.println("waiting for deque");
                    q.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(q.poll());
            q.notifyAll();
        }

    }

}
