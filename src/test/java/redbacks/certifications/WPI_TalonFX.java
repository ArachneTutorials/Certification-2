package redbacks.certifications;

/**
 * Fake WPI_TalonFX class to avoid test crashes
 */
public class WPI_TalonFX {
	private double speed;

	public WPI_TalonFX(int deviceNumber) {}

	public void set(double speed) {
		this.speed = speed;
	}

	public double get() {
		return speed;
	}
}
