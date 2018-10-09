package base.counter;

public class FrameCounter {
    int framecounter;
    int maxCounter;
    public FrameCounter(int maxCounter){
        this.maxCounter = maxCounter;
        this.framecounter =0;
    }
    public boolean run (){
        if(framecounter >= maxCounter){
            return true;
        }
        else {
            framecounter++;
            return false;
        }
    }
    public void reset(){
        framecounter = 0;
    }
}
