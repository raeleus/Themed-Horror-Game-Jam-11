package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;
import dev.lyze.gdxUnBox2d.Box2DGameObject;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.screens.GameScreen.*;

public class BehaviorZombieMovement extends BehaviourAdapter<Box2DGameObject>{
    private Box2DGameObject gameObject;
    private static final Vector2 temp = new Vector2();
    private float speed;
    
    public BehaviorZombieMovement(Box2DGameObject gameObject, float speed) {
        super(gameObject);
        this.gameObject = gameObject;
        this.speed = speed;
    }
    
    @Override
    public void update(float delta) {
        var body = gameObject.getBody();
        
        //movement
        if (player != null) {
            var x = m2p(body.getPosition().x);
            var y = m2p(body.getPosition().y);
            var playerX = m2p(player.getBody().getPosition().x);
            var playerY = m2p(player.getBody().getPosition().y);
            var angle = Utils.pointDirection(x, y, playerX, playerY);
            temp.set(speed, 0);
            temp.rotateDeg(angle);
            body.setLinearVelocity(p2m(temp.x), p2m(temp.y));
            gameObject.getBody().setTransform(body.getPosition(), MathUtils.degRad * angle);
        } else {
            gameObject.getBody().setLinearVelocity(p2m(0), p2m(0));
        }
    }
}
