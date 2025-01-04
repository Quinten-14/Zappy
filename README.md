# Zappy
A network based multiplayer game written in Java

## Data Formatting
The server and client will communicate with one another through sockets and tcp. The client will send its requests without waiting for their execution, the server sends back a message confirming or denying successful execution of the requests. 
The server also sends all board data to the graphic client.

### Client to Server
The client will send a string to the server which contains the name of the command and the life cost of the command, mostly referred to as the delay.
The format is as the following:
- `"<command-name>\n"`
  - The command name will be one word followed by the newline character. Any other combination of characters is an invalid request. 
  - Example: `"left\n"` 
    - This command would request the server to rotate the client 90°. 

### Server to Client
The server will respond to any client request with the right formatting. The server response will be built from the command's success followed by the data requested by the command.
The format is as the following:
- `"<command-success>\n<command-data>\n"`
  - The command success is one of the following strings followed by the newline character: ok, ko. This will be followed by the command's resulting data, if necessary. 
    - If the command data contains a list of results, which occurs when you run 'see' or 'inventory', the string will be surrounded by curly braces followed by the listed content where the listed content is seperated by a comma followed by a space.
    - If the command data contains a simple string, the string will be sent through as such. 
  - Example 'view' command: `"ok\n{food, player sibur, phiras phiras, }\n"`

### Server to Graphic Client
The server will send all the board data to the graphic client every game loop. This data will be used by the graphic client to create a graphical representation of what's going on in the game. This data will contain every tile on the board and its content. 
The format is as the following: 
- `"[y x <tile-content>]\n"`
  - The server will format the data tile by tile. The format will surround each tile with square braces, followed by the y and x coordinate of the tile, followed by the content of the tile seperated with a comma. Each tile will be seperated by the newline character.
  - Example for a 2x2 map: `"[0 0 player,sibur]\n[0 1 food]\n[1 0 ]\n[1 1 phiras,phiras]\n"`
  - 