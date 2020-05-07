package Assignment_part4;

public class Cars extends Thread {

	private String color;
	private String id;
	private Bridge bridge;

	public Cars(String id, String color, Bridge bridge) {

		this.id = id;
		this.color = color;
		this.bridge = bridge;
	}

	public String getColor() {
		return color;
	}

	public String getID() {
		return id;
	}

	public void run() {
		
		bridge.queuecar(this);

		if (color == "Red")
			bridge.crossred();
		else if (color == "Blue")
			bridge.crossblue();
	}

}