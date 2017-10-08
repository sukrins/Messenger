/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shrestha.javabrains.messenger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author sukrins
 */
public class MessageService {

MessageDatabase md = new MessageDatabase();
public List<Message> getAllMessages(){

return md.getAllMessages();
}

public List<Message> getAllMessagesForYear(int year) {
	List<Message> messagesForYear = new ArrayList<>();
	List<Message> messages = new ArrayList<>();
	messages = md.getAllMessages();
	Calendar cal = Calendar.getInstance();
	for(Message message : messages){
	cal.setTime(message.getCreated());
	if(cal.get(Calendar.YEAR) == year)
	messagesForYear.add(message);
	}
	return messagesForYear;
	}
public List<Message> getAllMessagesPaginated(int start, int size){
List<Message> list = new ArrayList<Message>(md.getAllMessages());
if(start + size > list.size())
return new ArrayList<>();
return list.subList(start, start+size);
}
public Message getMessage(long id){

    
	Message message = md.getMessage(id);
	if(message == null){
	throw new DataNotFoundException("Message with id "+id+" not found");
	}
	return message;
	}
	public Message addMessage(Message message){

	md.insertMessage(message);
	return message;
	}
	public Message updateMessage(Message message){
	if (message.getId() <= 0){
	return null;
	}

        md.updateMessage(message);
	return message;
	}
	public void removeMessage(long id){

            md.deleteMessage(id);
	}


		
	}
