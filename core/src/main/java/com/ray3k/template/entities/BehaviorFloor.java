package com.ray3k.template.entities;

import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineStageFloor.*;

public class BehaviorFloor extends BehaviourAdapter {
    private GameObject go;
    
    public BehaviorFloor(GameObject gameObject) {
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
