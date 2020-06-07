from socket import *

import mouse

import autopy

serverPort = 4545

serverSocket = socket(AF_INET, SOCK_DGRAM)
# blank quotation b/c we can't change the ip
serverSocket.bind(('', serverPort))
print('The server is ready')
codes = {
   '112',
   '113',
   '114',
   '115',
   '116',
   '117',
   '118',
   '119',
   '120',
   '121',
   '122',
   '123',
   '18',
   '8',
   '20',
   '17',
   '127',
   '40',
   '35',
   '27',
   '37',
   '524',
   '34',
   '33',
   '10',
   '39',
   '16',
   '32',
   '38',
   '130',
   'c',
   'rc'
}

while True:
   # clientAddress is ip and port of client
   message, clientAddress = serverSocket.recvfrom(1024)
   modifiedMessage = message.decode()
   serverSocket.sendto(modifiedMessage.encode(), clientAddress)
   if modifiedMessage in codes:
      if modifiedMessage == '112':
         autopy.key.tap(autopy.key.Code.F1)
      elif modifiedMessage == '113':
         autopy.key.tap(autopy.key.Code.F2)
      elif modifiedMessage == '114':
         autopy.key.tap(autopy.key.Code.F3)
      elif modifiedMessage == '115':
         autopy.key.tap(autopy.key.Code.F4)
      elif modifiedMessage == '116':
         autopy.key.tap(autopy.key.Code.F5)
      elif modifiedMessage == '117':
         autopy.key.tap(autopy.key.Code.F6)
      elif modifiedMessage == '118':
         autopy.key.tap(autopy.key.Code.F7)
      elif modifiedMessage == '119':
         autopy.key.tap(autopy.key.Code.F8)
      elif modifiedMessage == '120':
         autopy.key.tap(autopy.key.Code.F9)
      elif modifiedMessage == '121':
         autopy.key.tap(autopy.key.Code.F10)
      elif modifiedMessage == '122':
         autopy.key.tap(autopy.key.Code.F11)
      elif modifiedMessage == '123':
         autopy.key.tap(autopy.key.Code.F12)
      elif modifiedMessage == '18':
         autopy.key.tap(autopy.key.Code.ALT)
      elif modifiedMessage == '8':
         autopy.key.tap(autopy.key.Code.BACKSPACE)
      elif modifiedMessage == '17':
         autopy.key.tap(autopy.key.Code.CONTROL)
      elif modifiedMessage == '8':
         autopy.key.tap(autopy.key.Code.DELETE)
      elif modifiedMessage == '40':
         autopy.key.tap(autopy.key.Code.DOWN_ARROW)
      elif modifiedMessage == '27':
         autopy.key.tap(autopy.key.Code.ESCAPE)
      elif modifiedMessage == '36':
         autopy.key.tap(autopy.key.Code.HOME)
      elif modifiedMessage == '37':
         autopy.key.tap(autopy.key.Code.LEFT_ARROW)
      elif modifiedMessage == '91':
         autopy.key.tap(autopy.key.Code.META)
      elif modifiedMessage == '40':
         autopy.key.tap(autopy.key.Code.PAGE_DOWN)
      elif modifiedMessage == '48':
         autopy.key.tap(autopy.key.Code.PAGE_UP)
      elif modifiedMessage == '13':
         autopy.key.tap(autopy.key.Code.RETURN)
      elif modifiedMessage == '39':
         autopy.key.tap(autopy.key.Code.RIGHT_ARROW)
      elif modifiedMessage == '16':
         autopy.key.tap(autopy.key.Code.SHIFT)
      elif modifiedMessage == '32':
         autopy.key.tap(autopy.key.Code.SPACE)
      elif modifiedMessage == '38':
         autopy.key.tap(autopy.key.Code.UP_ARROW)
      elif modifiedMessage == 'c':
         mouse.click('left')
      elif modifiedMessage == 'rc':
         mouse.click('right')
   else:
      autopy.key.type_string(modifiedMessage)
