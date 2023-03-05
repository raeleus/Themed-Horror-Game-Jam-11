package com.ray3k.template.entities;

import dev.lyze.gdxUnBox2d.BehaviourState;
import dev.lyze.gdxUnBox2d.BodyDefType;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.Box2dBehaviour;

import static com.ray3k.template.screens.GameScreen.*;

public class BehaviorSpawnerSpawner extends BehaviourAdapter {
    private GameObject go;
    private float timeline;
    private int event;
    
    public BehaviorSpawnerSpawner(GameObject gameObject) {
        super(gameObject);
        this.go = gameObject;
    }
    
    @Override
    public void start() {
        var pickup = new GameObject(unBox);
        new Box2dBehaviour(BodyDefType.DynamicBody, pickup);
        new BehaviorPickupSpawner(pickup);
    }
    
    @Override
    public void update(float delta) {
        timeline += delta;
        
        var eventCounter = 0;
        if (timeline > 3 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new Box2dBehaviour(BodyDefType.DynamicBody, spawner);
            new BehaviorZombieSpawner(spawner);
            event++;
        } else if (timeline > 10 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new Box2dBehaviour(BodyDefType.DynamicBody, spawner);
            new BehaviorZombieSpawner(spawner);
            event++;
        } else if (timeline > 15 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new Box2dBehaviour(BodyDefType.DynamicBody, spawner);
            new BehaviorZombieSpawner(spawner);
            event++;
        } else if (timeline > 25 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new Box2dBehaviour(BodyDefType.DynamicBody, spawner);
            new BehaviorZombieFastSpawner(spawner);
            event++;
        } else if (timeline > 35 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new Box2dBehaviour(BodyDefType.DynamicBody, spawner);
            new BehaviorBomberSpawner(spawner);
            event++;
        } else if (timeline > 45 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new Box2dBehaviour(BodyDefType.DynamicBody, spawner);
            new BehaviorZombieSpawner(spawner);
            event++;
        } else if (timeline > 50 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new Box2dBehaviour(BodyDefType.DynamicBody, spawner);
            new BehaviorZombieSpawner(spawner);
            event++;
        } else if (timeline > 55 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new Box2dBehaviour(BodyDefType.DynamicBody, spawner);
            new BehaviorZombieFastSpawner(spawner);
            event++;
        } else if (timeline > 60 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new Box2dBehaviour(BodyDefType.DynamicBody, spawner);
            new BehaviorZombieFastSpawner(spawner);
            event++;
        } else if (timeline > 70 && event == eventCounter++) {
            var spawner = new GameObject(unBox);
            new Box2dBehaviour(BodyDefType.DynamicBody, spawner);
            new BehaviorTankSpawner(spawner);
            event++;
        } else if (timeline > 80 && event == eventCounter++) {
            for (var spawner : unBox.findBehaviours(BehaviorBomberSpawner.class)) {
                boolean destroyed = spawner.getState() == BehaviourState.DESTROYED || spawner.getState() == BehaviourState.DESTROYING;
                if (!destroyed) spawner.getGameObject().destroy();
            }
    
            for (var spawner : unBox.findBehaviours(BehaviorTankSpawner.class)) {
                boolean destroyed = spawner.getState() == BehaviourState.DESTROYED || spawner.getState() == BehaviourState.DESTROYING;
                if (!destroyed) spawner.getGameObject().destroy();
            }
    
            for (var spawner : unBox.findBehaviours(BehaviorZombieSpawner.class)) {
                boolean destroyed = spawner.getState() == BehaviourState.DESTROYED || spawner.getState() == BehaviourState.DESTROYING;
                if (!destroyed) spawner.getGameObject().destroy();
            }
    
            for (var spawner : unBox.findBehaviours(BehaviorZombieFastSpawner.class)) {
                boolean destroyed = spawner.getState() == BehaviourState.DESTROYED || spawner.getState() == BehaviourState.DESTROYING;
                if (!destroyed) spawner.getGameObject().destroy();
            }
            
            var boss = new GameObject(unBox);
            new Box2dBehaviour(BodyDefType.DynamicBody, boss);
            var bossBehavior = new BehaviorBoss(boss);
            bossBehavior.startX = 535;
            bossBehavior.startY = 530;
            event++;
        }
    }
}
