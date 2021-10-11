# SWAPI-CLIENT

#### Description
For a marketing campaign collect information about Star Wars universe using open API https://swapi.dev/
Store the following data in the local database:

•	character: Name, Gender, homeworld, and other if it is necessary

•	starship: Name, model, manufacturer and other if it is necessary

Create a report about characters who are using ships with the most (top 10) weight of lading

Develop a simple (lightweight) 3-tire application (front-end, back-end, database).
Back-end (collects data) must:
1. Retrieve a portion of data from API (see in your Variant) and store it in a database
2. Update data on demand
3. Update DB schema if needed on app’s update
   Front-end (outputs data) must:
1. Display any portion of the data stored in the DB
2. Provide a method to trigger data update process
   Database:
1. Choose Database type and data scheme in a suitable manner.
2. Data must be stored in a persistent way
3. It’s better to use cloud native DB solutions like an RDS/AzureSQL/CloudSQL.

#### SQL request
```
select  s.id starship_id, s.name starship_name,  s.cargo_capacity, s.model, s.manufacturer, sc.character_id, c.name character_name, c.gender character_gender, p.name character_homeworld
from Starship
join Starships_Characters sc on sc.starship_id=s.id
join Character c on c.id=sc.character_id
join Planet p on p.id=c.planet_id
order by s.cargo_capacity desc
limit 10

```

#### List of available endpoints:

1. GET http://swapi-client.niks.cloud/report

    А report about characters who are using ships with the most (top 10) weight of lading


2. GET http://swapi-client.niks.cloud/starships

    List of starships

3. GET http://swapi-client.niks.cloud/starships/{id}

    Particular starship

4. POST http://swapi-client.niks.cloud/starships/update

    Update particular starship

5. GET http://swapi-client.niks.cloud/characters

    List of characters

5. GET http://swapi-client.niks.cloud/characters/{id}

    Particular character

6. POST http://swapi-client.niks.cloud/characters/update

    Update particular character

7. GET http://swapi-client.niks.cloud/planets

    List of planets

8. GET http://swapi-client.niks.cloud/planets/{id}

    Particular planet

9. POST http://swapi-client.niks.cloud/planets/update

    Update particular planet

10. GET http://swapi-client.niks.cloud/init

    Init dataset

#### Example of update request:

```
curl -X POST -H "Content-Type: application/json" -d '{"name":"Super Name", "cargo_capacity":"1", "url":"https://swapi.dev/api/starships/13/","pilots":["https://swapi.dev/api/people/13/"]}' "http://swapi-client.niks.cloud/starships/update"
```

