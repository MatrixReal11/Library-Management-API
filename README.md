# Library Management API

## Ce face aplicatia?
O aplicatie backend pentru gestionarea unei biblioteci.
Poti adauga carti si membri, si poti imprumuta sau returna carti.

## Tehnologii folosite
- Java 17
- Spring Boot - framework-ul principal
- Spring Data JPA - conectare la baza de date
- PostgreSQL - baza de date
- Lombok - elimina codul repetitiv

## Cum functioneaza?
Aplicatia are 4 straturi:
- Model - descrie cum arata datele (Book, Member)
- Repository - vorbeste cu baza de date
- Service - logica aplicatiei
- Controller - primeste cererile din Postman

## Ce poti face cu ea?

### Membri
- Vezi toti membrii
- Cauta un membru dupa email
- Adaugi un membru nou
- Actualizezi un membru
- Stergi un membru

### Carti
- Vezi toate cartile
- Cauti o carte dupa titlu
- Vezi doar cartile disponibile
- Adaugi o carte noua
- Actualizezi o carte
- Stergi o carte
- Imprumuti o carte unui membru
- Returnezi o carte

## Reguli importante
- O carte creata este disponibila by default
- O carte poate fi imprumutata doar daca este disponibila
- La imprumut: available = false, borrowedBy = membrul
- La returnare: available = true, borrowedBy = null

## Cum testezi aplicatia?
Folosind Postman, trimiti cereri HTTP la aplicatie.

### Exemple de cereri:

GET http://localhost:8080/members
- Returneaza toti membrii din baza de date

POST http://localhost:8080/members
- Adauga un membru nou
- Trimiti date JSON: { "firstName": "Stefan", "lastName": "Hodoreanu", "email": "stefan@gmail.com" }

PUT http://localhost:8080/members/1
- Actualizeaza membrul cu id=1

DELETE http://localhost:8080/members/1
- Sterge membrul cu id=1

GET http://localhost:8080/books/search?title=Java
- Cauta carti dupa titlu

GET http://localhost:8080/books/available
- Returneaza doar cartile disponibile

PATCH http://localhost:8080/books/1/borrow/2
- Membrul cu id=2 imprumuta cartea cu id=1
- Cartea devine: available=false, borrowedBy=membrul

PATCH http://localhost:8080/books/1/return
- Returneaza cartea cu id=1
- Cartea devine: available=true, borrowedBy=null
