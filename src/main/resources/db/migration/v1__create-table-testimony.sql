CREATE TABLE testimony
(
    id       UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name     VARCHAR(50)  NOT NULL,
    deponent VARCHAR(255) NOT NULL,
    image    VARCHAR(255) NOT NULL

)