package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateCircleFixtureBehaviour;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineZombie.*;

public class BehaviorZombie extends BehaviourAdapter {
    private GameObject go;
    private EntityData ed;
    public float startX;
    public float startY;
    public BehaviorZombie(GameObject gameObject) {
        super(gameObject);
    }
    
    @Override
    public void start() {
        go = getGameObject();
        ed = new EntityData(go, skeletonData, animationData, startX, startY, DEPTH_ENEMIES);
        ed.skeleton.setSkin(skinZombie);
        ed.animationState.setAnimation(0, animationSpawn, false);
        ed.health = 20;
        ed.score = 10;
        
        var def = new FixtureDef();
        def.filter.categoryBits = CATEGORY_CHARACTER;
        new CreateCircleFixtureBehaviour(Vector2.Zero, p2m(10), def, go);
        
        new BehaviorEntity(go);
        new BehaviorZombieMovement(go, 100);
        new BehaviorEnemy(go);
    }
    
    @Override
    public void update(float delta) {
        var body = go.getBehaviour(Box2dBehaviour.class).getBody();
        ed.skeleton.getRootBone().setRotation(body.getAngle() * MathUtils.radDeg);
    }
    
    @Override
    public void onDestroy() {
    }
}
