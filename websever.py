#!/usr/bin/python
# -*- coding: UTF-8 -*-

from socket import *
import sys


def handleRequest(tcpSocket):
    # 1. Receive request message from the client on connection socket

    #  3. Read the corresponding file from disk
    # 4. Store in temporary buffer
    # 5. Send the correct HTTP response error
    # 6. Send the content of the file to the socket
    # 7. Close the connection socket
    print('Ready to serve...')
    connectionSocket, addr = tcpSocket.accept()  # 接收到客户连接请求后，建立新的TCP连接套接字
    try:
        message = connectionSocket.recv(1024)  # 获取客户发送的报文
        filename = message.split()[1]
        f = open(filename[1:])
        outputdata = f.read();
        # Send one HTTP header line into socket
        header = ' HTTP/1.1 200 OK\nConnection: close\nContent-Type: text/html\nContent-Length: %d\n\n' % (
            len(outputdata))
        connectionSocket.send(header.encode())

        # Send the content of the requested file to the client
        for i in range(0, len(outputdata)):
            connectionSocket.send(outputdata[i].encode())
        connectionSocket.close()
    except IOError:
        # Send response message for file not found
        header = ' HTTP/1.1 404 Found'
        connectionSocket.send(header.encode())

        # Close client socket
        connectionSocket.close()

    pass  # Rem
    # ove/replace when function is complete


def startServer(serverAddress, serverPort):
    # 1. Create server socket
    serverSocket = socket(AF_INET, SOCK_STREAM)
    # 2. Bind the server socket to server address and server port
    serverSocket.bind((serverAddress, serverPort))
    # 3. Continuously listen for connections to server socket
    serverSocket.listen(1)
    # 4. When a connection is accepted, call handleRequest function, passing new connection socket
    while True:
        handleRequest(serverSocket)
    #  5. Close server socket
        pass  # Remove/replace when function is complete


startServer("127.0.0.1", 8000)
