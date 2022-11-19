package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.User;
@Repository
public interface UserRepo  extends JpaRepository<User	, Integer> {
	
	
	public User findByUserNameAndPassword(String userName, String password);

	public User findByEmail(String email);

}
