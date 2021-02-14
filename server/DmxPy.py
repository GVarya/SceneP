import sys, time
import serial

import sys, time
import serial

DMXOPEN = chr(126)
DMXCLOSE = chr(231)
DMXINTENSITY = chr(6) + chr(1) + chr(2)
DMXINIT1 = chr(3) + chr(2) + chr(0) + chr(0) + chr(0)
DMXINIT2 = chr(10) + chr(2) + chr(0) + chr(0) + chr(0)


class DmxPy:
	def __init__(self, serialPort):
		try:
			# Only one should be uncommented!!!
			#self.serial=serial.Serial(serialPort, baudrate=57600, stopbits=serial.STOPBITS_TWO) # включает второй
			#self.serial = serial.Serial(serialPort, baudrate=250000, stopbits=serial.STOPBITS_TWO) #не включает ничего
		# self.serial=serial.Serial(serialPort, baudrate=9600, stopbits=serial.STOPBITS_TWO) #не включает ничего
		# self.serial=serial.Serial(serialPort, baudrate=115200, stopbits=serial.STOPBITS_TWO)
		except:
			print("Error: could not open Serial Port")
			sys.exit(0)
		self.serial.write((DMXOPEN + DMXINIT1 + DMXCLOSE).encode())
		self.serial.write((DMXOPEN + DMXINIT2 + DMXCLOSE).encode())

		self.dmxData = [chr(0)] * 513  # 128 plus "spacer".

	def setChannel(self, chan, intensity):
		if chan > 512: chan = 512
		if chan < 0: chan = 0
		if intensity > 255: intensity = 255
		if intensity < 0: intensity = 0
		self.dmxData[chan] = chr(intensity)

	def blackout(self):
		for i in range(1, 512, 1):
			self.dmxData[i] = chr(0)

	def render(self):
		sdata = ''.join(self.dmxData)
		self.serial.write((DMXOPEN + DMXINTENSITY + sdata + DMXCLOSE).encode())

