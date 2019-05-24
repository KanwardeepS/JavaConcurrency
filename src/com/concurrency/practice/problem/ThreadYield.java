package com.concurrency.practice.problem;

public class ThreadYield {

	public static void main( final String[] args ) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				int counter = 0;
				while ( counter < 2 ) {
					System.out.println( "ThreadName is: " + Thread.currentThread().getName() );
					counter++;
					Thread.yield();
				}
			}
		};
		new Thread( r ).start();
		new Thread( r ).start();

	}
}