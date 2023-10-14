package se.kth.id1201;

import java.util.Queue;
import java.util.LinkedList;

public class HeapList implements PriorityQueue{
    public static class Node{ 
        public Integer value; 
        public Node left, right; 

        public Node(Integer value){ 
            this.value = value; 
            this.left = null;
            this.right = null; 
        } 

        public int getSize(){
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(this);
            int size = 0;
            Node node;
            while(queue.peek() != null){
                size += 1;
                node = queue.remove();
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            return size;
        }

        public void add(Integer val){
            Integer tmp = val;
            if(val < this.value){
                tmp = this.value;
                this.value = val;
            }
            if(left == null){
                left = new Node(tmp);
            }else if(right == null){
                right = new Node(tmp);
            }else{
                if(left.getSize() < right.getSize()){
                    left.add(tmp);
                }else{
                    right.add(tmp);
                }
            }
        }

        public void promote(){
            if(this.left != null && this.right != null){
                if(right.value < left.value){
                    this.value = right.value;
                    if(right.right == null && right.left == null){
                        right = null;
                    }else{
                        right.promote();  
                    }           
                }else{
                    this.value = left.value;
                    if(left.left == null && left.right ==  null){
                        left = null;
                    }else{
                        left.promote(); 
                    }            
                }         
            }else{
                if(right == null){
                    this.value = left.value;
                    left = null;
                }else{
                    this.value = right.value;
                    right = null;
                }
            }
        }

    }

    Node root;

    public HeapList(){ 
        root = null;
    }

    public void clear(){
        root = null;
    }

    public void enqueue(Integer val){
        Integer tmp;
        if(root == null){
            root = new Node(val);
            return;
        }
        if(val < root.value){
            tmp = root.value;
            root.value = val;
            root.add(tmp);
        }else{
            root.add(val);
        }
    }

    public Integer dequeue(){
        Integer removedValue = root.value;
        if(root == null){
            return null;
        }
        if(root.right == null && root.left != null){
            root = root.left;
            return removedValue;
        }
        if(root.left == null && root.right != null){
            root = root.right;
            return removedValue;
        }
        if(root.left == null && root.right == null){
            root = null;
            return removedValue;
        }
        if(root.right != null && root.left != null){
            if(root.right.value < root.left.value){
                root.value = root.right.value;
                if(root.right.left == null && root.right.right ==  null){
                    root.right = null;
                }else{
                    root.right.promote();  
                }   
            }
            else{
                root.value = root.left.value;
                if(root.left.left == null && root.left.right == null){
                    root.left = null;
                }else{
                    root.left.promote(); 
                }     
            }
        }
        return removedValue;
    }
}

