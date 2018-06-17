CREATE TABLE Questions (
    id bigint,
    question VARCHAR(200),
    constraint pk_question primary key (id)
);

CREATE TABLE Answers (
    id bigint auto_increment,
    text VARCHAR(200),
    point int,
    question bigint,
    constraint pk_answer primary key (id),
    constraint fk_answer_question foreign key (question) references Questions(id)
);

CREATE TABLE Results (
    id bigint auto_increment,
    userName VARCHAR(100),
    startTime DATETIME,
    endTime DATETIME,
    result int,
    maxPoint int,
    constraint pk_result primary key (id)
);

INSERT INTO Questions (id, question) VALUES
    (1,'Adott az alábbi kód. Melyik kifejezés ad vissza hamisat?<br><code>class Animal {}<br>class Mammal extends Animal {}<br>class Deer extends Mammal {}</code>'),
    (2, 'Árvíztűrő tükörfúrógép');

INSERT INTO Answers (text, point, question) VALUES
    ('<code>new Mammal() instanceof Deer</code>', 1, 1),
    ('<code>new Deer() instanceof Mammal</code>', 0, 1),
    ('<code>new Deer() instanceof Animal</code>', 0, 1),
    ('<code>new Deer() instanceof Object</code>', 0, 1),
    ('2a válasz', 1, 2),
    ('2b válasz', 0, 2),
    ('2c válasz', 0, 2),
    ('2d válasz', 0, 2);