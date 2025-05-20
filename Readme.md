# OBJETIVO
- O objetivo desse programa é ser um sistema de escola onde será mostrar os alunos professores e notas de bimestres.

# 📚 Sistema de Cadastro de Pessoas e Notas Bimestrais

Este projeto é um modelo de banco de dados relacional simples, utilizando **SQLite**, com foco em armazenar informações de pessoas (alunos/professores) e suas respectivas notas bimestrais.

## 🧩 Estrutura do Banco de Dados

### Tabelas

#### `PERSON`

Armazena informações de pessoas, podendo ser alunos ou professores.

| Campo           | Tipo      | Descrição                       |
|----------------|-----------|----------------------------------|
| ID             | INTEGER   | Chave primária (autoincremento) |
| NAME           | TEXT      | Nome da pessoa                   |
| AGE            | INTEGER   | Idade                            |
| COURSE         | TEXT      | Curso                            |
| IDENTIFICATION | TEXT      | Tipo da pessoa (ALUNO/PROFESSOR) |

#### `BIMESTRES`

Armazena as notas e descrição do bimestre de uma pessoa.

| Campo         | Tipo     | Descrição                                 |
|---------------|----------|--------------------------------------------|
| ID            | INTEGER  | Chave primária (autoincremento)           |
| NOTAS1        | DECIMAL  | Nota 1 (0 a 10)                            |
| NOTAS2        | DECIMAL  | Nota 2 (0 a 10)                            |
| NOTAS3        | DECIMAL  | Nota 3 (0 a 10)                            |
| DESCRICAO     | TEXT     | Descrição automática do bimestre          |
| PERSON_ID_B   | INTEGER  | Chave estrangeira para `PERSON(ID)`       |

---

## 📊 Diagrama UML (Simplificado)

```plaintext
+--------------+            +---------------+
|   PERSON     |            |   BIMESTRES   |
+--------------+            +---------------+
| ID (PK)      |◄────────┐  | ID (PK)       |
| NAME         |         └──| PERSON_ID_B (FK) 
| AGE          |            | NOTAS1        |
| COURSE       |            | NOTAS2        |
| IDENTIFICATION|           | NOTAS3        |
+--------------+            | DESCRICAO     |
                            +---------------+
```
##Utilizado:
- [x] Java
- [x] SQLite
- [ ] Interface


![](https://media.lordicon.com/icons/wired/flat/1323-java-code-language.gif)
