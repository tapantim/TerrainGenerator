package fi.timotapanainen.noise;

import java.util.Collection;
import java.util.LinkedList;

public class CompositeNoisePlane implements NoisePlane {
	
	private Collection<NoisePlane> planes = new LinkedList<NoisePlane>();
	
	public CompositeNoisePlane(Double... freqPersistencePairs) {
        int pairs = freqPersistencePairs.length / 2;
        for (int pair = 0; pair < pairs; ++pair) {
            int startIndex = pair * 2;
            planes.add(new SimplexNoisePlane(freqPersistencePairs[startIndex], freqPersistencePairs[startIndex + 1]));
        }
    }

    public CompositeNoisePlane(int planeCount, double startFreq, double freqMultiplier, double startPersistence, double persistenceMultiplier) {
        double curFrequency = startFreq;
        double curPersistence = startPersistence;
        for (int i = 0; i < planeCount; ++i) {
            SimplexNoisePlane plane = new SimplexNoisePlane(curFrequency, curPersistence);
            planes.add(plane);
            curFrequency *= freqMultiplier;
            curPersistence *= persistenceMultiplier;
        }
    }
    
    public Collection<NoisePlane> getPlanes() {
    	return planes;
    }

	@Override
	public double produceNoise(double sampleXCoord, double sampleYCoord,
			double sampleRange) {
		double totalNoise = 0;
		for (NoisePlane plane : planes) 
			totalNoise += plane.produceNoise(sampleXCoord, sampleYCoord, sampleRange);
		return totalNoise;
	}
	
}
