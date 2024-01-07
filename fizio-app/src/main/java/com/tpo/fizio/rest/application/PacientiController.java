package com.tpo.fizio.rest.application;

import com.tpo.fizio.entity.fizioplan.impl.service.FizioplanService;
import com.tpo.fizio.entity.fizioplan.model.FizioplanDto;
import com.tpo.fizio.entity.fizioterapevt.model.FizioterapevtDto;
import com.tpo.fizio.entity.obvestilo.impl.service.ObvestiloService;
import com.tpo.fizio.entity.obvestilo.model.ObvestiloDto;
import com.tpo.fizio.entity.pacient.impl.service.PacientService;
import com.tpo.fizio.entity.pacient.model.PacientActionInformation;
import com.tpo.fizio.entity.pacient.model.PacientDto;
import com.tpo.fizio.entity.termin.impl.service.TerminService;
import com.tpo.fizio.entity.termin.model.TerminDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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
    private PacientService pacientService;

    @Autowired
    public PacientiController(
            FizioplanService fizioplanService,
            TerminService terminService,
            ObvestiloService obvestiloService,
            PacientService pacientService) {
        this.fizioplanService = fizioplanService;
        this.terminService = terminService;
        this.obvestiloService = obvestiloService;
        this.pacientService = pacientService;
    }

    @Operation(summary = "GET Fizioterapevt Pacienta",
            description = "<p>Vrne fizioterapveta pacienta</p>",
            tags = PACIENT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })

    @GetMapping("/{username}/fizioterapevt")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<FizioterapevtDto> getPacientsFizioterapevt(
            @PathVariable("username") String pacientUsername
    ) {
        FizioterapevtDto pacientsFizio = pacientService.getPacientsFizioterapevt(pacientUsername);
        if (pacientsFizio == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(pacientsFizio);
    }

    @Operation(summary = "GET Pacient",
            description = "<p>Vrne pacienta, ki ima kartoteko. V primeru, da pacienta ni je rezultat prazen</p>",
            tags = PACIENT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/{username}")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<PacientDto> getPacient(
            @PathVariable("username") String pacientUsername
    ) {
        PacientDto pacient = pacientService.getPacient(pacientUsername);
        if (pacient == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(pacient);
    }

    @Operation(summary = "GET Pacienti",
            description = "<p>Vrne paciente, ki imajo kartoteko. V primeru, da pacientov ni je rezultat prazen</p>",
            tags = PACIENT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<List<PacientDto>> getPacients() {
        List<PacientDto> pacients = pacientService.getPacients();
        if (pacients == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(pacients);
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

    @Operation(summary = "UPDATE Pacient",
            description = "<p>Posodobi Pacienta, če ta obstaja po podanem identifierju v requestu. " +
                    "V primeru, da Pacient ne obstaja je rezultat prazen.</p>",
            tags = PACIENT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @PutMapping(value = "/", consumes = "application/json", produces = "application/json")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<PacientActionInformation> updatePacient(
            @RequestBody PacientDto dto
    ) {
        PacientActionInformation information = pacientService.updatePacient(dto);
        return ResponseEntity.ok(information);
    }
}
