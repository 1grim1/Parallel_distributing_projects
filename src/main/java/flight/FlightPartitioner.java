package flight;

import org.apache.hadoop.mapreduce.Partitioner;

import javax.xml.soap.Text;

public class FlightPartitioner extends Partitioner<WComparable, Text> {

    @Override
    public int getPartition(WComparable wComparable, Text text, int i) {
        return 0;
    }
}
