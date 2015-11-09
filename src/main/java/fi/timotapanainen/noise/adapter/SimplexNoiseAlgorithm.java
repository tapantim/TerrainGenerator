package fi.timotapanainen.noise.adapter;


import fi.timotapanainen.noise.NoiseAlgorithm;
import fi.timotapanainen.noise.impl.SimplexNoise;

/**
 * Created with IntelliJ IDEA.
 * User: tapantim
 * Date: 20.5.2013
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */
public class SimplexNoiseAlgorithm implements NoiseAlgorithm {
    @Override
    public double getNoise(double x, double y) {
        return SimplexNoise.noise(x, y);
    }

}
