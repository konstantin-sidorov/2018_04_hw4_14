package ru.otus;

public class Main {
    public static void main(String[] args) {
        MultiSort multiSort = new MultiSort(new int[]{29, 37, 23, 16, 31, 19}, 4);
        multiSort.start();
        try {
            multiSort.join();
        } catch (Exception e) {

        }
        for (int elem:multiSort.getSorted()) {
            System.out.print("" + elem + " ");
        }
    }
}
