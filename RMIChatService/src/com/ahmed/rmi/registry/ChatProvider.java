package com.rmi.registry;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import com.rmi.server.ChatServerInterface;

public class ChatProvider  extends UnicastRemoteObject implements ChatProviderInterface {

	// Since we need a synchronized arraylist between threads
	public Vector ChatRooms;
	public Vector information;
	
	protected ChatProvider() throws RemoteException {
		super();
		ChatRooms = new Vector();
		information = new Vector();
	}

	// Registers chat room and adds the Room name to List of ChatRooms under this Provider
	@Override
	public int registerChatRoom(String name, String info) throws RemoteException {
		
		ChatRooms.add(name);
		information.add(info);
		System.out.println("Successfully registered Chat Room : " + name );
		return 0;
	}
	
	// Unregisters the chat room and deletes the Room name from List of ChatRooms under this Provider
	@Override
	public int unregisterChatRoom(String name) throws RemoteException {
		
		information.remove(ChatRooms.indexOf(name));
		ChatRooms.remove(ChatRooms.indexOf(name));
	
		System.out.println("Successfully Unregistered the Chat Room :" + name);
		return 0;
	}
	

    // Given a ChatRoom name, returns its information
	@Override
	public String getChatRoomInfo(String name) throws RemoteException {
		
		return (String) information.get(ChatRooms.indexOf(name));
	}
	
	@Override
	// Returns List of all ChatRooms registered under this provider
		public Vector listChatRooms() {
			return ChatRooms;
		}

		// Returns List of all registered ChatRooms information  under this provider
				public Vector listChatRoomInfo() {
					return information;
				}
}
