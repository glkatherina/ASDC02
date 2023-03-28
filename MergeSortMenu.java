import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MergeSortMenu {
    private static int comparisons = 0;
    private static int swaps = 0;

    public static void main(String[] args) {
        String[][] data = readDataFromFile("D:\\УНИВЕР\\II курс\\ASDC\\Lab2\\src\\Menu.txt");
        long startTime = System.currentTimeMillis();
        sort(data, 0, data.length - 1);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        printSortingInfo("Mergesort", data, duration);
    }

    private static String[][] readDataFromFile(String filename) {
        String[][] data = new String[50][5];
        try {
            Scanner scanner = new Scanner(new File(filename));
            int i = 0;
            while (scanner.hasNextLine()) {
                String[] fields = scanner.nextLine().split(",");
                data[i][0] = fields[0].trim();
                data[i][1] = fields[1].trim();
                data[i][2] = fields[2].trim();
                data[i][3] = fields[3].trim();
                data[i][4] = fields[4].trim();
                i++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void sort(String[][] data, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(data, left, mid);
            sort(data, mid + 1, right);
            merge(data, left, mid, right);
        }
    }

    private static void merge(String[][] data, int left, int mid, int right) {
        String[][] temp = new String[right - left + 1][5];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            comparisons++;
            if (data[i][0].compareTo(data[j][0]) <= 0) {
                temp[k] = data[i];
                i++;
            } else {
                temp[k] = data[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            temp[k] = data[i];
            i++;
            k++;
        }
        while (j <= right) {
            temp[k] = data[j];
            j++;
            k++;
        }
        for (k = 0; k < temp.length; k++) {
            swaps++;
            data[left + k] = temp[k];
        }
    }

    private static void printSortingInfo(String algorithm, String[][] data, long duration) {

        System.out.println();
        System.out.println("Merge sort:");
        System.out.println("Теоретическая оценка сложности: O(n log n)");
        System.out.println("Количество сравнений: " + comparisons);
        System.out.println("Количество перестановок: " + swaps);
        System.out.println("Время выполнения алгоритма: " + duration + " ms");
        System.out.println();

        System.out.println("Отсортированные данные: ");
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i][0] + ", " + data[i][1] + ", " + data[i][2] + ", " + data[i][3] + ", " + data[i][4]);
        }
    }
}