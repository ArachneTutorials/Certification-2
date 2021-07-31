package redbacks.certifications;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arachne.lib.io.sensors.GettableDoubleInput;
import arachne.lib.pipeline.DoublePipe;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Question1 extends Object {
	/*
	 * Create a function 'talk' which accepts a string 'message' as a parameter.
	 * 
	 * When the message is "hi", the function should return "hello".
	 * When the message is "bye", the function should return "goodbye".
	 * Otherwise, the function should return "input not understood".
	 */
	// Write your code here

	/*
	 * Create a function 'spin' which accepts a boolean 'activate' as a parameter.
	 * 
	 * When the parameter is true, spin the motor created below at 60% power.
	 * When the parameter is false, stop spinning the motor.
	 */
	WPI_TalonFX motor = new WPI_TalonFX(0);

	// Write your code here

	/**
	 * Create a function 'deadzoneTracking' which moves a motor towards a sensor target.
	 * The motor is the same as the one in the last task, and the sensor is defined below.
	 * Use sensor.get() to get the value of the sensor.
	 * 
	 * If the difference between the current sensor value and the target is less than (and not equal to) the deadzone, the motor should stop.
	 * If the sensor value is less than the target value, it should move in the positive direction at the given speed.
	 * If the sensor value is more, it should move in the negative direction.
	 * 
	 * You should use the existing variables rather than typing '10', '0.8' or '100' into your code.
	 */
	double DEADZONE = 10;
	double SPEED = 0.8;
	double TARGET = 100;

	GettableDoubleInput sensor = new GettableDoubleInput();

	// Write your code here

	/*
	 * Testing code, do not modify
	 */
	@BeforeEach
	void setUp() {
		motor.set(0);
	}

	@Test
	void testTalk() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = getClass().getDeclaredMethod("talk", String.class);
		assertEquals(String.class, method.getReturnType());

		assertEquals("hello", method.invoke(this, "hi"));
		assertEquals("goodbye", method.invoke(this, "bye"));
		assertEquals("input not understood", method.invoke(this, "something else"));
	}

	@Test
	void testSpin() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = getClass().getDeclaredMethod("spin", Boolean.TYPE);
		assertEquals(Void.TYPE, method.getReturnType());

		method.invoke(this, true);
		assertEquals(0.6, motor.get());

		method.invoke(this, false);
		assertEquals(0, motor.get());
	}

	@Test
	void testDeadzoneTracking() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = getClass().getDeclaredMethod("deadzoneTracking");
		assertEquals(Void.TYPE, method.getReturnType());

		DoublePipe sensorImpl = new DoublePipe(0);
		sensor.populate(sensorImpl);

		method.invoke(this);
		assertEquals(0.8, motor.get());

		sensorImpl.accept(90);
		method.invoke(this);
		assertEquals(0.8, motor.get());

		sensorImpl.accept(95);
		method.invoke(this);
		assertEquals(0, motor.get());

		sensorImpl.accept(105);
		method.invoke(this);
		assertEquals(0, motor.get());

		sensorImpl.accept(110);
		method.invoke(this);
		assertEquals(-0.8, motor.get());

		sensorImpl.accept(120);
		method.invoke(this);
		assertEquals(-0.8, motor.get());
	}
}
