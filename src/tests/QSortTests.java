package tests;

import impl.ListBuilder;
import impl.QuickSorter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.desktop.QuitEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QSortTests {

    /*
    - non repeating sorted
    - repeating sorted
    - non repeating shuffled
    - repeating shuffled
     */


    /*
    QuickSort on non repeating sorted sequences
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000, 3000, 5000})
    public void nonRepeatingSorted(int size) {

        ArrayList<Integer> expected = ListBuilder.getSortedNonRepeating(size, -100);
        ArrayList<Integer> sorted = QuickSorter.qsort(expected);

        Assertions.assertEquals(expected, sorted);

    }

    /*
    QuickSort on non repeating shuffled sequences
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000, 3000, 5000})
    public void nonRepeatingShuffled(int size) {

        ArrayList<Integer> expected = ListBuilder.getSortedNonRepeating(size, -100);

        ArrayList<Integer> shuffled = (ArrayList<Integer>) expected.clone();

        Collections.shuffle(shuffled);

        ArrayList<Integer> sorted = QuickSorter.qsort(shuffled);

        Assertions.assertEquals(expected, sorted);

    }


    /*
    QuickSort on repeating sorted sequences
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000, 3000, 5000})
    public void repeatingSorted(int size) {

        ArrayList<Integer> expected = ListBuilder.getSortedRepeating(size, -100, 100);
        ArrayList<Integer> sorted = QuickSorter.qsort(expected);

        Assertions.assertEquals(expected, sorted);

    }


    /*
    QuickSort on repeating shuffled sequences
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000, 3000, 5000})
    public void repeatingShuffled(int size) {

        ArrayList<Integer> expected = ListBuilder.getSortedRepeating(size, -100, 100);

        ArrayList<Integer> shuffled = (ArrayList<Integer>) expected.clone();

        Collections.shuffle(shuffled);

        ArrayList<Integer> sorted = QuickSorter.qsort(shuffled);

        Assertions.assertEquals(expected, sorted);

    }

}
