package com.ray3k.template.entities;

import com.esotericsoftware.spine.*;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class EntityData extends BehaviourAdapter {
    public Skeleton skeleton;
    public SkeletonBounds skeletonBounds;
    public AnimationState animationState;
    public float startX;
    public float startY;
    
    public EntityData(GameObject gameObject, SkeletonData skeletonData, AnimationStateData animationStateData, float startX,
                      float startY) {
        super(gameObject);
        this.skeleton = new Skeleton(skeletonData);
        skeletonBounds = new SkeletonBounds();
        animationState = new AnimationState(animationStateData);
        this.startX = startX;
        this.startY = startY;
    }
}