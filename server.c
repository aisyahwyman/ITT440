#include<stdio.h>
#include<string.h>   
#include<sys/socket.h>
#include<arpa/inet.h>
#include<unistd.h>
 
int main(int argc , char *argv[])
{
    int socket_desc , client_sock , c , read_size;
    struct sockaddr_in servaddr , cliaddr;
    char client_message[2000], temp;
    int i, j = 0;
     
    socket_desc = socket(AF_INET , SOCK_STREAM , 0);
    if (socket_desc == -1)
    {
        printf("Could not create socket");
    }
    puts("Socket created");
     
    //Prepare the sockaddr_in structure
    servaddr.sin_family = AF_INET;
    servaddr.sin_addr.s_addr = INADDR_ANY;
    servaddr.sin_port = htons( 9999 );
     
    if( bind(socket_desc,(struct sockaddr *)&servaddr , sizeof(servaddr)) < 0)
    {
        perror("bind failed. Error");
        return 1;
    }
    puts("bind done");
    listen(socket_desc , 3);
     
    //Accept and incoming connection
    puts("Waiting for incoming connections...");
    c = sizeof(struct sockaddr_in);
     
    //accept connection from incoming client
    client_sock = accept(socket_desc, (struct sockaddr *)&cliaddr, (socklen_t*)&c);
    if (client_sock < 0)
    {
        perror("Accept failed");
        return 1;
    }
    puts("Connection accepted");

    //Receive message from client
    while( (read_size = recv(client_sock , cliaddr_message , 2000 , 0)) > 0 )
    {
    
	//Revere message from client
	i = 0;
	j = strlen(cliaddr_message) - 1;

	while(i < j){
		temp = cliaddr_message[i];
		cliaddr_message[i] = client_message[j];
		cliaddr_message[j] = temp;
		i++;
		j--;
	}
	
	//Send the message back to client 
        write(client_sock , cliaddr_message, strlen(cliaddr_message));
	//memset(client_message,'\0',sizeof(client_message));
    }
     
    if(read_size == 0)
    {
        puts("Client disconnected");
        fflush(stdout);
    }
    else if(read_size == -1)
    {
        perror("Receive failed");
    }
     
    return 0;
}

