
public class Bubble {
    public static void main(String[] args) {
        int arr[] = {7,8,5,1,2};


        // bubble sort
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j <arr.length-i-1; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                   


                }
            }
        }

        // selection sort
        int arr1[] = {7,8,5,1,2};
        for(int i=0;i<arr1.length-1;i++) {
            int smallest = i;
            for(int j=i+1; j< arr1.length;j++) {
                if (arr1[smallest] > arr1[j]) {
                   smallest = j;
                    
                }
            }
            int temp = arr1[smallest];
            arr1[smallest] = arr1[i];
            arr1[i] = temp;
        }


        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();


        for(int i = 0; i<arr.length;i++) {
            System.out.print(arr[i] +  " ");

        }
        System.out.println();



        int arr2[] = {7,8,5,1,2};


        for (int i = 1; i < arr2.length; i++) {
            int current = arr2[i];
            int j= i-1;
            while(j>=0 && current < arr2[j]) {
                arr2[j+1] = arr2[j];
                j--;
            }

            // placement
            arr2[j+1] = current;
        }
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }




}   }   

