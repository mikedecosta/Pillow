package readtest;

import java.io.*;
import java.util.*;

/*
 * Util class
 *
 * Does the work of comparing two song fingerprints for sameness
 * It does so by comparing each frequency band from each fingerprint
 */

public class Util {

	public static void compareFingers(FingerPrint longFP, FingerPrint shortFP) {
		ArrayList longs = longFP.getFingerprint();
		ArrayList shorts = shortFP.getFingerprint();
		int shortSize = shorts.size();
		int longsSize = longs.size();
		long bestDifference = 0;
		long difference = 0;


		for(int i=0; i<=longsSize-shortSize; i++){
			difference = 0;
			for(int j=0; j<shortSize; j++){
				int ji = j+i;
				int[] longsji = (int[])longs.get(ji);
				int[] shortsj = (int[])shorts.get(j);
				int myDiff = 0;
				for(int k=0; k<4; k++) {
					if(longsji[k] == 0 || shortsj[k] == 0){
						continue;
					}
					myDiff = myDiff + Math.abs((longsji[k] - shortsj[k]));
				}
				difference = difference + myDiff;
			}
			if(i == 0){
				bestDifference = difference;
			} else {
				bestDifference = difference < bestDifference ? difference : bestDifference;
			}
		}
		long averageDiff = bestDifference / shortSize;
		String outcome = averageDiff < 39 ? "yes" : "no";
		String threshold = (averageDiff < 30 || averageDiff > 45) ? "100%" : "50%";
        System.out.println(outcome + " (" + threshold + " confidence)");
	}

    // Deletes temp files created by lame encodes/decodes
    public static void deleteWorkingFiles() {
        File first = new File("working/first.wav");
        File second = new File("working/second.wav");

        first.delete();
        second.delete();
    }

}
