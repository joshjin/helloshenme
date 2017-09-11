package cs131.pa1.filter.concurrent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class HeadFilter extends SequentialFilter {
	private Scanner reader;
	private int count;
	private int total;
	
	public HeadFilter(String line) throws Exception {
		super();
		count = 0;
		
		//parsing the head options
		String[] args = line.split(" ");
		String filename;
		System.err.println(args[0]);
		System.err.println(args[1]);
		//System.err.println(args[0]);
		//System.err.println(args[0]);
		//obviously incorrect number of parameters
		if(args.length == 1) {
			System.out.printf(Message.REQUIRES_PARAMETER.toString(), line);
			throw new Exception();
		}
		else if(args[1].charAt(0) == '-' ) {	//check to see if length option is used
			try {
				total = Integer.parseInt(args[1].substring(1));
			} catch (Exception e) {
				System.out.printf(Message.INVALID_PARAMETER.toString(), line);
				throw new Exception();
			}
			if(args.length > 2) {
				filename = args[2];
			} else {	//check to see if filename is given
				System.out.printf(Message.REQUIRES_PARAMETER.toString(), line);
				throw new Exception();
			}
		} else {	//no options, should just be "head filename.txt"
			total = 10;
			filename = args[1];
		}
		try {
			reader = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.printf(Message.FILE_NOT_FOUND.toString(), line);
			throw new Exception();
		}
	}
	
	public void process() {
		while(count < total) {
			String processedLine = processLine("");
			if(processedLine == null) {
				break;
			}
			output.add(processedLine);
		}
		reader.close();
	}
	
	public String processLine(String line) {
		if(reader.hasNextLine()) {
			count++;
			return reader.nextLine();
		} else {
			return null;
		}
	}
}
