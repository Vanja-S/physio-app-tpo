package com.tpo.fizio.rest.application;

import com.tpo.fizio.entity.obvestilo.impl.service.ObvestiloService;
import com.tpo.fizio.entity.obvestilo.model.ObvestiloActionInformation;
import com.tpo.fizio.entity.obvestilo.model.ObvestiloDto;
import com.tpo.fizio.entity.termin.model.TerminActionInformation;
import com.tpo.fizio.entity.vnos.model.VnosActionInformation;
import com.tpo.fizio.entity.vnos.model.VnosDto;
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
import static com.tpo.fizio.rest.util.RestConstants.TERMIN;

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

    @Operation(summary = "UPDATE NOV NASLOV",
            description = "<p>NOV OPIS</p>",
            tags = OBVESTILO)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    //dolocis novo pot v value = {NOVA POT}, consumes, produces ostane isto, načeloma za vse samo "/"
    @PutMapping( value = "/", consumes = "application/json", produces = "application/json" )
    @Secured({"ROLE_PACIENT"}) //ostane nespremenjeno
    public ResponseEntity<ObvestiloActionInformation> updateObvestilo( //novo ime metode
                                                                       @RequestBody ObvestiloDto dto //V tistmu kontrolerju ko si tist objekt VnosiController -> VnosDto
    ) {
        //1. če še ne obstaja ActionInformation za objekt ustvariš class na poti entity/{objekt}/model
        //2. Za service kreiraš novo metodo update{Objekt}(dto)
        ObvestiloActionInformation information = obvestiloService.updateObvestilo(dto);
        return ResponseEntity.ok(information);
    }
}
