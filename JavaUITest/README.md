В /src/test/java/github/meshhi/utils/conf.properties необходимо указать путь к chromedriver в одноименной переменной.  
Сборка: maven  
Тест-фреймворк: JUnit


mvn -Dtest="github/meshhi/tests/local/*Test" test