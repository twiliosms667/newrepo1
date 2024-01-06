package com.blogger4.blogger4.controller;

import com.blogger4.blogger4.entity.Role;
import com.blogger4.blogger4.entity.User;
import com.blogger4.blogger4.payload.JWTAuthResponse;
import com.blogger4.blogger4.payload.LoginDto;
import com.blogger4.blogger4.payload.SignUpDto;
import com.blogger4.blogger4.repositary.RoleRepository;
import com.blogger4.blogger4.repositary.UserRepositary;
import com.blogger4.blogger4.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepositary userRepositary;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;


    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }


    @PostMapping("/signup")


    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {

        if (userRepositary.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email id exists:" + signUpDto.getEmail(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (userRepositary.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username exists:" + signUpDto.getUsername(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));


        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));


        User saveduser = userRepositary.save(user);

        return new ResponseEntity<>("User Register Sucessfully!!", HttpStatus.OK);

    }



}
