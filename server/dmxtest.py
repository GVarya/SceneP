from DmxPy import DmxPy
import time

dmx = DmxPy('COM8')

for i in range(512):
	dmx.setChannel(i, 200)
	dmx.render()
	time.sleep(0.01)
	print("chan: ", i)


'''
dmx.setChannel(1, 100)
dmx.setChannel(2, 50)
dmx.render()
time.sleep(2)
dmx.setChannel(3, 100)
dmx.render()
time.sleep(2)
dmx.blackout()
dmx.render()
'''