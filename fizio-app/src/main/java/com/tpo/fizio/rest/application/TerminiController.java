package com.tpo.fizio.rest.application;

import com.tpo.fizio.entity.termin.impl.service.TerminService;
import com.tpo.fizio.entity.termin.model.TerminActionInformation;
import com.tpo.fizio.entity.termin.model.TerminDto;
import com.tpo.fizio.entity.vnos.model.VnosActionInformation;
import com.tpo.fizio.entity.vnos.model.VnosDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.tpo.fizio.rest.util.RestConstants.TERMIN;
import static com.tpo.fizio.rest.util.RestConstants.VNOS;

/**
 * @author Tadej Delopst
 */
@RestController
@RequestMapping("rest/v1/termini")
public class TerminiController {
    private TerminService terminService;

    @Autowired
    public TerminiController(TerminService terminService) {
        this.terminService = terminService;
    }

    @Operation(summary = "GET prosti Termini za določen datum",
            description = "<p>Vrne proste Termine, ki so jih razpisali fizioterapevti za določen datum. " +
                    "V primeru da ni prostih terminov je rezultat prazen</p>",
            tags = TERMIN)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "400", description = "Bad Request - invalid date format used.", content = @Content),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/{username}/available")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<List<TerminDto>> getAvailableTerminiForPacientOnGivenDate(
            @Parameter(example = "wy4833")
            @PathVariable("username") String username,
            @Parameter(example = "2024-01-15")
            @RequestParam String izbranDatum
    ) {
        LocalDate datum = null;
        try {
            datum = LocalDate.parse(izbranDatum);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        List<TerminDto> prostiTermini = terminService.getAvailableTerminiOnGivenDate(datum, username);
        if (prostiTermini == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(prostiTermini);
    }

    @Operation(summary = "UPDATE zasedenost Termina - Rezerviraj",
            description = "<p>Nastavi zasedenost Termina na true in mu pripiši pacienta, ki si je rezerviral ta termin. " +
                    "V primeru, da termin ali pacient ne obstajata je rezultat viden v odgovoru.</p>",
            tags = TERMIN)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @PutMapping( value = "/termini/{terminID}/book", produces = "application/json")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<TerminActionInformation> bookTerminForPacient(
            @PathVariable("terminID") Integer terminId,
            @RequestParam String pacientUsername
    ) {
        TerminActionInformation information = terminService.bookTermin(terminId, pacientUsername);
        return ResponseEntity.ok(information);
    }
    @Operation(summary = "UPDATE zasedenost Termina - Prekliči",
            description = "<p>Nastavi zasedenost Termina na false in mu izbriši pacienta, ki je rezerviral ta termin. " +
                    "V primeru, da termin ali pacient ne obstajata je rezultat viden v odgovoru.</p>",
            tags = TERMIN)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @PutMapping( value = "/termini/{terminID}/cancel", produces = "application/json")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<TerminActionInformation> cancelTerminForPacient(
            @PathVariable("terminID") Integer terminId
    ) {
        TerminActionInformation information = terminService.cancelTermin(terminId);
        return ResponseEntity.ok(information);
    }

    @Operation(summary = "GET Termini",
            description = "<p>Vrne vse termine, ki obstajajo. V primeru da terminov ni, je rezultat prazen</p>",
            tags = TERMIN)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<List<TerminDto>> getTermini(
    ) {
        List<TerminDto> termini = terminService.getTermini();
        if (termini == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(termini);
    }

    @Operation(summary = "GET Termin",
            description = "<p>Vrne termin. V primeru da termina ni, je rezultat prazen</p>",
            tags = TERMIN)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/{terminID}")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<TerminDto> getTermin(
            @PathVariable("terminID") Integer terminId
    ) {
        TerminDto termin = terminService.getTermin(terminId);
        if (termin == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(termin);
    }

    @Operation(summary = "UPDATE NOV NASLOV",
            description = "<p>NOV OPIS</p>",
            tags = TERMIN)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    //dolocis novo pot v value = {NOVA POT}, consumes, produces ostane isto, načeloma za vse samo "/"
    @PutMapping( value = "/", consumes = "application/json", produces = "application/json" )
    @Secured({"ROLE_PACIENT"}) //ostane nespremenjeno
    public ResponseEntity<TerminActionInformation> updateTermin( //novo ime metode
                                                             @RequestBody TerminDto dto //V tistmu kontrolerju ko si tist objekt VnosiController -> VnosDto
    ) {
        //1. če še ne obstaja ActionInformation za objekt ustvariš class na poti entity/{objekt}/model
        //2. Za service kreiraš novo metodo update{Objekt}(dto)
        TerminActionInformation information = terminService.updateTermin(dto);
        return ResponseEntity.ok(information);
    }
}
