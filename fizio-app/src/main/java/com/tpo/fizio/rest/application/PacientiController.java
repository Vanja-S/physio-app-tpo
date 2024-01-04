package com.tpo.fizio.rest.application;

import com.tpo.fizio.entity.fizioplan.impl.service.FizioplanService;
import com.tpo.fizio.entity.fizioplan.model.FizioplanDto;
import com.tpo.fizio.entity.obvestilo.impl.service.ObvestiloService;
import com.tpo.fizio.entity.obvestilo.model.Obvestilo;
import com.tpo.fizio.entity.obvestilo.model.ObvestiloDto;
import com.tpo.fizio.entity.pacient.impl.service.PacientService;
import com.tpo.fizio.entity.termin.impl.service.TerminService;
import com.tpo.fizio.entity.termin.model.TerminDto;
import com.tpo.fizio.entity.vnos.model.VnosDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.tpo.fizio.rest.util.RestConstants.PACIENT;

/**
 * @author Tadej Delopst
 */
@RestController
@RequestMapping("rest/v1/pacienti")
public class PacientiController {
    private FizioplanService fizioplanService;
    private TerminService terminService;
    private ObvestiloService obvestiloService;

    @Autowired
    public PacientiController(FizioplanService fizioplanService, TerminService terminService, ObvestiloService obvestiloService) {
        this.fizioplanService = fizioplanService;
        this.terminService = terminService;
        this.obvestiloService = obvestiloService;
    }

    @Operation(summary = "GET Fizioplan meta data za Pacienta",
            description = "<p>Meta podatki zadnjega Fizioplana pacienta. Namenjeno osnovni predstavitvi fizioplana po prijavi." +
                    " V primeru da username pacienta ni pravi ali pacient nima valid fizioplana je rezultat prazen</p>",
            tags = PACIENT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/{username}/fizioplan")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<FizioplanDto> getPacientsFizioplanMeta(
            @PathVariable("username") String username
    ) {
        FizioplanDto pacientFizioplan = fizioplanService.getPacientFizioplan(username);
        if (pacientFizioplan == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(pacientFizioplan);
    }

    @Operation(summary = "GET naslednji pregled za Pacienta",
            description = "<p>Pridobi naslednji pregled pacienta, ki je starejši od trenutnega časa. " +
                    "V primeru, da termin ne obstaja je vrnjen prazen rezultat</p>",
            tags = PACIENT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/{username}/termini/next")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<TerminDto> getNextPregledForPacient(
            @PathVariable("username") String username
    ) {
        TerminDto nextTermin = terminService.getNextTerminForPacient(username);
        if (nextTermin == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(nextTermin);
    }

    @Operation(summary = "GET vse Termine Pacienta",
            description = "<p>Pridobi vse termine, ki so bili ustvarjeni za pacienta. " +
                    "V primeru, da pacient nima terminov je odgovor prazen</p>",
            tags = PACIENT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/{username}/termini")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<List<TerminDto>> getTerminiPacienta(
            @PathVariable("username") String username
    ) {
        List<TerminDto> vsiTermini = terminService.getAllPacientTermini(username);
        if (vsiTermini == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(vsiTermini);
    }

    @Operation(summary = "GET vse Fizioplane Pacienta",
            description = "<p>Pridobi vse Fizioplane, ki so bili ustvarjeni za pacienta. " +
                    "V primeru, da pacient nima fizioplanov je odgovor prazen</p>",
            tags = PACIENT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/{username}/fizioplani")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<List<FizioplanDto>> getFizioplaniPacienta(
            @PathVariable("username") String username
    ) {
        List<FizioplanDto> vsiFizioplani = fizioplanService.getAllPacientFizioplani(username);
        if (vsiFizioplani == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(vsiFizioplani);
    }

    @Operation(summary = "GET vsa Obvestila Pacienta",
            description = "<p>Pridobi vsa obvestila, ki so bili ustvarjeni za pacienta. " +
                    "V primeru, da pacient nima obvestil je odgovor prazen</p>",
            tags = PACIENT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/{username}/obvestila")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<List<ObvestiloDto>> getObvestilaPacienta(
            @PathVariable("username") String username
    ) {
        List<ObvestiloDto> vsaObvestila = obvestiloService.getAllPacientObvestila(username);
        if (vsaObvestila == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(vsaObvestila);
    }


}
