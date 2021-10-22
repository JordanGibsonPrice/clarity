import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.clarity.beans.ACException;
import com.clarity.beans.AirCraftSize;
import com.clarity.beans.AirCraftType;
import com.clarity.beans.AirCrafts;
import com.clarity.beans.Constants;
import com.clarity.delegate.ACProcessor;

class ACProcessorUnitTests {
	
	private static AirCrafts a1;
	private static AirCrafts a2;
	private static AirCrafts a3;
	private static AirCrafts a4;
	private static AirCrafts a5;
	private static AirCrafts a6;
	private static AirCrafts a7;
	private static AirCrafts a8;
	private static ACProcessor acProcessor;
	private static ArrayList<AirCrafts> airCrafts;
	
	@BeforeAll
	static void buildUp() {
		 a1 = new AirCrafts(AirCraftType.Cargo, AirCraftSize.Large, Constants.DEQUEUE);
		 a2 = new AirCrafts(AirCraftType.Passenger, AirCraftSize.Large, Constants.DEQUEUE);
		 a3 = new AirCrafts(AirCraftType.Cargo, AirCraftSize.Small, Constants.DEQUEUE);
		 a4 = new AirCrafts(AirCraftType.Passenger, AirCraftSize.Small, Constants.DEQUEUE);
		 a5 = new AirCrafts(AirCraftType.Cargo, AirCraftSize.Large, Constants.ENQUEUE);
		 a6 = new AirCrafts(AirCraftType.Passenger, AirCraftSize.Large, Constants.ENQUEUE);
		 a7 = new AirCrafts(AirCraftType.Cargo, AirCraftSize.Small, Constants.ENQUEUE);
		 a8 = new AirCrafts(AirCraftType.Passenger, AirCraftSize.Small, Constants.ENQUEUE);
		 acProcessor = new ACProcessor();
		 airCrafts = new ArrayList<>();
	}

	@Test
	void testErrorHandling() throws ACException {
		Assertions.assertThrows(ACException.class, () ->
			acProcessor.aqm_request_process(true, null), 
			"expected ACException"
		);
		
		Assertions.assertThrows(ACException.class, () ->
		acProcessor.aqm_request_process(true, null), 
		"expected ACException"
	);
		
		Assertions.assertNotNull(acProcessor.aqm_request_process(false, null));
	}
	
	@Test
	void testOrdering() throws ACException {
		airCrafts.add(a1);
		airCrafts.add(a2);
		airCrafts.add(a3);
		airCrafts.add(a4);
		
		acProcessor.aqm_request_process(true, airCrafts);
		
		
				
		airCrafts.add(a5);
		airCrafts.add(a6);
		airCrafts.add(a7);
		airCrafts.add(a8);
		
		ArrayList holder = airCrafts;

		
		acProcessor.aqm_request_process(true, airCrafts);
		
		Assertions.assertTrue(airCrafts.isEmpty());

	}

}
