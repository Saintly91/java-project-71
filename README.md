# Проект «Вычислитель отличий»

**Вычислитель отличий** — утилита командной строки, определяющая различия между двумя файлами.  
Поддерживаемые форматы: **JSON** и **YAML**.

---

## 🚀 Возможности

- Сравнение двух файлов формата **JSON** или **YAML**

---

## 🛠️ Статус проекта и качество кода

[![Actions Status](https://github.com/Saintly91/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Saintly91/java-project-71/actions)  
[![Build](https://github.com/Saintly91/java-project-71/actions/workflows/build.yml/badge.svg)](https://github.com/Saintly91/java-project-71/actions/workflows/build.yml)  
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Saintly91_java-project-71&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Saintly91_java-project-71)  
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Saintly91_java-project-71&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Saintly91_java-project-71)

---

## 📦 Установка

```bash
git clone https://github.com/Saintly91/java-project-71.git
cd java-project-71
./gradlew installDist

## ▶️ Использование

Пример запуска для JSON:

./build/install/app/bin/app src/test/resources/file1.json src/test/resources/file2.json


Пример запуска для YAML:

./build/install/app/bin/app src/test/resources/file1.yml src/test/resources/file2.yml

## 🧪 Примеры работы
### Сравнение JSON
![Пример работы JSON](https://raw.githubusercontent.com/Saintly91/java-project-71/main/app/images/ExampleJSON.png)
### Сравнение YAML
![Пример работы YAML](https://raw.githubusercontent.com/Saintly91/java-project-71/main/app/images/ExampleYAML.png)
