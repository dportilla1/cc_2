import random
import json

loops = 250
file_name = f"benchmark/places_{loops}.json"

def rand_coords():
    latitude = random.uniform(-90, 90)
    longitude = random.uniform(-180, 180)
    return latitude, longitude

data = {
    "requestType": "tour",
    "earthRadius": 6371,
    "response": 1,
    "formula": "cosines",
    "places": []
}

for _ in range(loops):
    lat, lon = rand_coords()
    entry = {
        "name": "unknown",
        "latitude": str(lat),  
        "longitude": str(lon) 
    }
    data["places"].append(entry)

with open(file_name, 'w') as json_file:
    json.dump(data, json_file, indent=4)
