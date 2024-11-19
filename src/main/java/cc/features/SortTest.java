package cc.features;

/**
 * SortTest.java
 *
 * 各种排序算法的时间复杂度测试
 */
public class SortTest implements JvmTest {

    @Override
    public void runTest() throws Exception {
        // 示例：测试多种排序算法的性能
        int size = 100_000;
        int[] originalArray = generateRandomArray(size);

        // Define array clones for each sort to ensure the same input
        int[] arrayQuickSort = originalArray.clone();
        int[] arrayMergeSort = originalArray.clone();
        int[] arrayHeapSort = originalArray.clone();
        int[] arrayInsertionSort = originalArray.clone();
        int[] arraySelectionSort = originalArray.clone();
        int[] arrayBubbleSort = originalArray.clone();
        int[] arrayShellSort = originalArray.clone();
        int[] arrayCountingSort = originalArray.clone();

        // Quick Sort
        long quickSortStart = System.nanoTime();
        quickSort(arrayQuickSort, 0, arrayQuickSort.length - 1);
        long quickSortEnd = System.nanoTime();
        if (!isSorted(arrayQuickSort)) {
            throw new Exception("快速排序结果未排序正确。");
        }
        System.out.println("快速排序耗时: " + (quickSortEnd - quickSortStart) / 1_000_000 + " 毫秒");

        // Merge Sort
        long mergeSortStart = System.nanoTime();
        mergeSort(arrayMergeSort, 0, arrayMergeSort.length - 1);
        long mergeSortEnd = System.nanoTime();
        if (!isSorted(arrayMergeSort)) {
            throw new Exception("归并排序结果未排序正确。");
        }
        System.out.println("归并排序耗时: " + (mergeSortEnd - mergeSortStart) / 1_000_000 + " 毫秒");

        // Heap Sort
        long heapSortStart = System.nanoTime();
        heapSort(arrayHeapSort);
        long heapSortEnd = System.nanoTime();
        if (!isSorted(arrayHeapSort)) {
            throw new Exception("堆排序结果未排序正确。");
        }
        System.out.println("堆排序耗时: " + (heapSortEnd - heapSortStart) / 1_000_000 + " 毫秒");

        // Insertion Sort
        long insertionSortStart = System.nanoTime();
        insertionSort(arrayInsertionSort);
        long insertionSortEnd = System.nanoTime();
        if (!isSorted(arrayInsertionSort)) {
            throw new Exception("插入排序结果未排序正确。");
        }
        System.out.println("插入排序耗时: " + (insertionSortEnd - insertionSortStart) / 1_000_000 + " 毫秒");

        // Selection Sort
        long selectionSortStart = System.nanoTime();
        selectionSort(arraySelectionSort);
        long selectionSortEnd = System.nanoTime();
        if (!isSorted(arraySelectionSort)) {
            throw new Exception("选择排序结果未排序正确。");
        }
        System.out.println("选择排序耗时: " + (selectionSortEnd - selectionSortStart) / 1_000_000 + " 毫秒");

        // Shell Sort
        long shellSortStart = System.nanoTime();
        shellSort(arrayShellSort);
        long shellSortEnd = System.nanoTime();
        if (!isSorted(arrayShellSort)) {
            throw new Exception("希尔排序结果未排序正确。");
        }
        System.out.println("希尔排序耗时: " + (shellSortEnd - shellSortStart) / 1_000_000 + " 毫秒");

        // Counting Sort
        // Note: Counting Sort is efficient for integers within a specific range.
        // For demonstration, we'll adjust the array to a suitable range.
        int[] arrayCountingSortAdjusted = generateRandomArrayWithRange(size, 0, size);
        long countingSortStart = System.nanoTime();
        countingSort(arrayCountingSortAdjusted, 0, size);
        long countingSortEnd = System.nanoTime();
        if (!isSorted(arrayCountingSortAdjusted)) {
            throw new Exception("计数排序结果未排序正确。");
        }
        System.out.println("计数排序耗时: " + (countingSortEnd - countingSortStart) / 1_000_000 + " 毫秒");
    }

    @Override
    public String getTestName() {
        return "排序算法性能测试";
    }

