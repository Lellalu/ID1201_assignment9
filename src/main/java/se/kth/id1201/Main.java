package se.kth.id1201;

import java.util.Random;

public class Main 
{
    final static int REPETITION = 100;
    final static int MAX_VALUE = 1000000;

    public static void main( String[] args )
    {
        //pushBenchmark();
        priorityQueueAddRemoveBenchmark();
        //heapListVersusArray();
    }

    public static void priorityQueueAddRemoveBenchmark(){
        int max_operations = 5000;

        Random rand = new Random();
        PriorityQueueList add1removen;
        PriorityQueueList addnremove1;

        long add1_time;
        long add1_sum_time;
        long addn_time;
        long addn_sum_time;
        long remove1_time;
        long remove1_sum_time;
        long removen_time;
        long removen_sum_time;

        System.out.printf("#Add and remove x times in PriorityQueueList, time in ns\n");
        System.out.printf("#%18s%18s%18s%18s%18s\n", "x", "Add1", "Addn", "Remove1", "Removen");
        for(int operations = 2; operations < max_operations; operations*=2){
            add1_sum_time = 0;
            addn_sum_time = 0;
            remove1_sum_time = 0;
            removen_sum_time = 0;
            for(int i = 0; i < REPETITION; i++){
                add1removen = new PriorityQueueList();
                addnremove1 = new PriorityQueueList();

                add1_time = System.nanoTime();
                for(int j = 0; j < operations; j++){
                    add1removen.add_1(rand.nextInt(MAX_VALUE));
                }
                add1_sum_time += (System.nanoTime() - add1_time);

                removen_time = System.nanoTime();
                for(int j = 0; j < operations; j++){
                    add1removen.remove_n();
                }
                removen_sum_time += (System.nanoTime() - removen_time);

                addn_time = System.nanoTime();
                for(int j = 0; j < operations; j++){
                    addnremove1.add_n(rand.nextInt(MAX_VALUE));
                }
                addn_sum_time += (System.nanoTime() - addn_time);

                remove1_time = System.nanoTime();
                for(int j = 0; j < operations; j++){
                    addnremove1.remove_1();
                }
                remove1_sum_time += (System.nanoTime() - remove1_time);
            }
            System.out.printf("%19d&", operations);
            System.out.printf("%18.2f&%18.2f&%18.2f&%18.2f\\ \n",
                              (double)add1_sum_time/REPETITION,
                              (double)addn_sum_time/REPETITION,
                              (double)remove1_sum_time/REPETITION,
                              (double)removen_sum_time/REPETITION);
        }
    }

    public static void pushBenchmark(){
        Random rand = new Random();

        HeapBaobao heapPush = new HeapBaobao();
        HeapBaobao heapDeEn = new HeapBaobao();
        int randInt;
        for(int i = 0; i < 1023; i++){
            randInt = rand.nextInt(10000);
            heapPush.enqueue(randInt);
            heapDeEn.enqueue(randInt);
        }

        int numberOfPush = 1000;
        long push_time;
        long push_sum_time = 0;
        long dequeue_enqueue_time;
        long dequeue_enqueue_sum_time = 0;
        Integer tmp;
        for(int i = 0; i < numberOfPush; i++){
            randInt = 10 + rand.nextInt(90);

            push_time = System.nanoTime();
            heapPush.push(randInt);
            push_sum_time += (System.nanoTime() - push_time);

            dequeue_enqueue_time = System.nanoTime();
            tmp = heapDeEn.dequeue();
            heapDeEn.enqueue(tmp + randInt);
            dequeue_enqueue_sum_time += (System.nanoTime() - dequeue_enqueue_time);
        }
        System.out.printf("#push versus dequeue and enqueue %d times in Heap, time in ns\n", numberOfPush);
        System.out.printf("#%24s%24s\n", "Push", "dequeue and enqueue");
        System.out.printf("%24.2f%24.2f\n",
                              (double)push_sum_time/numberOfPush,
                              (double)dequeue_enqueue_sum_time/numberOfPush);
    }

    public static void heapListVersusArray(){
        HeapBaobao heapList;
        HeapArray heapArray;

        Random rand = new Random();
        int[] randomIntArray;

        long heap_list_enqueue_time;
        long heap_list_dequeue_time;
        long heap_list_enqueue_sum_time;
        long heap_list_dequeue_sum_time;
        long heap_array_enqueue_time;
        long heap_array_dequeue_time;
        long heap_array_enqueue_sum_time;
        long heap_array_dequeue_sum_time;

        int max_operations = 5000;
        System.out.printf("#Enqueue and dequeue x times in linkedlist and array of a heap, time in ns\n");
        System.out.printf("#%10s%22s%22s%22s%22s\n", "x", "HeapList enqueue", "HeapList dequeue", "HeapArray enqueue", "HeapArray dequeue");
        for(int operations = 2; operations < max_operations; operations*=2){
            randomIntArray = new int[operations];
            for(int j = 0; j < operations; j++){
                randomIntArray[j] = rand.nextInt(1000000);
            }

            heap_list_enqueue_sum_time = 0;
            heap_list_dequeue_sum_time = 0;
            heap_array_enqueue_sum_time = 0;
            heap_array_dequeue_sum_time = 0;
            for(int i = 0; i < REPETITION; i++){
                heapList = new HeapBaobao();
                heapArray = new HeapArray(10);

                heap_list_enqueue_time = System.nanoTime();
                for(int j = 0; j < operations; j++){
                    heapList.enqueue(randomIntArray[j]);
                }
                heap_list_enqueue_sum_time += (System.nanoTime() - heap_list_enqueue_time);

                heap_array_enqueue_time = System.nanoTime();
                for(int j = 0; j < operations; j++){
                    heapArray.enqueue(randomIntArray[j]);
                }
                heap_array_enqueue_sum_time += (System.nanoTime() - heap_array_enqueue_time);

                heap_list_dequeue_time = System.nanoTime();
                for(int j = 0; j < operations; j++){
                    heapList.dequeue();
                }
                heap_list_dequeue_sum_time += (System.nanoTime() - heap_list_dequeue_time);

                heap_array_dequeue_time = System.nanoTime();
                for(int j = 0; j < operations; j++){
                    heapArray.dequeue();
                }
                heap_array_dequeue_sum_time += (System.nanoTime() - heap_array_dequeue_time);
            }
            System.out.printf("%10d&", operations);
            System.out.printf("%18.2f&%18.2f&%18.2f&%18.2f\\ \n",
                              (double)heap_list_enqueue_sum_time/REPETITION,
                              (double)heap_list_dequeue_sum_time/REPETITION,
                              (double)heap_array_enqueue_sum_time/REPETITION,
                              (double)heap_array_dequeue_sum_time/REPETITION);
        }
    }
}
