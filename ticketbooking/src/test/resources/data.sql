INSERT INTO movies (create_time, update_time, director, duration_minutes, language, name, producer, release_date) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "Terence Fisher", 87, 0, "The Hound of Baskervilles", "Anthony Hinds", STR_TO_DATE('4,5,1959','%d,%m,%Y'));
INSERT INTO movies (create_time, update_time, director, duration_minutes, language, name, producer, release_date) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "The Wachowskis", 136, 0, "The Matrix", "Joel Silver", STR_TO_DATE('24,3,1999','%d,%m,%Y'));
INSERT INTO movies (create_time, update_time, director, duration_minutes, language, name, producer, release_date) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "The Wachowskis", 138, 0, "The Matrix Reloaded", "Joel Silver", STR_TO_DATE('15,5,2003','%d,%m,%Y'));
INSERT INTO movies (create_time, update_time, director, duration_minutes, language, name, producer, release_date) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "The Wachowskis", 129, 0, "The Matrix Revolutions", "Joel Silver", STR_TO_DATE('5,11,2003','%d,%m,%Y'));
INSERT INTO movies (create_time, update_time, director, duration_minutes, language, name, producer, release_date) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "Frank Darabont", 142, 0, "The Matrix Revolutions", "Niki Matvin", STR_TO_DATE('10,9,1994','%d,%m,%Y'));
INSERT INTO movies (create_time, update_time, director, duration_minutes, language, name, producer, release_date) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "Client Eastwood", 133, 0, "Invictus", "Client Eastwood", STR_TO_DATE('11,12,2009','%d,%m,%Y'));
INSERT INTO movies (create_time, update_time, director, duration_minutes, language, name, producer, release_date) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "Tom Shadyac", 101, 0, "Bruce Almighty", "Tom Shadyac", STR_TO_DATE('14,5,2003','%d,%m,%Y'));
INSERT INTO movies (create_time, update_time, director, duration_minutes, language, name, producer, release_date) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "Peter Jackson", 178, 0, "Lord of the Rings (LOTR) The Fellowship of the Ring", "Peter Jackson", STR_TO_DATE('10,12,2001','%d,%m,%Y'));
INSERT INTO movies (create_time, update_time, director, duration_minutes, language, name, producer, release_date) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "Peter Jackson", 179, 0, "Lord of the Rings (LOTR) The Two Towers", "Peter Jackson", STR_TO_DATE('5,12,2002','%d,%m,%Y'));
INSERT INTO movies (create_time, update_time, director, duration_minutes, language, name, producer, release_date) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "Peter Jackson", 201, 0, "Lord of the Rings (LOTR) The Return of the King", "Peter Jackson", STR_TO_DATE('1,12,2003','%d,%m,%Y'));


INSERT INTO cinemas (create_time, update_time, name, address, city) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "PVR", "101 new Street", "Bangalore");
INSERT INTO cinemas (create_time, update_time, name, address, city) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "Harihar Talkies", "purane mandir ke saamne", "Bangalore");
INSERT INTO cinemas (create_time, update_time, name, address, city) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "Inox", "102 the other Street", "Bangalore");
INSERT INTO cinemas (create_time, update_time, name, address, city) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "PVR", "420 the fabulous Street", "Bangalore");
INSERT INTO cinemas (create_time, update_time, name, address, city) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "Carnival Cinema", "Adjacent to Inox Cinema", "Bangalore");
INSERT INTO cinemas (create_time, update_time, name, address, city) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "Big Cinema", "just before Small Cinema", "Bangalore");
INSERT INTO cinemas (create_time, update_time, name, address, city) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "Harihar Talkies", "103 bloody old Street", "Pune");
INSERT INTO cinemas (create_time, update_time, name, address, city) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), "Harihar Talkies", "410 back passage road", "Mumbai");

