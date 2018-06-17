CREATE TABLE Questions (
    id bigint,
    question TEXT,
    constraint pk_question primary key (id)
) engine=InnoDB character set=utf8 collate=utf8_hungarian_ci;

CREATE TABLE Answers (
    id bigint auto_increment,
    text VARCHAR(200),
    point int,
    question bigint,
    constraint pk_answer primary key (id),
    constraint fk_answer_question foreign key (question) references Questions(id)
)engine=InnoDB character set=utf8 collate=utf8_hungarian_ci;

CREATE TABLE Results (
    id bigint auto_increment,
    userName VARCHAR(100),
    startTime DATETIME,
    endTime DATETIME,
    result int,
    maxPoint int,
    constraint pk_result primary key (id)
)engine=InnoDB character set=utf8 collate=utf8_hungarian_ci;

INSERT INTO Questions (id, question) VALUES
    (1,'Adott az alábbi kód. Melyik kifejezés ad vissza hamisat?<br><code>class Animal {}<br>class Mammal extends Animal {}<br>class Deer extends Mammal {}</code>'),
    (2,'Melyik egész adattípus?'),
    (3,'Melyik sor ad fordítási hibát?<br><code>3: List&lt;Integer&gt; list = new ArrayList&lt;&gt;();<br>4: list.add(10);<br>5: list.add(14);<br>6: for(int x : list){<br>7: &nbsp;&nbsp;System.out.print(x + ", ");<br>8: &nbsp;&nbsp;break;<br>9: }</code>'),
    (4,'Az interface-ek mely módosító szót tartalmazzák implicit módon?'),
    (5,'Az alábbiak közül melyik kifejezés igaz?');

INSERT INTO Answers (text, point, question) VALUES
    ('<code>new Mammal() instanceof Deer</code>', 1, 1),
    ('<code>new Deer() instanceof Mammal</code>', 0, 1),
    ('<code>new Deer() instanceof Animal</code>', 0, 1),
    ('<code>new Deer() instanceof Object</code>', 0, 1),
    ('<code>int</code>', 1, 2),
    ('<code>double</code>', 0, 2),
    ('<code>float</code>', 0, 2),
    ('<code>Double</code>', 0, 2),
    ('10, 14,', 0,3),
    ('10, 14', 0,3),
    ('10,', 1,3),
    ('A kód nem fordul le a 6. sor miatt', 0,3),
    ('A kód nem fordul le a 8. sor miatt', 0,3),
    ('A kód végtelen ciklust tartalmaz és nem ér véget', 0,3),
    ('<code>protected</code>', 0,4),
    ('<code>static</code>', 0,4),
    ('<code>void</code>', 0,4),
    ('<code>abstract</code>', 1,4),
    ('<code>default</code>', 0,4),
    ('a <code>compare()</code> a <code>Comparable</code> interface metódusa', 0,5),
    ('a <code>compare()</code> a <code>Comparator</code> interface metódusa', 1,5),
    ('a <code>compare()</code> metódusnak egy paramétere van', 0,5),
    ('a <code>compareTo()</code> metódusnak két paramétere van', 0,5);