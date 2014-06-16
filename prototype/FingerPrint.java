package readtest;

import java.util.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import flanagan.complex.Complex;
import flanagan.math.FourierTransform;

/*
 * FingerPrint class
 *
 * This class is used to create a unique song fingerprint
 * It does so in chunks utilizing the flannagan fourier transform jar
 * to break up the song into frequency bands and store those points
 */

public class FingerPrint {
	private ArrayList<int[]> fingerprint;

    public FingerPrint() {
		this.fingerprint = new ArrayList<int[]>();
    }

	public ArrayList<int[]> getFingerprint(){
		return fingerprint;
	}


    // Compute the fingerprint for the first 15seconds of the shorter song
    public void fingerPrint(File fileIn, boolean isLongSong) {
        int totalFramesRead = 0;

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileIn);
            Song song = new Song(audioInputStream);

            if (song.getBytesPerFrame() == AudioSystem.NOT_SPECIFIED) {
                // Set byte frame to 1 if not specified
                song.setBytesPerFrame(1);
            }

            int chunksAllowed = song.getNumChunks();
			int songChunkSize = song.getChunkSize();

            byte[] audioBytes = new byte[songChunkSize];
            byte[] allSong = new byte[(int) songChunkSize * chunksAllowed];

            int chunkNum = 0;

            try {
                int numBytesRead = 0;
                int numFramesRead = 0;
                FourierTransform ft = new FourierTransform();
                // Try to read numBytes bytes from the file.
                while ((numBytesRead = audioInputStream.read(audioBytes)) != -1 && chunkNum < chunksAllowed) {
                    numFramesRead = numBytesRead / song.getBytesPerFrame();
                    totalFramesRead += numFramesRead;

                    for (int i = 0; i < songChunkSize; i++) {

                        if (chunkNum < chunksAllowed) {
                            allSong[(chunkNum * songChunkSize) + i] = audioBytes[i];
                        }
                    }
                    chunkNum++;

                }

                int arrayLoc = 45; // start array after header
                Complex[] temp = new Complex[songChunkSize/2];
                double[] hannArray = new double[songChunkSize/2];
                int fftNumChunk = 0;
                while ((arrayLoc + songChunkSize) < (chunkNum * songChunkSize)) {
					int pointer = 0;
                    for (int i = 0; i < songChunkSize; i=i+2) {
                        double multiplier = 0.5 * (1 - Math.cos(2 * Math.PI * pointer / songChunkSize));
                        hannArray[pointer] = multiplier * allSong[arrayLoc + i];
                        temp[pointer] = new Complex(hannArray[pointer], 0);
						pointer++;
                    }
                    ft.setData(temp);
                    ft.transform();
                    temp = ft.getTransformedDataAsComplex();
                    filterFreqBands(temp,fftNumChunk);
                    arrayLoc = arrayLoc + (songChunkSize / 8);
                    fftNumChunk++;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Split the FFT analysis into 4 filter bands. Take find the max value in each band and store the time
    // The Hash value is computed from the max values.
    public void filterFreqBands(Complex[] transformedArray, int chunkNum) {
        int point1 = 0;
        int point2 = 0;
        int point3 = 0;
        int point4 = 0;

        for (int j = 25; j <= 256; j++) {
            int freq = (int) transformedArray[j].abs();
            if (j < 42) {
                if (transformedArray[point1].abs() < freq || point1 == 0) {
                    point1 = j;
                }
            } else if (j < 74) {
                if (transformedArray[point2].abs() < freq || point2 == 0) {
                    point2 = j;
                }
            } else if (j < 137) {
                if (transformedArray[point3].abs() < freq || point3 == 0) {
                    point3 = j;
                }
            } else {
                if (transformedArray[point4].abs() < freq || point4 == 0) {
                    point4 = j;
                }
            }
        }

        int[] points = new int[4];
		points[0] = point1;
		points[1] = point2;
		points[2] = point3;
		points[3] = point4;
 
		this.getFingerprint().add(points);
    }

}
