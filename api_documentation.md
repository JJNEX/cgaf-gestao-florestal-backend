# Documentação Resumida da API - CGAF

## Autenticação

-   **POST** `/auth/login`
    -   **Request:** `AutenticacaoRequest`
        ```json
        {
          "email": "string",
          "senha": "string"
        }
        ```
    -   **Response:** `DefaultResponse<AutenticacaoResponse>`
        ```json
        {
          "status": "integer",
          "message": "string",
          "data": {
            "id": "uuid",
            "email": "string",
            "token": "string",
            "ultimoLogin": "date-time",
            "perfil": "string"
          }
        }
        ```
        
## Response Page - Paginação Padrão

```json
{
	"content": [
		{
			"id": "f04e4da7-c900-4b93-860d-a21b59dc8767",
			"nome": "ADMINISTRADOR CGAF",
			"email": "admin@cgaf.com.br",
			"ativo": true,
			"perfil": "ADMIN",
			"ultimoLogin": "2026-04-20T23:49:31.007599",
			"dataCriacao": "2026-04-11T15:50:49.720878",
			"dataAtualizacao": "2026-04-20T23:49:31.016925"
		}
	],
	"empty": false,
	"first": true,
	"last": true,
	"number": 0,
	"numberOfElements": 1,
	"pageable": {
		"offset": 0,
		"pageNumber": 0,
		"pageSize": 20,
		"paged": true,
		"sort": {
			"empty": true,
			"sorted": false,
			"unsorted": true
		},
		"unpaged": false
	},
	"size": 20,
	"sort": {
		"empty": true,
		"sorted": false,
		"unsorted": true
	},
	"totalElements": 1,
	"totalPages": 1
}
```

## Administrador

-   **POST** `/admin`
    -   **Request:** `AdministradorRequest`
        ```json
        {
          "nome": "string",
          "email": "string",
          "senha": "string"
        }
        ```
    -   **Response:** `UsuarioResponse`
        ```json
        {
          "id": "uuid",
          "nome": "string",
          "email": "string",
          "ativo": "boolean",
          "perfil": "string",
          "ultimoLogin": "date-time",
          "dataCriacao": "date-time",
          "dataAtualizacao": "date-time"
        }
        ```
-   **GET** `/admin`
    -   **Request:** (None)
    -   **Response:** `Page<UsuarioResponse>`
-   **GET** `/admin/{id}`
    -   **Request:** (None)
    -   **Response:** `UsuarioResponse`

## Área Florestal e Alocação

-   **POST** `/admin/area-florestal`
    -   **Request:** `AreaFlorestalRequest`
        ```json
        {
          "nome": "string",
          "latitude": "number",
          "longitude": "number",
          "municipio": "string",
          "estado": "string",
          "tamanhoHectares": "number",
          "status": "string",
          "tipoFloresta": "NATIVA | PLANTADA | MISTA",
          "biomaPredominante": "AMAZONIA | CERRADO | MATA_ATLANTICA | CAATINGA | PAMPA | PANTANAL"
        }
        ```
    -   **Response:** `AreaFlorestalResponse`
        ```json
        {
          "id": "long",
          "nome": "string",
          "latitude": "number",
          "longitude": "number",
          "municipio": "string",
          "estado": "string",
          "tamanhoHectares": "number",
          "tipoFloresta": "NATIVA | PLANTADA | MISTA",
          "biomaPredominante": "AMAZONIA | CERRADO | MATA_ATLANTICA | CAATINGA | PAMPA | PANTANAL",
          "status": "string",
          "alocacoes": [
            {
              "id": "long",
              "idColaborador": "uuid",
              "nomeColaborador": "string",
              "dataInicio": "date",
              "dataFim": "date"
            }
          ]
        }
        ```
-   **GET** `/admin/area-florestal`
    -   **Request:** (Query: `status`, `pageable`)
    -   **Response:** `Page<AreaFlorestalResponse>`
-   **GET** `/admin/area-florestal/{id}`
    -   **Request:** (None)
    -   **Response:** `AreaFlorestalResponse`
-   **PUT** `/admin/area-florestal/{id}`
    -   **Request:** `AreaFlorestalRequest`
    -   **Response:** `DefaultResponse<AreaFlorestalResponse>`
