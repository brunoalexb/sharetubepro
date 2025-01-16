# ShareTubePro - Backend

Este repositório contém o backend da aplicação ShareTubePro, desenvolvido em Java com Spring Boot. O backend é responsável pelo processamento das requisições e pela lógica de download dos vídeos a partir de links fornecidos pelos usuários.

## Funcionalidades

- Processamento de links de vídeos enviados pelo frontend.
- Geração e envio do arquivo de vídeo e/ou audio para o usuário.

## Tecnologias Utilizadas

- **Java**: Linguagem principal utilizada no desenvolvimento.
- **Spring Boot**: Framework para construção e gerenciamento do backend.
- **Maven**: Gerenciador de dependências e build.

## Como Executar o Projeto

### Pré-requisitos
Certifique-se de ter as seguintes ferramentas instaladas:
- Java JDK 11 ou superior
- Maven

### Passo a Passo

1. Clone este repositório:
   ```bash
   git clone https://github.com/seuusuario/sharetubepro-backend.git
   ```

2. Navegue até a pasta do backend:
   ```bash
   cd sharetubepro-backend
   ```

3. Compile e inicie a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

4. O backend estará disponível em [http://localhost:8080](http://localhost:8080).

## Estrutura do Projeto

- `src/main/java`: Contém o código-fonte principal do backend.
- `src/main/resources`: Contém arquivos de configuração, como `application.properties`.

## Endpoints

- **`POST /api/download`**: Recebe um link de vídeo e retorna o arquivo para download.

### Exemplo de Requisição

```bash
Exemplo de Endpoint POST: http://localhost:8080/api/download?videoUrl=https://www.youtube.com/watch?v=pLfW-rj-0vo)
  JSON:
    {
  "videoUrl": "https://www.youtube.com/watch?v=pLfW-rj-0vo",
  "type": "audio",
  "quality": "best"
}
```

## Contribuindo
Contribuições são bem-vindas! Siga os passos abaixo para colaborar:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature/bugfix:
   ```bash
   git checkout -b minha-feature
   ```
3. Faça suas alterações e commit:
   ```bash
   git commit -m 'Adiciona minha feature'
   ```
4. Envie para o repositório remoto:
   ```bash
   git push origin minha-feature
   ```
5. Abra um Pull Request.

## Licença
Este projeto está licenciado sob a Licença MIT. Consulte o arquivo `LICENSE` para mais informações.

---

### Observação
Se precisar de ajuda ou encontrar algum problema, sinta-se à vontade para abrir uma [issue](https://github.com/seuusuario/sharetubepro-backend/issues) no repositório.

