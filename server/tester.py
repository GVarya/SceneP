from tkinter import *
import threading
import logging

logging.basicConfig(level=logging.INFO)

class DmxPyTry:
    def __init__(self):
        self.root = Tk()
        self.dmxData = [0] * 512
        for i in range(512):
            self.dmxData[i] = Label(self.root, text="{:03d}: ooo".format(i), bg="#000000", fg="#ffffff")
            self.dmxData[i].grid(row=i%16, column=i//16)

    def setChannel(self, chan, intensity):

        if chan > 512: chan = 512
        if chan < 0: chan = 0
        if intensity > 255: intensity = 255
        if intensity < 0: intensity = 0
        logging.info("Set intensity {} for channel {}".format(intensity, chan))
        global frame
        #frame = Frame(root, color=root.rgba(10, 0, 0, intensity / 255))
        self.dmxData[chan].configure(background="#{:02x}{:02x}{:02x}".format(255, intensity, intensity//3))
        self.dmxData[chan].configure(text="{:03d}: {:03d}".format(chan, intensity))



    def blackout(self):
        for i in range(512):
            self.dmxData[i].configure(background="black")
            self.dmxData[i].configure(text="{:03d}: ooo".format(i))



    def render(self):
        self.root.update()

    def OnAll(self, intensity):
        for i in range(512):
            self.dmxData[i].configure(background="#{:02x}{:02x}{:02x}".format(255, intensity, intensity // 3))
            self.dmxData[i].configure(text="{:03d}: {:03d}".format(i, intensity))
