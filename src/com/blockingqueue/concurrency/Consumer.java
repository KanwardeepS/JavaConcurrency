package com.blockingqueue.concurrency;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	private BlockingQueue<Message> queue;

	public Consumer( final BlockingQueue<Message> qu ) {
		this.queue = qu;
	}

	@Override
	public void run() {
		try {
			Message msg;
			while ( ( msg = queue.take() ).getMsg() != "exit" ) {
				Thread.sleep( 10 );
				System.out.println( "Consumed " + msg.getMsg() );
			}
		}
		catch ( InterruptedException ie ) {
			ie.printStackTrace();
		}

	}

}
