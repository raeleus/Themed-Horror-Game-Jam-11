package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ray3k.template.*;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.Core.*;

public class BehaviorKeyboardShooting extends BehaviourAdapter {
    private GameObject gameObject;
    private static final Vector2 temp = new Vector2();
    
    public BehaviorKeyboardShooting(GameObject gameObject) {
        super(gameObject);
        this.gameObject = gameObject;
    }
    
    @Override
    public void update(float delta) {
        //aiming angle
        var shoot = Core.isAnyBindingPressed(Binding.SHOOT_LEFT, Binding.SHOOT_RIGHT, Binding.SHOOT_UP, Binding.SHOOT_DOWN);
        var angle = 0f;
        if (Core.isBindingPressed(Binding.SHOOT_LEFT)) {
            angle = 180;
            if (Core.isBindingPressed(Binding.SHOOT_UP)) {
                angle = 135;
            } else if (Core.isBindingPressed(Binding.SHOOT_DOWN)) {
                angle = 225;
            }
        } else if (Core.isBindingPressed(Binding.SHOOT_RIGHT)) {
            angle = 0;
            if (Core.isBindingPressed(Binding.SHOOT_UP)) {
                angle = 45;
            } else if (Core.isBindingPressed(Binding.SHOOT_DOWN)) {
                angle = 315;
            }
        } else if (Core.isBindingPressed(Binding.SHOOT_UP)) {
            angle = 90;
        } else if (Core.isBindingPressed(Binding.SHOOT_DOWN)) {
            angle = 270;
        }
    
        if (shoot) {
            gameObject.getBody().setTransform(gameObject.getBody().getPosition(), MathUtils.degRad * angle);
        }
    }
}
