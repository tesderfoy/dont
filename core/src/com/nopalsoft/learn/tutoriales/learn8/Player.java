package com.nopalsoft.learn.tutoriales.learn8;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Learn more about libGDX:
 * My personal blog (spanish): https://tinyurl.com/yw5hawc2
 * Youtube video course: https://tinyurl.com/ytunwuad
 *
 * @author Yayo Arellano
 */
public class Player {
    static final float WIDTH = .45f;
    static final float HEIGHT = .6f;

    static final float DRAW_WIDTH = .8f;
    static final float DRAW_HEIGHT = 1.9f;

    static final float WALK_FRAME_DURATION = 0.05f;

    static final float WALK_SPEED = 2;

    boolean isWalking;
    boolean isWalkingDown;
    boolean isWalkingUp;

    float stateTime = 0;

    Vector2 position;
    Vector2 velocity;

    public Player(float x, float y) {
        position = new Vector2(x, y);
    }

    public void update(Body body, float delta, float accelX, float accelY ) {
        position.x = body.getPosition().x;
        position.y = body.getPosition().y;

        velocity = body.getLinearVelocity();

        if (accelX == -1) {
            velocity.x = -WALK_SPEED;
            isWalking = true;
        } else if (accelX == 1) {
            velocity.x = WALK_SPEED;
            isWalking =true;
        } else {
            velocity.x = 0;
            isWalking = false;
        }

        if (accelY == -1) {
            velocity.y = -WALK_SPEED;
            isWalkingUp = true;
            isWalkingDown = false;
        } else if (accelY == 1) {
            velocity.y = WALK_SPEED;
            isWalkingUp = false;
            isWalkingDown = true;
        }
        else{
            velocity.y = 0;
            isWalkingUp = false;
            isWalkingDown = false;
        }

        body.setLinearVelocity(velocity);
        stateTime += delta;
    }

}