-   **DELETE** `/admin/area-florestal/{id}`
    -   **Request:** (None)
    -   **Response:** `DefaultResponse<Void>`
-   **POST** `/admin/area-colaborador/alocacao`
    -   **Request:** `AlocacaoRequest`
        ```json
        {
          "idColaborador": "uuid",
          "idAreaFlorestal": "long",
          "dataInicio": "date"
        }
        ```
    -   **Response:** `AlocacaoResponse`
        ```json
        {
          "id": "long",
          "idColaborador": "uuid",
          "nomeColaborador": "string",
          "dataInicio": "date",
          "dataFim": "date"
        }
        ```
-   **PUT** `/admin/area-colaborador/alocacao/encerrar/{id}`
    -   **Request:** (Query: `dataFim`)
    -   **Response:** `DefaultResponse<Void>`

## Colaborador

-   **POST** `/colaborador`
    -   **Request:** `ColaboradorRequest`
        ```json
        {
          "nome": "string",
          "email": "string",
          "senha": "string",
          "cpf": "string",
          "matricula": "string",
          "funcao": "string",
          "areaAtuacao": "string",
          "dataAdmissao": "date",
          "contatoEmergencia": "string",
          "qualificacoes": "string"
        }
        ```
    -   **Response:** `ColaboradorResponse`
        ```json
        {
          "id": "uuid",
          "nome": "string",
          "email": "string",
          "ativo": "boolean",
          "cpf": "string",
          "matricula": "string",
          "funcao": "string",
          "areaAtuacao": "string",
          "dataAdmissao": "date",
          "contatoEmergencia": "string",
          "qualificacoes": "string",
          "dataCriacao": "date-time",
          "dataAtualizacao": "date-time"
        }
        ```
-   **GET** `/colaborador`
    -   **Request:** (Query: `pageable`)
    -   **Response:** `Page<ColaboradorResponse>`
-   **GET** `/colaborador/{id}`
    -   **Request:** (None)
    -   **Response:** `ColaboradorResponse`
-   **PUT** `/colaborador/{id}`
    -   **Request:** `ColaboradorRequest`
    -   **Response:** `DefaultResponse<ColaboradorResponse>`

## Equipamentos e Insumos (Recursos)

-   **POST** `/admin/recurso`
    -   **Request:** `EquipamentoInsumoRequest`
        ```json
        {
          "codigoPatrimonial": "string",
          "descricao": "string",
          "categoria": "EQUIPAMENTO | INSUMO | FERRAMENTA",
          "quantidadeEstoque": "integer",
          "estoqueMinimo": "integer",
          "unidadeMedida": "string",
          "localizacaoAtual": "string",
          "dataAquisicao": "date",
          "vidaUtilEstimada": "number",
          "responsavelGuarda": "string"
        }
        ```
    -   **Response:** `EquipamentoInsumoResponse`
        ```json
        {
          "id": "long",
          "codigoPatrimonial": "string",
          "descricao": "string",
          "categoria": "EQUIPAMENTO | INSUMO | FERRAMENTA",
          "quantidadeEstoque": "integer",
          "estoqueMinimo": "integer",
          "unidadeMedida": "string",
          "localizacaoAtual": "string",
          "dataAquisicao": "date",
          "vidaUtilEstimada": "number",
          "responsavelGuarda": "string",
          "ativo": "boolean",
          "dataCriacao": "date-time"
        }
        ```
-   **GET** `/admin/recurso`
    -   **Request:** (Query: `pageable`)
    -   **Response:** `Page<EquipamentoInsumoResponse>`
-   **GET** `/admin/recurso/{id}`
    -   **Request:** (None)
    -   **Response:** `EquipamentoInsumoResponse`
-   **PUT** `/admin/recurso/{id}`
    -   **Request:** `EquipamentoInsumoRequest`
    -   **Response:** `DefaultResponse<EquipamentoInsumoResponse>`
-   **PATCH** `/admin/recurso/{id}/estoque`
    -   **Request:** (Query: `quantidade`)
    -   **Response:** `DefaultResponse<EquipamentoInsumoResponse>`
