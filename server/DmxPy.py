import sys, time
import serial

# DMXOPEN = chr(126)
DMXOPEN = chr(0) + chr(0)
# DMXCLOSE = chr(231)
DMXCLOSE = chr(0) + chr(0)

DMXINTENSITY = chr(6) + chr(1) + chr(2)
DMXINIT1 = chr(3) + chr(2) + chr(0) + chr(0) + chr(0)
DMXINIT2 = chr(10) + chr(2) + chr(0) + chr(0) + chr(0)

class DmxPy:
    def __init__(self, serialPort, log_actions=False):
        try:
            # Only one should be uncommented!!!
            # self.serial=serial.Serial(serialPort, baudrate=57600, stopbits=serial.STOPBITS_TWO)
            self.serial = serial.Serial(serialPort, baudrate=250000, stopbits=serial.STOPBITS_TWO)
        # self.serial=serial.Serial(serialPort, baudrate=9600, stopbits=serial.STOPBITS_TWO)
        # self.serial=serial.Serial(serialPort, baudrate=115200, stopbits=serial.STOPBITS_TWO)
        except:
            print("Error: could not open Serial Port")
            sys.exit(0)
        self._log_actions = log_actions
        # self.serial.write((DMXOPEN+DMXINIT1+DMXCLOSE).encode())
        # self.serial.write((DMXOPEN+DMXINIT2+DMXCLOSE).encode())

        # self.dmxData=[chr(0)] * 513   #128 plus "spacer".
        self.dmxData = [0] * 513  # 128 plus "spacer".

    def setChannel(self, chan, intensity):
        if chan > 512: chan = 512
        if chan < 0: chan = 0
        if intensity > 255: intensity = 255
        if intensity < 0: intensity = 0
        # self.dmxData[chan] = chr(intensity)
        self.dmxData[chan] = intensity
        if self._log_actions:
            print("Preset for channel {} intensity {}".format(chan, intensity))

    def blackout(self):
        for i in range(1, 512, 1):
            self.dmxData[i] = 0

        print("Preset blackout")

    def render(self):
        # sdata=''.join(self.dmxData)
        # self.serial.write((DMXOPEN+DMXINTENSITY+sdata+DMXCLOSE).encode())
        # data_raw = (sdata).encode("ASCII")
        if self._log_actions:
            print(self.dmxData)
        self.serial.send_break(
            0.000176)  # Does not work properly https://www.raspberrypi.org/forums/viewtopic.php?t=239406
        # self.serial.break_condition = True
        time.sleep(0.000044)  # MAB - Mark after break
        self.serial.write(bytes(self.dmxData))


    def allOn(self, intensity):
            if intensity > 255: intensity = 255
            if intensity < 0: intensity = 0
            # self.dmxData[chan] = chr(intensity)
            for i in range(512):
                self.dmxData[i] = intensity