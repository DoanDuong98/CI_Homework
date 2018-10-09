package base.player;

import base.physics.BoxCollider;
import base.GameObject;
import base.physics.Phycics;
import base.Vector2D;
import base.enemy.Enemy;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerBullet extends GameObject implements Phycics {
    public Vector2D velovity;
    BoxCollider collider;
    public PlayerBullet(){
        super();
         ArrayList<BufferedImage> images = SpriteUtils.LoadImages(
                "assets/images/player-bullets/a/0.png",
                 "assets/images/player-bullets/a/1.png",
                 "assets/images/player-bullets/a/2.png",
                 "assets/images/player-bullets/a/3.png"
        );
        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(0,0);
        this.velovity = new Vector2D(0,0);
        this.collider = new BoxCollider(24,24);
    }

    @Override
    public void run() {
        Enemy enemy = GameObject.intersect(Enemy.class, this);
        if(enemy != null){
            enemy.destroy();
            this.destroy();
            return;
        }
        if (this.position.y< 0 && this.position.x > 100)
        {
            this.destroy();
            return;
        }
            this.position.addThis(velovity.x,velovity.y);
    }

    @Override
    public BoxCollider getBoxColldier() {
        return this.collider;
    }
}
