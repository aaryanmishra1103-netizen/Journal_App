package net.amazonprep.JournalEntryService;

import net.amazonprep.entity.JournalEntity;
import net.amazonprep.entity.User;
import net.amazonprep.repository.journalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class services {
    @Autowired
    private journalEntryRepo journalRepo;

    @Autowired
    private UserServices userServices;


    public void save(JournalEntity journalEntry, String userName){
        User user = userServices.findByUserName(userName);
        JournalEntity saved = journalRepo.save(journalEntry);
        user.getJournalEntries().add(saved);
        userServices.save(user);

    }

    public void save(JournalEntity journalEntry){
        journalRepo.save(journalEntry);

    }
    public List<JournalEntity> getAll(){

        return journalRepo.findAll();
    }
    public Optional<JournalEntity> findById(ObjectId id){
        return journalRepo.findById(String.valueOf(id));
    }
    public void DeleteByid(ObjectId id, String userName){
        User user = userServices.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userServices.save(user);
        journalRepo.deleteById(String.valueOf(id));
    }

}
