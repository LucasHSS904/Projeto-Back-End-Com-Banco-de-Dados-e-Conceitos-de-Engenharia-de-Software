package Projeto.controles.parte;

import Projeto.serviços.parte.ParteServiço;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/partes")
@Tag(name = "Parte", description = "API para gerenciamento de partes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ParteControleImpl implements ParteControle {

    private final ParteServiço parteServiço;

    @PostMapping
    @Operation(summary = "criar parte", method = "POST")
    @ResponseStatus(HttpStatus.CREATED)
    public ParteResposta create(@Valid @RequestBody ParteRequisiçao request) {

        return parteServiço.create(request);
    }

    @GetMapping
    @Operation(summary = "Listar partes", method = "GET")
    @ResponseStatus(HttpStatus.OK)
    public Set<ParteResposta> list() {

        return parteServiço.list();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter parte por ID", method = "GET")
    @ResponseStatus(HttpStatus.OK)
    public ParteResposta getById(@PathVariable Long id) {

        return parteServiço.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar parte por ID", method = "PUT")
    @ResponseStatus(HttpStatus.OK)
    public ParteResposta update(@PathVariable Long id, @Valid @RequestBody ParteRequisiçao request) {

        return parteServiço.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover parte por ID", method = "DELETE")
    @ResponseStatus(HttpStatus.OK)
    public ParteResposta delete(@PathVariable Long id) {

        return parteServiço.delete(id);
    }
}
