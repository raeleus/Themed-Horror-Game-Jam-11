package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import dev.lyze.gdxUnBox2d.Box2DGameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.screens.GameScreen.*;
import static dev.lyze.gdxUnBox2d.box2D.BodyDefType.DynamicBody;

public class BehaviorPickupSpawner extends BehaviourAdapter<Box2DGameObject>{
    private Box2DGameObject go;
    private float timer;
    
    public BehaviorPickupSpawner(Box2DGameObject gameObject) {
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
    
            var pickup = new Box2DGameObject(DynamicBody, unBox);
            var pickupBehavior = new BehaviorPickup(pickup);
            
            pickupBehavior.startX = MathUtils.random(36, 992);
            pickupBehavior.startY = MathUtils.random(18, 487);
        }
    }
}
