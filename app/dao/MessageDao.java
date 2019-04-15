package dao;


import models.Message;

import java.util.List;

public interface MessageDao {
	public List<Message> getMessageByFromId(int from);
	public List<Message> getMessageByToId(int to);
	public List<Message> getMessageUnReceive(int to);
	public List<Message> getMessageByType(int type);
	public void addMessage(Message message);
	public boolean deleteMessage(Message message);
	public boolean updateMessage(Message message);
}
