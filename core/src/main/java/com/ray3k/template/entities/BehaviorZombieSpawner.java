package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.ray3k.template.screens.*;
import dev.lyze.gdxUnBox2d.BodyDefType;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.behaviours.SoutBehaviour;

import static com.ray3k.template.Resources.SpineStageFloor.*;
import static com.ray3k.template.screens.GameScreen.*;

public class BehaviorZombieSpawner extends BehaviourAdapter {
    private GameObject go;
    private float delay = 1;
    private float timer;
    
    public BehaviorZombieSpawner(GameObject gameObject) {
        super(gameObject);
        this.go = gameObject;
    }
    
    @Override
    public void start() {
    
    }

    @Override
    public void update(float delta) {
        timer -= delta;
        if (timer < 0 && player != null) {
            timer = delay;
    
            var enemy = new GameObject(unBox);
            new Box2dBehaviour(BodyDefType.DynamicBody, enemy);
            var zombie = new BehaviorZombie(enemy);

            var location = MathUtils.random(3);
            switch (location) {
                case 0:
                    zombie.startX = 50;
                    zombie.startY = 288;
                    break;
                case 1:
                    zombie.startX = 1010;
                    zombie.startY = 288;
                    break;
                case 2:
                    zombie.startX = 535;
                    zombie.startY = 530;
                    break;
                case 3:
                    zombie.startX = 540;
                    zombie.startY = 16;
                    break;
            }
        }
    }
}
