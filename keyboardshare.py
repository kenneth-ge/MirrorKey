from socket import *
import keyboard

serverPort = 4545

serverSocket = socket(AF_INET, SOCK_DGRAM)
# blank quotation b/c we can't change the ip
serverSocket.bind(('', serverPort))
print('The server is ready')

# clientAddress is ip and port of client
message, clientAddress = serverSocket.recvfrom(2048)
modifiedMessage = message.decode().upper()

print(modifiedMessage)

ip, port = clientAddress

print(ip)

clientSocket = socket(AF_INET, SOCK_STREAM)
print(ip)
clientSocket.connect((ip, 4545)) #handshake

# todo: detect connection closed
while True:
	key = clientSocket.recv(2048).decode()

	keys = key.split(" ")
	if keys[0] == 'p':
		keyboard.press_and_release(keys[1])
	print(key)

clientSocket.close()
