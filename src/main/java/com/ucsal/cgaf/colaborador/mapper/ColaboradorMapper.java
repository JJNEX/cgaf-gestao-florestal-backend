package com.ucsal.cgaf.colaborador.mapper;

import com.ucsal.cgaf.colaborador.dto.ColaboradorResponse;
import com.ucsal.cgaf.colaborador.dto.ColaboradorRequest;
import com.ucsal.cgaf.colaborador.entity.Colaborador;
import org.springframework.stereotype.Component;

@Component
public class ColaboradorMapper {

    public Colaborador toEntity(ColaboradorRequest dto) {
        Colaborador colaborador = new Colaborador();
        colaborador.setNome(dto.nome());
        colaborador.setEmail(dto.email());
        colaborador.setSenha(dto.senha());
        colaborador.setCpf(dto.cpf());
        colaborador.setMatricula(dto.matricula());
        colaborador.setFuncao(dto.funcao());
        colaborador.setAreaAtuacao(dto.areaAtuacao());
        colaborador.setDataAdmissao(dto.dataAdmissao());
        colaborador.setContatoEmergencia(dto.contatoEmergencia());
        colaborador.setQualificacoes(dto.qualificacoes());
        return colaborador;
    }

    public ColaboradorResponse toResponse(Colaborador colaborador) {
        return new ColaboradorResponse(
            colaborador.getId(),
            colaborador.getNome(),
            colaborador.getEmail(),
            colaborador.getAtivo(),
            colaborador.getCpf(),
            colaborador.getMatricula(),
            colaborador.getFuncao(),
            colaborador.getAreaAtuacao(),
            colaborador.getDataAdmissao(),
            colaborador.getContatoEmergencia(),
            colaborador.getQualificacoes(),
            colaborador.getDataCriacao(),
            colaborador.getDataAtualizacao()
        );
    }
}
