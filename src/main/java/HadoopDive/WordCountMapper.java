package HadoopDive;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {
	private Text word = new Text();
	private final IntWritable one = new IntWritable(1);
	private final static String pattern = "[^\\w]";

	@Override
	public void map(Object key, Text value, Context contex) throws IOException, InterruptedException {
		// Break line into words for processing
		String line = value.toString().toLowerCase().replaceAll(pattern, " ");
		StringTokenizer wordList = new StringTokenizer(line);
		while (wordList.hasMoreTokens()) {
			word.set(wordList.nextToken());
			contex.write(word, one);
		}
	}
}