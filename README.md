# Serviciu pentru gestionarea locurilor unde se pot practica sporturi extreme

## Description

Proiectul a fost implementat folosind JDBC in loc de Spring Data JPA.
Motive: A fost primul meu proiect unde trebuie sa lucrez cu Spring si cu baza de date.
De aceea codul scris utilizand Spring Data JPA nu era intotdeauna clar, fiindca nu stiam
cum functioneaza in spate. Din cauza asta am hotarat sa lucren la un nivej mai jos,
direct cu baza de date. Acum, dupa implementarea proiectului si lucrul cu JDBC
stiu cum sa implementez proiectul si in Spring Data JPA(Practic fac acelasi lucru scriind
nai putin cod, nu o sa am nevoie si de package dao), stiind deja cum este implementata
interactiunea cu baza de date.
Am in plan sa rescriu proiect in Spring Data JPA, cel putin pentru experienta proprie,
dar nu e posibil momentan din cauza temelor care au aparut.
Toate functionalitatile au fost testate cu PostMan

---

## Functionality

### GET request

- /countries                - Intoarce o lista cu toate tari
- /countries/{countryName}  - Intoarce o tara anumita
- /regions                  - Intoarce o lista cu toate regiuni
- /regions/{redionName}     - Intoarce o regiune anumita
- /locations                - Intoarce o lista cu toate locatiuni
- /locations/{locationName} - Intoarce o locatiune anumita

### DELETE request

- /countries/{countryName}  - Sterge din baza de date tara {countryName}
- /regions/{regionName}     - Sterge din baza de date regiunea {regionName}
- /locations/{locationName} - Sterge din baza de date localitatea {locationName}
- /sports/{sportName}       - Sterge din baza de data sportul {sportName}

### POST request

- /countries
#### Request body:

     {
       "name" : "{CountryName}"
     }

- /regions 
#### Request body: 

     {
       "name" : "{regionName}",
       "countryName" : "{countryName}"
     }

- /locations
#### Request body:

    {
        "name" : "{locationName}",   
       "regionName" : "{regionName},
       "countryName" : "{countryName}",
       "locationSport":
        [
            {
                "sportName" : "{sportName}",
                "price" : Num,
                "startDate": "yyyy-mm-dd",
                "endDate": "yyyy-mm-dd"
            }
            ...
        ]
    }

- /sports
#### Request body:

    {
        "name": "{sportName}",
        "locations":
        [
            {
                "name": "{locationName}",
                "price" : Num,
                "startDate": "yyyy-mm-dd",
                "endDate": "yyyy-mm-dd"
            }
            ...
        ]
    }

### PUT request

- /locations
#### Request body:

    {
        "name": "{locationName}",
        "regionName": {can be null},
        "countryName" : {can be null},
        "locationSport":
        [
            {
                "sportName" : "{sportName}",
                /* You can specify only information you want to change */
                "price" : Num,
                "startDate": "yyyy-mm-dd",
                "endDate": "yyyy-mm-dd"
            }
            ...
        ]
    }

- /sports
#### Request body:

    {
        "name": "{sportName}",
        "locations":
        [
            {
                "name": "{locationName}",
                /* You can specify only information you want to change */
                "price" : Num,
                "startDate": "yyyy-mm-dd",
                "endDate": "yyyy-mm-dd"
            }
            ...
        ]
    }

### Search by period and sports(POST request)

- /location/sport
#### Request body:

    {
        "startDate": "yyyy-mm-dd" or null,
        "endDate": "yyyy-mm-dd" or null,
        "sports": ["{sportName1}", "{sportName2}", ...] or []
    }

