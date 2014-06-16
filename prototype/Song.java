package readtest;

import javax.sound.sampled.AudioInputStream;

/*
 * Song class
 *
 * Functions to handle song objects
 *
 */
public class Song {
    private int bytesPerFrame; //= audioInputStream.getFormat().getFrameSize();
    private long fileSize; //= audioInputStream.getFrameLength() * bytesPerFrame;
    private int chunkSize; //= 1024
    private int numChunks;//= (int) fileSize / numBytes


    public Song(AudioInputStream ais) {
    	this.bytesPerFrame = ais.getFormat().getFrameSize();
    	this.fileSize = ais.getFrameLength() * this.bytesPerFrame;
    	this.chunkSize = (int)(1024);
    	this.numChunks = (int)(this.fileSize) / this.chunkSize;
    }

    public Song() {}

    public void setBytesPerFrame(int bytesPerFrame) {
        this.bytesPerFrame = bytesPerFrame;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public void setNumChunks(int numChunks) {
        this.numChunks = numChunks;
    }

    public int getBytesPerFrame() {
        return bytesPerFrame;
    }

    public long getFileSize() {
        return fileSize;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public int getNumChunks() {
        return numChunks;
    }

    public String toString() {
    	String ret = 	"filesize="+ this.fileSize + "\n" +
    					"bytesPerFrame=" + this.bytesPerFrame + "\n" +
    					"numbBytes=" + this.chunkSize + "\n" +
    					"numChunks=" + this.numChunks;
    	return ret;
    }

}
