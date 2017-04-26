#include<stdio.h>	
#include<string.h>	
#include<sys/socket.h>	
#include<arpa/inet.h>	

int main(int argc , char *argv[])
{
	int sockt;
	struct sockaddr_in server;
	char msgSend[1000] , server_reply[2000];
	
	//Create socket
	sockt = socket(AF_INET , SOCK_STREAM , 0);
	if (sockt == -1)
	{
		printf("Could not create socket");
	}
	puts("Socket created");
	
	server.sin_addr.s_addr = inet_addr("202.58.86.29");
	server.sin_family = AF_INET;
	server.sin_port = htons( 8080 );

	//Connect to server
	if (connect(sockt , (struct sockaddr *)&server , sizeof(server)) < 0)
	{
		perror("connect failed. Error");
		return 1;
	}
	
	puts("Connected\n");
	
	//communicating 
	while(1)
	{
		printf("Enter message : ");
		scanf("%s" , msgSend);
		
		//Send data
		if( send(sockt , msgSend , strlen(msgSend) , 0) < 0)
		{
			puts("Send failed");
			return 1;
		}
		
		//Receive reply 
		if( recv(sockt , server_reply , 2000 , 0) < 0)
		{
			puts("receiving failed");
			break;
		}
		
		puts("Server reply :");
		puts(server_reply);
	}
	
	return 0;
}
