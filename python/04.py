import re
from pathlib import Path

input_file = Path(__file__).resolve().parent.parent / 'input' / '04l.txt'
total, counts = 0, {}
for game in input_file.read_text().splitlines():
    no, card = game.split(':')
    [no] = map(int, re.findall(r'\d+', no))
    counts[no] = counts.get(no, 0) + 1 
    winning, have = [re.findall(r'\d+', s) for s in card.strip().split('|')]
    p = sum([1 for number in have if number in winning])
    if p > 0: total += pow(2, p - 1)
    for i in range(no + 1, no + p + 1): counts[i] = counts.get(i, 0) + counts[no]
print(total, ' ', sum(counts.values()))