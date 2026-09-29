package com.claudio.todo.service;

import com.claudio.todo.exception.RegraNegocioException;
import com.claudio.todo.model.entity.Tarefa;
import com.claudio.todo.model.enums.StatusTarefa;
import com.claudio.todo.model.repository.TarefaRepository;
import com.claudio.todo.service.impl.TarefaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class TarefaServiceTest {

    @InjectMocks
    private TarefaServiceImpl service;

    @Mock
    private TarefaRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarTarefaComSucesso() {
        Tarefa tarefa = new Tarefa("Estudar Java", "Revisar tópicos do Spring Boot", StatusTarefa.PENDENTE, "Anotações do curso");
        
        Mockito.when(repository.save(Mockito.any(Tarefa.class))).thenReturn(tarefa);

        Tarefa salva = service.salvar(tarefa);

        Assertions.assertNotNull(salva);
        Assertions.assertEquals("Estudar Java", salva.getNome());
        Mockito.verify(repository, Mockito.times(1)).save(tarefa);
    }

    @Test
    void deveLancarExcecaoAoSalvarTarefaSemNome() {
        Tarefa tarefa = new Tarefa("", "Descrição sem nome", StatusTarefa.PENDENTE, "");

        RegraNegocioException exception = Assertions.assertThrows(
            RegraNegocioException.class, 
            () -> service.salvar(tarefa)
        );

        Assertions.assertEquals("O nome da tarefa é obrigatório.", exception.getMessage());
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void deveBuscarTarefaPorIdComSucesso() {
        Tarefa tarefa = new Tarefa("Fazer Exercícios", "Praticar testes com JUnit", StatusTarefa.EM_ANDAMENTO, "");
        tarefa.setId(1L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(tarefa));

        Tarefa encontrada = service.buscarPorId(1L);

        Assertions.assertNotNull(encontrada);
        Assertions.assertEquals(1L, encontrada.getId());
    }

    @Test
    void deveLancarExcecaoQuandoTarefaNaoForEncontrada() {
        Mockito.when(repository.findById(99L)).thenReturn(Optional.empty());

        RegraNegocioException exception = Assertions.assertThrows(
            RegraNegocioException.class, 
            () -> service.buscarPorId(99L)
        );

        Assertions.assertEquals("Tarefa não encontrada para o ID: 99", exception.getMessage());
    }
}