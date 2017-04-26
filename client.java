import java.net.*;
import java.io.*;

public class client3
{
	private static Socket socket;
	public static void main(String args[])
	{
		try
		{
			String host = "202.58.86.29";
			int port = 8080;
			InetAddress address = InetAddress.getByName(host);
			socket = new Socket(address,port);

			//send message to the server
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);

			String msg = "Testing";
			String sndMsg = msg + "\n";
			bw.write(sndMsg);
			bw.flush();
			System.out.println("Message sent: " + sndMsg);

			//Get message from the server
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String message = br.readLine();
			System.out.println("Message received: " + message);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
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
