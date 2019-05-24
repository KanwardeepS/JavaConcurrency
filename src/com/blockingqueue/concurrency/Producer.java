package com.blockingqueue.concurrency;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private BlockingQueue<Message> queue;

	public Producer( final BlockingQueue<Message> qu ) {
		this.queue = qu;

	}

	@Override
	public void run() {
		//produce messages

		for ( int i = 0; i < 100; i++ ) {
			Message msg = new Message( "" + i );
			try {
				Thread.sleep( i );
				queue.put( msg );
				System.out.println( "Produced Message" + msg.getMsg() );
			}
			catch ( InterruptedException ie ) {
				ie.printStackTrace();
			}
		}
		Message msgExit = new Message( "Exit" );
		try {
			queue.put( msgExit );
		}
		catch ( InterruptedException ie ) {
			ie.printStackTrace();
		}

	}

}
