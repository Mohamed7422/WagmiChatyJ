# Java Chat Application

A simple client-server chat application built with Java Swing GUI and socket programming.

## Features

- Real-time messaging between client and server
- Clean and intuitive GUI interface
- Multi-threaded architecture for handling concurrent operations
- Socket-based TCP communication

## Technologies Used

- Java SE
- Java Swing (GUI)
- Java Sockets (Networking)
- Multi-threading

## Project Structure
```
chat-application/
├── src/
│   └── chat/
│       ├── chat_server.java      # Server application
│       ├── chat_server.form      # Server GUI form
│       ├── chat_client.java      # Client application
│       └── chat_client.form      # Client GUI form
└── README.md
```

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- IntelliJ IDEA (recommended) or any Java IDE

## How to Run

### Step 1: Compile the Project

If using command line:
```bash
javac chat/chat_server.java
javac chat/chat_client.java
```

If using IntelliJ IDEA:
- Build → Build Project (Ctrl+F9)

### Step 2: Start the Server

**Using IntelliJ:**
1. Right-click on `chat_server.java`
2. Select "Run 'chat_server.main()'"
3. Wait for "Waiting for client..." message

**Using command line:**
```bash
java chat.chat_server
```

### Step 3: Start the Client

**Using IntelliJ:**
1. Right-click on `chat_client.java`
2. Select "Run 'chat_client.main()'"

**Using command line:**
```bash
java chat.chat_client
```

### Step 4: Start Chatting!

- Type your message in the text field
- Click "Send" button or press Enter
- Messages will appear in both server and client windows

## Configuration

- **Port:** 8080 (default)
- **Host:** localhost

To change the port, modify this line in both files:
```java
// Server
ServerSocket serverSocket = new ServerSocket(8080);

// Client
Socket guest = new Socket("localhost", 8080);
```

## Screenshots

*Add screenshots of your application here*

## How It Works

### Server Side
1. Creates a ServerSocket listening on port 8080
2. Waits for client connection
3. Accepts connection and creates input/output streams
4. Spawns a thread to continuously listen for client messages
5. Sends messages to client when user clicks "Send"

### Client Side
1. Connects to server via Socket on localhost:8080
2. Creates input/output streams
3. Spawns a thread to continuously listen for server messages
4. Sends messages to server when user clicks "Send"

## Architecture
```
┌─────────────┐                    ┌─────────────┐
│   Server    │                    │   Client    │
│             │                    │             │
│  ┌───────┐  │                    │  ┌───────┐  │
│  │  GUI  │  │                    │  │  GUI  │  │
│  └───┬───┘  │                    │  └───┬───┘  │
│      │      │                    │      │      │
│  ┌───▼───┐  │    Socket (8080)   │  ┌───▼───┐  │
│  │Thread │◄─┼────────────────────┼─►│Thread │  │
│  └───────┘  │                    │  └───────┘  │
│             │                    │             │
└─────────────┘                    └─────────────┘
```

## Known Issues

- Server can only handle one client at a time
- No message history persistence
- No user authentication

## Future Enhancements

- [ ] Support multiple clients simultaneously
- [ ] Add user authentication
- [ ] Save chat history to file
- [ ] Add emoji support
- [ ] Implement private messaging
- [ ] Add file transfer capability
- [ ] Improve error handling and reconnection logic

## Troubleshooting

**Problem: "Connection refused"**
- Make sure the server is running before starting the client
- Check if port 8080 is available

**Problem: "Address already in use"**
- Another application is using port 8080
- Kill the process or change the port number

**Problem: "Client disconnected immediately"**
- Check network connectivity
- Verify server is listening on correct port

## Contributing

Feel free to fork this project and submit pull requests with improvements!

## License

This project is open source and available under the [MIT License](LICENSE).

## Author

Mohamed Hussien - [Your GitHub Profile](https://github.com/Mohamed7422)

## Acknowledgments

- Java Socket Programming Documentation
- Java Swing Tutorial