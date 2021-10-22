package com.clarity.delegate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.clarity.beans.AirCraftSize;
import com.clarity.beans.AirCraftType;
import com.clarity.beans.AirCrafts;
import com.clarity.beans.Constants;
import com.clarity.beans.ACException;

public class ACProcessor {
	private ArrayList<AirCrafts> acQueue;

	public ArrayList<AirCrafts> aqm_request_process(boolean process, ArrayList<AirCrafts> airCrafts)
			throws ACException {
		ArrayList<AirCrafts> secondaries = new ArrayList<>();
		try {
			boolean isOn = Boolean.FALSE.equals(process);
			if (isOn) {
				acQueue = new ArrayList<AirCrafts>();
				return acQueue;
			}
			airCrafts.stream().sorted(Comparator.comparing(AirCrafts::getTimeQueued).reversed())
					.collect(Collectors.toList());
			acQueue.addAll(airCrafts.stream().filter(airCraft -> Constants.ENQUEUE.equals(airCraft.getProcess()))
					.collect(Collectors.toList()));
			echoQueue(Constants.ENQUEUE, acQueue);

			// logic to add from a database

			secondaries = (ArrayList<AirCrafts>) airCrafts.stream()
					.filter(airCraft -> AirCraftSize.Large.equals(airCraft.getAirCraftSizes())
							&& AirCraftType.Passenger.equals(airCraft.getAirCraftType()))
					.collect(Collectors.toList());
			echoQueue(Constants.DEQUEUE, secondaries);
			airCrafts.removeAll(secondaries);
			// logic to remove from a database d
			secondaries = (ArrayList<AirCrafts>) airCrafts.stream()
					.filter(airCraft -> airCraft.getAirCraftSizes().equals(AirCraftSize.Small)
							&& airCraft.getAirCraftType().equals(AirCraftType.Passenger))
					.collect(Collectors.toList());
			airCrafts.removeAll(secondaries);
			echoQueue(Constants.DEQUEUE, secondaries);
			secondaries = (ArrayList<AirCrafts>) airCrafts.stream()
					.filter(airCraft -> airCraft.getAirCraftSizes().equals(AirCraftSize.Large))
					.collect(Collectors.toList());
			echoQueue(Constants.DEQUEUE, secondaries);
			airCrafts.removeAll(secondaries);
			secondaries = (ArrayList<AirCrafts>) airCrafts.stream()
					.filter(airCraft -> airCraft.getAirCraftSizes().equals(AirCraftSize.Small)
							&& airCraft.getAirCraftType().equals(AirCraftType.Cargo))
					.collect(Collectors.toList());
			echoQueue(Constants.DEQUEUE, secondaries);
			airCrafts.removeAll(secondaries);

			// logic to remove from a database
		} catch (NullPointerException n) {
			throw new ACException("Error in processing", n);
		}
		acQueue = airCrafts;
		return acQueue;
	}

	private void echoQueue(String process, ArrayList<AirCrafts> airCrafts) {
		for (AirCrafts airCraft : airCrafts) {
			System.out.println("airCraft number " + airCraft.getId() + " is up for procress:: " + process);
		}
	}

}
