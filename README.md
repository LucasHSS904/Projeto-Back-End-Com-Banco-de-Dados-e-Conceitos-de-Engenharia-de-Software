# Missão Certificado - Mundo 3
 
## Estácio - TEAM 01

## 👾 Integrantes do grupo 👾

FELIPE MARQUES DE ALMEIDA         -        202208291929<br>
GERSON JOSÉ DE ALMEIDA JÚNIOR     -        202211317577<br>
LUCAS HENRIQUE SILVA SANTOS       -        202208335691<br>
ALESSANDRO SENDI SHIGEMATSU       -        202208809812<br>
ALEXANDRE CHIATETTI DO NASCIMENTO    -     202208291279<br>
ALEXANDRE HENRIQUE FERNANDES NOLLA   -     202208568921<br>

# 📂 Descrição do Projeto 📂

## 📎 Armazenamento de Notificações e Ligações no Banco de Dados:

Utilização de um banco de dados, como o PostgreSQL, para armazenar registros de notificações e suas conexões (como os dados dos processos, partes envolvidas, tipos de notificação, status etc.).

## 📝 Exemplo Técnico:

Estrutura de Tabelas no PostgreSQL:<br>
Tabela "Notificacoes": Campos como ID, Tipo de Notificação, Conteúdo, Status etc.<br>
Tabela "Processos": Armazenamento de dados do processo como Número, Status, Partes envolvidas etc.<br>
Relacionamentos entre as tabelas para registrar a ligação entre notificações e processos.<br>
Webservice seguindo Padrão REST:<br>

Desenvolvimento de endpoints GET e PUT para interação com o sistema e-Carta dos Correios, endpoints POST para cadastro de processos e partes, além de GET e PUT para o Diário da Justiça Eletrônico.<br>

## 📝 Exemplo Técnico:

Implementação de endpoints REST utilizando Java e Spring Boot:<br>
/api/notificacoes/{id} (GET/PUT): Para obter/atualizar detalhes de uma notificação específica.<br>
/api/processos (POST): Para cadastrar novos processos.<br>
/api/carta/enviar (GET): Para consultar cartas a serem enviadas pelo sistema e-Carta.<br>

## 📎 Recomendações e Tecnologias Específicas:

Uso de Java (JDK 17), Maven para gerenciamento de dependências, PostgreSQL como banco de dados, Git/GitHub para versionamento, e documentação técnica com diagrama UML.

## 📝 Exemplo Técnico:

Configuração do ambiente de desenvolvimento com JDK 17 e Maven.<br>
Utilização de dependências como Spring Boot e Hibernate para persistência no PostgreSQL.<br>
Uso do Git para versionamento e compartilhamento do código no GitHub.<br>
Dicas e Observações:

Uso da API do ViaCep para obtenção de dados de endereços, aplicação do Lombok para redução de verbosidade no código Java e configuração de senha de app para envio de e-mails pelo servidor do Google.

## 📝Exemplo Técnico:

Integração da API do ViaCep para obter detalhes do endereço a partir do CEP.<br>
Implementação de classes com Lombok para redução de código boilerplate.<br>
Configuração do servidor de e-mail utilizando a senha de app para garantir a segurança das credenciais.<br>


Este projeto visa criar um sistema robusto para notificação de partes envolvidas em processos judiciais, seguindo as diretrizes do CNJ, utilizando tecnologias modernas e práticas de desenvolvimento.