package com.ray3k.template.entities;

import com.ray3k.template.screens.*;
import dev.lyze.gdxUnBox2d.Box2DGameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.Core.*;

public class BehaviorRestarter extends BehaviourAdapter<Box2DGameObject>{
    public float timer = 2;
    public BehaviorRestarter(Box2DGameObject gameObject) {
        super(gameObject);
    }
    
    
    @Override
    public void update(float delta) {
        if (timer > 0) {
            timer -= delta;
            if (timer <= 0) {
                core.transition(new GameScreen());
            }
        }
    }
}
