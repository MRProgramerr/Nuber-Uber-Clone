package nuber.students;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;

public class Simulation {

	/**
	 * 
	 * @param regions The region names and maximum simultaneous active bookings allowed in that region
	 * @param maxDrivers The number of drivers to create
	 * @param maxPassengers The number of passengers to create
	 * @param maxSleep The maximum amount a thread will sleep (in millseconds)) to simulate driving to, or dropping off a passenger
	 * @param logEvents Whether to log booking events to the console
	 * @throws Exception
	 */
	public Simulation(HashMap<String, Integer> regions, int maxDrivers, int maxPassengers, int maxSleep, boolean logEvents) throws Exception {
		
		//store the current time
		long start = new Date().getTime();
		
		//print some space in the console
		System.out.println("\n\n\n");

		//store a queue of all current bookings as Future's that will eventually give us back a BookingResult object
		Queue<Future<BookingResult>> bookings = new LinkedList<Future<BookingResult>>();

		//convert the region names from the regions map into an array
		String[] regionNames = regions.keySet().toArray(new String[0]);

		//create a new dispatch object
		NuberDispatch dispatch = new NuberDispatch(regions, logEvents);

		// create drivers that are available for jobs
		for (int i = 0; i < maxDrivers; i++) {
			Driver d = new Driver("D-" + Person.getRandomName(), maxSleep);
			dispatch.addDriver(d);
		}

		// create passengers
		for (int i = 0; i < maxPassengers; i++) {
			
			Passenger p = new Passenger("P-" + Person.getRandomName(), maxSleep);
			
			//choose a random region to assign this person
			String randomRegion = regionNames[new Random().nextInt(regionNames.length)];
			
			//add each passenger to dispatch to book their travel for a random region
			Future<BookingResult> f = dispatch.bookPassenger(p, randomRegion);
			if (f != null)
			{
				//store the future to our list
				bookings.add(f);
			}
		}

		// tell all the regions to run all pending passengers, and then shutdown
		dispatch.shutdown();
		
		//check that dispatch won't let us book passengers after we've told it to shutdown
		if (dispatch.bookPassenger(new Passenger("Test", maxSleep), regionNames[new Random().nextInt(regionNames.length)]) != null)
		{
			throw new Exception("Dispatch bookPassenger() should return null if passenger requests booking after dispatch has started the shutdown");
		}

		//whilst there are still active bookings, print out an update every 1s
		while (bookings.size() > 0) {
			
			//go through each booking, and if it's done, remove it from our active bookings list
			Iterator<Future<BookingResult>> i = bookings.iterator();
			while (i.hasNext()) {
				Future<BookingResult> f = i.next();

				if (f.isDone()) {
					i.remove();
				}
			}

			//print status update
			System.out.println("Active bookings: " + bookings.size()+", pending: "+dispatch.getBookingsAwaitingDriver());

			//sleep for 1s and then print out the current bookings
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		//print out the final information for the simulation run
		long totalTime = new Date().getTime() - start;
		System.out.println("Simulation complete in "+totalTime+"ms");
	}
}
