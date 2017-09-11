package cs131.pa1.filter.concurrent;

import java.util.List;
import java.util.Scanner;

import cs131.pa1.filter.Filter;
import cs131.pa1.filter.Message;

public class SequentialREPL {

	static String currentWorkingDirectory;
	
	public static void main(String[] args){
		currentWorkingDirectory = System.getProperty("user.dir");
		Scanner s = new Scanner(System.in);
		System.out.print(Message.WELCOME);
		String command;
		while(true) {
			//obtaining the command from the user
			System.out.print(Message.NEWCOMMAND);
			command = s.nextLine();
			if(command.equals("exit")) {
				break;
			} else if(!command.trim().equals("")) {
				
				//building the filters list from the command
				SequentialFilter filterlist = SequentialCommandBuilder.createFiltersFromCommand(command);
				while(filterlist != null) {
					filterlist.process();
					filterlist = (SequentialFilter) filterlist.getNext();
				}
				
				//checking to see if construction was successful. If not, prompt user for another command
//				if(filterlist != null) {
//					//run each filter process manually.
//					Iterator<ConcurrentFilter> iter = filterlist.iterator();
//					while(iter.hasNext()){
//						iter.next().process();
//					}
//				}
			}
		}
		s.close();
		System.out.print(Message.GOODBYE);
	}

}
