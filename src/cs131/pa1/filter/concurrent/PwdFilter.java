package cs131.pa1.filter.concurrent;

public class PwdFilter extends SequentialFilter {
	public PwdFilter() {
		super();
	}
	
	public void process() {
		output.add(processLine(""));
	}
	
	public String processLine(String line) {
		return SequentialREPL.currentWorkingDirectory;
	}
}
