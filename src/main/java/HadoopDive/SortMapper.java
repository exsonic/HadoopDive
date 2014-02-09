package HadoopDive;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SortMapper extends Mapper<Object, Text, IntWritable, Text> {
	private Text word = new Text();
	private IntWritable count = new IntWritable();

	@Override
	public void map(Object key, Text value, Context contex) throws IOException, InterruptedException {
		// Break line into words for processing
		String[] line = value.toString().split("\t");

		word.set(line[0]);
		count.set(Integer.parseInt(line[1].trim()));
		contex.write(count, word);
	}
}

