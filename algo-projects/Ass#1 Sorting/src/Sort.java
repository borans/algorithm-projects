import java.util.Arrays;

public class Sort {




   public void insertionSort(int[] arr, int l){

        for(int j=1; j<l; j++){

            int key = arr[j];
            int i = j-1;

            while(i>=0 && arr[i]>key){

                arr[i+1] = arr[i];
                i = i-1;

            }
            arr[i+1] = key;
        }
    }


    public void mergeSort(int[] arr, int left, int right){

       if(left>=right){
           return;
       }else{
           int mid= left + (right-left)/2;

           mergeSort(arr, left, mid);
           mergeSort(arr, mid + 1, right);
           merge(arr, left, mid, right);

       }

    }

    public void merge(int[] arr, int left, int mid, int right){

        int[] left_half = new int[mid-left+1];
        int[] right_half = new int[right-mid];

        int l1 = mid-left+1;
        int l2 = right-mid;

        //data copying
        for(int i=0; i<l1; i++){
            left_half[i] = arr[left+i];
        }

        for(int j=0; j<l2; j++){
            right_half[j] = arr[mid + 1 + j];
        }


        int i=0, j=0;
        int a = left;

        while(i<l1 && j<l2){

            if(left_half[i] < right_half[j]){
                arr[a] = left_half[i];
                i++;
            }
            else{
                arr[a] = right_half[j];
                j++;
            }
            a++;
        }

        while(i<l1){
            arr[a] = left_half[i];
            i++;
            a++;
        }

        while(j<l2){
            arr[a] = right_half[j];
            j++;
            a++;
        }

    }



    public void pigeonholeSort(int[] arr, int l){

        int min = arr[0], max = arr[0];

        for (int j : arr) {
            if (j > max) {
                max = j;
            }
            if (j < min) {
                min = j;
            }
        }
        int range = max - min +1;
        int index=0;

        int[] holes = new int[range];
        Arrays.fill(holes, 0);

        for (int i=0; i<l; i++) {
            holes[arr[i] - min]++;
            index=0;
        }


        for(int j=0; j<range; j++){
            while(holes[j]-- > 0){
                arr[index] = j + min;
                index++;
            }
        }
    }


    public void countingSort(int[] arr,int k, int l){

        int[] output = new int[l];
        int[] count = new int[k+1];
        Arrays.fill(count, 0);

        for(int i=0; i<l; i++){
            ++count[arr[i]];
        }


        for(int i=1; i<=k; i++){
            count[i] += count[i-1];
        }

        for(int i=l-1; i>=0; i--){
            output[count[arr[i]] - 1] = arr[i];
            --count[arr[i]];
        }

        for (int i = 0; i < l; ++i)
            arr[i] = output[i];
    }

}
