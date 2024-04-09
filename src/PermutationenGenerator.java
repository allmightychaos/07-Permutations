import java.util.Iterator;
import java.util.NoSuchElementException;

public class PermutationenGenerator implements Iterable<Integer[]>, Iterator<Integer[]> {
    private Integer[] array;
    private boolean hasNext;

    public PermutationenGenerator(int n) {
        array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
        hasNext = true;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Integer[] next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }

        Integer[] result = array.clone();

        // Find the largest index k such that array[k] < array[k + 1]
        int k = -1;
        for (int i = array.length - 2; i >= 0; i--) {
            if (array[i] < array[i + 1]) {
                k = i;
                break;
            }
        }

        // If no such index exists, the permutation is the last permutation
        if (k == -1) {
            hasNext = false;
            return result;
        }

        // Find the largest index l greater than k such that array[k] < array[l]
        int l = -1;
        for (int i = array.length - 1; i > k; i--) {
            if (array[k] < array[i]) {
                l = i;
                break;
            }
        }

        // Swap the value of array[k] with that of array[l]
        swap(array, k, l);

        // Reverse the sequence from array[k + 1] up to and including the final element array[n]
        for (int i = k + 1, j = array.length - 1; i < j; i++, j--) {
            swap(array, i, j);
        }

        return result;
    }

    private void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public void remove() {
        // Nicht unterstützt
    }

    @Override
    public Iterator<Integer[]> iterator() {
        return this;
    }

    public static void main(String[] args) {
        PermutationenGenerator g = new PermutationenGenerator(4);
        for (Integer[] a : g) {
            System.out.println(java.util.Arrays.toString(a));
        }
    }
}
