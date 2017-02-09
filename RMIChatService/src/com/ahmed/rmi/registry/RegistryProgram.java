package com.rmi.registry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Vector;

import com.rmi.server.ChatServer;

public class RegistryProgram {
	
	public static void main(String[] args) throws MalformedURLException, NotBoundException {
		
		int Option = 0;
		Scanner scanner = new Scanner(System.in);
		
		try{
			ChatProvider provider = new ChatProvider();
			Naming.rebind("Provider", provider);
			
			while(Option!= 3)
		       {
		       
		       System.out.println("Options Menu: Enter "); 
		       System.out.println("  0 - To List all Chat Rooms "); 
		       System.out.println("  1 - To Register a Chat Room"); 
		       System.out.println("  2 - To Unregister a Chat Room"); 
		       System.out.println("  3 - To Exit"); 
		       System.out.println("/n/n please Enter an Option: ");
		       Option = scanner.nextInt();
		       
		       switch(Option){
		               
		               // Listing all chatrooms under the provider
		       case 0: 
		    	    Vector chatRooms = provider.listChatRooms();
		    	    System.out.println("List of Chat rooms currently Registered: "); 
		            for(int i=0; i<chatRooms.size(); i++){ 
		            System.out.println(i + " : " + chatRooms.get(i));
		            } 
		    	   break;
		    	   
		    	       // Registering a Chat Room and initiating a Server
		       case 1:
		    	   System.out.println("Please enter a name for the ChatRoom: ");
		    	   String name = scanner.next();
		    	   scanner.nextLine();
		    	   System.out.println("Please give a description (in one word) for the Chat Room");
		    	   String des = scanner.next();
		    	   scanner.nextLine();
		    	   provider.registerChatRoom(name, des);
		    	   Naming.rebind(name, new ChatServer());
		    	   break;
		      
		    	       // Unregistering a ChatRoom
		       case 2: 
		    	   System.out.println("Please Enter the Chat Room you wish to Unregister: ");
		    	   String Name = scanner.next();
		    	   scanner.nextLine();
		    	   provider.unregisterChatRoom(Name); 
		    	   Naming.unbind(Name);
		    	   break;
		      
		    	       // Exiting the registration
		       case 3:
		    	   System.out.println("Exiting the Chat Provider Registry..");
		    	   break;
		  	   
		       default:
		    		   break;

		       }
		    }
		}
		
		catch(RemoteException e){
			e.printStackTrace();
		}
	}

}
