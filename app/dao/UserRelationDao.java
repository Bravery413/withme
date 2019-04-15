package dao;



import models.UserRelation;

import java.util.List;

public interface UserRelationDao {
	//���id��ȡ�û�
	public UserRelation getUserRelation(int idA, int idB);
	//����û�
	public void addUserRelation(UserRelation userRelation);
	//ɾ���û�
	public boolean deleteUserRelation(int idA, int idB);
	//�����û�
	public boolean updateUser(UserRelation userRelation);
	//��ȡ�����û�
	@SuppressWarnings("rawtypes")
	public List getAllFriends(int id);
}
