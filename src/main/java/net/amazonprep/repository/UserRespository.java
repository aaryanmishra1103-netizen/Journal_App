package net.amazonprep.repository;

import net.amazonprep.entity.JournalEntity;
import net.amazonprep.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRespository extends MongoRepository<User, String> {
   User findByUserName(String userName);
}

