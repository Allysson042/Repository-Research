
import java.util.List;
import java.util.ArrayList;

public class deepseek_r1_disill_llama {

    private static final int INSERTION_SORT_THRESHOLD = 7;

    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            if (high - low + 1 < INSERTION_SORT_THRESHOLD) {
                insertionSort(array, low, high);
            } else {
                int pivotIndex = partition(array, low, high);
                quickSort(array, low, pivotIndex - 1);
                quickSort(array, pivotIndex + 1, high);
            }
        }
    }

    private static int partition(int[] array, int low, int high) {
        // Median-of-three pivot selection
        int mid = (low + high) / 2;
        if (array[mid] > array[high]) {
            swap(array, mid, high);
        }
        if (array[low] > array[high]) {
            swap(array, low, high);
        }
        if (array[mid] > array[low]) {
            swap(array, mid, low);
        }

        // Use the middle element as the pivot
        int pivot = array[low];
        int i = low + 1;
        int j = high;

        while (true) {
            while (i <= j && array[i] <= pivot) {
                i++;
            }
            while (i <= j && array[j] > pivot) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(array, i, j);
        }
        swap(array, low, j);
        return j;
    }

    private static void insertionSort(int[] array, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= low && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

 public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();

        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("../../inputs.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(Integer.parseInt(line.trim()));
            }
        } catch (java.io.IOException e) {
            return;
        }

        // Converte List<Integer> diretamente para int[]
        int[] arr = input.stream().mapToInt(Integer::intValue).toArray();

        // Usa o método existente
        sort(arr);

    }

}
