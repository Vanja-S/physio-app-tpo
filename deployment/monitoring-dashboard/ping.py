import requests
import mysql.connector
import logging

class ping():
    def __init__(self, url: str):
        self.url = url

    def ping(self) -> bool:
        try:
            up = requests.get(self.url).status_code == 200
            return up
        except requests.ConnectionError as e:
            logging.error(f"ERROR: {self.url} is not reachable, {e}")
            return False


def ping_database() -> bool:
    try:
        connection = mysql.connector.connect(host="db",
                                         database='fizio',
                                         user='fizioUser',
                                         password='fizioUser',
                                         auth_plugin='caching_sha2_password')
        if connection.is_connected():
            connection.close()
            return True
    except mysql.connector.Error as e:
        logging.error(f"ERROR: While connecting to MySQL, {e}")
        return False