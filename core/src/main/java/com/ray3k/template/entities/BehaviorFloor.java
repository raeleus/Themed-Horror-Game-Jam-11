package com.ray3k.template.entities;

import dev.lyze.gdxUnBox2d.Box2DGameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineStageFloor.*;

public class BehaviorFloor extends BehaviourAdapter<Box2DGameObject>{
    private Box2DGameObject go;
    
    public BehaviorFloor(Box2DGameObject gameObject) {
        super(gameObject);
        this.go = gameObject;
    }
    
    @Override
    public void start() {
        var ed = new EntityData(go, skeletonData, animationData, 0, 0, DEPTH_FLOOR);
        new BehaviorEntity(go);
        ed.skeleton.getRootBone().setScale(4);
    }
}
