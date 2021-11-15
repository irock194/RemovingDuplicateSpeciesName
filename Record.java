package hemophiliaB;

public class Record {
	
	private String header;
	private String sequence;
	
	Record(String header, String sequence)
	{
		this.header = header;
		this.sequence = sequence;
	}
	
	public String getHeader()
	{
		return header;
	}
	
	public String getSequence()
	{
		return sequence;
	}
	
	public void setHeader(String header)
	{
		this.header = header;
	}
	
	public void setSequence(String sequence)
	{
		this.sequence = sequence;
	}

}
