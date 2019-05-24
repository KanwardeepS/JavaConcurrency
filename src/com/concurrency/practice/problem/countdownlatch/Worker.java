package com.concurrency.practice.problem.countdownlatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class Worker implements Runnable {

	private CountDownLatch countDownLatch;
	private List<String> outputScraper;

	public Worker( final List<String> outputscarper, final CountDownLatch countDownlatch ) {
		this.outputScraper = outputscarper;
		this.countDownLatch = countDownlatch;
	}

	@Override
	public void run() {
		//
		outputScraper.add( "Counter Down" );
		countDownLatch.countDown();
	}

	@Test
	public void blockMainUntilCompletion() throws InterruptedException {
		List<String> outputScraper = Collections.synchronizedList( new ArrayList<>() );
		CountDownLatch countDownLatch = new CountDownLatch( 5 );

		//List<Thread> workers = Stream.generate( () -> new Thread( new Worker( outputscraper, countDownlatch ) ) ).limit( 5 ).collect( toList() );

		//	List<Thread> workers = Stream.generate( () -> new Thread( new Worker( outputScraper, countDownLatch ) ) ).limit( 5 ).collect( toList() );

		//workers.forEach( Thread::start );
		countDownLatch.await();
		outputScraper.add( "Latch released" );
	}

}
