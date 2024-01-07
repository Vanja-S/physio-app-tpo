package com.tpo.fizio.rest.application;

import com.tpo.fizio.entity.fizioplan.model.FizioplanActionInformation;
import com.tpo.fizio.entity.fizioplan.model.FizioplanDto;
import com.tpo.fizio.entity.fizioterapevt.impl.service.FizioterapevtService;
import com.tpo.fizio.entity.fizioterapevt.model.FizioterapevtActionInformation;
import com.tpo.fizio.entity.fizioterapevt.model.FizioterapevtDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tpo.fizio.rest.util.RestConstants.FIZIOPLAN;
import static com.tpo.fizio.rest.util.RestConstants.FIZIOTERAPEVT;

/**
 * @author Tadej Delopst
 */
@RestController
@RequestMapping("rest/v1/fizioterapevti")
public class FizioterapevtiController {
    private FizioterapevtService fizioterapevtService;

    @Autowired
    public FizioterapevtiController(FizioterapevtService fizioterapevtService) {
        this.fizioterapevtService = fizioterapevtService;
    }

    @Operation(summary = "GET Fizioterapevti",
            description = "<p>Vrne vse fizioterapevte, ki obstajajo. V primeru, da fizioterapevtov ni, je rezultat prazen.</p>",
            tags = FIZIOTERAPEVT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/")
    @Secured("ROLE_PACIENT")
    public ResponseEntity<List<FizioterapevtDto>> getFizioterapevts(
    ) {
        List<FizioterapevtDto> fizioterapevts = fizioterapevtService.getFizioterapevts();
        if (fizioterapevts == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(fizioterapevts);
    }

    @Operation(summary = "GET Fizioterapevt",
            description = "<p>Vrne Fizioterapevta, če ta obstaja. V primeru, da Fizioterapevt ne obstaja je odgovor prazen.</p>",
            tags = FIZIOTERAPEVT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/{username}")
    @Secured("ROLE_PACIENT")
    public ResponseEntity<FizioterapevtDto> getFizioplan(
            @PathVariable("username") String fizioterapevtUsername
    ) {
        FizioterapevtDto fizioterapevt = fizioterapevtService.getFizioterapevt(fizioterapevtUsername);
        if (fizioterapevt == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(fizioterapevt);
    }

    @Operation(summary = "UPDATE NOV NASLOV",
            description = "<p>NOV OPIS</p>",
            tags = FIZIOTERAPEVT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    //dolocis novo pot v value = {NOVA POT}, consumes, produces ostane isto, načeloma za vse samo "/"
    @PutMapping( value = "/", consumes = "application/json", produces = "application/json" )
    @Secured({"ROLE_PACIENT"}) //ostane nespremenjeno
    public ResponseEntity<FizioterapevtActionInformation> updateFizioterapevt( //novo ime metode
                                                                       @RequestBody FizioterapevtDto dto //V tistmu kontrolerju ko si tist objekt VnosiController -> VnosDto
    ) {
        //1. če še ne obstaja ActionInformation za objekt ustvariš class na poti entity/{objekt}/model
        //2. Za service kreiraš novo metodo update{Objekt}(dto)
        FizioterapevtActionInformation information = fizioterapevtService.updateFizioterapevt(dto);
        return ResponseEntity.ok(information);
    }
}
