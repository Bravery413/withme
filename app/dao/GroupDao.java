package dao;


import models.Group;

public interface GroupDao {
	public Group getGroup(int id);
	public Group getGroup(String groupId);
	public void addGroup(Group group);
	public boolean deleteGroup(int id);
	public boolean updateGroup(Group group);
}