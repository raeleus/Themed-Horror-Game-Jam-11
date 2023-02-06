package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ray3k.template.*;
import com.ray3k.template.Resources.*;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.screens.GameScreen.*;

public class BehaviorKeyboardShooting extends BehaviourAdapter {
    private GameObject gameObject;
    private static final Vector2 temp = new Vector2();
    private static float delay = .02f;
    private static float timer;
    private static float angleRange = 5f;
    
    public BehaviorKeyboardShooting(GameObject gameObject) {
        super(gameObject);
        this.gameObject = gameObject;
    }
    
    @Override
    public void update(float delta) {
        timer -= delta;
        
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
            if (timer < 0) {
                timer = delay;
                var bullet = new GameObject(unBox);
                var bulletBehavior = new BehaviorBullet(bullet, SpineBullet.skinWhite, m2p(player.getBody().getPosition().x), m2p(player.getBody().getPosition().y), angle - angleRange / 2 + MathUtils.random(angleRange), 800f);
                bulletBehavior.addDeltaX = m2p(player.getBody().getLinearVelocity().x);
                bulletBehavior.addDeltaY = m2p(player.getBody().getLinearVelocity().y);
                bulletBehavior.owner = BehaviorPlayer.class;
            }
        }
    }
}
