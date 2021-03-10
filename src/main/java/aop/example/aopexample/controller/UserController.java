package aop.example.aopexample.controller;

import aop.example.aopexample.aop.PropagateSelectedAnnotation;
import aop.example.aopexample.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity<List<User>> users(){

        return ResponseEntity.ok(getUsersCatalog());
    }

    @PropagateSelectedAnnotation
    @GetMapping("/{name}/{email}")
    public ResponseEntity<User> getUser(@PathVariable String name, @PathVariable String email){

        return ResponseEntity.ok(new User(name,email));
    }

    private List<User> getUsersCatalog(){
        User user=new User("marian","marian@mail.com");
        User user1=new User("gabi","gabi@mail.com");
        return Arrays.asList(user,user1);
    }
}
