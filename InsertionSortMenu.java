import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InsertionSortMenu {

    public static void main(String[] args) {
        String[][] data = readDataFromFile("D:\\УНИВЕР\\II курс\\ASDC\\Lab2\\src\\Menu.txt");
        long startTime = System.nanoTime();
        insertionSort(data);
        long endTime = System.nanoTime();
        printSortingInfo("Insertion sort", data, endTime - startTime);
    }

    private static void insertionSort(String[][] data) {
        for (int i = 1; i < data.length; i++) {
            String[] current = data[i];
            int j = i - 1;
            while (j >= 0 && data[j][0].compareTo(current[0]) > 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = current;
        }
    }

    private static String[][] readDataFromFile(String filename) {
        String[][] data = new String[50][5];
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data[i] = values;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void printSortingInfo(String algorithm, String[][] data, long time) {
        System.out.println();
        System.out.println("Insertion sort:");
        System.out.println("Теоретическая оценка сложности: O(n^2)");
        System.out.println("Количество сравнений: " + ((data.length * (data.length - 1)) / 2));
        System.out.println("Количество перестановок: " + ((data.length * (data.length - 1)) / 2));
        System.out.println("Время выполнения алгоритма: " + time + " ms");
        System.out.println();



        System.out.println("Отсортированные данные: ");
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i][0] + ", " + data[i][1] + ", " + data[i][2] + ", " + data[i][3] + ", " + data[i][4]);
        }
    }
}
