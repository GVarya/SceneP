from flask import Flask, request
import json
import threading
import queue

from effect_maker import effects_thread, get_effects_names

# Сервер. Осуществляет обмен данными между мобильной программой и компьютерной.

server = Flask(__name__)

commands = []

super_effects_commands = queue.Queue()


print("server run")


@server.route('/')
def info_page():
    print("/")
    return "<h1>DMX Server</h1>"


@server.route('/add', methods=['POST'])
def add_command(): # Считывет сигналы для прожекторов и добавляет в общий массив команд.
    print("spotlight")
    j_lamp = request.values.get("params")
    print(j_lamp)
    lamp = json.loads(j_lamp)
    # channel = content["channel"]
    # print(content["channel"])
    # intensity = content['intensity']
    # print(content['intensity'])
    commands.append(lamp)
    # print(commands)
    # return f'''Add command c:{channel} i:{intensity}'''
    return f''''''


@server.route('/addScene', methods=['POST'])
def addScene_command(): # Считывает сигналя для сцен, разбирает на отдельные команды для прожекторов и добавляет в общий массив команд.
    print("scene")
    j_scene = request.values.get("params")
    scene = json.loads(j_scene)
    lamps = scene["lamps"]
    #print(lamps)
    for i in range(len(lamps)):
        commands.append(lamps[i])
        #print(lamps[i])
    #print(j_scene)
    return f'''Add command c:{scene["name"]} i:{lamps}'''



@server.route('/addSuperEffect', methods=['POST'])
def addSuperEffect_command(): # Считывает сигналы для эффектов и отправляет в effect_maker.
    print("superEffect")
    j_f_command = request.values.get("params")
    f_command = json.loads(j_f_command)
    name = f_command["filterName"]
    command = f_command["command"]
    #print(name, command)
    super_effects_commands.put(f_command)
    return f'''Add name n:{name} command c:{command} '''


@server.route('/get_commands')
def get_commands(): # Отправляет текущий массив команд.
    print("getC")
    result = []
    for i in range(len(commands)):
        result.append(commands.pop(0))
    return {
        "commands": result
    }


@server.route('/getFiltersName', methods=['POST'])
def getFiltersName(): # Отправляет названия всех существующих эффектов.
    print("getFN")
    result = get_effects_names()

    return {
        "names": result
    }

t = threading.Thread(target=effects_thread, args=(super_effects_commands, commands,))
t.start()
server.run(host="0.0.0.0", port=5000)