package com.tpo.fizio.rest.application;

import com.tpo.fizio.entity.vnos.impl.service.VnosService;
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

import static com.tpo.fizio.rest.util.RestConstants.VNOS;

/**
 * @author Tadej Delopst
 */
@RestController
@RequestMapping("rest/v1/vnosi")
public class VnosiController {
    private VnosService vnosService;

    @Autowired
    public VnosiController(VnosService vnosService) {
        this.vnosService = vnosService;
    }

    @Operation(summary = "GET data za Vnos",
            description = "<p>Predstavitev vnosa za podrobnejši prikaz. Namenjen predstavitvi posameznega vnosa. " +
                    "V primeru, da vnos ne obstaja je rezultat prazen.</p>",
            tags = VNOS)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/{vnosId}")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<VnosDto> getPacientsFizioplanMeta(
            @PathVariable("vnosId") Integer vnosId
    ) {
        VnosDto dto = vnosService.getVnosById(vnosId);
        if (dto == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "UPDATE komentar Vnosa",
            description = "<p>Dodaj ali posodobi komentar obstoječega vnosa. V primeru da vnos ne obstaja primeren message</p>",
            tags = VNOS)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @PutMapping( value = "/{vnosId}/komentar/update", consumes = "application/json", produces = "application/json" )
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<VnosActionInformation> updateKomentarVnosa(
            @PathVariable("vnosId") Integer vnosId,
            @RequestBody VnosDto dto
    ) {
        VnosActionInformation information = vnosService.updateKomentarVnosa(vnosId, dto.getKomentar());
        return ResponseEntity.ok(information);
    }

    @Operation(summary = "GET Vnosi",
            description = "<p>Vrne vnosi, ki obstajajo. V primeru, da vnosov ni, je rezultat prazen.</p>",
            tags = VNOS)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @GetMapping("/")
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<List<VnosDto>> getVnosi(
    ) {
        List<VnosDto> vnosi = vnosService.getVnosi();
        if (vnosi == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(vnosi);
    }

    @Operation(summary = "UPDATE Vnos",
            description = "<p>Posodobi Vnos, če ta obstaja po podanem identifierju v requestu. " +
                    "V primeru, da Vnos ne obstaja je rezultat prazen.</p>",
            tags = VNOS)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success - successfully retrieved data."),
            @ApiResponse(responseCode = "204", description = "No Content - there is no existing data.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized.", content = @Content)
    })
    @PutMapping( value = "/", consumes = "application/json", produces = "application/json" )
    @Secured({"ROLE_PACIENT"})
    public ResponseEntity<VnosActionInformation> updateVnos(
            @RequestBody VnosDto dto
    ) {
        VnosActionInformation information = vnosService.updateVnos(dto);
        return ResponseEntity.ok(information);
    }
}
