package com.rmi.registry;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface ChatProviderInterface extends Remote {
	
	public int registerChatRoom(String name, String info) throws RemoteException;
	public int unregisterChatRoom(String name)throws RemoteException;
	public String getChatRoomInfo(String name) throws RemoteException;
	public Vector listChatRooms() throws RemoteException;


}
