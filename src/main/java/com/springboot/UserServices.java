package com.springboot.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

	UsersRepository usersRepository;
	
	@Autowired
    public UserServices(UsersRepository usersRepository) { 
      this.usersRepository = usersRepository;
    }
	
	public void saveUsers(Users users) {
		usersRepository.save(users);
	}


}
