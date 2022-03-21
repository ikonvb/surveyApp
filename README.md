# surveyApp 

**Описание**    
Для работы с REST API рекомендуется использовать [**Postman**](https://www.postman.com/).    
Для запуска приложения локально нужно скачать этот проект, установитьс субд PostgreSQL и PgAdmin.    
Создать бызу данных survey_db. Создать таблицы:    
clients 
~~~
CREATE TABLE clients
(
    id serial NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    user_role character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT clients_pkey PRIMARY KEY (id)
)
~~~

questions 
~~~
CREATE TABLE questions
(
    question_id serial NOT NULL,
    question_text text COLLATE pg_catalog."default" NOT NULL,
    question_type character varying(30) COLLATE pg_catalog."default" NOT NULL,    
    CONSTRAINT questions_pkey PRIMARY KEY (question_id)
)
~~~
survey_question    
~~~
CREATE TABLE survey_question
(
    survey_id bigint NOT NULL,
    question_id bigint NOT NULL
)
~~~
surveys    
~~~
CREATE TABLE surveys
(
    id bigint NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    start_date date NOT NULL,
    end_date date,
    description text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT surveys_pkey PRIMARY KEY (id)
)
~~~
Настроить *application.properties*
~~~
#postgresql config
spring.datasource.url=jdbc:postgresql://localhost/surveys_db
spring.datasource.username=your username
spring.datasource.password=your password
#spring.datasource.initialization=never
spring.jpa.hibernate.ddl-auto=none
spring.servlet.multipart.max-file-size=-1
spring.servlet.multipart.max-request-size=-1
~~~
~~~
/api/show/survey/{surveyId} - это GET метод. Возвращает список вопросов для конкретного опроса.    
~~~
~~~
/api/survey/find/all - это GET метод. Возвращает список всех актуальных опросов.   
~~~
~~~
/api/admin/add/question/survey/{surveyId} - это POST метод. Добавляет один вопрос к одному опросу.    
~~~
Принимает json 
~~~
{
    "questionText": "someText",    
    "questionType": "TEXT"
}  
~~~
~~~
/api/admin/add/survey - это POST метод. Добавляет один опрос.    
~~~
Принимает json    
~~~
{
    "name": "survey8",    
    "description": "paosidaosdjkasjdasjdsdsd"
}  
~~~
~~~
/api/admin/edit/survey/{id} - это PATCH метод. Редактирует один опрос.     
~~~
Принимает json    
~~~
{
    "name": "survey9",    
    "description": "paosidaosdjkasjdasjdsdsd"
}  
~~~
~~~
/api/admin/delete/survey/{id} - это DELETE метод. Удаляет один опрос.
~~~
