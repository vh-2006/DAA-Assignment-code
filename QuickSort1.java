

public class QuickSort1 {
    public static void quickSort(int arr[],int start,int end) {
        if (start < end) {
            int piviot = partition(arr, start, end);
            partition(arr, start, piviot-1);
            partition(arr, piviot+1, end);
        }
    }

    public static int  partition(int arr[],int start,int end) {
        int idx = start  -1;
        int piviot = arr[end];
        
        for (int i = start; i < end; i++) {
            if (arr[i] < piviot) {
                idx++;
                int temp = arr[idx];
                arr[idx] = arr[i];
                arr[i] = temp;
            }
        }
        
        idx++;
        int temp = arr[idx];
        arr[idx] = arr[end];
        arr[end] = temp;
        return idx;
    }

    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    public static void main(String[] args) {
        int  arr[] = {5, 2, 6, 4, 1, 3};
        quickSort(arr, 0, arr.length-1);
        printArray(arr);
    }
}