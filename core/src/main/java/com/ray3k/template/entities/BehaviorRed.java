package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import dev.lyze.gdxUnBox2d.BehaviourState;
import dev.lyze.gdxUnBox2d.Box2DGameObject;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.Resources.SpineRed.*;

public class BehaviorRed extends BehaviourAdapter<Box2DGameObject>{
    private Box2DGameObject go;
    
    public BehaviorRed(Box2DGameObject gameObject) {
        super(gameObject);
        this.go = gameObject;
    }
    
    @Override
    public void start() {
        var ed = new EntityData(go, skeletonData, animationData, 0, 0, DEPTH_SCREEN_ANIMATIONS);
        new BehaviorEntity(go);
        ed.skeleton.getRootBone().setScale(4);
        ed.animationState.setAnimation(0, animationAnimation, false);
        ed.animationState.addListener(new AnimationStateAdapter() {
            @Override
            public void complete(TrackEntry entry) {
                boolean destroyed = getState() == BehaviourState.DESTROYED || getState() == BehaviourState.DESTROYING;
                if (!destroyed) go.destroy();
            }
        });
        switch (MathUtils.random(1)) {
            case 0:
                sfx_buyThatForADollar.play(sfx);
                break;
            case 1:
                sfx_bigMoney.play(sfx);
                break;
        }
    }
}
