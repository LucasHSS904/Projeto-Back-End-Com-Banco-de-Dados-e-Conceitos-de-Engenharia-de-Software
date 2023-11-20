package Projeto.controles.processo;

import Projeto.serviços.processo.ProcessoServiço;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/processos")
@Tag(name = "Processo", description = "API para gerenciamento de processos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProcessoControleImpl implements ProcessoControle {

    private final ProcessoServiço processoServiço;

    @PostMapping
    @Operation(summary = "criar processo", method = "POST")
    @ResponseStatus(HttpStatus.CREATED)
    public ProcessoResposta create(@Valid @RequestBody ProcessoRequisiçao request) {

        return processoServiço.create(request);
    }

    @GetMapping
    @Operation(summary = "Listar processos", method = "GET")
    @ResponseStatus(HttpStatus.OK)
    public Set<ProcessoResposta> list() {

        return processoServiço.list();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter processo por ID", method = "GET")
    @ResponseStatus(HttpStatus.OK)
    public ProcessoResposta getById(@PathVariable Long id) {

        return processoServiço.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar processo por ID", method = "PUT")
    @ResponseStatus(HttpStatus.OK)
    public ProcessoResposta update(
            @PathVariable Long id, @Valid @RequestBody ProcessoRequisiçao request) {

        return processoServiço.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover processo por ID", method = "DELETE")
    @ResponseStatus(HttpStatus.OK)
    public ProcessoResposta delete(@PathVariable Long id) {

        return processoServiço.delete(id);
    }

    @PutMapping("/{id}/adicionar-partes")
    @Operation(summary = "Adicionar partes a um processo", method = "PUT")
    @ResponseStatus(HttpStatus.CREATED)
    public ProcessoResposta addPartes(
            @PathVariable("id") Long processoId, @RequestBody Set<Long> parteIds) {

        return processoServiço.addPartes(processoId, parteIds);
    }
}
