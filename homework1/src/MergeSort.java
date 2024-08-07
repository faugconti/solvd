import java.util.Arrays;

public class MergeSort {

    
    public static void sort(int a[]) {
        /*
         * MergeSort
         * Time complexity best-case -> O(N log(N))
         * Time complexity worst-case -> O(N log(N))
         */
        if (a.length <= 1)
            return;

        int left[] = Arrays.copyOfRange(a, 0, a.length / 2);
        int right[] = Arrays.copyOfRange(a, a.length / 2, a.length);

        sort(left);
        sort(right);

        merge(a, left, right);
    }


    private static void merge(int a[], int left[], int right[]) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) 
                a[k++] = left[i++];
            else 
                a[k++] = right[j++];
        }
        while (i < left.length) 
            a[k++] = left[i++];

        while (j < right.length) 
            a[k++] = right[j++];    
    }
}
