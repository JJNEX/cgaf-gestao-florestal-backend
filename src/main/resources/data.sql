INSERT INTO usuario (
    id,
    nome,
    email,
    senha,
    ativo,
    perfil,
    data_criacao,
    data_atualizacao
) VALUES (
     'f04e4da7-c900-4b93-860d-a21b59dc8767',
     'ADMINISTRADOR CGAF',
     'admin@cgaf.com.br',
     '$2a$10$clXuxKhTO8pzSvre8uw8HeZ0zoljU9fpbD9MXTlBzIA4FdVYtete2',
     true,
     'ADMIN',
     now(),
     now()
 )
ON CONFLICT (id) DO NOTHING;

INSERT INTO colaborador (
    id_usuario,
    cpf,
    matricula,
    funcao,
    area_atuacao,
    data_admissao,
    contato_emergencia,
    qualificacoes
) VALUES (
     'f04e4da7-c900-4b93-860d-a21b59dc8767',
     '97577193585',
     'ADM-001',
     'CTO',
     'Gestão',
     current_date,
     '7128545788',
     'Gestão de Sistemas; Administração'
)
ON CONFLICT (id_usuario) DO NOTHING;


INSERT INTO status_area (descricao) VALUES ('Ativa') ON CONFLICT (descricao) DO NOTHING;
INSERT INTO status_area (descricao) VALUES ('Em Recuperação') ON CONFLICT (descricao) DO NOTHING;
INSERT INTO status_area (descricao) VALUES ('Embargada') ON CONFLICT (descricao) DO NOTHING;
INSERT INTO status_area (descricao) VALUES ('Reservada') ON CONFLICT (descricao) DO NOTHING;
INSERT INTO status_area (descricao) VALUES ('Inativa') ON CONFLICT (descricao) DO NOTHING;