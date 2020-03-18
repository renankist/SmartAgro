# SmartAgro
Sistema desktop destinado a pequenas agropecuárias(adaptável a outras áreas de negócio), desenvolvido na disciplina de Projeto Integrador I, no curso de Engenharia de Software na Universidade do Vale do Taquari.

### Rodando o sistema

Ao seguir os passos abaixo, você poderá executar o software em seu computador: 

1) Após baixar o projeto, acesse a pasta "Scripts_Data_Base" que se encontra no diretório raiz da aplicação; 
2) Execute os scripts que se encontram dentro dos arquivos da pasta, na seguinte ordem: 
  - ScriptsBD_Base_Tabelas_SmartAgro; 
  - ScriptsBD_Inserts_SmartAgro;
  - ScriptsBD_functions_SmartAgro;
3) Neste momento, você já poderá logar no sistema com o usuário "admin" senha "12345" (no primeiro acesso será solicitado a alteração desta); 

### Pré-requisitos
 - PostgresSQL; 
 - Java-JDK; 

### Informações a mais
- A modelagem do banco de dados (modelo ER), se encontra na pasta "Modelagens" no diretório raiz do projeto; 
- O software realiza um controle de licença de uso para cada usuário, por meio de um arquivo criado no diretório raiz do projeto. Para se criar uma nova licença, basta rodar o .java do diretório "/src/apoio/CriarLicenca.java";
- Dentro de "/srv/apoio" temos o Server.java, rode este arquivo antes da aplicação, pois ele servirá como um servidor socket,e sempre quando um poduto estiver com estoque abaixo de 5, todos os usuários logados, e conectados a esse servidor, receberão uma mensagem de aviso.

## Considerações finais
Projeto de uso didático.

