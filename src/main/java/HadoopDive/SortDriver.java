package HadoopDive;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class SortDriver {

	public static void run(String inputPath, String outputPath) throws Exception{
		Job job=new Job(new Configuration());

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		job.setMapperClass(SortMapper.class);
		job.setReducerClass(SortReducer.class);

		job.setSortComparatorClass(Sorter.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.setInputPaths(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));

		job.setJarByClass(App.class);

		job.submit();
	}


	class Sorter extends WritableComparator {

		public Sorter() {
			//register
			super(IntWritable.class, true);
		}

		@Override
		public int compare(byte[] arg0, int arg1, int arg2, byte[] arg3, int arg4, int arg5) {
			//Decrease
			return -super.compare(arg0, arg1, arg2, arg3, arg4, arg5);
		}

		@Override
		public int compare(Object a, Object b) {
			//Decrease
			return -super.compare(a, b);
		}

	}
}
