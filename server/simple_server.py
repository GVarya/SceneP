from flask import Flask, request
import json
server = Flask(__name__)

commands = [

]

@server.route('/')
def info_page():
    return "<h1>DMX Server</h1>"


@server.route('/add', methods=['POST'])
def add_command():
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
def addScene_command():
    j_scene = request.values.get("params")
    scene = json.loads(j_scene)
    lamps = scene["lamps"]
    #print(lamps)
    for i in range(len(lamps)):
        commands.append(lamps[i])
        #print(lamps[i])
    #print(j_scene)
    return f'''Add command c:{scene["name"]} i:{lamps}'''




@server.route('/get_commands')
def get_commands():
    result = []
    for i in range(len(commands)):
        result.append(commands.pop(0))
    return {
        "commands": result
    }


server.run(host="0.0.0.0", port=5000)