INSERT INTO halls (create_time, update_time, hall_number, seat_count, cinema_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 4, (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore"));
INSERT INTO halls (create_time, update_time, hall_number, seat_count, cinema_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 4, (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore"));
INSERT INTO halls (create_time, update_time, hall_number, seat_count, cinema_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 3, 4, (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore"));
INSERT INTO halls (create_time, update_time, hall_number, seat_count, cinema_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 4, (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore"));
INSERT INTO halls (create_time, update_time, hall_number, seat_count, cinema_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 1, 4, (SELECT id from cinemas where name = "PVR" AND address = "420 the fabulous Street" AND city = "Bangalore"));
INSERT INTO halls (create_time, update_time, hall_number, seat_count, cinema_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 2, 4, (SELECT id from cinemas where name = "PVR" AND address = "420 the fabulous Street" AND city = "Bangalore"));
INSERT INTO halls (create_time, update_time, hall_number, seat_count, cinema_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 3, 4, (SELECT id from cinemas where name = "PVR" AND address = "420 the fabulous Street" AND city = "Bangalore"));
INSERT INTO halls (create_time, update_time, hall_number, seat_count, cinema_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 4, 4, (SELECT id from cinemas where name = "PVR" AND address = "420 the fabulous Street" AND city = "Bangalore"));


INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 0, 0, (SELECT id from halls where hall_number = 1 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));
INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 1, 0, (SELECT id from halls where hall_number = 1 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));
INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 2, 0, (SELECT id from halls where hall_number = 1 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));
INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 3, 0, (SELECT id from halls where hall_number = 1 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));
INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 0, 0, (SELECT id from halls where hall_number = 2 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));
INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 1, 0, (SELECT id from halls where hall_number = 2 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));
INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 2, 0, (SELECT id from halls where hall_number = 2 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));
INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 3, 0, (SELECT id from halls where hall_number = 2 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));
INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 0, 0, (SELECT id from halls where hall_number = 3 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));
INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 1, 0, (SELECT id from halls where hall_number = 3 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));
INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 2, 0, (SELECT id from halls where hall_number = 3 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));
INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 3, 0, (SELECT id from halls where hall_number = 3 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));
INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 0, 0, (SELECT id from halls where hall_number = 4 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));
INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 1, 0, (SELECT id from halls where hall_number = 4 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));
INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 2, 0, (SELECT id from halls where hall_number = 4 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));
INSERT INTO seats (create_time, update_time, is_undermaintainance, seat_location, seat_type, hall_id) values(UTC_TIMESTAMP(), UTC_TIMESTAMP(), 0, 3, 0, (SELECT id from halls where hall_number = 4 AND cinema_id = (SELECT id from cinemas where name = "PVR" AND address = "101 new Street" AND city = "Bangalore")));

INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('09,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('12,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 1 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "The Matrix"));
INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('12,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('15,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 1 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "The Matrix"));
INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('15,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('18,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 1 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "The Matrix"));
INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('18,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('21,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 1 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "The Matrix"));
INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('09,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('12,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 2 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "Bruce Almighty"));
INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('12,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('15,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 2 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "Lord of the Rings (LOTR) The Fellowship of the Ring"));
INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('15,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('18,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 2 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "Bruce Almighty"));
INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('18,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('21,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 2 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "Lord of the Rings (LOTR) The Fellowship of the Ring"));
INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('15,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('18,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 3 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "Lord of the Rings (LOTR) The Two Towers"));
INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('18,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('21,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 3 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "Lord of the Rings (LOTR) The Two Towers"));
INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('09,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('12,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 3 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "Lord of the Rings (LOTR) The Return of the King"));
INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('12,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('15,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 3 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "Lord of the Rings (LOTR) The Return of the King"));
INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('15,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('18,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 4 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "Bruce Almighty"));
INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('18,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('21,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 4 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "Bruce Almighty"));
INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('09,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('12,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 4 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "Bruce Almighty"));
INSERT INTO shows (create_time, update_time, start_time, end_time, hall_id, movie_id) values (UTC_TIMESTAMP(), UTC_TIMESTAMP(), STR_TO_DATE('12,0,1,1,2022','%H,%i,%d,%m,%Y'), STR_TO_DATE('15,0,1,1,2022','%H,%i,%d,%m,%Y'), (SELECT halls.id FROM halls INNER JOIN cinemas ON halls.cinema_id = cinemas.id where halls.id = 4 AND cinemas.name = "PVR" AND cinemas.address = "101 new Street" AND cinemas.city = "Bangalore"), (SELECT id FROM movies WHERE name = "Bruce Almighty"));