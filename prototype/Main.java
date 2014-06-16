package readtest;

import java.io.*;

/*
 * Main class
 *
 * Check the input songs for valid forms,
 * Create fingerprints of both files,
 * Compare the fingerprints to each other,
 * cleanup after outselves.
 */

public class Main {
    public static void main(String[] args) {
        File[] files = new File[2];
        try {
            files = InputChecker.checkSongs(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
	    return;
        }
        FingerPrint longFP = new FingerPrint();
        FingerPrint shortFP = new FingerPrint();

        longFP.fingerPrint(files[0], true);
        shortFP.fingerPrint(files[1], false);
        Util.compareFingers(longFP, shortFP);

		Util.deleteWorkingFiles();
    }
}
