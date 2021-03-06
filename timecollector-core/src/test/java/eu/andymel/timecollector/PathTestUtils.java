package eu.andymel.timecollector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.time.Instant;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import eu.andymel.timecollector.graphs.GraphNode;
import eu.andymel.timecollector.graphs.NodePermissions;

public class PathTestUtils {
	
	public static<ID_TYPE> void checkRecPath(TimeCollectorWithPath<ID_TYPE> tc, int expectedLength) {
	
		List<List<SimpleEntry<GraphNode<ID_TYPE, NodePermissions>, Instant>>> recordedPaths =  tc.getRecordedPaths();
		assertNotNull(recordedPaths);
		assertEquals(1, recordedPaths.size());
		List<SimpleEntry<GraphNode<ID_TYPE, NodePermissions>, Instant>> recPath = (List<SimpleEntry<GraphNode<ID_TYPE, NodePermissions>, Instant>>)recordedPaths.get(0);
		assertEquals(expectedLength, recPath.size());
		
		int count = 0;
		long[] times = recPath.stream().map(n -> n.getValue()).mapToLong(time->time.toEpochMilli()).toArray();

		for(int i=0; i<times.length; i++){
			if(i!=times[i]){
				fail("Wrong time at idx "+i+" in "+LongStream.of(times).boxed().map(l->l.toString()).collect(Collectors.joining(", ")));
			}
		}
		
	}

	
	
}
