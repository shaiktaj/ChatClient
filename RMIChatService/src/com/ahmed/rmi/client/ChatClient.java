package com.rmi.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import com.rmi.server.ChatServerInterface;

public class ChatClient extends UnicastRemoteObject implements ChatClientInterface{

	public String name = null;
	public ChatServerInterface chatServer;
	
	private volatile boolean running = true;

    public void terminate() {
        running = false;
    }
    
	protected ChatClient(String name, ChatServerInterface chatServer) throws RemoteException {
		this.name = name;
		this.chatServer = chatServer;
		chatServer.registerChatClient(this);
	}
	
	@Override
	public void receiveMessage(String message) throws RemoteException {
		System.out.println(message);
		
	}

	public void executing(){
		System.out.println("Please enter the Message to send: ");	
		Scanner s = new Scanner(System.in);
		String message;
	
	       	message = s.nextLine();
			
			try{
			
				chatServer.sendMessage(name + " : " + message);
			}
			catch(RemoteException e){
				e.printStackTrace();
			}
	}
}
