package net.amazonprep.repository;

import com.mongodb.client.MongoDatabase;
import net.amazonprep.entity.JournalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface journalEntryRepo extends MongoRepository<JournalEntity, String> {

}

