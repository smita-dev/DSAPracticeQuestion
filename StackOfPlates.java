import java.util.ArrayList;
import java.util.Stack;

public class StackOfPlates {
    ArrayList<Stack<Integer>> stacks;
    int capacity;

    StackOfPlates(int capacity){
        stacks=new ArrayList<>();
        this.capacity=capacity;
    }

    public void push(int item){
        if(stacks.isEmpty() || stacks.get(stacks.size()-1).size()==capacity){
            stacks.add(new Stack<>());
        }

        stacks.get(stacks.size()-1).push(item);
    }

    public int pop(){
        if(!stacks.isEmpty()){
            return stacks.get(stacks.size()-1).pop();
        }

        return -1;
    }

    public int top(){
        if(!stacks.isEmpty()){
            return stacks.get(stacks.size()-1).peek();
        }

        return -1;
    }

    public boolean isEmpty(int index){
        return stacks.get(index).isEmpty();
    }

    public boolean isFull(int index){
        return stacks.get(index).size()==capacity;
    }

    public int popAtIndex(int index){
        if(index>=stacks.size()){
            System.out.println("Wrong index");
            return -1;
        }
        if(isEmpty(index)){
            stacks.remove(index);
            return -1;
        }else{
            return stacks.get(index).pop();
        }
    }

    public static void main(String[] args) {
        StackOfPlates stackOfPlates=new StackOfPlates(3);
        stackOfPlates.push(1);
        stackOfPlates.push(2);
        stackOfPlates.push(3);
        stackOfPlates.push(4);
        stackOfPlates.push(5);

        System.out.println(stackOfPlates.top());
        stackOfPlates.pop();

        System.out.println(stackOfPlates.top());

        System.out.println(stackOfPlates.popAtIndex(0));
        System.out.println(stackOfPlates.popAtIndex(1));
        System.out.println(stackOfPlates.popAtIndex(1));
        System.out.println(stackOfPlates.popAtIndex(1));
    }
}
