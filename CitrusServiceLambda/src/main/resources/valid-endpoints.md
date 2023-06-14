
GET USER
curl -v -X GET http://127.0.0.1:3000/user/noahzcain@gmail.com

UPDATE USER
curl -v -X PUT http://127.0.0.1:3000/user/noahzcain@gmail.com             
-d '{"userId":"noahzcain@gmail.com", "firstName": "Noah","lastName": "Cain","gender": "Non-Binary", "dateOfBirth": "1995-03-30", "userInterests": ["cats","food"]}'

CREATE USER
curl -v -X POST http://127.0.0.1:3000/user/create -d '{"userId":"noahcainTEST@gmail.com", "firstName": "Noah","lastName": "Cain","gender": "Non-Binary", "dateOfBirth": "1995-06-30", "userInterests": ["cats","food"]}'

GET PLACE
curl -X GET -i http://127.0.0.1:3000/place/Capstone 

UPDATE INTERESTS
sam build && curl -v -X PUT http://127.0.0.1:3000/user/addInterests -d '{"userId":"noahzcain@gmail.com", "userInterests":["corgis","cooking"]}' \
-H 'Content-Type: application/json'

ADD ACCESSIBILITY TAGS
sam build && curl -X POST -H "Content-Type: application/json" -d '{
"placeId": "Capstone",
"accessibilityTags": ["ramp", "braille", "low-stimulation"]
}' http://127.0.0.1:3000/place/addTags
