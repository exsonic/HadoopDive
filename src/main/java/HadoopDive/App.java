package HadoopDive;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Random;

public class App {
    public static void main( String[] args) throws Exception{
	    args = args.length == 0 ? new String[]{"wordcount" ,"1.txt", "out"} : args;
	    if (args.length != 3) {
		    System.out.println("usage: [function] [input] [output]");
		    System.exit(-1);
	    } else if (args[0].equals("wordcount")) {
		    File tempDir = new File("wordcount-temp-" + Integer.toString(new Random().nextInt(Integer.MAX_VALUE)));
		    File out = new File(args[2]);
		    if (out.exists()) {
			    FileUtils.deleteDirectory(out);
		    }

		    try {
			    WordCountDriver.run(args[1], tempDir.getPath());
			    SortDriver.run(tempDir.getPath(), args[2]);
		    } finally {
			    FileUtils.deleteDirectory(tempDir);
		    }
	    }
    }

}
