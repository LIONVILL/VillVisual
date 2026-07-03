# Wiki Starter (MkDocs + Material)

## Локальный запуск

```bash
pip install -r requirements.txt
mkdocs serve
```

Сайт откроется на `http://127.0.0.1:8000`.

## Структура

```
docs/
  index.md              # главная
  guides/
    triggers.md          # список триггеров
    targets.md            # таргеты
mkdocs.yml               # конфиг: навигация, тема, плагины
```

Чтобы добавить новую страницу — создай `.md` файл в `docs/` и пропиши его в `nav:` внутри `mkdocs.yml`.

## Деплой

### Вариант 1: вручную
```bash
mkdocs gh-deploy --force
```
Это соберёт сайт и запушит его в ветку `gh-pages`.

### Вариант 2: автоматически (рекомендуется)
Уже настроен `.github/workflows/deploy.yml` — при каждом пуше в `main` GitHub сам соберёт и задеплоит сайт.

## Включить GitHub Pages
1. Repo → Settings → Pages
2. Source: Deploy from a branch
3. Branch: `gh-pages` / `root`
4. (опционально) свой домен через файл `CNAME` в `docs/`
