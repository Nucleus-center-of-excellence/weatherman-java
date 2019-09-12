 # stage 1
FROM node as node
WORKDIR /app
COPY ./Frontend .
RUN npm ci


ARG NG_ENV=production
RUN npm run ng build -- --configuration=$NG_ENV

# RUN npm run ng build --configuration=$NG_ENV

# ENTRYPOINT [ "ng","serve"]  
# stage 2
FROM nginx:alpine
COPY ./Frontend/nginx.conf /etc/nginx/nginx.conf
COPY --from=node /app/dist/weatherManApp /usr/share/nginx/html
# CMD [ "npm", "start" ]
