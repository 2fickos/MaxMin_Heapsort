
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MaxMinMaxHeap
{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[] arr = null;
        int size = 0;

        while (true) {
            System.out.println("-------- Menu --------");
            System.out.println("1. Build a Heap");
            System.out.println("2. Extract Maximum Value");
            System.out.println("3. Extract Minimum Value");
            System.out.println("4. Insert a Value");
            System.out.println("5. Delete a Value");
            System.out.println("6. Print the Heap");
            System.out.println("7. heapifymaxmin Heap");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the size of the array: ");
                    size = sc.nextInt();
                    arr = new int[size];
                    System.out.print("Enter the elements of the array: ");
                    for (int i = 0; i < size; i++) {
                        arr[i] = sc.nextInt();
                    }
                    buildMaxHeap(arr);
                    System.out.println("Heap has been built.");
                    break;
                case 2:
                    if (arr == null) {
                        System.out.println("Heap is not built yet.");
                    } else if (arr.length == 0) {
                        System.out.println("Heap is empty.");
                    } else {
                        buildMaxHeap(arr);
                        int maxValue = arr[0];
                        arr = heapExtractMax(arr);
                        System.out.println("Max value extracted from heap: " + maxValue);
                    }
                    break;
                case 3:
                    if (arr == null) {
                        System.out.println("Heap is not built yet.");
                    } else if (arr.length == 0) {
                        System.out.println("Heap is empty.");
                    } else
                    {
                        buildMinHeap(arr);
                        int minValue = arr[0];
                        arr = heapExtractMin(arr);
                        System.out.println("Min value extracted from heap: " + minValue);
                    }
                    break;

                case 4:
                    if (arr == null) {
                        System.out.println("Heap is not built yet.");
                    } else {
                        System.out.print("Enter a value to insert into the heap: ");
                        int valueToInsert = sc.nextInt();
                        arr = heapInsert(arr, valueToInsert);
                        System.out.println("Value has been inserted into the heap.");
                    }
                    break;
                case 5:
                    if (arr == null) {
                        System.out.println("Heap is not built yet.");
                    } else {
                        System.out.print("Enter the index of the value to delete from the heap: ");
                        int indexToDelete = sc.nextInt();
                        if (indexToDelete < 0 || indexToDelete >= arr.length) {
                            System.out.println("Invalid index.");
                        } else {
                            int[] newArr = heapDelete(arr, indexToDelete);
                            arr = newArr;
                            System.out.println("Value deleted from the heap.");
                        }
                    }
                    break;

                case 6:
                    if (arr == null) {
                        System.out.println("Heap is not built yet.");
                    } else {
                        System.out.println("Current heap: " + Arrays.toString(arr));
                    }
                    break;
                case 7 :
                    if (arr == null) {
                        System.out.println("Heap is not built yet.");
                    } else {
                        buildMinMaxHeap(arr);
                        //  printArray(arr);
                        System.out.println("Current heap: " + Arrays.toString(arr));
                    }
                    break;
                case 0:
                    System.out.println("Exiting the program...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void buildMinMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapifyMinMax(arr, n, i);
        }
    }

    public static void heapifyMinMax(int arr[], int N, int i) {
        int minmax = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Compare with the children based on the level
        if (isEvenLevel(i)) {
            if (left < N && arr[left] > arr[minmax])
                minmax = left;

            if (right < N && arr[right] > arr[minmax])
                minmax = right;
        } else {
            if (left < N && arr[left] < arr[minmax])
                minmax = left;

            if (right < N && arr[right] < arr[minmax])
                minmax = right;
        }

        if (minmax != i) {
            int swap = arr[i];
            arr[i] = arr[minmax];
            arr[minmax] = swap;

            heapifyMinMax(arr, N, minmax);
        }
    }

    public static boolean isEvenLevel(int i) {
        // Calculate the level of the node based on its index
        int level = (int) (Math.log(i + 1) / Math.log(2));
        return level % 2 == 0;
    }

    //    //////////////////////////////////
    public static void buildMinHeap(int[] arr) {
        int n = arr.length;
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapifyMin(arr, n, i);
        }
    }

    public static void buildMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    public static void heapify(int arr[], int N, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < N && arr[left] > arr[largest])

            largest = left;

        if (right < N && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, N, largest);
        }
    }

    public static int[] heapExtractMax(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        int max = arr[0];
        if (n == 1) {
            return new int[0]; // Return an empty array
        }
        int last = arr[n - 1];
        arr[0] = last;
        int[] newArr = resizeArray(arr, n - 1);
        heapifyMax(newArr, newArr.length, 0);
        return newArr;
    }

    public static int[] heapExtractMin(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        int min = arr[0];
        if (n == 1) {
            return new int[0]; // Return an empty array
        }
        int last = arr[n - 1];
        arr[0] = last;
        int[] newArr = resizeArray(arr, n - 1);
        heapifyMin(newArr, newArr.length, 0);
        return newArr;
    }

    public static void heapifyMax(int arr[], int N, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < N && arr[left] > arr[largest])
            largest = left;

        if (right < N && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapifyMax(arr, N, largest);
        }
    }

    public static void heapifyMin(int arr[], int N, int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < N && arr[left] < arr[smallest])
            smallest = left;

        if (right < N && arr[right] < arr[smallest])
            smallest = right;

        if (smallest != i) {
            int swap = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = swap;

            heapifyMin(arr, N, smallest);
        }
    }

    public static int[] heapInsert(int[] arr, int key) {
        int N = arr.length;
        arr = resizeArray(arr, N + 1);
        int i = N;
        arr[i] = key;
        while (i != 0 && arr[(i - 1) / 2] < arr[i]) {
            int parent = (i - 1) / 2;
            int temp = arr[i];
            arr[i] = arr[parent];
            arr[parent] = temp;
            i = parent;
        }
        return arr;
    }

    public static int[] heapDelete(int[] arr, int index) {
        int n = arr.length;
        if (n == 0) {
            throw new IllegalArgumentException("Heap is empty");
        }
        if (index < 0 || index >= n) {
            throw new IllegalArgumentException("Invalid index");
        }
        int[] newArr = new int[n - 1];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (i != index) {
                newArr[j] = arr[i];
                j++;
            }
        }
        buildMaxHeap(newArr);
        return newArr;
    }

    public static void printArray(int[] arr) {
        System.out.print(" ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println(" ");
    }

    private static int[] resizeArray(int[] arr, int newSize) {
        int[] newArr = new int[newSize];
        System.arraycopy(arr, 0, newArr, 0, Math.min(arr.length, newSize));
        return newArr;
    }
}
