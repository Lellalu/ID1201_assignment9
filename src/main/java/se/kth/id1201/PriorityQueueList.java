package se.kth.id1201;

public class PriorityQueueList {

    public class Node{ 
        Integer item;
        Node next;

        Node(Integer item, Node n){
             this.item=item; 
             this.next=n; 
        }
    }

    public Node first;
    public int length;

    public void add_1(Integer item){
        length += 1;
        Node newNode = new Node(item, first);
        first = newNode;
    }

    public Integer remove_n(){
        if (first == null){
            return null;
        }
        length -= 1;
        Node pre = null;
        Node node = first;
        Node minPre = null;
        Node minNode = first;
        while(node != null){
            if(node.item < minNode.item){
                minNode = node;
                minPre = pre;
            }  
            pre = node;
            node = node.next;
        }
        if(minPre == null){
            first = first.next;
        }else{
            minPre.next = minNode.next;
            minNode.next = null;
        }
        return minNode.item;
    }

    public void add_n(int value){
        length += 1;
        Node newNode = new Node(value, null);
        if(first == null){
            first = newNode;
            return;
        }

        Node pre = null;
        Node node = first;
        while(node != null){
            if(value < node.item){
                break; 
            }
            pre = node;
            node = node.next;
        }
        if(pre == null){
            newNode.next = first;
            first = newNode;
        }else{
            pre.next = newNode;
            newNode.next = node;
        }
    }

    public Integer remove_1(){
        length -= 1;
        Integer item = first.item;
        Node node = first;
        this.first = first.next;
        node.next = null;
        return item;
    }
}
