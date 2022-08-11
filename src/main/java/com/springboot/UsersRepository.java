package com.springboot.in;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	
	Optional<Users> findByEmail(String email);
	
	static Users findByidIgnoreCase(long Id) 
	{
		return null;
	}

	boolean existsByPhoneNo(long phoneNo);
}
