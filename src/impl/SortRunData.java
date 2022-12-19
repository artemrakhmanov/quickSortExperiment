package impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SortRunData {

    private final int shuffleness;
    private final int timeMean;
    private final int timeSD;
    private final int timeLow;
    private final int timeHigh;

    SortRunData(int s, double[] shuffleMeans) {
        shuffleness = s;
        timeMean = getMean(shuffleMeans);
        timeSD = getSD(shuffleMeans);
        timeLow = getLow(shuffleMeans);
        timeHigh = getHigh(shuffleMeans);
    }

    public static void transferDataToCSV(String fileName, ArrayList<SortRunData> data) {

        //build string csv from all data
        StringBuilder sb = new StringBuilder();

        //column names
        sb.append("s,");
        sb.append("mean,");
        sb.append("sd,");
        sb.append("low,");
        sb.append("high");

        //rows
        for (SortRunData srd : data) {
            sb.append("\n");
            sb.append(srd.toCSV());
        }

        //write to a file
        try {

            File csvFile = new File(fileName + ".csv");

            csvFile.createNewFile();

            FileWriter fileWriter = new FileWriter(csvFile);

            fileWriter.write(sb.toString());

            fileWriter.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private String toCSV() {
        return  shuffleness + "," + timeMean + "," + timeSD + "," + timeLow + "," + timeHigh;
    }

    private static int getMean(double[] shuffleMeans) {
        return (int) (Arrays.stream(shuffleMeans).sum() / shuffleMeans.length);
    }

    private static int getSD(double[] shuffleMeans) {

        int mean = getMean(shuffleMeans);
        int n = shuffleMeans.length;
        double sumNumerator = 0;

        for (double d: shuffleMeans) {
            sumNumerator += Math.pow((d - mean), 2);
        }

        return (int) Math.sqrt(sumNumerator/n);
    }

    private static int getLow(double[] shuffleMeans) {

        double lowest = shuffleMeans[0];

        for (double d : shuffleMeans) {

            lowest = Math.min(d, lowest);

        }

        return (int) lowest;
    }

    private static int getHigh(double[] shuffleMeans) {

        double highest = shuffleMeans[0];

        for (double d : shuffleMeans) {

            highest = Math.max(d, highest);
        }

        return (int) highest;
    }
}
