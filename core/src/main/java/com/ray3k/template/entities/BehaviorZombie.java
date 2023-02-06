package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateCircleFixtureBehaviour;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineZombie.*;

public class BehaviorZombie extends BehaviourAdapter {
    private GameObject go;
    private EntityData ed;
    public BehaviorZombie(GameObject gameObject) {
        super(gameObject);
    }
    
    @Override
    public void start() {
        go = getGameObject();
        ed = new EntityData(go, skeletonData, animationData, 50, 288);
        ed.skeleton.setSkin(skinZombie);
        new CreateCircleFixtureBehaviour(p2m(10), go);
        new BehaviorEntity(go);
        new BehaviorZombieMovement(go, 100);
        new BehaviorEnemy(go);
    }
    
    @Override
    public void update(float delta) {
        var body = go.getBody();
        ed.skeleton.getRootBone().setRotation(body.getAngle() * MathUtils.radDeg);
    }
    
    @Override
    public void onDestroy() {
    }
}
