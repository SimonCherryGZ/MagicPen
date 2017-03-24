package com.plattysoft.leonids.modifiers;

import android.util.Log;

import com.plattysoft.leonids.Particle;

public class CurveModifier implements ParticleModifier {

	private float mVelocity;
	private float mVelocityX;
	private float mVelocityY;

	public CurveModifier(float velocity) {
		mVelocity = velocity;
	}

	@Override
	public void apply(Particle particle, long miliseconds) {
//		float angleInRads = (float) Math.atan(particle.mSpeedY / particle.mSpeedX);
		float angleInRads = (float) Math.atan2(particle.mCurrentY-particle.mInitialY, particle.mCurrentX-particle.mInitialX);
		float angle = (float) ((angleInRads*180f/Math.PI));
		while (angle < 0) {
			angle+=360;
		}
		angle += 90;
		while (angle >= 360) {
			angle -= 360;
		}
		Log.e("angle", String.valueOf(angle));
		float velocityAngleInRads = (float) (angle*Math.PI/180f);

		mVelocityX = (float) (mVelocity * Math.cos(velocityAngleInRads));
		mVelocityY = (float) (mVelocity * Math.sin(velocityAngleInRads));

		particle.mCurrentX += mVelocityX*miliseconds*miliseconds;
		particle.mCurrentY += mVelocityY*miliseconds*miliseconds;
	}

}
