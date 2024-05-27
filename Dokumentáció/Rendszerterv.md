# Rendszerterv

## 1. A rendszer célja
A rendszer célja az, hogy egy vállalkozás számára nyújtson egy másik opciót az albumok eladására. A vállalkozásnak nincsen online felülete, csak személyesen vehetnek albumokat a vásárlók az üzleteikben. A profit növelésének érdekében a boltok mellett egy online felületet szeretne a megrendelő. A rendszer lehetővé teszi a felhasználók számára, hogy regisztráljanak és a fiókukba történő belépés után leadhatják a rendeléseiket. Ezek után a felhasználó megtekintheti a megrendelésének listáját.

## 2. Projektterv
Az alkalmazás Java nyelven készül grafikus felülettel, az adatok tárolására PHP mysql-t használunk. Mindketten front- és backenden is dolgozunk.
A megrendelő azt kérte, hogy egyszerű és könnyen használható legyen a felület kinézete, így a frontend kisebb szerepet kap a fejlesztésben.
Ezzel ellentétben a legfőbb fókszt a backend kapja.

## 3. Üzleti folyamatok modellje
![image](https://github.com/Vanessza02/Progtech/assets/78543866/1b3acdeb-4de3-4daf-94fc-502548e51634)


## 4. Követelmények
 - Felhasználók, albumok és rendelési adatok tárolása adatbázisban.
 - Rendelés létrehozása az albumok kosárba helyezésével.
 - Kosárban lévő elemek megjelenítése/törlése.
 - Adatvédelem.

## 5. Funkcionális terv
 - Guest: Csak regisztrálni tud, semmilyen más jogosultsággal nem rendelkezik így nem lát rá az alkalmazás többi részére sem.
 - User: Sikeres regisztráció és bejelentkezés után a felhasználó megtekintheti az albumokat, ezeket a kosarába helyezheti. A kosarából eltávolíthatja az albumokat.

## 6. Fizikai környezet
Az alkalmazás használatához regisztrálnia kell a felhasználónak, anélkül nem adhatja le a rendelését.
 - Nincsenek megvásárolt komponenseink.
 - Fejlesztő eszköz: IntelliJ IDEA 2023.2.5

## 7. Adatbázis terv

![localhost _ 127 0 0 1 _ albumshop _ phpMyAdmin 5 2 1 - Google Chrome 2024  05  27  20_32_38](https://github.com/Vanessza02/Progtech/assets/114110363/588add08-dc4e-4265-b7a3-ae1c92302461)


## 8. Tesztterv 
Az alkalmazás teszteléséhez unit teszteket fogunk alkalmazni, ami a programkód zömét lefedi. A tesztek elsődleges célja az eddig meglévő funkcióknak, metódusoknak tesztelése.

- Adatbázis tesztelése
- Adatok beolvasása és visszaadása
- Gombok tesztelése
- CRUD műveletek tesztelése
- Biztonság ellenőrzése (megfelelően vannak-e védve az adatok, jelszó titkosítva van-e)
- Hibaüzenetek tesztelése (felhasználó által bevitt hibás adatok esetén)

| Teszt neve                          | Eredmény       |
|-------------------------------------|----------------|
|Regisztráció helyes adatokkal|Sikeres regisztráció|
|Regisztráció helytelen adatokkal|Sikertelen regisztráció|
|Bejelentkezés helyes adatokkal|Sikeres bejelentkezés|
|Bejelentkezés helytelen adatokkal|Sikertelen bejelentkezés|
|Album megjelenítése| Sikeres megjelenítés|
|Rendelés létrehozása|Sikeres létrehozás|
|Kosárban lévő elemek megjelenítése|Sikeres megjelenítés|
|Tétel törlése kosárból|Sikeres törlés|


## 9. Telepítési terv
Nem szükséges telepítés az alkalmazás használatához.

## 10. Implementációs terv
Az alkalmazást Java programnyelven implementáljuk, a felhasználók adatait MySQL adatbázisban fogjuk eltárolni, phpMyAdmin-t használva.

### Funkciók:
- Regisztráció
- Bejelentkezés
- Kosár kilistázása
- Album kosárba tétele
- Tétel törlése kosárból

## 11. Karbantartási terv
- Egyes bugok javítása
- Design korszerűsítése
- Újabb, frissebb megoldások implementálása
