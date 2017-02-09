package com.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.rmi.client.ChatClient;
import com.rmi.client.ChatClientInterface;

public interface ChatServerInterface extends Remote{
	void registerChatClient(ChatClientInterface chatClient) throws RemoteException;
	void unregisterChatClient(ChatClientInterface chatClient) throws RemoteException;
	void sendMessage(String message) throws RemoteException;
	
}
