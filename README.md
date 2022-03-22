# surveyApp 
**Описание**  
Для использования приложения локально скачайте этот проект.    
Установите субд PostgreSQL и PgAdmin.    
Создайте базу данных {your_db_name}    
Проверьте настройки подключения к вашей базе данных в файле application.properties.    
~~~
#postgresql config
spring.datasource.url=jdbc:postgresql://localhost/{your_db_name}
spring.datasource.username={your_username}
spring.datasource.password={your_password}
#spring.datasource.initialization=never
spring.jpa.hibernate.ddl-auto=none
spring.servlet.multipart.max-file-size=-1
spring.servlet.multipart.max-request-size=-1
~~~
Скрипты таблиц:
~~~
CREATE TABLE public.client_roles
(
    client_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT fk_client_id FOREIGN KEY (client_id)
        REFERENCES public.clients (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_role_id FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
~~~

~~~
CREATE TABLE public.clients
(
    id serial NOT NULL,
    created date NOT NULL,
    updated date,
    status character varying(25) COLLATE pg_catalog."default" NOT NULL DEFAULT 'ACTIVE'::character varying,
    client_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    role_id integer NOT NULL DEFAULT 1,
    CONSTRAINT clients_pkey PRIMARY KEY (id),
    CONSTRAINT ctr_client_name UNIQUE (client_name)
)
~~~

~~~
CREATE TABLE public.questions
(
    question_text text COLLATE pg_catalog."default" NOT NULL,
    question_type character varying(30) COLLATE pg_catalog."default" NOT NULL,
    question_id serial NOT NULL,
    CONSTRAINT questions_pkey PRIMARY KEY (question_id)
)

~~~

~~~
CREATE TABLE public.roles
(
    id serial NOT NULL,
    created date NOT NULL DEFAULT CURRENT_DATE,
    updated date DEFAULT CURRENT_DATE,
    status character varying(30) COLLATE pg_catalog."default" NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
)
~~~

~~~
CREATE TABLE public.survey_question
(
    survey_id bigint NOT NULL,
    question_id bigint NOT NULL
)
~~~
~~~
CREATE TABLE public.surveys
(
    id serial NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    start_date date NOT NULL,
    end_date date,
    description text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT surveys_pkey PRIMARY KEY (id)
)
~~~

~~~
CREATE TABLE public.answers
(
    id serial NOT NULL,
    client_id bigint NOT NULL,
    survey_id bigint NOT NULL,
    question_id bigint NOT NULL,
    answer_text text COLLATE pg_catalog."default",
    one_choice_answer integer,
    multi_answer character varying COLLATE pg_catalog."default",
    CONSTRAINT answers_pkey PRIMARY KEY (id)

~~~
Вся документация по endpoint для Rest Api (Swagger ui) будет находится здесь - 
~~~
{ваш лоскадльны адресс}/swagger-ui/index.html       
~~~
Для работы с REST API рекомендуется использовать [**Postman**](https://www.postman.com/).    
