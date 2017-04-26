import java.io.*;
import java.net.*;

public class server3
{
	private static Socket socket;
	public static void main(String[] args)
	{
		try
		{
			int port = 8080;
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server Started and listening to port 8080");

			//Server is running always. This is done using this while(true) loop
			while(true)
			{
				//Reading the message from the client
				socket = serverSocket.accept();
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String msg = br.readLine();
				System.out.println("Message received from client is " + msg );

				//Reversing the message and forming the return message
				String returnMessage;
				try
				{
					char temp;
					char [] arr = msg.toCharArray();
					int len = arr.length;
					for ( int j=0; j<(msg.length())/2; j++,len--)
					{
						temp = arr[j];
						arr[j] = arr[len-1];
						arr[len-1] = temp;
					}

					returnMessage = String.valueOf(arr) + "\n";
				}
				catch(Exception e)
				{
					returnMessage = "Please enter a proper message\n";
				}

				//sending back the response to the client
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				BufferedWriter bw = new BufferedWriter(osw);
				bw.write(returnMessage);
				System.out.println("Message sent to client is" + returnMessage);
				bw.flush();
			}	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				socket.close();
			}
			catch(Exception e){}
		}
	}
}
