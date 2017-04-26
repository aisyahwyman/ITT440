import java.net.*;
import java.io.*;

public class client3
{
	private static Socket socket;
	public static void main(String args[])
	{
		try
		{
			String host = "192.168.35.134";
			int port = 8080;
			InetAddress address = InetAddress.getByName(host);
			socket = new Socket(address,port);

			//send the message to the server
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);

			String msg = "Hello World";
			String sendMessage = msg + "\n";
			bw.write(sendMessage);
			bw.flush();
			System.out.println("Message sent to the server: " + sendMessage);

			//Get the return message from the server
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String message = br.readLine();
			System.out.println("Message received from the server: " + message);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			//closing the socket
			try
			{
				socket.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
