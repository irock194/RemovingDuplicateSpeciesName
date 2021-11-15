package hemophiliaB;

import java.io.*;
import java.util.*;


public class RemoveDuplicates {
		
	public static void main(String [] args)
	{
		TreeMap<String, Record> fastaRecords = new TreeMap<String, Record>();
		int numberOfSpecies = 0;
		int numberOfUniqueSpecies = 0;
		try {
			File file = new File("C:\\Users\\Erin\\Desktop\\SJSU\\CS 123A\\Project\\primates.txt");
			Scanner sc = new Scanner(file);
			final int speciesNameStart = 27;
			String sequence = new String();
			String header = new String();
			String speciesName = new String();
			boolean beginStoringSequence = false;
			
			while(sc.hasNextLine())
			{
				String currentLine = sc.nextLine();
				if(currentLine.startsWith(">"))
				{
					if(beginStoringSequence)
					{
						//store fasta sequence in tree and then reset sequence
						//System.out.println(sequence);
						fastaRecords.put(speciesName, new Record(header, sequence));
						sequence = new String();
						numberOfSpecies++;
					}
					
					int firstWhiteSpace = currentLine.indexOf(" ", speciesNameStart);
					int secondWhiteSpace = currentLine.indexOf(" ", firstWhiteSpace + 1);
					speciesName = currentLine.substring(27, firstWhiteSpace) + "." + currentLine.substring(firstWhiteSpace + 1, secondWhiteSpace); //species name with a period
					
					header = currentLine;
					beginStoringSequence = true;
				}
				else
				{
					//continue adding the next line into the fasta sequence
					sequence += currentLine + "\n";
				}
				
			}
			
			sc.close();
		}
		catch(Exception e) {
			System.out.println("Exception Occured:");
			e.printStackTrace();
		}
		
		//Either method can be used based on nessecary requirments
		CreateNormalFastaFile(fastaRecords, numberOfUniqueSpecies);
		CreateFastaFileWithOnlySpeciesName(fastaRecords, numberOfUniqueSpecies);
		
		System.out.println("Total Species: " + numberOfSpecies);
		System.out.println("Total Unique Species: " + numberOfUniqueSpecies);
		
	}
	
	static public void CreateNormalFastaFile(TreeMap<String, Record> fastaRecords, int numberOfUniqueSpecies)
	{
		try {
			
			File file = new File("C:\\Users\\Erin\\Desktop\\SJSU\\CS 123A\\Project\\removedDuplicatedSpecies.txt");
			
			boolean flag = file.createNewFile();
			if (flag) {
				System.out.println("File has been created successfully.");
			}
			else {
				System.out.println("File already present at specified location");
			}
			
			FileWriter outputStream = new FileWriter(file);
			
			for(Map.Entry<String, Record> entry : fastaRecords.entrySet())
			{	
				outputStream.write(entry.getValue().getHeader() + "\n");
				outputStream.write(entry.getValue().getSequence());
				numberOfUniqueSpecies++;
			}
			
			outputStream.close();
		}
		catch(Exception e) {
			System.out.println("Exception Occured:");
			e.printStackTrace();
		}
	}
	
	static public void CreateFastaFileWithOnlySpeciesName(TreeMap<String, Record> fastaRecords, int numberOfUniqueSpecies)
	{
		try {
			
			File file = new File("C:\\Users\\Erin\\Desktop\\SJSU\\CS 123A\\Project\\speciesNameOnly.txt");
			
			boolean flag = file.createNewFile();
			if (flag) {
				System.out.println("File has been created successfully.");
			}
			else {
				System.out.println("File already present at specified location");
			}
			
			FileWriter outputStream = new FileWriter(file);
			
			for(Map.Entry<String, Record> entry : fastaRecords.entrySet())
			{	
				outputStream.write(">" + entry.getKey() + "\n");   //use this to isolate species name 
				outputStream.write(entry.getValue().getSequence());
				numberOfUniqueSpecies++;
			}
			
			outputStream.close();
		}
		catch(Exception e) {
			System.out.println("Exception Occured:");
			e.printStackTrace();
		}
	}
}
