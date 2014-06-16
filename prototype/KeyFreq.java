package readtest;

import flanagan.complex.Complex;

public class KeyFreq {
    private Complex frequency;
    private float time;

    public KeyFreq(Complex frequency, float time){
        this.frequency = frequency;
        this.time = time;
    }

    public Complex getFrequency() {
       return frequency;
    }

    public float getTime() {
       return time;
    }

    public void setFrequency(Complex frequency) {
       this.frequency = frequency;
    }

    public void setTime(float time) {
       this.time = time;
    }
}
