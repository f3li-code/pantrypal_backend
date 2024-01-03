### Backend Routes ###

1. /generate, POST
Make a request to ChatGPT's endpoint with request's meal type and ingredients
give back an assembled recipe object
request:
   RecipeReq {mealType: String, ingredients: String, isReal: boolean}
response: 
   Recipe {title: String, mealType: String, ingredients: String, instructions: String}

1. 
   - /recipe, GET
   Get all the recipes
   - /recipe, POST
   save a recipe in the db
   - /recipe, PUT
   update a recipe in the db, can update title, mealType, ingredients, instructions
   - /recipe, DELETE
   delete a recipe from the db

Tue Dec 26 2023:
Change Recipe ingredients and instruction data type from String[] to String for better data storage
implement /recipe PUT and DELETE methods

TO DO LIST:
implement User --*> Recipe relationship
implement Whisper and DallE APIs
error handling for OpenAI APIs