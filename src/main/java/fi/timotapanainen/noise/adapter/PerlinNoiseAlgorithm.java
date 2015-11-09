package fi.timotapanainen.noise.adapter;

import fi.timotapanainen.noise.NoiseAlgorithm;
import fi.timotapanainen.noise.impl.ImprovedNoise;

/**
 * Created with IntelliJ IDEA.
 * User: tapantim
 * Date: 22.5.2013
 * Time: 21:34
 * To change this template use File | Settings | File Templates.
 */
public class PerlinNoiseAlgorithm implements NoiseAlgorithm {

    @Override
    public double getNoise(double x, double y) {
        return ImprovedNoise.noise(x, y, 0);
    }

}
