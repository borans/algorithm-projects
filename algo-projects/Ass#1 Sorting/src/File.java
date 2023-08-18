import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class File {

    private final Sort sort;
    private final int[] data = new int[251281];
    private final int[] data_lengths = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251281};
    private final long[] average_times = new long[3];
    private final double[] times = new double[10];


    public File(Sort sort) {
        this.sort = sort;
    }

    public double[] getTimes() {
        return times;
    }

    public int[] getData_lengths() {
        return data_lengths;
    }

    public void readFile(String fileName) throws FileNotFoundException {

        Scanner scanner = new Scanner(new java.io.File("TrafficFlowDataset.csv"));

        //Setting the input array that is going to be experiment on
        int count=0;
        while(scanner.hasNextLine() && count<251281){
            String line = scanner.nextLine();
            if(line.split(",")[7].equals(" Flow Duration")){
                continue;
            }
            data[count] = Integer.parseInt(line.split(",")[7]);
            count++;
        }
        scanner.close();
    }


    public void sortingExperiment(int algo_k) throws IOException {


        for(int i=0; i<data_lengths.length; i++){


            // SETTING the array
            int[] arr;
            arr = Arrays.copyOfRange(data, 0, data_lengths[i]);

            //for insertion sort için


            for(int k=0; k<3; k++){
                // reversing the sorted array if sortKey=2
                if(k==2){
                    int temp;
                    for (int a = 0; a < data_lengths[i] / 2; a++) {
                        temp = arr[a];
                        arr[a] = arr[data_lengths[i] - a - 1];
                        arr[data_lengths[i] - a - 1] = temp;
                    }
                }

                sortingProcedure(data_lengths[i], arr, k, algo_k);



            }
            switch (algo_k){

                case 1:
                    // for insertion sort
                    System.out.println("Calculating average times for Insertion sort with input size " + data_lengths[i] + "...\n");
                    break;

                case 2:
                    // for merge sort
                    System.out.println("Calculating average times for Merge sort with input size " + data_lengths[i] + "...\n");
                    break;

                case 3:
                    // for pigeonhole sort
                    System.out.println("Calculating average times for Pigeonhole sort with input size " + data_lengths[i] + "...\n");
                    break;

                case 4:
                    // for counting sort
                    System.out.println("Calculating average times for Counting sort with input size " + data_lengths[i] + "...\n");
                    break;

            }

            System.out.println("Average time for unsorted array: " + average_times[0]);
            System.out.println("Average time for sorted array: "  + average_times[1]);
            System.out.println("Average time for reverse sorted array: " + average_times[2] +  "\n");

        }

    }


    public void sortingProcedure(int arr_length, int[] arr, int sortType_key, int algo_key){

        long beg_time, end_time, time_passed = 0;
        for(int j=0; j<10; j++){


            switch (algo_key){

                case 1:
                    beg_time = System.nanoTime();
                    sort.insertionSort(arr, arr_length);
                    end_time = System.nanoTime();
                    time_passed = end_time - beg_time;
                    break;
                case 2:
                    beg_time = System.nanoTime();
                    sort.mergeSort(arr, 0, arr_length-1);
                    end_time = System.nanoTime();
                    time_passed = end_time - beg_time;
                    break;
                case 3:
                    beg_time = System.nanoTime();
                    sort.pigeonholeSort(arr, arr_length);
                    end_time = System.nanoTime();
                    time_passed = end_time - beg_time;
                    break;
                case 4:
                    beg_time = System.nanoTime();
                    sort.countingSort(arr, 300000000 ,arr_length);
                    end_time = System.nanoTime();
                    time_passed = end_time - beg_time;
                    break;
            }

            times[j] = (double) time_passed;

        }



        long sum=0;
        for(double j: times){
            sum += j;
        }

        // if key=0 that means array is unsorted and function applies to unsorted array
        // if key=1 that means array is sorted and function applies to sorted array
        // if key =2 that means array is reverse sorted and function applies to rev. sorted array;
        average_times[sortType_key] = sum/10;



    }







}
