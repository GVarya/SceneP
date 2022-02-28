import requests
import time
#from tester import DmxPyTry
from DmxPy import DmxPy
from unity_tester import DmxPyTry

POLL_PERIOD = 0.01 # S

last_time = 0

#port_name = input() # Нужно раскоментировать если запускать с незнакомого компьютера (на реальном оборудование) и ввести название com порта (см. в диспетчере устройств).
#port_name = "COM8" # Сюда можно вписать название com порта, если он часто используется.
#COM8 # Название порта 
#dmx = DmxPy(port_name,  log_actions=True) # Создание объекта для отправки dmx сигнала(реальное оборудование).
dmx = DmxPyTry() # Создание объекта для управления эмулятором


while True: # В бесконечном  цикле постоянно посылаются запросы на сервер и считывается массив команд для прожекторов.
    response = requests.get('http://127.0.0.1:5000/get_commands')

    commands = response.json()['commands']
    if len(commands) > 0:
        for command in commands: # Цикл по массиву команд. В нём применяются команды.
            print(command["channel"], ":", command["intensity"])
            #print(command["channel"], ":", command["intensity"])
            if command["channel"] == "all":
                if int(command["intensity"]) > 0:
                    dmx.allOn(int(command["intensity"]))
                else:
                    dmx.blackout()
            # elif command["channel"] == "blackout":
            #     dmx.blackout()

            else:
                dmx.setChannel(chan=int(command["channel"]),
                               intensity=int(command["intensity"]))

        dmx.render()
    dmx.render()

    time.sleep(POLL_PERIOD)

