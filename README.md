
# 🎮 GamerLog

GamerLog é um aplicativo mobile desenvolvido em Java com Android Studio que permite aos usuários avaliarem os jogos que jogaram, como se fosse um "Letterboxd para jogos". A proposta é criar um espaço pessoal para registrar, avaliar e refletir sobre experiências com games.

---

## ✨ Funcionalidades

- Tela de login com validação de usuário
- Tela de cadastro de novos usuários
- Home com navegação inferior (Bottom Navigation Bar)
- Botão de adição para incluir novos jogos e avaliações
- Navegação entre telas de Home, Busca e Configurações
- Interface visual baseada em protótipo no Figma

---

## 🛠️ Tecnologias utilizadas

### Frontend
- **Java** com **Android Studio**
- **XML** para construção de layouts
- **Figma** para prototipagem de interfaces

### Backend (futuro)
- Integração planejada com **banco de dados** e **autenticação de usuários**
- Utilização de armazenamento local temporário enquanto não há backend ativo

---

## 📐 Arquitetura

O projeto adota uma arquitetura baseada em boas práticas de desenvolvimento mobile com foco na separação de responsabilidades. No momento, o foco está na implementação das telas e navegação entre elas, com comportamentos nativos e responsivos.

---

### Estrutura atual
```
app/
├── java/
│   └── com.example.gamelog/
│       ├── activities/        # Telas principais como Login, Cadastro, Home
│       ├── fragments/         # Fragments da Bottom Navigation
│       ├── models/            # (Futuro) Modelos de dados para jogos e usuários
│       └── utils/             # Utilitários e constantes
├── res/
│   ├── layout/                # Arquivos XML das interfaces
│   ├── font/                  # Fonte Montserrat personalizada
│   └── drawable/              # Ícones e imagens
```

---

## 🧪 Status do projeto

🚧 **Em desenvolvimento**  
As telas de login e cadastro já foram implementadas e estão sendo testadas. O próximo passo é integrar funcionalidades à tela principal, incluindo listagem e avaliação de jogos. No momento, não há conexão com banco de dados – as funcionalidades são focadas na navegação e na interface.

---

## 🧠 Próximos Passos

- [ ] Implementar armazenamento local temporário
- [ ] Adicionar tela de detalhes de jogo
- [ ] Incluir sistema de avaliação (notas e comentários)
- [ ] Futuramente, integrar banco de dados e autenticação

---

## 🙋‍♀️ Desenvolvido por

Feito com carinho por [Mariucha](https://github.com/vicmariucha) 💜  
Se quiser trocar uma ideia ou dar um feedback, é só me chamar! 😊

---

📌 Este projeto faz parte de um desafio acadêmico da disciplina de Desenvolvimento Mobile.
