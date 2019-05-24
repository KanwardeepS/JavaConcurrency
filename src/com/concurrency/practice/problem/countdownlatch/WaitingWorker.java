package com.concurrency.practice.problem.countdownlatch;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class WaitingWorker implements Runnable {

	  private List<String> outputScraper;
	    private CountDownLatch readyThreadCounter;
	    private CountDownLatch callingThreadBlocker;
	    private CountDownLatch completedThreadCounter;

	    public WaitingWorker(
	      final List<String> outputScraper,
	      final CountDownLatch readyThreadCounter,
	      final CountDownLatch callingThreadBlocker,
	      final CountDownLatch completedThreadCounter) {

	        this.outputScraper = outputScraper;
	        this.readyThreadCounter = readyThreadCounter;
	        this.callingThreadBlocker = callingThreadBlocker;
	        this.completedThreadCounter = completedThreadCounter;
	    }

	    @Override
	    public void run() {
	        readyThreadCounter.countDown();
	        try {
	            callingThreadBlocker.await();
	            //doSomeWork();
	            outputScraper.add("Counted down");
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } finally {
	            completedThreadCounter.countDown();
	        }
	    }

	@Test
	public static void whenParallelProcessing_thenMainThreadWillBlockUntilCompletion() throws InterruptedException {

		List<String> outputScraper = Collections.synchronizedList( new ArrayList<>() );
		CountDownLatch countDownLatch = new CountDownLatch( 5 );
		List<Thread> workers = Stream.generate( () -> new Thread( new Worker( outputScraper, countDownLatch ) ) )
			.limit( 5 )
			.collect( Collectors.toList() );

		workers.forEach( Thread::start );
		countDownLatch.await();
		outputScraper.add( "Latch released" );

		//assertThat( outputScraper )
		//.containsExactly( "Counted down", "Counted down", "Counted down", "Counted down", "Counted down", "Latch released" );
	}



}