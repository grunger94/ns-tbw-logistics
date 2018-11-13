insert into office (name) values ('CUU');
insert into office (name) values ('HMO');
insert into office (name) values ('CDMX');

insert into person (name, office_id) values ('Rafael Alejandro Manrique Zamora', 1);
insert into person (name, office_id) values ('Juan Daniel Amparán De La Garza', 1);
insert into person (name, office_id) values ('Norma Amaya', 1);
insert into person (name, office_id) values ('Erika Soto', 2);
insert into person (name, office_id) values ('Luisa Yañes', 2);
insert into person (name, office_id) values ('Martha Urrea', 3);

insert into office_manager (office_id, person_id) values (1, 3);
insert into office_manager (office_id, person_id) values (2, 4);
insert into office_manager (office_id, person_id) values (2, 5);
insert into office_manager (office_id, person_id) values (3, 6);

insert into daily_activity (name, description, date, duration_in_hours, place, attendance_limit)
  values ('Carnita asada', 'Nos reunimos a la llegada de los foraneos para una carne asada', CURRENT_DATE(), 4, 'La mansion', 0);
insert into daily_activity (name, description, date, duration_in_hours, place, attendance_limit)
  values ('Team building day', 'Trabajamos en actividades de integracion e innovacion', CURRENT_DATE(), 8, 'Hotel X', 0);

insert into ride_type (description) values ('going ride');
insert into ride_type (description) values ('ride back');

insert into ride (daily_activity_id, driver_id, available_seats, type) values (1, 4, 2, 1);
insert into ride (daily_activity_id, driver_id, available_seats, type) values (1, 5, 2, 1);
insert into ride (daily_activity_id, driver_id, available_seats, type) values (1, 5, 4, 2);

insert into ride_passenger (ride_id, person_id) values (1, 1);
insert into ride_passenger (ride_id, person_id) values (1, 2);
insert into ride_passenger (ride_id, person_id) values (2, 3);
insert into ride_passenger (ride_id, person_id) values (2, 6);
insert into ride_passenger (ride_id, person_id) values (3, 1);
insert into ride_passenger (ride_id, person_id) values (3, 2);
insert into ride_passenger (ride_id, person_id) values (3, 3);
insert into ride_passenger (ride_id, person_id) values (3, 6);