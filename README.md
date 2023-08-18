# How to run locally

`docker-compose up`

# How to run with rebuild

`docker-compose down && docker-compose build --no-cache && docker-compose up`

# How to get user token

1. Open the [Mattermost webiste](https://mattermost.pyn.ru/hhru/)
2. Open the website console
3. Run this command in the console:

   ```
   String(document.cookie).split(';').find((c) => c.includes('MMUSERID')).trim().split('=')[1]
   ```
4. Copy console output to the user token field
