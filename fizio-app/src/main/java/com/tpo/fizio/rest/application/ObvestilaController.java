package com.tpo.fizio.rest.application;

import com.tpo.fizio.entity.obvestilo.impl.service.ObvestiloService;
import com.tpo.fizio.entity.obvestilo.model.ObvestiloActionInformation;
import com.tpo.fizio.entity.obvestilo.model.ObvestiloDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tpo.fizio.rest.util.RestConstants.OBVESTILO;

/**
 * @author Tadej Delopst
 */
@RestController
@RequestMapping("rest/v1/obvestila")
public class ObvestilaController {
    private ObvestiloService obvestiloService;

    @Autowired
    public ObvestilaController(ObvestiloService obvestiloService) {
        this.obvestiloService = obvestiloService;
    }

    @Operation(summary = "GET Obvestila",
            description = "<p>Vrne vsa Obvestila, ki obstajajo. V primeru, da Obvestil ni, je rezultat prazen.</p>",
            tags = OBVESTILO)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/")
    @Secured("ROLE_PACIENT")
    public ResponseEntity<List<ObvestiloDto>> getObvestila(
    ) {
        List<ObvestiloDto> obvestila = obvestiloService.getObvestila();
        if (obvestila == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(obvestila);
    }

    @Operation(summary = "GET Obvestilo",
            description = "<p>Vrne Obvestilo, če ta obstaja. V primeru, da Obvestilo ne obstaja je odgovor prazen.</p>",
            tags = OBVESTILO)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/{obvestiloID}")
    @Secured("ROLE_PACIENT")
    public ResponseEntity<ObvestiloDto> getObvestilo(
            @PathVariable("obvestiloID") Integer obvestiloId
    ) {
        ObvestiloDto obvestilo = obvestiloService.getObvestilo(obvestiloId);
        if (obvestilo == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(obvestilo);
    }

    @Operation(summary = "UPDATE Obvestilo",
            description = "<p>Posodobi Obvestilo, če ta obstaja po podanem identifierju v requestu. " +
                    "V primeru, da Obvestilo ne obstaja je rezultat prazen.</p>",
            tags = OBVESTILO)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @PutMapping(value = "/", consumes = "application/json", produces = "application/json")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<ObvestiloActionInformation> updateObvestilo(
            @RequestBody ObvestiloDto dto
    ) {
        ObvestiloActionInformation information = obvestiloService.updateObvestilo(dto);
        return ResponseEntity.ok(information);
    }
}
