# ChatRoom - CSC1004 Java Project

## 📝 Description

Welcome to ChatRoom, a chatting tool with Java client and web client, built using Java and SpringBoot! 🤩

We've made it easy for you to get started with ChatRoom. 

Our project includes a README file with instructions for setting up and running the project. We've also included a demo video to show you what it looks like in action.

We would love to hear your feedback on ChatRoom. Please let us know if you have any suggestions or comments. 🤝

Thanks for checking out ChatRoom! 🎉

## 📺 Demo Video 

[![ChatRoom Demo Video](https://i2.hdslb.com/bfs/archive/df395fcbacde96f8c05c46d3b019f9d96726cba0.jpg)](https://www.bilibili.com/video/BV1Yy4y1o7qn/)

## 📋 Features
Build with Java and SpringBoot, we have implemented the following features:
- [x] Login and register
- [x] Chat with friends
- [x] Support multiple users
- [x] Support client and web client (In the same chat room)

### 🏗️ How we built it
- Using Java and SpringBoot to build the server
  - Communication between the server and the web client: **SpringBoot's WebSocket**
  - Communication between the server and the Java client: **Java's Socket**
  - Database: **MySQL**
  - Manage the project: **Maven** and **Git**
  - IDE: **IntelliJ IDEA**
- Using Swing and FlatLaf to build the client

### 🚀 What's next for ChatRoom
- [ ] Add more features
  - [ ] Image and file transfer
  - [ ] Private chat
  - [ ] Chat history
  - [ ] Voice and Video chat
- [ ] Support more platforms

## 🔧 Installation 

To get started with ChatRoom, simply download the client.jar and run it, or open the web client directly.

### 🖥️ Client
Client is a Java application, so you need to install Java first. You can download the client.jar from [here](https://github.com/yuantuo666/CSC1004-Java-ChatRoom/releases) and run it.

Note: If you want to use your own server, you need to change the server address in `client/src/main/java/com/Client.java` and build it again.

### 🌐 Web Client
You can open the web client directly from [here](http://1.117.214.115:8080/).

Note: Web Client is intergrated with the server, so only need to install the server and run it, then you can open the web client: http://YOUR_HOST:8080

### 📦 Server
Server contains the database and the server, so you need to install MySQL and Java first.

After installing MySQL, you need to create a database named `chatroom` and import the database.sql from [here](https://github.com/yuantuo666/CSC1004-Java-ChatRoom/releases).

You can download the server.jar from [here](https://github.com/yuantuo666/CSC1004-Java-ChatRoom/releases) and run it by `java -jar server.jar`.

Note: If you want to use your own server, simply make sure port **8080** and **9001** are open. Web client will connect to the server through port **8080**, and Java client is **9001**.

## 📖 Usage 

Once you have installed the client or opened the web client, you can register and login, then start chatting with your friends.

## 🤝 Contributing 

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

