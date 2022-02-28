from DmxPy import DmxPy
import time

# Тестовая программа, была написана для проверки работают ли переходник и библиотека. В работе сервера не участвует.

dmx = DmxPy('COM8')

# for i in range(512):
# 	dmx.setChannel(i, 200)
# 	dmx.render()
# 	print("chan: ", i)

dmx.allOn(225)
dmx.render()
time.sleep(1)
dmx.blackout()
dmx.render()


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