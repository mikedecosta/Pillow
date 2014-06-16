package readtest;

import java.io.*;
import java.util.regex.*;

/*
 * InputChecker
 *
 * This class makes sure our two inputs are in usable forms
 *
 */

public class InputChecker {

    public static File[] checkSongs(String[] args) throws Exception {
        int numInputs = args.length;

        // check wav and mp3 patterns and set bools if decoding is needed
        if(numInputs == 2) {

            File first = new File("working/first.wav");
            File second = new File("working/second.wav");
			if(!first.exists() || !second.exists()){
				throw new Exception("error: One or more of the working input files does not exist.");
			}

            // make sure the first song is the longer of the two otherwise, make it so
            if(first.length() < second.length()){
            	File temp = first;
            	first = second;
            	second = temp;
            }

		// Can't take files larger than 59 megs (approx)
            if(first.length() > 59000000 || second.length() > 59000000){
				throw new Exception("error: One or more of the input files is too large for this prototype to handle.");
			}

			File[] files = new File[2];
            files[0] = first;
            files[1] = second;
            return files;

        } else {
            throw new Exception("error: Must have two inputs.");
        }
    }

}
