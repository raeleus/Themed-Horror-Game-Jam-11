package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ray3k.template.*;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.Box2dBehaviour;

import static com.ray3k.template.Core.*;

public class BehaviorKeyboardMovement extends BehaviourAdapter {
    private GameObject gameObject;
    private static final Vector2 temp = new Vector2();
    private float speed;
    
    public BehaviorKeyboardMovement(GameObject gameObject, float speed) {
        super(gameObject);
        this.gameObject = gameObject;
        this.speed = speed;
    }
    
    @Override
    public void update(float delta) {
        //movement
        var move = Core.isAnyBindingPressed(Binding.LEFT, Binding.RIGHT, Binding.UP, Binding.DOWN);
        var angle = 0f;
        
        if (Core.isBindingPressed(Binding.LEFT)) {
            angle = 180;
            if (Core.isBindingPressed(Binding.UP)) {
                angle = 135;
            } else if (Core.isBindingPressed(Binding.DOWN)) {
                angle = 225;
            }
        } else if (Core.isBindingPressed(Binding.RIGHT)) {
            angle = 0;
            if (Core.isBindingPressed(Binding.UP)) {
                angle = 45;
            } else if (Core.isBindingPressed(Binding.DOWN)) {
                angle = 315;
            }
        } else if (Core.isBindingPressed(Binding.UP)) {
            angle = 90;
        } else if (Core.isBindingPressed(Binding.DOWN)) {
            angle = 270;
        }
        
        if (move) {
            temp.set(speed, 0);
            temp.rotateDeg(angle);
            gameObject.getBehaviour(Box2dBehaviour.class).getBody().setLinearVelocity(p2m(temp.x), p2m(temp.y));
        } else {
            gameObject.getBehaviour(Box2dBehaviour.class).getBody().setLinearVelocity(p2m(0), p2m(0));
        }
    }
}
