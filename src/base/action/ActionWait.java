package base.action;

import base.GameObject;
import base.counter.FrameCounter;

import java.io.FileReader;

public class ActionWait extends Action {
    FrameCounter counter;
    public ActionWait( int frame){
        this.counter = new FrameCounter(frame);
        this.isDone = false;
    }
    @Override
    public void run(GameObject master) {
        if (this.counter.run()){
            this.isDone = true;
        }
    }

    @Override
    public void reset() {
        this.counter.reset();
        this.isDone = false;
    }
}
