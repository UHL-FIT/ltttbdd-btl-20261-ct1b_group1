import os
import re

directory = r"D:\BaitapAS\baitapkotlin\app\src\main\java\com\example\dattuadulich"
pattern = re.compile(r'(?<!import\s)(?<!package\s)(?:androidx\.|android\.|java\.)[a-zA-Z0-9_.]+')

found = False
for root, _, files in os.walk(directory):
    for file in files:
        if file.endswith(".kt"):
            filepath = os.path.join(root, file)
            with open(filepath, 'r', encoding='utf-8') as f:
                lines = f.readlines()
                for i, line in enumerate(lines):
                    if not line.strip().startswith('import') and not line.strip().startswith('package'):
                        matches = pattern.findall(line)
                        if matches:
                            print(f"{filepath}:{i+1}: {line.strip()}")
                            found = True

if not found:
    print("No fully qualified names found.")
