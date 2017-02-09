# ChatClient
ChatClient using JavaRMI

This project consists of 3 Programs – 1 for ChatClient, 1 for ChatServer and the
other 1 for Registry Program.
ChatProvider Program helps to create several ChatServers.
ChatServer Program contains ChatRoom capabilities (Each Chat Server is a ChatRoom),
managings clients etc.
ChatClient Program implements Client capabilities and interactions/
For this I have created a Java Project with 3 Packages. The whole project structure is as follows:
RMIChatService
- src
- com.rmi.registry (Package)
- ChatProvider.java (implements the interface)
- ChatProviderInterface.java
- RegistryProgram.java (Main method)
- com.rmi.server(Package)
- ChatServerInterface.java
- ChatServer.java (implements the interface)
- com.rmi.server(Package)
- ChatClientInterface.java
- ChatClient.java (implements the interface)
- ChatClientMain.java (main method)
RMI method works in Java using Interfaces. So, the interfaces for each functionality are created
first and the ChatProvider, ChatServer, ChatCleint classes implements their corresponding
interfaces.

1) REGISTRY PROGRAM – (IN com.rmi.registry PACKAGE)
Com.rmi.registry – This includes 3 files:
a) ChatProviderInterface – This is an interface that is created and includes all the
methods that we want the ChatProvider class to implement.

b) ChatProvider.java – This implements the ChatProviderInterface and contains
method definitions for all the methods in above file.

c) RegistryProgram.java – This is the main program that runs in an infinite loop.

2) SERVER PROGRAM – (IN com.rmi.server PACKAGE)

Com.rmi.server – The ChatServer is something that lets cleitns to Join Leave and
maintains a count of all ChatClients joined currently, it also needs to take care of
sending messages among the clients. Receiving message is implemented as a
functionality within the ChatClient. This package includes 2 files:

a) ChatServerInterface.java – This declares methods to be executed by the chat
Server.

b) ChatServer.java – This implements all the methods declared in above file.

3) CLIENT PROGRAM – (IN com.rmi.client PACKAGE)

Com.rmi.client – The ChatClient is describes the methods that clients does to
Join Leave. This package includes 3 files:
a) ChatClientInterface.java – The ReceiveMessage is the only method that is
implemented at the ChatClient side and that is declared here.

b) ChatClient.java – This implements the interface in ChatClientInterface.java, also, another method to call
the Server’s sendMessage when needed.

c) ChatClientMain.java – This is the file that needs to be executed on each Client’s
terminal.

