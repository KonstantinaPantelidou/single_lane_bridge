package Assignment_part4;

import java.util.Random;

public class Main {

	public static void main(String[] args) {

		int num = 50;
		Random random = new Random();
		int TotalCars = random.nextInt(50);
		TotalCars += 1;
		int counter;

		Bridge bridge = new Bridge();

		Cars[] f = new Cars[num];

		for (int i = 0; i < num; i++) {

			// int rand = (int) (Math.random() * 100) % 2;
			Random rand = new Random();
			int j = rand.nextInt(100);
			j += 1;
			int pl = 0;

			boolean flag = false;
			for (int y = 2; y <= j / 2; ++y) {
				// condition for nonprime number
				if (j % y == 0) {
					flag = true;
					break;
				}
			}

			if (!flag)
				f[i] = new Cars("Blue Car" + (i + 1), "Blue", bridge);
			else
				f[i] = new Cars("Red Car" + (i + 1), "Red", bridge);
		}

		for (int i = 0; i < num; i++) {
			System.out.println(f[i].getID());
			f[i].start();
		}

		for (int i = 0; i < num; i++) {
			try {
				f[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
