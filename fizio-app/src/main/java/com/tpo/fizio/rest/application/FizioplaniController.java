package com.tpo.fizio.rest.application;

import com.tpo.fizio.entity.dto.VnosMetaDto;
import com.tpo.fizio.entity.fizioplan.model.FizioplanDto;
import com.tpo.fizio.entity.vnos.impl.service.VnosService;
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

import static com.tpo.fizio.rest.util.RestConstants.FIZIOPLAN;

/**
 * @author Tadej Delopst
 */
@RestController
@RequestMapping("rest/v1/fizioplani")
public class FizioplaniController {
    private VnosService vnosService;

    @Autowired
    public FizioplaniController(VnosService vnosService) {
        this.vnosService = vnosService;
    }

    @Operation(summary = "GET Vnosi meta za Fizioplan",
            description = "<p>List meta podatkov vnosov za predstavitev fizioplana. Namenjen predstavitvi vnosov za podrobnejši vpogled fizioplana. " +
                    "V primeru, da fizioplan ne obstaja ali nima vnosov je rezultat prazen.</p>",
            tags = FIZIOPLAN)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/{fizioplanID}/vnosi/meta")
    @Secured("ROLE_PACIENT")
    public ResponseEntity<List<VnosMetaDto>> getPacientsFizioplanMeta(
            @PathVariable("fizioplanID") Integer fizioplanID
    ) {
        List<VnosMetaDto> vnosiMetaData = vnosService.getFizioplansVnosiMetaData(fizioplanID);
        if (vnosiMetaData == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(vnosiMetaData);
    }

    @Operation(summary = "GET Fizioplani",
            description = "<p>Vrne vse fizioplane, ki obstajajo. V primeru, da fizioplanov ni, je rezultat prazen.</p>",
            tags = FIZIOPLAN)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/")
    @Secured("ROLE_PACIENT")
    public ResponseEntity<List<FizioplanDto>> getPacientsFizioplanMeta(
    ) {
        List<FizioplanDto> fizioplani = vnosService.getFizioplans();
        if (fizioplani == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(fizioplani);
    }

    @Operation(summary = "GET Fizioplan",
            description = "<p>Vrne fizioplan, če ta obstaja. V primeru, da fizioplan ne obstaja je odgovor prazen.</p>",
            tags = FIZIOPLAN)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/{fizioplanID}")
    @Secured("ROLE_PACIENT")
    public ResponseEntity<FizioplanDto> getFizioplan(
            @PathVariable("fizioplanID") Integer fizioplanID
    ) {
        FizioplanDto fizioplan = vnosService.getFizioplan(fizioplanID);
        if (fizioplan == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(fizioplan);
    }
}
