package com.ray3k.template.entities;

import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.SkeletonData;
import com.ray3k.template.*;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateCircleFixtureBehaviour;

public class BehaviorPlayer extends BehaviorEntity {
    public BehaviorPlayer(GameObject gameObject, SkeletonData skeletonData,
                          AnimationStateData animationStateData, float startX, float startY) {
        super(gameObject, skeletonData, animationStateData, startX, startY);
    }
    
    @Override
    public void start() {
        super.start();
        new CreateCircleFixtureBehaviour(Core.p2m(10), gameObject);
    }
}
