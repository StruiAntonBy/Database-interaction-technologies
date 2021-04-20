CREATE TABLE "user" (
    "id"                  SERIAL   PRIMARY KEY,
    "login"               TEXT     NOT NULL UNIQUE,
    "password"            TEXT     NOT NULL,
    "role"                SMALLINT NOT NULL CHECK ("role" IN (1, 2, 3))
    /*
        1 - ADMIN
        2 - Software tester
        3 - Client
    */
);

CREATE TABLE "software_tester" (
    "id"                  INTEGER  PRIMARY KEY REFERENCES "user"    ON UPDATE RESTRICT ON DELETE CASCADE, /* связь один-к-одному */
    "surname"             TEXT     NOT NULL,
    "name"                TEXT     NOT NULL,
	"middle_name"         TEXT     NOT NULL,
	"work_experience"     INTEGER  NOT NULL
);

CREATE TABLE "client" (
    "id"                  INTEGER  PRIMARY KEY REFERENCES "user"    ON UPDATE RESTRICT ON DELETE CASCADE, /* связь один-к-одному */
    "name"                TEXT     NOT NULL,
    "registered_address"  TEXT     NOT NULL,
    "bank_details"        TEXT     NOT NULL
);

CREATE TABLE "contact_person" (
"id" INTEGER  PRIMARY KEY REFERENCES "client"    ON UPDATE RESTRICT ON DELETE CASCADE,
"surname"             TEXT     NOT NULL,
"name"                TEXT     NOT NULL,
"middle_name"         TEXT     NOT NULL
);

CREATE TABLE "phone" (
    "id"				  INTEGER PRIMARY KEY,
    "number"              TEXT    NOT NULL,
    "contact_person_id"   INTEGER  NOT NULL REFERENCES "contact_person"     ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE "e-mail" (
    "id"				  INTEGER PRIMARY KEY,
"login" TEXT     NOT NULL,
"contact_person_id"   INTEGER  NOT NULL REFERENCES "contact_person"     ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE "project" (
   "id"				  INTEGER PRIMARY KEY,
   "client_id"        INTEGER NOT NULL REFERENCES "client"    ON UPDATE RESTRICT ON DELETE CASCADE,
   "start_date"        DATE NOT NULL,
   "end_date"          DATE 
);

CREATE TABLE "requirement"(
 "id"				  INTEGER PRIMARY KEY,
 "project_id"         INTEGER NOT NULL REFERENCES "project"    ON UPDATE RESTRICT ON DELETE CASCADE,
 "requirement"        TEXT NOT NULL,
 "start_date"         DATE,
 "planned_time"       INTEGER NOT NULL,
 "the_priority_of"    TEXT NOT NULL,
 "level_of_criticality_for_the_client" TEXT NOT NULL,
 "a_mark_of_completion" TEXT,
 "the_probability_of_a_change" TEXT NOT NULL
);

CREATE TABLE "planed_test"(
 "id"				  INTEGER PRIMARY KEY,
 "requirement_id"     INTEGER NOT NULL REFERENCES "requirement"    ON UPDATE RESTRICT ON DELETE CASCADE,
 "description_of_the_performance" TEXT NOT NULL,
 "expected_result" TEXT NOT NULL,
 "planned_time"     INTEGER NOT NULL,
 "level_test" TEXT NOT NULL
);

CREATE TABLE "completed_test"(
 "id"                  INTEGER  PRIMARY KEY REFERENCES "planed_test"    ON UPDATE RESTRICT ON DELETE CASCADE,
 "tester_id" INTEGER NOT NULL REFERENCES "project"    ON UPDATE RESTRICT ON DELETE CASCADE,
 "start_date_and_time" DATETIME NOT NULL,
 "length" INTEGER NOT NULL,
 "result" TEXT NOT NULL
);