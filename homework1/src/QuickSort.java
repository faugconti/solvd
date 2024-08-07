public class QuickSort {
    public static void sort(int a[]) {
        if (a == null || a.length == 0)
            return;
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(int a[], int lo, int hi) {
        int i = lo, j = hi;
        int pivot = a[lo + (lo + hi) / 2];
        while (i<=j){
            while (a[i]<pivot){
                i++;
            }
            while (a[j]>pivot){
                j--;
            }
            if(i<=j)
                Helper.swap(a,i++,j--);
        }
        if (lo<j)
            quickSort(a, lo,j);
        if (i>hi)
            quickSort(a,i, hi);
    }
}
