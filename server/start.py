import subprocess
import time


pserver = subprocess.Popen(['python', 'simple_server.py'])
pworker = subprocess.Popen(['python', 'dmx_worker.py'])


time.sleep(5)
print("Press any key to stop server and dmx service...")
_ = input()
