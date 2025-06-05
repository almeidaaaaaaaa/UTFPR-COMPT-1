# 🌿 Como criar uma nova branch e subir suas alterações

Antes de criar uma nova branch, certifique-se de que está na `main` e que ela está atualizada:

```bash
git checkout main
git pull
# Atualiza o main

git checkout -b nome-da-sua-branch
# Cria uma nova branch
# O nome da branch sempre será, o que foi feito ex:Feature, Bug, Refactor.
# + / e o link do card do trello ex: Feature/5-atividade-logica-de-cadastro-de-administradores

git add .
# Adiciona os arquivos.

git commit -m "feat: adiciona botão de login na navbar"
# Salva os arquivos modificados.
# A mensagem sera do que foi feito nas alterações.

git push
# Sobe para o github os arquivos modificados.
```
