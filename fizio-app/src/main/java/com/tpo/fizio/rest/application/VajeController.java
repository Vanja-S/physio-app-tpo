package com.tpo.fizio.rest.application;

import com.tpo.fizio.entity.vaja.impl.service.VajaService;
import com.tpo.fizio.entity.vaja.model.VajaDto;
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

import static com.tpo.fizio.rest.util.RestConstants.VAJA;

/**
 * @author Tadej Delopst
 */
@RestController
@RequestMapping("rest/v1/vaje")
public class VajeController {
    private VajaService vajaService;

    @Autowired
    public VajeController(VajaService vajaService) {
        this.vajaService = vajaService;
    }

    @Operation(summary = "GET Vaje",
            description = "<p>Vrne vse Vaje, ki obstajajo. V primeru, da Vaj ni, je rezultat prazen.</p>",
            tags = VAJA)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/")
    @Secured("ROLE_PACIENT")
    public ResponseEntity<List<VajaDto>> getVaje(
    ) {
        List<VajaDto> vaje = vajaService.getVaje();
        if (vaje == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(vaje);
    }

    @Operation(summary = "GET Vaja",
            description = "<p>Vrne Vajo, če ta obstaja. V primeru, da Vaja ne obstaja je odgovor prazen.</p>",
            tags = VAJA)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/{vajaID}")
    @Secured("ROLE_PACIENT")
    public ResponseEntity<VajaDto> getVaja(
            @PathVariable("vajaID") Long vajaId
    ) {
        VajaDto vaja = vajaService.getVaja(vajaId);
        if (vaja == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(vaja);
    }
}
