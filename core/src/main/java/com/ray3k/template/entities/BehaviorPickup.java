package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import dev.lyze.gdxUnBox2d.Box2DGameObject;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateCircleFixtureBehaviour;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineDollar.*;

public class BehaviorPickup extends BehaviourAdapter<Box2DGameObject>{
    private Box2DGameObject go;
    private EntityData ed;
    public float startX;
    public float startY;
    public BehaviorPickup(Box2DGameObject gameObject) {
        super(gameObject);
    }
    
    @Override
    public void start() {
        go = getGameObject();
        ed = new EntityData(go, skeletonData, animationData, startX, startY, DEPTH_PICKUPS);
        switch (MathUtils.random(2)) {
            case 0:
                ed.skeleton.setSkin(skinDollar);
                break;
            case 1:
                ed.skeleton.setSkin(skinGem);
                break;
            case 2:
                ed.skeleton.setSkin(skinRuby);
                break;
        }
        ed.animationState.setAnimation(0, animationAnimation, false);
        ed.health = 20;
        ed.score = 50;
        
        var def = new FixtureDef();
        def.filter.categoryBits = CATEGORY_PICKUP;
        def.isSensor = true;
        new CreateCircleFixtureBehaviour(Vector2.Zero, p2m(10), def, go);
        
        new BehaviorEntity(go);
    }
    
    @Override
    public void update(float delta) {
    }
    
    @Override
    public void onDestroy() {
    }
}
