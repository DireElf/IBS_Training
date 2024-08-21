## Окружение

- Приложение `qualit-sandbox`, запущенное на порту `8080`.
- Java версии 11 или выше.
- Установленные Maven и Google Chrome.

---

### Практическое задание №3: "Тестирование Java"

Для запуска тестов UI выполнить следующую команду в директории проекта:

```bash
mvn test -Dtest=TestAddGood
```

---

### Практическое задание №4: "JDBC"

Запуск тестов БД с помощью JDBC (в директории проекта):

```bash
mvn test -Dtest=TestAddGoodToBD
```
---

### Практическое задание №5: "Cucumber"

Код можно найти в 'src/test/java/com/ibs/tests/cucumber'. Инициализация раннера, запускающего Cucumber-тесты:

```bash
mvn test -Dtest=TestRunner
```
---

_Просмотреть отчет Allure после выполнения тестов:_
```bash
mvn allure:serve
```


