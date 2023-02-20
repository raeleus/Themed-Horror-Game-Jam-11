package com.ray3k.template.entities;

import com.esotericsoftware.spine.*;
import dev.lyze.gdxUnBox2d.Box2DGameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class EntityData extends BehaviourAdapter<Box2DGameObject>{
    public Skeleton skeleton;
    public SkeletonBounds skeletonBounds;
    public AnimationState animationState;
    public float startX;
    public float startY;
    public float health = 100f;
    public int score = 0;
    public int depth = 0;
    
    public EntityData(Box2DGameObject gameObject, SkeletonData skeletonData, AnimationStateData animationStateData, float startX,
                      float startY, int depth) {
        super(gameObject);
        this.skeleton = new Skeleton(skeletonData);
        skeletonBounds = new SkeletonBounds();
        animationState = new AnimationState(animationStateData);
        this.startX = startX;
        this.startY = startY;
        this.depth = depth;
    }
}
