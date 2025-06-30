from typing import Union

from fastapi import FastAPI

app = FastAPI()


@app.get("/")
def read_root():
    return {"Hello": "World"}


@app.get("/mock") # http://localhost:8000?lat=-34.7303025&lon=-58.268868&lang=sp&units=metric&appid=3270d0741d07c
def temperature_report(lat: str = -34.7303025, lon: str = -58.268868, lang: str = "sp", units: str = "metric", appid:str = "test"):
    return model_report()

def model_report():
    return {
        "main": model_temperature_embedded(),
        "name": "Quilmes"
    }

def model_temperature_embedded():
    return {
        "temp": 10.0
    }