﻿# REST-сервис для хранения данных о городских достопримечательностях
  
![icon]
  
## оглавление
* [Краткое описание]
* [Структура данных]
* [Инструкции по запуску]
* [Сервис предоставляет]
* [Коментарии]
* [Другое]
  
## Краткое описание
Программа представляет REST-сервис (основа Spring Boot) для хранения данных о городских достопримечательностях.
Храниние в базе данных(PostgreSQL), предоставление(формат JSON) и изменение данных по запросам.
База данных инициализирутся и контролируется системой Liquibase.
Для тестирования и демонстрации функций реализован простенький интерфейс(фронтенд),
который нужно заменить или профессионально отредактировать для полноценного использования сервиса.
  
## Структура данных
Город
* [Идентификатор (порядковый номер)]
* [Название города]
* [Численность населения]
* [Наличие метро]
* [Страна]

Достопримечательность
* [Идентификатор]
* [Название достопримечательности]
* [Дата постройки]
* [Краткое описание]
* [Тип достопримечательности (enum, например: Здание/Сооружение/Музей/Памятник/Заповедник)]
* [Идентификатор города]
     
## Инструкции по запуску
Перед первоначальным запуском необходимо в PostgreSQL иметь/создать пустую базу данных (имя по умолчанию worldsights, но можно любое другое).
В файле конфигураций application.yml (находится в корне программы) указать/изменить на свои:
* [url: jdbc:postgresql://localhost:5432/<имя базы данных>]
* [username: <имя пользователя базы данных>]
* [password: <пароль доступа базы данных>]
* []
Таблицы программа создат сама!
Использование интерфейса интуитивно понятно!

## Сервис предоставляет
Программа запускается по адресу http://localhost:8080/ и можно:
- Получить все достопримечательности (формат JSON) нажав на ссылку (по умолчанию упорядочены по ID,
опционально можно передать параметр для сортировки по наименованию достопримечательности sort=true
(в конце строки можно добавить ?sort=true или ?sort=1) через знак & также 
 можно передать параметр для фильтрации по типу достопримечательности type=(варианты type=building, construction, museum, monument, reserve),
 параметр для фильтрации по городу(достопримечательности конкретного города) townName=...
(например http://localhost:8080/sights?sort=true&type=building&townName=moskov выдаст отсортировано все здания москвы)
- Добавить/получить город
- Получить список всех городов из базы(можно передать параметр для сортировки по наименованию sort=true)
- Добавить достопримечательность
- Изменение данных по городу (возможно изменение только полей: Численность населения, Наличие метро)
- Изменение данных по достопримечательности (возможно изменение только поля: Краткое описание)
- Удаление достопримечательности из справочника
                               
## Коментарии
При создании таблиц создается город-заглушка с ID=0.
Контроль наличия города в базе, при создании достопримечательности лежит на пользователе.
Если города не находится, то подставляется заглушка (сначала внесите город, если его нет - потом объект).
                               
## Другое
Не судите строго! Я только недавно начал учиться...
Присылайте ошибки.
* Email: <forward2010@inbox.ru>  