    // 生成随机数组
    private int[] generateRandomArray(int size) {
        int[] array = new int[size];
        java.util.Random rand = new java.util.Random();
        for(int i=0;i<size;i++) {
            array[i] = rand.nextInt();
        }
        return array;
    }

    // 生成指定范围的随机数组（用于计数排序）
    private int[] generateRandomArrayWithRange(int size, int min, int max) {
        int[] array = new int[size];
        java.util.Random rand = new java.util.Random();
        for(int i=0;i<size;i++) {
            array[i] = rand.nextInt((max - min) + 1) + min;
        }
        return array;
    }

    // 快速排序实现
    private void quickSort(int[] arr, int low, int high) {
        if(low < high){
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }

    private int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = (low-1);
        for(int j=low; j<high; j++){
            if(arr[j] < pivot){
                i++;
                // 交换
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // 交换 pivot
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }

    // 归并排序实现
    private void mergeSort(int[] arr, int l, int r) {
        if(l < r){
            int m = l + (r - l)/2;
            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r);
            merge(arr, l, m, r);
        }
    }

    private void merge(int[] arr, int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];
        for(int i=0;i<n1;i++) L[i] = arr[l + i];
        for(int j=0;j<n2;j++) R[j] = arr[m + 1 + j];

        int i=0, j=0;
        int k=l;
        while(i < n1 && j < n2){
            if(L[i] <= R[j]){
                arr[k++] = L[i++];
            } else{
                arr[k++] = R[j++];
            }
        }

        while(i < n1){
            arr[k++] = L[i++];
        }
        while(j < n2){
            arr[k++] = R[j++];
        }
    }

    // 堆排序实现
    private void heapSort(int[] arr) {
        int n = arr.length;

        // 构建最大堆
        for(int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // 一个个从堆中取出元素
        for(int i = n -1; i >=0; i--){
            // 移动当前根到末尾
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 调整堆
            heapify(arr, i, 0);
        }
    }

    private void heapify(int[] arr, int n, int i){
        int largest = i; // 初始化最大元素为根
        int l = 2*i +1; // 左子节点
        int r = 2*i +2; // 右子节点

        // 如果左子节点比根大
        if(l < n && arr[l] > arr[largest])
            largest = l;

        // 如果右子节点比目前最大的大
        if(r < n && arr[r] > arr[largest])
            largest = r;

        // 如果最大不是根
        if(largest != i){
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // 递归堆化受影响的子树
            heapify(arr, n, largest);
        }
    }

    // 插入排序实现
    private void insertionSort(int[] arr){
        int n = arr.length;
        for(int i=1;i<n;i++){
            int key = arr[i];
            int j = i -1;

            while(j >=0 && arr[j] > key){
                arr[j+1] = arr[j];
                j = j -1;
            }
            arr[j+1] = key;
        }
    }

    // 选择排序实现
    private void selectionSort(int[] arr){
        int n = arr.length;
        for(int i=0;i<n-1;i++){
            int min_idx = i;
            for(int j=i+1;j<n;j++)
                if(arr[j] < arr[min_idx])
                    min_idx = j;

            // 交换
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    // 希尔排序实现
    private void shellSort(int[] arr){
        int n = arr.length;
        for(int gap = n/2; gap >0; gap /=2){
            for(int i=gap;i<n;i++){
                int temp = arr[i];
                int j = i;
                while(j >= gap && arr[j - gap] > temp){
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }

    // 计数排序实现
    private void countingSort(int[] arr, int min, int max){
        int range = max - min +1;
        int[] count = new int[range];
        int n = arr.length;

        // 统计每个元素的出现次数
        for(int i=0;i<n;i++)
            count[arr[i] - min]++;

        // 修改 count 数组，使每个元素表示其在输出数组中的位置
        for(int i=1;i<count.length;i++)
            count[i] += count[i-1];

        int[] output = new int[n];
        for(int i = n -1;i>=0;i--){
            output[count[arr[i] - min] -1] = arr[i];
            count[arr[i] - min]--;
        }

        // 将排序好的元素复制回原数组
        System.arraycopy(output, 0, arr, 0, n);
    }

    // 检查数组是否已排序
    private boolean isSorted(int[] arr){
        for(int i=1;i<arr.length;i++){
            if(arr[i-1] > arr[i]){
                return false;
            }
        }
        return true;
    }
}
