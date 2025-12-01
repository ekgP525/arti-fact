package ajou.artifact.arti_fact.controller;

import ajou.artifact.arti_fact.service.LikedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ajou.artifact.arti_fact.entity.Users;
import ajou.artifact.arti_fact.service.UserServices;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserServices userServices;
    private final LikedService likedService;

    public UserController(UserServices userServices, LikedService likedService) {
        this.userServices = userServices;
        this.likedService = likedService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable Long userId) {
        Users user = userServices.getUserById(userId);
        
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.ok(user);
    }
}