-   **DELETE** `/admin/recurso/{id}`
    -   **Request:** (None)
    -   **Response:** `DefaultResponse<Void>`

## Espécies

-   **POST** `/admin/especie`
    -   **Request:** `EspecieRequest`
        ```json
        {
          "nomeCientifico": "string",
          "nomePopular": "string",
          "familia": "string",
          "porte": "PEQUENO | MEDIO | GRANDE",
          "conservacao": "NAO_AVALIADA | POUCO_PREOCUPANTE | QUASE_AMEACADA | VULNERAVEL | EM_PERIGO | CRITICAMENTE_EM_PERIGO | EXTINTA_NA_NATUREZA | EXTINTA",
          "cicloVidaAnos": "integer",
          "exigenciasClimaticasSolo": "string",
          "nativa": "boolean"
        }
        ```
    -   **Response:** `EspecieResponse`
        ```json
        {
          "id": "long",
          "nomeCientifico": "string",
          "nomePopular": "string",
          "familia": "string",
          "porte": "PEQUENO | MEDIO | GRANDE",
          "conservacao": "NAO_AVALIADA | POUCO_PREOCUPANTE | QUASE_AMEACADA | VULNERAVEL | EM_PERIGO | CRITICAMENTE_EM_PERIGO | EXTINTA_NA_NATUREZA | EXTINTA",
          "cicloVidaAnos": "integer",
          "exigenciasClimaticasSolo": "string",
          "nativa": "boolean",
          "ativo": "boolean",
          "dataCriacao": "date-time"
        }
        ```
-   **GET** `/admin/especie`
    -   **Request:** (Query: `pageable`)
    -   **Response:** `Page<EspecieResponse>`
-   **GET** `/admin/especie/{id}`
    -   **Request:** (None)
    -   **Response:** `EspecieResponse`
-   **PUT** `/admin/especie/{id}`
    -   **Request:** `EspecieRequest`
    -   **Response:** `DefaultResponse<EspecieResponse>`
-   **DELETE** `/admin/especie/{id}`
    -   **Request:** (None)
    -   **Response:** `DefaultResponse<Void>`

## Operações de Campo

-   **POST** `/colaborador/inventario`
    -   **Request:** `InventarioRequest`
        ```json
        {
          "numeroParcela": "string",
          "areaFlorestalId": "long",
          "especieId": "long",
          "quantidadeIndividuos": "integer",
          "dapMedio": "number",
          "alturaMedia": "number",
          "presencaPragasDoencas": "boolean",
          "estadoGeral": "BOM | REGULAR | RUIM",
          "dataVistoria": "date",
          "colaboradorId": "uuid"
        }
        ```
    -   **Response:** `InventarioResponse`
        ```json
        {
          "id": "long",
          "numeroParcela": "string",
          "nomeAreaFlorestal": "string",
          "nomeEspecie": "string",
          "quantidadeIndividuos": "integer",
          "dapMedio": "number",
          "alturaMedia": "number",
          "presencaPragasDoencas": "boolean",
          "estadoGeral": "BOM | REGULAR | RUIM",
          "dataVistoria": "date",
          "nomeColaborador": "string"
        }
        ```
-   **GET** `/colaborador/inventario`
    -   **Request:** (Query: `pageable`)
    -   **Response:** `Page<InventarioResponse>`
-   **POST** `/colaborador/ocorrencia`
    -   **Request:** `OcorrenciaRequest`
        ```json
        {
          "tipo": "DESMATAMENTO | INCENDIO | ESPECIE_INVASORA | PRAGA | DOENCA | OUTRO",
          "latitude": "number",
          "longitude": "number",
          "fotos": ["string"],
          "urgencia": "BAIXA | MEDIA | ALTA | CRITICA",
          "descricao": "string",
          "areaFlorestalId": "long",
          "colaboradorId": "uuid"
        }
        ```
    -   **Response:** `OcorrenciaResponse`
        ```json
        {
          "id": "long",
          "tipo": "DESMATAMENTO | INCENDIO | ESPECIE_INVASORA | PRAGA | DOENCA | OUTRO",
          "latitude": "number",
          "longitude": "number",
          "fotos": ["string"],
          "urgencia": "BAIXA | MEDIA | ALTA | CRITICA",
          "descricao": "string",
          "protocolo": "string",
          "nomeAreaFlorestal": "string",
          "nomeColaborador": "string",
          "dataOcorrencia": "date-time"
        }
        ```
