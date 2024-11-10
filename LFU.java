import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFU {
        int minFreq=0;
        int capacity;
        HashMap<Integer,Integer> cache=new HashMap<>();
        HashMap<Integer,Integer> keyFreqMap=new HashMap<>();
        HashMap<Integer, LinkedHashSet<Integer>> freqKeysMap=new HashMap<>();

        public LFU(int capacity) {
            this.capacity=capacity;
            freqKeysMap.put(1,new LinkedHashSet<>());
        }

        public int get(int key) {
            if(!cache.containsKey(key)){
                return -1;
            }

            increaseCounter(key);
            return cache.get(key);
        }

        public void increaseCounter(int key){
            int freq=keyFreqMap.get(key);
            freqKeysMap.get(freq).remove(key);

            if(freq==minFreq && freqKeysMap.get(freq).size()==0){
                minFreq++;
            }

            if(!freqKeysMap.containsKey(freq+1)){
                freqKeysMap.put(freq+1,new LinkedHashSet<>());
            }

            freqKeysMap.get(freq+1).add(key);

            keyFreqMap.put(key,freq+1);
        }
        public void put(int key, int value) {
            if(capacity<=0) return;

            if(cache.containsKey(key)){
                cache.put(key,value);
                increaseCounter(key);
                return;
            }

            if(cache.size()>=capacity){
                int keyToBeEvicted=freqKeysMap.get(minFreq).iterator().next();
                freqKeysMap.get(minFreq).remove(keyToBeEvicted);
                cache.remove(keyToBeEvicted);
                keyFreqMap.remove(keyToBeEvicted);
            }

            cache.put(key,value);
            keyFreqMap.put(key,1);
            freqKeysMap.get(1).add(key);
            minFreq=1;

        }
    public static void main(String[] args) {
        LFU lfuCache=new LFU(5);
        lfuCache.put(1,2);
        lfuCache.put(2,3);
        lfuCache.put(3,4);
        lfuCache.put(4,5);
        lfuCache.put(5,5);
        lfuCache.put(6,1);
//        lfuCache.put(7,1);
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(7));
        System.out.println(lfuCache.get(6));
        lfuCache.put(6,2);
        System.out.println(lfuCache.get(6));
    }
}
