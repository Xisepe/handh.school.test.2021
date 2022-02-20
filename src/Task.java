import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.SecureRandom;
import java.util.*;

public class Task {
    public static void main(String[] args) {
        final int size = new Scanner(System.in).nextInt();
        int[][] result = generateNArrays(size);
    }

    public static int[][] generateNArrays(final int size) {
        if (!checkInput(size)) {
            return null;
        }
        int[][] arr = generateTwoDimensionalArray(size);

        initializeByRandom(arr, size);

        sortTask(arr, size);

        return arr;
    }

    /**
     * return true if generation is possible, else false
     */
    private static boolean checkInput(final int size) {
        final int MAX_BOUND = Integer.MAX_VALUE - 1;
        final int MIN_BOUND = 0;
        return (size < MAX_BOUND) && (size > MIN_BOUND);
    }

    private static int[] @NotNull [] generateTwoDimensionalArray(final int size) {
        return new int[size][];
    }

    private static List<Integer> getShuffledSequence(final int size) {
        List<Integer> seq = new ArrayList<>(size + size);
        int tmp = size;
        if (size < (Integer.MAX_VALUE - 2) / 2) {
            tmp += size;
        }
        for (int i = 1; i <= tmp; i++) {
            seq.add(i);
        }
        Collections.shuffle(seq);
        return seq.subList(0,size);
    }

    private static void initializeByRandom(int[] @Nullable [] array, final int size) {
        List<Integer> seq = getShuffledSequence(size);
        for (int i = 0; i < size; i++) {
            int tmp_size = seq.get(i);
            array[i] = new int[tmp_size];
            SecureRandom rand = new SecureRandom();
            for (int j = 0; j < tmp_size; j++) {
                array[i][j] = rand.nextInt();
            }
        }
    }

    private static void sortTask(int[] @NotNull [] arr, final int size) {
        for (int i = 0; i < size; i++) {
            if ((i & 1) == 0) {
                Arrays.sort(arr[i]);
            } else {
                arr[i] = Arrays.stream(arr[i]).boxed()
                        .sorted(Collections.reverseOrder())
                        .mapToInt(Integer::intValue)
                        .toArray();
            }
        }
    }
}
