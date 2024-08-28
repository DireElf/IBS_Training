[![Test](https://github.com/DireElf/Playwright-example/actions/workflows/main.yml/badge.svg)](https://github.com/DireElf/Playwright-example/actions/workflows/main.yml)

## Окружение

- Приложение `qualit-sandbox`, запущенное на порту `8080`.
- Java версии 11 или выше.
- Установленные Maven и Google Chrome.

## Запуск

Запуск всех последующих команд выполняется из директории проекта.

### Практическое задание №3: "Тестирование Java"

Запуск тестов UI:

```bash
mvn test -Dtest=TestAddGood
```

---

### Практическое задание №4: "JDBC"

Запуск тестов БД с помощью JDBC:

```bash
mvn test -Dtest=TestAddGoodByJDBC
```
---

### Практическое задание №5: "Cucumber"

(Код можно найти в 'src/test/java/com/ibs/tests/cucumber') Инициализация раннера, запускающего Cucumber-тесты:

```bash
mvn test -Dtest=TestRunner
```
---

### Практическое задание №7: "REST"
 
Запуск тестов API:

```bash
mvn test -Dtest=TestAddGoodAPI
```
---


_Просмотреть отчет Allure после выполнения тестов:_
```bash
mvn allure:serve
```


