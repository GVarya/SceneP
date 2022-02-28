import json
from random import randint
from pathlib import Path

path = Path('random.json')
data = json.loads(path.read_text(encoding='utf-8'))
for i in range(50):
    data['steps'].append({"intensities": [randint(0, 256), randint(0,256), randint(0,256), randint(0,256), randint(0,256), randint(0,256), randint(0,256), randint(0,256), randint(0,256), randint(0,256), randint(0,256), randint(0,256)], "delay-s": 0.1})
path.write_text(json.dumps(data, indent=4), encoding='utf-8')
print(data)

#Генерирует последовательность случайных яркостей для спецэффекта "Случайное"