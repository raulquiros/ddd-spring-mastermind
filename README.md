# Mastermind


## Endpoints

### /mastermind/create - [POST]

#### Parameters
- `color1`  
- `color2` 
- `color3` 
- `color4` 

#### Response
- `400` - Bad request. 
- `200` - Game was created. `{"id": "string", "attempts": "int"}`



### /mastermind/{ID} - [POST]

#### Parameters
- `color1` 
- `color2` 
- `color3` 
- `color4` 

#### Response
- `400` - Bad request. `{"message": "reason"}`
- `200` - Game result. `{"color1": "String", "color2": "String", "color3": "String", "color4": "String" "attempts": "int"}`
    > Responses
    Black (B): Color and position are correct.
    White (W): Color is correct but incorrect position.
    Null: Color is not in the game.
    >


### /mastermind/{ID} - [GET]

#### Parameters
- `id`  

#### Response
- `400` - Bad request. 
- `200` - Game result.