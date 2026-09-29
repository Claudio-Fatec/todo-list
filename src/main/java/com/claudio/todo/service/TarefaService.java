package com.claudio.todo.service;

import com.claudio.todo.model.entity.Tarefa;
import java.util.List;

public interface TarefaService {
    Tarefa salvar(Tarefa tarefa);
    Tarefa atualizar(Long id, Tarefa tarefaAtualizada);
    void deletar(Long id);
    List<Tarefa> listarTodas();
    Tarefa buscarPorId(Long id);
}