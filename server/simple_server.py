from flask import Flask, request
server = Flask(__name__)

commands = [

]

@server.route('/')
def info_page():
    return "<h1>DMX Server</h1>"


@server.route('/add')
def add_command():
    channel = (request.args["channel"])
    intensity = int(request.args["intensity"])
    commands.append({"channel" : channel, "intensity" : intensity})
    print(commands)
    return f'''Add command c:{channel} i:{intensity}'''


@server.route('/get_commands')
def get_commands():
    result = []
    for i in range(len(commands)):
        result.append(commands.pop(0))
    return {
        "commands": result
    }


server.run(host="0.0.0.0", port=5000)