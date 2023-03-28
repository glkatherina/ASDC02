import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BubbleSortMenu {

    private static final String FILE_PATH = "D:\\УНИВЕР\\II курс\\ASDC\\Lab2\\src\\Menu.txt";

    public static void main(String[] args) {
        String[][] menuData = readMenuDataFromFile(FILE_PATH);

        String[][] sortedData = bubbleSort(menuData, 0);
        printSortingInfo("Bubble Sort", sortedData);
    }

    private static String[][] readMenuDataFromFile(String filePath) {
        String[][] menuData = new String[50][5];
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNextLine()) {
                String[] fields = scanner.nextLine().split(",");
                menuData[i++] = fields;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return menuData;
    }

    private static String[][] bubbleSort(String[][] data, int keyIndex) {
        int comparisons = 0;
        int swaps = 0;
        long startTime = System.nanoTime();

        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j][keyIndex].compareTo(data[j + 1][keyIndex]) > 0) {
                    String[] temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    swaps++;
                }
                comparisons++;
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;

        System.out.println();
        System.out.println("Bubble Sort:");
        System.out.println("Теоретическая оценка сложности: O(n^2)");
        System.out.println("Количество сравнений: " + comparisons);
        System.out.println("Количество перестановок: " + swaps);
        System.out.println("Время выполнения алгоритма: " + duration + " ms");
        System.out.println();

        return data;
    }

    private static void printSortingInfo(String algorithm, String[][] data) {
        System.out.println("Отсортированные данные: ");
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i][0] + ", " + data[i][1] + ", " + data[i][2] + ", " + data[i][3] + ", " + data[i][4]);
        }
        System.out.println();
    }

}
