from socket import *
serverPort = 12000

serverSocket = socket(AF_INET, SOCK_DGRAM)
# blank quotation b/c we can't change the ip
serverSocket.bind(('', serverPort))
print('The server is ready')

while True:
	# clientAddress is ip and port of client
	message, clientAddress = serverSocket.recvfrom(2048)
	modifiedMessage = message.decode().upper()
	serverSocket.sendto(modifiedMessage.encode(), clientAddress)