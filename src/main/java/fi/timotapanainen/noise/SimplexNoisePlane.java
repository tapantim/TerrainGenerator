package fi.timotapanainen.noise;

import fi.timotapanainen.noise.adapter.SimplexNoiseAlgorithm;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: timotapanainen
 * Date: 25.7.2013
 * Time: 20.15
 * To change this template use File | Settings | File Templates.
 */
public class SimplexNoisePlane implements NoisePlane {

    private double frequency;
    private double persistence = 1d;
    private double sampleStartOffsetX, sampleStartOffsetY;
    private static Random rand = new Random(System.currentTimeMillis());
    private NoiseAlgorithm algorithm = new SimplexNoiseAlgorithm();
    
    public SimplexNoisePlane(double frequency) {
    	this(frequency, 1d);
    }
    
    public SimplexNoisePlane(double frequency, double persistence) {
    	this(frequency, persistence, rand.nextDouble(), rand.nextDouble());
    }
    
    public SimplexNoisePlane(double frequency, double persistence, double sampleStartOffsetX, double sampleStartOffsetY) {
    	this.frequency = frequency;
        this.persistence = persistence;
        this.sampleStartOffsetX = sampleStartOffsetX;
        this.sampleStartOffsetY = sampleStartOffsetY;
    }

    public double getFrequency() {
        return frequency;
    }

    public double getPersistence() {
        return persistence;
    }

	@Override
	public double produceNoise(double sampleXCoord, double sampleYCoord, double sampleRange) {
		 double freqMultiplier = frequency / sampleRange;
		 double noise = algorithm.getNoise(
				 sampleStartOffsetX + sampleXCoord * freqMultiplier,
				 sampleStartOffsetY + sampleYCoord * freqMultiplier);
		 return noise * persistence;
   	}
	
	public String toString() {
        return "NoiseParameters [frequency: " + frequency + ", persistence: " + persistence + "]";
    }

}
