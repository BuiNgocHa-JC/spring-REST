package org.example.springrest.controller;

import org.example.springrest.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserRestController {
    public static HashMap<Integer, User> userHashMap = new HashMap<Integer, User>();

    static {
        userHashMap.put(1, new User(1, "John", "John@gmail.com", "HN"));
        userHashMap.put(2, new User(2, "Cassy", "Cassy@gmail.com", "USA"));
        userHashMap.put(3, new User(3, "Tim", "Tim@gmail.com", "UK"));
    }

//    GET ALL USER

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userList = new ArrayList<User>(userHashMap.values());
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

//    GET USER BY ID

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserById(@PathVariable int id) {
        User user = userHashMap.get(id);
        if (user != null) {
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        }
        return new ResponseEntity<Object>("Not found", HttpStatus.NO_CONTENT);
    }

    // CREATE NEW USER

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> createNewUser(@RequestBody User user) {
        if (userHashMap.containsKey(user.getId())) {
            return new ResponseEntity<String>("User already exist", HttpStatus.OK);
        } else {
            userHashMap.put(user.getId(), user);
            return new ResponseEntity<String>("User Created", HttpStatus.OK);
        }
    }

    // DELETE USER

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
        User user = userHashMap.get(id);
        if (user != null){
            userHashMap.remove(id);
            return new ResponseEntity<Object>(user,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Object>("Not Found User", HttpStatus.CONFLICT);
        }
    }

    // UPDATE USER

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        User oldUser = userHashMap.get(user.getId());
        if (oldUser == null){
            return new ResponseEntity<Object>("Not found User", HttpStatus.OK);
        }
        else {
            userHashMap.put(user.getId(),user);
            return new ResponseEntity<Object>("Updated", HttpStatus.OK);
        }
    }




}
