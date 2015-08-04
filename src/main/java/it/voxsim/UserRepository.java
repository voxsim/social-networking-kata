package it.voxsim;

public interface UserRepository {

	User findByUsername(String username);

	void save(String username);

}
