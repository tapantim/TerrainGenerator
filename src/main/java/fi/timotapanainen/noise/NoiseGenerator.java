package fi.timotapanainen.noise;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: tapantim
 * Date: 20.5.2013
 * Time: 19:48
 * To change this template use File | Settings | File Templates.
 */
public class NoiseGenerator {

    public static void generate(double[][] samples, Collection<SimplexNoisePlane> planes) {
        for (SimplexNoisePlane plane : planes)
            generate(samples, plane);
    }

    public static void generate(double[][] samples, SimplexNoisePlane plane) {
        int width = samples.length;
        int height = samples[0].length;
       
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                samples[x][y] += plane.produceNoise(x, y, samples.length);
            }
        }
    }
    
}
