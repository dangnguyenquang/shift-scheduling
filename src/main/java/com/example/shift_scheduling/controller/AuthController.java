package com.example.shift_scheduling.controller;

import com.example.shift_scheduling.dto.request.AuthDTO;
import com.example.shift_scheduling.dto.response.ResponseData;
import com.example.shift_scheduling.security.CustomUserDetailsService;
import com.example.shift_scheduling.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseData<?> login(@RequestBody AuthDTO request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return new ResponseData<>(HttpStatus.UNAUTHORIZED.value(), "Invalid username or password", null);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        Map<String, String> data = new HashMap<>();
        data.put("token", jwt);

        return new ResponseData<>(HttpStatus.OK.value(), "Login successfully", data);
    }
}

