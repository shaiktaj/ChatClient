package com.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

import com.rmi.registry.ChatProviderInterface;
import com.rmi.server.ChatServerInterface;

public class ChatClientMain {
     
	 
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException{
		
		String rmiChatProviderURL = "rmi://localhost/Provider";
		ChatProviderInterface provider = (ChatProviderInterface) Naming.lookup(rmiChatProviderURL);
		
        //	String rmiChatServerURL = "rmi://localhost/RMIChatServer";
		//	ChatServerInterface chatServer = (ChatServerInterface) Naming.lookup(rmiChatServerURL);
			
		int Option = 0;
		Scanner scanner = new Scanner(System.in);
		   boolean setClient = false;
	       ChatClient chatClient = null;
	       Thread one;
	       ChatServerInterface chatServer = null;
	       HashMap<String, ArrayList> clientsList = new HashMap<String, ArrayList>();
	    
		try{
			while(Option !=5)
			{
			       System.out.println("Options Menu: Enter "); 
			       System.out.println("  0 - To List all Chat Rooms under the provider"); 
			       System.out.println("  1 - To Join a Chat Room"); 
			       System.out.println("  2 - To Leave a Chat Room"); 
			       System.out.println("  3 - To Send message in a ChatRoom");
			       System.out.println("  4 - To List all Clients under a given Chat Room");
			       System.out.println("  5 - To Exit"); 
			       System.out.println("/n/n please Enter an Option: ");
			       Option = scanner.nextInt();
			       
			       
			       switch(Option){
			       
			       case 0:    
		                    // Listing all chatrooms under the provider
//			      	System.out.println(System.nanoTime());
				    	    Vector chatRooms = provider.listChatRooms();
				    	    System.out.println("List of Chat rooms currently Registered: "); 
				            for(int i=0; i<chatRooms.size(); i++){ 
				            System.out.println(i + " : " + chatRooms.get(i));
				            } 
//				       	System.out.println(System.nanoTime());		
				    	   break;
			    	   
			    	        // To Join a ChatRoom
			       case 1:
			    	        
			    	        System.out.println("Please enter the ChatRoom name to Join: ");
			    	   		String name = scanner.next();
			    	   		Vector ChatRooms = provider.listChatRooms();
			    	  
			    	   		if(ChatRooms.contains(name))
			    	   		{
			    	   			String rmiChatServerURL = "rmi://localhost/" +name;
				    			chatServer = (ChatServerInterface) Naming.lookup(rmiChatServerURL);	
				    			
				    			System.out.println("Please enter the Chat Client name: ");
					    	   	String clientName = scanner.next();
					    	//   	System.out.println(System.nanoTime());
					    	   	System.out.println("Connecting to ChatRoom "+ "....");
					    	   	chatClient = new ChatClient(clientName, chatServer);
					    	  
					    	   	// For use while messaging
					    	   	setClient = true;
					    	   	
					    	   	// Adding this Client to ClientRoom's Client List
					    	   	
					    	   	if(clientsList.containsKey(name)){
					    	   		ArrayList<String> s = clientsList.get(name);
					    	   		s.add(clientName);
					    	   		clientsList.remove(name);
					    	   		clientsList.put(name,s);
					    	   	}
					    	   	else{
			    		    		ArrayList<String> s1 = new ArrayList<>();
			    		    		s1.add(clientName);
			    		    		clientsList.put(name,s1);
			    		    	}
					    	   	
				    		    System.out.println(clientName + " joined the Chatroom : " + name);
			    	   		}
			    	   		else{
			    	   			System.out.println("Entered ChatRoom Unavailable!" );
			    	   		}
			    	   		
			    	   break; 
			       
			       case 2:
			    	       // To leave a Chat Room
			    	      
			    	    System.out.println("Please enter the ChatRoom name to Leave: ");
		    	   		
			    	    String Name = scanner.next();
		    	   		Vector ChatRoomList = provider.listChatRooms();
		    	   		
		    	   		if(ChatRoomList.contains(Name))
		    	   		{
		    	   		
			    			chatServer.unregisterChatClient(chatClient);
			    			
			    			// for use in messaging
			    			setClient = false;
			    			
			    			// delete from chatrooms's list of cleints
			    			
			    		    	if(clientsList.containsKey(Name)){
				    	   		ArrayList<String> s = clientsList.get(Name);
				    	   		s.remove(chatClient.name);
				    	   		clientsList.remove(Name);
				    	   		clientsList.put(Name,s);
				    	   	}
			    		    	
			    			
			    			System.out.println("Successfully Unregistered the Client: "+ chatClient.name);
		    	   		}
		    	   		else{
		    	   			System.out.println("Entered ChatRoom Unavailable!" );
		    	   		}
		    	   	//	System.out.println(System.nanoTime());	
			    	   break;
			    	   
			       case 3:  //Send Message In Chat
			    	   if(setClient){
			    		
			    		   chatClient.executing();
			    		   	   }
			    	   else{
			    		   System.out.println("You have not joined any ChatRoom yet!");
			    	   }
			    	   break;
			    	   
			       case 4:
			    	     // To display List of regsitered Clients for a given ChatRoom
			    	   
			    	   System.out.println("Please enter the ChatRoom Name to get its List of Clients: ");
			    	   String roomName = scanner.next();
			    	   
			    	   if(clientsList.containsKey(roomName)){
			    		   
			    	   		ArrayList<String> s = clientsList.get(roomName);
			    	   		System.out.println("Clients currently under Chatroom : " +roomName );
			    	   		for(String str : s){
			    	   			System.out.println(str);
			    	   		}
			    	   	}
			    	   else{
			    		   System.out.println("Entered ChatRoom doesn't have Clients/not valid!");
			    	   }
			    	   
			    	   
			    	   break;
			    	   
			       default:
			    		   break;
			    	   
			       }
		  }
		}
			catch(Exception e){
				e.printStackTrace();
			}	
	}	
	
}
