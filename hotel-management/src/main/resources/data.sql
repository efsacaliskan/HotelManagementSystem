INSERT INTO EVENTS
(ID, NAME,DESCRIPTION , EVENT_DATE_TIME,LOCATION )
VALUES(1, 'Party', 'New Year Party, Must wear masks','2023-12-29', 'Hotel Restaurant');

INSERT INTO GUESTS
(ID, NAME , CONTACT_NUMBER, EMAIL, ADDRESS)
VALUES(1, 'Phil', '054320394','phil@gmail.com', 'USA, UTAH');

INSERT INTO GUESTS
(ID, NAME , CONTACT_NUMBER, EMAIL, ADDRESS)
VALUES(2, 'Jack', '053420332','jack@gmail.com', 'USA, California');


INSERT INTO MANAGERS
(ID, NAME , CONTACT_NUMBER,EMAIL,DEPARTMENT)
VALUES(1, 'Max', '054320393','max@gmail.com', 'Front Desk');

INSERT INTO ROOMS
(ID, ROOM_NUMBER , type ,IS_AVAILABLE,PRICE)
VALUES(10, 1, 'KingSuite', 1, 1200);

INSERT INTO ROOMS
(ID, ROOM_NUMBER , type ,IS_AVAILABLE,PRICE)
VALUES(11, 2, 'JuniorSuite', 1, 600);
INSERT INTO ROOMS
(ID, ROOM_NUMBER , type ,IS_AVAILABLE,PRICE)
VALUES(12, 3, 'NormalSuite', 1, 700);
INSERT INTO ROOMS
(ID, ROOM_NUMBER , type ,IS_AVAILABLE,PRICE)
VALUES(13, 4, 'KingSuite', 1, 1200);

INSERT INTO RESERVATIONS
(ID, guest_id, room_id , START_DATE,END_DATE,STATUS)
VALUES(100, 1, 10, '2023-12-25','2023-12-30', 'CONFIRMED');

INSERT INTO RESERVATIONS
(ID, guest_id, room_id , START_DATE,END_DATE,STATUS)
VALUES(101, 1, 11, '2023-12-25','2023-12-30', 'CONFIRMED');

INSERT INTO RESERVATIONS
(ID, guest_id, room_id , START_DATE,END_DATE,STATUS)
VALUES(102, 2, 12, '2023-12-25','2023-12-30', 'CONFIRMED');
INSERT INTO RESERVATIONS
(ID, guest_id, room_id , START_DATE,END_DATE,STATUS)
VALUES(103, 2, 13, '2023-12-25','2023-12-30', 'CONFIRMED');

INSERT INTO HOUSEKEEPING
(ID, room_id ,STATUS,NOTES)
VALUES(1000, 10,'CLEANED','Guests dont want it cleaned till the weekend');
