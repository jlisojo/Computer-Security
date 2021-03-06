import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;


public class Decryptor
{
	// fields
	private static Random randNum;
	
	// default constructor 
	public Decryptor()
	{
		randNum  = new Random();
		randNum.setSeed(7);
	}

	//////////////////////////////////////////////////
	// show a string as binary (used for debugging) 
	//////////////////////////////////////////////////	
	public void showBinary(String s)
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
		String filename = "toyou.txt";
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
	// decrypt the message
	//////////////////////////////////////////////////
	public String decryptMessage(String message)
	{
		//System.out.println("Before Decryption: " + message);
		
		int XORresult;
		String encryptedMessage = "";
		for (int i = 0; i < message.length(); i++)
		{
			XORresult = (int) (message.charAt(i) ^ random());
			encryptedMessage += (char) XORresult;
		}
		
		//System.out.println("After Decryption: " + encryptedMessage);
		
		return encryptedMessage;
	}	
	
	//////////////////////////////////////////////////
	// generate a random number with my seed 
	//////////////////////////////////////////////////
	public byte random()
	{
		int x = randNum.nextInt(127);
	    return (byte) x;
	}

	//////////////////////////////////////////////////
	// MAIN
	//////////////////////////////////////////////////	
	public static void main(String[] args) 
	{
		Decryptor decryptor = new Decryptor();
		String emsg = decryptor.getMessageFromFile();
		String msg = decryptor.decryptMessage(emsg);
		decryptor.writeMessageToFile(msg);
		System.out.println("Message Decrypted! View it in the file: toyou.txt");
	}
}
