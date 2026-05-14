package net.amazonprep.entity;

import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User {

    @Id
   private ObjectId id;
    @Indexed(unique = true)
    @NonNull
   private String userName;

    @NonNull
    public String getPass() {
        return pass;
    }

    public void setPass(@NonNull String pass) {
        this.pass = pass;
    }

    @NonNull
    public String getUserName() {

        return userName;
    }

    public void setUserName(@NonNull String userName) {

        this.userName = userName;
    }

    public List<JournalEntity> getJournalEntries() {
        return journalEntries;
    }

    public void setJournalEntries(List<JournalEntity> journalEntries) {
        this.journalEntries = journalEntries;
    }

    @NonNull
   private String pass;
    @DBRef
    private List<JournalEntity> journalEntries = new ArrayList<>();

}
