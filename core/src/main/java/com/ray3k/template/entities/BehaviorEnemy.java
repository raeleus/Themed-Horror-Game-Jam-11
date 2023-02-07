package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class BehaviorEnemy extends BehaviourAdapter {
    public BehaviorEnemy(GameObject gameObject) {
        super(gameObject);
    }
    
    @Override
    public void onDestroy() {
        switch (MathUtils.random(2)) {
            case 0:
                sfx_enemyHurt01.play(sfx * .2f);
                break;
            case 1:
                sfx_enemyHurt02.play(sfx * .2f);
                break;
            case 2:
                sfx_enemyHurt03.play(sfx * .2f);
                break;
        }
    }
}
