public class DeadlockScenario {

    public static void main(String[] args) {
        String lock1="Sm";
        String lock2="dev";
        Thread thread1=new Thread(()->{
            synchronized (lock1){
                try{
                    Thread.sleep(1000);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("lock acquired by thread1");
                }
            }
        },"thread1");

        Thread thread2=new Thread(()->{
            synchronized (lock2){
                try{
                    Thread.sleep(1000);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("lock acquired by thread2");
                }
            }
        },"thread1");

        thread1.start();
        thread2.start();
    }
}
