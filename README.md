# Java-text-server

## Features

* Support for multiple simultaneous users;
* You can slide into other users DMs;
* Listing of all the current users;
* Listing of all the available commands and what they do;

## Commands

```
whisper <username>: Allows you to send a private message to someone;

list: Lists all the currently online users;

commands: Lists all the available commands and how to use them;

quit: Makes you exit the server and alerts all the other users;
```

## Setup

You want to make sure you have a JDK installed in your machine 

With the jar file already installed, run the following command on the terminal:

```
java -jar Java-text-server.jar
```

With the jar file, you will be running a server, so need to actually connect to it. For that, use the following command on another terminal tab or machine:

```
nc <ip adress of the machine or localhost, if you are using the same one> 9000
```