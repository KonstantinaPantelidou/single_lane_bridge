package Assignment_part4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Bridge {

	/*
	 * private ReentrantLock lock = new ReentrantLock(); static int counter = 0;
	 */
	private Semaphore bridgeSem;
	private boolean blueflag = true;
	private boolean redflag = false;
	private Queue<Cars> redcarQueue = new LinkedList<Cars>(); //dimiourgia ouras gia kokkina amaksia gia na kratiountai posa pernoun apo tin mia plevra
	private Queue<Cars> bluecarQueue = new LinkedList<Cars>(); //dimiourgia ouras gia mple amaksia gia na kratiountai posa pernoun apo tin alli plevra

	public Bridge() {
		bridgeSem = new Semaphore(1); // one bridge resource, mutual exclusivity
	}

	public void queuecar(Cars car) {
		if (car.getColor() == "Red")
			redcarQueue.add(car); //red car added in the redcarqueue
		else
			bluecarQueue.add(car); //blue car added in the bluecarQueue

	}

	public synchronized void crossred() {

		while (!redflag && (Math.abs(redcarQueue.size() - bluecarQueue.size()) < 2)) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Cars car = redcarQueue.poll();
		System.out.println(car.getID() + ": Entering bridge");
		try {
			Thread.sleep((int) (Math.random() * 10000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(car.getID() + ": Exiting Bridge.");

		redflag = false;
		blueflag = true;

	}

	public synchronized void crossblue() {

		while (!blueflag && (Math.abs(redcarQueue.size() - bluecarQueue.size()) < 2)) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Cars car = bluecarQueue.poll();
		System.out.println(car.getID() + ": Entering bridge");
		try {
			Thread.sleep((int) (Math.random() * 1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(car.getID() + ": Exiting Bridge.");

		blueflag = false;
		redflag = true;

	}

}
