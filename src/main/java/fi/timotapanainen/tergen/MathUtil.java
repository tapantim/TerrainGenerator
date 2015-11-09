package fi.timotapanainen.tergen;

import com.google.common.primitives.Doubles;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tapantim
 * Date: 21.5.2013
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
public class MathUtil {

    public static double scaleToRange(double curValue, double curRangeMin, double curRangeMax, double newRangeMin, double newRangeMax) {
        double scaleFactor = (newRangeMax - newRangeMin) / (curRangeMax - curRangeMin);
        return (curValue - curRangeMin) * scaleFactor;
    }

    public static void scaleToRange(double[][] values, double lowerBound, double upperBound) {
        int width = values.length;
        int height = values[0].length;
        double valuesMin = min(values);
        double valuesMax = max(values);
        double scaleFactor = (upperBound - lowerBound) / (valuesMax - valuesMin);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                values[x][y] = (values[x][y] - valuesMin) * scaleFactor;
            }
        }
    }

    public static void scaleToRange(double[][][] values, double lowerBound, double upperBound) {
        int width = values.length;
        int height = values[0].length;
        int zelems = values[0][0].length;
        double valuesMin = min(values);
        double valuesMax = max(values);
        double scaleFactor = (upperBound - lowerBound) / (valuesMax - valuesMin);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < zelems; ++z)
                    values[x][y][z] = (values[x][y][z] - valuesMin) * scaleFactor;
            }
        }
    }

    public static void scaleToRange(double[][] values, double lowerBound, double upperBound, double alterValuesAbove) {
        int width = values.length;
        int height = values[0].length;
        double valuesMin = alterValuesAbove;
        double valuesMax = max(values);
        double scaleFactor = (upperBound - lowerBound) / (valuesMax - valuesMin);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (values[x][y] >= alterValuesAbove)
                    values[x][y] = (values[x][y] - valuesMin) * scaleFactor;
            }
        }
    }

    public static void addToAll(double[][] values, double value) {
        int width = values.length;
        int height = values[0].length;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                values[x][y] += value;
            }
        }

    }

    public static double percentile(double[][] values, double p) {
        List<Double> valueList = Doubles.asList(convertTo1D(values));
        Collections.sort(valueList);
        long index = Math.round(p / 100 * valueList.size());
        return valueList.get((int) index);
    }

    public static double[] convertTo1D(double[][] source) {
        int width = source.length;
        int height = source[0].length;
        double[] target = new double[width * height];
        for (int x = 0; x < source.length; ++x) {
            int xOffset = x * height;
            for (int y = 0; y < source[0].length; ++y) {
                target[xOffset + y] = source[x][y];
            }
        }
        return target;
    }

    public static double min(double[][] values) {
        int width = values.length;
        int height = values[0].length;
        double min = Double.MAX_VALUE;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (values[x][y] < min)
                    min = values[x][y];
            }
        }
        return min;
    }

    public static double min(double[][][] values) {
        int width = values.length;
        int height = values[0].length;
        int zelems = values[0][0].length;
        double min = Double.MAX_VALUE;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < zelems; ++z) {
                    if (values[x][y][z] < min)
                        min = values[x][y][z];
                }
            }
        }
        return min;
    }

    public static double max(double[][] values) {
        int width = values.length;
        int height = values[0].length;
        double max = Double.MIN_VALUE;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (values[x][y] > max)
                    max = values[x][y];
            }
        }
        return max;
    }

    public static double max(double[][][] values) {
        int width = values.length;
        int height = values[0].length;
        int zelems = values[0][0].length;
        double max = Double.MIN_VALUE;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < zelems; ++z) {
                    if (values[x][y][z] > max)
                        max = values[x][y][z];
                }

            }
        }
        return max;
    }

    public static void add(double[][] from, double[][] to) {
        int width = from.length;
        int height = from[0].length;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                to[x][y] += from[x][y];

            }
        }
    }
}
