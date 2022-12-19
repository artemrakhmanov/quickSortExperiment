package impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuickSortAnalyser {

    public static void main(String[] args) {

        //perform analysis on repetition size 5000
        ArrayList<SortRunData> data1 = performAnalysis(true, 10000,
                                            10000, 50,
                                            10);
        //collect data to csv
        SortRunData.transferDataToCSV("repetition10000", data1);


        //perform analysis on repetition size 3000
        ArrayList<SortRunData> data2 = performAnalysis(true, 5000,
                                            10000, 50,
                                            10);
        //collect data to csv
        SortRunData.transferDataToCSV("repetition5000", data2);

        //perform analysis on repetition size 1000
        ArrayList<SortRunData> data3 = performAnalysis(true, 3000,
                                            10000, 50,
                                            10);
        //collect data to csv
        SortRunData.transferDataToCSV("repetition3000", data3);



        //perform analysis on non-repeating size 10000
        ArrayList<SortRunData> data4 = performAnalysis(true, 10000,
                                            10000, 50,
                                            10);
        //collect data to csv
        SortRunData.transferDataToCSV("nonRepeating10000", data4);


        //perform analysis on non-repeating size 5000
        ArrayList<SortRunData> data5 = performAnalysis(true, 5000,
                                            10000, 50,
                                            10);
        //collect data to csv
        SortRunData.transferDataToCSV("nonRepeating5000", data5);

        //perform analysis on non-repeating size 3000
        ArrayList<SortRunData> data6 = performAnalysis(true, 3000,
                                            10000, 50,
                                            10);
        //collect data to csv
        SortRunData.transferDataToCSV("nonRepeating3000", data6);

    }

    /*
    This method performs all experiments, analyses data and collects
    it into an arraylist of SortRunData objects.
     */
    private static ArrayList<SortRunData> performAnalysis(boolean repetition, int size,
                                                          int maxShuffleness,
                                                          int shufflenessRepeatSample,
                                                          int listRepeatSample) {

        ArrayList<SortRunData> data = new ArrayList<>();

//        /*
//        Experiment instructions
//         */
//        int maxShuffleness = 10000;    // 0 -> n shuffles
//        int shufflenessRepeatSample = 50;   //each shuffleness list sample
//        int listRepeatSample = 10;      //each list repetition

        System.out.println("PERFORM ANALYSIS START");   //notification

        int s = 0;
        //analyse for each shuffleness
        while(s <= maxShuffleness) {

            double[] runTimeListMeans = new double[shufflenessRepeatSample];

            //for each shuffleness perform it
            for (int sRepeat = 0; sRepeat < shufflenessRepeatSample; sRepeat++) {

                int[] runTimes = new int[listRepeatSample];

                ArrayList<Integer> arrayList = generateArrayList(repetition, size);

                //shuffle to s
                arrayList = ListBuilder.getShuffledToDegree(s, arrayList);

                //for each list, sort n times to find mean
                for (int lRepeat = 0; lRepeat < listRepeatSample; lRepeat++) {

                    //run sort, collect times
                    runTimes[lRepeat] = QuickSorter.qsortTime(arrayList);

                }

                //find mean for a list
                double mean = Arrays.stream(runTimes).sum() / (double) listRepeatSample;

                runTimeListMeans[sRepeat] = mean;

            }

            System.out.println("shuffle: " + s);    //notification for progress

            //log data for shuffleness s
            data.add(new SortRunData(s, runTimeListMeans));


            /*
            based on observations from the trend, to save time and space for data,
            the shuffless gets skipped after the point where the trend plateaus
             */
            if (s < 50) {
                s++;
            } else if (s < 100) {
                s += 2;
            } else if (s < 200) {
                s += 10;
            } else if (s < 500) {
                s += 50;
            } else if (s < 1000) {
                s += 100;
            } else {
                s += 1000;
            }
        }

        return data;
    }

    private static ArrayList<Integer> generateArrayList(boolean repetition, int size) {
        return repetition ?
                ListBuilder.getSortedRepeating(size,-1000, 1000)
                :
                ListBuilder.getSortedNonRepeating(size, -500);
    }

}