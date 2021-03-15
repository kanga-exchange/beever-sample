# beever-sample

Simple project showing how to use beever library.

To run a test you need to have a running Postgres, Redis and MongoDB - the easiest way is to use docker (there is ready to use `test-docker.sh`).

## Beever Basics

Beever to "framework" stworzony na bazie Vert.X przeznaczony do tworzenia serwerów REST API. Bardzo dobrze nadaje się do tworzenia zarówno prostych jak i bardzo złożonych systemów. Upraszcza i przyspiesza proces tworzenia. Skupiony jest na pracy nad "domeną" problemu, a nie na warstwach logicznych. Daje narzędzia do wygodnego tworzenia
testów jednostkowych. Stosowana jest konwencja CoC (konwencja ponad konfiguracja) - czyli nie potrzeba dużo konfigurować, a wiele rzeczy "dzieje się" automatycznie według z góry okreslonej konwencji.

Podstawowym elementem w tworzeniu serwera przy użyciu Beever jest Handler. Handler to pojedyncza klasa, implementująca interfejs `BeeverHandle`. Handler wykonuje czynność, która musi być prosta i testowalana (testy jednostkowe). Z założenia musi być NIE BLOKUJĄCA - czyli nie może blokować "event loop" na dłużej niż kilka sekund. W czas
wykonania nie wlicza się czas wykonywania innych handlerów lub operacji asynchronicznych wołanych z tego handlera.

### Handlers

Handlers are simple, short and **not blocking** "methods". Each handler can be called by "name". Input and output - JSON Handler can be exposed as REST end point by adding annotation `@POST`, `@GET`.

#### Names of the handlers

Each handler name should end with word `Handler`. Each REST handlers use special name convention:
`PREFIX` `Name` `Handler`

PREFIX determines required Role to call this end point:

- `Public` - everyone can call
- `RoleName` - example: `User` or `Admin` - it names the `role` of the logged user. More about users in **Authorization** section.

Example:
`PublicSomeImportantCallHandler` - public (no user required) end point with path: `/some/important/call`

### Input and Output of the handler

### Authorization

The Beever uses similar to JWT technic to achieve authorization/authentication. To interact with fields of this key some annotations can be used.


