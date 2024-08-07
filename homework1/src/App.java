
public class App {

    public static void bubbleSort(int a[]) {
        /*
         * BubbleSort
         * Time complexity best-case -> O(N)
         * Time complexity worst-case -> O(N²)
         */
        int range = a.length - 1;
        boolean swapped = true;

        while (swapped) {
            swapped = false;
            for (int i = 0; i < range; i++) {
                if (a[i] > a[i + 1]) {
                    swap(a, i, i + 1);
                    swapped = true;
                }
            }
            range--;
        }
    }

    public static void selectionSort(int a[]) {
        /*
         * selectionSort
         * Time complexity best-case -> O(N²)
         * Time complexity worst-case -> O(N²)
         */
        int posMin;
        for (int i = 0; i < a.length; i++) {
            posMin = i;
            for (int j = i + 1; j < a.length; j++)
                if (a[posMin] > a[j])
                    posMin = j;
            if (i != posMin)
                swap(a, i, posMin);
        }
    }

    public static void insertionSort(int a[]) {
        /*
         * insertionSort
         * Time complexity best-case -> O(N)
         * Time complexity worst-case -> O(N²)
         */
        int j;
        for (int i = 1; i < a.length; i++) {
            j = i;
            while (j > 0 && a[j] < a[j - 1]) 
                swap(a, j, --j); 
        }
    }

    public static void swap(int a[], int pos1, int pos2) {
        int aux = a[pos1];
        a[pos1] = a[pos2];
        a[pos2] = aux;
    }

    public static void showArray(int a[]) {
        for (int i = 0; i < a.length; i++)
            System.out.printf("%d ", a[i]);
        System.out.println();
    }

    public static void main(String[] args) throws Exception {

        int array[] = { 100, 30, 50, 40, 10 };

        showArray(array);
        // bubbleSort(array);
        // selectionSort(array);
        // insertionSort(array);
        QuickSort.sort(array);
        // MergeSort.sort(array);
        showArray(array);
    }

}
