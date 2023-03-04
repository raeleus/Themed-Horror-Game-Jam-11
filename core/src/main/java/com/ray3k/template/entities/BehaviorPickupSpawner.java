package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import dev.lyze.gdxUnBox2d.BodyDefType;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.screens.GameScreen.*;

public class BehaviorPickupSpawner extends BehaviourAdapter {
    private GameObject go;
    private float timer;
    
    public BehaviorPickupSpawner(GameObject gameObject) {
        super(gameObject);
        this.go = gameObject;
    }
    
    @Override
    public void start() {
    
    }
    
    @Override
    public void update(float delta) {
        timer -= delta;
        if (timer < 0 && player != null) {
            timer = MathUtils.random(1f, 5f);
    
            var pickup = new GameObject(BodyDefType.DynamicBody, unBox);
            var pickupBehavior = new BehaviorPickup(pickup);
            
            pickupBehavior.startX = MathUtils.random(36, 992);
            pickupBehavior.startY = MathUtils.random(18, 487);
        }
    }
}
