import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerMain {
    public static void main(String[] args) {
        ProducerConsumer producerConsumer=new ProducerConsumer(5);
//        Thread thread1=new Thread(()->{
//            producerConsumer.enqueue(1);
//        },"producer");
//
//        Thread thread2=new Thread(()->{
//            producerConsumer.dequeue();
//        },"consumer");

//        thread1.start();
        ExecutorService threadPool2= Executors.newFixedThreadPool(5);
        threadPool2.execute(()->{
            producerConsumer.dequeue();
        });

        ExecutorService threadPool= Executors.newFixedThreadPool(5);
        threadPool.execute(()->{
            for (int i = 0; i < 5; i++) {
                producerConsumer.enqueue(1);
            }

        });

        ExecutorService threadPool3= Executors.newFixedThreadPool(5);
        threadPool3.execute(()->{
            for (int i = 0; i < 5; i++) {
                producerConsumer.dequeue();
            }
        });

    }
}
