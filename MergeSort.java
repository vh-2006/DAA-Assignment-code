public class MergeSort {

    public static void mergeSort(int arr[],int start,int mid,int end) {
        int i = start;
        int j = mid+1;
        int temp[] = new int[(end-start) + 1];
        int idx = 0;
        while (i<=mid && j<= end) {
            if (arr[i] <= arr[j]) {
                temp[idx++] = arr[i];
                i++;
            }
            else {
                temp[idx++] = arr[j];
                j++;
            }
        }
        while (i<= mid) {
            temp[idx++] = arr[i];
            i++;
        }
        while (j<=end) {
            temp[idx++] = arr[j];
            j++;
        }

        for (int k = 0; k < temp.length; k++) {
            arr[start+k] = temp[k]; 
        }

    }

    public static void merge(int arr[],int start ,int end) {
        if (start < end) {
            int mid = start + (end - start) /2;
            merge(arr, start, mid);
            merge(arr, mid+1,end);
            mergeSort(arr, start, mid, end);

        }
    }
    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
     public static void main(String[] args) {
        int arr[] = {5, 2, 6, 4, 1, 3};
        merge(arr, 0, arr.length-1);
        printArray(arr);
    }
}
