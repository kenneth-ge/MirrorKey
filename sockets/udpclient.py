from socket import *
serverName = '255.255.255.255'
serverPort = 4545
# 1.2.3.4 - IPv4
# 2607:f8b0:4006:804::200e - IPv6
'''IPv4'''
'''UDP'''
clientSocket = socket(AF_INET, SOCK_DGRAM);
clientSocket.setsockopt(SOL_SOCKET, SO_BROADCAST, 1)
message = 'hello'
clientSocket.sendto(message.encode(), (serverName, serverPort))
# message.encode() - converts to raw binary bits (e.g. 10011011)
'''Number of bits of data'''