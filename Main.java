public class Main {
    public static void main(String[] args) {

        LRU lruCache=new LRU(5);
        lruCache.put(1,2);
        lruCache.put(2,3);
        lruCache.put(3,4);
        lruCache.put(4,5);
        lruCache.put(5,5);
        lruCache.put(6,1);
        lruCache.put(7,1);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(7));
        System.out.println(lruCache.get(6));
        lruCache.put(6,2);
        System.out.println(lruCache.get(6));
    }
}