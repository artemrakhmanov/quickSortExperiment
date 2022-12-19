package impl;

import java.util.ArrayList;
import java.util.Comparator;

public class QuickSorter {

    /*
    "Not in-place" implementation of QuickSort algorithm with a last
    element pivoting.
     */
    public static ArrayList<Integer> qsort(ArrayList<Integer> inputArray) {

        int size = inputArray.size();

        if (size <= 1) {
            return inputArray;
        }
        else {
            int lastIndex = size - 1;
            int pivot = inputArray.get(lastIndex);

            ArrayList<Integer> leftPartition = new ArrayList<>();
            ArrayList<Integer> rightPartition = new ArrayList<>();

            for(int i = 0; i < lastIndex; i++) {

                int element = inputArray.get(i);

                if (element < pivot) {
                    leftPartition.add(element);
                } else {
                    rightPartition.add(element);
                }

            }

            ArrayList<Integer> result = qsort(leftPartition);
            result.add(pivot);
            result.addAll(qsort(rightPartition));

            return result;
        }
    }

    /*
    Method for getting the time of running qsort.
     */
    public static int qsortTime(ArrayList<Integer> inputArray) {

        long startTime = System.currentTimeMillis();

        qsort(inputArray);

        return (int) Math.abs(System.currentTimeMillis() - startTime);
    }

}









