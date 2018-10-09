package base;

import base.physics.Phycics;
import base.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    public static ArrayList<GameObject> newGameObject = new ArrayList<>();
    public static <E extends GameObject> E create(Class<E> childClass){
        try
        {
            GameObject newgameObject = childClass.newInstance();
            newGameObject.add(newgameObject);
            return (E) newgameObject;
        }catch (Exception e) {
            return null;
        }
    }
    public static <E extends GameObject> E recycle(Class<E> childClass){
        //1.có GameObject thỏa mãn yêu cầu ko?
        //(go.isActive == false && go instance of childClass) ko?
        // có thì dừng lại.
        // ko thì tạo mới
        //2. return GameObject
        for (GameObject go: gameObjects){
            if (!go.isActive && go.getClass().isAssignableFrom(childClass)){
                go.isActive = true;
                return (E) go;
            }
        }
        return create(childClass);
    }
    public static <E extends GameObject> E intersect(Class<E> childClass, Phycics phycics){
        for (GameObject go : gameObjects){
            if(go.isActive && go.getClass().isAssignableFrom(childClass)
            && go instanceof Phycics){
                Phycics phycicsGo = (Phycics) go;
                boolean intersected = phycics.getBoxColldier().intersect(phycicsGo, (GameObject) phycics);
                if(intersected){
                    return (E) phycicsGo;
                }
            }
        }
        return null;
    }
    public static void  runALl(){
        for( int i =0; i <gameObjects.size(); i++){
            GameObject go =gameObjects.get(i);
       // for (GameObject go : gameObjects){
            if(go.isActive) {
                go.run();
            }
        }
    }
    public static void renderAll(Graphics g){
        for( int i =0; i <gameObjects.size(); i++){
            GameObject go =gameObjects.get(i);
            if(go.isActive) {
                go.render(g);
            }
        }
        gameObjects.addAll(newGameObject);
        newGameObject.clear();
    }
    public Renderer renderer;
    public Vector2D position;
    public boolean isActive;

    public GameObject() {
        this.isActive = true;
    }

    public GameObject(BufferedImage image) {
        this.isActive = true;
        this.position = new Vector2D(0, 0);
    }
    public void destroy (){
        this.isActive = false;
    }

    public void run() {

    }

    public void render(Graphics g) {
        if(this.renderer != null) {
            this.renderer.render(g, this);
        }
    }
}
