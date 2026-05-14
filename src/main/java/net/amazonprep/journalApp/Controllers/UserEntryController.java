package net.amazonprep.journalApp.Controllers;

import net.amazonprep.JournalEntryService.UserServices;
import net.amazonprep.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntryController {


    @Autowired
    private UserServices userService;


    @GetMapping
    public List<User> getAllUsers(){
       return userService.getAll();
   }
   @PostMapping
     public void createUser(@RequestBody User user){
        userService.save(user);
   }
   @DeleteMapping
    public void deleteUser(@PathVariable ObjectId id){
        userService.DeleteByid(id);
   }
   @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User userEntry,@PathVariable String username){
        User userdb=  userService.findByUserName(username);
        if(userdb != null){
            userdb.setUserName(userEntry.getUserName());
            userdb.setPass(userEntry.getPass());
            userService.save(userdb);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}
