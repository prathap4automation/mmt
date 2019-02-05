package utility;

public class HtmlManager {
	
	public String fail(String s)
	{	return "<font color=\"red\"><b>"+s+"</b></font>"; 		}
	
	public String pass(String s)
	{	return "<font color=\"green\"><b>"+s+"</b></font>"; 		}
	
	public String skip(String s)
	{	return "<font color=\"orange\"><b>"+s+"</b></font>"; 		}
	
	public String info(String s)
	{	return "<font color=\"blue\"><b>"+s+"</b></font>"; 		}

}
