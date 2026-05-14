package net.amazonprep.journalApp.Controllers;

import net.amazonprep.JournalEntryService.UserServices;
import net.amazonprep.JournalEntryService.services;
import net.amazonprep.entity.JournalEntity;
import net.amazonprep.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class journalEntryController_2 {

    @Autowired
    private services journalService;

    @Autowired
    private UserServices userService;

    @GetMapping("{userName}")
    public ResponseEntity<?>  getallJounalEntries(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<JournalEntity> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("{userName}")
    public  ResponseEntity<?> putEntries(@RequestBody JournalEntity myentry, @PathVariable String userName){
        try {

            journalService.save(myentry, userName);
            return new ResponseEntity<>(myentry, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("id/{myid}")
    public ResponseEntity<?> getDetailsById(@PathVariable ObjectId myid){
        Optional<JournalEntity> journalEntry = journalService.findById(myid);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @DeleteMapping("id/{userName}/{myid}")
    public ResponseEntity<?> DeleteDetailsById(@PathVariable ObjectId myid,@PathVariable String userName){
        journalService.DeleteByid(myid, userName);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("id/{userName}/{myid}")
    public ResponseEntity<?> updateDetailsById(@PathVariable ObjectId myid , @RequestBody JournalEntity newEntry, @PathVariable String userName){
        try {
            JournalEntity old = journalService.findById(myid).orElse(null);
            if(old != null){
                old.setTitle(newEntry.getTitle() !=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            }
            journalService.save(old, userName);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
