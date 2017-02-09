package com.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;

import com.rmi.client.ChatClient;
import com.rmi.client.ChatClientInterface;

public class ChatServer extends UnicastRemoteObject implements ChatServerInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ChatClientInterface> chatClients;
	
	
	public ChatServer() throws RemoteException {
	
		chatClients = new ArrayList<ChatClientInterface>();
	
	}

	@Override
	public synchronized void registerChatClient(ChatClientInterface chatClient) 
			throws RemoteException {
		this.chatClients.add(chatClient);
	
	}
	
	
	public synchronized void unregisterChatClient(ChatClientInterface chatClient) 
			throws RemoteException {
		this.chatClients.remove(this.chatClients.indexOf(chatClient));
	
	}

	public synchronized void currentlyRegisteredClients(){
		int i=0;
		Vector clients;
		while(i<chatClients.size()){
			i++;
		//	clients.add(chatClients.get(i).toString());
		}
	}
	
	
	@Override
	public synchronized void sendMessage(String message) throws RemoteException {
		int i = 0;
		while(i<chatClients.size())
		{
			chatClients.get(i).receiveMessage(message);
			i++;
		}
		
	}


}
