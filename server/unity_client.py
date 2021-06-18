import socket
import sys
import time
import requests
import random

# Create a TCP/IP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Connect the socket to the port where the server is listening
server_address = ('localhost', 8052)
print('connecting to %s port %s' % server_address)
sock.connect(server_address)
print('connected')
message = ""
while message != "stop":
    try:
        message = str(random.randint(1, 5)) + " " + str(random.randint(1, 255))
        print('sending %s' % message)
        sock.sendall(message.encode())
        time.sleep(0.1)
    
        # response = requests.get('http://127.0.0.1:5000/get_commands')

        # commands = response.json()['commands']
        # if len(commands) > 0:
            # for command in commands:
                # print(command["channel"], ":", command["intensity"])
                # message = f"{command['channel']} {command['intensity']}"
                # print(message)
        # #message = str(random.randint(1, 5)) + " " + str(random.randint(1, 255))
                # print('sending %s' % message)
                # sock.sendall(message.encode())
                # time.sleep(0.1)
    except Exception as e:
        print("error: ", e)
        break
print('closing socket')
sock.close()