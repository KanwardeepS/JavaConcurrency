package com.concurrency.practice.problem;

public class Philosopher implements Runnable {

	private Object leftFork;
	private Object rightFork;

	public Philosopher( final Object leftFork, final Object rightFork ) {
		this.leftFork = leftFork;
		this.rightFork = rightFork;
	}

	@Override
	public void run() {

		try {
			while ( true ) {

				doAction( "Thinking" );
				synchronized ( leftFork ) {
					//eating
					doAction( System.nanoTime() + " Picked the left fork" );
					synchronized ( rightFork ) {
						doAction( System.nanoTime() + " : Picked the right fork - Eating" );

						doAction( System.nanoTime() + " : Put down right fork " );

					}
					doAction( System.nanoTime() + " Put down left fork. Back to Thinking" );
				}

			}
		}
		catch ( InterruptedException l_ex ) {
			Thread.currentThread().interrupt();
			l_ex.printStackTrace();
		}
	}

	private void doAction( final String action ) throws InterruptedException {
		System.out.println( Thread.currentThread().getName() + " " + action );
		Thread.sleep( (int) ( Math.random() * 100 ) );

	}

}
