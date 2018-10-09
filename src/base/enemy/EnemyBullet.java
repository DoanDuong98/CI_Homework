package base.enemy;

import base.*;
import base.physics.BoxCollider;
import base.physics.Phycics;
import base.player.Player;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyBullet extends GameObject implements Phycics {
    Vector2D velocity;
    BoxCollider collider;
    int damage;
    public EnemyBullet (){
        super();
        ArrayList<BufferedImage> images = SpriteUtils.LoadImages("assets/images/enemies/bullets/blue.png" ,
                "assets/images/enemies/bullets/cyan.png" ,
                "assets/images/enemies/bullets/green.png" ,
                "assets/images/enemies/bullets/pink.png" ,
                "assets/images/enemies/bullets/red.png",
                "assets/images/enemies/bullets/white.png",
                "assets/images/enemies/bullets/yellow.png");
        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(0,0);
        this.velocity = new Vector2D(0,0);
        this.collider = new BoxCollider(16,16);
        this.damage = 10;
    }

    @Override
    public void run() {
        Player player = GameObject.intersect(Player.class, this);
            if (player != null) {
                player.takeDamage( this.damage);
                this.destroy();
            }
        if(this.position.y > Settings.SCREEN_HEIGHT){
            this.destroy();
            return;
        }
        this.position.addThis(velocity.x,velocity.y);
    }
    @Override
    public BoxCollider getBoxColldier() {
        return this.collider;
    }
}
