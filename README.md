# Bosko API

### A solução Bosko foi criada pensando em ajudar as pessoas a manterem sua memória ativa. O aplicativo conta com uma biblioteca de livros, onde o usuário pode escolher o que quer ler, e ao final de cada capítulo, o usuário é desafiado a responder perguntas sobre o que leu. Caso o usuário tenha dificuldade de se lembrar de algo sobre o livro, ele poderá pedir ajuda para nosso assistente virtual.



**Endpoints User**
```
GET - /api/user/
GET - /api/user/:id
POST - /api/user/
PUT - /api/user/:id Basic user password
DELETE - /api/user/:id Basic user password
```

**Endpoints Book**
```
GET - /api/book/
GET - /api/book?company=
GET - /api/book/:id
POST - /api/book/
PUT - /api/book/:id Basic user password
DELETE - /api/book/:id Basic user password
```

**Endpoints Progress**
```
GET - /api/user/progress Basic user password
GET - /api/user/progress/:id Basic user password
POST - /api/user/progress Basic user password
PUT - /api/user/progress/:id Basic user password
DELETE - /api/user/progress/:id Basic user password
```
*Ao colocar seu username na autenticação, o endpoint sempre irá resgatar o progresso desse username*
# 