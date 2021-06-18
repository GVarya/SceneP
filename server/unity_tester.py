import threading
import logging
import socket

logging.basicConfig(level=logging.INFO)
server_address = ('localhost', 8052)


class DmxPyTry:
    def __init__(self):
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        logging.info(f"connecting to {server_address[0]} port {server_address[1]}")
        self.sock.connect(server_address)
        logging.info(f'connected')
        self.message = ""

    def setChannel(self, chan, intensity):
        if chan > 512: chan = 512
        if chan < 0: chan = 0
        if intensity > 255: intensity = 255
        if intensity < 0: intensity = 0
        self.message = f"{chan} {intensity}"
        self.sock.sendall(self.message.encode())


    def blackout(self):
        for i in range(512):
            self.message = f"{i} {0}"
            self.sock.sendall(self.message.encode())


    def render(self):
        pass

    def allOn(self, intensity):
        for i in range(512):
            self.message = f"{i} {255}"
            self.sock.sendall(self.message.encode())
