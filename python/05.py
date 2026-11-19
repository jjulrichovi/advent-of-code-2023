import math
import numpy as np
import re
from pathlib import Path

input_file = Path(__file__).resolve().parent.parent / 'input' / '05l.txt'
[duration, record] = [map(int, re.findall(r'\d+', line)) for line in input_file.read_text().splitlines()]
print(math.prod([sum (1 for w in range(d + 1) if w*(d - w) > r) for d, r in zip(duration, record)]))

[duration, record] = [int(''.join(re.findall(r'\d', line))) for line in input_file.read_text().splitlines()]
[w1, w2] = sorted(np.roots([1, -duration, record]))
w1, w2 = int(np.ceil(w1)), int(np.trunc(w2)) 
print(w2 - w1 + 1)
