package com.tpo.fizio.rest.application;

import com.tpo.fizio.entity.pacient.impl.dao.PacientDao;
import com.tpo.fizio.entity.pacient.impl.service.PacientService;
import com.tpo.fizio.entity.pacient.model.Pacient;
import com.tpo.fizio.entity.pacient.model.PacientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.tpo.fizio.rest.util.RestConstants.DEMO_AUTHORIZED;
import static com.tpo.fizio.rest.util.RestConstants.DEMO_UNAUTHORIZED;

/**
 * @author Tadej Delopst
 */

@RestController
@RequestMapping("/rest/v1")
public class ApplicationController {

    private PacientService pacientService;

    @Autowired
    public ApplicationController(PacientService pacientService) {
        this.pacientService = pacientService;
    }

    @Operation(summary = "Says hello to all users.",
               description = "<p>Says hello to all users. User doesn't have to be authenticated.</p>",
               tags = DEMO_UNAUTHORIZED)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully said hello."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/app")
    public ResponseEntity<List<PacientDto>> helloUnauthorized() {
        List<PacientDto> pacienti = pacientService.getAllPacients();
        return ResponseEntity.ok(pacienti);
    }



    @Operation(summary = "Says hello only to authorized users.",
               description = "<p>Says hello only to authorized users. User has to be authorized and get a valid token from keycloak.</p>",
               tags = DEMO_AUTHORIZED)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully said hello."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/user")
    @Secured({"ROLE_ADMIN", "ROLE_READ", "ROLE_WRITE"})
    public ResponseEntity<DemoDto> helloAuthorized() {
        DemoDto dto = new DemoDto("Hello Authorized World:)");
        return ResponseEntity.ok(dto);
    }
}
