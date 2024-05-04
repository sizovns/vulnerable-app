## The story

We've initiated a bug bounty program for our internal SWAG-shop to bolster our system's security. As part of our ongoing enhancements, we transitioned our admin panel from an old servlet technology to a Spring services.

To fortify our defenses, we've taken measures such as disabling login functionality on `/admin` and securing other servlets behind our proxy server. User management is now streamlined through `/api`, a dedicated Spring application.

In addition, we've introduced a new `/bff` service tailored to our frontend team's needs. Please note that our frontend is currently in the MVP development stage, so we kindly request that any frontend bugs be withheld from reporting.

Instead, we encourage you to focus on reviewing our configurations and APIs for any potential vulnerabilities. Your assistance in ensuring our system's security is greatly appreciated.

### Help
You can find our known vulnerabilities in [HELP.md](./HELP.md)