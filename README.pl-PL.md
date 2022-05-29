Dostępna jest rowniez [wersja angielskojęzyczna](./README.md) tego pliku readme

# beever-sample

Prosty projekt demonstrujacy uzytek biblioteki Beever.

Aby uruchomic test potrzebny jest dzialajacy Postgres, Redis i MongoDB - najprostszym
sposobem na ich przygotowanie jest uzycie dockera (uzywajac `test-docker.sh`, skryptu powloki
ktory przygotowalismy).

## Podstawy Beevera

Beever to "framework" stworzony na bazie [Vert.X][1] przeznaczony do tworzenia
serwerów REST API.  Bardzo dobrze nadaje się do tworzenia zarówno prostych jak
i bardzo złożonych systemów.  Upraszcza i przyspiesza proces tworzenia.
Skupiony jest na pracy nad "domeną" problemu, a nie na warstwach logicznych.
Daje narzędzia do wygodnego tworzenia testów jednostkowych.  Stosowana jest
konwencja CoC (konwencja ponad konfiguracja) - czyli nie potrzeba dużo
konfigurować, a wiele rzeczy "dzieje się" automatycznie według z góry
okreslonej konwencji.

Podstawowym elementem w tworzeniu serwera przy użyciu Beever jest Handler.
Handler to pojedyncza klasa, implementująca interfejs `BeeverHandle`. Handler
wykonuje czynność, która musi być prosta i testowalna (testy jednostkowe). Z
założenia musi być NIE BLOKUJĄCA - czyli nie może blokować "event loop" na
dłużej niż kilka sekund. W czas wykonania nie wlicza się czas wykonywania
innych handlerów lub operacji asynchronicznych wołanych z tego handlera.

### Handlery 
Handlery to proste, krotkie i nie blokujące metody.  Kazdy handler
moze byc wywolany "nazwą". Input i output - Handler JSON moze byc odslonięty
jako punkt koncowy REST dodajac annotacje `@POST`, `@GET`.

#### Nazwy handlerow

Kazda nazwa handlera powinna konczyc sie na slowo `Handler`. Kazdy REST handler
uzywa specjalnej konwencji nazwy: `<PREFIX><Name>Handler`, gdzie `<PREFIX>`
jest podmieniony na jeden z ponizszych specyfikatorow roli:

- `Public` - dla ktore moga byc przez kazdego
- `<RoleName>` np. `User` lub `Admin` - nazywa `role` zalogowanego uzytkownika.
  Wiecej o uzytkownikach w sekcji [Autoryzacja](#autoryzacja).

Przyklad: `PublicSomeImportantCallHandler` - 'publiczny' (nie sa wymagane
specjalne) punkt koncowy ze sciezka: `/some/imprtant/call`. 

### Autoryzacja

Beever uzywa techniki podobnej do [JWT][2] do osiagniecia
autoryzacji/autentykacji.  Aby wejsc w interakcje z polami tego klucza moze byc
uzywane kilka anotacji.

[1]: https://en.wikipedia.org/wiki/Vert.x 
[2]: https://en.wikipedia.org/wiki/JSON_Web_Token
