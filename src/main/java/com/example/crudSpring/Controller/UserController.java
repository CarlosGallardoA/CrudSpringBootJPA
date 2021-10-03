package com.example.crudSpring.Controller;

import com.example.crudSpring.Entity.User;
import com.example.crudSpring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
    // Read an user
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id){
        Optional<User> oUser = userService.findById(id);
        if (oUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oUser);
    }
    //Update an User
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable Long id){
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        // For all data
        //BeanUtils.copyProperties(userDetails, user.get());
        user.get().setName(userDetails.getName());
        user.get().setLastname(userDetails.getLastname());
        user.get().setEmail(userDetails.getEmail());
        user.get().setEnabled(userDetails.isEnabled());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));
    }
    // Delete an User
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if (userService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Read all Users
    @GetMapping
    public List<User> readAll(){
        List<User> users = StreamSupport.stream(userService.findAll().spliterator(),false).collect(Collectors.toList());
        return users;
    }
}
