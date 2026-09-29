package com.claudio.todo.service.impl;

import com.claudio.todo.exception.RegraNegocioException;
import com.claudio.todo.model.entity.Tarefa;
import com.claudio.todo.model.repository.TarefaRepository;
import com.claudio.todo.service.TarefaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TarefaServiceImpl implements TarefaService {

    private final TarefaRepository repository;

    public TarefaServiceImpl(TarefaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Tarefa salvar(Tarefa tarefa) {
        if (tarefa.getNome() == null || tarefa.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome da tarefa é obrigatório.");
        }
        return repository.save(tarefa);
    }

    @Override
    @Transactional
    public Tarefa atualizar(Long id, Tarefa tarefaAtualizada) {
        Tarefa tarefa = buscarPorId(id);

        if (tarefaAtualizada.getNome() != null && !tarefaAtualizada.getNome().trim().isEmpty()) {
            tarefa.setNome(tarefaAtualizada.getNome());
        }
        tarefa.setDescricao(tarefaAtualizada.getDescricao());
        if (tarefaAtualizada.getStatus() != null) {
            tarefa.setStatus(tarefaAtualizada.getStatus());
        }
        tarefa.setObservacoes(tarefaAtualizada.getObservacoes());

        return repository.save(tarefa);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Tarefa tarefa = buscarPorId(id);
        repository.delete(tarefa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Tarefa buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Tarefa não encontrada para o ID: " + id));
    }
}