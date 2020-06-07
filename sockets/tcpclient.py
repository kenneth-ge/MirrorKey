from socket import *
serverName = 'google.com'
serverPort = 80

clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName, serverPort)) #handshake

sentence = """GET / HTTP/1.1\r\nHost: google.com\r\nContent-Type: application/json\r\nContent-Length: 47\r\n\r\n{"capabilities": {}, "desiredCapabilities": {}}"""

print(sentence)

clientSocket.send(sentence.encode())

print('sent')
modifiedSentence = clientSocket.recv(2048)

print(modifiedSentence.decode())
clientSocket.close()