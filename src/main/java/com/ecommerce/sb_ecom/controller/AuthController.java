package com.ecommerce.sb_ecom.controller;

import com.ecommerce.sb_ecom.model.Role;
import com.ecommerce.sb_ecom.model.User;
import com.ecommerce.sb_ecom.repository.UserRepository;
import com.ecommerce.sb_ecom.security.jwt.JwtUtils;
import com.ecommerce.sb_ecom.security.request.LoginRequest;
import com.ecommerce.sb_ecom.security.request.SignUpRequest;
import com.ecommerce.sb_ecom.security.response.MessageResponse;
import com.ecommerce.sb_ecom.security.response.UserInfoResponse;
import com.ecommerce.sb_ecom.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

public class AuthController
{
    @Autowired
    private LoginRequest loginRequest;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest)
    {
        Authentication authentication;
        try
        {
            authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        }catch (AuthenticationException exception)
        {
            Map<String,Object> map=new HashMap<>();
            map.put("message","Bad Credentials");
            map.put("status",false);

            return new ResponseEntity<Object>(map, HttpStatus.UNAUTHORIZED);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails= (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken=jwtUtils.generateTokenFromUserName(userDetails);

        List<String> roles=userDetails.getAuthorities().stream()
                .map(item->item.getAuthority())
                .collect(Collectors.toList());

        UserInfoResponse loginResponse=new UserInfoResponse(userDetails.getId(),jwtToken,userDetails.getUsername()
                ,roles);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest)
    {
        if(userRepository.existsByUsername(signUpRequest.getName()))
            return new ResponseEntity<>(new MessageResponse("The user is already present"),HttpStatus.BAD_REQUEST);

        if(userRepository.existsByEmail(signUpRequest.getName()))
            return new ResponseEntity<>(new MessageResponse("The user is already present"),HttpStatus.BAD_REQUEST);

        User user=new User(signUpRequest.getName(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        //understand that we need to persist the way the Database understands and not how the input we
        // have received.
        Set<String> strRoles=signUpRequest.getRoles();
        Set<Role>roles=new HashSet<>();


    }

}
