import requests
import time
from tester import DmxPyTry
from DmxPy import DmxPy

POLL_PERIOD = 0.1 # S

last_time = 0

#port_name = input()

#COM8
#dmx = DmxPy(port_name,  log_actions=True)
dmx = DmxPyTry()

while True:
    response = requests.get('http://127.0.0.1:5000/get_commands')

    commands = response.json()['commands']
    if len(commands) > 0:
        for command in commands:
            #print(command["channel"], ":", command["intensity"])
            print(command["channel"], ":", command["intensity"])
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

    time.sleep(POLL_PERIOD)
