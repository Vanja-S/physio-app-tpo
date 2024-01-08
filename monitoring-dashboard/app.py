from sys import exc_info
from flask import Flask, render_template, request, redirect
from ping import ping, ping_database


# Ping objects
keycloak = ping("http://keycloak:8080")
server = ping("http://server:8081/swagger-ui/index.html#/")

app = Flask(__name__, static_url_path='/static')


def get_statuses():
    keycloak_up = "UP" if keycloak.ping() else "DOWN"
    server_up = "UP" if server.ping() else "DOWN"
    database_up = "UP" if ping_database() else "DOWN"
    return {"keycloak" : keycloak_up, "server" : server_up, "database" : database_up}



@app.route("/")
def index():
    up_status = get_statuses()
    return render_template('index.html', database_up=up_status["database"], keycloak_up=up_status["keycloak"],  server_up=up_status["server"])
        