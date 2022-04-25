insert into users
values(nextval('users_user_id_seq'), 'admin@admin.com', '$2a$12$toX0XhUeHDxOYfNkiAtk0.m7Rqy2ZhnJv9zsKpah.e5k/KOFDlTw2', now(), true);

insert into authorities
values(nextval('authorities_authority_id_seq'), 'USER');

insert into authorities
values(nextval('authorities_authority_id_seq'), 'ADMIN');

insert into users_authorities
values(1, 1);

insert into users_authorities
values(1, 2);

insert into animals
values(nextval('animals_animal_id_seq'), 'JimBob','Horse', 'Norwegian mock', 10, 'Fine');