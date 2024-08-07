public class Helper {
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
}
