package com.example.serviceUser.adopter.in;

import com.example.serviceUser.application.port.in.AccountCommand;
import com.example.serviceUser.application.port.in.AccountCreateUseCase;
import com.example.serviceUser.application.port.in.AccountDeleteUseCase;
import com.example.serviceUser.application.port.in.AccountResponse;
import com.example.serviceUser.application.port.in.AccountSearchUseCase;
import com.example.serviceUser.application.port.in.AccountUpdateUseCase;
import com.example.serviceUser.application.port.out.AccountDeletePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AccountController {

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
    public ResponseEntity<AccountResponse> getInfo(@RequestHeader(name = "Authorization") String accessToken) {
        return ResponseEntity.ok(searchUseCase.getInfo(accessToken));
    }

    @PutMapping
    public ResponseEntity<AccountResponse> update(@RequestHeader(name = "Authorization") String accessToken, @RequestBody AccountCommand command) {
        return ResponseEntity.ok(updateUseCase.update(accessToken, command));
    }

    @DeleteMapping
    public ResponseEntity<AccountResponse> delete(@RequestHeader(name = "Authorization") String accessToken) {
        return ResponseEntity.ok(deleteUseCase.delete(accessToken));
    }
}
