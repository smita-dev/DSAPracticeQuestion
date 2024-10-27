import org.w3c.dom.Node;

import java.util.HashMap;

class LLNode{
    LLNode prev;
    LLNode next;
    int key;
    int val;
    LLNode(){
    }

    LLNode(int key,int val){
        this.key=key;
        this.val=val;
    }


}
public class LRU {
    LLNode head;
    LLNode tail;
    HashMap<Integer,LLNode> keyMap;
    int capacity;
    public LRU(int capacity){
        head=new LLNode();
        tail=new LLNode();
        head.next=tail;
        tail.prev=head;
        keyMap=new HashMap<>();
        this.capacity=capacity;
    }

    public void put(int key, int value){

        if(keyMap.size()==capacity){
            int keyToBeRemoved=tail.prev.key;
            removeNode(tail.prev);
            keyMap.remove(keyToBeRemoved);
        }

        LLNode currentNode=null;

        if(keyMap.containsKey(key)){
            currentNode=keyMap.get(key);
            currentNode.val=value;
            keyMap.put(key,currentNode);
            removeNode(currentNode);
        }else{
            currentNode=new LLNode(key,value);
            keyMap.put(key,currentNode);
        }
        addNode(currentNode);
    }

    public int get(int key){
        LLNode currentNode=null;

        if(keyMap.containsKey(key)){
            currentNode=keyMap.get(key);
            removeNode(currentNode);
            addNode(currentNode);
            return currentNode.val;
        }else{
            return -1;
        }
    }

    private void addNode(LLNode currentNode) {
        currentNode.next=head.next;
        currentNode.next.prev=currentNode;
        head.next=currentNode;
    }

    private void removeNode(LLNode currentNode) {
        currentNode.prev.next=currentNode.next;
        currentNode.next.prev=currentNode.prev;
    }
}
