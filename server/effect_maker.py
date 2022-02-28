import os
import json
import time

# Здесь осуществляется работа с эффетами. С мобильной программы приходят не сами команды, а названия эффектов, сервер отправляет сюда эти названия, программа соотносит название эффекта с самим эффектом и добавляет его в общий массив команд.

directory = 'superEffects'
files = os.listdir(directory)

effects = {} 
print("FFFMMM work")
for file in files:
    if not ".json" in file:
        continue
    with open(f"{directory}\\{file}", "r") as f:
        print("Loading", file)
        data = json.load(f)
        name = data["name"]
        steps = data["steps"]
        effects[name] = data


def get_effects_names(): # Возвращает названия всех существующих эффектов.
    print(effects.keys())
    return sorted(effects.keys())


def apply_step(effect_name, step_num, dmx_worker_commands): # Добавляет команду эффекта в основной массив команд.
    try:
        print("apply_step on")
        current_step = effects[effect_name]["steps"][step_num]
        for i in range(len(current_step["intensities"])):
            dmx_worker_commands.append({"channel": i, 'intensity': current_step["intensities"][i]})
            print(i, current_step["intensities"][i])
        time.sleep(current_step["delay-s"])
    except:
        print(f"you have some problems with your effect name: {effect_name} is not correct")

def effects_thread(effect_commands, dmx_worker_commands): # Разбирает эффект на команды и отправляет в apply_step().
    print("thread f work")
    current_effect_name = None
    current_step = 0
    steps_total = 0
    while True:
        if not effect_commands.empty():
            command = effect_commands.get()
            if command["command"] == False:
                current_effect_name = None
                print("stopping effect")
            else:
                if command["filterName"] in effects:
                    print("starting effect")
                    current_effect_name = command["filterName"]
                    current_step = 0
                    steps_total = len(effects[current_effect_name]["steps"])

        if current_effect_name is None:
            continue

        apply_step(current_effect_name, current_step, dmx_worker_commands)
        current_step += 1
        if current_step >= steps_total:
            print("effect finished")
            current_step = 0
            steps_total = 0
            current_effect_name = None

if __name__ == "__main__":
    print(get_effects_names())