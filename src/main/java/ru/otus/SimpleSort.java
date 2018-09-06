package ru.otus;


public class SimpleSort {
    private int[] unsorted, sorted;

    public SimpleSort(int[] unsorted) {
        this.unsorted = unsorted;
    }

    public int[] getSorted() {
        return sorted;
    }

    /**
     * Мержит два отсортированных массива
     */
    public static int[] merge(int[] firstArray, int[] secondArray) {
        int cursorFirst = 0, cursorSecond = 0, counter = 0;
        int[] merged = new int[firstArray.length + secondArray.length];

        while (cursorFirst < firstArray.length && cursorSecond < secondArray.length) {
            if (firstArray[cursorFirst] <= secondArray[cursorSecond]) {
                merged[counter] = firstArray[cursorFirst];
                cursorFirst++;
            } else {
                merged[counter] = secondArray[cursorSecond];
                cursorSecond++;
            }
            counter++;
        }

        if (cursorFirst < firstArray.length) {
            System.arraycopy(firstArray, cursorFirst, merged, counter, merged.length - counter);
        }
        if (cursorSecond < secondArray.length) {
            System.arraycopy(secondArray, cursorSecond, merged, counter, merged.length - counter);
        }

        return merged;
    }

    /**
     * Простая сортировка массива в 1 поток
     */
    public void sort() {
        int middle;
        int[] first, second;

        if (unsorted.length <= 1) {
            sorted = unsorted;
        } else {
            middle = unsorted.length / 2;

            first = new int[middle];
            second = new int[unsorted.length - middle];

            System.arraycopy(unsorted, 0, first, 0, middle);
            System.arraycopy(unsorted, middle, second, 0, unsorted.length - middle);

            SimpleSort firstArray = new SimpleSort(first);
            SimpleSort secondArray = new SimpleSort(second);

            firstArray.sort();
            secondArray.sort();

            sorted = merge(firstArray.getSorted(), secondArray.getSorted());
        }
    }
}