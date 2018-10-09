package base.action;

import base.GameObject;

import java.util.ArrayList;
import java.util.Arrays;

public class ActionParallel extends Action {
    ArrayList<Action> actions;
    public ActionParallel (Action... actions){
        this.actions = new ArrayList<>(Arrays.asList(actions));
    }
    @Override
    public void run(GameObject master) {
        if (this.actions.size() > 0 && !this.isDone ){
            boolean iscontinue = true;
            for (Action action : actions){
                if(!action.isDone) {
                    action.run(master);
                }
                else {
                   iscontinue = false;
                }
            }
            this.isDone = !iscontinue;
        }
    }

    @Override
    public void reset() {
        for (Action action: this.actions){
            action.reset();
        }
        this.isDone = false;
    }
}
