import requests

url = "http://localhost:8081/auth"
headers = {
    "username": "admin@admin.com",
    "password": "1234"
}

response = requests.get(url, headers=headers)

print(response.status_code)
print(response.text)
