package com.example.oauth2.adopter.in;

import com.example.oauth2.application.port.in.AccountCommand;
import com.example.oauth2.application.port.in.AccountCreateUseCase;
import com.example.oauth2.application.port.in.AccountDeleteUseCase;
import com.example.oauth2.application.port.in.AccountResponse;
import com.example.oauth2.application.port.in.AccountSearchUseCase;
import com.example.oauth2.application.port.in.AccountUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth/account")
@RequiredArgsConstructor
public class OAuthController {

    private final AccountCreateUseCase createUseCase;
    private final AccountSearchUseCase searchUseCase;
    private final AccountUpdateUseCase updateUseCase;
    private final AccountDeleteUseCase deleteUseCase;

    @PostMapping
    public ResponseEntity<AccountResponse> create(@RequestBody AccountCommand command) {
        return ResponseEntity.ok(createUseCase.createUser(command));
    }

    @PostMapping("/sns")
    public ResponseEntity<AccountResponse> createSnsUser(@RequestBody AccountCommand command) {
        return ResponseEntity.ok(createUseCase.createSnsUser(command));
    }

    @GetMapping
    public ResponseEntity<AccountResponse> getInfo(
        @RequestHeader(name = "Authorization") String accessToken) {
        return ResponseEntity.ok(searchUseCase.getInfo(accessToken));
    }

    @GetMapping(value = "/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserInfo(
        @RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(JwtHelper.decode(token.replace("Bearer ", "")).getClaims());
    }

    @PutMapping("/login")
    public ResponseEntity<AccountResponse> updateLoginTome(@RequestBody AccountCommand command) {
        return ResponseEntity.ok(updateUseCase.updateLoginTime(command));
    }

    @PutMapping
    public ResponseEntity<AccountResponse> update(
        @RequestHeader(name = "Authorization") String accessToken,
        @RequestBody AccountCommand command) {
        return ResponseEntity.ok(updateUseCase.update(accessToken, command));
    }

    @DeleteMapping
    public ResponseEntity<AccountResponse> delete(
        @RequestHeader(name = "Authorization") String accessToken) {
        return ResponseEntity.ok(deleteUseCase.delete(accessToken));
    }
}