-   **GET** `/colaborador/ocorrencia`
    -   **Request:** (None)
    -   **Response:** `Page<OcorrenciaResponse>`
-   **GET** `/colaborador/ocorrencia/{protocolo}`
    -   **Request:** (None)
    -   **Response:** `OcorrenciaResponse`
-   **POST** `/colaborador/plantio`
    -   **Request:** `PlantioRequest`
        ```json
        {
          "dataHora": "date-time",
          "areaFlorestalId": "long",
          "especieId": "long",
          "quantidadeMudas": "integer",
          "latitudeTalhao": "number",
          "longitudeTalhao": "number",
          "temperatura": "number",
          "umidade": "number",
          "chuva": "boolean",
          "metodoPlantio": "string",
          "observacoes": "string",
          "colaboradorId": "uuid"
        }
        ```
    -   **Response:** `PlantioResponse`
        ```json
        {
          "id": "long",
          "dataHora": "date-time",
          "nomeAreaFlorestal": "string",
          "nomeEspecie": "string",
          "quantidadeMudas": "integer",
          "latitudeTalhao": "number",
          "longitudeTalhao": "number",
          "temperatura": "number",
          "umidade": "number",
          "chuva": "boolean",
          "metodoPlantio": "string",
          "observacoes": "string",
          "nomeColaborador": "string"
        }
        ```
-   **GET** `/colaborador/plantio`
    -   **Request:** (None)
    -   **Response:** `Page<PlantioResponse>`

## Relatórios

-   **GET** `/admin/relatorio/por-bioma`
    -   **Request:** (None)
    -   **Response:** `Page<RelatorioBiomaResponse>`
        ```json
        [
          {
            "bioma": "AMAZONIA | CERRADO | MATA_ATLANTICA | CAATINGA | PAMPA | PANTANAL",
            "totalAreas": "long",
            "totalHectares": "number"
          }
        ]
        ```
-   **GET** `/admin/relatorio/especies/fichas-tecnicas`
    -   **Request:** (None)
    -   **Response:** `Page<FichaTecnicaEspecieResponse>`
        ```json
        [
          {
            "nomeCientifico": "string",
            "nomePopular": "string",
            "conservacao": "NAO_AVALIADA | POUCO_PREOCUPANTE | QUASE_AMEACADA | VULNERAVEL | EM_PERIGO | CRITICAMENTE_EM_PERIGO | EXTINTA_NA_NATUREZA | EXTINTA",
            "exigenciasClimaticasSolo": "string",
            "nativa": "boolean"
          }
        ]
        ```
-   **GET** `/admin/relatorio/estoque/alertas`
    -   **Request:** (None)
    -   **Response:** `Page<AlertaEstoqueResponse>`
        ```json
        [
          {
            "codigoPatrimonial": "string",
            "descricao": "string",
            "quantidadeAtual": "integer",
            "estoqueMinimo": "integer",
            "unidadeMedida": "string",
            "responsavelGuarda": "string"
          }
        ]
        ```
-   **GET** `/admin/relatorio/estoque/previsao`
    -   **Request:** (None)
    -   **Response:** `Page<PrevisaoReposicaoResponse>`
        ```json
        [
          {
            "codigoPatrimonial": "string",
            "descricao": "string",
            "dataAquisicao": "date",
            "vidaUtilRestanteAnos": "number",
            "previsaoReposicao": "date"
          }
        ]
        ```
-   **GET** `/admin/relatorio/produtividade/{colaboradorId}`
    -   **Request:** (None)
    -   **Response:** `ProdutividadeColaboradorResponse`
        ```json
        {
          "nomeColaborador": "string",
          "mudasPlantadasNoMes": "long",
          "vistoriasRealizadasNoMes": "long",
          "ocorrenciasRelatadasNoMes": "long"
        }
        ```
-   **GET** `/admin/relatorio/alertas/criticos`
    -   **Request:** (None)
    -   **Response:** `DefaultResponse<Long>`
