package service;



import models.User;

import java.util.List;

public interface UserService {
		//���name��ȡ�û�
		public User getUser(int id);
		//���name��ȡ�û�
		public User getUser(String name);
		//����û�
		public void addUser(User user);
		//ɾ���û�
		public boolean deleteUser(int id);
		//�����û�
		public boolean updateUser(User user);
		//��ȡ�����û�
		public List<User> getAllUser(); 
}
