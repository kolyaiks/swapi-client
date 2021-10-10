For a marketing campaign collect information about Star Wars universe using open API https://swapi.dev/

Store the following data in the local database:

character: Name, Gender, homeworld, and other if it is necessary
starship: Name, model, manufacturer and other if it is necessary

Create a report about characters who are using ships with the most (top 10) weight of lading

SELECT s.id starship_id, s.name starship_name, s.cargo_capacity, sc.character_id, c.name character_name
FROM STARSHIP s
join STARSHIPS_CHARACTERS sc on s.id=sc.STARSHIP_ID
join CHARACTER c on sc.CHARACTER_ID = c.id
order by s.cargo_capacity desc
limit 10



{"name":"Jedi Interceptor","cargoCapacity":60,"model":"Eta-2 Actis-class light interceptor","manufacturer":"Kuat Systems Engineering","url":"https://swapi.dev/api/starships/65/","pilots":["https://swapi.dev/api/people/10/","https://swapi.dev/api/people/11/"]}

curl -X POST -H "Content-Type: application/json" -d '{"name":"Super Name", "cargo_capacity":1, "url":"https://swapi.dev/api/starships/13/","pilots":["https://swapi.dev/api/people/13/"]}' "http://localhost:8080/starships/update"
