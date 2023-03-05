package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ray3k.template.*;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.Box2dBehaviour;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.screens.GameScreen.*;

public class BehaviorZombieFastMovement extends BehaviourAdapter {
    private GameObject gameObject;
    private static final Vector2 temp = new Vector2();
    public float speed;
    private float maxSpeed;
    
    public BehaviorZombieFastMovement(GameObject gameObject, float speed) {
        super(gameObject);
        this.gameObject = gameObject;
        this.maxSpeed = speed;
    }
    
    @Override
    public void update(float delta) {
        var body = gameObject.getBehaviour(Box2dBehaviour.class).getBody();
        
        //movement
        if (player != null) {
            var x = m2p(body.getPosition().x);
            var y = m2p(body.getPosition().y);
            var playerX = m2p(player.getBehaviour(Box2dBehaviour.class).getBody().getPosition().x);
            var playerY = m2p(player.getBehaviour(Box2dBehaviour.class).getBody().getPosition().y);
            var angle = Utils.pointDirection(x, y, playerX, playerY);
            speed = Utils.approach(speed, maxSpeed, 100 * delta);
            temp.set(speed, 0);
            temp.rotateDeg(angle);
            body.setLinearVelocity(p2m(temp.x), p2m(temp.y));
            gameObject.getBehaviour(Box2dBehaviour.class).getBody().setTransform(body.getPosition(), MathUtils.degRad * angle);
        } else {
            gameObject.getBehaviour(Box2dBehaviour.class).getBody().setLinearVelocity(p2m(0), p2m(0));
        }
    }
}
