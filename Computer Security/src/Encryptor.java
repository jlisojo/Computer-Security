/** 
 * Joshua Lisojo
 * Computer Security 
 * Homework 1
 * September 25, 2012
 *    
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;


public class Encryptor 
{
	// fields
	private static Random randNum;
	
	// default constructor 
	public Encryptor()
	{
		randNum  = new Random();
		randNum.setSeed(23);
	}
	
	//////////////////////////////////////////////////
	// show a string as binary (used for debugging) 
	//////////////////////////////////////////////////	
	public static void showBinary(String s)
	{
		byte[] bytes = s.getBytes();
		StringBuilder binary = new StringBuilder();
		
		for (byte b : bytes)
		{
		     int val = b;
		     
		     for (int i = 0; i < 8; i++)
		     {
		        binary.append((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		     binary.append(' ');
		}
		
		System.out.println(binary);
	}
	
	//////////////////////////////////////////////////
	// read the message from the file 
	//////////////////////////////////////////////////
	public String getMessageFromFile()
	{
		String filename = "message.txt";
		String message = "";
		
		File file = new File(System.getProperty("user.dir") + "\\src\\" + filename);
        
        try {
            Scanner inputFile = new Scanner(file);
            
            while(inputFile.hasNext())
            {
            	message += inputFile.nextLine();
            }
                                    
            // Close the file.
            inputFile.close();
        } 
        catch (Exception e) 
        {
        	System.out.println(e);
        }
        
		return message;		
	}
	
	//////////////////////////////////////////////////
	// write message to the file 
	//////////////////////////////////////////////////
	public void writeMessageToFile(String message)
	{
		String filename = "toyou.txt";
		
		try {
			PrintWriter outputFile = new PrintWriter(System.getProperty("user.dir") + "\\src\\" + filename);
			
			// write the message 
			outputFile.println(message);
			
			// Close the file.
			outputFile.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////////////////
	// encrypt the message
	//////////////////////////////////////////////////
	public String encryptMessage(String message)
	{
		System.out.println("Before Encryption: " + message);
		//byte bite = (byte) random();
		//showBinary(message);
		
		int XORresult;
		String encryptedMessage = "";
		for (int i = 0; i < message.length(); i++)
		{
			XORresult = (int) (message.charAt(i) ^ random());
			encryptedMessage += (char) XORresult;
		}
		
		//showBinary(encryptedMessage);
		System.out.println("After Encryption: " + encryptedMessage);
		
		return encryptedMessage;
	}
	
	//////////////////////////////////////////////////
	// generate a random number with my seed 
	//////////////////////////////////////////////////
	public int random()
	{	    
//	    byte[] b = new byte[1];	 	    
//	    randnum.nextBytes(b);	    
//	    byte x = b[0];
		
		int x = randNum.nextInt(255);
	    
	    System.out.println("returning x = " + x);
	    return x;
	}	
	
	// main method 
	public static void main(String[] args) 
	{
		Encryptor encryptor = new Encryptor();
		
		String msg = encryptor.getMessageFromFile();
		String emsg = encryptor.encryptMessage(msg);
		encryptor.writeMessageToFile(emsg);				
	}
}
