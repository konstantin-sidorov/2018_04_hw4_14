package ru.otus;


public class MultiSort extends Thread {
    private int[] unsorted, sorted;
    private int count_threads;

    public MultiSort(int[] unsorted, int count_threads) {
        this.unsorted = unsorted;
        this.count_threads = count_threads;
    }

    public int[] getSorted() {
        return sorted;
    }

    public void run() {
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
            if (activeCount() < count_threads) {
                MultiSort firstArray = new MultiSort(first,this.count_threads);
                MultiSort secondArray = new MultiSort(second,this.count_threads);
                firstArray.start();
                secondArray.start();
                try {
                    firstArray.join();
                    secondArray.join();
                    sorted = SimpleSort.merge(firstArray.getSorted(), secondArray.getSorted());
                } catch (Exception e) {

                }

            } else {
                SimpleSort leftSort = new SimpleSort(first);
                SimpleSort rightSort = new SimpleSort(second);

                leftSort.sort();
                rightSort.sort();

                sorted = SimpleSort.merge(leftSort.getSorted(), rightSort.getSorted());
            }

        }
    }


}
