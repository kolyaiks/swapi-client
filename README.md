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
