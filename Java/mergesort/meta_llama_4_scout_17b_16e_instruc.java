
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class meta_llama_4_scout_17b_16e_instruc {

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;

            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);

            merge(arr, low, mid, high);
        }
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] left = new int[mid - low + 1];
        int[] right = new int[high - mid];

        System.arraycopy(arr, low, left, 0, mid - low + 1);
        System.arraycopy(arr, mid + 1, right, 0, high - mid);

        int i = 0, j = 0, k = low;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
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
        mergeSort(arr);

    }
}

