package base.enemy;

import base.*;
import base.action.Action;
import base.action.ActionRepeat;
import base.action.ActionSequence;
import base.action.ActionWait;
import base.physics.BoxCollider;
import base.physics.Phycics;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends GameObject implements Phycics {
    BoxCollider collider;
    Action actionF;
    Action actionM;
    public Enemy() {
        super();
        ArrayList<BufferedImage> images = SpriteUtils.LoadImages(
           "assets/images/enemies/level0/pink/0.png",
                "assets/images/enemies/level0/pink/1.png",
                "assets/images/enemies/level0/pink/2.png",
                "assets/images/enemies/level0/pink/3.png"
        );
        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(Settings.START_ENEMY_POSITION_X , Settings.START_ENEMY_POSITION_Y);
        this.collider = new BoxCollider(28,28);
        this.defineAction();
        this.actionMove();
    }
    void defineAction(){
        ActionWait actionWait = new ActionWait(20);
        Action actionFire = new Action() {
            @Override
            public void run(GameObject master) {
                fire();
                this.isDone = true;
            }
            @Override
            public void reset() {
                this.isDone = false;
            }
        };
        ActionSequence actionSequence = new ActionSequence(actionWait,actionFire);
        ActionRepeat actionRepeat = new ActionRepeat(actionSequence);
        this.actionF = actionRepeat;
    }
    void actionMove(){
        ActionWait actionWait = new ActionWait(1);
        Action actionMove = new Action() {
            @Override
            public void run(GameObject master) {
                move();
                this.isDone = true;
            }
            @Override
            public void reset() {
                this.isDone = false;
            }
        };
        ActionSequence actionSequence = new ActionSequence(actionWait,actionMove);
        ActionRepeat actionRepeat = new ActionRepeat(actionSequence);
        this.actionM = actionRepeat;
    }
    @Override
    public void run() {
        this.actionF.run(this);
        this.actionM.run(this);
    }
    public void move () {
        this.position.addThis(1,0);
    }
    public void fire() {
        EnemyBullet Straight = GameObject.recycle(EnemyBullet.class);
        EnemyBullet Left = GameObject.recycle(EnemyBullet.class);
        Left.velocity.set(-3,3);
        Straight.velocity.set(0,3);
        Left.position.set(this.position.x, this.position.y);
        Straight.position.set(this.position.x,this.position.y);
    }

    @Override
    public BoxCollider getBoxColldier() {
        return this.collider;
    }
}
