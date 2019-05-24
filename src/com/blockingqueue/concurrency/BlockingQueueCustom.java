package com.blockingqueue.concurrency;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueueCustom {

	private List queue = new LinkedList();
	private int limit = 10;

	public BlockingQueueCustom( final int limt ) {
		this.limit = limt;
	}

	public synchronized void enqueue( final Object item ) throws InterruptedException {
		while ( this.queue.size() == this.limit ) {
			wait();
		}
		if ( this.queue.size() == 0 ) {
			notifyAll();
		}
		this.queue.add( item );
	}

	public synchronized Object dequeue() throws InterruptedException {
		while ( this.queue.size() == 0 ) {
			wait();
		}
		if ( this.queue.size() == this.limit ) {
			notifyAll();
		}
		return this.queue.remove( 0 );
	}

}
