package com.blockingqueue.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

public class ProducerConsumerService {

	public static void main( final String[] args ) {
		BlockingQueue<Message> queue = new LinkedTransferQueue<Message>();
		Producer producer = new Producer( queue );
		Consumer consumer = new Consumer( queue );
		new Thread( producer ).start();
		new Thread( consumer ).start();

		System.out.println( "********  Producer Consumer Service has been started  *******" );
	}

}
