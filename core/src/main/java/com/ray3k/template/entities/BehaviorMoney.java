package com.ray3k.template.entities;

import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import dev.lyze.gdxUnBox2d.BehaviourState;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineMoney.*;
import static com.ray3k.template.Resources.*;

public class BehaviorMoney extends BehaviourAdapter {
    private GameObject go;
    
    public BehaviorMoney(GameObject gameObject) {
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
    
        sfx_money.play(sfx);
    }
}
