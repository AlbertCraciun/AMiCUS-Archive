create table countries (
                           country_id 		UUID,
                           country_name 	text not null,
                           last_update_on 	TIMESTAMP,
                           created_on 		TIMESTAMP not null,
                           primary key (country_id)
);

create table cities (
                        city_id 		UUID,
                        city_name 		text not null,
                        country_id 		UUID,
                        last_update_on 	TIMESTAMP,
                        created_on 		TIMESTAMP not null,
                        primary key(city_id),
                        foreign key(country_id) references countries(country_id)
);

create table branches (
                          branch_id 		UUID,
                          city 			UUID not null,
                          foundation_year INTEGER,
    -- history_id?
                          last_update_on 	TIMESTAMP,
                          created_on 		TIMESTAMP not null,
                          primary key(branch_id),
                          foreign key(city) references cities(city_id)
);

create table departments (
                             department_id 	UUID,
                             "name"			text,
                             last_update_on 	TIMESTAMP,
                             created_on 		TIMESTAMP not null,
                             primary key(department_id)
);

CREATE TYPE privilege_names AS ENUM ('superadmin', 'admin', 'user');
CREATE TYPE academic_status AS ENUM ('not started', 'enroled', 'finished');
CREATE TYPE event_types AS ENUM ('festival', 'congres');
create type role_names as enum ( 'COORDONATOR_DEPARTAMENT',
    'PRESEDINTE_FILIALA',
    'CAPELAN',
    'DIRECTOR_DE_TINERET',
    'MEMBRU',
    'PRESEDINTE_NATIONAL',
    'SECRETAR_DEPARTAMENT',
    'VICEPRESEDINTE',
    'MEMBRU_SENIOR' );

CREATE table privileges (
                            privilege_id 	UUID,
                            "name" 			privilege_names not null,
                            active_days		INTEGER not null,
                            last_update_on 	TIMESTAMP,
                            created_on 		TIMESTAMP not null,
                            PRIMARY KEY (privilege_id)
);

create table user_roles (
                            role_id 		UUID,
                            privilege		UUID,
                            "name" 			role_names,
                            department 		UUID,
                            start_date		TIMESTAMP,
                            last_update_on 	TIMESTAMP,
                            created_on 		TIMESTAMP not null,
                            primary key(role_id),
                            foreign key(department) references departments(department_id)
);

create table universities (
                              university_id	UUID,
                              city			UUID,
                              "name" 			text,
                              last_update_on 	TIMESTAMP,
                              created_on 		TIMESTAMP not null,
                              primary key(university_id),
                              foreign key(city) references cities(city_id)
);

create table faculties (
                           faculty_id 		UUID,
                           university		UUID,
                           "name"			text,
                           years			INTEGER,
                           last_update_on 	TIMESTAMP,
                           created_on 		TIMESTAMP not null,
                           primary key(faculty_id),
                           foreign key(university) references universities(university_id)
);

create table users (
                       user_id 		UUID,
                       last_name 		text not null,
                       first_name 		text not null,
                       email 			text not null,
                       password		text NOT null,
                       birth_date 		DATE not null,
                       branch 			UUID not null,

                       faculty			UUID not null,
                       bcd_start_year	INTEGER,
                       bcd_status		academic_status,
                       md_status		academic_status,
                       dd_status		academic_status,

                       user_role 		UUID not null,
                       approved_role_by UUID,
                       approved_role_on TIMESTAMP,
                       last_update_on 	TIMESTAMP,
                       created_on 		TIMESTAMP not null,
                       primary key(user_id),
                       foreign key(branch) references branches(branch_id),
                       foreign key(user_role) references user_roles(role_id),
                       foreign key(faculty) references faculties(faculty_id)
);

create table events (
                        event_id		UUID,
                        "name"			text,
                        "type"			event_types,
                        city			UUID,
                        start_date		DATE,
                        end_date		DATE,
                        description		text,
                        participants	INTEGER,
                        last_update_on 	TIMESTAMP,
                        created_on 		TIMESTAMP not null,
                        primary key(event_id),
                        foreign key(city) references cities(city_id)
);

create table projects (
                          project_id 		UUID,
                          branch			UUID,
                          department		UUID,
                          "name"			text,
                          editions		INTEGER,
                          start_year		INTEGER,
                          last_ed_date 			DATE,
                          last_ed_participants 	INTEGER,
                          last_ed_expenses		INTEGER,
                          objectives		text,
                          description		text,
                          last_update_on 	TIMESTAMP,
                          created_on 		TIMESTAMP not null,
                          primary key(project_id),
                          foreign key(branch) references branches(branch_id),
                          foreign key(department) references departments(department_id)
);

CREATE TABLE project_relations (
                                   relation_id    UUID PRIMARY KEY,
                                   project_id_1   UUID NOT NULL,
                                   project_id_2   UUID NOT NULL,
                                   relation_type  TEXT,
                                   created_on     TIMESTAMP NOT NULL,
                                   last_update_on TIMESTAMP,
                                   FOREIGN KEY (project_id_1) REFERENCES projects (project_id),
                                   FOREIGN KEY (project_id_2) REFERENCES projects (project_id)
);