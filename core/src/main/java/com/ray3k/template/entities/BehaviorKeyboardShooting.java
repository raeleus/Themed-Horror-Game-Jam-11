package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ray3k.template.*;
import com.ray3k.template.Resources.*;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.entities.BehaviorKeyboardShooting.ShootingMode.*;
import static com.ray3k.template.screens.GameScreen.*;

public class BehaviorKeyboardShooting extends BehaviourAdapter {
    private GameObject gameObject;
    private static final Vector2 temp = new Vector2();
    private static float machineGunDelay = .02f;
    private static float shotGunDelay = .16f;
    private static float rocketDelay = .25f;
    private static float grenadeDelay = .02f;
    private static float timer;
    private static float machineGunAngleRange = 15f;
    private static float shotGunAngleRange = 50f;
    private static float rocketAngleRange = 0f;
    private static float grenadeAngleRange = 90f;
    public enum ShootingMode {
        MACHINE_GUN, SHOTGUN, ROCKET, GRENADE
    }
    public ShootingMode shootingMode = ROCKET;
    
    public BehaviorKeyboardShooting(GameObject gameObject) {
        super(gameObject);
        this.gameObject = gameObject;
    }
    
    @Override
    public void update(float delta) {
        timer -= delta;
        
        //aiming angle
        var shoot = Core.isAnyBindingPressed(Binding.SHOOT_LEFT, Binding.SHOOT_RIGHT, Binding.SHOOT_UP, Binding.SHOOT_DOWN);
        var angle = 0f;
        if (Core.isBindingPressed(Binding.SHOOT_LEFT)) {
            angle = 180;
            if (Core.isBindingPressed(Binding.SHOOT_UP)) {
                angle = 135;
            } else if (Core.isBindingPressed(Binding.SHOOT_DOWN)) {
                angle = 225;
            }
        } else if (Core.isBindingPressed(Binding.SHOOT_RIGHT)) {
            angle = 0;
            if (Core.isBindingPressed(Binding.SHOOT_UP)) {
                angle = 45;
            } else if (Core.isBindingPressed(Binding.SHOOT_DOWN)) {
                angle = 315;
            }
        } else if (Core.isBindingPressed(Binding.SHOOT_UP)) {
            angle = 90;
        } else if (Core.isBindingPressed(Binding.SHOOT_DOWN)) {
            angle = 270;
        }
    
        if (shoot) {
            gameObject.getBody().setTransform(gameObject.getBody().getPosition(), MathUtils.degRad * angle);
            if (timer < 0) {
                
                switch (shootingMode) {
                    case GRENADE:
                        timer = grenadeDelay;
                        var bullet = new GameObject(unBox);
                        var bulletBehavior = new BehaviorBullet(bullet, SpineBullet.skinGrenade, m2p(player.getBody().getPosition().x), m2p(player.getBody().getPosition().y), angle - grenadeAngleRange / 2 + MathUtils.random(
                                grenadeAngleRange), 800f);
                        bulletBehavior.addDeltaX = m2p(player.getBody().getLinearVelocity().x);
                        bulletBehavior.addDeltaY = m2p(player.getBody().getLinearVelocity().y);
                        bulletBehavior.owner = gameObject;
                        bulletBehavior.damage = 0f;
                        bulletBehavior.lifeSpan = .2f;
                        bulletBehavior.createExplosion = true;
                        bulletBehavior.explosionDamage = 15f;
                        break;
                    case ROCKET:
                        timer = rocketDelay;
                        bullet = new GameObject(unBox);
                        bulletBehavior = new BehaviorBullet(bullet, SpineBullet.skinWhite, m2p(player.getBody().getPosition().x), m2p(player.getBody().getPosition().y), angle - rocketAngleRange / 2 + MathUtils.random(
                                rocketAngleRange), 550f);
                        bulletBehavior.owner = gameObject;
                        bulletBehavior.damage = 30f;
                        bulletBehavior.createExplosion = true;
                        bulletBehavior.explosionDamage = 30f;
                        bulletBehavior.homing = true;
                        break;
                    case SHOTGUN:
                        timer = shotGunDelay;
                        for (int i = 0; i < 6; i++) {
                            bullet = new GameObject(unBox);
                            bulletBehavior = new BehaviorBullet(bullet, SpineBullet.skinYellow, m2p(player.getBody().getPosition().x), m2p(player.getBody().getPosition().y), angle - shotGunAngleRange / 2 + MathUtils.random(
                                    shotGunAngleRange), 600f);
                            bulletBehavior.owner = gameObject;
                            bulletBehavior.damage = 16f;
                        }
                        break;
                    case MACHINE_GUN:
                    default:
                        timer = machineGunDelay;
                        bullet = new GameObject(unBox);
                        bulletBehavior = new BehaviorBullet(bullet, SpineBullet.skinWhite, m2p(player.getBody().getPosition().x), m2p(player.getBody().getPosition().y), angle - machineGunAngleRange / 2 + MathUtils.random(
                                machineGunAngleRange), 800f);
                        bulletBehavior.owner = gameObject;
                        bulletBehavior.damage = 19f;
                        break;
                }
            }
        }
    }
}
