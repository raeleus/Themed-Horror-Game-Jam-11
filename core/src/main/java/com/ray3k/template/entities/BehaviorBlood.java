package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.ray3k.template.*;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineBlood.*;

public class BehaviorBlood extends BehaviourAdapter {
    private GameObject go;
    private EntityData ed;
    public float startX;
    public float startY;
    public BehaviorBlood(GameObject gameObject) {
        super(gameObject);
    }
    
    @Override
    public void start() {
        go = getGameObject();
        ed = new EntityData(go, skeletonData, animationData, startX, startY, DEPTH_EFFECTS);
        ed.skeleton.getRootBone().setRotation(MathUtils.random(360f));
        switch (MathUtils.random(3)) {
            case 0:
                ed.skeleton.setSkin(skinBlood01);
                break;
            case 1:
                ed.skeleton.setSkin(skinBlood02);
                break;
            case 2:
                ed.skeleton.setSkin(skinBlood03);
                break;
            case 3:
                ed.skeleton.setSkin(skinBlood04);
                break;
        }
        ed.animationState.setAnimation(0, animationAnimation, false);
        ed.animationState.addListener(new AnimationStateAdapter() {
            @Override
            public void end(TrackEntry entry) {
                destroy();
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
