package base.player;
import base.*;
import base.counter.FrameCounter;
import base.event.KeyEventPress;
import base.physics.BoxCollider;
import base.physics.Phycics;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject implements Phycics {
    FrameCounter fireCounter;
    BoxCollider collider;
    int hp = 100;
    public Player() {
        super();
       ArrayList<BufferedImage> images = SpriteUtils.LoadImages(
               "assets/images/players/straight/0.png",
               "assets/images/players/straight/1.png",
               "assets/images/players/straight/2.png",
               "assets/images/players/straight/3.png",
               "assets/images/players/straight/4.png",
               "assets/images/players/straight/5.png",
               "assets/images/players/straight/6.png"
       );
        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(Settings.START_PLAYER_POSITION_X
                , Settings.START_PLAYER_POSITION_Y);
        this.fireCounter = new FrameCounter(10);
        this.collider = new BoxCollider(32,48);
    }

    @Override
    public void run() {
        if(KeyEventPress.isUpPress) {
            this.move(0, -1);
        }
        if(KeyEventPress.isDownPress) {
            this.move(0, 1);
        }
        if(KeyEventPress.isRightPress) {
            this.move(1, 0);
        }
        if(KeyEventPress.isLeftPress) {
            this.move(-1, 0);
        }
        //fire
        boolean fireCounterRun = this.fireCounter.run();
        if(KeyEventPress.isSpace && fireCounterRun)
        {
            this.fire();
        }
    }
    public void fire () {
      //  PlayerBullet bullet = GameObject.create(PlayerBullet.class);
        PlayerBullet left = GameObject.recycle(PlayerBullet.class);
        PlayerBullet right = GameObject.recycle(PlayerBullet.class);
        PlayerBullet straight = GameObject.recycle(PlayerBullet.class);
        left.velovity.set(-3,-3);
        right.velovity.set(3,-3);
        straight.velovity.set(0,-3);
       // bullet.position.set(this.position.x,this.position.y);
        left.position.set(this.position.x,this.position.y);
        right.position.set(this.position.x,this.position.y);
        straight.position.set(this.position.x,this.position.y);
            this.fireCounter.reset();
        }

    public void move(int translateX, int translateY) {
        this.position.addThis(translateX, translateY);
    }
    public void takeDamage ( int damage ){
        this.hp -= damage;
        if(this.hp <=0) {
            this.destroy();
            hp = 0;
        }
    }

    @Override
    public BoxCollider getBoxColldier() {
        return this.collider;
    }
}
