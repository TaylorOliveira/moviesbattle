package br.com.letscode.moviesbattle.api.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import br.com.letscode.moviesbattle.api.model.payload.response.MessageResponse;
import br.com.letscode.moviesbattle.api.model.payload.response.LoginResponse;
import br.com.letscode.moviesbattle.api.model.payload.request.SignupRequest;
import br.com.letscode.moviesbattle.api.model.payload.request.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import br.com.letscode.moviesbattle.core.security.service.LoggedInUser;
import org.springframework.security.core.context.SecurityContextHolder;
import br.com.letscode.moviesbattle.domain.repository.RoleRepository;
import br.com.letscode.moviesbattle.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import br.com.letscode.moviesbattle.core.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.letscode.moviesbattle.domain.model.enums.ERole;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;
import br.com.letscode.moviesbattle.domain.model.Role;
import br.com.letscode.moviesbattle.domain.model.User;
import org.springframework.http.ResponseEntity;
import java.util.stream.Collectors;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(value ="/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        LoggedInUser loggedInUser = (LoggedInUser) authentication.getPrincipal();

        return ResponseEntity.ok(convertToLoginResponse(jwt, loggedInUser, getRoles(loggedInUser)));
    }

    private List<String> getRoles(LoggedInUser loggedInUser) {
        return loggedInUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    private LoginResponse convertToLoginResponse(String jwt, LoggedInUser userDetails, List<String> roles) {
        return LoginResponse.builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(MessageResponse.builder()
                            .message("Error: Username is already taken!")
                            .build());
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(MessageResponse.builder()
                            .message("Error: Email is already in use!")
                            .build());
        }

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .build();

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByRole(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByRole(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByRole(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByRole(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(MessageResponse.builder()
                .message("User registered successfully!")
                .build());
    }
}
