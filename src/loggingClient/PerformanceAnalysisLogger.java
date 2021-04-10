import java.util.*;
import java.util.logging.*;
import java.io.*;

public class PerformanceAnalysisLogger {
	static long startTime;
	public static void main(String[] args) {
		Logger logger = Logger.getLogger("MyLog");  
		FileHandler fh;  
		try {
			// This block configure the logger with handler and formatter  
			fh = new FileHandler("../logs/PerformanceAnalysisLogFile.csv");  
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
			fh.setFormatter(formatter);
			startTime=System.currentTimeMillis();
			System.out.println("Check processing time of the sys out statement");
			System.out.println("Check processing time of the sys out statement");
			System.out.println("Check processing time of the sys out statement");
			System.out.println("Check processing time of the sys out statement");
			System.out.println("Check processing time of the sys out statement");
			System.out.println("Check processing time of the sys out statement");
			System.out.println("Check processing time of the sys out statement");
			System.out.println("Check processing time of the sys out statement");
			System.out.println("Check processing time of the sys out statement");
			System.out.println("Check processing time of the sys out statement");
		} catch (SecurityException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}
		logger.info("EndAnalysisRecord;"+(System.currentTimeMillis() - startTime)+";"+ ((int)((Math.random() * 40) + 1))+";");
	}
}
/*From Redis client: redis-cli
set 1111 '{"Organization": {"OrgName": "XYZ", "OrgCode": 123, "SiteName": "AAA"}, "Tags": {"T1": "A", "T2": "B"}}'
Jedis Jar downloaded from: https://jar-download.com/artifacts/redis.clients/jedis
*/