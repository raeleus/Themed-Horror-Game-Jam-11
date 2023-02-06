package com.ray3k.template.entities;

import com.ray3k.template.screens.*;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.Core.*;

public class BehaviorRestarter extends BehaviourAdapter {
    public float timer = 2;
    public BehaviorRestarter(GameObject gameObject) {
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
