package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import dev.lyze.gdxUnBox2d.BehaviourState;
import dev.lyze.gdxUnBox2d.Box2DGameObject;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineExplosion.*;

public class BehaviorExplosion extends BehaviourAdapter<Box2DGameObject>{
    private Box2DGameObject go;
    private EntityData ed;
    public float startX;
    public float startY;
    public BehaviorExplosion(Box2DGameObject gameObject) {
        super(gameObject);
    }
    
    @Override
    public void start() {
        go = getGameObject();
        ed = new EntityData(go, skeletonData, animationData, startX, startY, DEPTH_EFFECTS);
        ed.skeleton.getRootBone().setRotation(MathUtils.random(360f));
        ed.animationState.setAnimation(0, animationAnimation, false);
        ed.animationState.addListener(new AnimationStateAdapter() {
            @Override
            public void complete(TrackEntry entry) {
                boolean destroyed = getState() == BehaviourState.DESTROYED || getState() == BehaviourState.DESTROYING;
                if (!destroyed) go.destroy();
            }
        });
        
        new BehaviorEntity(go);
    }
    
    @Override
    public void update(float delta) {
    }
    
    @Override
    public void onDestroy() {
    }
}
