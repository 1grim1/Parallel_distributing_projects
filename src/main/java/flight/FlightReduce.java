package flight;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class FlightReduce extends Reducer<WComparable, Text, Text, Text>{
    private  static double COUNT = 0;
    private  static double SUM = 0;


    private Text getReduceText(double min, double max , double average){
        return new Text("Min Delay - " + min + ";  Max Delay - " + max + ";  Average dalay - " + average + ";");
    }

    @Override
    protected void reduce(WComparable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> iter = values.iterator();
        Text name = new Text(iter.next());
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        while (iter.hasNext()){
            double currentValue = Double.parseDouble(iter.next().toString());
            min = Double.min(min, currentValue);
            max = Double.max(max, currentValue);
            SUM += currentValue;
            COUNT++;
        }
        if (COUNT > 0) {
            context.write(name, getReduceText(min, max, SUM / COUNT));
        } else {
            context.write(name, getReduceText(min, max, 0.0));
        }
    }
}
