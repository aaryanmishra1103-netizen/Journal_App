package net.amazonprep.JournalEntryService;

import net.amazonprep.entity.User;
import net.amazonprep.repository.UserRespository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServices {
    @Autowired
    private UserRespository UserRepo;


    public void save(User user){

        UserRepo.save(user);
    }
    public List<User> getAll(){

        return UserRepo.findAll();
    }
    public Optional<User> findById(ObjectId id){

        return UserRepo.findById(String.valueOf(id));
    }
    public void DeleteByid(ObjectId id){

        UserRepo.deleteById(String.valueOf(id));
    }
    public User findByUserName(String userName){
        return UserRepo.findByUserName(userName);
    }

}
