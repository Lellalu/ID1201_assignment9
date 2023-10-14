package se.kth.id1201;

import java.util.LinkedList;
import java.util.Queue;

public class HeapBaobao {
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
    }

    Node root;

    public HeapBaobao(){ 
        root = null;
    }

    public void enqueue(Integer val){
        if(root == null){
            root = new Node(val);
            return;
        }
        heapDown(root, val);
    }

    private void heapDown(Node node, Integer val){
        Integer tmp = val;
        if(val.intValue() < node.value.intValue()){
            tmp = node.value;
            node.value = val;
        }

        if(node.left == null){
            node.left = new Node(tmp);
            return;
        }

        if(node.right == null){
            node.right = new Node(tmp);
            return;
        }

        if(node.left.getSize() < node.right.getSize()){
            heapDown(node.left, tmp);
        }else{
            heapDown(node.right, tmp);
        }
    }

    public int push(Integer val){
        if (root == null){
            return -1;
        }
        root.value += val;
        return push(root, 0);
    }

    private int push(Node node, int depth){
        Node minNode = node;
        if(node.left != null){
            minNode = node.left.value.intValue() < minNode.value.intValue() ? node.left : minNode;
        }
        if(node.right != null){
            minNode = node.right.value.intValue() < minNode.value.intValue() ? node.right : minNode;
        }

        if(minNode == node){
            return depth;
        }else{
            Integer tmp = node.value;
            node.value = minNode.value;
            minNode.value = tmp;
            return push(minNode, depth+1);
        }
    }

    public Integer dequeue(){
        if(root == null){
            return null;
        }

        Integer value = root.value;
        if(root.left == null && root.right == null){
            root = null;
        }else if(root.left == null && root.right != null){
            root = root.right;
        }else if(root.left != null && root.right == null){
            root = root.left;
        }else{
            if(root.left.value.intValue() < root.right.value.intValue()){
                heapUp(root, root.left);
            }else{
                heapUp(root, root.right);
            }
        }
        return value;
    }

    private void heapUp(Node parent, Node child){
        parent.value = child.value;
        if(child.left == null && child.right == null){
            if(parent.left == child){
                parent.left = null;
            }else{
                parent.right = null;
            }
            return;
        }else if(child.left == null && child.right != null){
            heapUp(child, child.right);
        }else if(child.left != null && child.right == null){
            heapUp(child, child.left);
        }else{
            if(child.left.value < child.right.value){
                heapUp(child, child.left);
            }else{
                heapUp(child, child.right);
            }
        }
    }
}

