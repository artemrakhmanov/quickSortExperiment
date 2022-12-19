package tests;

import impl.ListBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;

public class ListBuilderTest {

    /*

    - listsGeneratedCorrectSize: sorted rep & non rep correct size
    - listsAreSorted: sorted rep & non rep are sorted
    - singleShuffle: getShuffledToDegree 1 does 1 shuffle
     */


    /*
    sorted repeating & non repeating generate with correct size
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    public void listsGeneratedCorrectSize(int size) {

        ArrayList<Integer> nonRepeating = ListBuilder.getSortedNonRepeating(size, 0);
        Assertions.assertEquals(size, nonRepeating.size());

        ArrayList<Integer> repeating = ListBuilder.getSortedRepeating(size, 0, 100);
        Assertions.assertEquals(size, repeating.size());

    }


    /*
    sorted repeating & non repeating generate sorted arraylists
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    public void listsAreSorted(int size) {

        ArrayList<Integer> expectedNonRepeating = ListBuilder.getSortedNonRepeating(size, 0);
        ArrayList<Integer> generatedNonRepeating = (ArrayList<Integer>) expectedNonRepeating.clone();
        Collections.sort(expectedNonRepeating);

        Assertions.assertEquals(generatedNonRepeating, expectedNonRepeating);

        ArrayList<Integer> expectedRepeating = ListBuilder.getSortedRepeating(size, 0, 100);
        ArrayList<Integer> generatedRepeating = (ArrayList<Integer>) expectedRepeating.clone();
        Collections.sort(expectedNonRepeating);

        Assertions.assertEquals(expectedRepeating, generatedRepeating);

    }


    /*
    Tests for the shuffling method to work on one shuffle (degree = 1)
     */
    @ParameterizedTest
    @ValueSource(ints = {2, 10, 100, 1000})
    public void singleShuffle(int size) {

        ArrayList<Integer> nonRepeating = ListBuilder.getSortedNonRepeating(size, 0);
        ArrayList<Integer> shuffledNonRepeating;

        int shufflesCountNonRepeating = 0;

        while (shufflesCountNonRepeating == 0) {

            shuffledNonRepeating = ListBuilder.getShuffledToDegree(1, nonRepeating);

            for (int i = 0; i < nonRepeating.size(); i++) {

                if (!nonRepeating.get(i).equals(shuffledNonRepeating.get(i))) {
                    shufflesCountNonRepeating++;
                }

            }
        }

        //if one swap is conducted, there will be 2 mismatches
//        Assertions.assertEquals(nonRepeating, shuffledNonRepeating);
        Assertions.assertEquals(2, shufflesCountNonRepeating);

        ArrayList<Integer> repeating = ListBuilder.getSortedRepeating(size, 0, 100);
        ArrayList<Integer> shuffledRepeating;

        int shufflesCountRepeating = 0;

        //run few loops, there is a chance two same elements were swapped
        while(shufflesCountRepeating == 0) {

            shuffledRepeating  = ListBuilder.getShuffledToDegree(1, repeating);

            for (int i = 0; i < repeating.size(); i++) {

                if (!repeating.get(i).equals(shuffledRepeating.get(i))) {
                    shufflesCountRepeating++;
                }

            }
        }

        //if one swap is conducted, there will be 2 mismatches
        Assertions.assertEquals(2, shufflesCountRepeating);

    }

}
