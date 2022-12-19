package impl;

import java.util.*;

public class ListBuilder {

    /*
    Why random class is intialised multiple times?
    documentation +

    (pseudorandom, one instance would generate same sequences)

    https://stackoverflow.com/questions/14424608/why-isnt-random-class-static
     */

    /*
    Method for getting one array shuffled to a degree.
     */
    public static ArrayList<Integer> getShuffledToDegree(int degree,
                                                         ArrayList<Integer> sortedArray) {

        ArrayList<Integer> result = (ArrayList<Integer>) sortedArray.clone();

        for (int i = 0; i < degree; i++) {
            shuffleOnePair(result);
        }

        return result;
    }

    public static ArrayList<Integer> getSortedNonRepeating(int size,
                                                           int min) {
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            result.add(min++);
        }

        return result;
    }

    /*
    https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Random.html#ints(long)
     */
    public static ArrayList<Integer> getSortedRepeating(int size,
                                                        int min,
                                                        int max) {
        Random random = new Random();
        int[] arrayOfGeneratedNumbers = random.ints(size, min, max).toArray();
        Arrays.sort(arrayOfGeneratedNumbers);

        ArrayList<Integer> result = new ArrayList<>();

        for (int i : arrayOfGeneratedNumbers) {
            result.add(i);
        }

        return result;
    }


    private static void shuffleOnePair(ArrayList<Integer> arrayList) {

        int arraySize = arrayList.size();

        Collections.swap(arrayList,
                        getRandomIndex(arraySize),
                        getRandomIndex(arraySize));
    }


    /*
    Get a random int in the range deived from the arraySize.
    This method utilises java.util.Random.nextInt to retrieve
    a random number.
     */
    private static int getRandomIndex(int arraySize) {
        Random random = new Random();
        return random.nextInt(arraySize);
    }

}
